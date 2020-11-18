package com.sht.autosubmit.controller;

import com.alibaba.fastjson.JSONObject;
import com.sht.autosubmit.DTO.UserDTO;
import com.sht.autosubmit.entity.Login;
import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aaron
 * @date 2020/11/17 20:27
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final static int usernameLength = 14;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<JSONObject> register(@RequestBody UserDTO userDTO) {
        JSONObject jsonObject = new JSONObject();
        if (userDTO.getUsername().trim().length() != usernameLength) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        if (!userService.findByUsername(userDTO.getUsername().trim())){
            jsonObject.put("code", 400);
            jsonObject.put("msg", "该账号已存在");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        User user = new User();
        user.setUsername(userDTO.getUsername().trim());
        user.setPassword(userDTO.getPassword().trim());
        user.setEmail(userDTO.getEmail().trim());
        user.setMember(true);
        user.setSendEmail(true);
        user.setFlag(false);
        int i = userService.save(user);
        System.out.println("1111 "+i);
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("login")
    public ResponseEntity<JSONObject> login(@RequestBody Login login) {
        JSONObject jsonObject = new JSONObject();
        if (login == null || login.getUsername().trim().length() != usernameLength) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        User user = new User();
        user.setUsername(login.getUsername().trim());
        user.setPassword(login.getPassword().trim());
        User login1 = userService.login(user);
        if(login1 != null) {
            login1.setPassword("********");
            jsonObject.put("code", 200);
            jsonObject.put("msg", "success");
            jsonObject.put("data", login1);
            return ResponseEntity.ok(jsonObject);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号或密码错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
    }
}
