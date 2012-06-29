package com.sybase.automation.framework.tool;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description : Functional Test Script
 * 
 * @author pyin
 */
public class MobileObjectFigureHelper extends RationalTestScript {
	/**
	 * Script Name : <b>MobileObjectFigureHelper</b> Generated : <b>Apr 29,
	 * 2008 7:42:46 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/04/29
	 * @author pyin
	 */
	public static final String MOBILEOBJECT_ATTRIBUTE_ANCESTORLINE = "Figure->Viewport->AnimatableScrollPane->ResizableCompartmentFigure->EMobileObjectEditPart$EMobileObjectFigure->DefaultSizeNodeFigure->DiagramEditPart$1->BorderItemsAwareFreeFormLayer->FreeformLayeredPane->RenderedDiagramRootEditPart$DiagramRenderedScalableFreeformLayeredPane->FreeformLayeredPane->FreeformViewport->LightweightSystem$RootFigure";

	public static final String MOBILEOBJECT_MEHTOD_ANCESTORLINE = "Figure->Viewport->AnimatableScrollPane->ResizableCompartmentFigure->EMobileObjectEditPart$EMobileObjectFigure->DefaultSizeNodeFigure->DiagramEditPart$1->BorderItemsAwareFreeFormLayer->FreeformLayeredPane->RenderedDiagramRootEditPart$DiagramRenderedScalableFreeformLayeredPane->FreeformLayeredPane->FreeformViewport->LightweightSystem$RootFigure";

	private static HashMap attributes = new HashMap();

	private static HashMap methods = new HashMap();

