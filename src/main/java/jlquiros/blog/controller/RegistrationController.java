package jlquiros.blog.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jlquiros.blog.form.RegisterForm;
import jlquiros.blog.repository.UserRepository;
import jlquiros.blog.service.UserService;
import jlquiros.blog.serviceImpl.CommonServiceImpl;

@Controller
public class RegistrationController extends WebMvcConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	private static final String PATH_REDIRECT_HOME = "redirect:/";
	private static final String PATH_REGISTER = "register";
	private static final String PATH_LOGIN = "redirect:/login";

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage(HttpServletRequest request, RegisterForm registerForm) {
		if (CommonServiceImpl.getUserEmailFromSecurityContext() == null) {
			return PATH_REGISTER;
		} else {
			return PATH_REDIRECT_HOME;
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, ModelMap model)  {
		if (registerForm.getEmail().equals("") || registerForm.getPassword().equals(""))
		{
			model.addAttribute("error", true);
			return PATH_REGISTER;
		}
		try {
			userService.registerUser(registerForm);
		} catch (Exception e) {
			model.addAttribute("error", true);
			model.addAttribute("alreadyRegister", true);
			return PATH_REGISTER;
		}

		
		redirectAttributes.addAttribute("register_success", true);
		redirectAttributes.addAttribute("user",registerForm.getEmail());
		
		return PATH_LOGIN;
	}
}
