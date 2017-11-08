package net.sytes.codeline.entities;

public class RecommendationInfo {

	private String firstName1;
	private String lastName1;
	private String firstName2;
	private String lastName2;
	private String companyName;
	private String jobTitle;
	private String email;
	
	public RecommendationInfo() {
		super();
	}

	public RecommendationInfo(String firstName1, String lastName1, String firstName2, String lastName2,
			String companyName, String jobTitle, String email) {
		super();
		this.firstName1 = firstName1;
		this.lastName1 = lastName1;
		this.firstName2 = firstName2;
		this.lastName2 = lastName2;
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.email = email;
	}

	public String getFirstName1() {
		return firstName1;
	}

	public void setFirstName1(String firstName1) {
		this.firstName1 = firstName1;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getFirstName2() {
		return firstName2;
	}

	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
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
		return "RecommendationInfo [firstName1=" + firstName1 + ", lastName1=" + lastName1 + ", firstName2="
				+ firstName2 + ", lastName2=" + lastName2 + ", companyName=" + companyName + ", jobTitle=" + jobTitle
				+ ", email=" + email + "]";
	}
	
	
}
