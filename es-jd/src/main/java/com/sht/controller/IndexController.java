package com.sht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aaron
 * @date 2020/11/2 22:44
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "/index";
    }
}
