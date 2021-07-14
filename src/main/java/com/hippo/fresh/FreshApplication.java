package com.hippo.fresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 启动类
 */
@SpringBootApplication
public class FreshApplication {

	public static void main(String[] args) {

		SpringApplication.run(FreshApplication.class, args);
	}

}
