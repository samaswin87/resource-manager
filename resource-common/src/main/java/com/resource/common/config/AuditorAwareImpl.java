package com.resource.common.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	// Need to find the user from DB and assigned here
	@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ashwin");
    }
}
