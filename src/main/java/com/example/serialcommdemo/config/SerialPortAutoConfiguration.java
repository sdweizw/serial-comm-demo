package com.example.serialcommdemo.config;

import com.example.serialcommdemo.entity.SerialPortProperties;
import com.example.serialcommdemo.exception.SerialPortException;
import com.example.serialcommdemo.reader.SerialPortReader;
import com.example.serialcommdemo.reader.impl.DefaultSerialPortReader;
import com.example.serialcommdemo.util.NioUtil;
import com.example.serialcommdemo.util.StringUtil;
import com.example.serialcommdemo.writer.SerialPortWriter;
import com.example.serialcommdemo.writer.impl.DefaultSerialPortWriter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.vesalainen.comm.channel.SerialChannel;
import org.vesalainen.comm.channel.SerialSelector;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wei Zhanwei
 * @since 2023/2/26 14:38
 **/
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(SerialPortProperties.class)
public class SerialPortAutoConfiguration {

    private SerialPortProperties serialPortProperties;

    @Bean
    @ConditionalOnMissingBean
    public SerialPortReader serialPortReader() {
        return new DefaultSerialPortReader();
    }

    @Bean
    public List<SerialChannel> serialChannelList(SerialPortReader reader, ThreadPoolTaskExecutor executor) throws IOException {
        List<SerialChannel> list = serialPortProperties.getPorts().stream()
                .filter(serialPort -> StringUtil.isNotBlank(serialPort.getPort()))
                .map(serialPort -> {
                    try {
                        return new SerialChannel.Builder(serialPort.getPort(), serialPort.getSpeed()).setBlocking(false).get();
                    } catch (IOException e) {
                        throw new SerialPortException(e);
                    }
                }).collect(Collectors.toList());
        SerialSelector serialSelector = SerialSelector.open();
        list.forEach(serialChannel -> {
            try {
                serialChannel.register(serialSelector, SelectionKey.OP_READ);
            } catch (ClosedChannelException e) {
                throw new SerialPortException(e);
            }
        });
        this.serialPortRead(serialSelector, reader, executor);
        return list;
    }

    @Bean
    public SerialPortWriter serialPortWriter(List<SerialChannel> serialChannelList) {
        return new DefaultSerialPortWriter(serialChannelList, Charset.forName(serialPortProperties.getCharset()));
    }

    private void serialPortRead(SerialSelector selector, SerialPortReader reader, ThreadPoolTaskExecutor executor) {
        executor.submit(()-> {
            try {
                while (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()) {
                            SerialChannel channel = (SerialChannel)selectionKey.channel();
                            String string = NioUtil.read(channel, Charset.forName(serialPortProperties.getCharset()));
                            reader.read(channel.getPort(), string);
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception exception) {
                throw new SerialPortException(exception);
            }
        });
    }

}
