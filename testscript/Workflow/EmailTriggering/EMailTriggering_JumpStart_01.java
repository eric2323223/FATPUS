package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_JumpStart_01Helper;
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
public class EMailTriggering_JumpStart_01 extends EMailTriggering_JumpStart_01Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_JumpStart_01</b>
	 * Generated     : <b>Oct 17, 2011 4:05:22 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/17
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKeyMy")
			.parameter("dept_id,int,false,dept_id:dept_name,string(40),false,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id AND x.dept_name = :dept_name")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));

		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.name("Department")
			.project(Cfg.projectName)
			.mbo("Department")
			.objectQuery("findAll"));
//		
//		//Jump Start.......................
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKeyMy")
				.from("imo@localhost")
				.to("test@localhost")
				.cc("test1@localhost")
				.subject("Sub")
				.received("2009-07-20")
				.body("<name>[name]</name><value>{value}</value>")
				.fromMatchingRule("i")
				.verifyFrom("From,i.*", true)
				.setParameterValue("dept_id,Subject,Sub\\(")
		);
//		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]);
		vpManual("extractiorules","Subject,Sub\\(,,,dept_id",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]).performTest();
	
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKeyMy")
				.from("imo@localhost")
				.to("test@localhost")
				.cc("test1@localhost")
				.subject("Sub(1)End")
				.received("2009-07-20")
				.body("<name>[name]</name><value>{value}</value>")
				.fromMatchingRule("imo")
				.verifyFrom("From,imo.*", true)
				.setParameterValue("dept_name1,Body,<name>["));
		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[1]);
		vpManual("extractiorules","Body,<name>[,,,dept_name1",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[1]).performTest();
	
		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("General");
	
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("extractiorules","Body,,,,dept_id1:Body,<name>[,,,dept_name1",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
	}
}
//passed
