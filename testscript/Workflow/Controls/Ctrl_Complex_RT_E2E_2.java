package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Complex_RT_E2E_2Helper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFHtmlView;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Complex_RT_E2E_2 extends Ctrl_Complex_RT_E2E_2Helper
{
	/**
	 * Script Name   : <b>Ctrl_Complex_RT_E2E_2</b>
	 * Generated     : <b>Dec 19, 2011 2:12:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/19
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
//create the database table "mycontrols",has record:"1,7,sybase,true,6,21x51,186x52,203x52,-1x-1,132x31,110x68,92x91,86x96,-1x-1,126x65,172x80,192x88,199x91,baijing,sybase,2009-12-19,china,12:12:12,123,<b><i>sybase</i></b>"
		
////	EE.runSQL(new ScrapbookCP().database("sampledb")
////	.type("Sybase_ASA_12.x").name("My Sample Database"), 
////	GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/crete_table.sql"
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->mycontrols (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
//		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mycontrols")
				.objectQuery("findByPrimaryKey")
				.subject("id=1")
				.subjectMatchingRule("id=")
				.setParameterValue("id,Subject,id="));
		
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen");
		
		
		//1.edit
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("edit_id:")
				.newKeyBindMbo("edit_id,int,Mycontrols,id"));
		//2.Dynamic choice1
		WorkFlowEditor.showPropertiesViewInSD("myscreen");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Mycontrols,number");
		WorkFlowEditor.addWidget("myscreen", new WFChoice().label("choic1:")
				.labelPosition("RIGHT")
				.newKey("choice1,int")
				.logicalType("TEXT")
				.option("Dynamic,key2.key1,key2.key1")
		         );
		//3.static choice2
		WorkFlowEditor.addWidget("myscreen", new WFChoice().label("choic2:")
				.labelPosition("RIGHT")
				.newKey("choice2,int")
				.logicalType("NUMERIC")
				.ifReadonly(true)
				.option("Static,item1,1:item2,2"));
		//4.CKBOX1
		WorkFlowEditor.addWidget("myscreen", new WFCheckbox().label("ckbox1:")
				.labelPosition("LEFT")
				.newKeyBindMbo("ckbox1,bool,Mycontrols,gender"));
	    //5.Slider
		WorkFlowEditor.addWidget("myscreen", new WFSlider().label("sld:")
				.labelPosition("LEFT")
				.ifReadonly(true)
				.validationMessage("slider validation message")
				.newKeyBindMbo("sld,int,Mycontrols,age"));
		//6.Signature
		WorkFlowEditor.addWidget("myscreen", new WFSignature().label("sig1:")
				.labelPosition("LEFT")
				.newKeyBindMbo("sig,string,Mycontrols,sign"));
		
		MainMenu.saveAll();
		
		
//	1.edit1
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed1_city:")
				.newKeyBindMbo("city,string,Mycontrols,city")
				.validationExpression("expres{2}ion\\d+")
				.validationMessage("Please input a validate expression(expression1)")
				.newKey("key1,string")
				.labelPosition("TOP")
				.maxLength("20")
				.lines("2"));
		
		//2.
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed2_company:")
				.newKeyBindMbo("company,string,Mycontrols,company"));
		
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed3_birth:")
				.newKeyBindMbo("birth,string,Mycontrols,birth"));
		
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed4_country:")
				.newKeyBindMbo("country,string,Mycontrols,country"));
		
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed5_mytime:")
				.newKeyBindMbo("mt,string,Mycontrols,mytime"));
		
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("ed6_mypw:")
				.newKeyBindMbo("pw,string,Mycontrols,mypassword")
				.password(true));
		
		//set myscreen1 and myscreen2
		WorkFlowEditor.addScreen("Myscreen2");
		WorkFlowEditor.addWidget("myscreen2", new WFHtmlView()
				.newKeyBindMbo("hhmlview,string,Mycontrols,about"));
		
		WorkFlowEditor.addScreen("Myscreen3");
		WorkFlowEditor.link("myscreen2", "myscreen3");
		WorkFlowEditor.addWidget("myscreen3", new WFLview()
			.newKeyBindMbo("htmlview,string,Mycontrols,name,age"));
		
		//set cell in listview
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		addCell("0","cell line 0","key1","100");
		sleep(1);
		addCell("1","cell line 1","key2","100");
		sleep(1);
		MainMenu.saveAll();
		PropertiesView.restore();
		
		//Click "ctrl+save"
		DOF.getRoot().inputKeys("^s");
		
		//need to improve and run to match....
		
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Complex_RT_2.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();		
		
		
	}
	
	public void addCell(String order,String cellName,String cellKey,String length){
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
	}
	
	
	
}



