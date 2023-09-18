package myy803.springboot.sb_tutorial_7_signup_signin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;

@Service
public interface StudentService {
	public void saveProfile(Student student);
	Optional<Student> retriveProfile(String something);
    List<Subject> listStudentSubjects();
    public void applyToSubject(String something, Integer number);
    void updateStudent(Student student);
}
