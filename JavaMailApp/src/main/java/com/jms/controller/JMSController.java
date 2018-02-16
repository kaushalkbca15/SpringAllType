package com.jms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jms.service.JMSService;

@Controller
@RequestMapping(value = "home")
public class JMSController {

	@Autowired
	private JMSService jMSService;

	@GetMapping
	public String homePage() {
		return "home";
	}

	@PostMapping
	public String semdJMS(Map<String, String> map, @RequestParam("tomail") String to, @RequestParam("sub") String sub,
			@RequestParam("message") String message) {
		String responce = null;
		System.out.println();
		responce = jMSService.sendMail(to, sub, message);
		map.put("responce", responce);
		return "home";

	}
}
