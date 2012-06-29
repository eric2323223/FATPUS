package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.dialog.CacheGroupEditDialog;
import component.entity.model.CachePolicy;
import component.entity.model.Feature;
import component.entity.model.Interval;

public class Preference extends RationalTestScript{
	private static Properties properties;
	static{
		properties = new Properties("Preferences");
	}
	
	public static Properties getProperties(){
		return properties;
	}
	
	public static void open(){
		DOF.getMenu().click(atPath("Window->Preferences"));
	}
	
	public static void close(){
		DOF.getButton(dialog(), "OK").click();
	}
	
	public static void setMigratingPrompt(boolean showPrompt) {
		open();
		preferenceTree().click(
				atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		if (showPrompt) {
			((ToggleTestObject) DOF.getButton(dialog(),
					"Do not show prompt dialog for &migrate dialog again"))
					.setState(NOT_SELECTED);
		} else {
			((ToggleTestObject) DOF.getButton(dialog(),
					"Do not show prompt dialog for &migrate dialog again"))
					.setState(SELECTED);
		}
		close();
	}

	private static GuiSubitemTestObject preferenceTree(){
		return DOF.getTree(dialog());
	}
	
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Preferences");
	}

	public static String getResultSetResultCheckerSetting() {
		open();
		String result;
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		if(DOF.getButton(dialog(), "&Prompt").invoke("getSelection").toString().equals("true")){
			result = "Prompt";
		}
		else if(DOF.getButton(dialog(), "&Yes").invoke("getSelection").toString().equals("true")){
			result = "Yes";
		}
		else{
			result = "No";
		}
		close();
		return result;
	}
	
	public static void SetResultSetResultCheckerSetting(String value){
		open();
		String result;
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		if(value.equalsIgnoreCase("prompt")){
			DOF.getButton(dialog(), "&Prompt").click();
		}
		if(value.equalsIgnoreCase("yes")){
			DOF.getButton(dialog(), "&Yes").click();
		}
		if(value.equalsIgnoreCase("no")){
			DOF.getButton(dialog(), "N&o").click();
		}
		close();
	}




	
	
	
	
	
	
	
	
	
	
	
	public static void setEnterpriseExplorerSubGrouping(String value) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Enterprise Explorer->Subgroup"));
		if(value.equalsIgnoreCase("No grouping")){
			DOF.getButton(dialog(), "No grouping").click();
		}
		close();
		
	}

	public static String setDefaultCachePolicy(CachePolicy policy) {
		String result = null;
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		maximize();
		CacheGroupEditDialog.setPolicy(dialog(), policy);
		sleep(1);
		if(DOF.getCLabelByContent(dialog(), "Miscellaneous")==null){
			result  = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Composite->Composite->Composite->Composite->Composite->Composite->Shell->Shell")
			.getProperty("text").toString();
//			CacheGroupEditDialog.setPolicy(dialog(), new CachePolicy(CachePolicy.ONDEMAND, new Interval(0,0,0), false));
			cancel();
			
		}else{
			close();
		}
		return result;
	}
	
	private static void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	public static void maximize(){
		dialog().maximize();
	}

	public static void setCachePartition(String string) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		maximize();
		((ToggleGUITestObject)DOF.getButton(dialog(), "&Partition by requester and device identity")).clickToState(SELECTED);
		close();
	}

	public static void switchToAdavanceProfile() {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Developer Profile"));
		DOF.getButton(dialog(), "Ad&vanced profile").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
	}

	//8.5 fanfei add function:>>>>>>>>>>>>>>>>>>>
	public static void switchToBasicProfile() {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Developer Profile"));
		DOF.getButton(dialog(), "&Basic profile").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
	}
	
	public static void setSwitchingDialog(boolean showPrompt) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Developer Profile"));	
		DOF.getButton(DOF.getDialog("Preferences"), "Restore &Defaults").click();
		sleep(2);
		if (showPrompt) {
			DOF.getButton(dialog(),"&Do not show prompt dialog for profile switching again").click();	
		}	
		sleep(2);
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		sleep(2);
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
	}
	//<<<<<<<<<<<<<<<8.5 fanfei add function<<<<<<<<<<<<<<<<<<
	public static void restoreDefault(String str) {
		open();
		preferenceTree().click(atPath(str));
		DOF.getButton(dialog(),	"Restore &Defaults").click();
		close();
	}

	public static void setShowDialogForObjectQueryAutoGeneration(boolean value) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Miscellaneous"));
		if(value){
		((ToggleTestObject)DOF.getButton(dialog(), "Do no&t show prompt dialog for object query auto-generation")).setState(SELECTED);
		}else{
			((ToggleTestObject)DOF.getButton(dialog(), "Do no&t show prompt dialog for object query auto-generation")).setState(NOT_SELECTED);
		}
		close();
	}
	////////////8.4 fanfei add function (has problem)>>>>>>>>>>>>//////////////
	public boolean setProfile(Feature f,String featurename,String profiletype,boolean state){
		
		boolean tempstate = true;
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Developer Profile"));
		tempstate = f.dofeature(featurename,profiletype,state);	
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
		return tempstate ;	
	}
	public boolean setProfiledetail(Feature f,String featurename,String tablename,String wizardorcontext,String itemname,String profiletype,boolean state){
		boolean tempstate = true;
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Developer Profile->Details"));
		DOF.getCombo(DOF.getDialog("Preference"),"Feature:").click(atText(featurename));
		
		if(tablename.equals("Wizard")&&wizardorcontext != null){
			
			DOF.getCTabFolder(DOF.getRoot(), "Wizard").click(atText("Wizard"));
			DOF.getCombo(DOF.getDialog("Preference"),"Wizard:").click(atText(wizardorcontext));	
			tempstate = f.doWizard(itemname,profiletype,state);
			
		}else if(tablename.equals("Property View")&&wizardorcontext != null){
			
			DOF.getCTabFolder(DOF.getRoot(), "Wizard").click(atText("Property View"));
			DOF.getCombo(DOF.getDialog("Preference"),"Context:").click(atText(wizardorcontext));
			tempstate = f.doPropertyview(itemname,profiletype,state);
			
		}else if(tablename.equals("Workspace Navigator")&&wizardorcontext == null){
			
			DOF.getCTabFolder(DOF.getRoot(), "Wizard").click(atText("Workspace Navigator"));
			tempstate = f.doWorkspaceNavigator(itemname,profiletype,state);
			
		}else
		
			System.out.println("setProfiledetail has error!");
		
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
		return tempstate;
	}
	////<<<<<<<<<<<<<8.4 fanfei add function (has problem)<<<<<<<<<<<<<<<//////////////
	
	//>>> 10\14 flv add for Flow Design Page
	public static void setShowLocalValidationDialog(String setVal) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Mobile Workflow Forms Editor->Flow Design Page"));
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Mobile Workflow Forms Editor->Flow Design Page"));
//		Object o = obj.invoke("getSelection");
//		boolean isSelected = o.equals("true");
		if (setVal.equals("true")) {
			((ToggleTestObject)DOF.getButton(dialog(), "Show locale properties file validation dialog.")).setState(SELECTED);
		} else {
			((ToggleTestObject)DOF.getButton(dialog(), "Show locale properties file validation dialog.")).setState(NOT_SELECTED);
		}
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
	}



