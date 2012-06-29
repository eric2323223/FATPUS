package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class ParameterPage extends RationalTestScript{
	
	
	public static void setParameterDefaultValue(String str, TopLevelTestObject dialog){
		for(String entry: str.split(":")){
//			if(entry.contains("->")){
//				setComplexParameterDefaultValue(entry, dialog);
//			}else{
//				setSingleParameterDefaultValue(entry, dialog);
//			}
			LoadParameterPage.setParameterDefaultValue(str, dialog);
		}
	}

//	private static void setComplexParameterDefaultValue(String entry,	TopLevelTestObject dialog) {
//		// TODO Auto-generated method stub
//		LoadParameterPage.setComplexTypeParameterDafaultValue(entry, dialog);
//	}
//
//	public static void setSingleParameterDefaultValue(String str,	TopLevelTestObject dialog) {
//		String name = str.split(",")[0];
//		String defaultValue = str.split(",")[1];
//		GuiSubitemTestObject tree;
//		if(DOF.getDualHeadersTree(dialog)!=null){
//			tree = DOF.getTree(DOF.getDualHeadersTree(dialog));
//		}else{
//			tree = DOF.getTree(dialog);
//		}
//		tree.click(atCell(atRow(atPath(name)),atColumn("Default Value")));
//		dialog.inputChars(defaultValue);
//	}

}
