package com.sht.logback.controller;

import com.sht.logback.domain.R;
import com.sht.logback.utils.SseEmitterUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

    /**
     * 用于创建连接
     */
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterUtil.connect(userId);
    }

    /**
     * 推送给所有人
     *
     * @param message
     * @return
     */
    @GetMapping("/push/{message}")
    public R<String> push(@PathVariable(name = "message") String message) {
        //获取连接人数
        int userCount = SseEmitterUtil.getUserCount();
        //如果无在线人数，返回
        if(userCount<1){
            return R.fail("无人在线！");
        }
        SseEmitterUtil.batchSendMessage(message);
        return R.ok("发送成功！");
    }

    /**
     * 发送给单个人
     *
     * @param message
     * @param userid
     * @return
     */
    @GetMapping("/push_one/{message}/{userid}")
    public R<String> pushOne(@PathVariable(name = "message") String message, @PathVariable(name = "userid") String userid) {
        SseEmitterUtil.sendMessage(userid, message);
        return R.ok("推送消息给" + userid);
    }

    /**
     * 关闭连接
     */
    @GetMapping("/close/{userid}")
    public R<String> close(@PathVariable("userid") String userid) {
        SseEmitterUtil.removeUser(userid);
        return R.ok("连接关闭");
    }
}
