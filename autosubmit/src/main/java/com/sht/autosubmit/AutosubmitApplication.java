package com.sht.autosubmit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.sht.autosubmit.mapper")
@EnableScheduling
public class AutosubmitApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutosubmitApplication.class, args);
    }

}
