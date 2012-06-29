package com.sybase.automation.framework.widget.helper;
import java.awt.Point;
import java.util.regex.*;
import java.util.regex.Pattern;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author yongli
 */
public class RectangleHelper extends RationalTestScript
{
	/**
	 * Script Name   : <b>RectangleHelper</b>
	 * Generated     : <b>Aug 25, 2008 9:54:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/08/25
	 * @author yongli
	 */

	/**
	 * Script Name   : <b>RectangleHelper</b>
	 * Generated     : <b>Aug 21, 2008 8:58:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/08/21
	 * @author pyin
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here

	    parseRectangle(DOF.getButton(DOF.getDialog("Generate DDL"), "< &Back"),0);
	}
	
	private static final int X = 0;
	private static final int Y = 1;
	private static final int WIDTH = 2;
	private static final int HEIGHT = 3;
	
	private static int parseRectangle(TestObject obj, int element){
	    //sample string representation: java.awt.Rectangle[x=0,y=0,width=76,height=23]
//	    System.out.println(obj.invoke("getBounds").toString());
	    Pattern r = Pattern.compile("\\d+");
	    Matcher m = r.matcher(obj.invoke("getBounds").toString());
	    int data[] =new int[4];
	    int counter = 0;
	    while (m.find()) {
	    	data[counter++] = new Integer(m.group()).intValue();
	    } 
	    return data[element];
	}
	
	public static int getX(TestObject obj){
	    return parseRectangle(obj, X);
	    
	}
	
	public static int getY(TestObject obj){
	    return parseRectangle(obj, Y);
	}
	
	public static int getWidth(TestObject obj){
	    return parseRectangle(obj, WIDTH);
	}
	
	public static int getHeight(TestObject obj){
	    return parseRectangle(obj, HEIGHT);
	}

	public static Point getCenterPoint(TestObject obj){
		int width = getWidth(obj);
		int height = getHeight(obj);
		return new Point(width/2, height/2);
	}
}

