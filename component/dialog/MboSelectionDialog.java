package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

public class MboSelectionDialog extends RationalTestScript {
	public static void selectMbo(TopLevelTestObject dialog, String mbo){
		if(mbo.equalsIgnoreCase("all")){
			TreeHelper.checkNode(DOF.getTree(dialog), "Mobile Business Objects");
//			DOF.getTree(dialog()).click(RationalTestScript.atPath("Mobile Business Objects->Location(CHECKBOX)"));
		}else{
			for(String string:mbo.split(",")){
				selectSingleMbo(dialog, "Mobile Business Objects->Default->"+string);
			}
		};
	
	}
	
	public static void selectSingleMbo(TopLevelTestObject dialog, String str){
		TreeHelper.exclusiveCheckNode(DOF.getTree(dialog), str);
	}
	
	public static void finish(TopLevelTestObject dialog){
		DOF.getButton(dialog, "&Finish").click();
	}

}
