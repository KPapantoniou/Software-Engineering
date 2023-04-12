package myy803.springboot.Diploma_Managment.model;

import java.util.List;

public interface IBestApplicantStrategy {
	
	Student findBestApplicant(List<Application> app);
		

}
