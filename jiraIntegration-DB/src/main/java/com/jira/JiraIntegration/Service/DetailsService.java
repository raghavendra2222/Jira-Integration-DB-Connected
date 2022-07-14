package com.jira.JiraIntegration.Service;



import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.client.RestTemplate;

import com.jira.JiraIntegration.model.Details;
import com.jira.JiraIntegration.model.JsonDetails;
import com.jira.JiraIntegration.model.ResponseType;
import com.jira.JiraIntegration.repo.DetailsRepo;



@Configuration
@Service
@PropertySource("classpath:application.properties")
public class DetailsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String baseUrl ;
	@Value("${ticket-url}")
	private String ticketCreationUrl ;

	@Autowired
	DetailsRepo detailsRepo;
	public Details insertDetails(Details details) {
		return detailsRepo.save(details);
	}
	
	public ResponseType createJiraTicket(JsonDetails jsonDetails ) {
		baseUrl=detailsRepo.findUrl();
		System.out.println(baseUrl.concat(ticketCreationUrl));
		ResponseType response = restTemplate.postForObject(baseUrl.concat(ticketCreationUrl), jsonDetails,  ResponseType.class);
		if(response != null) {
			return response;
		}
		return null;
	}
	
	public Object getJiraIssue(String issueId) {
		baseUrl=detailsRepo.findUrl();
		String url = baseUrl+ticketCreationUrl+issueId;
		System.out.println(url);
		Object response = restTemplate.getForObject(url, Object.class);
		return response;
	}
	
	public String deleteIssue(String issueKey) {
		baseUrl=detailsRepo.findUrl();
		String url = baseUrl+ticketCreationUrl+issueKey;
		restTemplate.delete(url);
		return "successfully deleted";
	}
	
	public String updateJiraTicket(JsonDetails jsonDetails, String issueKey) {
		baseUrl=detailsRepo.findUrl();
		String url = baseUrl+ticketCreationUrl+issueKey;
		System.out.println(baseUrl.concat(ticketCreationUrl));
		restTemplate.put(url, jsonDetails);
	    return "Updated";
	}
	

}
