package com.sht.autosubmit.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

/**
 * @author Aaron
 * @date 2020/11/17 20:24
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Boolean sendEmail;
    private Boolean member;
    private Boolean flag;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
