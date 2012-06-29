package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_WS_E2EHelper;
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

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
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
import component.entity.model.Modifications;
import component.entity.model.ObjectQuery;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.model.WSOperation;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.WS;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_Mixed_WS_E2E extends OLC_Op_Mixed_WS_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_WS_E2E</b>
	 * Generated     : <b>Oct 25, 2011 11:07:06 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.createWSCP(new WSCP().name("ws").wsdl(Cfg.wsdl_file), true);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		
		WN.createMbo(new WSMBO().startParameter(Cfg.projectName)
				.dataSourceType("Web Service")
				.name("UserInfoList")
				.connectionProfile("ws")
				.method("SUPWebServicesSoapPort:UserInfoList"));
		
		//create the object query with findByName
		ObjectQuery oq = new ObjectQuery().name("findByName")
			.parameter("name,string(300),true,name")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->UserInfoList");
			new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		
		WN.createMbo(new WSMBO().startParameter(Cfg.projectName)
				.dataSourceType("Web Service")
				.connectionProfile("ws")
				.name("UserInfoByID")
				.method("SUPWebServicesSoapPort:UserInfoByID"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "UserInfoByID");
		mbo.setLoadArgument(new LoadArgument().name("userID").propagateTo("id"));
		
		WN.changeCacheGroup(Cfg.projectName, "UserInfoByID", "Default (Default)", "online");
		MainMenu.saveAll();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("ws,ws"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "UserInfoByID");
		WorkFlowEditor.dragMbo(Cfg.projectName, "UserInfoList");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("id").newKey("idkey,string"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("name").newKey("namekey,string"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findById")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("UserInfoByID")
				.defaultSuccessScreen("UserInfoByID")
				.objectQuery("findByParameter")
				.parametermapping("userID,idkey"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findByName")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("UserInfoList")
				.defaultSuccessScreen("UserInfoListDetail")
				.objectQuery("findByName")
				.parametermapping("name,namekey"));
		
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		WFCustomizer.verifyResult(new WFClientResult().data("value=2377,id=UserInfoList_id_attribKey|"+
				"value=yyyyy,id=UserInfoList_name_attribKey|"+
				"value=2337,id=UserInfoByID_id_attribKey|"+
				"value=hhh,id=UserInfoByID_name_attribKey"));
	
	}
	
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("namekey", "yyyyy");
		s.screen("Start").moveTo("UserInfoListDetail").throughMenuItem("findByName");
		s.screen("UserInfoListDetail").getData("UserInfoList_id_attribKey");
		s.screen("UserInfoListDetail").getData("UserInfoList_name_attribKey");
		s.screen("UserInfoListDetail").moveTo("Start").throughMenuItem("Back");
		
		s.screen("Start").setData("idkey", "2337");
		s.screen("Start").moveTo("UserInfoByID").throughMenuItem("findById");
		s.screen("UserInfoByID").moveTo("UserInfoByIDDetail").throughListItem("0");
		s.screen("UserInfoByIDDetail").getData("UserInfoByID_id_attribKey");
		s.screen("UserInfoByIDDetail").getData("UserInfoByID_name_attribKey");
		
		return s;
	}
}
//improved passed BB6T 20120423
