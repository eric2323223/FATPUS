package component.entity.wizard;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.ACW;

public class JarCreationWizard extends ACW {
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("JAR Export");
	}

	@Override
	public void start(String string) {
		if(DOF.getDialog("Deploy Mobile Application Project")==null){
			DOF.getButton(DOF.getDialog("New Mobile Deployment Package"), "&Create JAR...").click();
		}else{
			DOF.getButton(DOF.getDialog("Deploy Mobile Application Project"), "&Create JAR...").click();
		}
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setFileName")){
			return 0;
		}
		if(operation.equals("setPkg")){
			return 2;
		}
		if(operation.equals("setType")){
			return 0;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	public void setFileName(String str){
		DOF.getCombo(dialog()).click();
		String name = DOF.getCombo(dialog()).getProperty("text").toString();
		String newName = name.replace(name.substring(name.lastIndexOf("\\")+1, name.indexOf(".jar")), str);
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(newName);
		DOF.getCombo(dialog()).click();
		
	}
	
	public void setPkg(String str){
		DOF.getButton(dialog(), "Seal some &packages").click();
		DOF.getButton(dialog(), "D&etails...").click();
		sleep(1);
		GuiSubitemTestObject pkgTree = DOF.getTree(DOF.getDialog("Seal Packages"));
		pkgTree.click(atPath(str));
		DOF.getButton(DOF.getDialog("Seal Packages"), "OK").click();
		sleep(1);
	}
	
	public void finish(){
		RationalTestScript.sleep(1);
		DOF.getButton(dialog(), "&Finish").click();
		RationalTestScript.sleep(3);
		if(DOF.getDialog("JAR Export")!=null){
//			DOF.getButton(DOF.getDialog("JAR Export"), "OK").click();
			getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		}
	}
	
	public void setType(String str){}
}
