package com.sht.jdk17test.controller;

import com.sht.jdk17test.event.MyEvent;
import com.sht.jdk17test.service.PublishEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * event入口
 *
 * @author Aaron
 * @date 2022/12/11 21:05
 */
@RestController
@RequestMapping("send")
public class EventController {

    @Value("${newegg.context}")
    String context;

    @Autowired
    private PublishEventService publishEventService;

    @GetMapping()
    public void send() {
        System.out.println("===="  +  context);
        for (int i = 0; i < 5; i++) {
            publishEventService.publish("你若为我繁华，你好呀：" + (i + 1));
        }
    }
}
