package component.editor;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class VisualSQL extends RationalTestScript
{
	/**
	 * Script Name   : <b>VisualSQL</b>
	 * Generated     : <b>Sep 6, 2010 8:13:02 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/09/06
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	public void invoke(){
		DOF.getButton(DOF.getDialog("New Attributes"), "Visual S&QL").click();
	}
	public void OKbtn_asa(){
		DOF.getButton(DOF.getDialog("Select - ADAPTIVE SERVER ANYWHERE.JDBC.sampledb.dba"), "OK").click();
	}
}

