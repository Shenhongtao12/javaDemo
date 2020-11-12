package com.sht.apimybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sht.apimybatis.Mapper")
public class ApiMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMybatisApplication.class, args);
    }

}
