package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {
	
	private StrategyType strategyType; 
	private double gradeThreshold;
	private int coursesThreshold;

    public TemplateStrategyAlgorithm(StrategyType strategyType,int coursesThreshold,double gradeThreshold) {
		
		this.strategyType = strategyType;
		this.coursesThreshold = coursesThreshold;
		this.gradeThreshold = gradeThreshold;
	}
    public StrategyType getStrategyType() {
    	return strategyType;
    }
    
	@Override
    public Student findBestApplicant(List<Application> applications) {
       if(applications == null || applications.isEmpty()) {
    	   return null;
       }
       Student bestApplicant = null;
       switch(getStrategyType()) {
       case RANDOM_CHOICE:
    	   RandomStrategy randomStrategy = new RandomStrategy(strategyType, coursesThreshold, gradeThreshold);
    	   bestApplicant = randomStrategy.findBestApplicant(applications);
    	   break;
       case BEST_GRADE:
    	   BestAvgGradeStrategy bestAvgGradeStrategy = new BestAvgGradeStrategy(strategyType, coursesThreshold, gradeThreshold);
           bestApplicant = bestAvgGradeStrategy.findBestApplicant(applications);
    	  break;
       case FEWEST_COURSES:
    	   FewestCoursesStrategy fewestCoursesStrategy = new FewestCoursesStrategy(strategyType, coursesThreshold, gradeThreshold);
           bestApplicant = fewestCoursesStrategy.findBestApplicant(applications);
    	   break;
       case GRADE_AND_FEWEST:
    	   GradeAndFewestCoursesStrategy gradeAndFewestCoursesStrategy = new GradeAndFewestCoursesStrategy(strategyType, coursesThreshold, gradeThreshold);
           bestApplicant = gradeAndFewestCoursesStrategy.findBestApplicant(applications);
           break;
    	default:
    		throw new UnsupportedOperationException("Unknown strategy type: " + getStrategyType());
       }
       return bestApplicant;
    }

    protected abstract int compareApplications(Application app1, Application app2);    
    
}