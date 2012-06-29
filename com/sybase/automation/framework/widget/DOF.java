package com.sybase.automation.framework.widget;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.PaletteGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ShellTestObject;
import com.rational.test.ft.object.interfaces.TabitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.value.MethodInfo;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.ITestDataTreeNodes;
import com.rational.test.util.regex.Regex;
import com.sybase.automation.framework.tool.NativeInvoker;
import com.sybase.automation.framework.tool.StringUtil;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.RectangleHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.WorkFlow;

/**
 * Description : Functional Test Script
 * 
 * @author xjf
 */
public class DOF extends RationalTestScript {
	/**
	 * Script Name : <b>DOF </b> Generated : <b>Nov 13, 2007 5:30:35 PM </b>
	 * Description : Functional Test Script Original Host : WinNT Version 5.1
	 * Build 2600 (S)
	 * 
	 * @since 2007/11/13
	 * @author pyin
	 */

	/**
	 * Script Name : <b>ObjectFindHelper </b> Generated : <b>Oct 16, 2007
	 * 4:45:51 PM </b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2007/10/16
	 * @author pyin
	 */

	public static final int WAIT_ROOT_COUNT = 10;
	public final static String ANCESTORS_OF_SINGLE_TEXT_SQLRESULT = "Composite->Composite->SashForm->Composite->Composite->Composite->Composite->Shell";
	public final static String ANCESTORS_OF_EDITOR_TAB = "Canvas->Composite->Composite->Composite->CTabFolder->Composite->Composite->Composite->Composite->Composite->Shell";
	public final static String ANCESTORS_OF_SINGLE_GRID_SQLRESULT = "Composite->ScrolledComposite->Composite->Composite->SashForm->Composite->Composite->Composite->Composite->Shell";
	public final static String ANCESTORS_OF_OPENED_FILE = "Canvas->Composite->Composite->Composite->Composite->Composite->Composite->Composite->Shell";

	public void testMain(Object[] args) {

	}

	public static ScrollTestObject getFigureCanvas() {
		TestObject[] to = getRoot().find(
				atDescendant("class", "org.eclipse.draw2d.FigureCanvas"));
		if (to != null) {
			return (ScrollTestObject) to[0];
		} else
			return null;
	}

	public static TestObject getSashForm(TestObject parent) {
		TestObject[] forms = parent.find(atDescendant("class",
				"org.eclipse.swt.custom.SashForm"));
		if (forms != null) {
			return forms[0];
		}
		return null;
	}

	public static TopLevelTestObject getRoot() {
		Regex workspaceRegex = new Regex("Mobile Development");
		TopLevelTestObject rst = null;
		int counter = 0;
		while (rst == null && counter < WAIT_ROOT_COUNT) {
			try {
				rst = getAppWindow(workspaceRegex);
			} catch (Exception e) {
			}
			if (rst != null) {
				break;
			}
			counter++;
			sleep(0.5);
		}
		return rst;
	}

	public static String getSignature(TestObject to, String methodName) {
		MethodInfo[] m = to.getMethods();
		for (int i = 0; i < m.length; i++) {
			if (m[i].getName().equalsIgnoreCase(methodName)) {
				return m[i].getSignature();
			}
		}
		return null;
	}

	public static Object invoke(TestObject to, String command, int param) {
		String sig = getSignature(to, command);
		Object[] args = new Object[1];
		args[0] = new Integer(param);
		return to.invoke(command, sig, args);
	}

	public static Object invoke(TestObject to, String command, String param) {
		String sig = getSignature(to, command);
		Object[] args = new Object[1];
		args[0] = param;
		return to.invoke(command, sig, args);
	}

	public static GuiSubitemTestObject getMenu() {
		TestObject[] menues = getRoot().find(
				atDescendant("class", "org.eclipse.swt.widgets.Menu"));
		// System.out.println(menues.length);
		if (menues != null && menues.length > 0) {
			return (GuiSubitemTestObject) menues[0];
		} else
			return null;
	}

