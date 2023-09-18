package myy803.springboot.sb_tutorial_7_signup_signin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import myy803.springboot.sb_tutorial_7_signup_signin.model.Professor;
import myy803.springboot.sb_tutorial_7_signup_signin.model.Student;
import myy803.springboot.sb_tutorial_7_signup_signin.model.User;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
    Optional<Student> findById(String id);
    Optional<Student> findByUsername(String username);
}

