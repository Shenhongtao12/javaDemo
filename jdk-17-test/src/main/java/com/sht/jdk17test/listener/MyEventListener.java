package com.sht.jdk17test.listener;

import com.sht.jdk17test.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * TODO 类描述
 *
 * @author Aaron
 * @date 2022/12/11 20:51
 */
@Slf4j
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        // 逻辑处理
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(event.getMessage());
    }
}
