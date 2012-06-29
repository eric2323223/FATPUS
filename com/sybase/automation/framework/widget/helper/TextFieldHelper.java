package com.sybase.automation.framework.widget.helper;

import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

public class TextFieldHelper extends RationalTestScript {

	public static void hightLightText(TopLevelTestObject parent, TextScrollTestObject styledText, String highLighted){
		styledText.click();
		String content = styledText.getProperty("text").toString();
		int startPos = content.indexOf(highLighted);
		parent.inputKeys("{HOME}");
		for(int i=0;i<startPos;i++){
			parent.inputKeys("{ExtRight}");
		}
		for(int i=0;i<highLighted.length();i++){
			parent.inputKeys("+{ExtRight}");
		}
	}
	

}
