package com.example.serialcommdemo.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

/**
 * @author Wei Zhanwei
 * @since 2023/2/24 11:54
 **/
public class NioUtil {
    public static String read(ReadableByteChannel channel, Charset charset) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = new byte[0];
        while (channel.read(byteBuffer) > 0) {
            byte[] array = ArrayUtil.slice(byteBuffer.array(), 0, byteBuffer.position());
            bytes = ArrayUtil.concat(bytes, array);
            byteBuffer.clear();
        }
        return new String(bytes, charset);
    }

    public static void write(WritableByteChannel channel, String msg, Charset charset) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(charset));
        channel.write(byteBuffer);
        byteBuffer.clear();
    }
}
