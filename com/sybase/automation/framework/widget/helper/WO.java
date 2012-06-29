package com.sybase.automation.framework.widget.helper;

import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.common.FigureHelper;

public class WO extends RationalTestScript{
	public static void setTextField(TopLevelTestObject parent, GuiTestObject textField, String text){
		if(text!=null){
			textField.click(atPoint(3,3));
//			textField.click();
			parent.inputKeys(SpecialKeys.CLEARALL);
			parent.inputChars(text);
		}
	}
	
	public static int getWidth(TestObject to){
		Rectangle rect = (Rectangle)to.invoke("getBounds");
		return (int)rect.getWidth();
	}
	
	public static int getHeight(TestObject to){
		Rectangle rect = (Rectangle)to.invoke("getBounds");
		return (int)rect.getHeight();
	}
	
	public static void setCCombo(ScrollTestObject ccombo, String item){
		if(item!=null){
//			System.out.println(FigureHelper.getBounds(ccombo).getWidth());
			ccombo.click(atPoint((int)FigureHelper.getBounds(ccombo).getWidth()-5, FigureHelper.getBounds(ccombo).height/2));
			DOF.getPoppedUpList().click(atText(item));
		}
	}

	public static String getText(TestObject textField) {
		if(textField!=null){
			return textField.getProperty("text").toString();
		}else{
			return null;
		}
	}

	public static String getSelection(TestObject obj) {
		if(obj!=null){
			return obj.invoke("getSelection").toString();
		}else{
			return null;
		}
	}

	public static void setCombo(GuiSubitemTestObject combo, String text) {
		combo.click();
		sleep(0.5);
		combo.click(atText(text));
		
	}

	public static void checkButton(GuiTestObject button, boolean b) {
		if(b){
			((ToggleGUITestObject)button).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)button).clickToState(NOT_SELECTED);
		}
		
		
	}

	public static void clickList(GuiSubitemTestObject combo) {
		Rectangle rec = FigureHelper.getBounds(combo);
		combo.click(atPoint(rec.width-5, rec.height/2));
		
	}
}
