package myy803.springboot.sb_tutorial_7_signup_signin.model.test;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThesisTest {
	
	@Test
	void contextLoads() {
	}

    @Test
    public void testCalculateOverallGrade() {
   
        Professor professor = new Professor();
        Subject subject = new Subject();
        Student student = new Student();
        double implementationGrade = 90.0;
        double reportGrade = 80.0;
        double presentationGrade = 85.0;
       

   
        Thesis thesis = new Thesis();
        thesis.setProfessor(professor);
        thesis.setSubject(subject);
        thesis.setStudent(student);
        thesis.setImplementationGrade(implementationGrade);
        thesis.setReportGrade(reportGrade);
        thesis.setPresentationGrade(presentationGrade);

      
    }

    @Test
    public void testDefaultConstructor() {
        Thesis thesis = new Thesis();

        Assertions.assertNull(thesis.getId());
        Assertions.assertNull(thesis.getProfessor());
        Assertions.assertNull(thesis.getSubject());
        Assertions.assertNull(thesis.getStudent());
        Assertions.assertEquals(0.0, thesis.getTotalgrade(), 0.01);
        Assertions.assertEquals(0.0, thesis.getImplementationGrade(), 0.01);
        Assertions.assertEquals(0.0, thesis.getReportGrade(), 0.01);
        Assertions.assertEquals(0.0, thesis.getPresentationGrade(), 0.01);
    }

    @Test
    public void testConstructorWithParameters() {
        Long id = 1L;
        Professor professor = new Professor();
        Subject subject = new Subject();
        Student student = new Student();
        double totalGrade = 90.0;
        double implementationGrade = 80.0;
        double reportGrade = 85.0;
        double presentationGrade = 95.0;

        Thesis thesis = new Thesis(id, professor, subject, student, totalGrade, implementationGrade, reportGrade, presentationGrade);

        Assertions.assertEquals(id, thesis.getId());
        Assertions.assertEquals(professor, thesis.getProfessor());
        Assertions.assertEquals(subject, thesis.getSubject());
        Assertions.assertEquals(student, thesis.getStudent());
        Assertions.assertEquals(totalGrade, thesis.getTotalgrade(), 0.01);
        Assertions.assertEquals(implementationGrade, thesis.getImplementationGrade(), 0.01);
        Assertions.assertEquals(reportGrade, thesis.getReportGrade(), 0.01);
        Assertions.assertEquals(presentationGrade, thesis.getPresentationGrade(), 0.01);
    }

    @Test
    public void testGettersAndSetters() {
        Thesis thesis = new Thesis();

        Long id = 1L;
        Professor professor = new Professor();
        Subject subject = new Subject();
        Student student = new Student();
        double totalGrade = 90.0;
        double implementationGrade = 80.0;
        double reportGrade = 85.0;
        double presentationGrade = 95.0;

        thesis.setId(id);
        thesis.setProfessor(professor);
        thesis.setSubject(subject);
        thesis.setStudent(student);
        thesis.setTotalgrade(totalGrade);
        thesis.setImplementationGrade(implementationGrade);
        thesis.setReportGrade(reportGrade);
        thesis.setPresentationGrade(presentationGrade);

        Assertions.assertEquals(id, thesis.getId());
        Assertions.assertEquals(professor, thesis.getProfessor());
        Assertions.assertEquals(subject, thesis.getSubject());
        Assertions.assertEquals(student, thesis.getStudent());
        Assertions.assertEquals(totalGrade, thesis.getTotalgrade(), 0.01);
        Assertions.assertEquals(implementationGrade, thesis.getImplementationGrade(), 0.01);
        Assertions.assertEquals(reportGrade, thesis.getReportGrade(), 0.01);
        Assertions.assertEquals(presentationGrade, thesis.getPresentationGrade(), 0.01);
    }
}
