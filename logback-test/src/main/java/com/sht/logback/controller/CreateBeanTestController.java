package com.sht.logback.controller;

import com.sht.logback.service.ICreateBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Aaron.H.Shen
 * @date 2023/5/19 13:15
 */
@RestController
@RequestMapping("create-bean")
public class CreateBeanTestController {

	@Resource(type = ICreateBeanService.class)
	private ICreateBeanService service;


	@GetMapping
	public String getkey(){
		return service.getString();
	}
}
