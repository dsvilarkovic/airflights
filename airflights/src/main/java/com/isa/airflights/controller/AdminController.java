package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.service.AdminService;

@RestController
@RequestMapping(value="/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
    @GetMapping("/all")
    public Collection<AbstractUserDTO> getAllAdmins() {
    	Collection<AbstractUser> users = service.getAll();
        
    	List<AbstractUser> admins = users.stream().filter(u -> u.getIdCompany() != 0).collect(Collectors.toList());
    	List<AbstractUserDTO> usersDTO = new ArrayList<AbstractUserDTO>();
    	
    	for (AbstractUser u : admins) {
    		usersDTO.add(new AbstractUserDTO(u));
    	}
    	
    	return usersDTO;
    }
}