	public static GuiSubitemTestObject getCombo(TestObject parent, String label) {
		TestObject[] combos = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Combo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (combos[i].getProperty(".priorLabel") != null
						&& isVisible(combos[i])
						&& combos[i].getProperty(".priorLabel").toString()
								.equalsIgnoreCase(label)) {
					return (GuiSubitemTestObject) combos[i];
				}
			}
		}
		return null;
	}
	
	public static GuiSubitemTestObject getComboByItem(TestObject parent,String item) {
		TestObject[] combos = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Combo"));
		for(TestObject combo:combos){
			String items = combo.getProperty(".itemText").toString();
			items = items.substring(1, items.length()-1);
			String[] names = items.split(",");
			for (String name : names) {
				if (name.equals(item)) {
					return (GuiSubitemTestObject) combo;
				}
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getComboByBounds(TestObject parent,Rectangle rectangle) {
		TestObject[] combos = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Combo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (isVisible(combos[i])) {
					String des = combos[i].getProperties().get("bounds")
							.toString();
					Pattern r = Pattern.compile("(\\d+)");
					Matcher m = r.matcher(des);
					ArrayList<String> matches = new ArrayList<String>();
					while (m.find()) {
						matches.add(m.group());
					}
					if (matches.size() == 4) {
						int x = new Integer(matches.get(0)).intValue();
						int y = new Integer(matches.get(1)).intValue();
						int width = new Integer(matches.get(2)).intValue();
						int height = new Integer(matches.get(3)).intValue();
						if (x == (int) rectangle.getX()
								&& y == (int) rectangle.getY()
								&& width == (int) rectangle.getWidth()
								&& height == (int) rectangle.getHeight()) {
							return (GuiSubitemTestObject) combos[i];
						}
					}

				}
			}
			return null;
		}
		return null;
	}

	public static GuiSubitemTestObject getCombo(TestObject parent) {

		TestObject[] combos = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Combo"));
		// System.out.println(combos.length);
		if (combos != null && combos.length > 0) {
			// System.out.println(NativeInvoker.invoke(combos[0],"getItem",0));
			for(TestObject combo:combos){
				if(isVisible(combo)){
					return (GuiSubitemTestObject) combo;
				}
			}
		} 
		return null;
	}
	
	public static TestObject getCanvas(TestObject parent) {
		TestObject[] canvas = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Canvas"));
		if (canvas != null && canvas.length > 0) {
			for(TestObject obj:canvas){
				if(isVisible(obj)){
					return  obj;
				}
			}
		} 
		return null;
	}

	public static GuiSubitemTestObject getComboByToolTip(TestObject parent,
			String toolTipText, Boolean visible) {
		try {
			TestObject[] combos = parent.find(atDescendant("class",
					"org.eclipse.swt.widgets.Combo"));
			if (combos != null && combos.length > 0) {
				for (int i = 0; i < combos.length; i++) {
					if (new Boolean(combos[i].invoke("isVisible").toString())
							.booleanValue() == visible
							&& (combos[i].getProperties().get("toolTipText") != null)
							&& combos[i].getProperty("toolTipText").equals(
									toolTipText)) {
						// for (Iterator itr =
						// combos[i].getProperties().keySet()
						// .iterator(); itr.hasNext();) {
						// Object key = (Object) itr.next();
						// Object value = (Object) combos[i].getProperties()
						// .get(key);
						// System.out.println(key + "--" + value);
						// }
						// System.out.println("******************************");
						return (GuiSubitemTestObject) combos[i];
					}
				}
				return null;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static GuiSubitemTestObject getComboByItem(TestObject parent,
			String itemText, Boolean visible) {
		try {
			TestObject[] combos = parent.find(atDescendant("class",
					"org.eclipse.swt.widgets.Combo"));
			if (combos != null && combos.length > 0) {
				for (int i = 0; i < combos.length; i++) {
					if (new Boolean(combos[i].invoke("isVisible").toString())
							.booleanValue() == visible
							&& (combos[i].getProperties().get(".itemText") != null)
							&& combos[i].getProperties().get(".itemText")
									.toString().contains(itemText)) {
						return (GuiSubitemTestObject) combos[i];
					}
				}
				return null;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean ifDialogExist(String name) {
		TestObject[] dialogs = RationalTestScript.find(RationalTestScript
				.atDescendant(".domain", "Java"));
		if (dialogs != null && dialogs.length > 0) {

			for (int i = 0; i < dialogs.length; i++) {
				if (dialogs[i].getObjectClassName().equalsIgnoreCase(
						"org.eclipse.swt.widgets.Shell")
						&& dialogs[i].getProperty(".captionText").toString()
								.equalsIgnoreCase(name))
					return true;
			}
		}
		return false;

	}

	public static boolean ifTreeItemExist(TestObject tree, String path) {
		if (path != null && !path.startsWith("->") && !path.endsWith("->")) {
			// System.out.println("here!");
			String[] items = path.split("->");
			// for (int m = 0; m < items.length; m++) {
			// System.out.print(items[m] + " ");
			// }
			ITestDataTree cdTree;
			ITestDataTreeNodes cdTreeNodes;
			ITestDataTreeNode[] cdTreeNode;

			cdTree = (ITestDataTree) tree.getTestData("tree");
			cdTreeNodes = cdTree.getTreeNodes();
			cdTreeNode = cdTreeNodes.getRootNodes();
			if (cdTreeNode == null) {
				// System.out.println("cdTreeNode is null");
				return false;
			} else {

				for (int i = 0; i < items.length; i++) {
					boolean flag = false;
					for (int j = 0; j < cdTreeNode.length; j++) {
						// System.out.println("current tree position:
						// cdTreeNode["
						// + j + "]" + cdTreeNode[j].getNode().toString());
						//
						// System.out.println("items[" + i + "].trim()= "
						// + items[i].trim());

						if (items[i].trim().equals(
								cdTreeNode[j].getNode().toString().trim())) {
							if (cdTreeNode[j].getChildren() != null
									&& cdTreeNode[j].getChildCount() > 0) {
								cdTreeNode = cdTreeNode[j].getChildren();

								// System.out.println("next tree position: "
								// + cdTreeNode[0].getNode().toString());
							}
							flag = true;
							break;

						}
					}
					if (flag == false)
						return false;
				}
				return true;
			}
		} else
			return false;
	}

	public static void printProperties(TestObject to) {
		// Hashtable props = to.getRecognitionProperties();
		Hashtable props = to.getProperties();
		Enumeration keys = props.keys();
		Enumeration values = props.elements();
		while (keys.hasMoreElements()) {
			System.out.println("[ " + keys.nextElement() + " ] = "
					+ values.nextElement());
		}
	}

	public static void printMethods(TestObject to) {
		MethodInfo[] m = to.getMethods();
		for (int i = 0; i < m.length; ++i) {
			if (!m[i].getDeclaringClass().equals("java.lang.Object"))
				System.out.println(m[i].getDeclaringClass() + "."
						+ m[i].getName() + "() *** signature < "
						+ m[i].getSignature() + " >");
		}
	}

	/**
	 * Description : Find dialog according to the caption of the dialog.
	 * 
	 * @param String
	 *            The caption of the dialog.
	 * @return <b>TestOject </b> The dialog that found. <code>null</code> if not
	 *         found.
	 * @version v0.1
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To close a "Properties" dialog by click the
	 *        "Cancel" button <br>
	 * <br>
	 *        <code>(getDialog("Properties")).click(atText("&Cancel"));</code>
	 */
	public static TopLevelTestObject getDialog(String caption) {
		TestObject dialog = null;
		TestObject[] to = RationalTestScript.find(RationalTestScript.atChild(
				".domain", "Java"));
		for (int i = 0; i < to.length; i++) {
			// System.out.println(i + ": [" + to[i].getObjectClassName() + "] -
			// "+ to[i].getProperty(".captionText").toString());
			if (to[i].getObjectClassName().equalsIgnoreCase(
					"org.eclipse.swt.widgets.Shell")
					&& isVisible(to[i])
					&& to[i].invoke("isActive").toString().equals("true")
					&& to[i].getProperty(".captionText").toString()
							.equalsIgnoreCase(caption)) { /* NOI18N */
				dialog = to[i];
				// System.out.println("dialog found");
				break;
			}
		}
		return (TopLevelTestObject) dialog;
	}

	public static TopLevelTestObject getActiveDialog() {
		return DOF.getDialog(getScreen().getActiveWindow().getText());
	}

	public static SelectGuiSubitemTestObject getPoppedUpList() {
		sleep(1); // make sure the list is popped up
		TestObject[] to = RationalTestScript.find(atDescendant(".domain", "Java"));
		for (TestObject obj : to) {
			if (obj.getObjectClassName().equals("org.eclipse.swt.widgets.List")	&& isVisible(obj)) {
//					System.out.println(DOF.getAncestorLine(obj));
//					String[] items = (String[])obj.invoke("getItems");
//					for(String item:items){
//						System.out.println(item);
//					}
					if(DOF.getAncestorLine(obj).startsWith("Shell")&&DOF.getAncestorLine(obj).endsWith("Shell")){
						return (SelectGuiSubitemTestObject) obj;
					}
			}
		}
		return null;
	}
	
	public static ShellTestObject getShell() {
		TestObject[] to = RationalTestScript.find(atDescendant(".domain",
				"Java"));
		for (TestObject obj : to) {
			if (obj.getObjectClassName().equalsIgnoreCase(
					"org.eclipse.swt.widgets.Shell")
					&& isVisible(obj)) {
				System.out.println(obj.getProperties());
			}
		}
		return null;
	}

	public static String getTopDialogName() {
		return getScreen().getActiveWindow().getText();
	}

	public static GuiTestObject getTextFieldByIndex(TestObject parent, int index) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts.length >= index && isVisible(texts[index])) {
			return (GuiTestObject)texts[index];
		}
		return null;
	}

	public static TestObject getDirectChildTextField(TestObject parent) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null) {
			return texts[0];
		}
		return null;
	}

	public static TestObject getStatusLine(TestObject parent) {
		TestObject[] statusLines = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.jface.action.StatusLine"));
		if (statusLines != null && statusLines.length > 0)
			return statusLines[0];
		else
			return null;
	}

	public static GuiTestObject getLabel(TestObject parent) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		if (labels != null && labels.length > 0) {
			for (TestObject label : labels) {
				if (isVisible(label)
						&& !label.getProperty("text").toString().trim().equals(
								"")) {
					return (GuiTestObject) label;
				}
			}
		}
		return null;
	}

	public static ScrollTestObject getSybaseLabel(TestObject parent) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.guiutils.CLabel"));
		if (labels != null && labels.length > 0) {
			for (TestObject label : labels) {
				if (isVisible(label)) {
					return (ScrollTestObject) label;
				}
			}
		}
		return null;
	}

	public static ScrollTestObject getSybaseLabelByBounds(TestObject parent,
			Rectangle rectangle) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.guiutils.CLabel"));
		if (labels != null && labels.length > 0) {
			for (TestObject label : labels) {
				if (isVisible(label)) {
					String des = label.getProperties().get("bounds").toString();
					Pattern r = Pattern.compile("(\\d+)");
					Matcher m = r.matcher(des);
					ArrayList<String> matches = new ArrayList<String>();
					while (m.find()) {
						matches.add(m.group());
					}
					if (matches.size() == 4) {
						int x = new Integer(matches.get(0)).intValue();
						int y = new Integer(matches.get(1)).intValue();
						int width = new Integer(matches.get(2)).intValue();
						int height = new Integer(matches.get(3)).intValue();
						// System.out
						// .println(x + ":" + y + ":" + width + ":" + height);
						if (x == (int) rectangle.getX()
								&& y == (int) rectangle.getY()
								&& width == (int) rectangle.getWidth()
								&& height == (int) rectangle.getHeight()) {
							return (ScrollTestObject) label;
						}
					}
				}
			}
		}
		return null;
	}

	public static GuiTestObject getLabelByBound(TestObject parent,
			Rectangle rectangle) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		if (labels != null && labels.length > 0) {
			for (TestObject label : labels) {
				String des = label.getProperties().get("bounds").toString();
				Pattern r = Pattern.compile("(\\d+)");
				Matcher m = r.matcher(des);
				ArrayList<String> matches = new ArrayList<String>();
				while (m.find()) {
					matches.add(m.group());
				}
				if (matches.size() == 4) {
					int x = new Integer(matches.get(0)).intValue();
					int y = new Integer(matches.get(1)).intValue();
					int width = new Integer(matches.get(2)).intValue();
					int height = new Integer(matches.get(3)).intValue();
					// System.out
					// .println(x + ":" + y + ":" + width + ":" + height);
					if (x == (int) rectangle.getX()
							&& y == (int) rectangle.getY()
							&& width == (int) rectangle.getWidth()
							&& height == (int) rectangle.getHeight()
							&& isVisible(label)) {
						return (GuiTestObject) label;
					}
				}
			}
		}
		return null;
	}

	public static GuiTestObject getTextFieldByBound(TestObject parent,
			Rectangle rectangle) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null && texts.length > 0) {
			for (TestObject text : texts) {
				String des = text.getProperties().get("bounds").toString();
				Pattern r = Pattern.compile("(\\d+)");
				Matcher m = r.matcher(des);
				ArrayList<String> matches = new ArrayList<String>();
				while (m.find()) {
					matches.add(m.group());
				}
				if (matches.size() == 4) {
					int x = new Integer(matches.get(0)).intValue();
					int y = new Integer(matches.get(1)).intValue();
					int width = new Integer(matches.get(2)).intValue();
					int height = new Integer(matches.get(3)).intValue();
					// System.out
					// .println(x + ":" + y + ":" + width + ":" + height);
					if (x == (int) rectangle.getX()
							&& y == (int) rectangle.getY()
							&& width == (int) rectangle.getWidth()
							&& height == (int) rectangle.getHeight()
							&& isVisible(text)) {
						return (GuiTestObject) text;
					}
				}
			}
		}
		return null;
	}

	public static GuiTestObject getLabel(TestObject parent, String label) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (isVisible(labels[i])&&labels[i].getProperty("text").toString().equals(label))
					return (GuiTestObject) labels[i];
			}
		}
		return null;
	}
	
	public static GuiTestObject getLabelByAncestorLine(TestObject parent, String str) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (isVisible(labels[i])&&DOF.getAncestorLine(labels[i]).equals(str))
					return (GuiTestObject) labels[i];
			}
		}
		return null;
	}
	
	public static GuiTestObject getLabelByAncestorLine(TestObject parent, String str, int index) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		List <TestObject> list = new ArrayList<TestObject>();
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (isVisible(labels[i])&&DOF.getAncestorLine(labels[i]).equals(str))
				{
					list.add(labels[i]);
				}
			}
		}
		if(list.size()>0){
			return (GuiTestObject)list.get(index);
		}else{
			return null;
		}
	}
	
	public static GuiTestObject getLabel(TestObject parent, int index) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Label"));
		if (labels != null && labels.length > 0) {
//			for (int i = 0; i < labels.length; i++) {
			if(isVisible(labels[index])){
				return (GuiTestObject)labels[index];
			}
//			}
//				if (isVisible(labels[i])&&labels[i].getProperty(".classIndex").toString().equals(new Integer(index).toString()))
//					return (GuiTestObject) labels[i];
		}
		return null;
	}

	public static TestObject getCLabel(TestObject parent) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.CLabel"));
		if (labels != null && labels.length > 0) {
			return labels[0];
		}
		return null;
	}

	public static ScrollTestObject getCLabel(TestObject parent, String label) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.guiutils.CLabel"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				System.out.println(labels[i].getProperty(".priorLabel")
						.toString());
				if (labels[i].getProperty(".priorLabel").toString().equals(
						label))
					return (ScrollTestObject) labels[i];
			}
		}
		return null;
	}
	
	public static GuiTestObject getCLabelByContent(TestObject parent, String label) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CLabel"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (labels[i].getProperty("text").toString().equals(
						label)&&isVisible(labels[i]))
					return (GuiTestObject) labels[i];
			}
		}
		return null;
	}
	
	public static GuiTestObject getCLabelStartWith(TestObject parent, String label) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CLabel"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (labels[i].getProperty("text").toString().startsWith(
						label)&&isVisible(labels[i]))
					return (GuiTestObject) labels[i];
			}
		}
		return null;
	}
	
	public static GuiTestObject getCustomCLabel(TestObject parent) {
		TestObject[] labels = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CLabel"));
		if (labels != null && labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				if (isVisible(labels[i]))
					return (GuiTestObject) labels[i];
			}
		}
		return null;
	}

	public static TestObject getTextFieldByText(TestObject parent, String text) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				System.out.println(texts[i].getProperty("text").toString());
				if (texts[i].getProperty("text").toString() != null
						&& texts[i].getProperty("text").toString().equals(text))
					return texts[i];
			}
		}
		return null;
	}

	public static TextScrollTestObject getTextField(TestObject parent) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null) {
			for (TestObject text : texts) {
				if (isVisible(text))
					return (TextScrollTestObject) text;
			}
		}
		return null;
	}

	public static TextScrollTestObject getTextFieldByCaretLocation(
			TestObject parent, int x, int y) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null) {
			for (TestObject text : texts) {
				Point point = (Point) text.getProperty("caretLocation");
				if (isVisible(text) && point.x == x && point.y == y)
					return (TextScrollTestObject) text;
			}
		}
		return null;
	}

	public static TextScrollTestObject getTextFieldByClientArea(
			TestObject parent, int width, int height) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null) {
			for (TestObject text : texts) {
				Rectangle rec = (Rectangle) text.getProperty("clientArea");
				if (isVisible(text) && rec.width == width
						&& rec.height == height)
					return (TextScrollTestObject) text;
			}
		}
		return null;
	}

	public static TextScrollTestObject getnoeditTextField(TestObject parent) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null) {
			for (int i = 0; i < texts.length; i++) {
				// System.out.println(texts[i].getProperties());
				if (texts[i].getProperty("editable").toString().equals("false"))

					return (TextScrollTestObject) texts[i];

			}
		}
		return null;
	}

	public static TextScrollTestObject getTextfieldbygroup(TestObject parent,
			String Caption) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		for (int i = 0; i < texts.length; i++) {
			// System.out.println("there are "+texts.length+" texts");
			// System.out.println(i+":
			// "+texts[i].getProperty(".priorLabel").toString());
			System.out.println(texts[i].getProperties().toString());
			if (texts[i].getProperty(".groupText").toString().equals(Caption)) {
				return (TextScrollTestObject) texts[i];
			}
		}
		return null;
	}

	public static GuiTestObject getTextField(TestObject parent, String Caption) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		for (int i = 0; i < texts.length; i++) {
			if (texts[i].getProperty(".priorLabel") != null
					&& texts[i].getProperty(".priorLabel").toString().equals(
							Caption) && isVisible(texts[i])) {
				return (GuiTestObject) texts[i];
			}
		}
		return null;
	}
