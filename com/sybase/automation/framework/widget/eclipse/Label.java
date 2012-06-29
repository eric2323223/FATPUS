package com.sybase.automation.framework.widget.eclipse;

import com.sybase.automation.framework.widget.interfaces.ILabel;
import com.rational.test.ft.object.interfaces.*;

/**
 *  * Created on Mar 28, 2007
 * @author zhouw
 *
 * Widget to encapsulate the properties of the Label Test Object
 */
public class Label implements ILabel
{
	private GuiTestObject _Label;
	
    public Label(GuiTestObject label) 
    {
    	_Label = label;
    	_Label.getObjectReference();

    }
    
    /**
	 * Get the Text of the label
	 */
    public String getText() 
    {
        return String.valueOf(_Label.getProperty(TEXT));
    }
    
}
