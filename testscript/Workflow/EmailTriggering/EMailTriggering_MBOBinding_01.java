package testscript.Workflow.EmailTriggering;
import java.awt.Point;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_MBOBinding_01Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_MBOBinding_01 extends EMailTriggering_MBOBinding_01Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_MBOBinding_01</b>
	 * Generated     : <b>Oct 17, 2011 11:20:40 PM</b>
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
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Department")
			.objectQuery("findAll"));
		
		PropertiesView.setEmailMbo("Department", "wf");
		
		vpManual("hasObjectQuery","true",PropertiesView.ifhasObjectQuery("findByPrimaryKey")).performTest();
		PropertiesView.setObjectQueryOnly("findByPrimaryKey");
		
		System.out.print(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","Body,,,,dept_id",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		PropertiesView.clearMbo();
		
		System.out.print(PropertiesView.getObjectQueryOfEmailMbo());
		vpManual("getExtractionRules","",PropertiesView.getObjectQueryOfEmailMbo()).performTest();
		
		System.out.println(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","null",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		PropertiesView.setObjectQuery("Department","findByPrimaryKey");
		
		vpManual("getKey","dept_id1",PropertiesView.getKeyWhenEditExtractionRulesofStartPoint("Body")).performTest();
		PropertiesView.editExtractionRulesofStartPoint("Body","Subject,Test\\(,\\),dept_id1");
		System.out.println(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","Subject,Test\\(,\\),,dept_id1",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		
		sleep(2);
		WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x,point.y));
		PropertiesView.clickTab("General");
		
		System.out.println(PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("getExtractionRules","Subject,Test\\(,\\),,dept_id1",PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		System.out.println(PropertiesView.getObjectQueryOfEmailMbo());
		vpManual("getExtractionRules","findByPrimaryKey",PropertiesView.getObjectQueryOfEmailMbo()).performTest();
	}
}
//passed
