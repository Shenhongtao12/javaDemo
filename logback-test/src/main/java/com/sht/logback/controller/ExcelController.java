package com.sht.logback.controller;

import com.sht.logback.domain.AjaxResult;
import com.sht.logback.domain.ShippingAddress;
import com.sht.logback.domain.User;
import com.sht.logback.poi.ExcelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * @author as2i
 * @date 2023/3/30 11:19
 */
@RestController
public class ExcelController {

	@PostMapping("excel")
	public void test(HttpServletResponse response) {
		User user = new User();
		user.setId(100L);
		user.setUsername("123123abc");
		user.setPrice(BigDecimal.valueOf(1236.98));
		user.setInDate(LocalDateTime.now());
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setName("西安市");
		shippingAddress.setCity("西安");
		user.setShippingAddresses(shippingAddress);

		ExcelUtil<User> excelUtil = new ExcelUtil<>(User.class);
		excelUtil.exportExcel(response, Collections.singletonList(user),"userlist");
	}
}
