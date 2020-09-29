package com.codingchallenge.HCPcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Enrollee> findEnrolleeById(@PathVariable(value = "id") int enrolleeId){
		
		Enrollee enrollee = new Enrollee(); 
		
		try {	
			enrollee = enrolleeRepository.findById(enrolleeId);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		return ResponseEntity.ok(enrollee);
	}
	
	@PostMapping("/enrollee")
	public ResponseEntity<Enrollee> addEnrollee(@Valid @RequestBody Enrollee newEnrollee) {
			
		Enrollee enrollee  = enrolleeRepository.save(newEnrollee);
		
		return ResponseEntity.ok(enrollee);
	}
	
	@PutMapping("/enrollee/{id}")
	public ResponseEntity<Enrollee> updateEnrollee(@PathVariable(value = "id") int enrolleeId, @Valid @RequestBody Enrollee newEnrollee){
		
		Enrollee enrollee = new Enrollee(); 
		
		try {	
			enrollee = enrolleeRepository.findById(enrolleeId);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		enrollee.setFirstName(newEnrollee.getFirstName());
		enrollee.setLastName(newEnrollee.getLastName());
		enrollee.setActivationStatus(newEnrollee.isActivationStatus());
		enrollee.setBirthday(newEnrollee.getBirthday());
		
		Enrollee updatedEnrollee = enrolleeRepository.save(enrollee);
		
		return ResponseEntity.ok(updatedEnrollee);
	}
	
	@DeleteMapping("/enrollee/{id}")
public ResponseEntity<?> deleteDependent(@PathVariable(value = "id") int enrolleeId) throws Exception{
		
	
	try {	
		enrolleeRepository.findById(enrolleeId);
	}catch(Exception e) {
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
		
		enrolleeRepository.deleteById(enrolleeId);
		
		return ResponseEntity.ok().build();
	}
}
