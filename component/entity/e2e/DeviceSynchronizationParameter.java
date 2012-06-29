package component.entity.e2e;

import com.sybase.automation.framework.common.JarLoader;

public class DeviceSynchronizationParameter {
	private Object rawParameter;
	private String className;
	private static String project;

	public DeviceSynchronizationParameter(Object syncParameter, String klass) {
		this.rawParameter = syncParameter;
		this.className = klass;
	}
	
	public static void setProject(String p){
		project = p;
	}
	
	public static String getProject(){
		if(project!=null){
			return project;
		}else{
			throw new RuntimeException("Project for DeviceSynchronizationParameter is not set");
		}
	}
	
	public void setParameter(String pName, String value){
		String capInitial = pName.substring(0, 1).toUpperCase()+ pName.substring(1, pName.length());
		String operation = "set" + capInitial;
		Class[] type = JarLoader.getParameters(getProject()+"."+this.className, operation);
		Object[] parameter = new Object[] { ObjectHelper.getObject(type[0], value) };
		JarLoader.invokeInstanceMethod(this.rawParameter, getProject()+"."+this.className, operation, type, parameter);
	}
	
	public Object getParameter(String pName){
		String capInitial = pName.substring(0, 1).toUpperCase()+ pName.substring(1, pName.length());
		String operation = "get" + capInitial;
		return JarLoader.invokeInstanceMethod(this.rawParameter, getProject()+"."+this.className, operation, null, null);
	}

}
