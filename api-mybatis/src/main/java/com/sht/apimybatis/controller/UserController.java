package com.sht.apimybatis.controller;


import com.sht.apimybatis.dto.UserDto;
import com.sht.apimybatis.entity.User;
import com.sht.apimybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findById")
    public ResponseEntity<List<User>> findById(@RequestParam("id") int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("findUserRoles")
    public ResponseEntity<UserDto> findUserRoles(@RequestParam("id") int id) {
        return ResponseEntity.ok(userService.findUserRoles(id));
    }
}
