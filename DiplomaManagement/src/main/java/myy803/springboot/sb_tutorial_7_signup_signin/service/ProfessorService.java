package myy803.springboot.sb_tutorial_7_signup_signin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;

@Service
public interface ProfessorService {
	Professor retrieveProfile(String professorId);
	public void saveProfessor(Professor professor);
    public List<Subject> listProfessorSubjects(String professorId);
    public List<Application> listApplications(String professorId, Integer subjectId);
   
    List<Thesis> getThesesByProfessor(String currentUsername);
    void updateProfessor(Professor professor);
    Optional<Professor> getProfessorByUsername(String currentUsername);
}