	public static void explore(TestObject mof) {
		Queue queue = new LinkedList();
		try {
			// TestObject figure = (TestObject) mbf.invoke("getChildren");
			queue.remove(mof);
//			queue.enqueue(mof);
			// NativeInvoker.printMethods(figure);
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
							.toString()
							.equals(
									"com.sybase.uep.tooling.ui.diagram.edit.parts.EAttributeEditPart$AttributeNameFigure")) {
						// System.out.println(DOF.getAncestorLine(obj));
						attributes.put(obj.invoke("getText"), obj);
					}
					if (obj
							.getProperty("class")
							.toString()
							.equals(
									"com.sybase.uep.tooling.ui.diagram.edit.parts.EOperationEditPart$OperationNameFigure")) {
						// System.out.println(DOF.getAncestorLine(obj));
						methods.put(obj.invoke("getText"), obj);
					}

					// System.out.println(obj.getProperty("class")+"->"+DOF.getAncestorLine(obj));
					queue.remove(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] getAttributes(TestObject mof) {
		String[] att = new String[attributes.keySet().size()];
		for (int i = 0; i < att.length; i++) {
			att[i] = (String) attributes.keySet().toArray()[i];
		}
		return att;
	}

	public static String[] getMethods(TestObject mof) {
		String[] mth = new String[methods.keySet().size()];
		for (int i = 0; i < mth.length; i++) {
			mth[i] = (String) methods.keySet().toArray()[i];
		}
		return mth;
	}

	public static Point getCenterPoint(TestObject mof) {
		int x = BasicFigureHelper.getX(mof) + BasicFigureHelper.getWidth(mof)
				/ 2;
		int y = BasicFigureHelper.getY(mof) + BasicFigureHelper.getHeight(mof)
				/ 2;
		return new Point(x, y);
	}

	/**
	 * @Desc Add Attribute From Popup Rectangle
	 * @paramName MBO name
	 * @paramVals String
	 * @author qma
	 */
	public static void addAttriFromPopupRectangle(String mboName) {
		Point p = ObjectDiagramHelper.getVacantCanvasPoint();
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(p.x, p.y));
		TestObject mbf = ObjectDiagramHelper.getMobileObjectFigure(mboName);
		System.out.println("mbf==null?" + (mbf == null));
		Point pp = MobileObjectFigureHelper.getCenterPoint(mbf);
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pp.x, pp.y));
		TestObject popup = ObjectDiagramHelper
				.getFigureByClass("org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy$RoundedRectangleWithTail");
		if (popup != null) {
			Point pt = MobileObjectFigureHelper.getCenterLeftPoint(popup);
			DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pt.x, pt.y));
		}
	}

	/**
	 * @Desc Add Operation From Popup Rectangle
	 * @paramName MBO name
	 * @paramVals String
	 * @author qma
	 */
	public static void addOpFromPopupRectangle(String mboName) {
		Point p = ObjectDiagramHelper.getVacantCanvasPoint();
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(p.x, p.y));
		TestObject mbf = ObjectDiagramHelper.getMobileObjectFigure(mboName);
		Point pp = MobileObjectFigureHelper.getCenterPoint(mbf);
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pp.x, pp.y));
		TestObject popup = ObjectDiagramHelper
				.getFigureByClass("org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy$RoundedRectangleWithTail");
		Point pt = MobileObjectFigureHelper.getCenterRightPoint(popup);
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pt.x, pt.y));
	}

	/**
	 * @Desc Add Operation From Popup Rectangle
	 * @paramName mboName
	 * @paramDesc MBO name
	 * @paramName section
	 * @paramDesc attribute or operation
	 * @paramName clps
	 * @paramDesc true for collapse, false for un-collapse
	 * @author qma
	 */
	public static void setCollapse(String mboName, String section, boolean clps) {
		final String attribute = "att.*";
		// final String operation= "op.*";
		Point p = ObjectDiagramHelper.getVacantCanvasPoint();
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(p.x, p.y));
		TestObject mbf = ObjectDiagramHelper.getMobileObjectFigure(mboName);
		Point pp = MobileObjectFigureHelper.getCenterPoint(mbf);
		DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pp.x, pp.y));
		TestObject[] collapseFigure = new TestObject[3];
		int cfi = 0;
		try {
			TestObject contents = (TestObject) DOF.getActiveObjectDiagramCanvas()
					.invoke("getContents");
			Queue queue = new LinkedList();
			queue.remove(contents);
			while (!queue.isEmpty()) {
				TestObject to = (TestObject) queue.poll();
				TestObject children = (TestObject) to.invoke("getChildren");
				int length = ((Integer) children.invoke("size")).intValue();
				// System.out.println("length: "+length);
				for (int i = 0; i < length; i++) {
					TestObject obj = (TestObject) NativeInvoker.invoke(
							children, "get", i);
					queue.remove(obj);
					if (obj.getProperty("class").equals(
							"org.eclipse.gmf.runtime.diagram.ui."
									+ "internal.figures.CollapseFigure")) {
						collapseFigure[cfi++] = obj;
						System.out.println("Collapsed: "
								+ (Boolean) obj.invoke("isCollapsed"));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (MobileObjectFigureHelper.getCenterPoint(collapseFigure[0]).y > MobileObjectFigureHelper
				.getCenterPoint(collapseFigure[1]).y) {
			TestObject temp = collapseFigure[0];
			collapseFigure[0] = collapseFigure[1];
			collapseFigure[1] = temp;
		}
		if (section.matches(attribute)) {
			Point pt = MobileObjectFigureHelper
					.getCenterPoint(collapseFigure[0]);
			boolean collapsed = collapseFigure[0].invoke("isCollapsed")
					.toString().equals("true");

			if (collapsed != clps) {
				DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pt.x, pt.y));
			}
		} else {
			Point pt = MobileObjectFigureHelper
					.getCenterPoint(collapseFigure[1]);
			boolean collapsed = collapseFigure[1].invoke("isCollapsed")
					.toString().equals("true");

			if (collapsed != clps) {
				DOF.getActiveObjectDiagramCanvas().click(LEFT, atPoint(pt.x, pt.y));
			}
		}
	}

	public static void collapseOperation() {
		throw new RuntimeException(
				"MobileObjectFigureHelper.collapseOperation: Not implemented.");
	}

	public static Point getCenterLeftPoint(TestObject mof) {
		int x = BasicFigureHelper.getX(mof) + BasicFigureHelper.getWidth(mof)
				/ 4;
		int y = BasicFigureHelper.getY(mof) + BasicFigureHelper.getHeight(mof)
				/ 2;
		return new Point(x, y);
	}

	public static Point getCenterRightPoint(TestObject mof) {
		int x = BasicFigureHelper.getX(mof) + 3
				* BasicFigureHelper.getWidth(mof) / 4;
		int y = BasicFigureHelper.getY(mof) + BasicFigureHelper.getHeight(mof)
				/ 2;
		return new Point(x, y);
	}
	
	

	public static String getName(TestObject mobileObject) {
		TestObject children = (TestObject) mobileObject.invoke("getChildren");
		int length = ((Integer) children.invoke("size")).intValue();
		for (int i = 0; i < length; i++) {
			if ((NativeInvoker.invoke(children, "get", i))
					.getProperty("class")
					.equals(
							"org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel")) {
				String rst = NativeInvoker.invoke(children, "get", i).invoke(
						"getText").toString();

				return rst.substring(0, rst.length() - 2);
			}
		}
		return null;

	}

	public static void printStore() {
		System.out.println(attributes);
		System.out.println(methods);
	}

	public static Point getSelectMBOPoint(TestObject mof) {
		return getCenterPoint(mof);
//		return getTopRightPoint(mof);
	}

	public static Point getAttributesAreaPoint(TestObject mof) {
		TestObject attributes = getAttributesObject(mof);
		return getTopRightPoint(attributes);
	}

	public static Point getOperationsAreaPoint(TestObject mof) {
		TestObject operations = getOperationsObject(mof);
		return getTopRightPoint(operations);
	}

	public static Point getTitleAreaPoint(TestObject mof) {
		TestObject title = getTitleObject(mof);
		return getTopRightPoint(title);
	}

	private static Point getTopRightPoint(TestObject figure) {
		int x = BasicFigureHelper.getX(figure)
				+ BasicFigureHelper.getWidth(figure) - 10;
		int y = BasicFigureHelper.getY(figure) + 5;
		return new Point(x, y);
	}

	public static TestObject getTitleObject(TestObject mof) {
		return getChild(mof, 0);
	}
	
	public static TestObject getAttributesObject(TestObject mof) {
		return getChild(mof, 1);
	}

	public static TestObject getOperationsObject(TestObject mof) {
		return getChild(mof, 2);
	}

	private static TestObject getChild(TestObject mof, int index) {
		TestObject children = (TestObject) mof.invoke("getChildren");
		return (TestObject) NativeInvoker.invoke(children, "get", index);
	}

	public static void selectMBO(TestObject mof) {
		GefEditPartTestObject canvas = DOF.getActiveObjectDiagramCanvas();
		ObjectDiagramHelper.scrollCanvasToViewMBO(getName(mof));
		Point vacant = ObjectDiagramHelper.getVacantCanvasPoint();
		Point mofClientCenter = ObjectDiagramHelper
				.transferPointToClient(getSelectMBOPoint(mof));
		canvas.click(vacant);
		canvas.click(mofClientCenter);
	}

	public static void selectAttributesArea(TestObject mof) {
		selectMBO(mof);
		clickAttributesArea(mof);
	}

	public static void clickAttributesArea(TestObject mof) {
		GefEditPartTestObject canvas = DOF.getActiveObjectDiagramCanvas();
		Point attrAreaPoint = ObjectDiagramHelper
				.transferPointToClient(getAttributesAreaPoint(mof));
		canvas.click(attrAreaPoint);
	}

	public static void selectOperationsArea(TestObject mof) {
		selectMBO(mof);
		clickOperationsArea(mof);
	}

	public static void selectTitleArea(TestObject mof) {
		selectMBO(mof);
		clickTitleArea(mof);
	}

	public static void selectTitleText(TestObject mof) {
		selectTitleArea(mof);
		sleep(1);
		clickTitleArea(mof);
	}

	public static void changeTitleText(TestObject mof, String newName) {
		selectTitleText(mof);
		getScreen().getActiveWindow().inputKeys("a{BACKSPACE}");
		getScreen().getActiveWindow().inputChars(newName);
		getScreen().getActiveWindow().inputKeys("{ENTER}");
	}

	public static void clickTitleArea(TestObject mof) {
		GefEditPartTestObject canvas = DOF.getActiveObjectDiagramCanvas();
		Point operAreaPoint = ObjectDiagramHelper
				.transferPointToClient(getTitleAreaPoint(mof));
		canvas.click(operAreaPoint);
	}

	public static void clickOperationsArea(TestObject mof) {
		GefEditPartTestObject canvas = DOF.getActiveObjectDiagramCanvas();
		Point operAreaPoint = ObjectDiagramHelper
				.transferPointToClient(getOperationsAreaPoint(mof));
		canvas.click(operAreaPoint);
	}
	
	public static List getMBOTips(TestObject mof) {
		List l = new ArrayList();
		TestObject title = getTitleObject(mof);
		TestObject toolTip = (TestObject) title.invoke("getToolTip");
		TestObject children = (TestObject) toolTip.invoke("getChildren");
		int length = ((Integer) children.invoke("size")).intValue();
		for (int i = 0; i < length; i++) {
			TestObject child = (TestObject) NativeInvoker.invoke(children,
					"get", i);
			l.add(child.getProperty("text"));
		}
		return l;
	}
	
	public static TestObject getTitleImage(TestObject mof, int index) {
		TestObject title = getTitleObject(mof);
		return (TestObject) NativeInvoker.invoke(title, "getIcon", index);
	}
}