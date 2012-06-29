package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

public class SearchDialog extends RationalTestScript{
	
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Search");
	}

	public static void search(){
		try{
		DOF.getButton(dialog(), "&Search").click();
		sleep(1);
		DOF.getButton(dialog(), "&Search").click();
		sleep(3);
		}catch(Exception e){
			
		}
	}

	public static void setSearchString(String string) {
		DOF.getCombo(dialog(),"Search string (* = any string, ? = any character):").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
}
