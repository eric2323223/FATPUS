package component.entity.e2e;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.sybase.automation.framework.common.JarLoader;
import com.sybase.persistence.Query;

public class DeviceMbo {

	private static String packageName;
	private String className;
	private Object mbo;


	public DeviceMbo(String className, Object obj) {
		this.className = className;
		this.mbo = obj;
	}

	public static void setProject(String p) {
		packageName = p;
	}	
	
	public Object getMbo(){
		return this.mbo;
	}
	
	public static List<DeviceMbo> findAll(String className) {
		List rawObjects = (List) JarLoader.invokeClassMethod(
				completeClassName(className), "findAll", null, null);
		Iterator it = rawObjects.iterator();
		List<DeviceMbo> result = new ArrayList<DeviceMbo>();
		while (it.hasNext()) {
			DeviceMbo mbo = new DeviceMbo(className, it.next());
			result.add(mbo);
		}
		return result;
	}
	
	public static List<DeviceMbo> findWithQuery(String className, Query query){
		List rawObjects = (List) JarLoader.invokeClassMethod(
				completeClassName(className), "findWithQuery", null, null);
		Iterator it = rawObjects.iterator();
		List<DeviceMbo> result = new ArrayList<DeviceMbo>();
		while (it.hasNext()) {
			DeviceMbo mbo = new DeviceMbo(className, it.next());
			result.add(mbo);
		}
		return result;
	}

	private static String getPackageName() {
		if (packageName == null) {
			throw new RuntimeException("Package name is not set!");
		}
		return packageName;
	}

	public static DeviceMbo createInstance(String className) {
		Object rawMbo = JarLoader.createInstance(completeClassName(className));
		return new DeviceMbo(className, rawMbo);
	}

	public static void synchronize(String className) {
		JarLoader.invokeClassMethod(completeClassName(className),
				"synchronize", null, null);
	}

	public static DeviceMbo findByPrimaryKey(String className, String pk) {
		Class[] clazz = JarLoader.getParameters(completeClassName(className), "findByPrimaryKey");
		Object para = ObjectHelper.getObject(clazz[0], pk);
		Object mbo = JarLoader.invokeClassMethod2(completeClassName(className),
				"findByPrimaryKey", 1, new Object[] { para });
		return new DeviceMbo(className, mbo);
	}
	
	private DeviceSynchronizationParameter getSynchronizationParameters(){
		String parameterClassName = this.className+"SynchronizationParameters";
		return new DeviceSynchronizationParameter(JarLoader.invokeClassMethod(completeClassName(className),
				"getSynchronizationParameters", null, null), parameterClassName);
	}
	
	private static String completeClassName(String className) {
		return getPackageName() + "." + className;
	}

	public DeviceMbo getAssociatedMbo(String relationshipName, String mboName) {
		String capInitial = relationshipName.substring(0, 1).toUpperCase() + relationshipName.substring(1, relationshipName.length());
		String operationName = "get" + capInitial;
		return new DeviceMbo(mboName, JarLoader.invokeInstanceMethod(mbo,completeClassName(className), operationName, null, null));
	}
	
	public List<DeviceMbo> getAssociatedMbos(String relationshipName,
			String mboName) {
		String capInitial = relationshipName.substring(0, 1).toUpperCase()
				+ relationshipName.substring(1, relationshipName.length());
		String operationName = "get" + capInitial;
		List rawObjects = (List) JarLoader.invokeInstanceMethod(mbo,
				completeClassName(className), operationName, null, null);
		Iterator it = rawObjects.iterator();
		List<DeviceMbo> result = new ArrayList<DeviceMbo>();
		while (it.hasNext()) {
			DeviceMbo mbo = new DeviceMbo(mboName, it.next());
			result.add(mbo);
		}
		return result;
	}

