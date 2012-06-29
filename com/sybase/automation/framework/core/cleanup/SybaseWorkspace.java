package com.sybase.automation.framework.core.cleanup;

import org.jawin.*;
import org.jawin.constants.*;
import org.jawin.marshal.*;
import org.jawin.io.*;

import com.sybase.automation.framework.common.ResourceUtil;
import java.io.*;


/**
 * Jawin generated code please do not edit
 * 
 * CoClass Interface: SybaseWorkspace Description: Help file:
 * 
 *  
 */

/**
 * @author xfu
 */
public class SybaseWorkspace
{

    public static void main(String[] args) throws COMException
    {
      //  new SybaseWorkspace().restartEclipse();
       // new SybaseWorkspace().resetWorkspace();
       // new SybaseWorkspace().activateWorkSpaceLauncher();
    	new SybaseWorkspace().shutDownEclipse();
    }
    public static final GUID        CLSID = new GUID("{6e03e6e3-b27e-4231-8C0E-1816E6A056C4}");
    private  _SybaseWorkspace impl;

    public SybaseWorkspace() throws COMException
    {
        _SybaseWorkspace temp = new _SybaseWorkspace(CLSID);        
        ResourceUtil.loadResource("\\testrunner.properties");
        String workspaceTitle = ResourceUtil.getProperty("workspace_title");
        String workspaceInstallationDir = ResourceUtil.getProperty("workspace_installation_dir");
        impl = temp.getInstance(workspaceTitle, workspaceInstallationDir);
    }

    public void shutDownEclipse() throws COMException{
    	impl.shutdownWorkspace() ;
    }
    
    
    public void resetEclipse() throws COMException
    {
        impl.resetEclipse();
    }
      /**
     * 
     * 
     * @return void
     */
    public void restartEclipse() throws COMException
    {
        impl.restartEclipse();
    }
    
    /**
     * @throws COMException
     */
    public void activateWorkSpaceLauncher() throws COMException{
        
        impl.activateWorkSpaceLauncher() ;
    }
}
