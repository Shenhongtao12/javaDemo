package com.sht.common.controller;

import com.sht.common.config.QQConfig;
import com.sht.common.service.QqService;
import com.sht.common.untils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Aaron
 * @date 2020/12/8 22:31
 */
@RestController
@RequestMapping("api/qq")
public class QqController extends BaseController {

    @Autowired
    private QqService qqService;

    /**
     * 跳转到qq授权网页
     * 限制site必须为6位字符
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<RestResponse> qqLogin(HttpSession session, HttpServletResponse response,
                                                @RequestParam(value = "site", required = true) String site) {

        if (qqService.checkCallBack(site) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ERROR("暂无权限"));
        }
        /**
         * 防止请求受到攻击
         */
        String uuid = site + UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", uuid);

        String url = QQConfig.GETQQPAGE + "?" +
                "response_type=code" +
                "&client_id=" + QQConfig.APPID +
                "&redirect_uri=" + URLEncoder.encode(QQConfig.BACKURL) +
                "&state=" + uuid;
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(SUCCESS("success"));
    }

    /**
     * qq回调地址
     *
     * @param request
     * @return
     */
    @GetMapping("/callBack")
    public void callback(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String uuid = (String) session.getAttribute("state");
        if (uuid != null) {
            if (!uuid.equals(state)) {
                System.out.println("TOKEN错误, 防止CSRF攻击, 业务异常处理......");
            }
        }
        qqService.callBack(state.substring(0, 5), code);
    }

}
