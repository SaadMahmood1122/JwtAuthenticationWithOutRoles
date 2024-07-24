package com.futurenostic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futurenostic.dtos.LoginResponse;
import com.futurenostic.dtos.LoginUserDto;
import com.futurenostic.dtos.RegisterUserDto;
import com.futurenostic.entities.User;
import com.futurenostic.service.AuthenticationService;
import com.futurenostic.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private JwtService jwtService;
	@Autowired
    private AuthenticationService authenticationService;
	
  
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
        								.token(jwtToken)
        								.expiresIn(jwtService.getExpirationTime())
        								.build();
        return ResponseEntity.ok(loginResponse);
    }

}
