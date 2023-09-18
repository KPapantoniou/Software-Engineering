package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.Collections;
import java.util.List;

public class GradeAndFewestCoursesStrategy extends TemplateStrategyAlgorithm {
	private double gradeThreshold;
	private int coursesThreshold;

	public GradeAndFewestCoursesStrategy(StrategyType strategyType,int coursesThreshold,double gradeThreshold) {
		super(strategyType, coursesThreshold, gradeThreshold);
//		this.coursesThreshold= coursesThreshold;
//		this.gradeThreshold = gradeThreshold;
	}

	@Override
	protected int compareApplications(Application app1, Application app2) {
	    int courses1 = app1.getStudent().getCourses();
	    int courses2 = app2.getStudent().getCourses();

	    if (courses1 < coursesThreshold && courses2 >= coursesThreshold) {
	        return -1;
	    } else if (courses1 >= coursesThreshold && courses2 < coursesThreshold) {
	        return 1;
	    } else if (courses1 < coursesThreshold && courses2 < coursesThreshold) {
	        double avgGrade1 = app1.getStudent().getGrade();
	        double avgGrade2 = app2.getStudent().getGrade();

	        if (avgGrade1 >= gradeThreshold && avgGrade2 < gradeThreshold) {
	            return -1;
	        } else if (avgGrade1 < gradeThreshold && avgGrade2 >= gradeThreshold) {
	            return 1;
	        } else {
	            return Double.compare(avgGrade2, avgGrade1);
	        }
	    } else {
	        return Integer.compare(courses2, courses1);
	    }
	}

	@Override
	public Student findBestApplicant(List<Application> applications){
		Collections.sort(applications , this::compareApplications);
		return applications.get(0).getStudent();
    }

}