//ff>>>>>>>>>>>
	public static TextScrollTestObject getStyledTextHasLabel(TestObject parent, String label) {
		TestObject[] styledTexts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.StyledText"));
		System.out.println(styledTexts.length);
//		if (styledTexts != null && styledTexts.length > 0)
//			for(TestObject obj: styledTexts){
//				if(isVisible(obj)){
//					return (TextScrollTestObject) obj;
//				}
//			}
		for (int i = 0; i < styledTexts.length; i++) {
			// System.out.println("there are "+texts.length+" texts");
			// System.out.println(i+":
			// "+texts[i].getProperty(".priorLabel").toString());
			if (styledTexts[i].getProperty(".priorLabel") != null
					&& styledTexts[i].getProperty(".priorLabel").toString().equals(
							label) && isVisible(styledTexts[i])) {
				return (TextScrollTestObject) styledTexts[i];
			}
		}
		return null;
	}
	
	//<<<<<<<<<<<<<<<<<<<>ff
	
	
	
	public static TestObject getTextFieldAmongTextSquence(TestObject parent,
			String[] sequence, String name) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		String[] textList = new String[texts.length];
		for (int i = 0; i < texts.length; i++) {
			try {
				textList[i] = texts[i].getProperty(".priorLabel").toString();
			} catch (Exception e) {
				textList[i] = "null";
			}
		}
		int pos = StringUtil.subArray(textList, sequence);
		if (pos != -1) {
			for (int i = pos; i < pos + sequence.length; i++) {
				if (texts[i].getProperty(".priorLabel").toString().equals(name))
					return texts[i];
			}
		}
		return null;
	}

	public static TextScrollTestObject getStyledText(TestObject parent) {
		TestObject[] styledTexts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.StyledText"));
//		System.out.println(styledTexts.length);
		if (styledTexts != null && styledTexts.length > 0)
			for(TestObject obj: styledTexts){
				if(isVisible(obj)){
					return (TextScrollTestObject) obj;
				}
			}
		return null;
	}

	public static GuiSubitemTestObject getSETree() {
		TestObject[] trees = getRoot().find(
				RationalTestScript.atDescendant("class",
						"org.eclipse.swt.widgets.Tree"));
		// System.out.println(trees.length);
		for (int i = 0; i < trees.length; i++) {
			TestObject[] items = (TestObject[]) trees[i].invoke("getItems");
			if (items != null
					&& items[0].invoke("getText").toString().equals("Private")) {
				// System.out.println("item was found");
				return (GuiSubitemTestObject) trees[i];
			}
		}
		return null;

	}

	/**
	 * Description : Find the tree under Enterprise Explorer tab of WorkSpace.
	 * 
	 * @return <b>GuiSubitemTestObject </b> The Tree Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.1
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To connect MyServiceContainer in the EE. <br>
	 * <br>
	 *        <code>getEETree().click(right,atPath("Service Containers->MyServiceContainer"));<br>
	 * 									getContextMenu().click(atText("Connect"));</code>
	 */
	public static GuiSubitemTestObject getEETree() {

		TestObject[] trees = getRoot().find(atDescendant("class", "org.eclipse.swt.widgets.Tree"));
		for (int i = 0; i < trees.length; i++) {
			if (TreeHelper.getFirstItem((ScrollGuiSubitemTestObject) trees[i]) != null
					&& TreeHelper.getFirstItem((ScrollGuiSubitemTestObject) trees[i]).equals(
							"Database Connections") && isVisible(trees[i]))
				return (GuiSubitemTestObject) trees[i];
		}
		return null;
	}

	public static GuiSubitemTestObject getWNTree() {
		TestObject[] trees = DOF.getRoot().find(
				atDescendant("class", "org.eclipse.swt.widgets.Tree"));
		for (int i = 0; i < trees.length; i++) {
			if (getAncestorLine(trees[i]).equals(
					"Composite->Composite->Composite->Composite->Shell")
					&& isVisible(trees[i])
					&& TreeHelper
							.getFirstItem((ScrollGuiSubitemTestObject) trees[i]) != null
					&& !TreeHelper.getFirstItem(
							(ScrollGuiSubitemTestObject) trees[i]).equals(
							"Database Connections")
					&& !TreeHelper.getFirstItem(
							(ScrollGuiSubitemTestObject) trees[i]).startsWith(
							"Warning")
					&& !TreeHelper.getFirstItem(
							(ScrollGuiSubitemTestObject) trees[i]).startsWith(
							"Error"))
				return (GuiSubitemTestObject) trees[i];
		}
		return null;
	}
	
	public static GuiSubitemTestObject getTreeByBound(TestObject parent, Rectangle rectangle){
		TestObject[] trees = parent.find(
				atDescendant("class", "org.eclipse.swt.widgets.Tree"));
		for(TestObject tree:trees){
			String des = tree.getProperties().get("bounds").toString();
			Pattern r = Pattern.compile("(\\d+)");
			Matcher m = r.matcher(des);
			ArrayList<String> matches = new ArrayList<String>();
			while (m.find()) {
				matches.add(m.group());
			}
			if (matches.size() == 4) {
				int x = new Integer(matches.get(0)).intValue();
				int y = new Integer(matches.get(1)).intValue();
				int width = new Integer(matches.get(2)).intValue();
				int height = new Integer(matches.get(3)).intValue();
				// System.out
				// .println(x + ":" + y + ":" + width + ":" + height);
				if (x == (int) rectangle.getX()
						&& y == (int) rectangle.getY()
						&& width == (int) rectangle.getWidth()
						&& height == (int) rectangle.getHeight()
						&& isVisible(tree)) {
					return (GuiSubitemTestObject) tree;
				}
			}
		
		}
		return null;
	}

	public static GuiSubitemTestObject getTree(TestObject parent) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		if (trees != null && trees.length > 0) {
			for (TestObject tree : trees) {
				if (isVisible(tree)) {
					return (GuiSubitemTestObject) tree;
				}
			}
		}
		return null;
	}

	public static ScrollGuiSubitemTestObject getTreeByColumnCount(
			TestObject parent, int columnCount) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		if (trees != null && trees.length > 0) {
			for (TestObject tree : trees) {
				if (isVisible(tree)) {
					int cc = new Integer(tree.getProperty("columnCount")
							.toString()).intValue();
					if (cc == columnCount) {
						return (ScrollGuiSubitemTestObject) tree;
					}
				}
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTreeExceptPreferenceTree(
			TestObject parent, boolean visible) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		TestObject preferenceTree = null;
		if (trees != null && trees.length > 0) {
			for (int i = 0; i < trees.length; i++) {
				String visibleStr = trees[i].invoke("isVisible").toString();
				if (TreeHelper
						.getFirstItem((ScrollGuiSubitemTestObject) trees[i]) != null
						&& TreeHelper.getFirstItem(
								(ScrollGuiSubitemTestObject) trees[i]).equals(
								"General"))
					preferenceTree = trees[i];
				if (trees[i] != preferenceTree
						&& new Boolean(visibleStr).booleanValue() == visible) {
					return (GuiSubitemTestObject) trees[i];
				}
			}

		}
		return null;
	}

	public static GuiSubitemTestObject getTree(TestObject parent,
			boolean visible) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		if (trees != null && trees.length > 0) {
			for (int i = 0; i < trees.length; i++) {
				String visibleStr = trees[i].invoke("isVisible").toString();
				if (new Boolean(visibleStr).booleanValue() == visible) {
					return (GuiSubitemTestObject) trees[i];
				}
			}

		}
		return null;
	}

	public static TestObject[] getallTree(TestObject parent) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		if (trees != null && trees.length > 0) {
			return trees;
		} else
			return null;
	}

	public static GuiSubitemTestObject getTree(TestObject parent, String label) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		for (int i = 0; i < trees.length; i++) {
			try {
				//System.out.println(buttons[i].getProperty("text").toString());
				if (trees[i].getProperty(".priorLabel").toString()
						.equals(label)) {
					return (GuiSubitemTestObject) trees[i];
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTreeByRootElement(TestObject parent,
			String text) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		for (TestObject tree : trees) {
			if (TreeHelper.getFirstItem((ScrollGuiSubitemTestObject) tree)
					.equals(text)
					&& isVisible(tree)) {
				return (GuiSubitemTestObject) tree;
			}
		}
		return null;
	}

	/**
	 * Description : Find Button according to parent TestObject and text.
	 * <i>Note: Normally, an "&" should be added in front of the text of the
	 * Button in order to find the object. </i>
	 * 
	 * @param TestObject
	 *            The parent TestObject of the Button.
	 * @param String
	 *            The text of the Button.
	 * @return <b>GuiSubitemTestObject </b> The Button Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.1
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To get the "Next" toolbar of the New
	 *        Connection Profile Properties Dialog and click it. <br>
	 * <br>
	 *        <code>(getDialog("New Connection Profile Dialog Properties"),"&Next").click();</code>
	 */

	public static GuiTestObject getButton(TestObject parent, String text) {
		// root = getRoot();
		TestObject[] buttons = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Button"));
		if (buttons != null) {
			for (int i = 0; i < buttons.length; i++) {
				//System.out.println(buttons[i].getProperty("text").toString());
				if (buttons[i].getProperty("text") != null
						&& buttons[i].getProperty("text").toString().equals(
								text) && isVisible(buttons[i])) {
					return (GuiTestObject) buttons[i];
				}
			}
		}
		return null;
	}

	public static GuiTestObject getButton(TestObject parent, String text,
			boolean visible) {
		// root = getRoot();
		TestObject[] buttons = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Button"));
		if (buttons != null) {
			// System.out.println(buttons.length);
			for (int i = 0; i < buttons.length; i++) {
				boolean isVisible = new Boolean(buttons[i].invoke("isVisible")
						.toString()).booleanValue();
				// System.out.println(isVisible);
				if (buttons[i].getProperty("text").toString().equals(text)
						&& isVisible == visible) {
					return (GuiTestObject) buttons[i];
				}
			}
		}
		return null;
	}

	public static GuiTestObject getButton(TestObject parent, int index) {
		TestObject[] buttons = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Button"));
		if (buttons != null && buttons.length > 0) {
			return (GuiTestObject) buttons[index];
		}
		return null;
	}

	/**
	 * Description : Find RadioButton according to parent TestObject and text.
	 * <i>Note: Normally, an "&" should be added in front of the text of the
	 * Button in order to find the object. </i>
	 * 
	 * @param TestObject
	 *            The parent TestObject of the RadioButton.
	 * @param String
	 *            The text of the RadioButton.
	 * @return <b>GuiSubitemTestObject </b> The RadioButton Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.1
	 * @author Grace
	 * @since Nov 07, 2008</br><br>
	 *        <b>Example </b></br> <br>
	 * <br>
	 *        <code>getRadioButton(getDialog("New Connection Profile Dialog Properties"),"&Next").click();</code>
	 */
	public static ToggleGUITestObject getRadioButton(TestObject parent,
			String text) {
		// root = getRoot();
		TestObject[] radiobuttons = parent.find(RationalTestScript
				.atDescendant("class", "org.eclipse.swt.widgets.Button"));
		for (int i = 0; i < radiobuttons.length; i++) {
			// System.out.println(buttons[i].getProperty("text").toString());
			if (radiobuttons[i].getProperty("text").toString().equals(text)) {
				return (ToggleGUITestObject) radiobuttons[i];
			}
		}
		return null;
	}

	/**
	 * Description : Find CTabFolder object according to it's tab caption.
	 * <i>Note: This method is highly recommended in the WorkSpace test cause it
	 * run much faster than the static mapped object. </i>
	 * 
	 * @param TestObject
	 *            The parent control of the tree.
	 * @param String
	 *            The tab caption of the CTabFolder.
	 * @return <b>GuiSubitemTestObject </b> The CTabFolder Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.11
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To get the "Enterprise Explorer" tab and
	 *        click it. <br>
	 * <br>
	 *        <code>getTabFolder(getRoot(),"Enterprise Explorer").click(atText("Enterprise Explorer"));</code>
	 */
	public static GuiSubitemTestObject getCTabFolder(TestObject parent,
			String caption) {
		TestObject[] tabs = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CTabFolder"));
		// System.out.println(tabs.length);
		if (tabs != null && tabs.length > 0) {
			for (int i = 0; i < tabs.length; i++) {

				if (tabs[i].getProperty(".tabs") != null
						&& tabs[i].getProperty(".tabs").toString().indexOf(
								caption) != -1 && isVisible(tabs[i]))
					return (GuiSubitemTestObject) tabs[i];
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTabFolder(TestObject parent,
			String caption) {
		TestObject[] tabs = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.TabFolder"));
		// System.out.println(tabs.length);
		if (tabs != null && tabs.length > 0) {
			for (int i = 0; i < tabs.length; i++) {
				if (tabs[i].getProperty(".tabs").toString().indexOf(caption) != -1)
					return (GuiSubitemTestObject) tabs[i];
			}
			return null;
		}
		return null;
	}

	public static GuiSubitemTestObject getTabFolder(TestObject parent,
			String caption, Boolean visible) {
		TestObject[] tabs = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.TabFolder"));
		System.out.println(tabs.length);
		if (tabs != null && tabs.length > 0) {
			for (int i = 0; i < tabs.length; i++) {
				String visibleStr = tabs[i].invoke("isVisible").toString();
				if (tabs[i].getProperty(".tabs").toString().indexOf(caption) != -1
						&& new Boolean(visibleStr).booleanValue() == visible)
					return (GuiSubitemTestObject) tabs[i];
			}
			return null;
		}
		return null;
	}

	/**
	 * For select tab in properties view Auther: Grace
	 * 
	 * @param parent
	 * @param text
	 * @return
	 */

	public static GuiTestObject getTabItem(TestObject parent, String text) {
		TestObject[] tabs = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CTabItem"));
		// System.out.println(tabs.length);
		if (tabs != null && tabs.length > 0) {
			for (int i = 0; i < tabs.length; i++) {
				if (tabs[i].getProperty("text").toString().equals(text))
					return (GuiTestObject) tabs[i];
			}
			return null;
		}
		return null;
	}

	public static GuiSubitemTestObject getTable(TestObject parent,
			boolean visible) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (int i = 0; i < tables.length; i++) {
				String visibleStr = tables[i].invoke("isVisible").toString();
				if (new Boolean(visibleStr).booleanValue() == visible) {
					return (GuiSubitemTestObject) tables[i];
				}
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTable(TestObject parent) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (TestObject table : tables) {
				if (isVisible(table)) {
					return (GuiSubitemTestObject) table;
				}
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTable(TestObject parent,
			int columnCount, int itemCount) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (int i = 0; i < tables.length; i++) {
				if (TableHelper
						.getColumnCount((GuiSubitemTestObject) tables[i]) == columnCount
						&& TableHelper
								.getItemCount((GuiSubitemTestObject) tables[i]) == itemCount)
					return (GuiSubitemTestObject) tables[i];
			}
		}
		return null;
	}

	/**
	 * For get a table from priorLabel Grace
	 * 
	 * @param parent
	 * @param label
	 * @return
	 */
	public static ScrollGuiSubitemTestObject getTable(TestObject parent,
			String label) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (int i = 0; i < tables.length; i++) {
				if (isVisible(tables[i])
						&& tables[i].getProperty(".priorLabel") != null
						&& tables[i].getProperty(".priorLabel").toString()
								.equals(label))
					return (ScrollGuiSubitemTestObject) tables[i];
			}
		}
		return null;
	}

	public static GuiSubitemTestObject getTableByColumnData(TestObject parent,
			int columnIndex, String[] pattern) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (int i = 0; i < tables.length; i++) {
				if (TableHelper
						.getColumnCount((GuiSubitemTestObject) tables[i]) > 0
						&& TableHelper
								.getItemCount((GuiSubitemTestObject) tables[i]) > 0) {
					String[] data = TableHelper.getColumnItems(
							(GuiSubitemTestObject) tables[i], columnIndex);
					if (StringUtil.ifStrArrayContainStrArray(data, pattern)) {
						return (GuiSubitemTestObject) tables[i];
					}
				}
			}
		}
		return null;
	}

	// The Table.getColumns normally returns a Array that has one more elements
	// then shows in the screen,
	// the Text of the first element is empty.
	public static GuiSubitemTestObject getTableByColumnsNames(
			TestObject parent, String[] columnNames) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant("class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			for (int i = 0; i < tables.length; i++) {
				TestObject[] columns = (TestObject[]) tables[i].invoke("getColumns");
				if (columns != null) {
					if (columns.length != columnNames.length)
						continue;
					boolean flag = true;
					for (int j = 0; j < columns.length; j++) {
						String column = columns[j].invoke("getText").toString();
						if (!column.equals(columnNames[j])) {
							flag = false;
							break;
						}
					}
					if (flag) {
						TestObject[] final_columns = (TestObject[]) tables[i].invoke("getColumns");
						return (GuiSubitemTestObject) tables[i];
					}
				}
			}
		}
		return null;
	}

	public static ScrollTestObject getTwistie(TestObject parent) {
		TestObject[] twisties = parent.find(atChild("class",
				"org.eclipse.ui.forms.widgets.Twistie"));
		if (twisties != null && twisties.length > 0) {
			return (ScrollTestObject) twisties[0];
		}
		return null;
	}

	public static TestObject getGroup(TestObject parent, String name) {
		TestObject[] groups = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Group"));
		if (groups != null && groups.length > 0) {
			for (int i = 0; i < groups.length; i++) {
				if (isVisible(groups[i]) && groups[i].getProperty("text").toString().indexOf(name) != -1) {
					return groups[i];
				}
			}
			return null;
		} else
			return null;
	}

	// public static void test() {
	// System.out.println(getDomains().length);
	// for (int i = 0; i < getDomains().length; i++) {
	// System.out.println(getDomains()[i].getName().toString());
	// if (getDomains()[i].getName().equals("Java")) {
	//
	// TestObject[] objects =
	// RationalTestScript.getDomains()[i].getTopObjects();
	//
	// for (int j = 0; j < objects.length; j++) {
	// System.out.print(objects[j].getProperty("class").toString()
	// + " : ");
	// System.out.println(objects[j].getProperty("text")
	// .toString());
	// try {
	// ((TopLevelTestObject) objects[j]).inputKeys("{enter}");
	// System.out.println("Enter key has been pushed.");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	// }
	// }
	// }

	/**
	 * Description : Find context menu.
	 * 
	 * @return <b>GuiSubitemTestObject </b> The contextMenu Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.1
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To get the context menu and click
	 *        "Properties" .<br>
	 * <br>
	 *        <code>getContextMenu().click(atText("Properties"));</code>
	 */
	public static GuiSubitemTestObject getContextMenu() {
		GuiSubitemTestObject contextMenu = null;
		TestObject[] to = RationalTestScript.find(RationalTestScript.atChild(
				".domain", "Java"));
		for (int i = 0; i < to.length; i++) {
			if (to[i].getObjectClassName().equalsIgnoreCase("Java.PopupMenu")) {
				contextMenu = new GuiSubitemTestObject(to[i]);
//				System.out.println(contextMenu.getObjectClassName());
//				printMethods(contextMenu);
				break;
			}
		}
		return contextMenu;
	}

	public static GuiSubitemTestObject getTree(String pattern) {
		TestObject[] trees = getRoot().find(
				atDescendant("class", "org.eclipse.swt.widgets.Tree"));
		for (int i = 0; i < trees.length; i++) {
			if (ifTreeItemExist(trees[i], pattern))
				return (GuiSubitemTestObject) trees[i];
		}
		return null;
	}

	public static boolean searchTree(String catagory, String name) {
		TestObject[] trees = getRoot().find(
				RationalTestScript.atDescendant("class",
						"org.eclipse.swt.widgets.Tree"));
		boolean findFlag = false;
		if (trees != null && trees.length > 0) {
			for (int i = 0; i < trees.length; i++) {

				TestObject parent = trees[i];

				ITestDataTree cdTree;
				ITestDataTreeNodes cdTreeNodes;
				ITestDataTreeNode[] cdTreeNode;

				cdTree = (ITestDataTree) parent.getTestData("tree");
				cdTreeNodes = cdTree.getTreeNodes();
				cdTreeNode = cdTreeNodes.getRootNodes();

				if (cdTreeNode != null) {
					for (int k = 0; k < cdTreeNode.length; k++) {
						if (cdTreeNode[k].getNode().toString().equals(catagory)) {
							int itemCount = cdTreeNode[k].getChildCount();
							ITestDataTreeNode[] items = cdTreeNode[k]
									.getChildren();
							for (int p = 0; p < itemCount; p++) {
								if (items[p].getNode().toString().equals(name))
									findFlag = true;
							}
						}
					}
				}
			}
		}
		return findFlag;
	}

	/**
	 * Description : Find ToolBar according to parent TestObject and
	 * ToolTipText.
	 * 
	 * @param TestObject
	 *            The parent TestObject of the ToolBar.
	 * @param String
	 *            The toolTipText of the ToolBar.
	 * @return <b>GuiSubitemTestObject </b> The ToolBar Object that found.
	 *         <code>null</code> if not found.
	 * @version v0.1
	 * @author Eric
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b></br> To get the "Save" toolbar and click it. <br>
	 * <br>
	 *        <code>getToolBar(getRoot(),"Save").click(atToolTipText("Save"));</code>
	 */
	public static GuiSubitemTestObject getToolBar(TestObject parent,
			String caption) {
		TestObject[] toolBars = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.ToolBar"));
		if (toolBars != null) {
			for (int i = 0; i < toolBars.length; i++) {
				if (toolBars[i].getProperty(".itemToolTipText") != null
						&& toolBars[i].getProperty(".itemToolTipText")
								.toString().indexOf(caption) != -1) {
					return (GuiSubitemTestObject) toolBars[i];
				}
			}
			return null;
		}
		return null;
	}

	public static GuiSubitemTestObject getToolBar(TestObject parent,
			String caption, int itemCount) {
		TestObject[] toolBars = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.ToolBar"));
		if (toolBars != null) {
			for (int i = 0; i < toolBars.length; i++) {

				if (toolBars[i].getProperty("itemCount").equals(
						new Integer(itemCount).toString())
						&& toolBars[i].getProperty(".itemToolTipText") != null
						&& toolBars[i].getProperty(".itemToolTipText")
								.toString().indexOf(caption) != -1) {
					return (GuiSubitemTestObject) toolBars[i];
				}
			}
			return null;
		}
		return null;
	}

	private static com.rational.test.ft.object.interfaces.TestObject getToolPallete(
			java.lang.String name) {
		com.rational.test.ft.object.interfaces.TestObject palletes[] = getRoot()
				.find(
						com.rational.test.ft.script.SubitemFactory
								.atDescendant("class",
										"com.sybase.stf.common.toolpalette.ToolPaletteContainer"));
		if (palletes != null) {
			for (int i = 0; i < palletes.length; i++)
				if (palletes[i].invoke("getText").toString().equals(name))
					return palletes[i];

			return null;
		} else {
			return null;
		}
	}

	public static com.rational.test.ft.object.interfaces.ScrollTestObject getPalleteItem(
			java.lang.String palleteName, java.lang.String itemName) {
		com.rational.test.ft.object.interfaces.TestObject pallete = getToolPallete(palleteName);
		if (pallete != null) {
			com.rational.test.ft.object.interfaces.TestObject label[] = pallete
					.find(com.rational.test.ft.script.SubitemFactory.atChild(
							"class", "org.eclipse.swt.custom.CLabel"));
			((com.rational.test.ft.object.interfaces.GuiTestObject) label[0])
					.click();
			return getToolPalleteItem(pallete, itemName);
		} else {
			return null;
		}
	}

	private static com.rational.test.ft.object.interfaces.ScrollTestObject getToolPalleteItem(
			com.rational.test.ft.object.interfaces.TestObject parent,
			java.lang.String name) {
		com.rational.test.ft.object.interfaces.TestObject palleteItems[] = parent
				.find(com.rational.test.ft.script.SubitemFactory.atDescendant(
						"class",
						"com.sybase.stf.common.toolpalette.ToolPaletteItem"));
		if (palleteItems != null && palleteItems.length > 0) {
			for (int i = 0; i < palleteItems.length; i++)
				if (((java.lang.String) palleteItems[i].invoke("getText"))
						.equals(name))
					return (com.rational.test.ft.object.interfaces.ScrollTestObject) palleteItems[i];

			return null;
		} else {
			return null;
		}
	}

	public static TestObject getSection(TestObject parent, String name) {
		TestObject[] sections = parent.find(atDescendant("class",
				"org.eclipse.ui.forms.widgets.Section"));
		if (sections != null && sections.length > 0) {
			for (int i = 0; i < sections.length; i++) {
				TestObject[] texts = sections[i].find(atChild("class",
						"org.eclipse.swt.widgets.Label"));
				if (texts != null && texts.length > 0
						&& texts[0].getProperty("text").toString().equals(name)) {
					return sections[i];
				}
			}
		}
		return null;
	}

	public static TestObject getWrappedPageBook(TestObject parent) {
		TestObject[] pageBooks = parent.find(atDescendant("class",
				"org.eclipse.ui.internal.forms.widgets.WrappedPageBook"));
		if (pageBooks != null && pageBooks.length > 0) {
			// System.out.println(pageBooks.length);
			// NativeInvoker.printMethods(pageBooks[0]);
			return pageBooks[0];
		}
		return null;
	}

	public static TestObject getSection(String formName, String sectionName) {
		TestObject form = getForm(getRoot(), formName);
		if (form != null) {
			return getSection(form, sectionName);
		}
		return null;
	}

	public static TestObject getScrolledForm(TestObject parent, String name) {
		TestObject[] forms = parent.find(atDescendant("class",
				"org.eclipse.ui.forms.widgets.ScrolledForm"));
		if (forms != null && forms.length > 0) {
			for (int i = 0; i < forms.length; i++) {
				System.out.println(forms[i].invoke("getText").toString());
				printMethods(forms[i]);
				String text = forms[i].getProperty("tabText").toString();
				if (text.equals(name)) {
					return forms[i];
				}
			}
		}
		return null;
	}

	private static TestObject getFormHeading(TestObject parent, String name) {
		TestObject[] headings = parent.find(atChild("class",
				"org.eclipse.ui.internal.forms.widgets.FormHeading"));
		if (headings != null && headings.length > 0) {
			for (int i = 0; i < headings.length; i++) {
				String caption = headings[i].invoke("getText").toString();
				if (name.equals(caption)) {
					return headings[i];
				}
			}
		}
		return null;
	}

	public static TestObject getForm(TestObject parent, String name) {
		TestObject[] forms = parent.find(atDescendant("class",
				"org.eclipse.ui.forms.widgets.Form"));
		if (forms != null && forms.length > 0) {
			for (int i = 0; i < forms.length; i++) {
				if (getFormHeading(forms[i], name) != null) {
					return forms[i];
				}
			}
		}
		return null;
	}

	public static ScrollTestObject getInterfaceDesigner() {
		TestObject[] ids = getRoot()
				.find(
						atDescendant(
								"class",
								"com.sybase.stf.service.framework.interfacedesigner.editor.InterfaceDesignerControl"));
		if (ids != null && ids.length > 0) {
			return (ScrollTestObject) ids[0];
		}
		return null;
	}

	public static ScrollTestObject getCCombo(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"org.eclipse.swt.custom.CCombo"));
		if (combos != null && combos.length > 0)
			for (TestObject combo : combos) {
				if (isVisible(combo)) {
					return (ScrollTestObject) combo;
				}
			}
		return null;
	}

	public static ScrollTestObject getCComboWithTooltip(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"com.sybase.uep.common.guiutils.CComboWithTooltip"));
		if (combos != null && combos.length > 0)
			for (TestObject combo : combos) {
				if (isVisible(combo)) {
					return (ScrollTestObject) combo;
				}
			}
		return null;
	}

	public static ScrollTestObject getCCombo(TestObject parent, String label) {
		TestObject[] combos = parent.find(atDescendant("class",	"org.eclipse.swt.custom.CCombo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (combos[i].getProperty(".priorLabel") != null && isVisible(combos[i]) && combos[i].getProperty(".priorLabel").equals(label)) {
					return (ScrollTestObject) combos[i];
				}
			}
		}
		return null;
	}
	
	public static ScrollTestObject getCCombo(TestObject parent, int index) {
		TestObject[] combos = parent.find(atDescendant("class",	"org.eclipse.swt.custom.CCombo"));
		if (combos != null && combos.length > index) {
			if (isVisible(combos[index])) {
				return (ScrollTestObject) combos[index];
			}
		}
		return null;
	}
	
	public static ScrollTestObject getCComboByAncestorLine(TestObject parent, String line) {
		TestObject[] combos = parent.find(atDescendant("class",	"org.eclipse.swt.custom.CCombo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (isVisible(combos[i]) && DOF.getAncestorLine(combos[i]).equals(line)) {
					return (ScrollTestObject) combos[i];
				}
			}
		}
		return null;
	}

	public static SelectGuiSubitemTestObject getList(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.List"));
		if (combos != null && combos.length > 0) {
			for (TestObject list : combos) {
				if (isVisible(list)) {
					return (SelectGuiSubitemTestObject) list;
				}
			}
		}
		return null;
	}

	public static SelectGuiSubitemTestObject getList(TestObject parent,
			String priorText) {
		TestObject[] combos = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.List"));
		if (combos != null && combos.length > 0) {
			for (TestObject list : combos) {
				if (isVisible(list)
						&& list.getProperty(".priorLabel").toString().equals(
								priorText)) {
					return (SelectGuiSubitemTestObject) list;
				}
			}
		}
		return null;
	}

	// York: Find the drop-down list by context
	public static SelectGuiSubitemTestObject getListbyItem(String itemText) {
		TestObject[] combos = DOF.getRootTestObject().find(
				atDescendant("class", "org.eclipse.swt.widgets.List"));
		System.out.println(combos.length);
		if (combos != null && combos.length > 0) {
			for (TestObject list : combos) {
				System.out.println(list.getProperty(".itemText").toString());
				if (isVisible(list)
						&& list.getProperty(".itemText").toString()
								.equalsIgnoreCase(itemText)) {
					return (SelectGuiSubitemTestObject) list;
				}
			}
		}
		return null;

	}

	public static boolean isVisible(TestObject obj) {
		try {
			if (obj.invoke("isVisible").toString().equalsIgnoreCase("true")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getClass());
			return false;
		}
	}

	public static ScrollGuiSubitemTestObject getList(TestObject parent,
			boolean visible) {
		TestObject[] combos = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.List"));
		System.out.println(combos.length);
		if (combos != null && combos.length > 0)
			for (int i = 0; i < combos.length; i++) {
				String visibleStr = combos[i].invoke("isVisible").toString();
				if (new Boolean(visibleStr).booleanValue() == visible) {
					return (ScrollGuiSubitemTestObject) combos[i];
				}
			}
		return null;

	}

	public static SelectGuiSubitemTestObject[] getallList(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.List"));
		System.out.println(combos.length);
		if (combos != null && combos.length > 0)
			return (SelectGuiSubitemTestObject[]) combos;
		else
			return null;
	}

	/**
	 * Added for click the list element in properties view The index is the
	 * order, for example, if the index is 0, then the "general" element will be
	 * select Auther: Grace
	 * 
	 * @param parent
	 * @param index
	 * @return
	 */
	public static ScrollTestObject gettabbedlistElement(TestObject parent,
			String index) {
		TestObject[] tlist = parent
				.find(atDescendant(
						"class",
						"org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList$ListElement"));
		if (tlist != null && tlist.length > 0)
			for (int i = 0; i < tlist.length; i++) {

				if (isVisible(tlist[i])
						&& tlist[i].getProperty(".classIndex").toString()
								.equals(index)) {
					return (ScrollTestObject) tlist[i];
				}
			}
		return null;
	}

	public static boolean gettabbedlist(TestObject parent, int index,
			String tabItemName) {
		TestObject[] tlist = parent
				.find(atDescendant("class",
						"org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList"));
		// System.out.println(tlist.length);
		TestObject obj = NativeInvoker.invoke(tlist[0], "getElementAt", index);
		// System.out.println(obj.invoke("toString").toString());
		if (obj.invoke("toString").toString().equalsIgnoreCase(tabItemName)) {
			return true;
		} else
			return false;
	}

	// public static ScrollTestObject getCCombo(TestObject parent, String[]
	// pattern){
	// TestObject[] combos =
	// parent.find(atDescendant("class","org.eclipse.swt.custom.CCombo"));
	// if(combos!=null && combos.length>0){
	// boolean flag = true;
	// for(int i=0;i<combos.length;i++){
	// String[] items = CComboHelper.getItems(combos[i]);
	// if(StringUtil.ifStrArrayContainStrArray(items,pattern))
	// return (ScrollTestObject)combos[i];
	// }
	// }
	// return null;
	//		
	// }

	public static ScrollTestObject getImageCombo(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"com.sybase.stf.service.framework.utils.ImageCombo"));
		if (combos != null && combos.length > 0) {
			System.out.println("there are " + combos.length + " image combo");
			// printMethods(combos[0]);
			return (ScrollTestObject) combos[0];
		}
		return null;
	}

	// public static ScrollTestObject getImageCombo(TestObject parent, String[]
	// pattern){
	// TestObject[] combos =
	// parent.find(atDescendant("class",
	// "com.sybase.stf.service.framework.utils.ImageCombo"));
	// if(combos!=null && combos.length>0){
	// for(int i=0;i<combos.length;i++){
	// String[] items = ImageComboHelper.getItems(combos[i]);
	// if(items!=null && items.length>0){
	// if(StringUtil.ifStrArrayContainStrArray(items,pattern))
	// return (ScrollTestObject)combos[i];
	// }
	// }
	// }
	// return null;
	// }

	public static void showAllImageCombo(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"com.sybase.stf.service.framework.utils.ImageCombo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				System.out.println(i
						+ ":"
						+ ((Rectangle) combos[i].invoke("getBounds"))
								.toString());
			}
		}
	}

	// public static ScrollTestObject getInterfaceDesingerImageCombo(){
	// TestObject[] combos =
	// getRoot().find(atDescendant("class",
	// "com.sybase.stf.service.framework.utils.ImageCombo"));
	// int maxWidth = 9999;
	// int index = -1;
	// String[] pattern = {"Input","Output"};
	// if(combos!=null && combos.length>0){
	// for(int i=0;i<combos.length;i++){
	// String[] items = ImageComboHelper.getItems(combos[i]);
	// if(items!=null && items.length>0){
	// if(StringUtil.ifStrArrayContainStrArray(items,pattern)){
	// Rectangle rec = (Rectangle)combos[i].invoke("getBounds");
	// if(rec.width<maxWidth){
	// maxWidth = rec.width;
	// index = i;
	// }
	// }
	// }
	// }
	// return (ScrollTestObject)combos[index];
	// }
	// return null;
	//		
	// }

	public static GuiTestObject getHyperLink(TestObject parent, String text) {
		TestObject[] links = parent.find(atDescendant("class",
				"org.eclipse.ui.forms.widgets.Hyperlink"));
		if (links != null && links.length > 0) {
			for (int i = 0; i < links.length; i++) {
				String str = links[i].invoke("getText").toString();
				if (text.equals(str)) {
					return (GuiTestObject) links[i];
				}
			}
		}
		return null;
	}

	public static TestObject getComposite(TestObject parent) {
		TestObject[] composites = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.Composite"));
		if (composites != null && composites.length > 0) {
			return (TestObject) composites[0];
		}
		return null;
	}
	
	public static TestObject getCompositeByEclipseId(TestObject parent, String id) {
		TestObject[] composites = parent.find(atDescendant("class",	"org.eclipse.swt.widgets.Composite"));
		for(TestObject composite:composites){
			if(composite.getProperty("eclipseId")!=null && composite.getProperty("eclipseId").equals(id)){
				return composite;
			}
		}
		return null;
	}

	public static GuiTestObject getProgressRegion(TestObject parent) {
		TestObject[] progressRegion = parent.find(atDescendant("class",
				"org.eclipse.ui.internal.progress.ProgressRegion$1"));
		if (progressRegion != null && progressRegion.length > 0) {
			return (GuiTestObject) progressRegion[0];
		}
		return null;
	}

	public static GuiTestObject getProgressBar(TestObject parent) {
		TestObject[] progressBars = parent.find(atDescendant("class",
				"org.eclipse.swt.widgets.ProgressBar"));
		if (progressBars != null && progressBars.length > 0) {
			for (TestObject bar : progressBars) {
				if (isVisible(bar)) {
					return (GuiTestObject) bar;
				}
			}
		}
		return null;
	}

	public static TestObject getContainerSelectionGroup(TestObject parent) {
		TestObject[] CSGs = parent.find(atDescendant("class",
				"org.eclipse.ui.internal.ide.misc.ContainerSelectionGroup"));
		if (CSGs != null && CSGs.length > 0) {
			System.out.println("there are " + CSGs.length + " CSG");
			return CSGs[0];
		}
		return null;
	}

	public static String getAncestorLine(TestObject widget) {
		String ancestors = null;
		TestObject parent = (TestObject) widget.invoke("getParent");
		while (parent != null) {
			// System.out.println(parent.getProperty("class"));
			if (ancestors == null) {
				ancestors = parent
						.getProperty("class")
						.toString()
						.substring(
								parent.getProperty("class").toString()
										.lastIndexOf(".") + 1,
								parent.getProperty("class").toString().length());
			} else {
				ancestors = ancestors
						+ "->"
						+ parent.getProperty("class").toString()
								.substring(
										parent.getProperty("class").toString()
												.lastIndexOf(".") + 1,
										parent.getProperty("class").toString()
												.length());
				// ancestors = ancestors + "->" +
				// parent.invoke("getName").toString();
			}
			parent = (TestObject) parent.invoke("getParent");
		}
		return ancestors;

	}

	public static TextScrollTestObject getCurrentEditorTab() {
		TestObject[] styledTexts = DOF.getRoot().find(
				atDescendant("class", "org.eclipse.swt.custom.StyledText"));
		for (int i = 0; i < styledTexts.length; i++) {
			String ancestorLine = DOF.getAncestorLine(styledTexts[i]);
			if (ancestorLine.equals(ANCESTORS_OF_EDITOR_TAB)
					&& isVisible(styledTexts[i])) {
				return (TextScrollTestObject) styledTexts[i];
			}
		}
		return null;
	}

	public static TextScrollTestObject getCurrentFileInEditorTab() {
		TestObject[] styledTexts = DOF.getRoot().find(
				atDescendant("class", "org.eclipse.swt.custom.StyledText"));
		for (int i = 0; i < styledTexts.length; i++) {
			String ancestorLine = DOF.getAncestorLine(styledTexts[i]);
			if (ancestorLine.equals(ANCESTORS_OF_OPENED_FILE)
					&& isVisible(styledTexts[i])) {
				return (TextScrollTestObject) styledTexts[i];
			}
		}
		return null;
	}

	public static ScrollTestObject getObjectDiagramCanvas_rft7() {
		TestObject[] to = getRoot().find(
				atDescendant("class", "org.eclipse.draw2d.FigureCanvas"));
		for (int i = 0; i < to.length; i++) {
			TestObject parent = (TestObject) to[i].invoke("getParent");
			if (parent.getProperty("class").equals(
					"org.eclipse.gef.ui.rulers.RulerComposite")) {
				return (ScrollTestObject) to[i];
			}
		}
		return null;
	}

	public static PaletteGuiSubitemTestObject getPaletteRoot() {
		TestObject[] to = getRoot().find(atDescendant(".class", "org.eclipse.gef.palette.PaletteRoot"));
		if (to != null) {
			for (TestObject obj : to) {
				if (isVisible(obj)) {
					return (PaletteGuiSubitemTestObject) obj;
				}
			}
		}
		return null;
	}

	/**
	 * Description : This method is used to find eclipse shell.
	 * 
	 * @param Regex
	 *            The Name of any eclipse based app. For Sybase WorkSpace,
	 *            <code> new Regex("Sybase WorkSpace")</code> is enough.
	 * @return <b>TestObject </b> The specified the app shell.
	 * @version v0.1
	 * @author Mark Nowacki
	 * @since July 31, 2007</br><br>
	 *        <b>Example </b> <br>
	 *        To get the Shell of Sybase WorkSpace <br>
	 * <br>
	 *        <code>TestObject workSpaceShell = getAppWindow(new Regex("Sybase WorkSpace"));</code>
	 */
	private static TopLevelTestObject getAppWindow(Regex windowCaption) {
		TestObject shell = null;
		int count = -1;

		TestObject[] to = RationalTestScript.find(atChild("class",
				"org.eclipse.swt.widgets.Shell"));

		if (null != to) {
			count = to.length;
			if (0 < count && null != windowCaption) {
				for (int i = 0; i < count; i++) {

					if (windowCaption.matches((String) to[i]
							.getProperty("text"))) {
						shell = to[i];
						break;
					}
				}
			}
		}
		return (TopLevelTestObject) shell;
	}

	public static final int RELATIVE_DIRECTION_UPON = 1;
	public static final int RELATIVE_DIRECTION_BELOW = 2;
	public static final int RELATIVE_DIRECTION_LEFT = 3;
	public static final int RELATIVE_DIRECTION_RIGHT = 4;

	public static GuiTestObject getGuiObjectCloseToGuiObject(TestObject parent,
			String className, GuiTestObject referentialObject, int direction) {
		int ref_x = RectangleHelper.getX(referentialObject);
		int ref_y = RectangleHelper.getY(referentialObject);
		int ref_width = RectangleHelper.getWidth(referentialObject);
		int ref_height = RectangleHelper.getHeight(referentialObject);
		TestObject[] objs = parent.find(RationalTestScript.atDescendant(
				"class", className));
		int minDistance = 10000;
		GuiTestObject candidateObject = null;
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				// System.out.println(objs[i].getProperties());
				int obj_x = RectangleHelper.getX(objs[i]);
				int obj_y = RectangleHelper.getY(objs[i]);
				int obj_width = RectangleHelper.getWidth(objs[i]);
				int obj_height = RectangleHelper.getHeight(objs[i]);
				if (direction == RELATIVE_DIRECTION_BELOW) {
					int distance = obj_y - ref_y - ref_height;
					if (distance < minDistance && distance >= 0) {
						minDistance = distance;
						candidateObject = (GuiTestObject) objs[i];
					}
				}
				if (direction == RELATIVE_DIRECTION_UPON) {
					int distance = ref_y - obj_height - obj_y;
					if (distance < minDistance && distance >= 0) {
						minDistance = distance;
						candidateObject = (GuiTestObject) objs[i];
					}
				}
				if (direction == RELATIVE_DIRECTION_LEFT) {
					int distance = ref_x - obj_width - obj_x;
					if (distance < minDistance && distance >= 0) {
						minDistance = distance;
						candidateObject = (GuiTestObject) objs[i];
					}
				}
				if (direction == RELATIVE_DIRECTION_RIGHT) {
					int distance = obj_x - ref_width - ref_x;
					if (distance < minDistance && distance >= 0) {
						minDistance = distance;
						candidateObject = (GuiTestObject) objs[i];
					}
				}
			}
			// System.out.println(minDistance);
			return candidateObject;
		} else
			return null;

	}

	public static TestObject[] getallTable(TestObject parent) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Table"));
		if (tables != null && tables.length > 0) {
			System.out.println("There are " + tables.length + " tables");
			return (TestObject[]) tables;
		} else
			return null;
	}

	public static TextGuiSubitemTestObject getSpinner(TestObject parent,
			String string) {
		TestObject[] spinners = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Spinner"));
		if (spinners != null && spinners.length > 0) {
			for (int i = 0; i < spinners.length; i++) {
				if (spinners[i].getProperty("toolTipText").toString().equals(
						string)) {
					return (TextGuiSubitemTestObject) spinners[i];
				}
			}
		}
		return null;
	}

	public static TestObject[] getSpinner(TestObject parent) {
		TestObject[] spinners = (TestObject[]) parent.find(RationalTestScript
				.atDescendant("class", "org.eclipse.swt.widgets.Spinner"));
		return spinners;
	}

	public static GuiTestObject getEnabledButton(TestObject parent, String text) {
		// root = getRoot();
		TestObject[] buttons = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Button"));
		for (int i = 0; i < buttons.length; i++) {
			// System.out.println(buttons[i].getProperty("text").toString());
			if ((buttons[i].getProperty("text").toString().equals(text))
					& buttons[i].getProperty("enabled").toString().equals(
							"true")) {
				return (GuiTestObject) buttons[i];
			}
		}
		return null;
	}

	public static TabitemTestObject getCTabItem(TestObject parent,
			String caption) {
		TestObject[] tabs = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.custom.CTabItem"));
		if (tabs != null && tabs.length > 0) {
			for (int i = 0; i < tabs.length; i++) {
				if (tabs[i].getProperty("text") != null && tabs[i].getProperty("text").toString().indexOf(
								caption) != -1)
					return (TabitemTestObject) tabs[i];
			}
		}
		return null;
	}

	public static ScrollTestObject getCComboTip(TestObject parent) {
		TestObject[] combos = parent.find(atDescendant("class",
				"com.sybase.uep.common.guiutils.CComboWithTooltip"));
		if (combos != null && combos.length > 0)
			return (ScrollTestObject) combos[0];
		return null;
	}

	public static ScrollTestObject getCComboTip(TestObject parent, String label) {
		TestObject[] combos = parent.find(atDescendant("class",
				"com.sybase.uep.common.guiutils.CComboWithTooltip"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (combos[i].getProperty(".priorLabel").equals(label)) {
					return (ScrollTestObject) combos[i];
				}
			}
		}
		return null;
	}

	public static TestObject getDualHeadersTree(TestObject parent) {
		TestObject[] trees = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.ui.table.DualHeadersTree"));
		if (trees != null && trees.length > 0) {
			for (TestObject tree : trees) {
				if (isVisible(tree)) {
					return (TestObject) tree;
				}
			}
		}
		return null;
	}

	public static ScrollTestObject getDualHeadersTable(TestObject parent) {
		TestObject[] tables = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.ui.table.DualHeadersTable"));
		if (tables != null && tables.length > 0) {
			for (TestObject table : tables) {
				if (isVisible(table)) {
					return (ScrollTestObject) table;
				}
			}
		}
		return null;

	}

	// Need to run WN.doubleClike(path) before use this method.
	public static GefEditPartTestObject getActiveObjectDiagramCanvas() {
		TestObject[] objs = getRoot()
				.find(RationalTestScript.atDescendant(".class",
							"com.sybase.uep.tooling.ui.diagram.edit.parts.EMODiagramEditPart"));
		if (objs != null && objs.length > 0) {
			System.out.println(objs.length);
			for (TestObject obj : objs) {
				// System.out.println(obj.invoke("hasFocus").toString());
				if (obj.invoke("hasFocus").toString().equals("true")) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		System.out.println("Not found any canvas");
		return null;
	}

	public static GefEditPartTestObject getMboFigure(TestObject parent,
			String name) {
		TestObject[] objs = parent.find(atDescendant(".class",
								"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (DOF.getMboNameFigure(obj, name) != null) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFScreenFigure(TestObject parent,String name) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (DOF.getWFScreenNameFigure(obj, name) != null) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}
	
	//ff add :used to find the screen ,in the name of "general" according to the screen name 
	public static boolean getWFScreenFigure(String str) {
		TestObject[] objs = DOF.getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				((GefEditPartTestObject) obj).click();
				sleep(0.5);
				String label = DOF.getTextField(DOF.getRoot(), "Name:").getProperty("text").toString();
				if(label.equals(str))
					return true;
			}
		}
		return false;
	}
	
	//ff add:use to doubelClick the screen:
	public static GefEditPartTestObject getWFScreenFigureToClick(String str) {
		TestObject[] objs = DOF.getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart"));
		if (objs != null && objs.length > 0) {
		for (TestObject obj : objs) {
		((GefEditPartTestObject) obj).click();
		sleep(0.5);
		String label = DOF.getTextField(DOF.getRoot(), "Name:").getProperty("text").toString();
		if(label.equals(str))
		return (GefEditPartTestObject) obj;
		}
		}
		return null;
		}
	
	//<<<<<<<<<<<<<<20120305<<<<<<<<<<<<<<<
	
	
	public static GefEditPartTestObject getWFMenuFigure(TestObject parent) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.MenuMenuItemsCompartmentEditPart"));
//		System.out.println(objs.length);
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFCustomActionsFigure(TestObject parent) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenActionsEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}

	public static GefEditPartTestObject getWFCustomMenuFigure(TestObject parent) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenActionsCompartmentEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFMenuItemFigure(TestObject parent,String name) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.MenuItemNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (obj.getProperty("text").equals(name)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GuiTestObject getWFCustomActionFigure(TestObject parent, String name) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenActionItemNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (obj.getProperty("text").equals(name)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GuiTestObject getWFCustomMenuItemFigure(TestObject parent, String name) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenActionItemNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (obj.getProperty("text").equals(name)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GefEditPartTestObject getMboNameFigure(TestObject parent,
			String name) {
		TestObject[] objs = parent.find(atDescendant(".class",
								"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (obj.getProperty("text").toString().trim().equals(name)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFScreenNameFigure(TestObject parent, String name) {
		TestObject[] objs =  parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ScreenNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				String actualName = obj.getProperty("text").toString();
				if(!actualName.endsWith("...")){
					if(actualName.equals(name)){
						return (GefEditPartTestObject)obj;
					}
				}
				else if(name.startsWith(actualName.replace("...", ""))){
					((GefEditPartTestObject)obj).click();
					DOF.getCTabItem(DOF.getRoot(), "Properties");
					sleep(0.5);
					PropertiesTabHelper.clickTabName("General");
					sleep(0.5);
					if(WO.getText(DOF.getTextField(DOF.getRoot(), "Name:")).equals(name)){
						return (GefEditPartTestObject)obj;
					}
				}
				
//				if (obj.getProperty("text").toString().trim().equals(name)) {
//					return (GefEditPartTestObject) obj;
//				}
			}
		}
		return null;
	}

	public static GefEditPartTestObject getAssociationFigure(TestObject parent,
			String fromMbo, String toMbo) {
		TestObject[] objs = parent.find(atDescendant(".class",
								"com.sybase.uep.tooling.ui.diagram.edit.parts.EAssociationEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				TestObject source = (TestObject) obj.invoke("getSource");
				TestObject target = (TestObject) obj.invoke("getTarget");
				if (DOF.getMboNameFigure(source, fromMbo) != null
						&& DOF.getMboNameFigure(target, toMbo) != null) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static Point getAssociationSourceAnchorPoint(TestObject association) {
		TestObject sourceAnchor = (TestObject) association
				.invoke("getSourceConnectionAnchor");
		// NativeInvoker.printMethods(sourceAnchor);
		TestObject box = (TestObject) sourceAnchor.invoke("getBox");
		TestObject center = (TestObject) box.invoke("getCenter");
		NativeInvoker.printMethods(center);
		Double x = (Double) center.invoke("preciseX");
		Double y = (Double) center.invoke("preciseY");
		System.out.println(x + ":" + y);
		return new Point(x.intValue(), y.intValue());

	}

	public static GefEditPartTestObject getMboAttributesFigure(TestObject parent) {
		TestObject[] objs = getRoot()
				.find(
						RationalTestScript
								.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectAttributesEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject) objs[0];
		}
		return null;
	}

	public static GefEditPartTestObject getMboAttributeFigure(
			TestObject parent, String text) {
		TestObject[] objs = getRoot().find(RationalTestScript.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EAttributeEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				String screenName = obj.getProperty("text").toString();
				screenName = screenName.substring(0, screenName.indexOf(":")).trim();
				if (screenName.equals(text)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GefEditPartTestObject getMboOperationFigure(
			TestObject parent, String text) {
		TestObject[] objs = getRoot()
				.find(
						RationalTestScript
								.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EOperationEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				if (obj.getProperty("text").equals(text)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GefEditPartTestObject getMboOperationContainerFigure(
			TestObject parent, String text) {
		TestObject[] objs = getRoot()
				.find(
						RationalTestScript
								.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectOperationsEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				System.out.println(obj.invoke("getOperationsTitle"));
				// NativeInvoker.printMethods(obj);
				if (obj.getProperty("text").equals(text)) {
					return (GefEditPartTestObject) obj;
				}
			}
		}
		return null;
	}

	public static GefEditPartTestObject getMboNoteFigure() {
		TestObject[] objs = getRoot()
				.find(
						RationalTestScript
								.atDescendant(".class",
										"org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart"));
		if (objs != null && objs.length > 0) {
			NativeInvoker.printMethods(objs[0]);
		}
		return null;
	}

	public static ScrollTestObject getMapperFigureContorol(TestObject parent) {
		TestObject[] objs = parent.find(atDescendant(".class",
				"com.sybase.uep.tooling.common.ui.mapper.MapperFigureControl"));
		// System.out.println(objs.length);
		for (TestObject obj : objs) {
			if (isVisible(obj)) {
				// System.out.println("find mapper");
				return (ScrollTestObject) obj;
			}
		}
		return null;
	}

	public static GuiTestObject getAnchorDecoration(TestObject parent) {
		TestObject[] objs = parent.find(atDescendant("class",
				"com.sybase.uep.tooling.common.ui.mapper.AnchorDecoration"));
		System.out.println(objs.length);
		for (TestObject obj : objs) {
			if (isVisible(obj)) {
				return (GuiTestObject)obj;
			}
		}
		return null;
	}

	public static GefEditPartTestObject getObjectDiagramCanvas() {
		TestObject[] objs = getRoot()
				.find(atDescendant(".class","com.sybase.uep.tooling.ui.diagram.edit.parts.EMODiagramEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}

	public static GefEditPartTestObject getWFScreenCanvas() {
		TestObject[] objs = getRoot()
		.find(atDescendant(".class",
				"com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFFlowDesignCanvas() {
//		TestObject[] objs = getRoot()
//			.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.XmlBobDocumentEditPart"));
		TestObject[] objs = getRoot()
			.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.XmlBobDocumentEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFScreenDesignCanvas() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFScreenDisplayFigure() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.DisplayEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFActivateFlowStartingPointFigure() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFCredentialRequestFlowStartingPointFigure() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFClientInitiateFlowStartingPointFigure() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}

	public static GefEditPartTestObject getWFServerInitiateFlowStartingPointFigure() {
		TestObject[] objs = getRoot().find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	
	public static void printAllClasses(TestObject parent) {
		TestObject[] to = parent.find(atDescendant(".domain", "Java"));
		Hashtable<String, String> hash = new Hashtable<String, String>();
		for (TestObject obj : to) {
			hash.put(obj.getObjectClassName(), "");
		}
		for (String clazz : hash.keySet()) {
			System.out.println(clazz);
		}
	}

	public static GuiTestObject getTextFieldByToolTip(TestObject parent, String string) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				if (texts[i].getProperty("toolTipText") != null
						&& texts[i].getProperty("toolTipText").toString()
								.equals(string))
					return (GuiTestObject) texts[i];
			}
		}
		return null;
	}

	public static GuiTestObject getTabbedPropertyTitle(	TestObject parent) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle"));
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				if (isVisible(texts[i])){
					return (GuiTestObject) texts[i];
				}
			}
		}
		return null;
	}
	
	public static GuiTestObject getLayoutComposite(	TestObject parent, String pirorLabel) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant("class", "org.eclipse.ui.forms.widgets.LayoutComposite"));
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				if (isVisible(texts[i]) && texts[i].getProperty(".priorLabel")!=null && texts[i].getProperty(".priorLabel").equals(pirorLabel)){
					return (GuiTestObject) texts[i];
				}
			}
		}
		return null;
	}

	public static GuiTestObject getSybaseCCombo(TestObject parent) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "com.sybase.uep.common.guiutils.CComboWithTooltip"));
		if (texts != null && texts.length > 0) {
			for (int i = 0; i < texts.length; i++) {
				if (isVisible(texts[i])){
					return (GuiTestObject) texts[i];
				}
			}
		}
		return null;
	}

	public static GuiTestObject getTextFieldByAncestorLine(TestObject parent, String string) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		for(TestObject text:texts){
			if(DOF.getAncestorLine(text).equals(string)&&isVisible(text)){
				return (GuiTestObject)text;
			}
		}
		return null;
	}

	public static GuiTestObject getTextFieldByIndex(TopLevelTestObject parent,String index) {
		TestObject[] texts = parent.find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Text"));
		for(TestObject text:texts){
			if(text.getProperty(".classIndex").toString().equals(index)){
				return (GuiTestObject)text;
			}
		}
		return null;
	}

	public static ShellTestObject getShellObject(String string) {
		TestObject[] shells = find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Shell"));
		for(TestObject shell:shells){
			if(isVisible(shell) && shell.getProperty(".captionText").toString().equals(string)){
				return (ShellTestObject)shell;
			}
		}
		return null;
		
	}

	public static GuiTestObject getWFStartPointNameFigure(TestObject parent, String string) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointNameEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	

	public static GuiTestObject getWFStartPointFigure(String type) {
		if(type.equals(WorkFlow.SP_SERVER_INIT)){
			return DOF.getWFServerInitiateFlowStartingPointFigure();
		}
		if(type.equals(WorkFlow.SP_CLIENT_INIT)){
			return DOF.getWFClientInitiateFlowStartingPointFigure();
		}
		if(type.equals(WorkFlow.SP_ACTIVATE)){
			return DOF.getWFActivateFlowStartingPointFigure();
		}
		if(type.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
			return DOF.getWFCredentialRequestFlowStartingPointFigure();
		}
		throw new RuntimeException("Unknown starting point type: "+type);
	}
	//ff1.16>>>>>>>>>>>>>
	public static GuiTestObject getWFServerPointNameFigure(TestObject parent, String string) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointNameEditPart"));
		if (objs != null && objs.length > 0) {
			return (GefEditPartTestObject)objs[0];
		}
		return null;
	}
	//ff1.16<<<<<<<<<<<<<<
	public static String getWFScreenName(TestObject screen){
		TestObject[] objs = screen.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ScreenNameEditPart"));
		if (objs.length ==1) {
			return objs[0].getProperty("text").toString();
		}
		throw new RuntimeException("Not able to find screen name");
	}
	
	public static String getWFServerInitiatedStartPointName(TestObject sp){
		TestObject[] objs = sp.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointNameEditPart"));
		if (objs.length ==1) {
			return objs[0].getProperty("text").toString();
		}
		throw new RuntimeException("Not able to find server initiated start point name");
	}
	//ff1.16>>>>>>>>>>>>>
	
	public static String getWFActivateInitiatedStartPointName(TestObject sp){
		TestObject[] objs = sp.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointNameEditPart"));
		if (objs.length ==1) {
			return objs[0].getProperty("text").toString();
		}
		throw new RuntimeException("Not able to find Activate initiated start point name");
	}
	
	public static String getWFClientInitiatedStartPointName(TestObject sp){
		TestObject[] objs = sp.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointNameEditPart"));
		if (objs.length ==1) {
			return objs[0].getProperty("text").toString();
		}
		throw new RuntimeException("Not able to find client initiated start point name");
	}
	
	public static String getWFCredentialRequestStartPointName(TestObject sp){
		TestObject[] objs = sp.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointNameEditPart"));
		if (objs.length ==1) {
			return objs[0].getProperty("text").toString();
		}
		throw new RuntimeException("Not able to find CredentialRequest start point name");
	}
	//<<<<<<<<<ff1.16
	public static GefEditPartTestObject getWFGoToFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.GotoNavigationEditPart"));
		if (objs != null && objs.length > 0) {
			for(TestObject obj:objs){
				TestObject s = (TestObject)obj.invoke("getSource");
				TestObject t = (TestObject)obj.invoke("getTarget");
				String objectClassNameS = s.getObjectClassName();
				String objectClassNameT = t.getObjectClassName();
				
				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenFigure(target))
//					if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(t).equals(target))
				{
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart") && source.equals(WorkFlow.SP_CLIENT_INIT)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart") && source.equals(WorkFlow.SP_ACTIVATE)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart") && source.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
						return (GefEditPartTestObject)obj;
					}
					//ff11.2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && source.equals(WorkFlow.SP_SERVER_INIT)){
						return (GefEditPartTestObject)obj;
					}
					//ff11.2<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && getWFServerInitiatedStartPointName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(s).equals(source)){
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenFigure(source)){
						return (GefEditPartTestObject)obj;
					}
				}
			}
