package com.isa.airflights.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
 
	@RequestMapping("/hello")
	public String hello() {
		return "<title>Home</title><b>Hello from HomeController<b>!";
	}
	
	@RequestMapping("/cookie")
	public String cookie() {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		return "<title>Cookie</title><body>It's nice you stopped by, here's cookie for you.<br/> <img src='\\src\\main\\resources\\static\\img\\cookie.jpg' alt='Img error'><body><html>";
	}
	
	@RequestMapping(value="/home", produces=MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String home() {
		return "app.component.html";
	}
}
