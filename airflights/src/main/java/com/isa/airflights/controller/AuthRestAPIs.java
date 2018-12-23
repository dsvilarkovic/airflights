package com.isa.airflights.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.airflights.model.AbstractUser;
import com.isa.airflights.model.Role;
import com.isa.airflights.model.RoleName;
import com.isa.airflights.repository.AbstractUserRepository;
import com.isa.airflights.repository.RoleRepository;
import com.isa.airflights.request.LoginForm;
import com.isa.airflights.request.SignUpForm;
import com.isa.airflights.response.JwtResponse;
import com.isa.airflights.security.jwt.JwtProvider;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AbstractUserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
       
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<AbstractUser> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        

        // Creating user's account
        AbstractUser user = new AbstractUser();
        
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setAddress(signUpRequest.getAddress());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        
        
        
        Set<Role> roles = new HashSet<>();
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
        		.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        		roles.add(userRole);   

        user.setRoles(roles);
        userRepository.save(user);


        return new ResponseEntity<AbstractUser>(new AbstractUser(user), HttpStatus.CREATED);
    }
}