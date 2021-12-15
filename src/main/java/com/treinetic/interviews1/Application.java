package com.treinetic.interviews1;

import com.treinetic.interviews1.domain.Role;
import com.treinetic.interviews1.domain.Student;
import com.treinetic.interviews1.repository.RoleRepository;
import com.treinetic.interviews1.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {
		return (args) -> {
			Role adminRole = roleRepository.findByRole("ADMIN");
			Role userRole;
			if (adminRole == null) {
				userRole = new Role();
				userRole.setRole("ADMIN");
				roleRepository.save(userRole);
			}

			userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}

		};
	}

	@Bean
	CommandLineRunner init1(StudentRepository studentRepository) {
		return (args) -> {
			Student student = studentRepository.findByEmail("admin@treinetic.com");
			Student student1;
			if (student == null) {
				student1 = new Student();
				student1.setEmail("admin@treinetic.com");
				student1.setPassword("iAmAdmin");
				student1.setEnabled(true);
				studentRepository.save(student1);
			}


		};
	}
}
