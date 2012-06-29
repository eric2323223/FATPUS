package component.entity.WFCustomizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import component.entity.WorkFlowEditor;
import component.entity.model.Email;

public class CallBackMethod {
	String name;
	Class receiver;
	List parameters = new ArrayList();
	
	public CallBackMethod methodName(String name){
		this.name = name;
		return this;
	}
	
	public CallBackMethod receiver(Class receiver){
		this.receiver = receiver;
		return this;
	}
	
	public CallBackMethod parameter(Object parameter){
		this.parameters.add(parameter);
		return this;
	}
	
	public void call(){
		try {
			Method method = this.receiver.getDeclaredMethod(this.name, getParameterTypes());
			method.invoke(null, getParameters());
//			method.invoke(this.receiver, parameters);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Class[] getParameterTypes(){
		if(parameters.size()==0){
			return null;
		}
		Class[] types = new Class[parameters.size()];
		for(int i =0;i<types.length;i++){
			types[i] = parameters.get(i).getClass();
		}
		return types;
	}
	
	private Object[] getParameters(){
		if(parameters.size()==0){
			return null;
		}
		Object[] args = new Object[parameters.size()];
		for(int i =0;i<args.length;i++){
			args[i] = parameters.get(i);
		}
		return args;
	}

	public static void main(String[] args){
		new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().from("DT").selectTo("DT")).call();
	}
}
