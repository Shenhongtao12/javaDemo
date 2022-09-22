package com.sht.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author as2i
 * @date 2022/9/22 15:00
 */
@RestController
public class UserController {

//    @Autowired
//    private NacosConfig nacosConfig;

    @NacosValue(value = "${spring.redis.host:s}", autoRefreshed = true)
    public String host;

    @GetMapping("getHost")
    public String getHost() {
        return host;
    }
}
