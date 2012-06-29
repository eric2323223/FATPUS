package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class GlobalAction {
	private String methodName;
	private String code;
	private boolean isNewMethod = false;
	private String[] parameters;

	public String getMethodName() {
		return methodName;
	}
	
	public boolean isNewMethod(){
		return this.isNewMethod;
	}
	
	public String[] getParameters(){
		return this.parameters;
	}

	public String getCode() {
		return code;
	}

	public GlobalAction(String methodName, String code) {
		this.isNewMethod = false;
		this.methodName = methodName;
		this.code = code;
	}
	
	public GlobalAction(String code){
		this.code = code;
	}
	
	public GlobalAction(String methodName, String[] parameters, String code, boolean isNewMethod){
		this.isNewMethod = isNewMethod;
		this.parameters = parameters;
		this.code = code;
		this.methodName = methodName;
	}

}
