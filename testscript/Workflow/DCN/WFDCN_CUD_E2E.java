package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.DCN.WFDCN_CUD_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DCNRequestSender;
import com.sybase.automation.framework.common.QueryCriteria;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class WFDCN_CUD_E2E extends WFDCN_CUD_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_CUD_E2E</b>
	 * Generated     : <b>Nov 15, 2011 8:45:48 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/15
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
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
		
		DCNRequestSender.post(insertUrl());
		vpManual("cdb", 1, CDBUtil.getRecordCount("antotest_xp32en", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='x'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");
		
		DCNRequestSender.post(updateUrl());
		vpManual("cdb", 1, CDBUtil.getRecordCount("autotest_xp32en", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='x'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), updateCheck(), "");
		
		DCNRequestSender.post(deleteUrl());
		vpManual("cdb", 0, CDBUtil.getRecordCount("autotest_xp32en", Cfg.projectName, "Department", new QueryCriteria().clause("dept_name='x'"))).performTest();
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), deleteCheck(), "");
		// after delete :
		WFCustomizer.verifyResult(new WFClientResult().data("found = false"));
		
		
		
		
	}
	private URL insertUrl(){
		try{
			String wfdcn_request = "{\"id\":\"dcntest_911\",\"op\":\":upsert\",\"subject\":\"dept_id = 700\",\"to\":\"supAdmin\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"dcntest_1_data_1\",\"pkg\":\"wfdcn201:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"700\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "autotest_xp32en", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private URL updateUrl(){
		try {
			String wfdcn_request = "{\"id\":\"dcntest_1111\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"DT\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"dcntest_1_data_1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"701\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "autotest_xp32en", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private URL deleteUrl(){
		try {
			String wfdcn_request = "{\"id\":\"dcntest_1111\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"DT\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"dcntest_1_data_1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"701\",\"dept_name\":\"x\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
					return new URL("HTTP", "autotest_xp32en", 8000,
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
//		s.screen("Department").checkListItem("701", "0");
		
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		return s;
	}
	
	private CustomJsTestScript deleteCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").checkListItem("701", "0");
//		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
//		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
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

