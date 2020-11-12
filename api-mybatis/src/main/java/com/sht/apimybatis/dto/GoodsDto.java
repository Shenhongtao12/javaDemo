package com.sht.apimybatis.dto;

import com.sht.apimybatis.entity.Comment;
import com.sht.apimybatis.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GoodsDto {

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
