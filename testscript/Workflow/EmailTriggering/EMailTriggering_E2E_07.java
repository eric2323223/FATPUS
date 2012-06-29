package testscript.Workflow.EmailTriggering;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_07Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_E2E_07 extends EMailTriggering_E2E_07Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_07_2</b>
	 * Generated     : <b>Oct 20, 2011 12:17:29 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/20
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	    WN.useProject(Cfg.projectName);

		 EE.runSQL(new ScrapbookCP().database("sampledb")
		 .type("Sybase_ASA_12.x").name("My Sample Database"),
		 GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/EmailTriggering/setup/ff_wf_temp_date.sql");

		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_temp_date (dba)");

		ObjectQuery oq = new ObjectQuery().name("mydate")
		.parameter("dept_date,DATE,true,dept_date")
		.returnType(ObjectQueryWizard.RT_SINGLE)
		.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Ff_wf_temp_date");
		new ObjectQueryWizard().create(oq, new WizardRunner());

		WN.deployProject(new DeployOption()
		.startParameter(Cfg.projectName)
		.server("My Unwired Server")
		.mode(DeployOption.MODE_REPLACE)
		.serverConnectionMapping("My Sample Database,sampledb"));

		//vp1:modify the transformation Rules format as "yyyy-MM-dd"
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
		.name("myWF")
		.option(WorkFlow.SP_SERVER_INIT)
		.project(Cfg.projectName)
		.mbo("Ff_wf_temp_date")
		.objectQuery("mydate")
		.subject("deptdate")
		.subjectMatchingRule("dept")
		.setParameterValue("dept_date,Subject,dept,,yyyy-MM-dd"));
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();

		runTest("dept2011-10-01");
		vp();

		deleteGeneratedWorkFlowFolder();
		
		
		//vp2:modify the transformation Rules format as ""
		PropertiesView.jumpStart(new WorkFlow().setParameterValue("dept_date,Subject,dept,,empty"));
		MainMenu.saveAll();

		runTest("dept2011-10-01");
		vp();

		deleteGeneratedWorkFlowFolder();
		
		//vp3:modify the transformation Rules format as "yyyy-MM-ddTHH:mm:ss"
		PropertiesView.jumpStart(new WorkFlow().setParameterValue("dept_date,Subject,dept,,yyyy-MM-ddTHH:mm:ss"));
		MainMenu.saveAll();

		runTest("dept2011-10-01T12:00:00");
		vp();

		}

		private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("FfwftempdateDetail").getData("Ff_wf_temp_date_dept_id_attribKey");
		s.screen("FfwftempdateDetail").getData("Ff_wf_temp_date_dept_date_attribKey");
		return s;
		}

		private void runTest(String subject){
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser(Cfg.deviceUser),
		customTestScript(),
//		"tplan.Workflow.iconcommon.BB.server_dt_icon2.Script",
		new CallBackMethod().receiver(WorkFlowEditor.class)
		.methodName("sendNotification")
		.parameter(new Email()
		.selectTo(Cfg.deviceUser)
		.subject(subject)));
		}

		private void vp(){
		WFCustomizer.verifyResult(new WFClientResult().data(
		"id=Ff_wf_temp_date_dept_id_attribKey,value=1|" +
		"id=Ff_wf_temp_date_dept_date_attribKey,value=2011-10-01T12:00:00"));
		}
		
		public static void deleteGeneratedWorkFlowFolder() {
			//delete the Generated Workflow->"+"myWF1
			DOF.getWNTree().click(RIGHT,atPath(WN.filePath(Cfg.projectName, "Generated Workflow->"+"myWF")));
			DOF.getContextMenu().click(atPath("Delete"));
			DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
			sleep(3);
			}
		
		}

//passed BB6T 20120216:need to solve (send WF one by one ,or has error "getHttpd")