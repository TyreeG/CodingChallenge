package com.codingchallenge.HCPrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingchallenge.HCPmodel.Dependent;

@Repository
public interface DependentRepository extends CrudRepository<Dependent,Integer> {
	
	public Dependent findById(int id);

}
