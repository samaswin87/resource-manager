package com.resource.manager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGen {

	public static void main(String[] args) {
		int i = 0;
		while (i < 10) {
			String password = "aswin";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
			i++;
		}
	}
}
