package com.isa.airflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.service.AbstractUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value="api/abstractUsers")
@CrossOrigin(origins = "http://localhost:4200")
public class AbstractUserController {
	
	private Logger logger = LoggerFactory.getLogger(AbstractUserController.class);
	
	@Autowired
	private AbstractUserService abstractUserService;
	
	@RequestMapping("/test")
	public String test() {
		return "Ovo je test";
	}
}
