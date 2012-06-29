package com.sybase.automation.framework.core.cleanup;

import org.jawin.COMException;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.core.cleanup.ICleanUp;

/**
 * Description : Functional Test Script
 * 
 * @author xfu
 */
public class RestartEclipse implements ICleanUp
{
    /**
     * Script Name : <b>RestartEclipse </b> Generated : <b>Oct 15, 2006 7:48:15 PM </b> Description : XDE Tester Script
     * Original Host : WinNT Version 5.0 Build 2195 (Service Pack 4)
     * @throws COMException
     * 
     * @since 2006/10/15
     * @author xfu
     */
    /*
     * (non-Javadoc)
     * 
     * @see framework.core.cleanup.ICleanUp#doClean()
     */
    public void doClean() throws COMException
    {
        new SybaseWorkspace().restartEclipse();
    }

   

}
