package com.sht.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aaron
 * @date 2020/12/8 23:01
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "callback")
public class CallBack {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String site;
    private String domain;
}
