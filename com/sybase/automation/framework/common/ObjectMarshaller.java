package com.sybase.automation.framework.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import component.entity.model.WSResponse;

public class ObjectMarshaller {

	public static String serialize(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String strRep="";
		Method[] methods = object.getClass().getDeclaredMethods();
		for(Method method:methods){
			if(method.getName().startsWith("get")){
				String attributeName = method.getName().replace("get", "");
				attributeName = attributeName.substring(0,1).toLowerCase()+attributeName.substring(1, attributeName.length());
				if(method.invoke(object,null)!=null){
					strRep = strRep+"["+attributeName+"]"+method.invoke(object, null).toString();
				}
			}
		}
		return strRep;
	}

	public static Object deserialize(String str, Class klass) {
		try{
			Object obj = klass.newInstance();
			for(String attribute:getAllAttributes(str)){
				
				Field field = klass.getDeclaredField(attribute);
				field.setAccessible(true);
				field.set(obj, getAttributeValue(str, attribute));
			}
			return obj;

		}catch(Exception e){
			throw new RuntimeException("Unable to deserialize string "+str);
		}
	}
	
	public static List<String> getAllAttributes(String str){
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("\\[(\\w+)\\]");
		Matcher m = p.matcher(str);
		while(m.find()){
			list.add(m.group(1));
		}
		return list;
	}
	
	public static String getAttributeValue(String str, String attribute){
		Pattern p = Pattern.compile("\\[(\\w+)\\]([^\\[]+)");
		Matcher m = p.matcher(str);
		while(m.find()){
			if(m.group(1).equals(attribute)){
				return m.group(2);
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, InstantiationException, NoSuchFieldException{
		System.out.println(getAttributeValue("[a]va[c]1[b]bbbb", "c"));
		System.out.println(getAllAttributes("[a]va[c]1[b]bbbb").size());
		WSResponse response = new WSResponse().rootElement("b").xsdUri("a");
		System.out.println(ObjectMarshaller.serialize(response));
		WSResponse res = (WSResponse)ObjectMarshaller.deserialize(ObjectMarshaller.serialize(response), WSResponse.class);
		System.out.println(res.getRootElement());
	}

	public static boolean isValid(String string) {
		Pattern pattern = Pattern.compile("(\\[\\w+\\]\\w+)+");
		Matcher m = pattern.matcher(string);
		if(m.matches()){
			return true;
		}else{
			return false;
		}
	}

}
