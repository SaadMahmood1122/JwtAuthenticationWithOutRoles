package com.futurenostic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.futurenostic.dtos.LoginUserDto;
import com.futurenostic.dtos.RegisterUserDto;
import com.futurenostic.entities.User;
import com.futurenostic.repositories.UserRepo;

@Service
public class AuthenticationService {
	@Autowired
	 private PasswordEncoder passwordEncoder;
	   @Autowired 
	private AuthenticationManager authenticationManager;
	   @Autowired
	   private UserRepo userRepo;
	   
	   public User signup(RegisterUserDto input) {
	        User user =User.builder().fullName(input.getFullName()).email(input.getEmail())
	        		   .password(passwordEncoder.encode(input.getPassword())).build();
	        		
	           

	        return userRepo.save(user);
	    }
	   

	    public User authenticate(LoginUserDto input) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        input.getEmail(),
	                        input.getPassword()
	                )
	        );

	        return userRepo.findByEmail(input.getEmail())
	                .orElseThrow();
	    }

}
