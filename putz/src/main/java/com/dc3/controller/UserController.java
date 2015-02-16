package com.dc3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dc3.model.User;
import com.dc3.service.UserService;
import com.dc3.service.exception.ServiceException;

/**
 * Classe responsável por gerenciar as açoes de Cadastro de usuario
 * @author Caio
 *
 */
@Controller
@RequestMapping(value = "/userController")
public class UserController extends GenericController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8", value = "/create")
	public ResponseEntity<?> create(@RequestBody User user, HttpServletRequest request) {
		try {
			userService.save(user);
			return new ResponseEntity<String>("OK",HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity(getMessageSource().getMessage(e.getMsgResource(), null, null, request.getLocale()), HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/login")
	public ResponseEntity<?> create(@RequestParam String email, @RequestParam String pwd, HttpServletRequest request) {
		try {
			User usr = userService.doLogin(email, pwd);
			return new ResponseEntity<User>(usr,HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity(getMessageSource().getMessage(e.getMsgResource(), null, null, request.getLocale()), HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
