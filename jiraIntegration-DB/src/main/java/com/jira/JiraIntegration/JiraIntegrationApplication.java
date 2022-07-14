package com.jira.JiraIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.jira.JiraIntegration.repo.DetailsRepo;

@SpringBootApplication
public class JiraIntegrationApplication {
	
	@Autowired
	DetailsRepo detailsRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(JiraIntegrationApplication.class, args);
	}
	
	@Bean
	public RestTemplate template(RestTemplateBuilder builder) {
        return builder.basicAuthentication(detailsRepo.findUsername(), detailsRepo.findPass()).build();
    }
	

}
