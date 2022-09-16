package com.sht.provider.service.impl;

import com.sht.serviceInterface.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author as2i
 * @date 2022/9/15 18:22
 */
@DubboService(interfaceClass = IUserService.class)
@Service
public class StudentServiceImpl implements IUserService {
    @Override
    public String getUser() {
        return "张三";
    }
}
