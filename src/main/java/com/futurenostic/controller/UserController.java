package com.futurenostic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.futurenostic.entities.User;
import com.futurenostic.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	@Autowired
	 private UserService userService;

	    @GetMapping("/me")
	    public ResponseEntity<User> authenticatedUser() {
	    	System.out.println("UserController.authenticatedUser()");
	    	
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        User currentUser = (User) authentication.getPrincipal();

	        return ResponseEntity.ok(currentUser);
	    }

	    @GetMapping("/")
	    public ResponseEntity<List<User>> allUsers() {
	        List <User> users = userService.allUsers();

	        return ResponseEntity.ok(users);
	    }

}
