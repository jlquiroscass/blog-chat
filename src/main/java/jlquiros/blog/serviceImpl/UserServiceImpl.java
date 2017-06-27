package jlquiros.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jlquiros.blog.form.RegisterForm;
import jlquiros.blog.model.Role;
import jlquiros.blog.model.User;
import jlquiros.blog.repository.RoleRepository;
import jlquiros.blog.repository.UserRepository;
import jlquiros.blog.service.UserService;
import jlquiros.blog.tools.PasswordCrypto;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public Long registerUser(RegisterForm user) throws Exception {
		User existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser != null) {
			throw new Exception("User " + user.getEmail() + " already registered!");
		}

		if (user.getEmail().equals("") || user.getPassword().equals(""))
			throw new Exception("Invalid data!");
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(PasswordCrypto.getInstance().encrypt(
				user.getPassword()));
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());

		Role role = roleRepository.getOne("USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);

		newUser.setRoles(roles);

		User savedUser = userRepository.save(newUser);

		return savedUser.getId();
	}

}
