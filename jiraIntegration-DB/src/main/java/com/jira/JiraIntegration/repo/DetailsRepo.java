package com.jira.JiraIntegration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jira.JiraIntegration.model.Details;

public interface DetailsRepo extends JpaRepository<Details, Long> {
	@Query(value="select password from cusdetails c where c.id=1", nativeQuery=true)
	public String findPass();

	@Query(value="select baseurl from cusdetails c where c.id=1", nativeQuery=true)
	public String findUrl();
	
	@Query(value="select username from cusdetails c where c.id=1", nativeQuery=true)
	public String findUsername();
}
