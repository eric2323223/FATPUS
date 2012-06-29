package component.wizard.page;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.model.JavaClass;
import component.entity.model.ResultChecker;

public class ResultCheckerDefinitionPage extends RationalTestScript{
	
	private static String javaNature;
	private static String javaNaturePrompt = "no";

	public static void setResultChecker(String str, TopLevelTestObject dialog) {
		if(str!=null){
			ResultChecker rc = new ResultChecker(str);
	//		ResultChecker rc = (ResultChecker)new ObjectMarshaller().deserialize(str, ResultChecker.class);
			DOF.getLabel(dialog, "Result Checker").click();
			sleep(1);
			if(rc.getType().equals(ResultChecker.TYPE_DEFAULT)){
				DOF.getButton(dialog, "Defau&lt").click();
			}else if(rc.getType().equals(ResultChecker.TYPE_NONE)){
				DOF.getButton(dialog, "N&one").click();
			}else if(rc.getType().equals(ResultChecker.TYPE_NEW)){
				setNewResultChecker(str, dialog);
			}else{
				setExistingResultChecker(str, dialog);
			}
		}
	}
	
	public static void ok(TopLevelTestObject dialog){
		DOF.getButton(dialog, "OK").click();
	}

	private static void setExistingResultChecker(String str,
			TopLevelTestObject dialog) {
		DOF.getLabel(dialog, "Result Checker").click();
		DOF.getButton(dialog, "C&ustom").click();
		DOF.getButton(dialog, "Bro&wse...").click();
		TopLevelTestObject dialog2 = DOF.getDialog("Select Web Service Result Checker Class");
		dialog2.inputChars(str);
		sleep(1);
		dialog.inputKeys(SpecialKeys.DOWN);
		dialog.inputKeys(SpecialKeys.ENTER);
	}

	private static void setNewResultChecker(String str,
			TopLevelTestObject dialog) {
		DOF.getLabel(dialog, "Result Checker").click();
		JavaClass klass = new JavaClass(str);
		DOF.getButton(dialog, "C&ustom").click();
		DOF.getButton(dialog, "&Create...").click();
		sleep(1);
		if(DOF.getDialog("Add Java Nature")!=null){
			javaNaturePrompt = "yes";
			if(javaNature!=null && javaNature.equalsIgnoreCase("false")){
				DOF.getButton(DOF.getDialog("Add Java Nature"), "&No").click();
			}else{
				DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
			}
		}
//		DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
		TopLevelTestObject newJavaClassdialog = DOF.getDialog("New Java Class");
		if(klass.getSourceFolder()!=null){
			DOF.getTextField(newJavaClassdialog, "Source folder:").click();
			newJavaClassdialog.inputKeys(SpecialKeys.CLEARALL);
			newJavaClassdialog.inputChars(klass.getSourceFolder());
		}
		DOF.getTextField(newJavaClassdialog, "Name:").click();
		newJavaClassdialog.inputChars(klass.getName());
		DOF.getButton(newJavaClassdialog, "&Finish").click();
		sleep(3);
		
	}

	public static String getJavaNaturePrompt() {
		return javaNaturePrompt;
	}
	


}
