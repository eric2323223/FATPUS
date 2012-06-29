package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_201To213_OLCHelper;
import testscript.Workflow.Migration.cfg.Cfg;

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
import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_201To213_OLC extends Mgrt_201To213_OLCHelper
{
	/**
	 * Script Name   : <b>Mgrt_201To213_OLC</b>
	 * Generated     : <b>May 10, 2012 12:16:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/10
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT201);
		sleep(3);
		WN.openLegacyProjectMbo("pro201", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName1)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName1, "onlinequery"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser("dt"),
			customTestScript() 
//			"tplan.Workflow.iconcommon.BB.Mgrt_icons.Script"
			);

		WFCustomizer.verifyResult(new WFClientResult().data(
						"id=Departmentonline_dept_id_attribKey,value=400|"+
						"id=Departmentonline_dept_name_attribKey,value=Marketing|"+
						"id=Departmentonline_dept_head_id_attribKey,value=1576"));
	}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("item1", "Marketing");
		s.screen("Start_Screen").moveTo("Departmentonline").throughMenuItem("findquery");
		s.screen("Departmentonline").moveTo("DepartmentonlineDetail").throughListItem("0");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_id_attribKey");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_name_attribKey");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_head_id_attribKey");
		
		return s;
	}
}

