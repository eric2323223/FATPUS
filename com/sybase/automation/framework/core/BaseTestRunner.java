/**
 * Created on Mar 13, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jawin.COMException;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;

import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.core.cleanup.CleanerFactory;
import com.sybase.automation.framework.core.cleanup.ICleanUp;
import com.sybase.automation.framework.core.cleanup.ResetEclipse;
import com.sybase.automation.framework.core.cleanup.RestartEclipse;
import com.sybase.automation.framework.core.exception.ActionNotFoundException;
import com.sybase.automation.framework.core.exception.ComponentNotFoundException;
import com.sybase.automation.framework.core.exception.VerificationFailure;
import com.sybase.automation.framework.core.parser.CSVTestCaseParser;
import com.sybase.automation.framework.core.parser.DBTestCaseParser;
import com.sybase.automation.framework.core.parser.ITestCaseParser;
import com.sybase.automation.framework.core.parser.ParserFactory;

/**
 * @author xfu
 */
abstract public class BaseTestRunner extends RationalTestScript {

	/**
	 * Script Name : <b>BaseTestRunnerScript </b> Generated : <b>Oct 14, 2006
	 * 9:11:23 AM </b> Description : XDE Tester Script Original Host : WinNT
	 * Version 5.0 Build 2195 (Service Pack 4)
	 * 
	 * @since 2006/10/14
	 * @author xfu
	 */

	private Logger log = Logger.getLogger(this.getClass().getName());

	protected String caseFileSuffix;

	protected String actionComments;

	private TestCase currentTestCase;

	protected boolean doClean = true;

	/**
	 * Constructor
	 */
	public BaseTestRunner() {
		ResourceUtil.loadResource("\\testrunner.properties");
		caseFileSuffix = ResourceUtil.getProperty("case_file_suffix");
		actionComments = ResourceUtil.getProperty("comment");
	}

	/**
	 * Method runTestCase.
	 */
	public void run(String path) throws IOException, Throwable {
		onStart(path);
		try {
			runTest();
		} catch (Throwable e) {
			if (verificationFails(e)) {
				onVerificationFail((VerificationFailure) e.getCause());
			} else {
				onUnexpectedtFail(e);
			}
		} finally {
			onEnd();
		}
	}

	/**
	 * Run the testcase from step "start" to step "end"
	 * 
	 * @param path
	 * @param start
	 * @param end
	 * @throws Throwable
	 */
	public void run(String path, int start, int end) throws Throwable {
		onStart(path, start, end);

		try {
			runTest();

		} catch (Throwable e) {

			if (verificationFails(e)) {
				onVerificationFail((VerificationFailure) e.getCause());
			} else {
				onUnexpectedtFail(e);
			}
		} finally {
			onEnd();
		}
	}

	/**
	 * Method testStarted.
	 */
	protected void onStart(String path) throws FileNotFoundException,
			IOException {

		/* add file extension automatically */
		if (!path.endsWith(caseFileSuffix))
			path = path + "." + caseFileSuffix;
		/* choose the parser according to the config setting */
		ITestCaseParser parser = ParserFactory.createParser();
		currentTestCase = parser.parse(path);
		logInfo("Testcase " + currentTestCase.getName() + " Starting...");
	}

	/**
	 * @param path
	 * @param start
	 * @param end
	 */
	protected void onStart(String path, int start, int end)
			throws FileNotFoundException, IOException {
		String parserOption = ResourceUtil.getProperty("parser");
		/* add file extension automatically */
		if (!path.endsWith(caseFileSuffix))
			path = path + "." + caseFileSuffix;
		/* choose the parser according to the config setting */
		ITestCaseParser parser = ParserFactory.createParser();
		currentTestCase = parser.parse(path);

		ArrayList actions = currentTestCase.getActions();
		int actCount = actions.size();
		if (end==-1) { end = actCount; }
		for (int i = 0; i < actCount; i++) {
			String[] nextStep = (String[]) actions.get(i);
			if (i < start - 1 || i > end - 1) {
				nextStep[0] = actionComments + nextStep[0];
				actions.set(i, nextStep);
			}

		}
		RationalTestScript.logInfo("Testcase " + currentTestCase.getName()
				+ " Starting...");
	}

	protected void runTest() throws Exception {
		ArrayList actions = currentTestCase.getActions();
		int i = 0;

		for (Iterator iter = actions.iterator(); iter.hasNext();) {
			String[] nextStep = (String[]) iter.next();
			i++;
			/* if a step starts with #@, skip it */
			boolean skip = nextStep[0].startsWith("#");
			if (skip) {
				//Pure comments, won't log the action
				if (!nextStep[0].startsWith("#@")) {
					continue;
				}
			}
			logAction(nextStep, i, skip);
			if (!skip)
				doAction(nextStep);

		}

	}

	/**
	 * Method testEnded.
	 * 
	 * @throws COMException
	 */
	protected void onEnd() throws COMException {
		RationalTestScript.logInfo("Testcase :" + currentTestCase.getName()
				+ " Ending......");
		logXDEResult();
		if (doClean)
			doClean();
	}

