package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="professor")   
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String fullname;
	@Column(name="speciality")  
	private String speciality;
	@Column(name="username")  
	private String username;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "professorid")
	private List<Subject> subjects;
	
	  
	  
	public String getUsername() {
		  return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    	  
	public Professor() {
    }

	public Professor(String fullname, String speciality) {
	    this.speciality = speciality;
	    this.fullname = fullname;
	}
	
	public int getId() {
		return id;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getFullname() {
		return fullname;
	}
	
	
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	
	public String getSpeciality() {
		return speciality;
	}
	
	
	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
	
	public List<Subject> getSubjects() {
		return subjects;
	}
	
	
	
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
