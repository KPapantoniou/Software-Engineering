package myy803.springboot.sb_tutorial_7_signup_signin.model;
import java.util.Collections;
import java.util.List;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

    public FewestCoursesStrategy(StrategyType strategyType, int coursesThreshold, double gradeThreshold) {
        super(strategyType, coursesThreshold, gradeThreshold);
    }

    @Override
    protected int compareApplications(Application app1, Application app2) {
        int numCourses1 = app1.getStudent().getCourses();
        int numCourses2 = app2.getStudent().getCourses();
        return Integer.compare(numCourses1, numCourses2);
    }

    @Override
    public Student findBestApplicant(List<Application> applications) {
        if (applications == null || applications.isEmpty()) {
            return null;
        }  
        Collections.sort(applications, this::compareApplications);
        return applications.get(0).getStudent();
    }
}