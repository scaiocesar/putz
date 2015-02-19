package com.dc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dc3.model.User;
import com.dc3.service.UserService;
import com.dc3.service.exception.ServiceException;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> validate(User user) {
		try {
			user = userService.doLogin(user);
		} catch (ServiceException e) {			
			return new ResponseEntity<String>(e.getMsgResource(), HttpStatus.UNAUTHORIZED);
		}
		ResponseEntity<User> respEnt = new ResponseEntity<User>(user,HttpStatus.OK);
		return respEnt;
	}
}