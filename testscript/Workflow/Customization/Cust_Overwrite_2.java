package testscript.Workflow.Customization;
import java.io.File;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;

import resources.testscript.Workflow.Customization.Cust_Overwrite_2Helper;
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
import com.sybase.automation.framework.common.FileWatcher;
import com.sybase.automation.framework.common.IOperation;
import com.sybase.automation.framework.common.WatchLog;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class Cust_Overwrite_2 extends Cust_Overwrite_2Helper
{
	/**
	 * Script Name   : <b>Cust_Overwrite_2</b>
	 * Generated     : <b>Mar 20, 2012 10:05:17 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/20
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll"));
		vpManual("generate", false, WN.hasFile(Cfg.projectName, "Generated Workflow")).performTest();
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.deployToServer("false"));
		vpManual("generate", true, WN.hasFile(Cfg.projectName, "Generated Workflow")).performTest();
		vpManual("generate", true, WN.hasFile(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->js->Custom.js"))).performTest();
		vpManual("generate", true, WN.hasFile(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->css->Stylesheet.css"))).performTest();
		
		WatchLog log = fileWatcher().perform();
		vpManual("contentChange", false, log.hasChanges()).performTest();
	}
	
	private FileWatcher fileWatcher(){
		return new FileWatcher().watch(new String[]{customJsPath(), stylesheetsCssPath()}, new IOperation(){
			public void operate() {
				WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
						.unwiredServer("My Unwired Server")
						.deployToServer("false"));
			}
		});
	}
	
	private String customJsPath(){
		return WN.getAbsoluteFilePath(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->js->Custom.js"));
	}
	
	private String stylesheetsCssPath(){
		return WN.getAbsoluteFilePath(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->css->Stylesheet.css"));
	}

}

