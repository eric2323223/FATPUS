package com.sybase.automation.framework.core.cleanup;

//import java.util.logging.Logger;

import org.jawin.COMException;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ResourceUtil;

/**
 * Description : Functional Test Script
 * 
 * @author xfu
 */
public class ResetEclipse implements ICleanUp
{
    /**
     * Script Name : <b>ResetEclipse </b> Generated : <b>Oct 15, 2006 7:47:59 PM </b> Description : XDE Tester Script
     * Original Host : WinNT Version 5.0 Build 2195 (Service Pack 4)
     * 
     * @since 2006/10/15
     * @author xfu
     */

    /**
     * @see util.recover.Recover#doClean()
     */
      public void doClean() throws COMException
    {
        new SybaseWorkspace().resetEclipse();
        
    }

   

}
