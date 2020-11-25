package com.sht.autosubmit;

import com.alibaba.fastjson.JSONObject;
import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.entity.UserInfo;
import com.sht.autosubmit.mapper.UserMapper;
import com.sht.autosubmit.service.AutoSubmitService;
import com.sht.autosubmit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = AutosubmitApplication.class)
class AutosubmitApplicationTests {

    @Autowired
    private AutoSubmitService autoSubmitService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void autoSubmit() throws Exception {
        userService.autoSubmit();
    }

    @Test
    public void testSendEmail() throws Exception {
        User user = new User();
        user.setEmail("785820791@qq.com");
        user.setUsername("17610903150644");
        userService.sendRegisterEmailCode(user, "成功");
        System.out.println(new Date());
    }

    @Test
    public void testEm() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("username", "17%");
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
        System.out.println(users.size());
    }

    @Test
    void contextLoads() {
        User user = new User("","17610807150143", "22391");
        user.setEmail("shenhongtao12@aliyun.com");
        String token = autoSubmitService.getToken(user);
        System.out.println(token);
    }

    @Test
    public void testUserInfo() {
        autoSubmitService.autoSubmit(new User("","17610807150143", "223913"));
    }

    public void testAuto() {
        UserInfo userInfo = new UserInfo();
        JSONObject body = new JSONObject();
        body.put("bz1", userInfo.getBz1());
        body.put("bz2", userInfo.getBz2());
        body.put("cc", userInfo.getCc());
        body.put("fdygh", userInfo.getFdyh());
        body.put("fxdj", userInfo.getFxdj());
        body.put("fxrq", userInfo.getFxrq());
        body.put("fxstzk", userInfo.getFxstzk());
        body.put("jchbsj", userInfo.getJchbsj());
        body.put("jcsj", userInfo.getJcsj());
        body.put("jtdz", userInfo.getJtdz());
        body.put("jtgj", userInfo.getJtgj());
        body.put("jtzz", userInfo.getJtzz());
        body.put("lxdh", userInfo.getLxdh());
        body.put("mqcs", userInfo.getMqcs());
        body.put("mqsxdz", userInfo.getMqsxdz());
        body.put("mqsxzd", userInfo.getMqsxzd());
        body.put("mqszd", userInfo.getMqszd());
        body.put("qtstyc", userInfo.getMqszd());
        body.put("rctw", userInfo.getMqszd());
        body.put("sfbs", userInfo.getSfbs());
        body.put("sffl", userInfo.getSffl());
        body.put("sffr", userInfo.getSffr());
        body.put("sffx", userInfo.getSffx());
        body.put("sfjchbr", userInfo.getSfjchbr());
        body.put("sfjcwhry", userInfo.getSfjcwhry());
        body.put("sfjjgl", userInfo.getSfjjgl());
        body.put("sfjz", userInfo.getSfjz());
        body.put("sfks", userInfo.getSfks());
        body.put("sfqz", userInfo.getSfqz());
        body.put("sfys", userInfo.getSfys());
        body.put("sfyxgl", userInfo.getSfyxgl());
        body.put("sfyz", userInfo.getSfyz());
        body.put("sfzs", userInfo.getSfzs());
        body.put("xb", userInfo.getXb());
        body.put("xh", userInfo.getXh());
        body.put("xm", userInfo.getXm());
        body.put("xxdz", userInfo.getXxdz());
    }

}
