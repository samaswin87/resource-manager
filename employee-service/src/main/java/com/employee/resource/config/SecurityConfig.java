package com.employee.resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.resource.common.config.Roles;
import com.resource.common.service.ISessionService;

@EnableWebSecurity
/**
 * refer https://docs.spring.io/spring-security/site/docs/5.0.0.M5/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html 
 * Method Summary
 * https://www.baeldung.com/spring-security-custom-filter
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	private ISessionService service;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // refer https://stackoverflow.com/a/52244758/2634405
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/**").hasAnyAuthority(Roles.getRoles())
        .and().formLogin()
        .and().logout().logoutSuccessUrl("/login").permitAll()
        .and().csrf().disable();
        
        http.addFilterBefore(new SessionFilter(service), BasicAuthenticationFilter.class);
    }
}
