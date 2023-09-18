package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "student")
    private Thesis thesis;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "courses")
    private Integer courses;
   
    @Column(name = "username")
    private String username; 


    public Student() {
    
    }

    public Student(int id, Thesis thesis, String studentName, Integer yearOfStudies, String title,
                   Double averageGrade, Integer remainingCourses, String appliedSubjectIds) {
        this.id = id;
        this.thesis = thesis;
        this.name = studentName;
        this.year = yearOfStudies;
        this.grade = averageGrade;
        this.courses = remainingCourses;
    }

   

    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Integer getCourses() {
		return courses;
	}

	public void setCourses(Integer courses) {
		this.courses = courses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}