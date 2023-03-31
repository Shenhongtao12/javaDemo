package com.sht.logback.domain;

import com.sht.logback.excel.Excel;
import com.sht.logback.excel.Excels;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author as2i
 * @date 2023/3/30 11:01
 */
@Data
public class User {
	@Excel(name = "ID")
	private Long id;
	@Excel(name = "用户名")
	private String username;
	@Excel(name = "昵称")
	private String nickname;
	private String phone;
	@Excel(name = "地址")
	private String address;
	private Integer age;
	private Boolean ex;

	@Excel(name = "时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inDate;

	@Excel(name = "价钱", prompt = "aabbc")
	private BigDecimal price;

	@Excels({
			@Excel(name = "部门名称", targetAttr = "name", type = Excel.Type.EXPORT),
			@Excel(name = "城市", targetAttr = "city", type = Excel.Type.EXPORT, combo = {"西安","上海", "北京"})
	})
	private ShippingAddress shippingAddresses;
}
