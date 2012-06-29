package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

//import org.dom4j.DocumentException;
import org.dom4j.*;

import com.rational.test.ft.PropertyNotFoundException;
import com.rational.test.ft.application.rational_ft_impl;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.services.ILog;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.core.cleanup.ICleanUp;
import com.sybase.automation.framework.core.exception.ParameterNotFoundException;
import com.sybase.automation.framework.core.exception.VerificationFailure;
import com.sybase.automation.framework.tool.ComponentGenerator;
import com.sybase.automation.framework.widget.eclipse.CCombo;
import com.sybase.automation.framework.widget.eclipse.Combo;
import com.sybase.automation.framework.widget.eclipse.Label;
import com.sybase.automation.framework.widget.eclipse.Spinner;
import com.sybase.automation.framework.widget.eclipse.Table;
import com.sybase.automation.framework.widget.eclipse.Tree;
import com.sybase.automation.framework.widget.eclipse.Window;
import com.sybase.automation.framework.widget.factory.EclipseWidgetFactory;
import com.sybase.automation.framework.widget.interfaces.IComboBox;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.IToolbar;
import com.sybase.automation.framework.widget.interfaces.ITree;
import com.sybase.automation.framework.widget.eclipse.TextBox;
import com.sybase.automation.framework.widget.eclipse.ListBox;

import com.sybase.automation.framework.core.helper.ComponentHelper;

/**
 * Description : 
 * This class is created for RFT Test Script Standard. 
 * 
 * @author yongliu
 *  
 */
public abstract class ScriptHelper extends ComponentHelper {

	/**
	 * @methodDesc Used to setup test environment, should be implemented in specific script.
	 */ 
    abstract public void preRun();
    
	/**
	 * @methodDesc Used to cleanup test environment, should be override in specific script.
	 */ 
    abstract public void afterRun();
    
	/**
	 * @methodDesc Used to implement test steps.
	 */ 
    abstract public void test();
    

	/**
	 * @methodDesc Used to execute test steps.
	 */ 
    public void runTest(){
	    try{
			try{
		    	while (!dpDone()){
						test();
						dpNext();
					}
				}catch (com.rational.test.ft.DatapoolException e){
					test();
			}
			}catch(Exception e){
				afterRun();
				RationalTestScript.logException(e);
		}
    }



}
