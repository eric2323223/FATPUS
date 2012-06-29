package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import resources.testscript.Workflow.DCN.WFDCN_PartialValidRecipients_E2EHelper;
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
 * @author ffan
 */
public class WFDCN_PartialValidRecipients_E2E extends WFDCN_PartialValidRecipients_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_PartialValidRecipients_E2E</b>
	 * Generated     : <b>Nov 18, 2011 3:28:33 PM</b>
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
		
	//vp1: set a Valid recipient:
		DCNRequestSender.post(insertUrlValidTo());
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new com.sybase.automation.framework.common.QueryCriteria().clause("dept_name='myname1'"))).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("DT")
			.deployToServer("true")
			.unwiredServer("My Unwired Server"), insertCheck(), "");
			
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));	
				
	//vp2: set a invalid Recipient:
			DCNRequestSender.post(insertUrlInValidTo());
			vpManual("cdb", 0, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "Department", new com.sybase.automation.framework.common.QueryCriteria().clause("dept_name='myname2'"))).performTest();
			WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("DT")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), insertCheck(), "");
	}
	
	//the "supAdmin" is a valid recipient
	private URL insertUrlValidTo(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"101\",\"dept_name\":\"myname101\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//assume the "supAdminInvalid" is a invalid recipient
	private URL insertUrlInValidTo(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdminInvalid\",\"from\":\"ff\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"Department\",\"op\":\":upsert\",\"cols\":{\"dept_id\":\"10\",\"dept_name\":\"myname2\",\"dept_head_id\":\"501\"},\"parameters\":{}}]}]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}

	private CallBackMethod insertDCNValidTo() {
		return new CallBackMethod().receiver(DCNRequestSender.class)
			.methodName("post")
			.parameter(insertDCNValidTo())	;
	}
	
	private CallBackMethod insertDCNInValidTo() {
		return new CallBackMethod().receiver(DCNRequestSender.class)
		.methodName("post")
		.parameter(insertDCNInValidTo())	;
	}
	

	private CustomJsTestScript insertCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").checkListItem("101", "0");
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

