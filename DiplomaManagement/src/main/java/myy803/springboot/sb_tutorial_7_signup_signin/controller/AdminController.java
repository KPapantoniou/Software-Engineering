package myy803.springboot.sb_tutorial_7_signup_signin.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import myy803.springboot.sb_tutorial_7_signup_signin.dao.ApplicationDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.ProfessorDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.SubjectDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.dao.ThesisDAO;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.BestAvgGradeStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.FewestCoursesStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.GradeAndFewestCoursesStrategy;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.StrategyType;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;
import myy803.springboot.sb_tutorial_7_signup_signin.service.ProfessorService;
  
@Controller
public class AdminController {

	@Autowired
    private ProfessorService professorService;
	
	@Autowired
    private ProfessorDAO professorDAO;

	@Autowired
    private SubjectDAO subjectDAO;
	
	@Autowired
    private ApplicationDAO applicationDAO;
	
	@Autowired
    private ThesisDAO thesisDAO;
	
	// Returns the admin dashboard view.
	@RequestMapping("/admin/dashboard")
    public String getAdminHome(){
        return "admin/dashboard";
    }
	
	// Returns the admin's subjects view and retrieves the list of subjects.
	@RequestMapping("/admin/my_subjects")
	public String getMySubjects(Model model) {
	    Subject subject = new Subject(); 

	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    
	    Optional<Professor> professorOptional = professorDAO.findByUsername(username);

	    
	    if (professorOptional.isPresent()) {
	        Professor professor = professorOptional.get();

	        
	        List<Subject> subjects = subjectDAO.findByProfessor(professor);

	        model.addAttribute("subject", subject);
	        model.addAttribute("subjects", subjects); 	    }

	    
	    List<Professor> professors = professorDAO.findAll();
	    model.addAttribute("professors", professors); 

	    return "admin/my_subjects";
	}





