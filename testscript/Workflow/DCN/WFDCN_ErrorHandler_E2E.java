package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import resources.testscript.Workflow.DCN.WFDCN_ErrorHandler_E2EHelper;
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

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
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
import component.entity.model.Email;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WFDCN_ErrorHandler_E2E extends WFDCN_ErrorHandler_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_ErrorHandler_E2E</b>
	 * Generated     : <b>Nov 18, 2011 1:03:31 PM</b>
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
		
		//vp1:try to insert same data twice
		DCNRequestSender.post(insertUrl());
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='x'"))).performTest();
		DCNRequestSender.post(insertUrl());
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");
		
		//VP2:try to undate the record that is not exist:
		DCNRequestSender.post(updateUrl());
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), updateCheck(), "");
		
		//VP3:Try to delete the record ""that is not exist :
		DCNRequestSender.post(deleteUrl());
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), deleteCheck(), "");
		
//		VP4: not using correct credential info
		DCNRequestSender.post(insertUrlWrongCredential());
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='y'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");
		
		
//		VP5: is using wrong domain info
		DCNRequestSender.post(insertUrlWrongDomain());
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='c'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");
		
//		vp6: is not using compliant format
		
		
		
		
//		vp7: contains invalid request
		DCNRequestSender.post(insertUrlWithInvalidRequest());
		vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='d'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");	
		
	}
	
	//assume "Invalidwf" is a invalid pkg to set request Invalid
	private URL insertUrlWithInvalidRequest(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"Invaildwf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"720\",\"dept_name\":\"d\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//wrong Credential,assume "mysupAdmin" is a wrong username:
	private URL insertUrlWrongCredential(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"705\",\"dept_name\":\"y\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=wrong&username=mysupAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//assume "wrong" is a wrong Domain:
	private URL insertUrlWrongDomain(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"710\",\"dept_name\":\"c\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=wrong&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private URL insertUrl(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"700\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	// ff:assume the record id ="701" is not exist here:
	private URL updateUrl(){
		try {
			String wfdcn_request = "{\"id\":\"2\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"DT\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"2\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"701\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ff:assume the record id ="701" is not exist here:
	private URL deleteUrl(){
		try {
			String wfdcn_request = "{\"id\":\"3\",\"op\":\":delete\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"3\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"701\"},\"parameters\":{}}]}]}";
					return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private CallBackMethod insertDCN() {
		return new CallBackMethod().receiver(DCNRequestSender.class)
			.methodName("post")
			.parameter(insertUrl())	;
	}
	
	private CallBackMethod updateDCN() {
		return new CallBackMethod().receiver(DCNRequestSender.class)
			.methodName("get")
			.parameter(updateUrl())	;
	}

	private CustomJsTestScript insertCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		return s;
	}
	
	private CustomJsTestScript updateCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		return s;
	}
	
	private CustomJsTestScript deleteCheck() {
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

