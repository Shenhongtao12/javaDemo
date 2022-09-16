package com.sht.provider.controller;

import com.sht.serviceInterface.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author as2i
 * @date 2022/9/15 18:27
 */
@RestController
public class StudentController {

    @DubboReference(interfaceClass = IUserService.class)
    IUserService userService;



    @GetMapping(value = "/student/{name}")
    @ResponseBody
    public Object studentCount(@PathVariable String name){
        String str = userService.getUser();
        return "学生的 ："+str + " nick name : " + name;
    }
}
