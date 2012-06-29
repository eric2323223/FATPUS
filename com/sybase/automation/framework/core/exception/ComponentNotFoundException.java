package com.sybase.automation.framework.core.exception;


/**
 * 
 * Thrown when TestRunner can not find the component defined in action file during the 
 * execution of testcase.
 * @author xfu
 *
 */
public class ComponentNotFoundException extends com.sybase.automation.framework.core.exception.ActionException 
{
    public ComponentNotFoundException() 
    {
    }
    public ComponentNotFoundException(String componentName) 
    {
        super("The component:"+componentName+" is not found");
    }

}
