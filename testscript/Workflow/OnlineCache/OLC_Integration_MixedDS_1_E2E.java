package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Integration_MixedDS_1_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.Operation;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.model.WSOperation;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Integration_MixedDS_1_E2E extends OLC_Integration_MixedDS_1_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Integration_MixedDS_1_E2E</b>
	 * Generated     : <b>Nov 24, 2011 11:35:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
		//1.create a SAP :
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
			.dataSourceType("SAP")
			.name("Bank")
			.connectionProfile("sap")
			.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
			.parameter("BANK_CTRY,in")
			.parameter("BANK_LIST,out")
			.parameter("BANK_LIST,in")
			.parameterValue("BANK_CTRY,AM"));
		
		MBOProperties mbosap = new MBOProperties(Cfg.projectName, "Bank");
		mbosap.setLoadArgument(new LoadArgument().name("BANK_CTRY")
				.propagateTo("BANK_CTRY"));
		
//		//2.create a DB(department) 
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		MBOProperties mbodb = new MBOProperties(Cfg.projectName, "Department");
		mbodb.setMboDefinition("sql", "SELECT * FROM sampledb.dba.department where dept_name=:dept_name");
		mbodb.setLoadArgument(new LoadArgument().name("dept_name").propagateTo("dept_name"));
		
		//3.create a WS 
		EE.createWSCP(new WSCP().name("ws").wsdl(Cfg.wsdl_file), true);
		WN.createMbo(new WSMBO().startParameter(Cfg.projectName)
				.dataSourceType("Web Service")
				.connectionProfile("ws")
				.name("UserInfoByID")
				.method("SUPWebServicesSoapPort:UserInfoByID"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "UserInfoByID");
		mbo.setLoadArgument(new LoadArgument().name("userID").propagateTo("id"));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb")
				.serverConnectionMapping("ws,ws")
				.serverConnectionMapping("sap,sap")
				);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "UserInfoByID");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
		
		//1.ws>>>>>>>>>>
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("ws_id").newKey("ws_idkey,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("OpenWSByID")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("UserInfoByID")
				.defaultSuccessScreen("UserInfoByID")
				.objectQuery("findByParameter")
				.parametermapping("userID,ws_idkey"));
		
		//2.SAP >>>>>>>>>
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("BANK_CTRY").newKey("BANK_CTRYkey,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findallBANK")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.defaultSuccessScreen("Bank")
				.objectQuery("findByParameter")
				.parametermapping("BANK_CTRY,BANK_CTRYkey"));
		
		WorkFlowEditor.addMenuItem("BankDetail", new WFScreenMenuItem().name("Open Start")
				.type("Open")
				.screen("Start"));
		
		//3.DB>>>
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_name").newKey("dept_namekey,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("OpenDeptByname")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.defaultSuccessScreen("Department")
				.objectQuery("findByParameter")
				.parametermapping("dept_name,dept_namekey"));
		WorkFlowEditor.addMenuItem("DepartmentDetail", new WFScreenMenuItem().name("Open Start")
				.type("Open")
				.screen("Start"));
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=200|"+
				"id=Bank_BANK_CTRY_attribKey,value=AM|"+
				"id=UserInfoByID_userID_attribKey,value=2377"));	
		
}
	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		
		//1.DB department
		s.screen("Start").setData("dept_namekey", "Sales");
		s.screen("Start").moveTo("Department").throughMenuItem("OpenDeptByname");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").moveTo("Start").throughMenuItem("Open Start");
		
		//2.SAP Bank>>>>>>>>>>
		s.screen("Start").setData("BANK_CTRYkey", "AM");
		s.screen("Start").moveTo("Bank").throughMenuItem("findallBANK");
		s.screen("Bank").moveTo("BankDetail").throughListItem("0");
		s.screen("BankDetail").getData("Bank_BANK_CTRY_attribKey");
		s.screen("BankDetail").moveTo("Start").throughMenuItem("Open Start");
		
		//2.WS UserInfoByIDdelete1
		s.screen("Start").setData("ws_idkey", "2337");
		s.screen("Start").moveTo("UserInfoByID").throughMenuItem("OpenWSByID");
		s.screen("UserInfoByID").moveTo("UserInfoByIDDetail").throughListItem("0");
		s.screen("UserInfoByIDDetail").getData("UserInfoByID_id_attribKey");
		return s;
	}	
}
//passed BB6T 20120424

