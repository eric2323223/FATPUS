package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.Prop_ScreenDesign_General_2Helper;
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
import com.sybase.automation.framework.widget.helper.ListHelper;

import component.entity.MainMenu;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Prop_ScreenDesign_General_2 extends Prop_ScreenDesign_General_2Helper
{
	/**
	 * Script Name   : <b>Prop_ScreenDesign_General_2</b>
	 * Generated     : <b>Nov 26, 2011 2:44:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/26
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		MainMenu.saveAll();
		sleep(1);
		WorkFlowEditor.addScreen("Screen1");
		MainMenu.saveAll();
		sleep(1);
		WorkFlowEditor.addScreen("Screen2");
		MainMenu.saveAll();
		sleep(1);
		WorkFlowEditor.addScreen("Screen3");
		sleep(1);
		MainMenu.saveAll();
		
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem().name("One"));
		WorkFlowEditor.addMenuItem("Screen2", new WFScreenMenuItem().name("Two"));
		WorkFlowEditor.addMenuItem("Screen3", new WFScreenMenuItem().name("Three"));
		
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		
		getToolBar(DOF.getRoot(), "This wizard generates the code for the Mobile Workflow package and",2);
		vpManual("hasMenu",true,WFScreenDesigner.hasMenuItem("One")).performTest();
		
		getToolBar(DOF.getRoot(), "This wizard generates the code for the Mobile Workflow package and",-1);
		vpManual("hasMenu",true,WFScreenDesigner.hasMenuItem("Two")).performTest();
		
		getToolBar(DOF.getRoot(), "This wizard generates the code for the Mobile Workflow package and",-1);
		vpManual("hasMenu",true,WFScreenDesigner.hasMenuItem("Three")).performTest();
	
	}
	
	public static boolean getToolBar(TestObject parent,
			String caption,int order) {
		TestObject[] toolBars = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.ToolBar"));
		if (toolBars != null) {
			for (int i = 0; i < toolBars.length; i++) {
				if (toolBars[i].getProperty(".itemToolTipText") != null
						&& toolBars[i].getProperty(".itemToolTipText")
								.toString().indexOf(caption) != -1) {
					((GuiSubitemTestObject) toolBars[i]).click();
					if(order>0){
						for(int j=0;j<order;j++){
							sleep(0.5);
							DOF.getRoot().inputKeys(SpecialKeys.UP);
							sleep(0.5);
						}
					}else{
						for(int j=0;j>order;j--){
							sleep(0.5);
							DOF.getRoot().inputKeys(SpecialKeys.DOWN);
							sleep(0.5);
						}
					}
					DOF.getRoot().inputKeys(SpecialKeys.ENTER);
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
//passed
