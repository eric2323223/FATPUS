package testscript.Workflow.DCN;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import resources.testscript.Workflow.DCN.WFDCN_AllDataTypes_E2E_ffHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DCNRequestSender;
import com.sybase.automation.framework.common.QueryCriteria;

import component.entity.PropertiesView;
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
import component.entity.model.Email;
import component.entity.model.ObjectQuery;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WFDCN_AllDataTypes_E2E_ff extends WFDCN_AllDataTypes_E2E_ffHelper
{
	/**
	 * Script Name   : <b>WFDCN_AllDataTypes_E2E_ff</b>
	 * Generated     : <b>Nov 23, 2011 9:28:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/23
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
			//Note:	device name = supAdmin
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.name("AllMBODataType")
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from AllMBODataType"));
		
		//create the objectQuery "FindByPrimaryKey" for "AllD":
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
			.parameter("id,int,false,id")
			.queryDefinition("SELECT x.* FROM AllMBODataType x WHERE x.id = :id")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->AllMBODataType");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		WN.createCacheGroup(new CacheGroup()
				.startParameter(Cfg.projectName)
				.name("dcn")
				.type(CachePolicy.DCN));
		WN.changeCacheGroup(Cfg.projectName, "AllMBODataType", "Default (Default)", "dcn");	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
	//create server_initial
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("WF_server")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("AllMBODataType")
				.objectQuery("findByPrimarykey")
				.to("supAdmin")
				.subject("id=1")
				.toMatchingRule("supAdmin")
				.setParameterValue("id,Subject,id="));
		
	//create client.active.cretential
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				);
	//set screen:
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllMBODataType");
		WorkFlowEditor.link("Activate Screen", "Credential Request Screen");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("AllMBODataType")
				.objectQuery("findAll")
				.defaultSuccessScreen("AllMBODataType"));
		
	//set credential default value ,not need to input value in device:
		PropertiesView.resetWidget("Credential Request Screen",new WFEditBox().label("Username:"));
		PropertiesView.set(new WFEditBox().defaultValue("supAdmin"));
		PropertiesView.resetWidget("Credential Request Screen",new WFEditBox().label("Password:"));
		PropertiesView.set(new WFEditBox().defaultValue("s3pAdmin"));
		
	//deploy client :
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("supAdmin")
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), activate(), "");
//		
////////////////////////////////1.create record:
		DCNRequestSender.post(insertUrl());
		vpManual("cdb", 1, CDBUtil.getRecordCount("10.56.252.222", Cfg.projectName, "AllMBODataType", new QueryCriteria().clause("type_INT=1"))).performTest();
		
	//deploy server :
//		WFCustomizer.runTest(new WorkFlowPackage()
//			.startParameter(WN.filePath(Cfg.projectName, "WF_server"))
//			.unwiredServer("My Unwired Server")
//			.deployToServer("true")
//			.assignToUser("supAdmin"),
//			insertOrUpdateCheck(), 
//			"", new CallBackMethod().receiver(WorkFlowEditor.class)
//				.methodName("sendNotification")
//				.parameter(new Email()
//				.unwiredServer("My Unwired Server")
//				.selectTo("supAdmin")
//				.bcc("supAdmin")
//				.cc("supAdmin")
//				.subject("id=1")));
//		
//		// after insert :
//		WFCustomizer.verifyResult(new WFClientResult().data("id=AllMBODataType_type_it_attribKey,value =1"));
//	//ok=========================================================================
	}

	private CustomJsTestScript activate() {
					CustomJsTestScript s = new CustomJsTestScript();
					s.screen("Activate_Screen").moveTo("Credential_Request_Screen").throughMenuItem("Open Credential Request Screen");
					s.screen("Credential_Request_Screen").menuItem("Save_Screen");
					return s;
			}
				
	private URL insertUrl(){
					try{
						String wfdcn_request = "{\"id\":\"dcntest_9216\",\"op\":\":upsert\",\"subject\":\"id=1\",\"to\":\"supAdmin\",\"from\":\"yongli\",\"read\":false,\"priority\":true,\"body\":\"WF%20DCN%20Test%20Body\",\"data\":[{\"id\":\"wfdcn201_1_data_1\",\"pkg\":\"wf:1.0\",\"messages\":[{\"id\":\"1\",\"mbo\":\"AllMBODataType\",\"op\":\":upsert\",\"cols\":{\"id\":\"1\",\"type_INT\":\"1\",\"type_LONG\":\"1\",\"type_STRING\":\"aaa\",\"type_DATE\":\"2010-01-01\",\"type_DATETIME\":\"2010-01-01T00:00:00\",\"type_TIME\":\"00:00:00\",\"type_DECIMAL\":\"1.000\",\"type_FLOAT\":\"1.0\",\"type_DOUBLE\":\"1.0\",\"type_BOOLEAN\":\"1\",\"type_BINARY\":\"0x616161\"},\"parameters\":{}}]}]}";
						return new URL("HTTP", "10.56.252.222", 8000,
								"/dcn/DCNServlet?cmd=wf&security=admin&domain=default&username=supAdmin&password=s3pAdmin&dcn_request=" + URLEncoder.encode(wfdcn_request));
					}catch(MalformedURLException e){
						e.printStackTrace();
						return null;
					}
			}
	private CustomJsTestScript insertOrUpdateCheck() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllMBODataTypeDetail").getData("AllMBODataType_type_it_attribKey");
		return s;
	}
	
	
	}

