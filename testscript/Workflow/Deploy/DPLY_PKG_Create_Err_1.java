package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.DPLY_PKG_Create_Err_1Helper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.widget.DOF;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DPLY_PKG_Create_Err_1 extends DPLY_PKG_Create_Err_1Helper
{
	/**
	 * Script Name   : <b>DPLY_PKG_Create_Err_1</b>
	 * Generated     : <b>Sep 29, 2011 7:35:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_ACTIVATE));
		vpManual("noError", 1, Problems.getErrors().size()).performTest();
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)+"->"+Cfg.wfName+".xbw"));
		DOF.getContextMenu().click(atText("Generate Mobile Workflow Package..."));
		sleep(1);
		vpManual("errorDetected", true, DOF.getDialog("Error Detected")!=null).performTest();
		DOF.getButton(DOF.getDialog("Error Detected"), "&No").click();

	}
}

