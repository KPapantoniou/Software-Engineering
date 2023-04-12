package myy803.springboot.Diploma_Managment.model;

public class BestApplicantStrategy {
  private String applicant;
  
  public BestApplicantStrategy(String a) {
	  this.setApplicant(a);
  }

public String getApplicant() {
	return applicant;
}

public void setApplicant(String applicant) {
	this.applicant = applicant;
}
}
