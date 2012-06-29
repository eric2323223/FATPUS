package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

public class DefaultValueTreeHelper extends RationalTestScript{

	public static void setDefaultValue(GuiSubitemTestObject tree,String parameter, String value) {
		if(parameter.contains("->")){
			String type = TreeHelper.getCellValue(tree, parameter, "Datatype");
			String complexType = parameter.split("->")[0];
			String subName = parameter.split("->")[1];
			tree.click(atCell(atRow(atPath(parameter)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(tree, "...").click();
			TopLevelTestObject dialog = DOF.getDialog("Edit Values");
			GuiSubitemTestObject tree2 = DOF.getTree(dialog);
			String path = parameter.replace(complexType, type);
			TreeHelper.setTextCellValue(tree2, path, "Values", value);
			DOF.getButton(dialog, "OK").click();
		}else{
			TreeHelper.setTextCellValue(tree, parameter, "Default Value", value);
		}
	}
	
	

}
