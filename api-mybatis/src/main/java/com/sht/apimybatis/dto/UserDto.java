package com.sht.apimybatis.dto;

import com.sht.apimybatis.entity.Goods;
import com.sht.apimybatis.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {
    private int id;
    private String username;
    private String email;
    private List<Role> roleList;
}
