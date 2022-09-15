package com.sht.provider.service.impl;

import com.sht.provider.service.IStudentService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author as2i
 * @date 2022/9/15 18:22
 */
@DubboService(interfaceClass = IStudentService.class)
public class StudentServiceImpl implements IStudentService {
    @Override
    public String getStudent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "张三";
    }
}
