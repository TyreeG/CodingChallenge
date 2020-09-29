package com.codingchallenge.HCPrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingchallenge.HCPmodel.Enrollee;

@Repository
public interface EnrolleeRepository extends CrudRepository<Enrollee,Integer> {

	public Enrollee findById(int id);
}
