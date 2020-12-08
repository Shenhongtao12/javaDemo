package com.sht.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aaron
 * @date 2020/12/8 21:53
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity {
    String fromName;
    String email;
    String subject;
    String registerMessage;
    String code;
}