//			TestObject sa = (TestObject)objs[0].invoke("getSourceConnectionAnchor");
//			TestObject position1 = (TestObject)sa.invoke("getAnchorPosition");
//			TestObject ta = (TestObject)objs[0].invoke("getTargetConnectionAnchor");
//			TestObject position2 = (TestObject)ta.invoke("getAnchorPosition");
//			
//			System.out.println(position1.invoke("toString"));
//			System.out.println(position2.invoke("toString"));
		}
		return null;
	}
	//>> flv 2012/03/21 add for async error link type
	public static GefEditPartTestObject getWFAsyncErrorLinkFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.AsyncRequestErrorNavigationEditPart"));
		if (objs != null && objs.length > 0) {
			for(TestObject obj:objs){
				TestObject s = (TestObject)obj.invoke("getSource");
				TestObject t = (TestObject)obj.invoke("getTarget");
				String objectClassNameS = s.getObjectClassName();
				String objectClassNameT = t.getObjectClassName();
				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenFigure(target))
//				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(t).equals(target))
				{
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart") && source.equals(WorkFlow.SP_CLIENT_INIT)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart") && source.equals(WorkFlow.SP_ACTIVATE)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart") && source.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && getWFServerInitiatedStartPointName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
				}
			}
//			TestObject sa = (TestObject)objs[0].invoke("getSourceConnectionAnchor");
//			TestObject position1 = (TestObject)sa.invoke("getAnchorPosition");
//			TestObject ta = (TestObject)objs[0].invoke("getTargetConnectionAnchor");
//			TestObject position2 = (TestObject)ta.invoke("getAnchorPosition");
//			
//			System.out.println(position1.invoke("toString"));
//			System.out.println(position2.invoke("toString"));
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFListviewDetailsNavigationFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ListviewDetailsNavigationEditPart"));
		return getLink(objs, source, target);
	}
	
	private static GefEditPartTestObject getLink(TestObject[] objs, String source, String target){
		if (objs != null && objs.length > 0) {
			for(TestObject obj:objs){
				TestObject s = (TestObject)obj.invoke("getSource");
				TestObject t = (TestObject)obj.invoke("getTarget");
				String objectClassNameS = s.getObjectClassName();
				String objectClassNameT = t.getObjectClassName();
				
//				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(t).equals(target))
				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenFigure(target))
				{
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart") && source.equals(WorkFlow.SP_CLIENT_INIT)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart") && source.equals(WorkFlow.SP_ACTIVATE)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart") && source.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
						return (GefEditPartTestObject)obj;
					}
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && getWFServerInitiatedStartPointName(s).equals(source)){
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart")){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
				}
			}
		}
		return null;
		
	}

	public static Object getAsyncRequestErrorNavigationFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.AsyncRequestErrorNavigationEditPart"));
		return getLink(objs, source, target);
		
	}
	
	public static GefEditPartTestObject getOperationSuccessNavigationFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.OperationSuccessNavigationEditPart"));
		return getLink(objs, source, target);
	}
	
	public static GefEditPartTestObject getOperationErrorNavigationFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.OperationErrorNavigationEditPart"));
		return getLink(objs, source, target);
	}
	
	public static TestObject[] getWFEditBoxFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.EditBoxEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	
	//add by yanxu
	public static TestObject[] getWFCheckBoxFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.CheckboxEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFChoiceFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ChoiceEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	
	public static TestObject[] getWFSliderFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.SliderEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFSignatureFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.SignatureEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFHtmlViewFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.HtmlViewEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFListViewFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ListviewEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFLabelFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.LabelEditPart"));
