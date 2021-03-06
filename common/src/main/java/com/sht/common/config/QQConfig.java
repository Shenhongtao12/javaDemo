package com.sht.common.config;

/**
 * @author Sht
 * @project deal
 * @date 2020/3/28 - 18:30
 **/

public class QQConfig {
    /**
     * 域名
     */
    private static final String DOMAIN = "http://eurasia.plus:8800";

    /**
     * APPID
     */
    public static final String APPID = "101868336";

    /**
     * APPKEY
     */
    public static final String APPKEY = "0c7410ff03f13f6769a1c8436d44abf1";

    /**
     * 回调地址
     */
    public static final String BACKURL = DOMAIN+"/api/user/qqCallBack";

    /**
     * 获取qq授权网页
     */
    public static final String GETQQPAGE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 获取Access Token
     */
    public static final String GETACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * 获取账户OPENID
     */
    public static final String GETACCOUNTOPENID = "https://graph.qq.com/oauth2.0/me";

    /**
     * 获取账户信息
     */
    public static final String GETACCOUNTINFO = "https://graph.qq.com/user/get_user_info";
}
