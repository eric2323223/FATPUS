package testscript.Workflow.Migration;
import java.util.List;

import resources.testscript.Workflow.Migration.Mgrt_212To213_CUD_2Helper;
import testscript.Workflow.Migration.cfg.Cfg;

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

import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_212To213_CUD_2 extends Mgrt_212To213_CUD_2Helper
{
	/**
	 * Script Name   : <b>Mgrt_212To213_CUD_2</b>
	 * Generated     : <b>May 24, 2012 4:56:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/24
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT212);
		sleep(3);
		WN.openLegacyProjectMbo("pro212", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName4)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WorkFlowEditor.open("pro212", "synccrudwf1");
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName4, "synccrudwf1"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser("dt"),
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.Mgrt_icons.Script",
		sendNotification());
		
	vpManual("db",1,getDB("select * from Department where dept_id=100 and dept_name='R' and dept_head_id=501")).performTest();
	
	}
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").subject("findall212"));
	}
	
	public static int getDB(String sql){
		try {
		ASA db = new ASA();
		db.setProperty("host", "localhost");
		db.setProperty("login", "dba");
		db.setProperty("password", "sql");
		db.setProperty("port", "5500");
		db.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
		List result = DBUtil.runSQLForResult((IDBResource) db, sql);
		System.out.println(result.size());
		return result.size();
		} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Failed to get record data from DB");
		}
		}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").moveTo("Department_update_instance").throughMenuItem("Open Department_update_instance");
		s.screen("Department_update_instance").setData("Department_dept_name_attribKey", "R");
		s.screen("Department_update_instance").moveTo("Department_create").throughMenuItem("Update");
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "1");
		return s;
	}
	
}

