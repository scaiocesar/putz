package com.dc3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dc3.model.User;
import com.dc3.service.UserService;
import com.dc3.service.exception.ServiceException;

/**
 * Classe responsável por gerenciar as açoes de Cadastro de usuario
 * 
 * @author Caio
 * 
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends GenericController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> create(@RequestBody User user) {
		ResponseEntity<User> respEnt = null;
		
		try {
			userService.save(user);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(getMessageSource().getMessage(
					e.getMsgResource(), null, null), HttpStatus.BAD_REQUEST);
		}

		respEnt = new ResponseEntity<User>(user,HttpStatus.OK);
		return respEnt;
	}
}