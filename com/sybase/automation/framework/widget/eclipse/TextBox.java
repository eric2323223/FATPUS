package com.sybase.automation.framework.widget.eclipse;

import java.io.File;

import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.Utilities;
import com.sybase.automation.framework.core.exception.VerificationFailure;
import com.sybase.automation.framework.core.helper.ComponentHelper;
import com.sybase.automation.framework.widget.interfaces.ITextBox;

/**
 * Widget to encapsulate the behavior the TextField Test Object
 * 
 * @author xfu <br>
 * 
 * Target Object: org.eclipse.swt.widgets.Text <br>
 * Example: TextField tt=new TextField(Text1()); <br>
 * tt.setText("hello World"); <br>
 * System.out.println(tt.getText()); <br>
 */
public class TextBox extends ComponentHelper implements ITextBox{// YongLiu, Nov 13, 2007.
	private TextScrollTestObject _TextBox;

	public TextBox(TextScrollTestObject textBox) {
		_TextBox = textBox;
	}

	public String getText() {
		return _TextBox.getText();
	}

	public void clearText() {
		_TextBox.setText("");

	}

	public void setText(String s) {
		//_TextBox.setText(s);
		pasteText(s);
	}

	public void pasteText(String s) {
		ClipBoardUtil.setClipboardText(s);
		clearText();
		/* paste the contents into textbox */
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("^v");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.ITestBox#getLines()
	 */
	public String[] getLines() {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.ITestBox#getLines(int, int)
	 */
	public String getLines(int start, int end) {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.ITestBox#insertLines(int,
	 *      java.lang.String)
	 */
	public void insertLines(int line, String txt) {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ITextBox#verifyText(java.lang.String)
	 */
	public void verifyText(String expected) {
		if (!expected.equals(getText())) {
			RationalTestScript.logTestResult(new VerificationFailure(expected,
					getText()).getMessage(), false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ITextBox#verifyText(java.lang.String,
	 *      boolean)
	 */
	public void verifyText(String expected, boolean fromFile) {
		if (false)
			verifyText(expected);
		else {
			String actual = normalize(getText());
			File vpFile = new File(Utilities.getProjectRoot() + expected);
			if (!vpFile.exists()) {
				System.out.println("write new content");
				FileUtil.writeToFile(vpFile, actual);
			}
			String expectedValue = normalize(FileUtil.readFromFile(vpFile));
			String actualValue = normalize(actual);
			if (!expectedValue.equals(actualValue)) {
					RationalTestScript.logTestResult(new VerificationFailure(
							expectedValue, actualValue).getMessage(), false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ITextBox#createVPFile(java.lang.String,
	 *      java.lang.String)
	 */
	public void createVPFile(String actual, String vpPath) {

	}

}
