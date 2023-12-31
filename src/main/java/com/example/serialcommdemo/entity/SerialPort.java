package com.example.serialcommdemo.entity;

import lombok.Data;

/**
 * @author Wei Zhanwei
 * @since 2023/2/26 15:53
 **/
@Data
public class SerialPort {

    private String port;

    private Integer speed = 9600;
}
