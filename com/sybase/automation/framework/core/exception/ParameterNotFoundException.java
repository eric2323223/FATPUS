/*
 * Created on Nov 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.core.exception;


/**
 * Thrown when TestRunner can not find the parameter of the action defined in action file
 * during the execution of testcase.
 * 
 * @author xfu
 */
public class ParameterNotFoundException  extends com.sybase.automation.framework.core.exception.ActionException 
{
    public ParameterNotFoundException() 
    {
    }
    public ParameterNotFoundException(String ParameterName) 
    {
        super("The parameter:"+ParameterName+" is not found");
    }
}
