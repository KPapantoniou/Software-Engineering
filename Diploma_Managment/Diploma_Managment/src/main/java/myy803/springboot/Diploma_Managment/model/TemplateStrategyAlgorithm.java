package myy803.springboot.Diploma_Managment.model;

import java.util.List;

public class TemplateStrategyAlgorithm implements IBestApplicantStrategy  {
	
	public  TemplateStrategyAlgorithm() {}
	public Student findBestApplicant(List<Application> app ) {
		return (Student) app; 
	}
}
