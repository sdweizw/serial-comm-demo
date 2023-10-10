package com.example.serialcommdemo.writer;

/**
 * @author Wei Zhanwei
 * @since 2023/3/17 17:53
 **/
public interface SerialPortWriter {

    /**
     * 向串口写数据接口
     * @param port 串口号
     * @param msg 信息
     */
    void write(String port, String msg);
}
