package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.dialog.EditDefaultValueDialog;
import component.entity.DefaultValueTreeHelper;

public class OperationParameterPage extends RationalTestScript{

	public static void setParameterDefaultValue(String str, TopLevelTestObject dialog) {
		for(String entry:str.split(":")){
			setSingleParameterDefaultValue(entry, dialog);
		}
		
	}

	private static void setSingleParameterDefaultValue(String str,
			TopLevelTestObject dialog) {
		if(str.contains("->")){
			setSingleComplexTypeParameterDefaultValue(str, dialog);
		}else{
			setSingleSimpleTypeParameterDefaultValue(str, dialog);
		}
		
	}

	private static void setSingleSimpleTypeParameterDefaultValue(String str,
			TopLevelTestObject dialog) {
		String parameter = str.split(",")[0];
		String value = str.split(",")[1];
		DefaultValueTreeHelper.setDefaultValue(parameterTree(dialog), parameter, value);
	}

	private static void setSingleComplexTypeParameterDefaultValue(String str,
			TopLevelTestObject dialog) {
		parameterTree(dialog).click(atCell(atRow(atPath(str.substring(0, str.indexOf("->")))), atColumn("Default Value")));
		sleep(1);
		DOF.getButton(dialog, "...").click();
		EditDefaultValueDialog.setDefaultValue(str, DOF.getDialog("Edit Values"));
		
	}
	
	private static GuiSubitemTestObject parameterTree(TopLevelTestObject dialog){
		return DOF.getTree(DOF.getDualHeadersTree(dialog));
	}


}
