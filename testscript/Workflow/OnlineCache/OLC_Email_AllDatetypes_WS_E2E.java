package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_AllDatetypes_WS_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.LoadArgument;
import component.entity.model.WFEditBox;
import component.entity.model.WFParameterMatchingRule;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_AllDatetypes_WS_E2E extends OLC_Email_AllDatetypes_WS_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_AllDatetypes_WS_E2E</b>
	 * Generated     : <b>Nov 24, 2011 8:38:37 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
	//	EE.createWSCP(new WSCP().name("ws").wsdl(Cfg.wsdl_file), true);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
		WN.createMbo(new WSMBO().startParameter(Cfg.projectName)
			.dataSourceType("Web Service")
			.connectionProfile("ws")
			.name("User")
			.method("SUPWebServicesSoapPort:UserInfoByID"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "User");
		mbo.setLoadArgument(new LoadArgument().name("userID").propagateTo("id"));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("ws,ws"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("User")
				.objectQuery("findByParameter")
				.subject("id=1")
				.subjectMatchingRule("id=")
				.setParameterValue("userID,Subject,id="));
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser(Cfg.deviceUser)
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.server_dt_icon.Script", sendNotification());
		
		WFCustomizer.verifyResult(new WFClientResult().data("value=2337, id=User_id_attribKey|"+
				"value=hhh, id=User_name_attribKey|"+
				"value=sss, id=User_address_attribKey|"+
				"value=1999-01-01, id=User_birthday_attribKey|"+
				"value=0, id=User_sqlcode_attribKey"));
	
	}
	
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to(Cfg.deviceUser).subject("id=2337"));
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("User").moveTo("UserDetail").throughListItem("0");
		s.screen("UserDetail").getData("User_id_attribKey");
		s.screen("UserDetail").getData("User_name_attribKey");
		s.screen("UserDetail").getData("User_address_attribKey");
		s.screen("UserDetail").getData("User_birthday_attribKey");
		s.screen("UserDetail").getData("User_sqlcode_attribKey");
		return s;
	}
}
//improved passed BB6T 20120423
