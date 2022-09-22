package com.sht.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author as2i
 * @date 2022/9/22 16:35
 */
@RestController
@RefreshScope
public class UserCloudController {

    @Value(value = "${spring.redis.host:s}")
    private String host;

    @GetMapping("getHost2")
    public String getHost() {
        return host;
    }

}
