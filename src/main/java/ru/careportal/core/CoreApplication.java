package ru.careportal.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import ru.careportal.core.db.model.Admin;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.service.UserService;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})

public class CoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
