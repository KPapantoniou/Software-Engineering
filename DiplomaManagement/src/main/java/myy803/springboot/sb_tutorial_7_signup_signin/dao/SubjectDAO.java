package myy803.springboot.sb_tutorial_7_signup_signin.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Subject;

@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	@Transactional
	void deleteById(Long id);
	Optional<Subject> findById(Long id);
	Optional<Subject> findByName(String name);
	List<Subject> findByProfessor(Professor professor);
}