//3.12 whan add function:>>>>>>>>>>>>>>>>>>>

public static Boolean getMoblieWorkFlowFormsEditor() {

	open();
	preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Mobile Workflow Forms Editor"));
	Object b = DOF.getButton(DOF.getDialog("Preferences"),"Show the 'Introduction' page when opening the designer.").invoke("getSelection");
	close();
	
	if(b.equals(true))
		return true;
	else
		return false;

}
	
public static void setDisplayIntroductionTabInWorkflowEditor(boolean b) {
		open();
		preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Mobile Workflow Forms Editor"));
		if(b){
			((ToggleGUITestObject)DOF.getButton(dialog(), "Show the 'Introduction' page when opening the designer.")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "Show the 'Introduction' page when opening the designer.")).clickToState(NOT_SELECTED);
		}
		close();
	}

public static void  setMoblieWorkFlowFormsEditor(String value) {

	open();
	preferenceTree().click(atPath("Sybase, Inc->Mobile Development->Mobile Workflow Forms Editor"));
	
	if(value.equalsIgnoreCase("true")){
		((ToggleTestObject)DOF.getButton(dialog(), "Show the 'Introduction' page when opening the designer.")).setState(SELECTED);
		
   }
	if(value.equalsIgnoreCase("false")){
		((ToggleTestObject)DOF.getButton(dialog(), "Show the 'Introduction' page when opening the designer.")).setState(NOT_SELECTED);
   }
	
	if(value.equalsIgnoreCase("defaults")){
		DOF.getButton(DOF.getDialog("Preferences"), "Restore &Defaults").click();
   }
	
	sleep(1);
	DOF.getButton(DOF.getDialog("Preferences"), "&Apply").click();
	sleep(1);
	DOF.getButton(DOF.getDialog("Preferences"), "OK").click();
	

   }
}
