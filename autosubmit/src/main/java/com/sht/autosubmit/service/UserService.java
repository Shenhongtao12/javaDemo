package com.sht.autosubmit.service;

import com.sht.autosubmit.entity.User;
import com.sht.autosubmit.mapper.UserMapper;
import com.sht.autosubmit.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Aaron
 * @date 2020/11/17 20:28
 */
@Service
public class UserService {

    @Value("${email.host-name}")
    public String EMAIL_HOST_NAME;
    @Value("${email.authentication.username}")
    public String EMAIL_AUTHENTICATION_USERNAME;
    @Value("${email.authentication.password}")
    public String EMAIL_AUTHENTICATION_PASSWORD;
    @Value("${email.charset}")
    public String EMAIL_CHARSET;
    @Value("${email.form.mail}")
    public String EMAIL_FORM_MAIL;
    @Value("${email.form.name}")
    public String EMAIL_FORM_NAME;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AutoSubmitService autoSubmitService;

    public int save(User user) {
        return userMapper.insert(user);
    }

    public boolean findByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return userMapper.selectOneByExample(example) == null;
    }

    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        criteria.andEqualTo("password", user.getPassword());
        return userMapper.selectOneByExample(example);
    }

    @Scheduled(cron = "10 * 0 * * ?")
    public void autoSubmit() throws Exception {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getMember() && !user.getFlag()) {
                try {
                    autoSubmitService.autoSubmit(user);
                    user.setFlag(true);
                    userMapper.updateByPrimaryKey(user);
                    sendRegisterEmailCode(user, "成功");
                    Thread.sleep(100);
                } catch (Exception e) {
                    sendRegisterEmailCode(user, "失败");
                }
            }
        }
    }

    @Scheduled(cron = "* * 02 * * ?")
    public void changeFlag() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            user.setFlag(false);
            userMapper.updateByPrimaryKey(user);
        }
    }


    public void sendRegisterEmailCode(User user, String status) throws Exception {
        String REGISTER_SUBJECT = "Eurasia健康日报填写平台";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String REGISTER_MSG = "<head><base target=\"_blank\" /><style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style><style id=\"cloudAttachStyle\"type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style><style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style><style type=\"text/css\">body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}th,td{font-family:arial,verdana,sans-serif;line-height:1.666}img{ border:0}header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}blockquote{margin-right:0px}</style>\n</head><body tabindex=\"0\" role=\"listitem\"><table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\"><tbody><tr><td><div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\"><tbody><tr><td width=\"210\"></td></tr></tbody></table></div><div style=\"width:680px;padding:0 10px;margin:0 auto;\"><div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\"><strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\">"+user.getUsername()+" </span>您好！</strong><strong style=\"display:block;margin-bottom:15px;\">您正在使用<span style=\"color: red\">健康日报辅助填写平台</span>，"+sdf.format(new Date())+"填写：<span style=\"color:#f60;font-size: 24px\">" + status + "</span> </strong></div><div style=\"margin-bottom:30px;\"><small style=\"display:block;margin-bottom:20px;font-size:12px;\"><p style=\"color:#747474;\">注意：如果您收到填写失败的邮件，请您手动完成健康日报填写，并与我们管理员取得联系。WX: sht18161765318 / lxulxu555 <br>（该日报填写平台为私人共享平台，请勿泄漏！)</p></small></div></div><div style=\"width:700px;margin:0 auto;\"><div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\"><p>此为系统邮件，请勿回复<br>请保管好您的邮箱，避免账号被他人盗用</p><p>Eurasia网络科技团队</p></div></div></td></tr></tbody></table></body>";

        EmailUtils.sendEmailCode(this.EMAIL_HOST_NAME, this.EMAIL_FORM_MAIL, this.EMAIL_FORM_NAME, this.EMAIL_AUTHENTICATION_USERNAME, this.EMAIL_AUTHENTICATION_PASSWORD, user.getEmail(), REGISTER_SUBJECT, REGISTER_MSG);

    }

    public Boolean checkEmailRule(String email) {
        if (email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)")) {
            return false;
        }
        return true;
    }
}
