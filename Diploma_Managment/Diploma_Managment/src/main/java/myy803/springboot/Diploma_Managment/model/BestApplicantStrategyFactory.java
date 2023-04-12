package myy803.springboot.Diploma_Managment.model;

public class BestApplicantStrategyFactory {
	public BestApplicantStrategy createStrategy(String applicant) {
		return new BestApplicantStrategy(applicant);
	}

}
