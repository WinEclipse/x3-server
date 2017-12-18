package com.x3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.x3.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@RequestMapping(value = "/users", method = RequestMethod.GET, headers="Accept=application/json")
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			User user = new User();
			user.setId("id" + i);
			user.setUsername("username" + i);
			user.setPassword("password" + i);
			user.setMail("mail" + i);
			user.setRole("role" + i);
			users.add(user);
		}
		return users;
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public User getUser(@PathVariable("id") String id) {
		User user = new User();
		user.setId(id);
		user.setUsername("username" + id);
		user.setPassword("password" + id);
		user.setMail("mail" + id);
		user.setRole("role" + id);
		return user;
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
	public int modeifyUser(@PathVariable("id") String id, @PathVariable("name") String name, @PathVariable("mail") String mail, @PathVariable("age") int age) {
		System.out.println(id);
		return 1;
	}
}
