package com.sht.autosubmit.entity;

import lombok.*;

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
    private String name;
    private String username;
    private String password;
}
