package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.dialog.ComplexTypeDefaultValueDialog;
import component.entity.MainMenu;
import component.entity.model.LoadParameter;

public class LoadParameterTab extends RationalTestScript{
	
	private static GuiSubitemTestObject tree(){
		return DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
	}

	public static LoadParameter getParameter(String string) {
		String dv;
		String type = TreeHelper.getCellValue(tree(), string, "Datatype");
		if(type.equals("DATE")){
			tree().click(atCell(atRow(atPath(string)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(DOF.getRoot(), "...").click();
			dv = DOF.getTextField(DOF.getDialog("Date Time"), "Value:").getProperty("text").toString();
			DOF.getButton(DOF.getDialog("Date Time"), "OK").click();
		}else if(type.equals("DATETIME")){
			tree().click(atCell(atRow(atPath(string)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(DOF.getRoot(), "...").click();
			dv = DOF.getTextField(DOF.getDialog("Date"), "Value:").getProperty("text").toString();
			DOF.getButton(DOF.getDialog("Date"), "OK").click();
		}else{
			dv = TreeHelper.getCellValue(tree(), string, "Default Value");
		}
		return new LoadParameter().name(string).type(type).defaultValue(dv);
	}
	
	public static void addLoadParameter(LoadParameter parameter){
		DOF.getButton(DOF.getRoot(), "&Add").click();
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
//		tree.click(atPath("parameter1"));
//		DOF.getRoot().inputChars(parameter.getName());
		TreeHelper.setTextCellValue(tree, "parameter1", "Name", parameter.getName());
		TreeHelper.setTextCellValue(tree,"parameter1", "Datatype", parameter.getType());
		tree.click(atCell(atRow(atPath(parameter.getName())), atColumn("Name")));
	}

	public static void setParameter(String name,LoadParameter parameter) {
		if(parameter.getSyncParameter()!=null){
			tree().click(atCell(atRow(atPath(name)), atColumn("Synchronization Parameter")));
			tree().click(atCell(atRow(atPath(name)), atColumn("Synchronization Parameter")));
			DOF.getPoppedUpList().click(atText(parameter.getSyncParameter()));
			tree().click(atCell(atRow(atPath(name)), atColumn("Name")));
		}
		if(parameter.getPk()!=null){
			tree().click(atCell(atRow(atPath(name)), atColumn("Personalization Key")));
			tree().click(atCell(atRow(atPath(name)), atColumn("Personalization Key")));
			DOF.getPoppedUpList().click(atText(parameter.getPk()));
			tree().click(atCell(atRow(atPath(name)), atColumn("Name")));
			
		}
		if(name.contains("->")){
			setComplexTypeParameter(name, parameter);
		}else{
			setSimpleTypeParameter(name, parameter);
		}
	}

	private static void setSimpleTypeParameter(String name,	LoadParameter parameter) {
		setType(name, parameter.getType());
		setPropagateTo(name, parameter.getPropagateTo());
		setDefaultValue(name, parameter.getDefaultValue());
	}
	
	private static void setDefaultValue(String name, String value){
		if(value!=null){
			tree().click(atCell(atRow(atPath(name)), atColumn("Default Value")));
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(value);
		}
	}
	
	private static void setType(String name, String type){
		if(type!=null){
			tree().click(atCell(atRow(atPath(name)), atColumn(1)));
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(type);
//			DOF.getSybaseCCombo(tree()).click();
//			DOF.getPoppedUpList().click(atText(type));
			tree().click(atCell(atRow(atPath(name)), atColumn(6)));
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(type);
//			DOF.getSybaseCCombo(tree()).click();
//			DOF.getPoppedUpList().click(atText(type));
		}
	}
	
	private static void setPropagateTo(String name, String propagateTo){
		if(propagateTo!=null){
			tree().click(atCell(atRow(atPath(name)), atColumn("Propagate to Attribute")));
			DOF.getCCombo(tree()).click();
			DOF.getPoppedUpList().click(atText(propagateTo));
		}
	}

	private static void setComplexTypeParameter(String name,LoadParameter parameter) {
		if(parameter.getDefaultValue()!=null){
			String initial = name.split("->")[0];
			tree().click(atCell(atRow(atPath(initial)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(tree(), "...").click();
			String string = name+","+parameter.getDefaultValue();
			ComplexTypeDefaultValueDialog.setDefaultValue(string);
		}
	}
	///////////////////feifan>729>>>>>>>>>>
	public static void rename(String from, String to) {
		tree().click(atCell(atRow(atPath(from)), atColumn(0)));
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(to);
	}
    ////////////////<<<<<<<<<729//feifan
}
