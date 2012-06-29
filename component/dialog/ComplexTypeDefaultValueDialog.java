package component.dialog;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

public class ComplexTypeDefaultValueDialog extends RationalTestScript {
	public static void setDefaultValue(String values){
		if(values.contains(":")){
			String[] data = values.split(":");
			for(String value:data){
				setSingleDefaultValue(value);
			}
		}else{
			setSingleDefaultValue(values);
		}
		DOF.getButton(dialog(), "OK").click();
	}
	
	private static void setSingleDefaultValue(String value){
		String rootNode = TreeHelper.getFirstItem((ScrollGuiSubitemTestObject)tree());
		String[] data = value.split(",");
		tree().click(atCell(atRow(atPath(value.replace(value.split("->")[0],rootNode).replace(","+data[1], ""))),  atColumn("Values")));
//		tree().click(atCell(atRow(atPath(rootNode+"->"+data[0])), atColumn("Values")));
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(data[1]);
	}
	
	private static GuiSubitemTestObject tree(){
		return DOF.getTree(dialog());
	}
	
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Edit Values");
	}

}
