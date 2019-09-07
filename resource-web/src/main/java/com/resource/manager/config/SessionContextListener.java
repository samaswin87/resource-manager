package com.resource.manager.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.resource.common.service.ISessionService;


@WebListener
public class SessionContextListener implements ServletContextListener {

	@Autowired
	ISessionService service;
	
	@Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
		service.clearSessionAttributes();
		service.clearSession(); 
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	service.clearSessionAttributes();
    	service.clearSession();
    }
}
