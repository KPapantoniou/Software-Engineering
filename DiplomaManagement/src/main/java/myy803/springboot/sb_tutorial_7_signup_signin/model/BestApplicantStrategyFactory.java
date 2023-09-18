package myy803.springboot.sb_tutorial_7_signup_signin.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BestApplicantStrategyFactory {

    public static BestApplicantStrategy getBestApplicantStrategy(String strategyType, int courseThreshold, double gradeThreshold) {
        StrategyType type = StrategyType.valueOf(strategyType.toUpperCase());

        if (type == StrategyType.BEST_GRADE) {
            return new BestAvgGradeStrategy(type, courseThreshold, gradeThreshold);
        } else if (type == StrategyType.FEWEST_COURSES) {
            return new FewestCoursesStrategy(type, courseThreshold, gradeThreshold);
        } else if (type == StrategyType.GRADE_AND_FEWEST) {
            return new GradeAndFewestCoursesStrategy(type, courseThreshold, gradeThreshold);
        } else if (type == StrategyType.RANDOM_CHOICE) {
            return new RandomStrategy(type, courseThreshold, gradeThreshold);
        } else {
            throw new IllegalArgumentException("Invalid strategy type: " + strategyType);
        }
    }
}