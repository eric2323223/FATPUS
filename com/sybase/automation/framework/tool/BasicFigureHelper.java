package com.sybase.automation.framework.tool;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Functional Test Script
 * 
 * @author pyin
 */
public class BasicFigureHelper extends RationalTestScript {
	/**
	 * Script Name : <b>BasicFigureHelper</b> Generated : <b>May 3, 2008
	 * 10:51:09 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/05/03
	 * @author pyin
	 */
	public static boolean isPointInsideFigure(Point point, TestObject figure) {
		int figureX = getX(figure);
		int figureY = getY(figure);
		int figureWidth = getWidth(figure);
		int figureHeight = getHeight(figure);
		return point.x >= figureX && point.x <= figureX + figureWidth
				&& point.y >= figureY && point.y <= figureY + figureHeight;
	}

	public static Point getRandomPointOfFigure(TestObject figure) {
		int width = getWidth(figure);
		int height = getHeight(figure);
		Random r = new Random();
		int x = r.nextInt(width + 1);
		int y = r.nextInt(height + 1);
		return new Point(x, y);
	}

	public static int getWidth(TestObject obj) {
		return parseDimension(obj, "width");
	}

	public static int getHeight(TestObject obj) {
		return parseDimension(obj, "height");
	}

	public static int getX(TestObject obj) {
		return parseDimension(obj, "x");
	}

	public static int getY(TestObject obj) {
		return parseDimension(obj, "y");
	}

	private static int parseDimension(TestObject obj, String des) {
		// System.out.println(obj.getProperty("class"));
		if (obj
				.getProperty("class")
				.toString()
				.equals(
						"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectEditPart$EMobileObjectFigure")
				|| obj
						.getProperty("class")
						.toString()
						.equals(
								"org.eclipse.gmf.runtime.diagram.ui.internal.figures.CollapseFigure")
				|| obj
						.getProperty("class")
						.toString()
						.equals(
								"com.sybase.uep.tooling.ui.diagram.extension.ui.actions.TitledResizableCompartmentFigure")
				|| obj
						.getProperty("class")
						.toString()
						.equals(
								"org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel")) {
			TestObject rectangle = (TestObject) obj.invoke("getBounds");
			String recString = rectangle.invoke("toString").toString();
			String[] numbers = recString.split(",");
			if (des.equals("width")) {
				Integer width = new Integer(numbers[2].trim());
				return width.intValue();
			}
			if (des.equals("height")) {
				Integer width = new Integer(numbers[3].trim().substring(0,
						numbers[3].trim().length() - 1));
				return width.intValue();
			}
			if (des.equals("x")) {
				Integer x = new Integer(numbers[0].substring(numbers[0]
						.indexOf("(") + 1, numbers[0].length()));
				return x.intValue();
			}
			if (des.equals("y")) {
				Integer y = new Integer(numbers[1].trim());
				return y.intValue();
			}
		} else if (obj.getProperty("class").toString().equals(
				"org.eclipse.gmf." + "runtime.diagram.ui.editpolicies."
						+ "PopupBarEditPolicy$RoundedRectangleWithTail")
				|| obj
						.getProperty("class")
						.toString()
						.equals(
								"org.eclipse.gmf.runtime."
										+ "diagram.ui.editpolicies.PopupBarEditPolicy$PopupBarLabelHandle")) {
			TestObject rectangle = (TestObject) obj.invoke("getBounds");
			String recString = rectangle.invoke("toString").toString();
			String[] numbers = recString.split(",");
			if (des.equals("width")) {
				Integer width = new Integer(numbers[2].trim());
				return width.intValue();
			}
			if (des.equals("height")) {
				Integer width = new Integer(numbers[3].trim().substring(0,
						numbers[3].trim().length() - 1));
				return width.intValue();
			}
			if (des.equals("x")) {
				Integer x = new Integer(numbers[0].substring(numbers[0]
						.indexOf("(") + 1, numbers[0].length()));
				return x.intValue();
			}
			if (des.equals("y")) {
				Integer y = new Integer(numbers[1].trim());
				return y.intValue();
			}

		} else {
			// if
			// (obj.getProperty("class").toString().equals("org.eclipse.draw2d.FigureCanvas"))
			// {
			Rectangle rectangle = (Rectangle) obj.invoke("getBounds");
			if (des.equals("width"))
				return rectangle.width;
			if (des.equals("height"))
				return rectangle.height;
			if (des.equals("x"))
				return rectangle.x;
			if (des.equals("y"))
				return rectangle.y;
		}
		return -1;
	}
}