//		printMethods(objs[0]);
		return objs;
	}
	public static TestObject[] getWFLinkFigures(TestObject parent){
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.LinkEditPart"));
//		printMethods(objs[0]);
		return objs;
	}

//end
	
	
	
	
	public static GefEditPartTestObject getWFNoteFigure(TestObject parent, String text){
		TestObject[] objs = parent.find(atDescendant(".class","org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart"));
		for(TestObject obj:objs){
			if(getWFNoteDescriptionFigure(obj, text)!=null){
				return (GefEditPartTestObject)obj;
			}
		}
		return null;
	}
	
	public static GefEditPartTestObject getWFNoteDescriptionFigure(TestObject parent, String text){
		TestObject[] objs = parent.find(atDescendant(".class","org.eclipse.gmf.runtime.diagram.ui.editparts.DescriptionCompartmentEditPart"));
		for(TestObject obj:objs){
			if(obj.getProperty("text").equals(text)){
				return (GefEditPartTestObject)obj;
			}
		}
		return null;
	}
//	//ff10.12>>>>>>>>>>>>
//	public static GefEditPartTestObject getConditionalOperationSuccessFigure(TestObject parent, String source, String target) {
//		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ConditionalGotoNavigationEditPart"));
//		if (objs != null && objs.length > 0) {
//			for(TestObject obj:objs){
//				TestObject s = (TestObject)obj.invoke("getSource");
//				TestObject t = (TestObject)obj.invoke("getTarget");
//				String objectClassNameS = s.getObjectClassName();
//				String objectClassNameT = t.getObjectClassName();
//				
//				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(t).equals(target))
//				{
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart") && source.equals(WorkFlow.SP_CLIENT_INIT)){
//						return (GefEditPartTestObject)obj;
//					}
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart") && source.equals(WorkFlow.SP_ACTIVATE)){
//						return (GefEditPartTestObject)obj;
//					}
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart") && source.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
//						return (GefEditPartTestObject)obj;
//					}
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && getWFServerInitiatedStartPointName(s).equals(source)){
//						return (GefEditPartTestObject)obj;
//					}
//					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(s).equals(source)){
//						return (GefEditPartTestObject)obj;
//					}
//				}
//			}
//		}
//		return null;
//	}

	public static GefEditPartTestObject getConditionalOperationSuccessFigure(TestObject parent, String source, String target) {
		TestObject[] objs = parent.find(atDescendant(".class","com.sybase.uep.xbw.diagram.edit.parts.ConditionalOperationSuccessNavigationEditPart"));
		if (objs != null && objs.length > 0) {
			for(TestObject obj:objs){
				TestObject s = (TestObject)obj.invoke("getSource");
				TestObject t = (TestObject)obj.invoke("getTarget");
				String objectClassNameS = s.getObjectClassName();
				String objectClassNameT = t.getObjectClassName();
				
				if(objectClassNameT.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(t).equals(target))
				{
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.StartFlowStartingPointEditPart") && source.equals(WorkFlow.SP_CLIENT_INIT)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ActivateFlowStartingPointEditPart") && source.equals(WorkFlow.SP_ACTIVATE)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.CredentialRequestStartingPointEditPart") && source.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
						return (GefEditPartTestObject)obj;
					}
					//ff11.2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && source.equals(WorkFlow.SP_SERVER_INIT)){
						return (GefEditPartTestObject)obj;
					}
					//ff11.2<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.EmailStartingPointEditPart") && getWFServerInitiatedStartPointName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
					if(objectClassNameS.equals("com.sybase.uep.xbw.diagram.edit.parts.ScreenEditPart") && DOF.getWFScreenName(s).equals(source)){
						return (GefEditPartTestObject)obj;
					}
				}
			}
//			TestObject sa = (TestObject)objs[0].invoke("getSourceConnectionAnchor");
//			TestObject position1 = (TestObject)sa.invoke("getAnchorPosition");
//			TestObject ta = (TestObject)objs[0].invoke("getTargetConnectionAnchor");
//			TestObject position2 = (TestObject)ta.invoke("getAnchorPosition");
//			
//			System.out.println(position1.invoke("toString"));
//			System.out.println(position2.invoke("toString"));
		}
		return null;
	}	

}
