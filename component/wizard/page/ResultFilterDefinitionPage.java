package component.wizard.page;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

import component.dialog.JavaClassDefinitionDialog;
import component.entity.model.JavaClass;
import component.entity.model.ResultChecker;
import component.entity.model.ResultFilter;

public class ResultFilterDefinitionPage extends RationalTestScript {

	public static void setResultFilter(String str, TopLevelTestObject dialog) {
		ResultFilter rf = new ResultFilter(str);
		String dialogTitle =  dialog.getProperty("text").toString();
		DOF.getLabel(dialog, "Result Set Filters").click();
		//screen changed, need to get the dialog again
		sleep(2);
		TopLevelTestObject newDialog = DOF.getDialog(dialogTitle);
		if(rf.getType().equals(ResultFilter.TYPE_NEW)){
			creatResultFilter(rf.getJavaClass(), newDialog);
		}else{
			addExistingResultFilter(rf.getJavaClass(), newDialog);
		}
		
	}

	private static void addExistingResultFilter(String javaClass, TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Add...").click();
		throw new RuntimeException("TBD");
	}

	private static void creatResultFilter(String javaClass, TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Create...").click();
		JavaClassDefinitionDialog.createJavaClass(javaClass, dialog);
		
	}

}
