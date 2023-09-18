	package myy803.springboot.sb_tutorial_7_signup_signin.dao;
	
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	
	import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
	import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
	import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;
	import myy803.springboot.sb_tutorial_7_signup_signin.model.Thesis;
	
	@Repository
	public interface ThesisDAO extends JpaRepository<Thesis, Long> {
		Optional<Thesis> findById(Long id);
		List<Thesis> findByProfessor(Professor professor);
	    List<Thesis> findByStudent(Student student);
	    List<Thesis> findBySubject(Subject subject);
	    List<Thesis> findAll(); // Change the return type to Optional<List<Thesis>>
		Optional<Thesis> findByStudentAndSubject(Student student, Subject subject);
	}
