package com.sht.autosubmit.service;

import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/11/17 20:28
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AutoSubmitService autoSubmitService;

    public int save(User user) {
        return userMapper.insert(user);
    }

    public boolean findByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return userMapper.selectOneByExample(example) == null;
    }

    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        criteria.andEqualTo("password", user.getPassword());
        return userMapper.selectOneByExample(example);
    }

    //@Scheduled(cron = "* * 00 * * ?")
    public void autoSubmit() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getFlag());
            if (user.getMember() && !user.getFlag()) {
                try {
                    autoSubmitService.autoSubmit(user);
                    user.setFlag(true);
                    userMapper.updateByPrimaryKey(user);
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(cron = "* * 02 * * ?")
    public void changeFlag() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            user.setFlag(false);
            userMapper.updateByPrimaryKey(user);
        }
    }
}
