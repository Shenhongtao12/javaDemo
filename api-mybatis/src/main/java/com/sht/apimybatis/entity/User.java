package com.sht.apimybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String nickname;
    private String openId;
    private String intro;  //介绍
    private String phone;
    private String sex;
    private String img;
    private String flag; //判断是否修改过用户名，只能修改一次   //注册的时候携带改参数flag="注册"

    private String code; //邮箱验证码

    private List<Goods> goodsList;
}
