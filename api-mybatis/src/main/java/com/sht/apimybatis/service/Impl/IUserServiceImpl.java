package com.sht.apimybatis.service.Impl;

import com.sht.apimybatis.Mapper.UserMapper;
import com.sht.apimybatis.dto.UserDto;
import com.sht.apimybatis.entity.User;
import com.sht.apimybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public UserDto findUserRoles(int id) {
        return userMapper.findUserRoles(id);
    }
}
