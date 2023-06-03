package com.sht.logback.controller;

import com.sht.logback.service.ICreateBeanService;
import com.sht.logback.utils.SpringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aaron.H.Shen
 * @date 2023/5/19 13:25
 */
@RestController
@RequestMapping("spring-test")
public class SpringTestController {

	@GetMapping
	public String getkey() {
		SpringUtils.getBean(ICreateBeanService.class).setString();
		return SpringUtils.getBean(ICreateBeanService.class).getString();
	}
}
