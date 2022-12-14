package com.sht.jdk17test.service;

import com.sht.jdk17test.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 发布事件
 *
 * @author Aaron
 * @date 2022/12/11 20:57
 */
@Service
public class PublishEventService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String message) {
        applicationEventPublisher.publishEvent(new MyEvent("", message));
    }
}
