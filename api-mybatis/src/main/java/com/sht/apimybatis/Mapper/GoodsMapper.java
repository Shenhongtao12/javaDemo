package com.sht.apimybatis.Mapper;

import com.sht.apimybatis.dto.GoodsDto;
import org.apache.ibatis.annotations.*;

public interface GoodsMapper {

    @Select("select id,name,intro,price1 ,userid from goods where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "intro", column = "intro"),
            @Result(property = "price1", column = "price1"),
            @Result(property = "user", column = "userid", many = @Many(select = "com.sht.apimybatis.Mapper.UserMapper.findUserInfo"))
    })
    GoodsDto findById(int id);

    GoodsDto findByOne(int id);
}
