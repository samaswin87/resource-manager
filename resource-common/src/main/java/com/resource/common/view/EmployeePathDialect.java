package com.resource.common.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import com.resource.common.constants.EmployeesPaths;

public class EmployeePathDialect extends AbstractDialect implements IExpressionObjectDialect{

	public EmployeePathDialect() {
		super("employees_path");
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {
			
			private final Set<String> objectNames = Collections.unmodifiableSet(
			        new HashSet<>(Arrays.asList("employees")));
			
            @Override
            public Set<String> getAllExpressionObjectNames() {
            	return objectNames;
            }

            @Override
            public Object buildObject(IExpressionContext context,
                    String expressionObjectName) {
                return EmployeesPaths.getInstance();
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
	}

}
