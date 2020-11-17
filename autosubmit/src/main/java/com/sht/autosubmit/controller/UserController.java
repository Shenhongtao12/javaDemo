package com.sht.autosubmit.controller;

import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aaron
 * @date 2020/11/17 20:27
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<Integer> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
