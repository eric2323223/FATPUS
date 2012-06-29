package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.ComplexTypeDefaultValueDialog;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;

public class LoadArgumentTab extends RationalTestScript{
	public static void setLoadArgument(LoadArgument la){
		if(la.getName().contains("->")){
			setComplexTypeLoadArgument(la);
		}else{
			setSimpleTypeLoadArgument(la);
		}
		handleWarning(la.getName());
	}

	private static void setComplexTypeLoadArgument(LoadArgument la) {
		if(la.getDefaultValue()!=null){
			String initial = la.getName().split("->")[0];
			DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot())).click(atCell(atRow(atPath(initial)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot())), "...").click();
			String string = la.getName()+","+la.getDefaultValue();
			ComplexTypeDefaultValueDialog.setDefaultValue(string);
		}
		//ff12.5 add >>>>>>>>>>>>>>>>
		if(la.getPk()!= null){
			DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot())).click(atCell(atRow(atPath(la.getName())), atColumn("Personalization Key")));
			WO.setCCombo(DOF.getCCombo(DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()))), la.getPk());	
		}
		//ff<<<<<<<<<<<
		
	}
	
	private static void handleWarning(String entry){
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
		tree.click(atPath(entry));
		sleep(1);
		if(DOF.getDialog("Information")!=null){
			DOF.getButton(DOF.getDialog("Information"), "OK").click();
		}
	}

	private static void setSimpleTypeLoadArgument(LoadArgument la) {
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
		if(la.getType()!=null){
			tree.click(atCell(atRow(atPath(la.getName())), atColumn("Datatype")));
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(la.getType());
			handleWarning(la.getName());
		}
		if(la.getPropagateTo()!=null){
			tree.click(atCell(atRow(atPath(la.getName())), atColumn("Propagate to Attribute")));
			DOF.getCCombo(tree).click();
			DOF.getPoppedUpList().click(atText(la.getPropagateTo()));
			handleWarning(la.getName());
		}
		if(la.getDefaultValue()!=null){
			tree.click(atCell(atRow(atPath(la.getName())), atColumn("Default Value")));
			DOF.getRoot().inputChars(la.getDefaultValue());
			handleWarning(la.getName());
		}
		if(la.getPk()!=null){
			tree.click(atCell(atRow(atPath(la.getName())), atColumn("Personalization Key")));
//			DOF.getCCombo(tree).click();
//			sleep(1);
//			DOF.getPoppedUpList().click(atText(la.getPk()));
			WO.setCCombo(DOF.getCCombo(tree), la.getPk());
			handleWarning(la.getName());
		}
//		tree.click(atPath(la.getName()));
//		handleWarning();
	}
	
	private static GuiSubitemTestObject tree(){
		return DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
	}

	public static LoadArgument getParameter(String string) {
		String type = TreeHelper.getCellValue(tree(), string, "Datatype");
		String dv = TreeHelper.getCellValue(tree(), string, "Default Value");
		return new LoadArgument().name(string).type(type).defaultValue(dv);
	}

}
