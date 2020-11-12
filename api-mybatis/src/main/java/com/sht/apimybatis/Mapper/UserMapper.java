package com.sht.apimybatis.Mapper;

import com.sht.apimybatis.dto.UserDto;
import com.sht.apimybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findUserInfo(int id);

    List<User> findById(int id);

    UserDto findUserRoles(int id);
}
