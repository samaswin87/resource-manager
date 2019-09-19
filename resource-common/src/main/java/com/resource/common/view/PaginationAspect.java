package com.resource.common.view;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

@Aspect
@Configuration
@Component
public class PaginationAspect {
	
	@Autowired
    private ApplicationContext _appContext;
	
	@Before("@annotation(PaginatedList) && args(pageIndex,map,..)")
	public void before(JoinPoint joinPoint, @RequestParam("page") Optional<Integer> pageIndex, ModelMap map) throws Throwable{
		PaginatedList pagination = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(PaginatedList.class);
		int currentPage = pageIndex.orElse(1);
		Pageable page = PageRequest.of(currentPage - 1, Integer.parseInt(pagination.pageSize()), Sort.by(pagination.sort()).descending());
		Object service = _appContext.getBean(pagination.service());
		Method method = service.getClass().getMethod(pagination.method(), Pageable.class);
		Page<?> objects = (Page<?>)method.invoke(service, page);
		map.addAttribute(pagination.name(), objects);
		Integer totalPages = objects.getTotalPages();
		if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
									             .boxed()
									             .collect(Collectors.toList());
            map.addAttribute("pageNumbers", pageNumbers);
        }
	}
}
