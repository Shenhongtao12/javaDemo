package com.sht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aaron
 * @date 2020/11/2 23:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    private String title;
    private String price;
    private String img;
}
