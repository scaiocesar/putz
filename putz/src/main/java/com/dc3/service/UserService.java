package com.dc3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc3.model.User;
import com.dc3.repository.UserRepository;
import com.dc3.service.exception.ServiceException;

@Service
@Transactional
public class UserService extends GenericService {

	@Autowired
	private UserRepository userRepository;

	
	public void save(User user) throws ServiceException {
		User usr = userRepository.findTop1ByEmail(user.getEmail());
		if(usr == null){
			user.setActive(true);
			userRepository.save(user);
		}else{
			throw new ServiceException("Usuario já cadastrado", "error.user.already_exists");
		}
	}
	
	
	@Transactional(readOnly = true)
	public User doLogin(String email, String pwd) throws ServiceException{
		User usr = userRepository.findTop1ByEmail(email);
		if(usr == null){
			throw new ServiceException("Login não cadastrado","error.user.login_notfound");
		}else{
			if(!usr.getPassword().equals(pwd)){
				throw new ServiceException("Senha não confere","error.user.wrong_password");
			}else{
				return usr;
			}
		}
		
	}

}
