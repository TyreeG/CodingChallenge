package com.codingchallenge.HCPcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.HCPmodel.Dependent;
import com.codingchallenge.HCPmodel.Enrollee;
import com.codingchallenge.HCPrepository.EnrolleeRepository;

@RestController
@RequestMapping("/api")
public class EnrolleeController {

	@Autowired
	EnrolleeRepository enrolleeRepository;
	
	
	@GetMapping("/enrollees")
	public List<Enrollee> findAllEnrollees(){
		
		ArrayList<Enrollee> enrollee = (ArrayList<Enrollee>) enrolleeRepository.findAll();
		
		return enrollee;
	}
	
	@GetMapping("/enrollee/{id}")
	public Enrollee findEnrolleeById(@PathVariable(value = "id") int enrolleeId){
		
		Enrollee enrollee = enrolleeRepository.findById(enrolleeId);
		
		
		return enrollee;
	}
	
	@PostMapping("/enrollee")
	public Enrollee addEnrollee(@Valid @RequestBody Enrollee enrollee) {
			
		
		return enrolleeRepository.save(enrollee);
	}
	
	@PutMapping("/enrollee/{id}")
	public ResponseEntity<Enrollee> updateEnrollee(@PathVariable(value = "id") int enrolleeId, @Valid @RequestBody Enrollee newEnrollee){
		
		Enrollee enrollee = enrolleeRepository.findById(enrolleeId);
		
		enrollee.setFirstName(newEnrollee.getFirstName());
		enrollee.setLastName(newEnrollee.getLastName());
		enrollee.setActivationStatus(newEnrollee.isActivationStatus());
		enrollee.setBirthday(newEnrollee.getBirthday());
		
		Enrollee updatedEnrollee = enrolleeRepository.save(enrollee);
		
		return ResponseEntity.ok(updatedEnrollee);
	}
	
	@DeleteMapping("/enrollee/{id}")
	public Map<String,Boolean> deleteEnrollee(@PathVariable(value = "id") int enrolleId){
		
		Enrollee enrollee = enrolleeRepository.findById(enrolleId);
		
		enrolleeRepository.delete(enrollee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Delete", true);
		return response;
	}
}
