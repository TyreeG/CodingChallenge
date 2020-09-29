package com.codingchallenge.HCPmodel;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Dependent")
public class Dependent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dependent_id")
	private int dependentId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birthday")
	private String birthday;
	
	private Enrollee enrollee; 

	public Dependent() {
		
	}
	
	public Dependent(int dependentId, String firstName, String lastName, String birthday) {
		super();
		this.dependentId = dependentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public Dependent(int dependentId, String firstName, String lastName, String birthday, Enrollee enrollee) {
		this.dependentId = dependentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.enrollee = enrollee;
	}

	public int getDependentId() {
		return dependentId;
	}

	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Enrollee getEnrollee() {
		return enrollee;
	}

	public void setEnrollee(Enrollee enrollee) {
		this.enrollee = enrollee;
	}

	@Override
	public String toString() {
		return "Dependent [dependentId=" + dependentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthday=" + birthday + "]";
	}
	
	
	
	

}
