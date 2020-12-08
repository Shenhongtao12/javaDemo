package com.sht.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aaron
 * @date 2020/12/8 22:55
 */
@Getter
@Setter
@NoArgsConstructor
public class QqUser {

    private String openId;
    private String nickname;
    private String sex;
    private String img;
}
