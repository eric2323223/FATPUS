package component.entity.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.rational.test.ft.script.RationalTestScript;
import component.entity.wizard.IWizard;


public class WizardRunner extends RationalTestScript{
	
	public void run(IWizardEntity mbo, IWizard wizard) {
		try {
			wizard.start(mbo.sp());
			int currentPage = 0;
			int max = maxPage(mbo,wizard);
			while (currentPage <= max && wizard.canContinue()) {
				System.out.println("=======page " + currentPage + "=========");
				for (String method : getGetMethods(mbo.getClass())) {
					if(runMethod(mbo, method)!=null){
						String setMethodName = method.replaceFirst("get", "set");
					if (wizard.getPageIndexOfOperation(setMethodName) == currentPage) {
						String param = getParam(mbo, method);
						System.out.println("starting to run: "+setMethodName+"("+ param+")");
						if (param != null) {
							Method m = findMethod(wizard.getClass(),setMethodName);
							Object[] arg = new Object[] { param };
							m.invoke(wizard, arg);
							runVerification(mbo, wizard, setMethodName);
						}
					}
					}
				}
				if (currentPage < max) {
					nextPage(wizard);
				}
				currentPage++;
			}
			if(!wizard.canContinue()){
				cancel(wizard);
			}else{
				finish(wizard);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
		}
	}


	private Object runMethod(Object mbo, String method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method m = findMethod(mbo.getClass(), method);
		return m.invoke(mbo);
		}

	private void cancel(IWizard wizard){
		wizard.cancel();
		System.out.println("Wizard canceled");
	}

//	private void cancel(IWizard wizard) throws IllegalArgumentException,
//	IllegalAccessException, InvocationTargetException {
//		Method m = findMethod(wizard.getClass(), "cancel");
//		m.invoke(wizard);
//		System.out.println("Wizard canceled");
//	}
	
	private void runVerification(IWizardEntity mbo, IWizard wizard, String operation) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String verificationNames = wizard.getDependOperation(operation);
		if(verificationNames!=null){
			for(String veficationMethod:verificationNames.split(",")){
				runSingleVerification(mbo, wizard, veficationMethod);
			}
		}
	}
	

	private void runSingleVerification(IWizardEntity mbo, IWizard wizard, String verificationName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (verificationName != null) {
			Method m = findMethod(mbo.getClass(), verificationName, 0);
			if (m.invoke(mbo) != null) {
				VerificationCriteria vc = (VerificationCriteria)m.invoke(mbo);
//				String expected = m.invoke(mbo).toString();
				System.out.println("starting to run: " + verificationName + "("
						+ vc.getExpected() + ","+ vc.isContinueWhenMatch() +")");
				RationalTestScript.sleep(1);
				Object[] arg = new Object[] { vc};
				Method method = findMethod(wizard.getClass(), verificationName);
//				System.out.println(method.getParameterTypes().length);
				method.invoke(wizard, arg);
			}
		}
	}
	
//	private void runVerification(IWizardEntity mbo, IWizard wizard, String operation) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//		String verificationName = wizard.getDependOperation(operation);
//		if (verificationName != null) {
//			Method m = findMethod(mbo.getClass(), verificationName, 0);
//			if (m.invoke(mbo) != null) {
//				VerificationCriteria vc = (VerificationCriteria)m.invoke(mbo);
////				String expected = m.invoke(mbo).toString();
//				System.out.println("starting to run: " + verificationName + "("
//						+ vc.getExpected() + ","+ vc.isContinueWhenMatch() +")");
//				RationalTestScript.sleep(1);
//				Object[] arg = new Object[] { vc};
//				Method method = findMethod(wizard.getClass(), verificationName);
////				System.out.println(method.getParameterTypes().length);
//				method.invoke(wizard, arg);
//			}
//		}
//	}

	private void finish(IWizard wizard) {
		wizard.finish();
		System.out.println("Wizard finished");

	}

//	private void finish(IWizard wizard) throws IllegalArgumentException,
//	IllegalAccessException, InvocationTargetException {
//		Method m = findMethod(wizard.getClass(), "finish");
//		m.invoke(wizard);
//		System.out.println("Wizard finished");
//		
//	}

	private void nextPage(IWizard wizard){
		wizard.next();
	}
//	private void nextPage(IWizard wizard) throws IllegalArgumentException,
//	IllegalAccessException, InvocationTargetException {
//		Method m = findMethod(wizard.getClass(), "next");
//		m.invoke(wizard);
//	}

	private String getParam(Object mbo, String method)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method m = findMethod(mbo.getClass(), method);
		if (m.invoke(mbo) != null) {
			return m.invoke(mbo).toString();
		} else {
			return null;
		}
	}

	private String[] getGetMethods(Class clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		java.util.List<String> list = new ArrayList<String>();
		// String[] result = new String[methods.length / 2];
		int j = 0;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get")) {
				list.add(methods[i].getName());
			}
		}
		return (String[]) list.toArray(new String[0]);
	}

	public Method findMethod(Class componentClass, String actionName) {
		Method method = null;
		Method[] m = componentClass.getDeclaredMethods();
		for (int i = 0; i < m.length; i++) {
			if (actionName.equals(m[i].getName())) {
				method = m[i];
				break;
			}
		}
		if(method == null){
			throw new RuntimeException("Failed to find method: "+actionName);
		}
		return method;
	}


	public Method findMethod(Class componentClass, String actionName,
			int parameterTypeCount) {
		Method method = null;
		Method[] m = componentClass.getDeclaredMethods();
		for (int i = 0; i < m.length; i++) {
			if (actionName.equals(m[i].getName())) {
				int pCount = m[i].getParameterTypes().length;
				if (parameterTypeCount == pCount) {
					method = m[i];
					break;
				}
			}
		}
		if(method == null){
			throw new RuntimeException("Failed to find method: "+actionName+"("+parameterTypeCount+")");
			
		}
		return method;
}


	private int maxPage(Object mbo, IWizard dbMboCreationWizard) throws 
	IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		int page = 0;
		for (String method : getGetMethods(mbo.getClass())) {
			String param = getParam(mbo, method);
			if (method.startsWith("get")&& param!=null) {
				String setMethod = method.replaceFirst("get", "set");
				int pageNo = dbMboCreationWizard.getPageIndexOfOperation(setMethod);
				if (pageNo > page) {
					page = pageNo;
				}
			}
		}
//		System.out.println("Max page is "+page);
		return page;
	}
}
