package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_OLC_Q20To213Helper;
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
public class Mgrt_OLC_Q20To213 extends Mgrt_OLC_Q20To213Helper
{
	/**
	 * Script Name   : <b>Mgrt_OLC_Q20To213</b>
	 * Generated     : <b>Apr 25, 2012 5:03:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/25
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT20);
		sleep(1);
		WN.openLegacyProjectMbo("pro20", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "onlinequery"))
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
		s.screen("Start_Screen").setData("item1", "400");
		s.screen("Start_Screen").moveTo("Departmentonline").throughMenuItem("findquery");
		s.screen("Departmentonline").moveTo("DepartmentonlineDetail").throughListItem("0");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_id_attribKey");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_name_attribKey");
		s.screen("DepartmentonlineDetail").getData("Departmentonline_dept_head_id_attribKey");
		
		return s;
	}
	
}

