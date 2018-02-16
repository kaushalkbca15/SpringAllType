package com.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class JMSServiceImpl implements JMSService{

	@Autowired
	private MailSender  mailSender;  
	@Override
	public String sendMail(String to, String sub, String message) {
		String response="FAIL";
		if(to!=null&&sub!=null&&message!=null){
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(to);
			mailMessage.setSubject(sub);
			mailMessage.setText(message);
			try{
				mailSender.send(mailMessage);
				response="successfully sended";
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}

	
}
