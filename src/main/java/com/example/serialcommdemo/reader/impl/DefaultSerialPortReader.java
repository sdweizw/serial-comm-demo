package com.example.serialcommdemo.reader.impl;

import com.example.serialcommdemo.reader.SerialPortReader;

/**
 * @author Wei Zhanwei
 * @since 2023/3/17 17:15
 **/
public class DefaultSerialPortReader implements SerialPortReader {

    @Override
    public void read(String port, String msg) {
        String str = String.format("SerialPort[%s]:%s", port, msg);
        System.out.println(str);
    }
}
