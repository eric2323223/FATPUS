package com.sybase.automation.framework.tool;


public class MethodUtil {
	public static String getCallingMethodInfo() {
		final Throwable fakeThrowable = new Throwable();
		final StackTraceElement[] stackTrace = fakeThrowable.getStackTrace();
		if (stackTrace != null && stackTrace.length >= 2) {

			StackTraceElement s = stackTrace[2];
			if (s != null) {
				return s.getClassName() + "[" + s.getMethodName() + "]";
			}
		}
		return null;
	}
	
	public static Class getCallingClass(){
		final Throwable fakeThrowable = new Throwable();
		final StackTraceElement[] stackTrace = fakeThrowable.getStackTrace();
		if (stackTrace != null && stackTrace.length >= 2) {
			
			StackTraceElement s = stackTrace[2];
			if (s != null) {
				try {
					return Class.forName(s.getClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
