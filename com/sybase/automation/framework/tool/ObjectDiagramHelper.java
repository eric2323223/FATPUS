package com.sybase.automation.framework.tool;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.Queue;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description : Functional Test Script
 * 
 * @author pyin
 */
public class ObjectDiagramHelper extends RationalTestScript {
	/**
	 * Script Name : <b>ObjectDiagramHelper</b> Generated : <b>Apr 29, 2008
	 * 2:35:38 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/04/29
	 * @author pyin
	 */
	private static HashMap mobileObjectFigures = new HashMap();

	private static HashMap relationshipFigures = new HashMap();

	private static void reset() {
		mobileObjectFigures.clear();
	}

	public static Point getVacantCanvasPoint() {
		Point p = null;
		boolean isPointValid;
		while (true) {
			isPointValid = true;
			p = BasicFigureHelper.getRandomPointOfFigure(DOF
					.getActiveObjectDiagramCanvas());
			Iterator it = mobileObjectFigures.values().iterator();
			while (it.hasNext()) {
				TestObject mof = (TestObject) it.next();
				if (mof != null) {
					if (BasicFigureHelper.isPointInsideFigure(
							transferPointToAbsolute(p), mof)) {
						isPointValid = false;
						break;
					}
				}
			}
			if (isPointValid) {
				return p;
			}
		}
	}

	public static void exploreCanvas() {
		unregisterAll();
		reset();
		try {
			Queue queue = new LinkedList();
			TestObject canvas = DOF.getActiveObjectDiagramCanvas();
			// System.out.println(DOF.getAncestorLine(canvas));
			TestObject figure = (TestObject) canvas.invoke("getContents");
			queue.remove(figure);
			while (!queue.isEmpty()) {
				TestObject to = (TestObject) queue.poll();
				// String al = DOF.getAncestorLine(to);
				// System.out.println(to.getProperty("class").toString()+"->"+al);
				TestObject children = (TestObject) to.invoke("getChildren");
				int length = ((Integer) children.invoke("size")).intValue();
				// System.out.println(length);
				for (int i = 0; i < length; i++) {
					TestObject obj = (TestObject) NativeInvoker.invoke(
							children, "get", i);
					if (obj
							.getProperty("class")
							.equals(
									"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectEditPart$EMobileObjectFigure")) {
						String name = MobileObjectFigureHelper.getName(obj);
						mobileObjectFigures.put(name, obj);
					}
					if (obj
							.getProperty("class")
							.equals(
									"com.sybase.uep.tooling.ui.diagram.edit.parts.EAssociationEditPart$EAssociationFigure")) {
						String name = RelationshipFigureHelper.getName(obj);
						relationshipFigures.put(name, obj);

					}
					queue.remove(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exploreCanvas1() {
		unregisterAll();
		reset();
		TestObject figure = (TestObject) DOF.getActiveObjectDiagramCanvas().invoke(
				"getContents");
		doExplorer(figure, "");
	}

	private static void doExplorer(TestObject obj, String tab) {
		try {
			String cls = (String) obj.getProperty("class");
			System.out.println(tab + cls);
			if (cls
					.equals("com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectEditPart$EMobileObjectFigure")) {
				String name = MobileObjectFigureHelper.getName(obj);
				mobileObjectFigures.put(name, obj);
			} else if (cls
					.equals("com.sybase.uep.tooling.ui.diagram.edit.parts.EAssociationEditPart$EAssociationFigure")) {
				String name = RelationshipFigureHelper.getName(obj);
				relationshipFigures.put(name, obj);
			}
			TestObject children = (TestObject) obj.invoke("getChildren");
			int length = ((Integer) children.invoke("size")).intValue();
			for (int i = 0; i < length; i++) {
				TestObject child = (TestObject) NativeInvoker.invoke(children,
						"get", i);
				doExplorer(child, "  " + tab);
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	/**
	 * @Desc Get the figure by its class name, mostly used for getting the popup
	 *       figure
	 * @paramName class name
	 * @paramVals String
	 * @author qma
	 */

	public static TestObject getFigureByClass(String classname) {
		TestObject popup = null;

		try {
			Queue queue = new LinkedList();
			TestObject canvas = DOF.getActiveObjectDiagramCanvas();
			// System.out.println(DOF.getAncestorLine(canvas));
			TestObject figure = (TestObject) canvas.invoke("getContents");
			queue.remove(figure);
			while (!queue.isEmpty()) {
				boolean brk = false;
				TestObject to = (TestObject) queue.poll();
				TestObject children = (TestObject) to.invoke("getChildren");
				int length = ((Integer) children.invoke("size")).intValue();
				// System.out.println("length: "+length);
				for (int i = 0; i < length; i++) {
					TestObject obj = (TestObject) NativeInvoker.invoke(
							children, "get", i);
					queue.remove(obj);

					System.out.println(obj.getProperty("class"));

					if (obj.getProperty("class").equals(classname)) {
						brk = true;
						popup = obj;
						break;
					}
				}
				if (true == brk) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return popup;
	}

	public static TestObject getRelationshipFigure(String name) {
		return (TestObject) relationshipFigures.get(name);
	}

	public static TestObject getMobileObjectFigure(String name) {
		System.out.println((TestObject) mobileObjectFigures.get(name));
		return (TestObject) mobileObjectFigures.get(name);
	}

	/**
	 * @Desc Add MBO From Popup Figure
	 * @paramName
	 * @paramVals
	 * @author qma
	 */
	public static void addMBOFromPopupFigure() {
		Point p = getVacantCanvasPoint();
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(p.x, p.y));
		sleep(1);
		TestObject popup = getFigureByClass("org.eclipse.gmf.runtime.diagram.ui.editpolicies."
				+ "PopupBarEditPolicy$PopupBarLabelHandle");
		Point pt = MobileObjectFigureHelper.getCenterPoint(popup);
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pt.x, pt.y));
	}

	public static int size() {
		return mobileObjectFigures.size();
	}

	public static void printStore() {
		System.out.println(mobileObjectFigures);
		System.out.println(relationshipFigures);
	}

	public static Point getScrollPoint() {
		TestObject canvas = DOF.getActiveObjectDiagramCanvas();
		Integer x = (Integer) ((TestObject) canvas.invoke("getHorizontalBar"))
				.invoke("getSelection");
		Integer y = (Integer) ((TestObject) canvas.invoke("getVerticalBar"))
				.invoke("getSelection");
		return new Point(x.intValue(), y.intValue());
	}

	public static Point transferPointToClient(Point absolutePoint) {
		Point scrollPoint = getScrollPoint();
		return new Point(absolutePoint.x - scrollPoint.x, absolutePoint.y
				- scrollPoint.y);
	}

	public static Point transferPointToAbsolute(Point clientPoint) {
		Point scrollPoint = getScrollPoint();
		return new Point(clientPoint.x + scrollPoint.x, clientPoint.y
				+ scrollPoint.y);
	}

	public static void scrollCanvasToViewMBO(String mboName) {
		TestObject mbo = getMobileObjectFigure(mboName);
		TestObject canvas = DOF.getActiveObjectDiagramCanvas();
		Point mboCenter = MobileObjectFigureHelper.getCenterPoint(mbo);
		Point canvasSize = (Point) canvas.getProperty("size");
		Object[] params = { Integer.valueOf(mboCenter.x - (canvasSize.x / 2)),
				Integer.valueOf(mboCenter.y - (canvasSize.y / 2)) };
		canvas.invoke("scrollSmoothTo", "(II)V", params);
	}
}

