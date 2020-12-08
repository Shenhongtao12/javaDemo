package com.sht.common.untils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aaron
 * @date 2020/11/26 20:01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse {
    private Integer code;
    private String message;
    private Object data;

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
}
