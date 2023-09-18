package myy803.springboot.sb_tutorial_7_signup_signin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.sb_tutorial_7_signup_signin.dao.ApplicationDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.ProfessorDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.StudentDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.SubjectDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.ThesisDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;
import myy803.springboot.sb_tutorial_7_signup_signin.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
    private ProfessorDAO professorDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    private ThesisDAO thesisDAO;
    
    @Autowired
    private StudentDAO studentDAO;
    
    public ProfessorServiceImpl(ProfessorDAO professorDAO) {
    	this.professorDAO = professorDAO;
    	
    }

    @Override
    public Professor retrieveProfile(String professorId) {
        return professorDAO.findById(professorId).orElse(null);
    }

    public void saveProfessor(Professor professor) {
        professorDAO.save(professor);
    }

    @Override
    public List<Subject> listProfessorSubjects(String professorId) {
        Professor professor = professorDAO.findById(professorId).orElse(null);
        if (professor != null) {
            //return subjectDAO.findByPId(professor.getId());
        	return null;
        }
        return new ArrayList<>();
    }

    
    @Override
    public List<Application> listApplications(String professorId, Integer subjectId) {
        return applicationDAO.findByIdAndSubject(professorId, subjectId);
    }


    @Override
    public List<Thesis> getThesesByProfessor(String speciality) {
        Professor professor = (Professor) professorDAO.findBySpeciality(speciality);
        if (professor != null) {
            //return thesisDAO.findBySupervisorId(professor.getId());
        	return null;
        }
        return new ArrayList<>();
    }

    @Override
    public void updateProfessor(Professor professor) {
        professorDAO.save(professor);
    }

    @Override
    public Optional<Professor> getProfessorByUsername(String currentUsername) {
        return professorDAO.findByFullname(currentUsername);
    }

	
	



}
