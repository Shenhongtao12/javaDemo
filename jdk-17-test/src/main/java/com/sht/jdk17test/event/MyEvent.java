package com.sht.jdk17test.event;

import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * 自定义事件
 *
 * @author Aaron
 * @date 2022/12/11 20:46
 */
public class MyEvent extends ApplicationEvent {
    private String message;

    public MyEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public MyEvent(Object source, Clock clock, String message) {
        super(source, clock);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
