package myy803.springboot.Diploma_Managment.model;

public class FewestStrategtyAlgorithm extends TemplateStrategyAlgorithm{

	public FewestStrategtyAlgorithm() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int compareAplications(Application first, Application second) {
		if (first >second){
			return 1;
		}
		return 0;		
	} 

}
