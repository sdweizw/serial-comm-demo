package com.example.serialcommdemo.writer.impl;

import com.example.serialcommdemo.exception.SerialPortException;
import com.example.serialcommdemo.util.NioUtil;
import com.example.serialcommdemo.writer.SerialPortWriter;
import lombok.AllArgsConstructor;
import org.vesalainen.comm.channel.SerialChannel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Wei Zhanwei
 * @since 2023/3/17 17:56
 **/
@AllArgsConstructor
public class DefaultSerialPortWriter implements SerialPortWriter {

    private final List<SerialChannel> serialChannelList;

    private final Charset charset;

    @Override
    public void write(String port, String msg) {
        serialChannelList.stream().filter(serialChannel -> serialChannel.getPort().equals(port)).findAny().ifPresent(serialChannel -> {
            try {
                NioUtil.write(serialChannel, msg, charset);
            } catch (IOException e) {
                throw new SerialPortException(e);
            }
        });
    }
}
