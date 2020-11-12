package com.sht.apimybatis.entity;

import lombok.Data;

@Data
public class Comment {
	private Integer commentid;
	private String createtime;
	private String content;
	private Integer number;
	private Integer userid;  //留言的发布人id
	private Integer goodsid;
	private Integer leaf;  //null 用来区分留言和回复
	private Object state;  //判断点赞

}
