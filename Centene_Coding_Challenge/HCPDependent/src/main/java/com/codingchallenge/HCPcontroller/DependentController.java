package com.codingchallenge.HCPcontroller;

import java.util.List;

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
import com.codingchallenge.HCPrepository.DependentRepository;
import com.codingchallenge.HCPrepository.EnrolleeRepository;

@RestController
@RequestMapping("/api")
public class DependentController {
	
	@Autowired
	EnrolleeRepository enrolleeRepository;
	
	@Autowired
	DependentRepository dependentRepository;
	
	@GetMapping("/dependents")
	public List<Dependent> findAllDependents(){
		
		List<Dependent> dependent = (List<Dependent>) dependentRepository.findAll();
		
		return dependent;
	}
	
	@GetMapping("/dependent/{id}")
	public ResponseEntity<Dependent> findById(@PathVariable(value = "id") int dependentId) {

		Dependent dependent = new Dependent();
		
		try {	
			dependent = dependentRepository.findById(dependentId);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.ok(dependent);
	} 
	
	@GetMapping("/enrollee/{id}/dependents")
	public List<Dependent> findAllDependentsByEnrollee(@PathVariable(value = "id") int enrolleeId){
		
		Enrollee enrollee = enrolleeRepository.findById(enrolleeId);
		
		List<Dependent> dependent = enrollee.getDependents();
		
		return dependent;
	}
	
	@PostMapping("/enrollee/{id}/dependent")
	public ResponseEntity<Dependent> addDependent(@PathVariable(name = "id") int enrolleeId, @Valid @RequestBody Dependent newDependent) {
		
		
		Enrollee enrollee = new Enrollee();
		Dependent dependent = new Dependent();
		
		try {	
			enrollee = enrolleeRepository.findById(enrolleeId);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		List<Dependent> dependents = enrollee.getDependents();
		
		dependents.add(dependent);
		
		enrollee.setDependents(dependents);
		
		enrolleeRepository.save(enrollee);
		
		return ResponseEntity.ok(dependent);
	}
	
	@PutMapping("/enrollee/{id}/dependent/{id}")
	public ResponseEntity<Dependent> updateDependent(@PathVariable(value = "id") int enrolleeId, @PathVariable(value = "id") int dependentId, Dependent newDependent ){
		
		Enrollee enrollee = new Enrollee();
		Dependent dependent = new Dependent();
		
		try {	
			enrollee = enrolleeRepository.findById(enrolleeId);
			dependent = dependentRepository.findById(dependentId);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		dependent.setFirstName(newDependent.getFirstName());
		dependent.setLastName(newDependent.getLastName());
		dependent.setBirthday(newDependent.getBirthday());
		
		final Dependent updatedDependent = dependentRepository.save(dependent);
		
		List<Dependent> dependents = enrollee.getDependents();
		for(int i = 0;i>dependents.size();i++) {
			if(dependents.get(i).equals(dependent)) {
				
				dependents.set(i, newDependent);
				enrollee.setDependents(dependents);
				enrolleeRepository.save(enrollee);
			}	
		}
		
		return ResponseEntity.ok(updatedDependent);
	}
	
	@DeleteMapping("/enrollee/{id}/dependent/{id}")
	public ResponseEntity<Dependent> deleteDependent(@PathVariable(value = "id") int enrolleeId, @PathVariable(value = "id") int dependentId) throws Exception{
		
		Enrollee enrollee = new Enrollee();
		Dependent dependent = new Dependent();
	
	try {	
		enrollee = enrolleeRepository.findById(enrolleeId);
		dependent = dependentRepository.findById(dependentId);
	}catch(Exception e) {
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
		
		List<Dependent> dependents = enrollee.getDependents();
		for(int i = 0;i>dependents.size();i++) {
			if(dependents.get(i).equals(dependent)) {
				dependents.remove(i);
			}	
		}
		
		dependentRepository.deleteById(dependentId);
		
		return ResponseEntity.ok().build();
	}

}
