package com.sht.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sht.common.config.QQConfig;
import com.sht.common.entity.CallBack;
import com.sht.common.entity.QqUser;
import com.sht.common.mapper.CallBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Aaron
 * @date 2020/12/8 22:41
 */
@Service
public class QqService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CallBackMapper callBackMapper;

    public void callBack(String site, String code) {
        QqUser user = userInfo(code);
        if (user != null) {
            CallBack callBack = checkCallBack(site);
            restTemplate.postForEntity(callBack.getDomain(), user, String.class);
        }
    }


    public QqUser userInfo(String code) {
        // 获取Access Token
        String access_url = QQConfig.GETACCESSTOKEN+"?grant_type=authorization_code" +
                "&client_id=" + QQConfig.APPID +
                "&client_secret=" + QQConfig.APPKEY +
                "&code=" + code +
                "&redirect_uri=" + QQConfig.BACKURL;
        String access_res = restTemplate.getForEntity(access_url, String.class).getBody();
        String access_token = "";
        if (access_res.indexOf("access_token") >= 0) {
            String[] array = access_res.split("&");
            for (String str: array) {
                if (str.indexOf("access_token") >= 0) {
                    access_token = str.substring(str.indexOf("=") + 1);
                    break;
                }
            }
        }

        // 获取qq账户 openId
        String open_id_url = QQConfig.GETACCOUNTOPENID+"?access_token="+access_token;
        String open_id_res = restTemplate.getForEntity(open_id_url, String.class).getBody();
        int startIndex = open_id_res.indexOf("(");
        int endIndex = open_id_res.lastIndexOf(")");
        String open_id_res_str = open_id_res.substring(startIndex + 1, endIndex);
        JSONObject jsonObject = JSON.parseObject(open_id_res_str);
        String openId = jsonObject.getString("openid");

        // 获取账户qq信息
        String account_info_url = QQConfig.GETACCOUNTINFO+"?access_token="+access_token+
                "&oauth_consumer_key=" + QQConfig.APPID +
                "&openid=" + openId;
        String account_info_res = restTemplate.getForEntity(account_info_url, String.class).getBody();
        JSONObject userJson = JSON.parseObject(account_info_res);
        QqUser qqUser = new QqUser();
        qqUser.setOpenId(openId);
        qqUser.setNickname(userJson.getString("nickname"));
        qqUser.setSex(userJson.getString("gender"));
        qqUser.setImg(userJson.getString("figureurl_qq_1"));
        return qqUser;
    }

    public CallBack checkCallBack(String site) {
        Example example = new Example(CallBack.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("site", site);
        return callBackMapper.selectOneByExample(example);
    }
}
