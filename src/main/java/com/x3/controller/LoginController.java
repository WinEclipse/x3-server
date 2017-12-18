package com.x3.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.x3.model.User;

/**
 * 
 * @author heming
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<User> login(@RequestBody String username, @RequestBody String password) {
		if(null == username) {
			return null;
		}
		User user = new User();
		user.setId("abc");
		user.setUsername(username);
		user.setPassword(password);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
