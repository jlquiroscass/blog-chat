package jlquiros.blog.serviceImpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jlquiros.blog.service.CommonService;

public class CommonServiceImpl implements CommonService {
	
	public static final String getUserEmailFromSecurityContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		if (userEmail.compareTo("anonymousUser") == 0) {
			return null;
		}
		return userEmail;
	}

}
