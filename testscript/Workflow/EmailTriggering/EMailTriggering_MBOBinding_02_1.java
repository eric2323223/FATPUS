package testscript.Workflow.EmailTriggering;
import java.awt.Point;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_MBOBinding_02_1Helper;
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
import component.entity.WFFlowDesigner;
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
public class EMailTriggering_MBOBinding_02_1 extends EMailTriggering_MBOBinding_02_1Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_MBOBinding_02_1</b>
	 * Generated     : <b>Oct 18, 2011 1:37:04 AM</b>
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
		
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKeyMy1")
			.parameter("dept_id,int,false,dept_id:dept_name,string(40),false,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id AND x.dept_name = :dept_name")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		WN.useProject("wf_2");
		
		WN.createWorkFlow(new WorkFlow().startParameter("wf_2")
				.name("myWF"));

		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.project(Cfg.projectName)
			.mbo("Department")
			.objectQuery("findAll"));

		PropertiesView.setEmailMbo("Department", Cfg.projectName);
		vpManual("hasObjectQuery","true",PropertiesView.ifhasObjectQuery("findByPrimaryKeyMy1")).performTest();
		PropertiesView.setObjectQueryOnly("findByPrimaryKeyMy1");

		System.out.print(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","Body,,,,dept_id:Body,,,,dept_name",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();

		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow("wf_2","myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x,point.y));
		PropertiesView.clickTab("General");

		System.out.print(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","Body,,,,dept_id:Body,,,,dept_name",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();

		System.out.print(PropertiesView.getObjectQueryOfEmailMbo());
		vpManual("getExtractionRules","findByPrimaryKeyMy1",PropertiesView.getObjectQueryOfEmailMbo()).performTest();
		
	}
}
//passed
