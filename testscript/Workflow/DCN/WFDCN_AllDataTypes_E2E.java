package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import resources.testscript.Workflow.DCN.WFDCN_AllDataTypes_E2EHelper;
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
public class WFDCN_AllDataTypes_E2E extends WFDCN_AllDataTypes_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_AllDataTypes_E2E</b>
	 * Generated     : <b>Nov 18, 2011 1:42:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
//				.name("T_olc")
//				.dataSourceType("JDBC")
//				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from T_olc"));
//		WN.createCacheGroup(new CacheGroup()
//				.startParameter(Cfg.projectName)
//				.name("dcn")
//				.type(CachePolicy.DCN));
//		WN.changeCacheGroup(Cfg.projectName, "T_olc", "Default (Default)", "dcn");	
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_SERVER_INIT)
//				.option(WorkFlow.SP_ACTIVATE)
//				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
//				.project(Cfg.projectName)
//				.mbo("T_olc")
//				.objectQuery("findAll")
//				.from("ffan_xp32en_2")
//				.body("WF")
//				.subject("updated")
//				.subjectMatchingRule("updated")
//				.to("DT")
//				.toMatchingRule("DT")
//				);
//		WorkFlowEditor.link("Activate Screen", "Credential Request Screen");
//		WFCustomizer.runTest(new WorkFlowPackage()
//				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//				.assignToUser("DT")
//				.deployToServer("true")
//				.unwiredServer("My Unwired Server"), activate(), "");
		
		
		//vp1:create one or many new records:
		DCNRequestSender.post(insertUrl());
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "T_olc", new QueryCriteria().clause("a='2'"))).performTest();
		vpManual("cdb", 1, CDBUtil.getRecordCount("ffan_xp32en_2", Cfg.projectName, "T_olc", new QueryCriteria().clause("a='3'"))).performTest();

//		WFCustomizer.runTest(new WorkFlowPackage()
//			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//			.assignToUser("DT")
//			.deployToServer("true")
//			.unwiredServer("My Unwired Server"), insertCheck(), "");
//
//		// if successed after insert :
//		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=3"));
		}
	
	private CustomJsTestScript activate() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate_Screen").moveTo("Credential_Request_Screen").throughMenuItem("Open Credential Request Screen");
		s.screen("Credential_Request_Screen").setData("cc_username","supAdmin");
		s.screen("Credential_Request_Screen").setData("cc_password","s3pAdmin");
		s.screen("Credential_Request_Screen").menuItem("Save_Screen");
		return s;
	}
	
	//ff:A data include one or many dcn requests:
	private URL insertUrl(){
		try{
			String wfdcn_request = "{\"id\":\"1\",\"op\":\":upsert\",\"subject\":\"updated\",\"to\":\"supAdmin\",\"from\":\"ffan_xp32en_2\",\"read\":false,\"priority\":true,\"body\":\"WF\",\"data\":[" +
					"{\"id\":\"1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"T_olc\",\"op\":\":upsert\",\"cols\":{\"a\":\"2\",\"b\":\"true\",\"c\":\"12:33:33\",\"d\":\"2011-10-23T12:12:12\"},\"parameters\":{}}]}" +
					"{\"id\":\"2\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"2\",\"mbo\":\"T_olc\",\"op\":\":upsert\",\"cols\":{\"a\":\"3\",\"b\":\"true\",\"c\":\"12:23:33\",\"d\":\"2011-10-21T12:12:12\"},\"parameters\":{}}]}" +
					"]}";
			return new URL("HTTP", "ffan_xp32en_2", 8000,
					"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
		}catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private CallBackMethod insertDCN() {
		return new CallBackMethod().receiver(DCNRequestSender.class)
		.methodName("post")
		.parameter(insertUrl())	;
	}
	
	private CustomJsTestScript insertCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		//have 3 records in T_olc after insert
		s.screen("T_olc").getListItemsCount();
		return s;
	}
}

