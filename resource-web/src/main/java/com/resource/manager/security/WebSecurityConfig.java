package com.resource.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.resource.manager.service.WebUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String[] ROLES = {"ADMIN", "EMPLOYEE", "HR-MANAGER", "MANAGER"}; 
	
	@Autowired
	private WebUserService service;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/resources/**",
				"/img/**",
				"/css/**",
				"/js/**", 
                "/robots.txt",  
                "/favicon.ico"
				);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/resources/**").permitAll()
			.antMatchers("/", "/resources/static/**").permitAll()
			.antMatchers("/home.html").hasAnyAuthority(ROLES)
			.antMatchers("/login.html").permitAll().anyRequest()
			.authenticated().and().formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/home.html", true)
			.failureHandler(authenticationFailureHandler()).and().logout()
			.logoutSuccessUrl("/login.html").logoutUrl("/perform_logout")
			.deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
	
	@Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
