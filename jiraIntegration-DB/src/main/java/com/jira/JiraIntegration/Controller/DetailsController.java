package com.jira.JiraIntegration.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.jira.JiraIntegration.model.Details;
import com.jira.JiraIntegration.Service.DetailsService;
import com.jira.JiraIntegration.model.JsonDetails;
import com.jira.JiraIntegration.model.ResponseType;

@RestController
public class DetailsController {
	@Autowired
	private DetailsService detailsService;
	
	@PostMapping("/DetailsSave")
	public String insertDetails(@RequestBody Details details) {
		detailsService.insertDetails(details);
		return "Record is saved Successfully";
	}
	
	@RequestMapping(value="/ticket", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseEntity<ResponseType> createJiraTicket(@RequestBody JsonDetails jsonDetails){
		try {
			ResponseType response = detailsService.createJiraTicket(jsonDetails);
			return new ResponseEntity<ResponseType>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseType>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getIssue/{IssueId}",method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getJiraIssue(@PathVariable("IssueId") String issueId) {
		Object response = detailsService.getJiraIssue(issueId);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/deleteIssue/{IssueKey}",method = RequestMethod.DELETE)
	public @ResponseBody String deleteIssue(@PathVariable("IssueKey") String issueKey) {
		String response = detailsService.deleteIssue(issueKey);
		return response;
	}
	
	@RequestMapping(value = "/updateIssue/{IssueKey}",method = RequestMethod.PUT)
	public @ResponseBody String updateJiraTicket(@RequestBody JsonDetails jsonDetails, @PathVariable("IssueKey") String issueKey) {
		String response = detailsService.updateJiraTicket(jsonDetails,issueKey);
		return response;
	}
	

}