	// Returns the admin's profile view.
	@RequestMapping("/admin/profile")
    public String getAdminsProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Optional<Professor> professor = professorDAO.findByFullname(username);
        if (professor.isPresent()) {
            model.addAttribute("professor", professor.get());
        } else {
            model.addAttribute("professor", new Professor());
        }
        return "admin/profile";
    }
    
	// Saves the admin's profile information.
	@PostMapping("/admin/save")
	public String saveProfile(@ModelAttribute("professor") Professor professor) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String username = userDetails.getUsername();
	        
	        Optional<Professor> existingProfessor = professorDAO.findByUsername(username);
	        if (existingProfessor.isPresent()) {
	        	Professor professorToUpdate = existingProfessor.get();
	            professorToUpdate.setFullname(professor.getFullname());
	            professorToUpdate.setSpeciality(professor.getSpeciality());
	            professorDAO.save(professorToUpdate);
	        } else {
	            professor.setUsername(username);
	            professorDAO.save(professor);
	        }
	        
	        return "admin/dashboard";
	}

	// Saves the subject information for the admin.
	Professor professor;
	@PostMapping("/admin/my_subjects")
	public String saveSubject(@ModelAttribute("subject") Subject subject) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    String username = userDetails.getUsername();

	    Optional<Professor> existingProfessor = professorDAO.findByUsername(username);
	    if (existingProfessor.isPresent()) {
	        Professor professorToUpdate = existingProfessor.get();
	        
	        subject.setProfessor(professorToUpdate);
	        subjectDAO.save(subject);
	    } else {

	        return "redirect:/admin/my_subjects?error";
	    }

	    return "redirect:/admin/my_subjects";
	}

	// Deletes a subject based on its ID.
	@PostMapping("/admin/delete_subject/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") Long subjectId) {
	    subjectDAO.deleteById(subjectId); 
	    return "redirect:/admin/my_subjects"; 
	}

	// Retrieves the applications for a specific subject.
	@RequestMapping("/admin/subject_applications/{subjectId}")
	public String getSubjectApplications(@PathVariable("subjectId") Long subjectId, Model model) {
	    
	    Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
	    if (!subjectOptional.isPresent()) {

	        return "error";
	    }

	    
	    List<Application> applications = applicationDAO.findBySubjectId(subjectId);
	    model.addAttribute("applications", applications);
	    model.addAttribute("subjectId", subjectId); 
	    model.addAttribute("app", new Application()); 

	    return "admin/subject_applications";
	}


	// Assigns a subject to a student based on the application.
	@PostMapping("/admin/assign_subject")
	public String assignSubject(@RequestParam("applicationId") Long applicationId,
	                            @RequestParam("subjectId") Long subjectId, Model model) {
	    Optional<Application> applicationOptional = applicationDAO.findById(applicationId);
	    if (!applicationOptional.isPresent()) {

	        return "error";
	    }

	    Application application = applicationOptional.get();
	    Student student = application.getStudent();
	    Subject subject = application.getSubject();
	    Professor professor = application.getProfessor();

	    List<Thesis> existingTheses = thesisDAO.findBySubject(subject);
	    if (!existingTheses.isEmpty()) {
	        model.addAttribute("errorMessage", "This subject has already been assigned to a student.");
	        return "admin/error"; 
	    }

	   
	    List<Thesis> existingThesesForStudent = thesisDAO.findByStudent(student);
	    if (!existingThesesForStudent.isEmpty()) {
	        model.addAttribute("errorMessage", "This student has already been assigned a Thesis Diploma Subject");
	        return "admin/error"; 
	    }

	   
	    Thesis thesis = new Thesis();
	    thesis.setStudent(student);
	    thesis.setSubject(subject);
	    thesis.setProfessor(professor);
	    thesis.setReportGrade(0.0);
	    thesis.setImplementationGrade(0.0);
	    thesis.setPresentationGrade(0.0);
	    thesis.setTotalgrade(0);
	   
	    thesisDAO.save(thesis);

	    
	    applicationDAO.deleteById(applicationId);

	    model.addAttribute("subjectId", subjectId);

	    return "redirect:/admin/subject_applications/" + subjectId;
	}

	// Assigns a subject to the student with the best average grade.
	@PostMapping("/admin/assign_subject/random")
	public ResponseEntity<String> assignSubjectToRandomStudent(@RequestParam("subjectId") Long subjectId) {
	    
	    List<Application> applications = applicationDAO.findBySubjectId(subjectId);

	   
	    if (applications.isEmpty()) {
	        return ResponseEntity.badRequest().body("No applications found for the subject.");
	    }

	    
	    List<Thesis> existingTheses = thesisDAO.findBySubject(applications.get(0).getSubject());
	    if (!existingTheses.isEmpty()) {
	        return ResponseEntity.badRequest().body("This subject has already been assigned to a student.");
	    }

	    
	    Random random = new Random();
	    Application randomApplication = applications.get(random.nextInt(applications.size()));
	    Student randomStudent = randomApplication.getStudent();

	    
	    Thesis thesis = new Thesis();
	    thesis.setStudent(randomStudent);
	    thesis.setSubject(randomApplication.getSubject());
	    thesis.setProfessor(randomApplication.getProfessor());
	    thesis.setReportGrade(0.0); 
	    thesis.setImplementationGrade(0.0);
	    thesis.setPresentationGrade(0.0);
	    thesis.setTotalgrade(0);

	    
	    thesisDAO.save(thesis);

	    
	    applicationDAO.deleteById(randomApplication.getId());

	    return ResponseEntity.ok("Thesis assigned to a random student.");
	}

	// Assigns a subject to the student with the fewest courses.
	@PostMapping("/admin/assign_subject/best_avg_grade")
	@Transactional
	public String assignSubjectWithBestAvgGrade(@RequestParam("subjectId") Long subjectId, Model model) {
	    Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
	    if (!subjectOptional.isPresent()) {
	       return "error";
	    }
	    
	    Subject subject = subjectOptional.get();
	    
	     List<Thesis> existingTheses = thesisDAO.findBySubject(subject);
	    if (!existingTheses.isEmpty()) {
	        model.addAttribute("errorMessage", "This subject has already been assigned to a student.");
	        return "admin/error";
	    }
	    List<Application> applications = applicationDAO.findBySubjectId(subjectId);
	    
	    if (applications.isEmpty()) {
	        model.addAttribute("errorMessage", "No applications found for the subject.");
	        return "admin/error";
	    } 
	    BestAvgGradeStrategy strategy = new BestAvgGradeStrategy(StrategyType.BEST_GRADE, 1, 2);
	    
	  Student bestApplicant = strategy.findBestApplicant(applications);
	    
	    Thesis thesis = new Thesis();
	    thesis.setStudent(bestApplicant);
	    thesis.setSubject(subject);
	    thesis.setProfessor(subject.getProfessor());
	    thesis.setReportGrade(0.0); 
	    thesis.setImplementationGrade(0.0);
	    thesis.setPresentationGrade(0.0);
	    thesis.setTotalgrade(0);
	    
	   thesisDAO.save(thesis);
	    
	    applicationDAO.deleteBySubjectIdAndStudentId(subjectId, bestApplicant.getId());
	    
	    model.addAttribute("subjectId", subjectId);
	    
	    return "redirect:/admin/subject_applications/" + subjectId;
	}
	
	// Assigns a subject to the student with the fewest courses.
	@PostMapping("/admin/assign_subject/fewest_courses")
	@Transactional
	public String assignSubjectWithFewestCourses(@RequestParam("subjectId") Long subjectId, Model model) {
	    Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
	    if (!subjectOptional.isPresent()) {

	        return "error";
	    }
	    
	    Subject subject = subjectOptional.get();

	    List<Thesis> existingTheses = thesisDAO.findBySubject(subject);
	    if (!existingTheses.isEmpty()) {
	        model.addAttribute("errorMessage", "This subject has already been assigned to a student.");
	        return "admin/error"; 
	    }List<Application> applications = applicationDAO.findBySubjectId(subjectId);
	    if (applications.isEmpty()) {
	        model.addAttribute("errorMessage", "No applications found for the subject.");
	        return "admin/error"; 
	    }

	    FewestCoursesStrategy strategy = new FewestCoursesStrategy(StrategyType.FEWEST_COURSES, 1, 2);
	    Student bestApplicant = strategy.findBestApplicant(applications);
	    Thesis thesis = new Thesis();
	    thesis.setStudent(bestApplicant);
	    thesis.setSubject(subject);
	    thesis.setProfessor(subject.getProfessor());
	    thesis.setReportGrade(0.0); 
	    thesis.setImplementationGrade(0.0);
	    thesis.setPresentationGrade(0.0);
	    thesis.setTotalgrade(0);
	     applicationDAO.deleteBySubjectIdAndStudentId(subjectId, bestApplicant.getId());
	    
	    model.addAttribute("subjectId", subjectId);
	    
	    return "redirect:/admin/subject_applications/" + subjectId;
	}
	
	// Assigns a subject to the student with the fewest courses and a minimum grade threshold.
	@PostMapping("/admin/assign_subject/fewest_courses_and_Grade")
	@Transactional
	public String assignSubjectWithGradeAndFewestCourses(@RequestParam("subjectId") Long subjectId,
            @RequestParam("thresholdCourse") int thresholdCourse,@RequestParam("thresholdGrade") double thresholdGrade, Model model) {
		
	    Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
	    if (!subjectOptional.isPresent()) {

	        return "error";
	    }
	    
	    Subject subject = subjectOptional.get();

	    List<Thesis> existingTheses = thesisDAO.findBySubject(subject);
	    if (!existingTheses.isEmpty()) {
	        model.addAttribute("errorMessage", "This subject has already been assigned to a student.");
	        return "admin/error"; 
	    }List<Application> applications = applicationDAO.findBySubjectId(subjectId);
	    if (applications.isEmpty()) {
	        model.addAttribute("errorMessage", "No applications found for the subject.");
	        return "admin/error"; 
	    }

	    GradeAndFewestCoursesStrategy strategy = new GradeAndFewestCoursesStrategy(StrategyType.GRADE_AND_FEWEST, thresholdCourse, thresholdGrade);
	    Student bestApplicant = strategy.findBestApplicant(applications);
	    Thesis thesis = new Thesis();
	    thesis.setStudent(bestApplicant);
	    thesis.setSubject(subject);
	    thesis.setProfessor(subject.getProfessor());
	    thesis.setReportGrade(0.0);
	    thesis.setImplementationGrade(0.0);
	    thesis.setPresentationGrade(0.0);
	    thesis.setTotalgrade(0);
	     applicationDAO.deleteBySubjectIdAndStudentId(subjectId, bestApplicant.getId());
	    
	    model.addAttribute("subjectId", subjectId);
	    
	    return "redirect:/admin/subject_applications/" + subjectId;
	}
	
	// Retrieves the assigned thesis projects for the professor.
	@RequestMapping("/admin/thesis_project")
	public String getAssignedThesisProjects(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    Optional<Professor> professorOptional = professorDAO.findByUsername(username);

	    if (professorOptional.isPresent()) {
	        Professor professor = professorOptional.get();

	        List<Thesis> assignedThesisProjects = thesisDAO.findByProfessor(professor);

	        model.addAttribute("assignedThesisProjects", assignedThesisProjects);
	        
	    }

	    return "admin/thesis_project";
	}
	
	// Shows the set grade page for a specific thesis
	@RequestMapping("/admin/set_grade/{thesisId}")
	public String showSetGradePage(@PathVariable("thesisId") Long thesisId, Model model) {
	    Optional<Thesis> thesis = thesisDAO.findById(thesisId);
	    if (!thesis.isPresent()) {
	        return "error";
	    }
	    
	    model.addAttribute("thesisId", thesisId);
	    return "/admin/set_grade";
	}
	
	// Sets the implementation grade for a thesis.
	@PostMapping("/admin/set_grade/set_implementation_grade")
	@Transactional
	public String setImplementationGrade(@RequestParam("thesisId") Long thesisId,
	        @RequestParam("implementationgrade") double implementationgrade, Model model) {

	    Optional<Thesis> thesis = thesisDAO.findById(thesisId);
	    if (!thesis.isPresent()) {
	        return "error";
	    }
	    Thesis thesisToGrade = thesis.get();
	    thesisToGrade.setImplementationGrade(implementationgrade);
	    thesisToGrade.setTotalgrade(0);
	    thesisDAO.save(thesisToGrade);
	    model.addAttribute("thesisId", thesisId);
	    model.addAttribute("successMessage", "Implementation grade has been set successfully.");

	    return "/admin/set_grade";
	}

	// Sets the report grade for a thesis.
	@PostMapping("/admin/set_grade/set_report_grade")
	@Transactional
	public String setReportGrade(@RequestParam("thesisId") Long thesisId,
			@RequestParam("reportgrade") double reportgrade,Model model) {
		
		Optional<Thesis>  thesis = thesisDAO.findById(thesisId);
		if(!thesis.isPresent()) {
			return "error";
		}
		Thesis thesisToGrade = thesis.get();
		thesisToGrade.setReportGrade(reportgrade);
		thesisToGrade.setTotalgrade(0);
		thesisDAO.save(thesisToGrade);
		model.addAttribute("thesisId",thesisId);
	    model.addAttribute("successMessage", "Report grade has been set successfully.");

		return "/admin/set_grade";
	}
	
	// Sets the presentation grade for a thesis.
	@PostMapping("/admin/set_grade/set_presentation_grade")
	@Transactional
	public String setPresentationGrade(@RequestParam("thesisId") Long thesisId,
			@RequestParam("presentationgrade") double presentationgrade,Model model) {
		
		Optional<Thesis>  thesis = thesisDAO.findById(thesisId);
		if(!thesis.isPresent()) {
			return "error";
		}
		Thesis thesisToGrade = thesis.get();
		thesisToGrade.setPresentationGrade(presentationgrade);
		thesisToGrade.setTotalgrade(0);
		thesisDAO.save(thesisToGrade);
		model.addAttribute("thesisId",thesisId);	
	    model.addAttribute("successMessage", "Presentation grade has been set successfully.");

		return "/admin/set_grade";
	}
	
	// Sets the total grade for a thesis.
	@RequestMapping("/admin/calculate_total_grade")
	public String calculateTotalGrade(@RequestParam("thesisId") Long thesisId, Model model) {

	    Optional<Thesis> thesis = thesisDAO.findById(thesisId);
	    if (!thesis.isPresent()) {
	        return "error";
	    }
	    Thesis thesisToGrade = thesis.get();
	    
	    double implementationGrade = thesisToGrade.getImplementationGrade();
	    double reportGrade = thesisToGrade.getReportGrade();
	    double presentationGrade = thesisToGrade.getPresentationGrade();
	    double overallGrade = 0.7 * implementationGrade + 0.15 * reportGrade + 0.15 * presentationGrade;
	    thesisToGrade.setTotalgrade(overallGrade);
	    thesisDAO.save(thesisToGrade);
	    model.addAttribute("thesisId", thesisId);
	    model.addAttribute("overallGrade", overallGrade);
	    model.addAttribute("implementationGrade",implementationGrade);
	    model.addAttribute("reportGrade",reportGrade);
	    model.addAttribute("presentationGrade",presentationGrade);

	    return "/admin/set_grade";
	}

}

      

