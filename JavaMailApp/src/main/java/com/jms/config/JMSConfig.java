package com.jms.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JMSConfig {

	@Bean
	public MailSender createMailSender() {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com");
		senderImpl.setUsername("kaushalk2367@gmail.com");
		senderImpl.setPassword("kaushalk236@#");
		senderImpl.setPort(587);
		senderImpl.setJavaMailProperties(getProperty());
		return senderImpl;
	}

	private Properties getProperty() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.server.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");
		return properties;
	}
}
