package com.sht.apimybatis.service;

import com.sht.apimybatis.dto.UserDto;
import com.sht.apimybatis.entity.User;

import java.util.List;

public interface UserService {

    List<User> findById(int id);

    UserDto findUserRoles(int id);
}
