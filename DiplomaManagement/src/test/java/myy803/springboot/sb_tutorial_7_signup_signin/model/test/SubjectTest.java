package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SubjectTest {

    @Test
    public void testSetName() {
        Subject subject = new Subject();
        String name = "Mathematics";
        subject.setName(name);

        Assertions.assertEquals(name, subject.getName());
    }

    @Test
    public void testSetProfessor() {
        Subject subject = new Subject();
        Professor professor = new Professor();
        subject.setProfessor(professor);

        Assertions.assertEquals(professor, subject.getProfessor());
    }

    @Test
    public void testSetId() {
        Subject subject = new Subject();
        Long id = 1L;
        subject.setId(id);

        Assertions.assertEquals(id, subject.getId());
    }

   

   
    @Test
    public void testSetTitle() {
        Subject subject = new Subject();
        String title = "Computer Science";
        subject.setTitle(title);

        Assertions.assertEquals(title, subject.getTitle());
    }

    @Test
    public void testSetObjectives() {
        Subject subject = new Subject();
        String objectives = "Learn programming concepts";
        subject.setObjectives(objectives);

        Assertions.assertEquals(objectives, subject.getObjectives());
    }

    @Test
    public void testGetGrade() {
        Subject subject = new Subject();

        double expectedGrade = 0.0;
        Assertions.assertEquals(expectedGrade, subject.getGrade());
    }
}
