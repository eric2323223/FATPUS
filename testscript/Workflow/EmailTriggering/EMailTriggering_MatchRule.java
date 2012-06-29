package testscript.Workflow.EmailTriggering;

import java.awt.Point;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_MatchRuleHelper;

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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_MatchRule extends EMailTriggering_MatchRuleHelper
{
	/**
	 * Script Name   : <b>EMailTriggering_MatchRule</b>
	 * Generated     : <b>Oct 16, 2011 10:21:34 PM</b>
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
		.name("myWF")
		.option(WorkFlow.SP_SERVER_INIT)
		.mbo("Department")
		.objectQuery("findAll"));

		PropertiesView.addMatchingRulesOfEmail("Subject,Test\\(\\d+\\)");

		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","Subject,Test\\(\\d+\\)",PropertiesView.getMatchingRulesOfEmail().getKey()).performTest();

		System.out.print(PropertiesView.getAllTypeInMatchingRule(false));
		vpManual("getmatchrule","To,From,CC,Body",PropertiesView.getAllTypeInMatchingRule(false)).performTest();

		PropertiesView.addMatchingRulesOfEmail("From,Sender");
		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","From,Sender",PropertiesView.getMatchingRulesOfEmail().getKey().split(":")[1]).performTest();

		PropertiesView.editMatchingRulesOfEmail("Subject","Subject,Test(\\d+)");
		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","Subject,Test(\\d+)",PropertiesView.getMatchingRulesOfEmail().getKey().split(":")[0]).performTest();
		MainMenu.saveAll();
		
		
		MainMenu.saveAll();
		PropertiesView.deleteMatchingRulesOfEmail("Subject");
		MainMenu.saveAll();
		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","From,Sender",PropertiesView.getMatchingRulesOfEmail().getKey()).performTest();

		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));


		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","From,Sender",PropertiesView.getMatchingRulesOfEmail().getKey()).performTest();

		PropertiesView.editMatchingRulesOfEmail("From","From,(");
		System.out.println("getMatchingRule="+PropertiesView.getMatchingRulesOfEmail().getKey());
		vpManual("getmatchrule","From,(",PropertiesView.getMatchingRulesOfEmail().getKey().split(":")[0]).performTest();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		
	}
}
//passed
