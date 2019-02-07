package com.isa.airflights.controller;

import java.util.HashSet;
import java.util.Optional;
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
import com.isa.airflights.response.ErrorResponse;
import com.isa.airflights.response.JwtResponse;
import com.isa.airflights.security.jwt.JwtProvider;
import com.isa.airflights.service.AbstractUserService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AbstractUserService aus;
	
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

    	System.out.println("DFKLJF " + loginRequest.getEmail());
    	if(aus.checkVerify(loginRequest.getEmail())) {
    		System.out.println("Verifikovan");
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Get auth: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<AbstractUser> user = userRepository.findByEmail(loginRequest.getEmail());
            
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), user.get().getId()));
    	} else {
    		System.out.println("Nije verifikovan");
    		  return  ResponseEntity.ok(new ErrorResponse("Ne valja","Ne valja","Ne valja"));
    	}
    	
        
       
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<AbstractUser> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        

        // Creating user's account
        AbstractUser user = new AbstractUser();
    /*    List<AbstractUser> lista = userRepository.findAll();
        AbstractUser poslednji = new AbstractUser();
		System.out.println("lista size: " + lista.size());
		poslednji = lista.get(lista.size() - 1);
		System.out.println("Poslednji id: " + poslednji.getId());
		Long pom = poslednji.getId() + 1;
		user.setId(pom);
        */
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setAddress(signUpRequest.getAddress());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setIdRentACar(0);
        user.setChangePass(true);
        
        
        
        Set<Role> roles = new HashSet<>();
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
        		.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        		roles.add(userRole);   

        user.setRoles(roles);
        userRepository.save(user);


        return new ResponseEntity<AbstractUser>(new AbstractUser(user), HttpStatus.CREATED);
    }
}