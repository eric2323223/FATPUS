package com.sybase.automation.framework.tool;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

/**
 * Description : Functional Test Script
 * 
 * @author xjf
 */
public class NativeInvoker  {

	public static String getSignature(TestObject to, String method,
			String paramSig) {
		MethodInfo[] infos = to.getMethods();
		for (int i = 0; i < infos.length; i++) {
			if (infos[i].getName().equalsIgnoreCase(method)
					&& compareParamSig(infos[i], paramSig)) {
				// System.out.println(infos[i].getSignature());
				return infos[i].getSignature();
			}
		}

		return null;
	}

	private static boolean compareParamSig(MethodInfo methodInfo,
			String paramSig) {
		return methodInfo.getSignature().indexOf(paramSig) != -1;
	}

	public static void printMethods(TestObject to) {
		MethodInfo[] m = to.getMethods();
		for (int i = 0; i < m.length; ++i) {
			if (!m[i].getDeclaringClass().equals("java.lang.Object"))
				System.out.println(m[i].getName() + "() *** signature < "
						+ m[i].getSignature() + " >");
			// System.out.println(m[i].getDeclaringClass() + "."
			// + m[i].getName() + "() *** signature < "
			// + m[i].getSignature() + " >");
		}
	}

	public static TestObject invoke(TestObject to, String method, String param) {
		return doInvoke(to, method, param, "(Ljava/lang/String;)");
	}

	public static TestObject invoke(TestObject to, String method, int param) {
		return doInvoke(to, method, Integer.valueOf(param), "(I)");
	}

	public static TestObject invoke(TestObject to, String method, boolean param) {
		return doInvoke(to, method, Boolean.valueOf(param), "(Z)");
	}

	private static TestObject doInvoke(TestObject to, String method,
			Object param, String paramSig) {
		String sig = getSignature(to, method, paramSig);
		if (sig != null) {
			Object[] args = { param };
			return (TestObject) to.invoke(method, sig, args);
		} else
			return null;
	}
}