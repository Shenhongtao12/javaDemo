package com.sht.provider;

import com.sht.provider.config.EmbeddedZooKeeper;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        new EmbeddedZooKeeper(2181, false).start();
        SpringApplication.run(ProviderApplication.class, args);
    }
}
