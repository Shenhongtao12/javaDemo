package com.sht.autosubmit.utils;

import org.apache.commons.mail.HtmlEmail;

/**
 * @author Administrator
 */
public class EmailUtils
{
	public static void sendEmailCode(String EMAIL_HOST_NAME, String EMAIL_FORM_MAIL, String EMAIL_FORM_NAME, String EMAIL_AUTHENTICATION_USERNAME, String EMAIL_AUTHENTICATION_PASSWORD, String receiverEmail, String subject, String msg) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(EMAIL_HOST_NAME);
			email.setSmtpPort(465);
			email.setCharset("utf-8");
			email.setFrom(EMAIL_FORM_MAIL, EMAIL_FORM_NAME);
			email.setAuthentication(EMAIL_AUTHENTICATION_USERNAME, EMAIL_AUTHENTICATION_PASSWORD);
			email.addTo(receiverEmail);
			email.setSubject(subject);
			email.setMsg(msg);
			email.setSSLOnConnect(true);
			email.setSslSmtpPort("465");
			email.send();
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}
}
