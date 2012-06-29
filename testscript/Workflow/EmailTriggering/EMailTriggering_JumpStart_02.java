package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_JumpStart_02Helper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_JumpStart_02 extends EMailTriggering_JumpStart_02Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_JumpStart_02</b>
	 * Generated     : <b>Oct 17, 2011 11:13:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKeyMy")
//			.parameter("dept_id,int,false,dept_id:dept_name,string(40),false,dept_name")
//			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id AND x.dept_name = :dept_name")
//			.returnType(ObjectQueryWizard.RT_SINGLE)
//			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
//		new ObjectQueryWizard().create(oq, new WizardRunner()); 
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF"));
//		
//		WorkFlowEditor.addStartingPoint(new StartPoint()
//			.type(WorkFlow.SP_SERVER_INIT)
//			.name("Department")
//			.project("wf")
//			.mbo("Department")
//			.objectQuery("findAll"));
		
	//Jump Start.......................
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKeyMy")
				.from("imo@localhost")
				.to("test@localhost")
				.cc("test1@localhost")
				.subject("NewSub2End")
				.received("2009-07-20")
				.body("<name>[name]</name><value>{value}</value>")
				.subjectMatchingRule("Sub")
				.bodyMatchingRule("</name>")
				.setParameterValue("dept_id,Subject,NewSub"));
		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("extractiorules","Subject,NewSub,,,dept_id",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("General");
	
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("extractiorules","Subject,NewSub,,,dept_id",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]).performTest();
	}
}
//passed
