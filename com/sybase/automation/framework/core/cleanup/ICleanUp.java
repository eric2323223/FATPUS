package com.sybase.automation.framework.core.cleanup;

import org.jawin.COMException;

/**
 * 
 *  Define the interface of the function which will be executed right after the
 * execution of each test case in order to make the execution of testcases 
 * independent from each other 
 * @author xfu
 *
 * 
 */
public interface ICleanUp 
{
    void doClean() throws COMException;
}
