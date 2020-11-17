package com.sht.autosubmit.service;

import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Scheduled(cron = "* * 0 * * ?")
    public void autoSubmit() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            autoSubmitService.autoSubmit(user);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
