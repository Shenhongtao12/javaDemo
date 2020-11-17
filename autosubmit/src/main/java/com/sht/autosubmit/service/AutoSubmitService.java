package com.sht.autosubmit.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sht.autosubmit.DTO.LoginDTO;
import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;

/**
 * @author Aaron
 * @date 2020/11/17 20:23
 */
@Service
public class AutoSubmitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoSubmitService.class);

    @Autowired
    private RestTemplate restTemplate;

    public String getToken(User user) {
        String url = "https://stu.eurasia.edu/yqsb/login/in?zh="+ user.getUsername() +"&&mm=" + user.getPassword();
        LoginDTO loginDTO = restTemplate.getForObject(url, LoginDTO.class);
        return loginDTO.getToken();
    }

    public void autoSubmit(User user) {
        String token = getToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<MultiValueMap<String,String>> request =  new HttpEntity<>(null,headers);
        //获取userInfo
        ResponseEntity<String> response = restTemplate.exchange("https://stu.eurasia.edu/yqsb/user/info", HttpMethod.GET, request, String.class, "");
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        UserInfo userInfo = JSON.toJavaObject(JSONObject.parseObject(jsonObject.get("data").toString()), UserInfo.class);
        LOGGER.info(userInfo.toString());

        headers.add("Content-Type", "application/json");
        HttpEntity<Object> requestSave =  new HttpEntity<>(userInfo, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://stu.eurasia.edu/yqsb/jkdj/save", HttpMethod.POST, requestSave, String.class, userInfo);
        LOGGER.info(responseEntity.toString());
    }
}
