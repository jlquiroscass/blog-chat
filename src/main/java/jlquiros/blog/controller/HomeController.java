package jlquiros.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jlquiros.blog.config.CustomUserDetails;
import jlquiros.blog.form.PostForm;
import jlquiros.blog.model.Post;
import jlquiros.blog.repository.PostRepository;
import jlquiros.blog.repository.TagRepository;
import jlquiros.blog.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	PostRepository postrepository;
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
    PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,Authentication authentication) {
		
		List<Post> latest5Posts = postrepository.findAll();
		model.addAttribute("latest5posts", latest5Posts);
		
        return "index";
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	

	
	
	@RequestMapping(value = "/toPost", method = RequestMethod.POST)
	public String toPost (@ModelAttribute PostForm postForm,Authentication authentication)
	{
		
		postForm.setAuthor(((CustomUserDetails) authentication.getPrincipal()).getIdUser());
		postService.post(postForm);

		 return "redirect:/"; 
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
    public String post(Model model) {
	
		return "post";
	}
}