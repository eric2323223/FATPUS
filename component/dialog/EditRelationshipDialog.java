package component.dialog;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.Relationship;

public class EditRelationshipDialog extends RationalTestScript{
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Edit relationship");
	}

	public static void setRelationship(Relationship relationship) {
		if(relationship.getName()!=null){
			DOF.getTextField(dialog(), "Attribute name:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(relationship.getName());
		}
		if(relationship.getComposite()!=null){
			if(relationship.getComposite().equals("true")){
				((ToggleGUITestObject)DOF.getButton(dialog(), "Compo&site")).clickToState(SELECTED);
			}else{
				((ToggleGUITestObject)DOF.getButton(dialog(), "Compo&site")).clickToState(NOT_SELECTED);
			}
		}
	}

	public static void ok() {
		DOF.getButton(dialog(), "OK").click();
	}

	public static Relationship getRelationship() {
//		String name = DOF.getTextField(dialog(), "Atrribute name:").getProperty("text").toString();
		String composite = DOF.getButton(dialog(), "Compo&site").invoke("getSelection").toString().equals("true")?"true":"false";
		Relationship result = new Relationship().composite(composite);
		ok();
		return result;
	}

}
