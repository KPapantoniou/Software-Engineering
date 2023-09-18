package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.Collections;
import java.util.List;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {   
   

    public BestAvgGradeStrategy(StrategyType strategyType,int coursesThreshold,double gradeThreshold) {
		super(strategyType, coursesThreshold, gradeThreshold);
	}

	@Override
    public int compareApplications(Application app1, Application app2) {
		double avgGrade1 = app1.getStudent().getGrade();
        double avgGrade2 = app2.getStudent().getGrade();
        return Double.compare(avgGrade2, avgGrade1);
    }  
	
	@Override
	public Student findBestApplicant(List<Application> applications){
		Collections.sort(applications , this::compareApplications);
		return applications.get(0).getStudent();
    }
}