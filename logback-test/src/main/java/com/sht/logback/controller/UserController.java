package com.sht.logback.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author as2i
 * @date 2023/1/28 13:38
 */
@RequestMapping("user")
@RestController
@Slf4j
public class UserController {
	private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@GetMapping
	public Object get() {
		LocalDateTime now = LocalDateTime.now();
		log.info("系统级别日志：" + now);
		sys_user_logger.info("配置用户级别日志：" + now);
		LOGGER.error("错误级别日志：" + now);
		return now;
	}

}
