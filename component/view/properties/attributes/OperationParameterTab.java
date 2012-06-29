package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.dialog.EditDefaultValueDialog;
import component.entity.model.OperationParameter;

public class OperationParameterTab extends RationalTestScript{
	
	private static GuiSubitemTestObject tree(TopLevelTestObject parent){
		return DOF.getTree(DOF.getDualHeadersTree(parent));
	}

	public static void set(OperationParameter op, TopLevelTestObject parent) {
		if(op.getDefaultValue()!=null){
			tree(parent).click(atCell(atRow(atPath(op.getName())), atColumn("Default Value")));
			sleep(0.5);
			if(DOF.getButton(parent, "...")==null){
				parent.inputChars(op.getDefaultValue());
			}else{
				DOF.getButton(parent, "...").click();
				EditDefaultValueDialog.addListOfValues(op.defaultValueAsArray(), DOF.getDialog("Edit Values"));
			}
		}
		if(op.getPk()!=null){
			TreeHelper.setCComboCellValue(tree(parent), op.getName(), "Personalization Key", op.getPk());
		}
	}
	
	
}
