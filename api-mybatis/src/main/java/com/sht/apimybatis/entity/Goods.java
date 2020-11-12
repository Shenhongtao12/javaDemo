package com.sht.apimybatis.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Goods implements Serializable
{
	private Integer id;
	private String name;
	private String intro;
	private Double price1;
	private Double price2;
	private String images;
	private String weixin;
	private String create_time;
	private Integer state;
	private Integer classify2_id;
	private Integer userid;
	private boolean code;  //判断用户是否收藏该产品
	private Integer commentNum;  //留言条数
	private User user;
	private List<Comment> commentList;

}
