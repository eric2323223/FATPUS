/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.sybase.automation.framework.widget.win32.impl;

import com.jacob.com.*;

public class _TreeView extends Dispatch 
{

    public static final String componentName = "SuadeXDE._NativeTreeView";

    public _TreeView() 
    {
        super(componentName);
    }

    /**
	* This constructor is used instead of a case operation to
	* turn a Dispatch object into a wider object - it must exist
	* in every wrapper class whose instances may be returned from
	* method calls wrapped in VT_DISPATCH Variants.
	*/
    public _TreeView(Dispatch d) 
    {
        // take over the IDispatch pointer
        m_pDispatch = d.m_pDispatch;
        // null out the input's pointer
        d.m_pDispatch = 0;
    }

    public _TreeView(String compName) 
    {
        super(compName);
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getChildCount(int hwndTV, int lastParam) 
    {
        return Dispatch.call(this, "GetChildCount", new Variant(hwndTV), new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getRoot(int lastParam) 
    {
        return Dispatch.call(this, "GetRoot", new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @param lastParam an input-parameter of type String
	 * @return the result is of type int
	 */
    public int findChild(int hwndTV, int hItem, String lastParam) 
    {
        return Dispatch.call(this, "FindChild", new Variant(hwndTV), new Variant(hItem), lastParam).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param className an input-parameter of type String
	 * @param xPos an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int findTreeView(String className, int xPos, int lastParam) 
    {
        return Dispatch.call(this, "FindTreeView", className, new Variant(xPos), new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type boolean
	 */
    public boolean expandItem(int hwndTV, int lastParam) 
    {
        return Dispatch.call(this, "ExpandItem", new Variant(hwndTV), new Variant(lastParam)).toBoolean();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getParent(int hwndTV, int lastParam) 
    {
        return Dispatch.call(this, "GetParent", new Variant(hwndTV), new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getChild(int hwndTV, int lastParam) 
    {
        return Dispatch.call(this, "GetChild", new Variant(hwndTV), new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type int
	 */
    public int getNextSibling(int hwndTV, int lastParam) 
    {
        return Dispatch.call(this, "GetNextSibling", new Variant(hwndTV), new Variant(lastParam)).toInt();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @param lastParam an input-parameter of type int
	 * @return the result is of type String
	 */
    public String getItemText(int hwndTV, int hItem, int lastParam) 
    {
        return Dispatch.call(this, "GetItemText", new Variant(hwndTV), new Variant(hItem), new Variant(lastParam)).toString();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @return the result is of type String
	 */
    public String getItemText(int hwndTV, int hItem) 
    {
        return Dispatch.call(this, "GetItemText", new Variant(hwndTV), new Variant(hItem)).toString();
    }

    /**
	 * Wrapper for calling the ActiveX-Method and receiving the output-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @param lastParam is an one-element array which sends the input-parameter
	 *                  to the ActiveX-Component and receives the output-parameter
	 * @return the result is of type String
	 */
    public String getItemText(int hwndTV, int hItem, int[] lastParam) 
    {
        Variant vnt_lastParam = new Variant();
        if( lastParam == null || lastParam.length == 0 )
        vnt_lastParam.noParam();
        else
        vnt_lastParam.putIntRef(lastParam[0]);

        String result_of_GetItemText = Dispatch.call(this, "GetItemText", new Variant(hwndTV), new Variant(hItem), vnt_lastParam).toString();

        if( lastParam != null && lastParam.length > 0 )
        lastParam[0] = vnt_lastParam.toInt();

        return result_of_GetItemText;
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @param isLeft an input-parameter of type boolean
	 * @param lastParam an input-parameter of type short
	 * @return the result is of type boolean
	 */
    public boolean click(int hwndTV, int hItem, boolean isLeft, short lastParam) 
    {
        return Dispatch.call(this, "Click", new Variant(hwndTV), new Variant(hItem), new Variant(isLeft), new Variant(lastParam)).toBoolean();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @param isLeft an input-parameter of type boolean
	 * @return the result is of type boolean
	 */
    public boolean click(int hwndTV, int hItem, boolean isLeft) 
    {
        return Dispatch.call(this, "Click", new Variant(hwndTV), new Variant(hItem), new Variant(isLeft)).toBoolean();
    }

    /**
	 * Wrapper for calling the ActiveX-Method with input-parameter(s).
	 * @param hwndTV an input-parameter of type int
	 * @param hItem an input-parameter of type int
	 * @return the result is of type boolean
	 */
    public boolean click(int hwndTV, int hItem) 
    {
        return Dispatch.call(this, "Click", new Variant(hwndTV), new Variant(hItem)).toBoolean();
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

}
