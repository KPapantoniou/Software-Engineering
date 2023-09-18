package myy803.springboot.sb_tutorial_7_signup_signin.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @OneToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "student")
    private Student student;
      

    @Column(name = "presentationgrade")
    private double presentationgrade;
    
    @Column(name = "reportgrade")
    private double  reportgrade;
    
    @Column(name = "implementationgrade")
    private double  implementationgrade;
    
    @Column(name="totalgrade")
    private double totalgrade;
    
    public Thesis() {
	
	}

    
    public Thesis(Long id, Professor professor, Subject subject, Student student,double totalgrade, double implementationgrade, double reportgrade, double presentationgrade) {
		this.id = id;
		this.professor = professor;
		this.subject = subject;
		this.student = student;

		this.implementationgrade = implementationgrade;
		this.reportgrade = reportgrade;
		this.presentationgrade = presentationgrade;
		this.totalgrade=totalgrade;

	}
    
    public double getTotalgrade() {
		return totalgrade;
	}

	public void setTotalgrade(double totalgrade) {
		this.totalgrade = totalgrade;
	}

	public double getImplementationGrade() {
		return implementationgrade;
	}

	public void setImplementationGrade(double implementationgrade) {
		this.implementationgrade = implementationgrade;
	}

	public double getReportGrade() {
		return reportgrade;
	}

	public void setReportGrade(double reportgrade) {
		this.reportgrade = reportgrade;
	}

	public double getPresentationGrade() {
		return presentationgrade;
	}

	public void setPresentationGrade(double presentationgrade) {
		this.presentationgrade = presentationgrade;
	}

	
	public Long getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }
   

	public Subject getSubject() {
        return subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}


