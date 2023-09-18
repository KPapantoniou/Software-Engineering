package myy803.springboot.sb_tutorial_7_signup_signin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import myy803.springboot.sb_tutorial_7_signup_signin.dao.StudentDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;



@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
	private StudentDAO studentDAO;
    private List<Subject> studentSubjectList;
	private CrudRepository<Student, Long> userDAO;
    //private SubjectDAO subjectDAO;


    
    public StudentServiceImpl(StudentDAO studentDAO) {
    	this.studentDAO = studentDAO;
    	this.studentSubjectList = new ArrayList<>();
    }
    
    @Override
	public void saveProfile(Student student) {
		studentDAO.save(student);
		
	}

	@Override
	public Optional<Student> retriveProfile(String studentName) {
		return studentDAO.findById(studentName);
	}

	@Override
	public List<Subject> listStudentSubjects() {
		return this.studentSubjectList ;
	}

	@Override
	public void applyToSubject(String subjectName, Integer subjectID) {
		Subject subject = null;
		
//		for(Subject s: studentSubjectList) {
//			if(s.getTitle().equals(subjectName) && s.getId() == subjectID){
//				subject = s;
//				break;
//			}
//		}
		if(subject==null) {
			throw new IllegalArgumentException("Invalid thesis subject"+ subjectName+ ","+subjectID);
		}
				
		//studentDAO.findByTitleAndAppliedSubjectIds(subject.getTitle(), subject.getId());
	}
	
	@Override
	public void updateStudent(Student student) {
	    userDAO.save(student);
	}
}
    

