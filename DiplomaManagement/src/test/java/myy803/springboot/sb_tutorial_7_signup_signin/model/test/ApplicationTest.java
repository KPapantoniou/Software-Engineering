package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;

@SpringBootTest
public class ApplicationTest {
	 @Test
	    public void testGettersAndSetters() {
	        Subject subject = new Subject();
	        Student student = new Student();
	        Professor professor = new Professor();

	        Application application = new Application();
	        application.setId(1L);
	        application.setSubject(subject);
	        application.setStudent(student);
	        application.setProfessor(professor);

	        Assertions.assertEquals(1L, application.getId());
	        Assertions.assertEquals(subject, application.getSubject());
	        Assertions.assertEquals(student, application.getStudent());
	        Assertions.assertEquals(professor, application.getProfessor());
	    }
}
