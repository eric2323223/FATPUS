package tplan.Workflow.Supplement.BB.S668687_validation_for_dynachoice_BB;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script  extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 97f,
			matchArea="Rectangle:45,96,400,610"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openMessageEntry("DT_findall");
		
		clickOnWithOffSet("hangoff_BB",-230,0); //Menu_BB
		clickOn("Openmyscreen");
		
		clickOn("id");
		type("1");
		
		waitForMatch("name",10);
		
		clickOn("name");
		type("f");
		
		clickOnWithOffSet("hangoff_BB",-230,0); //Menu_BB
		clickOn("create");
		
		clickOn("alert");
	}
	
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.BB.S668687_validation_for_dynachoice_BB.Script");
	}
}