	/**
	 * Method testFailed.
	 * 
	 * @param e
	 */
	protected void onVerificationFail(VerificationFailure e) {
		RationalTestScript.logTestResult(e.getMessage(), false);
		currentTestCase.setResult("FAIL");
		throw e;
	}

	/**
	 * Method unexpectedTestFailed.
	 * 
	 * @param e
	 * @throws Throwable
	 */
	protected void onUnexpectedtFail(Throwable e) throws Throwable {

		currentTestCase.setResult("FAIL");
		if (e.getCause() != null) {
			RationalTestScript.logException(e.getCause());
			throw e.getCause();
		} else {
			RationalTestScript.logException(e);
			throw e;
		}

	}

	/**
	 * Method logXDEResult.
	 */
	private void logXDEResult() {

		String caseResult = currentTestCase.getResult();
		String name = currentTestCase.getName();

		if (caseResult.equals("FAIL")) {
			logTestResult("Testcase:" + name + " failed", false);

		} else {
			logTestResult("Testcase:" + name + " passed", true);
		}

	}

	/**
	 * 
	 * @param nextLine
	 * @param stepNumber
	 * @param skip
	 */
	private void logAction(String[] nextLine, int stepNumber, boolean skip) {
		/* skip log statement */
		if (nextLine[0].equals("Logger"))
			return;
		StringBuffer action = new StringBuffer();
		action.append("Step " + stepNumber + ": ");
		action.append(nextLine[0] + "." + nextLine[1] + "\t");
		for (int i = 2; i < nextLine.length; i++) {
			action.append(nextLine[i] + "\t");
		}
		if (!skip)
			logInfo(action.toString());
		else
			logWarning("SKIP " + action.toString());
	}

	
	
	
	/**
	 * Method invokeMethod.
	 * 
	 * lastUpdated by zhouw 2007-05-23
	 */
	private void doAction(String[] step) throws Exception {
		trim(step);
		boolean found = false;
//		String className = step[0];
		String methodName = step[1];
		Method method = null ; 
//		Object currentClass= null ; 
		Object component = loadComponentsByPath(step[0]);
		String runHelper = "com.sybase.automation.framework.core.helper.RunHelper" ;  
		if (component == null)
			throw new ComponentNotFoundException(step[0]);
		
//		look for the method among classes between the current class and it's 
//		parent classes below the runHelper class
		for(Class currentClass = (Class)component.getClass(); currentClass.getName()!=runHelper ; currentClass=currentClass.getSuperclass())
		{
			method = findMethod(currentClass, methodName); 
			if (method!=null)
			{	
				found = true ; 
				break ;
			}
		}
		if(found)
		{	
			HashMap input = new HashMap();
			for (int j = 2; j < step.length; j++) 
			{
				String parameter = step[j];
				System.out.println("Parameter is: "+parameter) ;
				int start = parameter.indexOf('=');
				String key = parameter.substring(0, start);
				String value = parameter.substring(start + 1);
//				System.out.println("Key is : "+ key) ; 
//				System.out.println("Value is : "+ value); 
				input.put(key, value);
			}
			Object[] parameters = null;
			if (input.size() > 0)
				parameters = new Object[] { input };
			
			//invoke the action
			Object result = method.invoke(component, parameters);
			/* check whether or not any verification point failed */
			if (result != null && result.toString().equals("false")) {
				currentTestCase.setResult("FAIL");
			}
		}
		else
		{	
			throw new ActionNotFoundException(methodName); 
		}
	}
	
	
	/**
	 * The method is used to find the specified method in the specified class
	 * 
	 * @author zhouw
	 */
	public Method findMethod(Class componentClass, String actionName)
	{
		Method method = null ; 
		Method[] m = componentClass.getDeclaredMethods();
		for (int i = 0; i < m.length; i++) 
		{
			if (actionName.equals(m[i].getName())) 
			{
				method =  m[i] ;
				break; 
			}
		}
		return method; 
	}
		
	

	/**
	 * Use this method to replace method loadComponents() when using this
	 * method,in csv file user need to provide the complete path,e.g.
	 * component.editor.common.SQLEditor
	 * 
	 * @author ywhu
	 *  
	 */

	private Object loadComponentsByPath(String path) {
		String component = path;
		try {
			Object componentClass = Class.forName(component).newInstance();
			return componentClass;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param nextStep
	 */
	private void trim(String[] nextStep) {

		for (int i = 0; i < nextStep.length; i++) {
			nextStep[i] = nextStep[i].trim();
		}
	}

	/**
	 * clean the GUI test env.
	 * 
	 * @throws COMException
	 */
	private void doClean() throws COMException {
		ICleanUp cleaner = CleanerFactory.createCleaner();
		cleaner.doClean();
	}

	/**
	 * @param e
	 * @return
	 */
	private boolean verificationFails(Throwable e) {
		return e.getCause() instanceof VerificationFailure;
	}

	/**
	 * @return Returns the currentTestCase.
	 */
	public TestCase getCurrentTestCase() {
		return currentTestCase;
	}
}