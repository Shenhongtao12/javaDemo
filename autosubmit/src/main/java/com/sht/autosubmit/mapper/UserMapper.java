package com.sht.autosubmit.mapper;

import com.sht.autosubmit.entity.User;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Aaron
 * @date 2020/11/17 20:24
 */
public interface UserMapper extends Mapper<User> {

    @Update("update user set flag = 0")
    public void updateFlag();
}
