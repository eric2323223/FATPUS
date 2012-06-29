/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.sybase.automation.framework.widget.win32.impl;

import com.jacob.com.*;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.sybase.automation.framework.widget.win32.impl._ContextMenu;

public class _ContextMenu extends Dispatch 
{

    public static final String componentName = "SuadeXDE._NativeContextMenu";

    public _ContextMenu() 
    {
        super(componentName);
    }

    /**
	* This constructor is used instead of a case operation to
	* turn a Dispatch object into a wider object - it must exist
	* in every wrapper class whose instances may be returned from
	* method calls wrapped in VT_DISPATCH Variants.
	*/
    public _ContextMenu(Dispatch d) 
    {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public _ContextMenu(String compName) 
    {
        super(compName);
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param logFile an input-parameter of type String
	 * @param lastParam an input-parameter of type int
	 */
    public void configLog(String logFile, int lastParam) 
    {
        Dispatch.call(this, "ConfigLog", logFile, new Variant(lastParam));
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @return the result is of type int
	 */
    public int getPopupMenu() 
    {
        return Dispatch.call(this, "GetPopupMenu").toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param lastParam an input-parameter of type String
	 * @return the result is of type int
	 */
    public int findChild(int hMenu, String lastParam) 
    {
        return Dispatch.call(this, "FindChild", new Variant(hMenu), lastParam).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param lastParam an input-parameter of type String
	 * @return the result is of type int
	 */
    public int findItem(int hMenu, String lastParam) 
    {
        return Dispatch.call(this, "FindItem", new Variant(hMenu), lastParam).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type String
	 */
    public String getMenuText(int hMenu, int lastParam) 
    {
        return Dispatch.call(this, "GetMenuText", new Variant(hMenu), new Variant(lastParam)).toString();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getItemCount(int hMenu) 
    {
        return Dispatch.call(this, "GetItemCount", new Variant(hMenu)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type boolean
	 */
    public boolean menuItemChecked(int hMenu, int lastParam) 
    {
        return Dispatch.call(this, "MenuItemChecked", new Variant(hMenu), new Variant(lastParam)).toBoolean();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type boolean
	 */
    public boolean menuItemEnabled(int hMenu, int lastParam) 
    {
        return Dispatch.call(this, "MenuItemEnabled", new Variant(hMenu), new Variant(lastParam)).toBoolean();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hMenu an input-parameter of type int
	 * @param idx an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type boolean
	 */
    public boolean p_GetItemState(int hMenu, int idx, int lastParam) 
    {
        return Dispatch.call(this, "p_GetItemState", new Variant(hMenu), new Variant(idx), new Variant(lastParam)).toBoolean();
    }

}
