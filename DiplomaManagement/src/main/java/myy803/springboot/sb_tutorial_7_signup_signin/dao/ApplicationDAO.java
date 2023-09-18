package myy803.springboot.sb_tutorial_7_signup_signin.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Long> {

    Optional<Application> findById(Long id);

    List<Application> findByStudent(Student student);

    List<Application> findBySubject(Subject subject);

	List<Application> findByIdAndSubject(String professorId, Integer subjectId);

	List<Application> findBySubjectId(Long subjectId);

	void deleteBySubjectIdAndStudentId(Long subjectId, Integer id);
     
}
