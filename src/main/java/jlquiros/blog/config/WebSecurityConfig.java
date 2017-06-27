package jlquiros.blog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static PasswordEncoder encoder;

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
		http
		.headers().frameOptions().disable();
		http
		.csrf().disable()
        .authorizeRequests()
        	.antMatchers( "/live","/name","/css/**", "/", "/img/**", "/chat/**", "/topic/**" , "/app/**" , "/register", "/resources/**", "/js/**", "**/js/**", "/static/**", "/login", "/webjars/**","/chat.js").permitAll()
        	.antMatchers("/admin/**").hasAuthority("ADMIN")
        	.antMatchers("/post").hasAnyAuthority("USER", "ADMIN")
        	/*.antMatchers("/**").hasAnyAuthority("USER", "ADMIN")*/
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")            
            .permitAll()
           .and()
        .logout()
            .permitAll()
            .and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler);    
	}
	
	static class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {
        public void handle(HttpServletRequest request, HttpServletResponse response,
                AccessDeniedException accessDeniedException) throws IOException, ServletException {

        	if (accessDeniedException instanceof MissingCsrfTokenException
        	        || accessDeniedException instanceof InvalidCsrfTokenException) {
        		System.out.println("################# CSRF Access Denied Exception################");    
        		response.sendRedirect(request.getContextPath()+"/login");   
                	
        	}
        	super.handle(request, response, accessDeniedException);
        }
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (encoder == null) {
			encoder = new BCryptPasswordEncoder();
		}
		return encoder;
	}
}