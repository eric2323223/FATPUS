package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import resources.testscript.Workflow.DCN.WFDCN_Security_E2E_ffHelper;
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
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WFDCN_Security_E2E_ff extends WFDCN_Security_E2E_ffHelper
{
	/**
	 * Script Name   : <b>WFDCN_Security_E2E_ff</b>
	 * Generated     : <b>Nov 24, 2011 3:20:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//Note:	device name = supAdmin
//		WN.useProject(Cfg.projectName);
//		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
//				.name("Department")
//				.dataSourceType("JDBC")
//				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from department"));
//		
//		//create the objectQuery "FindByPrimaryKey" for "department":
//		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
//			.parameter("dept_id,int,false,dept_id")
//			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id")
//			.returnType(ObjectQueryWizard.RT_SINGLE)
//			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
//		new ObjectQueryWizard().create(oq, new WizardRunner()); 
//		
//		WN.createCacheGroup(new CacheGroup()
//				.startParameter(Cfg.projectName)
//				.name("dcn")
//				.type(CachePolicy.DCN));
//		WN.changeCacheGroup(Cfg.projectName, "Department", "Default (Default)", "dcn");	
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//	//create server_initial
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("WF_server")
//				.option(WorkFlow.SP_SERVER_INIT)
//				.mbo("Department")
//				.objectQuery("findByPrimarykey")
//				.to("supAdmin")
//				.subject("dept_id = 1")
//				.toMatchingRule("supAdmin")
//				.setParameterValue("dept_id,Subject,dept_id = "));
//		
//	//create client.active.cretential
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT)
//				.option(WorkFlow.SP_ACTIVATE)
//				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
//				);
//	//set screen:
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
//		WorkFlowEditor.link("Activate Screen", "Credential Request Screen");
//		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
//				.type("Online Request")
//				.project(Cfg.projectName)
//				.mbo("Department")
//				.objectQuery("findAll")
//				.defaultSuccessScreen("Department"));
//		
//	//set credential default value ,not need to input value in device:
//		PropertiesView.resetWidget("Credential Request Screen",new WFEditBox().label("Username:"));
//		PropertiesView.set(new WFEditBox().defaultValue("supAdmin"));
//		PropertiesView.resetWidget("Credential Request Screen",new WFEditBox().label("Password:"));
//		PropertiesView.set(new WFEditBox().defaultValue("s3pAdmin"));
//		
////	deploy client :
//		WFCustomizer.runTest(new WorkFlowPackage()
//				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//				.assignToUser("supAdmin")
//				.deployToServer("true")
//				.unwiredServer("My Unwired Server"), activate(), "");
		
		//vp1: set Valid security parameter:
		DCNRequestSender.post(insertUrlValidSecurity());
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_id=2000"))).performTest();
		
	//vp2: set Invalid security Parameter:
		DCNRequestSender.post(insertUrlInavalidSecurity());
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_id=2100"))).performTest();
	
		
		
		
//	????????????????????????????????deploy server :
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "WF_server"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToUser("supAdmin"),
			insertOrUpdateCheck(), 
			"", new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
				.unwiredServer("My Unwired Server")
				.selectTo("supAdmin")
				.bcc("supAdmin")
				.cc("supAdmin")
				.subject("dept_id = 700")));
//	}
	}
		
	private CustomJsTestScript activate() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate_Screen").moveTo("Credential_Request_Screen").throughMenuItem("Open Credential Request Screen");
		s.screen("Credential_Request_Screen").menuItem("Save_Screen");
		return s;
	}
	
	private URL insertUrlValidSecurity(){
		try{
			
			String wfdcn_request = "{\"id\":\"dcntest_2002\",\"op\":\":upsert\",\"subject\":\"dept_id = 2000\",\"to\":\"supAdmin\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"dcntest_1_data_1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"2000\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "10.56.252.222", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private URL insertUrlInavalidSecurity(){
		try{
			String wfdcn_request = "{\"id\":\"dcntest_2001\",\"op\":\":delete\",\"subject\":\"dept_id = 2100\",\"to\":\"supAdmin\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"dcntest_1_data_1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":delete\",\"cols\":{\"dept_id\":\"2100\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "10.56.252.222", 8000,
					"/dcn/DCNServlet?cmd=wf&security=wrong&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}

	private CustomJsTestScript insertOrUpdateCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		return s;
	}
}

