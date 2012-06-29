package com.sybase.automation.framework.tool;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

/**
 * Description   : Functional Test Script
 * @author pyin
 */
public class RelationshipFigureHelper extends RationalTestScript
{
	/**
	 * Script Name   : <b>RelationshipFigureHelper</b>
	 * Generated     : <b>May 3, 2008 10:03:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/05/03
	 * @author pyin
	 */
	public static String getName(TestObject relationshipFigure){
		return ((TestObject)relationshipFigure.invoke("getFigureEAssociationNameFigure")).invoke("getText").toString();
	}
}

