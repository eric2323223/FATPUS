package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.dialog.RESTWSDefinitionDialog;
import component.entity.model.RestWSMbo;
import component.entity.model.WSResponse;

public class RestWSDefinitionPage extends RationalTestScript{

	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
		
	}

	public static void setExpectedStatusCode(String str,
			TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getCombo(dialog, "Expected status code:").click();
			DOF.getCombo(dialog, "Expected status code:").click(atText(str));
		}
	}

	public static void setHttpMethod(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getCombo(dialog, "HTTP method:").click();
			DOF.getCombo(dialog, "HTTP method:").click(atText(str));
		}
		
	}

	public static void setResourceUriTemplate(String str,
			TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getCombo(dialog, "Resource URI template:").click();
			DOF.getCombo(dialog, "Resource URI template:").click(atText(str));
		}
	}

	public static void setResourceBaseUri(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getTextField(dialog, "Resource base URL:").click();
			dialog.inputKeys(SpecialKeys.CLEARALL);
			dialog.inputChars(str);
		}
	}

	public static void setResponse(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getTabFolder(dialog, "Re&presentation").setState(SINGLE_SELECT, atText("Re&presentation"));
			WSResponse response = new WSResponse(str);
			((ToggleGUITestObject)DOF.getButton(dialog, "Re&sponse")).clickToState(SELECTED);
			DOF.getButton(dialog, "Ed&it...").click();
			RESTWSDefinitionDialog.setResponse(response, DOF.getDialog("Define Representation"));
	
		}
	}
	
	public static void setRequest(String str, TopLevelTestObject dialog){
		if(str!=null){
			DOF.getTabFolder(dialog, "Re&presentation").setState(SINGLE_SELECT, atText("Re&presentation"));
			WSResponse response = new WSResponse(str);
			((ToggleGUITestObject)DOF.getButton(dialog, "Re&quest")).clickToState(SELECTED);
			DOF.getButton(dialog, "E&dit...").click();
			RESTWSDefinitionDialog.setResponse(response, DOF.getDialog("Define Representation"));
			
		}
		
	}

	public static void setHeader(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getTabFolder(dialog, "Re&presentation").setState(SINGLE_SELECT, atText("H&TTP Header"));
			for(String item:str.split(":")){
				setSingleHeader(item, dialog);
			}
		}
	}
	
	private static void setSingleHeader(String str, TopLevelTestObject dialog){
		String name = str.split(",")[0];
		String value = str.split(",")[1];
		DOF.getButton(dialog, "&Add").click();
		GuiSubitemTestObject table = DOF.getTable(dialog);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "header");
		TableHelper.setTextCellValue(table, row, "Name", name);
		TableHelper.setTextCellValue(table, row, "Value", value);
		table.click(atCell(atRow(0),atColumn("Name")));
	}

	public static void setUserName(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getTabFolder(dialog, "Re&presentation").setState(SINGLE_SELECT, atText("Aut&hentication"));
			((ToggleGUITestObject)DOF.getButton(dialog, "HTTP Basic Aut&hentication:")).clickToState(SELECTED);
			DOF.getCombo(dialog, "User name:").click();
			sleep(1);
			DOF.getCombo(dialog, "User name:").click();
			dialog.inputChars(str);
		}
	}

	public static void setPassword(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getCombo(dialog, "Password:").click();
			sleep(1);
			DOF.getCombo(dialog, "Password:").click();
			dialog.inputChars(str);
		}
	}


}
