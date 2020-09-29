package com.codingchallenge.HCP;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.codingchallenge.HcpApplication;
import com.codingchallenge.HCPmodel.Dependent;
import com.codingchallenge.HCPmodel.Enrollee;

import oracle.sql.DATE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HcpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HcpApplicationTests{
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testFIndAllEnrollee() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/enrollee",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetEnrolleeById() {
		Enrollee enrollee = restTemplate.getForObject(getRootUrl() + "/enrollee/1", Enrollee.class);
		System.out.println(enrollee.getFirstName());
		Assert.assertNotNull(enrollee);
	}

	@SuppressWarnings("null")
	@Test
	public void testCreateEnrollee() {
		
		Enrollee enrollee = new Enrollee();
		enrollee.setFirstName("John");
		enrollee.setLastName("Doe");
		enrollee.setBirthday("2009-08-18");
		enrollee.setActivationStatus(true);
		
		Dependent dependent = new Dependent();
		dependent.setFirstName("larry");
		dependent.setLastName("wells");
		dependent.setBirthday("1998-08-11");
		
		List<Dependent> dependents = null;
		dependents.add(dependent);
		
		enrollee.setDependents(dependents);

		ResponseEntity<Enrollee> postResponse = restTemplate.postForEntity(getRootUrl() + "/enrollee/", enrollee, Enrollee.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateEnrollee() {
		int enrolleeId = 1;

		Enrollee enrollee = restTemplate.getForObject(getRootUrl() + "/dependent/" + enrolleeId, Enrollee.class);
		enrollee.setFirstName("Mike");
		enrollee.setLastName("Lowery");

		restTemplate.put(getRootUrl() + "/enrollee/" + enrolleeId , enrollee);

		Enrollee updatedEnrollee = restTemplate.getForObject(getRootUrl() + "/dependent/" + enrolleeId, Enrollee.class);
		Assert.assertNotNull(updatedEnrollee);
	}

	@Test
	public void testDeleteEnrollee() {
		int enrolleeId = 1;
		
		 Enrollee enrollee = restTemplate.getForObject(getRootUrl() + "/dependent/" + enrolleeId, Enrollee.class);
		Assert.assertNotNull(enrollee);

		restTemplate.delete(getRootUrl() + "/enrollee/" + enrolleeId + "/dependent/" + enrolleeId);

		try {
			enrollee = restTemplate.getForObject(getRootUrl() + "/dependent/" + enrolleeId, Enrollee.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}