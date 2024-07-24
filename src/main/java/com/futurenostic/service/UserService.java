package com.futurenostic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futurenostic.entities.User;
import com.futurenostic.repositories.UserRepo;
@Service
public class UserService {
	@Autowired
	 private UserRepo userRepository;


	    public List<User> allUsers() {
	        List<User> users = new ArrayList<>();

	        userRepository.findAll().forEach(users::add);

	        return users;
	    }

}
