package com.sybase.automation.framework.core.cleanup;

import org.jawin.*;
import org.jawin.constants.*;
import org.jawin.marshal.*;
import org.jawin.io.*;
import java.io.*;
import java.util.Date;

/**
 * Jawin generated code please do not edit
 *
 * Dispatch: _SybaseWorkspace
 * Description: 
 * Help file:   
 *
 * @author JawinTypeBrowser
 */

public class _SybaseWorkspace extends DispatchPtr {
	public static final GUID DIID = new GUID("{c6969604-6bc5-475c-B1B9-C31ECBC885D4}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, _SybaseWorkspace.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * _SybaseWorkspace (it is required by Jawin for some internal working though).
	 */
	public _SybaseWorkspace() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the _SybaseWorkspace interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public _SybaseWorkspace(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the _SybaseWorkspace interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public _SybaseWorkspace(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the _SybaseWorkspace interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the _SybaseWorkspace interface on.
	 */
	public _SybaseWorkspace(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    /*public void QueryInterface(Object[] riid,void[] 
        [] ppvObj) throws COMException
    {
      
		invokeN("QueryInterface", new Object[] {riid, ppvObj});
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int AddRef() throws COMException
    {
      
		return ((Integer)invokeN("AddRef", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int Release() throws COMException
    {
      
		return ((Integer)invokeN("Release", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return void
    **/
    /*public void GetTypeInfoCount(int[] pctinfo) throws COMException
    {
      
		invokeN("GetTypeInfoCount", new Object[] {pctinfo});
        
    }*/
    /**
    *
    
    * @param itinfo
    * @param lcid
    * @return void
    **/
    /*public void GetTypeInfo(int itinfo,int lcid,void[] 
        [] pptinfo) throws COMException
    {
      
		invokeN("GetTypeInfo", new Object[] {new Integer(itinfo), new Integer(lcid), pptinfo});
        
    }*/
    /**
    *
    
    * @param cNames
    * @param lcid
    * @return void
    **/
    /*public void GetIDsOfNames(Object[] riid,int[] 
        [] rgszNames,int cNames,int lcid,int[] rgdispid) throws COMException
    {
      
		invokeN("GetIDsOfNames", new Object[] {riid, rgszNames, new Integer(cNames), new Integer(lcid), rgdispid});
        
    }*/
    /**
    *
    
    * @param dispidMember
    * @param lcid
    * @param wFlags
    * @return void
    **/
    /*public void Invoke(int dispidMember,Object[] riid,int lcid,short wFlags,Object[] pdispparams,Variant[] pvarResult,Object[] pexcepinfo,int[] puArgErr) throws COMException
    {
      
		invokeN("Invoke", new Object[] {new Integer(dispidMember), riid, new Integer(lcid), new Short(wFlags), pdispparams, pvarResult, pexcepinfo, puArgErr});
        
    }*/
    /**
    *
    
    * @param caption
    * @param path
    * @return void
    **/
    public _SybaseWorkspace getInstance(String caption,String path) throws COMException
    {
      _SybaseWorkspace res = new _SybaseWorkspace();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("getInstance", new Object[] {caption, path});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public void focus() throws COMException
    {
      
		invokeN("focus", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void closeAllFiles() throws COMException
    {
      
		invokeN("closeAllFiles", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void closeAllDialogs() throws COMException
    {
      
		invokeN("closeAllDialogs", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void resetPerspective() throws COMException
    {
      
		invokeN("resetPerspective", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void shutdownWorkspace() throws COMException
    {
      
		invokeN("shutdownWorkspace", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void restartWorkspace() throws COMException
    {
      
		invokeN("restartWorkspace", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void resetEclipse() throws COMException
    {
      
		invokeN("resetEclipse", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void restartEclipse() throws COMException
    {
      
		invokeN("restartEclipse", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void waitForExistence() throws COMException
    {
      
		invokeN("waitForExistence", new Object[] {});
        
    }
    
    public void activateWorkSpaceLauncher() throws COMException{
    	invokeN("activateWorkSpaceLauncher", new Object[] {});
    }
   
}