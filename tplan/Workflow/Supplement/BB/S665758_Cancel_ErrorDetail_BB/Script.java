package tplan.Workflow.Supplement.BB.S665758_Cancel_ErrorDetail_BB;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script  extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="Rectangle:30,98,440,754"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openMessageEntry("DT_findall_BB6");
		
		clickOnWithOffSet("hangoff_BB", -230, 0); //Menu_Android
		clickOn("OpenDepartmentcreate");
		clickOn("dept_id");
	
		clickOnWithOffSet("hangoff_BB",44,146); //del_Android
		type("100");
		
		clickOnWithOffSet("hangoff_BB", -230, 0);//Menu_Android
		clickOn("Create");
		
		clickOn("ErrorList");
		
		clickOnWithOffSet("hangoff_BB",-200,-480); //position1
		clickOnWithOffSet("hangoff_BB",-170,-350); //Position2
		
		clickOnWithOffSet("hangoff_BB", -230, 0);//Menu_
		clickOn("Cancel");
		waitForMatch("ErrorList",10);
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.BB.S665758_Cancel_ErrorDetail_BB.Script");
	}
}
