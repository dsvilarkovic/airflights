package com.isa.airflights.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.dto.AbstractUserDTO;
import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Role;
import com.isa.airflights.service.AdminService;

@RestController
@RequestMapping(value="/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
    @Autowired
    private PasswordEncoder encoder;
	
	
    @GetMapping("/all")
    public Collection<AbstractUserDTO> getAllAdmins() {
    	Collection<AbstractUser> users = service.getAll();
        
    	List<AbstractUser> admins = users.stream().filter(u -> u.getRole()!=null && u.getRole().getId() != 1).collect(Collectors.toList());
    	List<AbstractUserDTO> usersDTO = new ArrayList<AbstractUserDTO>();
    	
    	for (AbstractUser u : admins) {
    		usersDTO.add(new AbstractUserDTO(u));
    	}
    	
    	return usersDTO;
    }
    
    @RequestMapping(value="/add", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> addAdmin(@RequestBody AbstractUser abstractUser) {
		
    	abstractUser.setPassword(encoder.encode(abstractUser.getPassword()));
    	
    	Role role = abstractUser.getRole();
    	abstractUser.getRoles().add(role);
    	
    	/*if (abstractUser.getAirline()!=null) {
    		
    	} else if (abstractUser.getIdCompany()!=null) {
    		
    	} else if (abstractUser.getHotel()!=null) {
    		
    	} else {
    		// System admin
    		
    	}*/
    	
    	AbstractUserDTO au = new AbstractUserDTO(service.save(abstractUser));
    	
    	
    	
    	return new ResponseEntity<AbstractUserDTO>(au,HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> getAdmin(@PathVariable Long id) {
		
    	AbstractUserDTO ret = new AbstractUserDTO(service.findOne(id));
    	
    	return new ResponseEntity<AbstractUserDTO>(ret, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, 
    		consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbstractUserDTO> updatePass(@RequestBody AbstractUserDTO user) {
		
    	AbstractUser u = service.findOne(user.getId());
    	u.setPassword(encoder.encode(user.getNewPassword()));
    	
    	service.save(u);
    	
    	return new ResponseEntity<AbstractUserDTO>(new AbstractUserDTO(u), HttpStatus.CREATED);
    }
    
    

    
    
}
