package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Complex_RT_E2E_1Helper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Complex_RT_E2E_1 extends Ctrl_Complex_RT_E2E_1Helper
{
	/**
	 * Script Name   : <b>Ctrl_Complex_RT_E2E</b>
	 * Generated     : <b>Dec 19, 2011 9:55:19 AM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_employee (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.addScreen("myscreen");
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("emp_id:")
				.newKey("emp_id,int"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findByPrimaryKey")
				.project(Cfg.projectName)
				.type("Online Request")
				.mbo("Ff_wf_employee")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("myscreen")
				.parametermapping("emp_id,emp_id"));
		
		//1.edit
		WorkFlowEditor.addWidget("myscreen", new WFEditBox().label("edit:")
				.key("Ff_wf_employee_emp_name_attribKey"));
		//2.Dynamic choice1
		WorkFlowEditor.showPropertiesViewInSD("myscreen");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,int,Ff_wf_employee,emp_id");
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
				.logicalType("Numeric")
				.ifReadonly(true)
				.option("Static,item1,1:item2,2"));
		//4.CKBOX1
		WorkFlowEditor.addWidget("myscreen", new WFCheckbox().label("ckbox1:")
				.labelPosition("LEFT")
			.key("Ff_wf_employee_emp_gender_attribKey"));
	    //5.Slider
		WorkFlowEditor.addWidget("myscreen", new WFSlider().label("sld:")
				.labelPosition("LEFT")
				.ifReadonly(true)
				.validationMessage("slider validation message")
					.key("Ff_wf_employee_dept_id_attribKey"));
		//6.Signature
		WorkFlowEditor.addWidget("myscreen", new WFSignature().label("sig1:")
				.labelPosition("LEFT")
				.key("Ff_wf_employee_emp_sign_attribKey"));
		
		MainMenu.saveAll();
		
		//TPlan:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DT")
			.verifyResult("Successfully deployed the workflow", true));
	
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Complex_RT_1.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();	
		
		
		
		
		
		
		
	}
}

