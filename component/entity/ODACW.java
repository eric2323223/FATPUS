package component.entity;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

public class ODACW extends ACW {

	@Override
	public void start(String string) {
		TestObject canvas = DOF.getObjectDiagramCanvas();
		DOF.getMboFigure(canvas, string).click(RationalTestScript.RIGHT, RationalTestScript.atPoint(5,5));
		DOF.getContextMenu().click(RationalTestScript.atPath("New->Attribute"));
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	
	public void setName(String str){
		DOF.getRoot().inputChars(str);
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
//		super.setName(str);
	}

}
