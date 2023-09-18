package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@SpringBootTest
public class ProfessorTest {

	 @Test
	    public void testSetId() {
	        Professor professor = new Professor();
	        int id = 1;
	        professor.setId(id);

	        Assertions.assertEquals(id, professor.getId());
	    }

	    @Test
	    public void testSetFullname() {
	        Professor professor = new Professor();
	        String fullname = "John Doe";
	        professor.setFullname(fullname);

	        Assertions.assertEquals(fullname, professor.getFullname());
	    }

	    @Test
	    public void testSetSpeciality() {
	        Professor professor = new Professor();
	        String speciality = "Computer Science";
	        professor.setSpeciality(speciality);

	        Assertions.assertEquals(speciality, professor.getSpeciality());
	    }

	    @Test
	    public void testSetUsername() {
	        Professor professor = new Professor();
	        String username = "prof123";
	        professor.setUsername(username);

	        Assertions.assertEquals(username, professor.getUsername());
	    }

	   

}
