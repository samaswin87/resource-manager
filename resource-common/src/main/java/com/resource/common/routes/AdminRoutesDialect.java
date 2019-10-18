package com.resource.common.routes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class AdminRoutesDialect extends AbstractDialect implements IExpressionObjectDialect {

	public AdminRoutesDialect() {
		super("adminRoutes");
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {
			
			private final Set<String> objectNames = Collections.unmodifiableSet(
			        new HashSet<>(Arrays.asList("admin")));
			
            @Override
            public Set<String> getAllExpressionObjectNames() {
            	return objectNames;
            }

            @Override
            public Object buildObject(IExpressionContext context,
                    String expressionObjectName) {
                return AdminPath.class;
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
	}

}
