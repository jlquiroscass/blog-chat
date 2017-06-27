package jlquiros.blog.service;

import org.springframework.stereotype.Service;

import jlquiros.blog.form.RegisterForm;
import jlquiros.blog.model.User;

@Service
public interface UserService {
	
	/**
	 * Registers a new user in the platform.
	 * 
	 * @param user
	 *            Data of the user to be registered in the platform.
	 * 
	 * @return The identifier of the newly created user.
	 * 
	 * 
	 */
	Long registerUser(RegisterForm user) throws Exception;


}
