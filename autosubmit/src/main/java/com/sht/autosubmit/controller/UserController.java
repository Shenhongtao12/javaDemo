package com.sht.autosubmit.controller;

import com.alibaba.fastjson.JSONObject;
import com.sht.autosubmit.DTO.UserDTO;
import com.sht.autosubmit.entity.Login;
import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.service.AutoSubmitService;
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

    @Autowired
    private AutoSubmitService autoSubmitService;

    @PostMapping("login")
    public ResponseEntity<JSONObject> login(@RequestBody UserDTO userDTO) {
        JSONObject jsonObject = new JSONObject();
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().length() != usernameLength) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        User user1 = userService.findByUsername(userDTO.getUsername().trim());
        if (user1 != null){
            if (!user1.getPassword().equals(userDTO.getPassword())) {
                jsonObject.put("code", 400);
                jsonObject.put("msg", "账号或密码错误");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
            }
            jsonObject.put("code", 200);
            jsonObject.put("msg", "success");
            jsonObject.put("data", user1);
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject);
        }
        User user = new User();
        user.setUsername(userDTO.getUsername().trim());
        user.setPassword(userDTO.getPassword().trim());
        user.setMember(true);
        user.setSendEmail(true);
        user.setFlag(false);
        if (autoSubmitService.getToken(user) == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号或密码错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        int i = userService.save(user);
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        jsonObject.put("data", userService.findByUsername(userDTO.getUsername()));
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("update")
    public ResponseEntity<JSONObject> updateUserInfo(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (user.getEmail() != null && userService.checkEmailRule(user.getEmail().trim())){
            jsonObject.put("code", 400);
            jsonObject.put("msg", "邮箱格式错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        if (user.getSendEmail() && user.getEmail() == null){
            jsonObject.put("code", 400);
            jsonObject.put("msg", "邮箱不能为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        int i = userService.update(user);
        if (i != 1){
            jsonObject.put("code", 400);
            jsonObject.put("msg", "设置失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        jsonObject.put("code", 200);
        jsonObject.put("data", userService.findById(user.getId()));
        return ResponseEntity.ok(jsonObject);
    }

}
