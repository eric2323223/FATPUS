package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import java.awt.Point;
import com.sybase.automation.framework.widget.interfaces.IFigureCanvas;


/**
 * @author yongliu
 *  
 */

public class FigureCanvas implements IFigureCanvas {
	private GuiTestObject _FigureCanvas;

	// private GuiTestObject button;
	
	public FigureCanvas(GuiTestObject figureCanvas) {
		_FigureCanvas = figureCanvas;
	}

	
	public void clickAt(int x, int y) {
		_FigureCanvas.click(new Point(x,y));
	}

	
	public void rightClickAt(int x, int y) {
		_FigureCanvas.click(RationalTestScript.RIGHT,new Point(x,y));
	}

}
