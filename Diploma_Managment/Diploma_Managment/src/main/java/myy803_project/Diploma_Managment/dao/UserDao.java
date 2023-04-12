package myy803_project.Diploma_Managment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.Diploma_Managment.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
