package com.sht.nacos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author as2i
 * @date 2022/9/22 15:05
 */
//@Configuration
public class NacosConfig {

    @Value(value = "${spring:redis:host}")
    public String host;
}
