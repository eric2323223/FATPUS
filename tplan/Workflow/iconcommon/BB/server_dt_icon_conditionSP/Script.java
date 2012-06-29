package tplan.Workflow.iconcommon.BB.server_dt_icon_conditionSP;
import resources.tplan.Workflow.iconcommon.BB.server_dt_icon_conditionSP.ScriptHelper;
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
import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.TplanTest;

/**
 * Description   : Functional Test Script
 * @author min zhao
 */
public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(

			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="desktop"

	)
	public void doTest() throws Exception {
		connect("10.56.252.210", "sybase");
		

		openMessageEntry("server_dt_BB");
//		clickOn("server_dt*");
//		disconnect();
	}
}

