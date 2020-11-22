package com.sht.autosubmit.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sht.autosubmit.DTO.RespDTO;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aaron
 * @date 2020/11/17 20:23
 */
@Service
public class AutoSubmitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoSubmitService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    public String getToken(User user) {
        String url = "https://stu.eurasia.edu/yqsb/login/in?zh="+ user.getUsername() +"&&mm=" + user.getPassword();
        RespDTO loginDTO = restTemplate.getForObject(url, RespDTO.class);
        if (loginDTO != null && loginDTO.getSuccess()){
            return loginDTO.getToken();
        }else {
            try {
                userService.sendRegisterEmailCode(user, "失败,您可能修改了密码,请重新登录绑定");
                userService.deleteById(user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Map<String, Object> autoSubmit(User user) {
        String token = getToken(user);
        if (token != null){
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
            Map<String, Object> map = new HashMap<>(2);
            map.put("userInfo", userInfo);
            map.put("code", responseEntity.getBody());
            return map;
        }else {
            return null;
        }
    }
}
