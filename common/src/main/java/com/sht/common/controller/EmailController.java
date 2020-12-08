package com.sht.common.controller;

import com.sht.common.entity.EmailEntity;
import com.sht.common.service.EmailService;
import com.sht.common.untils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aaron
 * @date 2020/12/8 21:32
 */
@RestController
@RequestMapping("api/email")
public class EmailController extends BaseController{

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<RestResponse> sendEmailCode(@RequestBody EmailEntity emailEntity) {

        if (emailEntity.getRegisterMessage() == null || emailEntity.getRegisterMessage().equals("")){
            String code = emailService.getNumber();
            emailEntity.setCode(code);
            if(emailService.sendRegisterEmailCode(emailEntity)){
                return ResponseEntity.ok(SUCCESS(code));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR("发送失败,请重新发送"));
        }
        if(emailService.sendRegisterEmailCode(emailEntity)){
            return ResponseEntity.ok(SUCCESS("success"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR("发送失败,请重新发送"));
    }
}
