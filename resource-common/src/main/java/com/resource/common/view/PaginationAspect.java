package com.resource.common.view;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
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
	
	@Before("@annotation(PaginatedList) && args(pageIndex,map,request,..)")
	public void beforePaginatedList(JoinPoint joinPoint, @RequestParam("page") Optional<Integer> pageIndex, ModelMap map, HttpServletRequest request) throws Throwable{
		String url = getFullURL(request);
		PaginatedList pagination = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(PaginatedList.class);
		int currentPage = pageIndex.orElse(1);
		Pageable page = PageRequest.of(currentPage - 1, Integer.parseInt(pagination.pageSize()), Sort.by(pagination.sort()).descending());
		Object service = _appContext.getBean(pagination.service());
		Method method = service.getClass().getMethod(pagination.method(), Pageable.class);
		Page<?> objects = (Page<?>)method.invoke(service, page);
		map.addAttribute(pagination.name(), objects);
		Integer totalPages = objects.getTotalPages();
		if (totalPages > 0) {
			setMap(map, totalPages, currentPage, url);
        }
	}
	
	@Before("@annotation(PaginateByName) && args(pageIndex,name,map,request,..)")
	public void beforePaginateByName(JoinPoint joinPoint, @RequestParam("page") Optional<Integer> pageIndex, @RequestParam("name") Optional<String> name, ModelMap map, HttpServletRequest request) throws Throwable{
		if (StringUtils.isEmpty(name.get()))
			return;
		String url = getFullURL(request);
		PaginateByName pagination = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(PaginateByName.class);
		int currentPage = pageIndex.orElse(1);
		Pageable page = PageRequest.of(currentPage - 1, Integer.parseInt(pagination.pageSize()), Sort.by(pagination.sort()).descending());
		Object service = _appContext.getBean(pagination.service());
		Method method = service.getClass().getMethod(pagination.method(), Pageable.class, String.class);
		Page<?> objects = (Page<?>)method.invoke(service, page, name.get());
		map.addAttribute(pagination.name(), objects);
		Integer totalPages = objects.getTotalPages();
		if (totalPages > 0) {
			setMap(map, totalPages, currentPage, url);
        }
	}
	
	private static String getFullURL(HttpServletRequest request) {
		String url = null;
	    StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
	    String queryString = request.getQueryString();
	    if (queryString == null) {
	    	url = requestURL.toString();
	    } else {
	    	url = requestURL.append('?').append(queryString).toString();
	    }
	    url = removeQueryParameter(requestURL.toString(), "page");
	    return url;
	}
	
	private static String removeQueryParameter(String url, String parameterName) {
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			List<NameValuePair> queryParameters = uriBuilder.getQueryParams();
		    for (Iterator<NameValuePair> queryParameterItr = queryParameters.iterator(); queryParameterItr.hasNext();) {
		        NameValuePair queryParameter = queryParameterItr.next();
		        if (queryParameter.getName().equals(parameterName)) {
		            queryParameterItr.remove();
		        }
		    }
		    uriBuilder.setParameters(queryParameters);
		    url = uriBuilder.build().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	    
	    return url;
	}
	
	private void setMap(ModelMap map, Integer totalPages, Integer currentPage, String url) {
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	             .boxed()
	             .collect(Collectors.toList());
		map.addAttribute("pageNumbers", pageNumbers);
		map.addAttribute("last", totalPages);
		map.addAttribute("first", 1);
		map.addAttribute("url", url);
		map.addAttribute("next", currentPage + 1);
		map.addAttribute("prev", currentPage - 1);
		map.addAttribute("isLast", currentPage == totalPages);
		map.addAttribute("isFirst", currentPage == 1);
	}
}
