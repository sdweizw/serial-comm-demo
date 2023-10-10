package com.example.serialcommdemo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wei Zhanwei
 * @since 2023/2/26 15:56
 **/
@Data
@ConfigurationProperties(prefix = "com.serial")
public class SerialPortProperties {

    private List<SerialPort> ports = new ArrayList<>();

    private String charset = "GBK";
}
