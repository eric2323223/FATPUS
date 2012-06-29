package com.sybase.automation.framework.tool;

import java.lang.annotation.Annotation;
import java.util.List;

import component.entity.model.E2ETestScript;

public class AnnotationUtil {
	
	public static boolean containAnnotation(Class klass, String annotationString){
		Annotation[] annotations = klass.getDeclaredAnnotations();
		for(Annotation annotation: annotations){
			if(annotation.toString().contains(annotationString)){
				return true;
			}
		}
		return false;
	}

//	public static Object getProperty(String annotation, String property) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
