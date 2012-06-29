package com.sybase.automation.framework.widget.util;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class NativeInvoker2 
{
	/**
	 * Script Name   : <b>NativeInvoker</b>
	 * Generated     : <b>Nov 18, 2008 2:05:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/11/18
	 * @author Administrator
	 */
	public void testMain(String[] args){
		
	}
	
	
	
	public static String getSignature(TestObject to, String method) {
		MethodInfo[] infos = to.getMethods();
		for (int i = 0; i < infos.length; i++) {
			if (infos[i].getName().equalsIgnoreCase(method)) {
//			    	System.out.println(infos[i].getSignature());
				return infos[i].getSignature();
			}
		}
		
		return null;
	}
	
	public static java.util.List<String> getSignatures(TestObject to, String method) {
		java.util.List<String> signatures = new ArrayList<String>();
		MethodInfo[] infos = to.getMethods();
		for (int i = 0; i < infos.length; i++) {
			if (infos[i].getName().equals(method)) {
				signatures.add(infos[i].getSignature());
//				System.out.println(infos[i]);
			}
		}
		return signatures;
	}
	
	public static void printMethods(TestObject to) {
		MethodInfo[] m = to.getMethods();
		for (int i = 0; i < m.length; ++i) {
			if (!m[i].getDeclaringClass().equals("java.lang.Object"))
				System.out.println(m[i].getDeclaringClass() + "."
						+ m[i].getName() + "() *** signature < "
						+ m[i].getSignature() + " >");
		}
	}

	public static Object invoke(TestObject to, String method, String param) {
		String sig = getSignature(to, method);
		if (sig != null) {
			Object[] args = new Object[1];
			args[0] = param;
			return to.invoke(method, sig, args);
		} else
			return null;
	}
	
	public static Object invoke(TestObject to, String method, int param) {
//		String sig = getSignature(to, method);
//		if (sig != null) {
//			Object[] args = new Object[1];
//			args[0] = new Integer(param);
//			return to.invoke(method, sig, args);
//		} else
//			return null;
		return invokeMultipleMethodsWithSameName(to, method, param);
	}
	
	public static Object invokeMultipleMethodsWithSameName(TestObject to, String method, int param) {
		java.util.List<String> sigs = getSignatures(to, method);
		String signature = sigs.remove(0);
		while(sigs.size()>=0){
			try{
				Object[] args = new Object[1];
				args[0] = new Integer(param);
				return to.invoke(method, signature, args);
			}catch(Exception e){
				signature = sigs.remove(0);
			}
		}
		return null;
	}
	
	public static Object invoke(TestObject to, String method, boolean param) {
		String sig = getSignature(to, method);
		if (sig != null) {
			Object[] args = new Object[1];
			args[0] = new Boolean(param);
			return to.invoke(method, sig, args);
		} else
			return null;
	}

	

}

