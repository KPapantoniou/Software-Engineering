package myy803.springboot.sb_tutorial_7_signup_signin.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Application;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

    Optional<Professor> findById(String id);
    Optional<Professor> findByFullname(String fullname);
    Optional<Professor> findByUsername(String username);
    List<Professor> findBySpeciality(String speciality);
	Optional<Professor> findById(Long professorId);
    

}	
