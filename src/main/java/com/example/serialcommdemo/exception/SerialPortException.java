package com.example.serialcommdemo.exception;

/**
 * @author Wei Zhanwei
 * @since 2023/3/21 10:18
 **/
public class SerialPortException extends RuntimeException{

    public SerialPortException(String msg) {
        super(msg);
    }

    public SerialPortException(Exception exception) {
        super(exception);
    }
}
