package myy803.springboot.Diploma_Managment.model;

public class Professor {
	private Thesis thesis;
    private Subject subject;
    
	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
