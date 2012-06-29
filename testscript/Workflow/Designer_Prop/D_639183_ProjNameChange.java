package testscript.Workflow.Designer_Prop;
import java.awt.MenuItem;

import resources.testscript.Workflow.Designer_Prop.D_639183_ProjNameChangeHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class D_639183_ProjNameChange extends D_639183_ProjNameChangeHelper
{
	/**
	 * Script Name   : <b>ProjNameChange</b>
	 * Generated     : <b>Aug 27, 2011 5:56:25 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll"));
		
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
		
		WN.renameProject(Cfg.projectName,"newproject");
		
		vpManual("haserror",1,Problems.getErrors().size()).performTest();
		WorkFlowEditor.open("newproject","myWF.xbw");
		
		WorkFlowEditor.setMenuItem("Departmentupdateinstance", new WFScreenMenuItem().name("Update"));
		String update = (String) DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text");
		System.out.println("update:"+update);
		vpManual("haserror","wf/Department",update).performTest();
		
		WorkFlowEditor.setMenuItem("Departmentdeleteinstance", new WFScreenMenuItem().name("Delete"));
		String delete = (String) DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text");
		System.out.println("delete:"+delete);
		vpManual("haserror","wf/Department",delete).performTest();
		
		WorkFlowEditor.setMenuItem("Departmentcreate", new WFScreenMenuItem().name("Create"));
		String create = (String) DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text");
		System.out.println("create:"+create);
		vpManual("haserror","wf/Department",create).performTest();
		
		WN.deleteProject("newproject");
	}
}
//PASSED