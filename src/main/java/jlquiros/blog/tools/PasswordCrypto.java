package jlquiros.blog.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordCrypto {

	private static PasswordEncoder passwordEncoder;
	private static PasswordCrypto instance;
	
	private static int length=60;

	public static PasswordCrypto getInstance() {
		if (instance == null) {
			instance = new PasswordCrypto();
			passwordEncoder = new BCryptPasswordEncoder();
		}

		return instance;
	}

	public String encrypt(String str) {
		passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(str);
	}
	
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
		
	}

	public static int getLength() {
		return length;
	}
	
}