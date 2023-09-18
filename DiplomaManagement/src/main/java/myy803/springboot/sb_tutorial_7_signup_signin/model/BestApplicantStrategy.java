package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.List;

public interface BestApplicantStrategy {

    Student findBestApplicant(List<Application> applications);

}