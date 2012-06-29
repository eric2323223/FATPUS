package testscript.Workflow.Migration;
import java.sql.SQLException;

import resources.testscript.Workflow.Migration.Mgrt_RestoreDataHelper;
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
import com.sybase.automation.framework.common.DBUtil;

import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_RestoreData extends Mgrt_RestoreDataHelper
{
	/**
	 * Script Name   : <b>Mgrt_RestoreData</b>
	 * Generated     : <b>May 25, 2012 3:21:03 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/25
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from Department where dept_id in(1,2,3,4)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

