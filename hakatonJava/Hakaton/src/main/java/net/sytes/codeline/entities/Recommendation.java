package net.sytes.codeline.entities;

import java.util.Date;
import java.util.List;

public class Recommendation {

	private String firstName;
	private String lastName;
	private String companyName;
	private String jobTitle;
	private String email;
		
	public Recommendation() {
		super();
	}

	public Recommendation(String firstName, String lastName, String companyName, String jobTitle, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.email = email;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Recommendation [firstName=" + firstName + ", lastName=" + lastName + ", companyName=" + companyName
				+ ", jobTitle=" + jobTitle + ", email=" + email + "]";
	}

	
	
}