	public void invokeOperation(String operation) {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), operation, null, null);
	}

	public void create() {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), "create", null, null);
	}

	public void update() {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), "update", null, null);
	}

	public void delete() {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), "delete", null, null);

	}

	public void save() {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), "save", null, null);
	}

	public void userDefinedOperation(String opName, Object[] parameters) {
		Object[] args = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] instanceof DeviceMbo) {
				args[i] = ((DeviceMbo) parameters[i]).mbo;
			} 
			else if(parameters[i] instanceof List){
				args[i] = parameters[i];
			}
			else {
				args[i] = ObjectHelper.getObject(parameters[i].getClass(), parameters[i]
						.toString());
			}
		}
		JarLoader.invokeInstanceMethod2(this.mbo,completeClassName(this.className), opName, parameters.length,args);
	}
	
	public void userDefinedOperation(String opName) {
		JarLoader.invokeInstanceMethod(this.mbo,completeClassName(this.className), opName, null ,null);
	}

	public void setProperty(String property, String value) {
		String operation = property.substring(0, 1).toUpperCase()
				+ property.substring(1, property.length());
		operation = "set" + operation;
		Class[] type = JarLoader.getParameters(
				completeClassName(this.className), operation);
		Object[] parameter = new Object[] { ObjectHelper.getObject(type[0], value) };
		JarLoader.invokeInstanceMethod2(this.mbo,
				completeClassName(this.className), operation, 1, parameter);
	}

	public void setProperty(String property, Object value) {
			Object arg = new Object();
			if (value instanceof DeviceMbo) {
				arg = ((DeviceMbo) value).mbo;
			} 
			else if(value instanceof List){
				arg = value;
			}
			else {
				arg = ObjectHelper.getObject(value.getClass(), value.toString());
			}
		String operation = property.substring(0, 1).toUpperCase()
				+ property.substring(1, property.length());
		operation = "set" + operation;
//		Class[] clazz = new Class[] { byte[].class };
		JarLoader.invokeInstanceMethod2(this.mbo,completeClassName(this.className), operation, 1, new Object[]{arg});
	}

	public void submitPending() {
		JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), "submitPending", null, null);
	}

	public String getProperty(String column) {
		String capInitial = column.substring(0, 1).toUpperCase()
				+ column.substring(1, column.length());
		String operation = "get" + capInitial;
		Object result = JarLoader.invokeInstanceMethod(this.mbo,
				completeClassName(this.className), operation, null, null);
		if(result!=null){
			return result.toString();
		}else{
			return "<NULL>";
		}
//		return JarLoader.invokeInstanceMethod(this.mbo,
//				completeClassName(this.className), operation, null, null)
//				.toString();
	}
	
	public void setSynchronizationParameter(String parameterName, String value){
		getSynchronizationParameters().setParameter(parameterName, value);
	}
	
	public Object getSynchronizationParameter(String parameterName){
		return getSynchronizationParameters().getParameter(parameterName);
	}

	public static List<DeviceMbo> findByObjectQuery(String mboClass, String objectQueryName, String[] parameters) {
		Class[] types = JarLoader.getParameters2(completeClassName(mboClass), objectQueryName, parameters.length);
		Object[] values = new Object[types.length];
		for(int i = 0;i<types.length;i++){
			values[i] = ObjectHelper.getObject(types[i], parameters[i]);
		}
		Object rawResult = JarLoader.invokeClassMethod(completeClassName(mboClass), objectQueryName, types, values);
		if(rawResult.getClass().getName().equals(completeClassName(mboClass))){
			List<DeviceMbo> result = new ArrayList<DeviceMbo>();
			result.add(new DeviceMbo(mboClass, rawResult));
			return result;
		}else{
			Iterator it = ((List)rawResult).iterator();
			List<DeviceMbo> result = new ArrayList<DeviceMbo>();
			while (it.hasNext()) {
				DeviceMbo mbo = new DeviceMbo(mboClass, it.next());
				result.add(mbo);
			}
			return result;
		}
	}
	
}
