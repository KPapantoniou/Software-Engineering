package myy803.springboot.sb_tutorial_7_signup_signin.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.sb_tutorial_7_signup_signin.dao.ApplicationDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.ProfessorDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.StudentDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.SubjectDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;
import myy803.springboot.sb_tutorial_7_signup_signin.service.UserService;
import myy803.springboot.sb_tutorial_7_signup_signin.service.StudentService;

@Controller
public class UserController {
    private final UserService userService;
    private final StudentDAO studentDAO;
    private final ProfessorDAO professorDAO;
    private final SubjectDAO subjectDAO;
    private final ApplicationDAO applicationDAO;

    public UserController(UserService userService, StudentDAO studentDAO, ProfessorDAO professorDAO, SubjectDAO subjectDAO, ApplicationDAO applicationDAO) {
        this.userService = userService;
        this.studentDAO = studentDAO;
        this.professorDAO = professorDAO;
        this.subjectDAO = subjectDAO;
        this.applicationDAO = applicationDAO;
    }
    
    // Creates a new student model attribute for the view
    @ModelAttribute("student")
    public Student createStudentModel() {
        return new Student();
    }
    
	// Retrieves the user dashboard view
	@RequestMapping("/user/dashboard")
	public String getUserHome() {
	    return "user/dashboard";
	}
	
	// Retrieves the user profile view
    @RequestMapping("/user/profile")
    public String getAdminsProfile() {
        return "user/profile";
    }
    
    // Retrieves the subjects view and adds available subjects to the model
    @RequestMapping("/user/subjects")
    public String getSubjects(Model model) {
        
        List<Subject> subjects = subjectDAO.findAll();
        model.addAttribute("availableSubjects", subjects); 

        return "user/subjects";
    }

    // Saves the user profile and redirects to the user dashboard view
    @PostMapping("/user/save")
    public String saveProfile(@ModelAttribute("student") Student student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Optional<Student> existingStudent = studentDAO.findByUsername(username);
        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setName(student.getName());
            studentToUpdate.setYear(student.getYear());
            studentToUpdate.setGrade(student.getGrade());
            studentToUpdate.setCourses(student.getCourses());
            studentDAO.save(studentToUpdate);
        } else {
            student.setUsername(username);
            studentDAO.save(student);
        }

        return "user/dashboard"; 
    }
    
    // Applies for a subject and redirects to the subjects view
    @PostMapping("/user/apply")
    public String applyForSubject(@RequestParam("subjectId") Long subjectId) {
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<Student> existingStudent = studentDAO.findByUsername(username);
        if (!existingStudent.isPresent()) {

            return "error";
        }


        Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
        if (!subjectOptional.isPresent()) {
            return "error";
        }

        Student student = existingStudent.get();
        Subject subject = subjectOptional.get();
        Professor professor = subject.getProfessor(); 

        Application application = new Application();
        application.setStudent(student);
        application.setProfessor(professor);

        application.setSubject(subject);

        applicationDAO.save(application);

        return "redirect:/user/subjects";
    }
    
    // Retrieves the subject details view for a specific subject
    @RequestMapping("/user/details")
    public String getSubjectDetails(@RequestParam("subjectId") Long subjectId, Model model) {

        Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
        if (!subjectOptional.isPresent()) {

            return "error";
        }

        Subject subject = subjectOptional.get();
        model.addAttribute("subject", subject);

        return "user/details";
    }
}
