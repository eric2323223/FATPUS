package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.ACT_MLI_E2E_AndroidHelper;
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
import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_MLI_E2E_Android extends ACT_MLI_E2E_AndroidHelper
{
	/**
	 * Script Name   : <b>ACT_MLI_E2E_Android</b>
	 * Generated     : <b>Nov 1, 2011 7:30:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/01
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/muli3.sql");
		//DND Table to create MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->Customer (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->SalesOrder (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->Item (dba)");
		//Create Relationship
		WN.createRelationship(new Relationship()
				.startParameter(WN.mboPath(Cfg.projectName, "Customer"))
				.target("SalesOrder")
				.mapping("cid,custid")
				.composite("true")
				.type(Relationship.TYPE_OTM));
		WN.createRelationship(new Relationship()
				.startParameter(WN.mboPath(Cfg.projectName, "SalesOrder"))
				.target("Item")
				.mapping("oid,orderid")
				.composite("true")
				.type(Relationship.TYPE_OTM));
		//Deploy MBOs
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Customer");
		DOF.getWFScreenFigure(DOF.getRoot(), "Item").click();
		WorkFlowEditor.link("SalesOrder_add", "Item");
		DOF.getWFScreenFigure(DOF.getRoot(), "Customer_create").click();
		WorkFlowEditor.link("Start Screen", "Customer_create");
		WorkFlowEditor.link("Customer_create", "SalesOrder");
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("fname='flv'");
		clause.add("lname='flv'");
		vpManual("dbresult", true, CDBUtil.getRecordCount("flv-vmpc", "wf", "Customer", clause) > 0).performTest();
		clause.clear();
		clause.add("orderid=1");
		clause.add("product='1'");
		vpManual("dbresult", true, CDBUtil.getRecordCount("flv-vmpc", "wf", "Item", clause) > 0).performTest();
		clause.clear();
		clause.add("custid=1");
		clause.add("order_date='2011-11-02'");
		vpManual("dbresult", true, CDBUtil.getRecordCount("flv-vmpc", "wf", "SalesOrder", clause) > 0).performTest();
//		WFCustomizer.verifyResult(new WFClientResult().data());
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("Customer_create").throughMenuItem("Open Customer_create");
		s.screen("Customer_create").setData("Customer_create_fname_paramKey", "flv");
		s.screen("Customer_create").setData("Customer_create_lname_paramKey", "flv");
		s.screen("Customer_create").setData("Customer_create_address_paramKey", "address");
		s.screen("Customer_create").setData("Customer_create_city_paramKey", "xian");
		s.screen("Customer_create").setData("Customer_create_state_paramKey", "CN");
		s.screen("Customer_create").setData("Customer_create_zip_paramKey", "100100");
		s.screen("Customer_create").setData("Customer_create_phone_paramKey", "88351524");
		s.screen("Customer_create").setData("Customer_create_company_name_paramKey", "sybase");
		s.screen("Customer_create").moveTo("SalesOrder").throughMenuItem("Open SalesOrder");
		s.screen("SalesOrder").moveTo("SalesOrder_add").throughMenuItem("Add");
		s.screen("SalesOrder_add").setData("SalesOrder_create_custid_paramKey", "1");
		s.screen("SalesOrder_add").setData("SalesOrder_create_order_date_paramKey", "2011-11-02");
		s.screen("SalesOrder_add").setData("SalesOrder_create_region_paramKey", "1");
		s.screen("SalesOrder_add").moveTo("Item").throughMenuItem("Open Item");
		s.screen("Item").moveTo("Item_add").throughMenuItem("Add");
		s.screen("Item_add").setData("Item_create_orderid_paramKey", "1");
		s.screen("Item_add").setData("Item_create_product_paramKey", "1");
		//Create Item record
		s.screen("Item_add").moveTo("Item").throughMenuItem("Create");
		s.screen("Item").moveTo("SalesOrder_add").throughMenuItem("Back");
		//Create SalesOrder record
		s.screen("SalesOrder_add").moveTo("SalesOrder").throughMenuItem("Create");
		s.screen("SalesOrder").moveTo("Customer_create").throughMenuItem("Back");
		//Create Customer record
		s.screen("Customer_create").moveTo("Customer_create").throughMenuItem("Create");
		
		return s;
	}
}
