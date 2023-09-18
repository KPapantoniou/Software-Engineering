package myy803.springboot.sb_tutorial_7_signup_signin.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {  

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "objectives")
    private String objectives;
    
    @ManyToOne
    @JoinColumn(name = "professorid")
    private Professor professor;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Thesis thesis;

    public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Application> getApplications() {
        return applications;
    }

    public Thesis getThesis() {
        return thesis;
    }
    
    public String getTitle() {
        return name;
    }
    public void setTitle(String title) {
        this.name = title;
    }
    public String getObjectives() {
        return objectives;
    }
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public double getGrade() {
        return 0;
    }
    
    

}