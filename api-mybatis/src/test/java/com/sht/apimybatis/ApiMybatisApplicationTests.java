package com.sht.apimybatis;

import com.sht.apimybatis.dto.GoodsDto;
import com.sht.apimybatis.service.GoodsService;
import com.sht.apimybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiMybatisApplicationTests {

    @Autowired
    private GoodsService goodsService;


    @Test
    void contextLoads() {
        GoodsDto goodsDto = goodsService.findById(2);
        System.out.println(goodsDto);
    }

    @Test
    void oneToOne() {
        System.out.println(goodsService.findByOne(2));
    }

   /* @Test
    void  findUserRoles() {
        System.out.println(userService.findUserRoles(5));
    }*/

}
