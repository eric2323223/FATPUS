/*
 * Created on Jun 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.rational.test.ft.sys.NotSupportedException;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.Utilities;
import com.sybase.automation.framework.common.XMLUtil;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.IWindow;
import component.editor.SQLEditor;

/**
 * @author xfu
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ComponentGenerator {

	/** *********************************************************** */
	private static String CHECKBOX = "CheckBox";

	private static String RADIOBUTTON = "RadioButton";

	private static String TEXTBOX = "Text";

	private static String BUTTON = "Button";

	private static String TREE = "Tree";

	private static String TABLE = "Table";

	private static String LISTBOX = "xx";

	private static String COMBOBOX = "ComboBox";

	public static String WINDOW = "Frame";

	private static String MENU = "MenuBar";

	private static String CONTEXTMENU = "PopupMenu";

	private static String TABFOLDER = "PageTabList";

	///////////////////////////

	public static void main(String[] args) throws DocumentException {

	}

	static void testFindFile() throws DocumentException {

		//		File com = new File(getDefPath(path));
		//		handleSimpleFile(com);
	}

	/**
	 * @param componentClass
	 * @param string
	 */
	public static void generate(Object ob, Class componentClass, String root) {

		StringBuffer ret = new StringBuffer();
		ret
				.append("\t\t//********Beginning of Component Methods Generation********\n");

		Method[] m = componentClass.getDeclaredMethods();
		String upperName = componentClass.getName();
		int index = upperName.lastIndexOf('.') + 1;
		upperName = upperName.substring(index);
		String first = upperName.substring(0, 1).toLowerCase();
		String lowerName = first + upperName.substring(1);
		ret.append("\t\t" + upperName + " " + lowerName + " = new " + upperName
				+ "();\n");

		for (int i = 0; i < m.length; i++) {

			String methodName = m[i].getName();
			if (methodName.equals("testMain")) {
				continue;
			}
			String type = methodName.substring(methodName.length() - 3,
					methodName.length());
			//			System.out.println(type);
			String method = "UNKNOW";
			if (type.equals("txt")) {
				method = ".setText(\"\")";

			} else if (type.equals("btn")) {
				method = ".click()";

			} else if (type.equals("chx")) {
				method = ".check()";
			} else if (type.equals("rdb")) {
				method = ".click()";
			} else if (type.equals("lbx")) {
				method = ".select(\"\")";
			} else if (type.equals("cbx")) {
				method = ".select(\"\")";
			} else if (type.equals("tbl")) {
				method = ".setCell(1,2,\"\")";
			} else if (type.equals("tre")) {
				method = ".clickAtPath(\"\")";
			} else if (type.equals("mnu")) {
				method = ".clickAtPath(\"\")";
			} else if (type.equals("tbf")) {
				method = ".selectTab(\"\")";
			} else if (type.equals("cmu")) {
				method = ".clickAtPath(\"\")";
			} else if (type.equals("dow")) {
				method = ".verifyTitle(\"\")";
			} else {
				
				//				Method curr=m[i];
				//				Class[] types=curr.getParameterTypes();
				//				for (int j = 0; j < types.length; j++) {
				//					types[j].getName();
				//				}
				method = "";
			}
			ret.append("\t\t" + lowerName + "." + m[i].getName() + "()"
					+ method + ";\n");

		}
		ret
				.append("\t\t\n//***********End of Component Methods Generation**********\n");
		//======insert into the testmain function******************//
		String str = ob.toString();
		str = root + "/"
				+ str.substring(0, str.indexOf("@")).replaceAll("\\.", "/")
				+ ".java";

		String src = FileUtil.readFromFile(str);
		String insertPoint = "public void testMain(Object[] args) {";
		index = src.indexOf(insertPoint) + insertPoint.length() + 1;
		if (index == -1) {
			throw new IllegalStateException("Can not find the testMain method!");
		}
		src = src.substring(0, index) + ret.toString() + src.substring(index);
		FileUtil.writeToFile(str, src);

	}

	public static void generate(Object ob, String root)
			throws DocumentException {
		String str = ob.toString();
		System.out.println("in generating....." + str);
		str = "/" + str.substring(0, str.indexOf("@")) + ".java";
		String path = str.replaceAll("\\.", "/");
		File com = new File(toDef(path, root));
		handleSimpleFile(com);
	}

	private static String toDef(String path, String root) {
		return (root + "/resources" + path).replaceAll(".java", ".rftdef");
	}

	private static String toSrc(String path) {
		path = path.replaceAll("resources", "");
		path = path.replace('\\', '@');
		path = path.replaceAll("@@", "@");
		path = path.replace('@', '/');
		path = path.replaceAll(".rftdef", ".java");
		return path;
	}

	public static void recursivelyHandle(File file) {
		if (file.isDirectory()) {
			File[] subfiles = file.listFiles();
			if (subfiles != null) {
				for (int i = 0; i < subfiles.length; i++) {
					recursivelyHandle(subfiles[i]);
				}
			}
		} else if (file.isFile() && file.getName().indexOf("java") != -1) {
			try {
				handleSimpleFile(file);
			} catch (Exception ex) {
				// ignore
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @param def
	 * @throws DocumentException
	 */
	private static void handleSimpleFile(File def) throws DocumentException {

		Document doc = XMLUtil.readFile(def);
		Element scriptNameMap = (Element) doc
				.selectSingleNode("/ScriptDefinition/ScriptNameMap");
		List testObjects = doc
				.selectNodes("/ScriptDefinition/ScriptNameMap/TestObject");

		String src = addImports(def);
		src = deleteOldCode(src);
		src = addComments(src);
		//generating the code
		for (Iterator iter = testObjects.iterator(); iter.hasNext();) {
			Element testObject = (Element) iter.next();
			String name = testObject.selectSingleNode("Name").getText();
			String type = testObject.selectSingleNode("Role").getText();

			if (type.equals(TEXTBOX)) {
				src = addMethod(src, build("txt", "TextBox", name));
			} else if (type.equals(BUTTON)) {
				src = addMethod(src, build("btn", "Button", name));
			} else if (type.equals(CHECKBOX)) {
				src = addMethod(src, build("chx", "CheckBox", name));

			} else if (type.equals(RADIOBUTTON)) {
				src = addMethod(src, build("rdb", "RadioButton", name));

			} else if (type.equals(LISTBOX)) {
				src = addMethod(src, build("lbx", "ListBox", name));

			} else if (type.equals(COMBOBOX)) {
				src = addMethod(src, build("cbx", "ComboBox", name));

			} else if (type.equals(TABLE)) {
				src = addMethod(src, build("tbl", "Table", name));

			} else if (type.equals(TREE)) {
				src = addMethod(src, build("tre", "Tree", name));

			} else if (type.equals(MENU)) {
				src = addMethod(src, build("mnu", "Menu", name));

			} else if (type.equals(CONTEXTMENU)) {
				src = addMethod(src, build("cmu", "Menu", name));

			} else if (type.equals(TABFOLDER)) {
				src = addMethod(src, build("tbf", "TabFolder", name));

			} else {
				System.out.println("*************** New type reported: " + type
						+ " ,please contact the administrator*"
						+ "**********************");
				//throw new NotSupportedException("Not supported widget type");
			}

		}
		src = addWindowWidget(src);
		/* write the src back to source java file */
		FileUtil.writeToFile(toSrc(def.getAbsolutePath()), src);

	}

	/**
	 * @param src
	 */
	private static String deleteOldCode(String src) {
		int start = src
				.indexOf("\n\t/**********************widgets generation***********************/");

		if (start == -1) {
			/* first time */
			return src;
		} else {
			int end = src.lastIndexOf("}") - 2;
			return src.substring(0, start) + src.substring(end);
		}

	}

	/**
	 * @return
	 */
	private static String addComments(String src) {
		// TODO Auto-generated method stub

		return addMethod(src,
				"\n\n\t/**********************widgets generation***********************/");
	}

	/**
	 * @return
	 */
	private static String addImports(File def) {
		String src = FileUtil.readFromFile(toSrc(def.getAbsolutePath()));
		if (src
				.indexOf("import com.sybase.automation.framework.widget.eclipse.*;") == -1) {
			src = src
					.replaceFirst(
							"import",
							"/*************generated automatically***************/\nimport com.sybase.automation.framework.widget.eclipse.*;\nimport com.sybase.automation.framework.widget.interfaces.*;\nimport com.sybase.automation.framework.widget.interfaces.IWindow;\nimport");
		}
		return src;
	}

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	private static String build(String pre, String type, String name) {
		// TODO Auto-generated method stub
		String part_name = name.indexOf('_') == 0 ? name.substring(1) : name;
		String text = "\n\tpublic I" + type + " " + part_name + "_" + pre
				+ "(){\n\t\t return ewFactory.create" + type + "(" + name
				+ "());\n\t}\n";
		//System.out.println(text);
		return text;
	}

	private static String addWindowWidget(String src) {
		String text = "\n\tpublic IWindow ActiveWindow(){\n\t\treturn ewFactory.createWindow();\n\t}\n";
		int end = src.lastIndexOf("}") - 2;
		return src.substring(0, end) + text + src.substring(end);
	}

	/**
	 * @param src
	 * @param string
	 */
	private static String addMethod(String src, String string) {
		int end = src.lastIndexOf("}") - 2;
		return src.substring(0, end) + string + src.substring(end);

	}

}