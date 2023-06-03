package com.sht.logback.service.impl;

import com.sht.logback.service.ICreateBeanService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Aaron.H.Shen
 * @date 2023/5/19 13:17
 */
@Service
public class CreateBeanServiceImpl implements ICreateBeanService {

	private String key;

	@Override
	public String getString() {
		System.out.println("key: " + key);
		return key;
	}

	@Override
	public void setString() {
		key = UUID.randomUUID().toString();
	}
}
