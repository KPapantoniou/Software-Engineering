package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;

@SpringBootTest
public class StudentTest {

    @Test
    public void testSetId() {
        Student student = new Student();
        Integer id = 1;
        student.setId(id);

        Assertions.assertEquals(id, student.getId());
    }


    @Test
    public void testSetName() {
        Student student = new Student();
        String name = "John Doe";
        student.setName(name);

        Assertions.assertEquals(name, student.getName());
    }

    @Test
    public void testSetYear() {
        Student student = new Student();
        Integer year = 3;
        student.setYear(year);

        Assertions.assertEquals(year, student.getYear());
    }

    @Test
    public void testSetGrade() {
        Student student = new Student();
        Double grade = 4.5;
        student.setGrade(grade);

        Assertions.assertEquals(grade, student.getGrade());
    }

    @Test
    public void testSetCourses() {
        Student student = new Student();
        Integer courses = 5;
        student.setCourses(courses);

        Assertions.assertEquals(courses, student.getCourses());
    }

    @Test
    public void testSetUsername() {
        Student student = new Student();
        String username = "johndoe123";
        student.setUsername(username);

        Assertions.assertEquals(username, student.getUsername());
    }
}
