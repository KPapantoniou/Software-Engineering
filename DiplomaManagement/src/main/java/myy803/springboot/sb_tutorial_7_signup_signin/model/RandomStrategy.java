package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomStrategy extends TemplateStrategyAlgorithm {

	public RandomStrategy(StrategyType strategyType,int coursesThreshold,double gradeThreshold) {
		super(strategyType, coursesThreshold, gradeThreshold);
	}

	@Override
	public int compareApplications(Application app1, Application app2) {
		Random rand = new Random();    
		int randInt = rand.nextInt(100);
		if(randInt%2==0) {
			return   1;
		}else {
			return -1;
		}  
		    
		 
	}
	@Override
	public Student findBestApplicant(List<Application> applications){
		Collections.sort(applications , this::compareApplications);
		return applications.get(0).getStudent();
    }

	
}