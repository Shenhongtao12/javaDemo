package com.sht.common.controller;


import com.sht.common.untils.RestResponse;

/**
 * @author Aaron
 * @date 2020/11/26 20:00
 */
public class BaseController {

    public RestResponse SUCCESS(String message){
        return new RestResponse(200, message);
    }

    public RestResponse SUCCESS(Object data) {
        return new RestResponse(200, data);
    }

    public RestResponse ERROR(String message) {
        return new RestResponse(500, message);
    }

    public RestResponse ERROR(Integer code, String message) {
        return new RestResponse(code, message);
    }
}
