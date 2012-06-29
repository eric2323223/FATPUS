package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class OperationCacheUpdatePolicyTab extends RationalTestScript{

	public static void setApplyResultToCache(TestObject parent, boolean value){
		if(value){
			((ToggleGUITestObject)DOF.getButton(parent, "A&pply results to the cache")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(parent, "A&pply results to the cache")).clickToState(NOT_SELECTED);
			
		}
	}
}
