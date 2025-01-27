package com.leadconsult.task;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication implements CommandLineRunner {
	// Spring Cloud Azure will automatically inject SecretClient in your ApplicationContext.
	private final SecretClient secretClient;
	public TaskApplication(SecretClient secretClient) {
		this.secretClient = secretClient;
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("h2url: " + secretClient.getSecret("h2url").getValue());
	}

}
