package com.sybase.automation.framework.core.exception;


/**
 * Thrown when TestRunner can not find the action defined in action file during the 
 * execution of testcase.
 *  
 * @author xfu
 */
public class ActionNotFoundException extends com.sybase.automation.framework.core.exception.ActionException
{

    public ActionNotFoundException() 
    {
    }
    public ActionNotFoundException(String actionName) 
    {
        super("The action: "+actionName+" is not found");
    }
    public ActionNotFoundException(String actionName,String msg) 
    {
        super("The action: "+actionName+" is not found "+msg);
    }
}
