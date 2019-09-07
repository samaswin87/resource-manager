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

import com.resource.common.config.Roles;
import com.resource.manager.service.WebUserService;

@Configuration
@EnableWebSecurity
//Session: https://www.baeldung.com/spring-security-session
// try this : https://github.com/spring-projects/spring-session/blob/2.1.8.RELEASE/samples/boot/findbyusername/src/main/java/sample/session/SessionDetailsFilter.java
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	
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
			.antMatchers("/home").hasAnyAuthority(Roles.getRoles())
			.antMatchers("/companies/**").hasAnyAuthority(Roles.getRoles())
			.antMatchers("/employees/**").hasAnyAuthority(Roles.getRoles())
			.antMatchers("/error").permitAll()
			.antMatchers("/login").permitAll().anyRequest()
			.authenticated().and().formLogin()
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/home", true)
			.failureHandler(authenticationFailureHandler()).and().logout()
            .invalidateHttpSession(true)
			.logoutSuccessUrl("/login?logout=true").permitAll().logoutUrl("/logout")
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
