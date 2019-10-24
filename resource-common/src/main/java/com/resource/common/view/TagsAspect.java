package com.resource.common.view;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Aspect
@Configuration
@Component
public class TagsAspect {

	@Before("execution(* com.employee.resource.*.*Controller*.*(..)) && args(map,..)")
	public void handle(JoinPoint joinPoint, ModelMap map) throws Exception {
		Class clazz = joinPoint.getTarget().getClass();
		EnableTags tags = (EnableTags) clazz.getDeclaredAnnotation(EnableTags.class);
		if (tags != null)
		{
			Arrays.asList(tags.value()).forEach(t -> map.addAttribute(t, true));
			map.addAttribute(tags.selected(), "selected");
		}
	}
	
	@Before(value = "@annotation(SelectedTab) && args(map,..)") // aspect method who have the annotation
	public void handleTabs(JoinPoint joinPoint, ModelMap map) throws Exception {
		SelectedTab tab = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SelectedTab.class);
		map.addAttribute(tab.value()+"_selected", "selected");
	}
	
}
