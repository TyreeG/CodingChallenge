package com.codingchallenge.HCPmodel;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Enrollee")
public class Enrollee {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "enrolle_id")
	private int enrolleId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "activation_status", nullable = false)
	private boolean activationStatus;
	
	@Column(name = "birthday", nullable = false)
	private Date birthday;
	
	@Column(name = "dependents")
	private List<Dependent> dependents;
	
	
	public Enrollee() {
		
	}
	
	public Enrollee(int enrolleId, String firstName, String lastName, boolean activationStatus, Date birthday,
			List<Dependent> dependents) {
		super();
		this.enrolleId = enrolleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.activationStatus = activationStatus;
		this.birthday = birthday;
		this.dependents = dependents;
	}

	public int getEnrolleId() {
		return enrolleId;
	}

	public void setEnrolleId(int enrolleId) {
		this.enrolleId = enrolleId;
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

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	@Override
	public String toString() {
		return "Enrollee [enrolleId=" + enrolleId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", activationStatus=" + activationStatus + ", birthday=" + birthday + ", dependents=" + dependents
				+ "]";
	}
	
	

}
