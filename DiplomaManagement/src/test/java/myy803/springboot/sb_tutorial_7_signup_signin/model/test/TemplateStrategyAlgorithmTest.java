package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.BestAvgGradeStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.FewestCoursesStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.GradeAndFewestCoursesStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.RandomStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.StrategyType;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.TemplateStrategyAlgorithm;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class TemplateStrategyAlgorithmTest {

    private static class TemplateStrategyAlgorithmTestImpl extends TemplateStrategyAlgorithm {

        private Student expectedBestApplicant;

        public TemplateStrategyAlgorithmTestImpl(StrategyType strategyType, int coursesThreshold, double gradeThreshold) {
            super(strategyType, coursesThreshold, gradeThreshold);
        }

        public void setExpectedBestApplicant(Student expectedBestApplicant) {
            this.expectedBestApplicant = expectedBestApplicant;
        }

        @Override
        public Student findBestApplicant(List<Application> applications) {
            return expectedBestApplicant;
        }

        @Override
        protected int compareApplications(Application app1, Application app2) {
            return 0;
        }

    }

    @Test
    public void testFindBestApplicant_WithRandomStrategy() {
        List<Application> applications = new ArrayList<>();
        Application application1 = new Application();
        Application application2 = new Application();
        Application application3 = new Application();
        Application application4 = new Application();
        Student student1 = new Student();
        student1.setGrade(9.0);
        student1.setCourses(5);
        Student student2= new Student();
        student2.setCourses(1);
        student2.setGrade(2.0);
        Student student3= new Student();
        student3.setCourses(1);
        student3.setGrade(9.0);
        Student student4= new Student();
        student4.setCourses(8);
        student4.setGrade(1.0);
        
        application1.setStudent(student1);
        application2.setStudent(student2);
        application3.setStudent(student3);
        application4.setStudent(student4);
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        applications.add(application4);

        RandomStrategy randomStrategy = Mockito.mock(RandomStrategy.class);
        Student expectedBestApplicant = new Student();

        TemplateStrategyAlgorithmTestImpl templateStrategyAlgorithm = new TemplateStrategyAlgorithmTestImpl(StrategyType.RANDOM_CHOICE, 3, 80.0);
        templateStrategyAlgorithm.setExpectedBestApplicant(expectedBestApplicant);

        Student bestApplicant = templateStrategyAlgorithm.findBestApplicant(applications);

        Assertions.assertEquals(expectedBestApplicant, bestApplicant);
    }

    @Test
    public void testFindBestApplicant_WithBestAvgGradeStrategy() {
        List<Application> applications = new ArrayList<>();
        Application application1 = new Application();
        Application application2 = new Application();
        Application application3 = new Application();
        Application application4 = new Application();
        Student student1 = new Student();
        student1.setGrade(9.0);
        student1.setCourses(5);
        Student student2= new Student();
        student2.setCourses(1);
        student2.setGrade(2.0);
        Student student3= new Student();
        student3.setCourses(1);
        student3.setGrade(9.0);
        Student student4= new Student();
        student4.setCourses(8);
        student4.setGrade(1.0);
        
        application1.setStudent(student1);
        application2.setStudent(student2);
        application3.setStudent(student3);
        application4.setStudent(student4);
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        applications.add(application4);

        BestAvgGradeStrategy bestAvgGradeStrategy = new BestAvgGradeStrategy(StrategyType.BEST_GRADE, 3, 8.0);
        Student expectedBestApplicant = new Student();
        expectedBestApplicant.setGrade(2.0);

        TemplateStrategyAlgorithmTestImpl templateStrategyAlgorithm = new TemplateStrategyAlgorithmTestImpl(StrategyType.BEST_GRADE, 3, 80.0);
        templateStrategyAlgorithm.setExpectedBestApplicant(expectedBestApplicant);

        Student bestApplicant = templateStrategyAlgorithm.findBestApplicant(applications);

        Assertions.assertEquals(expectedBestApplicant, bestApplicant);
        Assertions.assertEquals(expectedBestApplicant.getGrade(), bestApplicant.getGrade(), 0.01);
    }

    @Test
    public void testFindBestApplicant_WithFewestCoursesStrategy() {
        List<Application> applications = new ArrayList<>();
        Application application1 = new Application();
        Application application2 = new Application();
        Application application3 = new Application();
        Application application4 = new Application();
        Student student1 = new Student();
        student1.setGrade(9.0);
        student1.setCourses(5);
        Student student2= new Student();
        student2.setCourses(1);
        student2.setGrade(2.0);
        Student student3= new Student();
        student3.setCourses(1);
        student3.setGrade(9.0);
        Student student4= new Student();
        student4.setCourses(8);
        student4.setGrade(1.0);
        
        application1.setStudent(student1);
        application2.setStudent(student2);
        application3.setStudent(student3);
        application4.setStudent(student4);
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        applications.add(application4);
        FewestCoursesStrategy fewestCoursesStrategy = new FewestCoursesStrategy(StrategyType.FEWEST_COURSES, 3, 8.0);
        Student expectedBestApplicant = new Student();
        expectedBestApplicant.setCourses(1);

        TemplateStrategyAlgorithmTestImpl templateStrategyAlgorithm = new TemplateStrategyAlgorithmTestImpl(StrategyType.FEWEST_COURSES, 3, 8.0);
        templateStrategyAlgorithm.setExpectedBestApplicant(expectedBestApplicant);

        Student bestApplicant = templateStrategyAlgorithm.findBestApplicant(applications);

        Assertions.assertEquals(expectedBestApplicant, bestApplicant);
        Assertions.assertEquals(expectedBestApplicant.getCourses(), bestApplicant.getCourses(), 0.01);
    }
    @Test
    public void testFindBestApplicant_WithGradeAndFewestCoursesStrategy() {
        List<Application> applications = new ArrayList<>();
        
        Application application1 = new Application();
        Application application2 = new Application();
        Application application3 = new Application();
        Application application4 = new Application();
        Student student1 = new Student();
        student1.setGrade(9.0);
        student1.setCourses(5);
        Student student2= new Student();
        student2.setCourses(1);
        student2.setGrade(2.0);
        Student student3= new Student();
        student3.setCourses(1);
        student3.setGrade(9.0);
        Student student4= new Student();
        student4.setCourses(8);
        student4.setGrade(1.0);
        
        application1.setStudent(student1);
        application2.setStudent(student2);
        application3.setStudent(student3);
        application4.setStudent(student4);
        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
        applications.add(application4);
        
        GradeAndFewestCoursesStrategy gradeAndFewestCoursesStrategyMock = new GradeAndFewestCoursesStrategy(StrategyType.GRADE_AND_FEWEST, 3, 8.0);
        Student expectedBestApplicant = new Student();
        expectedBestApplicant.setCourses(1);
        expectedBestApplicant.setGrade(9.0);
        TemplateStrategyAlgorithmTestImpl templateStrategyAlgorithm = new TemplateStrategyAlgorithmTestImpl(StrategyType.GRADE_AND_FEWEST, 3, 8.0);
        templateStrategyAlgorithm.setExpectedBestApplicant(expectedBestApplicant);

        Student bestApplicant = templateStrategyAlgorithm.findBestApplicant(applications);

        Assertions.assertEquals(expectedBestApplicant, bestApplicant);
        Assertions.assertEquals(expectedBestApplicant.getCourses(), bestApplicant.getCourses(), 0.01);
        Assertions.assertEquals(expectedBestApplicant.getGrade(), bestApplicant.getGrade(), 0.01);
    }
    

}
