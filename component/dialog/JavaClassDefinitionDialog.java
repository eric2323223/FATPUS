package component.dialog;

import java.util.List;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.model.JavaClass;

public class JavaClassDefinitionDialog extends RationalTestScript {

	public static void createJavaClass(JavaClass jc, TopLevelTestObject dialog) {
//		setPackage(jc.getPackage(), dialog);
		setName(jc.getName(), dialog);
		finish(dialog);
	}
	
	public static void createJavaClass(String jc, TopLevelTestObject dialog) {
//		setPackage(jc.getPackage(), dialog);
		setName(jc, dialog);
		finish(dialog);
	}


	private static void finish(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Finish").click();
	}


	private static void setName(String name, TopLevelTestObject dialog) {
		if(name!=null){
			DOF.getTextField(dialog, "Name:").click();
			dialog.inputChars(name);
		}
	}


	public String[] getInterfaces(TopLevelTestObject dialog){
		GuiSubitemTestObject table = DOF.getTable(dialog);
		return TableHelper.getColumnData(table,0);
	}

}
