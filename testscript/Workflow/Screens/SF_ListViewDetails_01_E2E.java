package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.SF_ListViewDetails_01_E2EHelper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
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
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SF_ListViewDetails_01_E2E extends SF_ListViewDetails_01_E2EHelper
{
	/**
	 * Script Name   : <b>SF_ListViewDetails_01_E2E</b>
	 * Generated     : <b>Sep 27, 2011 2:47:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/27
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		WN.deployProject(new DeployOption()
     		.startParameter(Cfg.projectName)
     		.server("My Unwired Server")
     		.mode(DeployOption.MODE_REPLACE)
     		.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.addScreen("scr1");
		MainMenu.saveAll();
		WorkFlowEditor.addScreen("scr2");
		MainMenu.saveAll();
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Wf_ff_a")
				.objectQuery("findAll")
				.defaultSuccessScreen("scr1"));
		
		
		WorkFlowEditor.addWidget("scr1", new WFLview().key("Wf_ff_a")
				.lvDetailScreen("scr2"));
		sleep(0.5);
		MainMenu.saveAll();
		addCell("0","cell line 0","Wf_ff_a_aid_attribKey","100");
		addCell("1","cell line 1","Wf_ff_a_aname_attribKey","100");
		sleep(0.5);

		//2.set bDetailScreen:
		WorkFlowEditor.addWidget("scr2", new WFEditBox().label("aid:")
				.key("Wf_ff_a_aid_attribKey"));
		WorkFlowEditor.addWidget("scr2", new WFEditBox().label("aname:")
				.key("Wf_ff_a_aname_attribKey"));
		
		//deploy wf:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
		
	WFCustomizer.verifyResult(new WFClientResult().data(
			"list_items_count=2|"+
			"id=Wf_ff_a_aid_attribKey,value=1|"+
			"id=Wf_ff_a_aname_attribKey,value=Aone"));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("scr1").throughMenuItem("findall");
		s.screen("scr1").getListItemsCount();
		s.screen("scr1").moveTo("scr2").throughListItem("1->0");
		s.screen("scr2").getData("Wf_ff_a_aid_attribKey");
		s.screen("scr2").getData("Wf_ff_a_aname_attribKey");
		return s;
	}
	
	public void addCell(String order,String cellName,String cellKey,String length){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName))
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line "+order), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");
		
		DOF.getCCombo(dialog, "Key:").click();
		DOF.getCCombo(dialog, "Key:").setProperty("text", cellKey);
		
		WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), length);
		DOF.getButton(dialog,"OK").click();
		}
		sleep(1);
		MainMenu.saveAll();
		PropertiesView.restore();
	}
}
//passed BB6T 20120207
