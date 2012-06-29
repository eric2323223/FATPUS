package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import resources.testscript.Workflow.DCN.WFDCN_MultiMsg_E2EHelper;
import testscript.Workflow.cfg.Cfg;

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
import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DCNRequestSender;
import com.sybase.automation.framework.common.QueryCriteria;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WFDCN_MultiMsg_E2E extends WFDCN_MultiMsg_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_MultiMsg_E2E</b>
	 * Generated     : <b>Nov 18, 2011 4:27:06 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.name("Department")
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from department"));
		WN.createCacheGroup(new CacheGroup()
				.startParameter(Cfg.projectName)
				.name("dcn")
				.type(CachePolicy.DCN));
		WN.changeCacheGroup(Cfg.projectName, "Department", "Default (Default)", "dcn");	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.option(WorkFlow.SP_ACTIVATE)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.from("ff")
				.subject("updated")
				.subjectMatchingRule("updated")
				.to("DT")
				.toMatchingRule("DT")
				);
		WorkFlowEditor.link("Activate Screen", "Credential Request Screen");
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), activate(), "");
		
	//vp1: set Multiple valid Messages
		DCNRequestSender.post(insertUrlMultipleMessage());
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='myname1'"))).performTest();
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='myname2'"))).performTest();
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='myname3'"))).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("DT")
			.deployToServer("true")
			.unwiredServer("My Unwired Server"), insertCheck(), "");
		
	//vp2: set Multiple Invalid Messages
		DCNRequestSender.post(insertUrlMultipleInvalidMessage());
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='myname4'"))).performTest();
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='myname5'"))).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
		.assignToUser("DT")
		.deployToServer("true")
		.unwiredServer("My Unwired Server"), insertCheck(), "");
				
	}
	
	//not include the invalid message:
	private URL insertUrlMultipleMessage(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"10\",\"dept_name\":\"myname1\",\"dept_head_id\":\"501\"},\"parameters\":{}}" +
					"{\"id\":\"2\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"11\",\"dept_name\":\"myname11\",\"dept_head_id\":\"502\"},\"parameters\":{}}," +
					"{\"id\":\"3\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"12\",\"dept_name\":\"myname12\",\"dept_head_id\":\"503\"},\"parameters\":{}}]}"; 
					
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//include a invalid message(mbo=InvalidDepartment):
	private URL insertUrlMultipleInvalidMessage(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"14\",\"dept_name\":\"myname4\",\"dept_head_id\":\"504\"},\"parameters\":{}}" +
					"{\"id\":\"2\",\"mbo\":\"InvalidDepartment\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"15\",\"dept_name\":\"myname5\",\"dept_head_id\":\"505\"},\"parameters\":{}}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
//	private CallBackMethod insertDCN() {
//		return new CallBackMethod().receiver(DCNRequestSender.class)
//			.methodName("post")
//			.parameter(insertDCN())	;
//	}
	

	private CustomJsTestScript insertCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		return s;
	}
	
	
	private CustomJsTestScript activate() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate_Screen").moveTo("Credential_Request_Screen").throughMenuItem("Open Credential Request Screen");
		s.screen("Credential_Request_Screen").setData("cc_username","supAdmin");
		s.screen("Credential_Request_Screen").setData("cc_password","s3pAdmin");
		s.screen("Credential_Request_Screen").menuItem("Save_Screen");
		return s;
	}
		
}

