package component.entity.e2e;

import com.sybase.automation.framework.common.JarLoader;

public class DevicePK {
	private static String project;
	
	private static String pkClassName() {
		return project+".PersonalizationParameters";
	}
	
	public static Object getPK(String pkName){
		String capInitial = pkName.substring(0,1).toUpperCase()+pkName.substring(1,pkName.length()); 
		String operation = "get"+capInitial;
		return JarLoader.invokeInstanceMethod(JarLoader.createInstance(pkClassName()), pkClassName(), operation, null, null);
	}
	
	private static Object setStringPK(String pkName, String value){
		String capInitial = pkName.substring(0,1).toUpperCase()+pkName.substring(1,pkName.length()); 
		String operation = "set"+capInitial;
		Class[] clazz = JarLoader.getParameters(pkClassName(), operation);
		Object para = ObjectHelper.getObject(clazz[0], value);
		return JarLoader.invokeInstanceMethod(JarLoader.createInstance(pkClassName()), pkClassName(), operation, clazz, new Object[]{para});
	}
	
	public static Object setPK(String pkName, Object value){
		Object para;
		if(value instanceof DeviceMbo){
			para = ((DeviceMbo)value).getMbo();
			String capInitial = pkName.substring(0,1).toUpperCase()+pkName.substring(1,pkName.length()); 
			String operation = "set"+capInitial;
			return JarLoader.invokeInstanceMethod2(JarLoader.createInstance(pkClassName()), pkClassName(), operation, 1, new Object[]{para});
		}
		else if(value instanceof String){
			return setStringPK(pkName, (String)value);
		}else{
			throw new RuntimeException("Unknown type: "+value.getClass().getName());
		}
	}

	public static void setProject(String p) {
		project = p;
	}
}
