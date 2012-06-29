package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.One2OneRelation_Ctrl_Textbox_Binding_211Helper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFHtmlView;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class One2OneRelation_Ctrl_Textbox_Binding_211 extends One2OneRelation_Ctrl_Textbox_Binding_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 24, 2011 9:58:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test One2OneRelation_Ctrl_Textbox_Binding
		WN.useProject(Cfg.projectName);
		
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_table_forcontrol.sql");
	
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->forcontrol (dba)"));
		
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("myemployee")
				.mapping("dept_id,id")
				.composite("true")
				.target("Forcontrol")
				.type(Relationship.TYPE_OTO));
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("ctrlbinding")
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.jumpStart(new WorkFlow()
		.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("deptid=2")
				.subjectMatchingRule("deptid=")
				.setParameterValue("dept_id,Subject,deptid="));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		WorkFlowEditor.addScreen("linktoemail1");
		WorkFlowEditor.link("linktoemail", "linktoemail1");
		
		//add checkbox
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail", new WFCheckbox().label("checkbox:")
				.newKeyBindMbo("checkboxkey,bool,Forcontrol,type_BOOLEAN"));	
		PropertiesView.clickTab("General");
//		String s = PropertiesView.verifykeylist();
		vpManual("keylist1","checkboxkey,",PropertiesView.verifykeylist()).performTest();
		//end
		
		//add editbox		
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail", new WFEditBox().label("Editbox:")
				.newKeyBindMbo("editboxkey,DateTime,Forcontrol,type_TIME"));
		PropertiesView.clickTab("General");
//		String s = PropertiesView.verifykeylist();
		vpManual("keylist2","editboxkey,",PropertiesView.verifykeylist()).performTest();
		//end
		
		//add choice
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail", new WFChoice().label("choice:")
				.newKeyBindMbo("choicekey,DateTime,Forcontrol,type_DATE"));
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		vpManual("keylist3","",PropertiesView.verifykeylist()).performTest();
		//end
//		
//		//add slider
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail", new WFSlider().label("slider:")
				.newKeyBindMbo("sliderkey,int,Forcontrol,id"));	
		PropertiesView.clickTab("General");
//		String s = PropertiesView.verifykeylist();
		vpManual("keylist4","sliderkey,",PropertiesView.verifykeylist()).performTest();
		//end
		
		//add signature
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail", new WFSignature().label("signature:")
				.newKeyBindMbo("signaturekey,string,Forcontrol,type_STRING"));
		PropertiesView.clickTab("General");
//		String s = PropertiesView.verifykeylist();
		vpManual("keylist5","signaturekey,",PropertiesView.verifykeylist()).performTest();
			//end
		
		//add html
		WorkFlowEditor.addWidget(Cfg.projectName, "ctrlbinding.xbw", "linktoemail1", new WFHtmlView());
		PropertiesView.clickTab("General");
//		String s = PropertiesView.verifykeylist();
		vpManual("keylist6","signaturekey,",PropertiesView.verifykeylist()).performTest();
		//end
	}
}

