package com.badr.security;

import com.badr.security.auth.AuthenticationService;
import com.badr.security.auth.RegisterRequest;
import com.badr.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;

import static com.badr.security.user.Role.*;

@SpringBootApplication()
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service){
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("admin")
					.lastname("admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)

					.build();
			System.out.println("Athentication token for admin :" + service.register(admin).getAccessToken());


			var manager = RegisterRequest.builder()
					.firstname("manager")
					.lastname("manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)

					.build();
			System.out.println("Athentication token for manager :" + service.register(manager).getAccessToken());

		};
	}
}
