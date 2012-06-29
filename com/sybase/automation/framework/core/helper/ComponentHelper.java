package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.DocumentException;
import org.dom4j.*;

import com.rational.test.ft.PropertyNotFoundException;
import com.rational.test.ft.application.rational_ft_impl;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.services.ILog;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.core.cleanup.ICleanUp;
import com.sybase.automation.framework.core.exception.ParameterNotFoundException;
import com.sybase.automation.framework.core.exception.VerificationFailure;
import com.sybase.automation.framework.tool.ComponentGenerator;
import com.sybase.automation.framework.widget.eclipse.CCombo;
import com.sybase.automation.framework.widget.eclipse.Combo;
import com.sybase.automation.framework.widget.eclipse.Label;
import com.sybase.automation.framework.widget.eclipse.Spinner;
import com.sybase.automation.framework.widget.eclipse.Table;
import com.sybase.automation.framework.widget.eclipse.Tree;
import com.sybase.automation.framework.widget.eclipse.Window;
import com.sybase.automation.framework.widget.factory.EclipseWidgetFactory;
import com.sybase.automation.framework.widget.interfaces.IComboBox;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.IToolbar;
import com.sybase.automation.framework.widget.interfaces.ITree;
import com.sybase.automation.framework.widget.eclipse.TextBox;
import com.sybase.automation.framework.widget.eclipse.ListBox;

//

/**
 * @author xfu
 *  
 */
public class ComponentHelper extends RationalTestScript {

    protected EclipseWidgetFactory ewFactory = new EclipseWidgetFactory();

    /* generate the component class */
    protected void generateComponent() {
        try {
            ComponentGenerator.generate(this, getProjectRootPath());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void generateTestcase(Class class1) {
        try {
            ComponentGenerator.generate(this, class1, getProjectRootPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the root path of this project
     */
    protected String getProjectRootPath() {
        // TODO Auto-generated method stub
        return (String) getOption("rt.datastore");
    }

    /**
     * pretty-print a hashtable (sorted)
     */
    protected static void printHash(Hashtable h) {
        TreeMap sortedMap = new TreeMap(h);
        Set keySet = sortedMap.keySet();
        Iterator iter = keySet.iterator();

        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = h.get(key);
            System.out.println(key + " = " + value);

        }
    }

    /**
     * pretty-print a array (sorted)
     */
    protected static void printArray(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + ": " + array[i]);
        }
    }

    protected String normalize(String input) {
        String output;
        output = input.replaceAll("\r", "");
        output = output.replaceAll(" ; ", ";");
        return output;
    }
    
   
}