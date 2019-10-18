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

	@Before(value = "@annotation(EnableTags) && args(map,..)") // aspect method who have the annotation
	public void handle(JoinPoint joinPoint, ModelMap map) throws Exception {
		EnableTags tags = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(EnableTags.class);
		Arrays.asList(tags.value()).forEach(t -> map.addAttribute(t, true));
	 }
}
