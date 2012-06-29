package com.sybase.automation.framework.widget.helper;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.ITestDataTreeNodes;
import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class TreeHelper extends RationalTestScript
{
	/**
	 * Script Name   : <b>TreeHelper</b>
	 * Generated     : <b>Jul 23, 2010 5:33:11 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/07/23
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		expandTreePath(DOF.getEETree(), "SAP Servers->SAP->strlabpc3.sybase.com(3)->Application Components->Sales and Distribution->Sales->SalesOrder->GetList");
		System.out.println(isNodeChecked(DOF.getTree(DOF.getDialog("Deploy Mobile Application Project"), "Select Mobile Business Objects:"), "Mobile Business Objects->Default->product"));
		System.out.println(isNodeChecked(DOF.getTree(DOF.getDialog("Deploy Mobile Application Project"), "Select Mobile Business Objects:"), "Mobile Business Objects"));
	}
	
	public static String getFirstItem(ScrollGuiSubitemTestObject tree) {
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;

		cdTree = (ITestDataTree) tree.getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		if (cdTreeNode != null)
			return cdTreeNode[0].getNode().toString();
		else
			return null;
	}
	
	public static void printAllItems(TestObject tree) {
		Stack<ITestDataTreeNode[]> stack = new Stack<ITestDataTreeNode[]>();
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;

		cdTree = (ITestDataTree) tree.getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		stack.push(cdTreeNode);
		while (!stack.isEmpty()) {
			ITestDataTreeNode[] current = (ITestDataTreeNode[]) stack.pop();
			if (current == null) {
				continue;
			}
			for (int i = 0; i < current.length; i++) {
				System.out.println(current[i].getNode().toString());
				for(ITestDataTreeNode child:current[i].getChildren()){
					System.out.println("["+current[i].getNode().toString()+"]"+child.getNode().toString());
				}
				stack.push(current[i].getChildren());
			}
		}
	}
	
	public static void expandTreePath(GuiSubitemTestObject tree, String path)
	{
		expendAllAlongTheWay((ScrollGuiSubitemTestObject)tree, path);
	}

	
	private static ITestDataTreeNode getNode(TestObject tree, Pattern pattern){
		String[] parts = pattern.toString().split("->");
		ITestDataTreeNode result= null;
		boolean match = false;
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;
		
		cdTree = (ITestDataTree) tree.getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		for(String part:parts){
//			System.out.println(part);
			match = false;
			Pattern p = Pattern.compile(part);
			for(ITestDataTreeNode node: cdTreeNode){
				Matcher m = p.matcher(node.getNode().toString());
				if(m.matches()){
//				if(node.getNode().toString().equals(part)){
					result = node;
					cdTreeNode = node.getChildren();
					match = true;
					break;
				}
			}
		}
		if(!match){
			return null;
		}
		return result;
	}

	
	private static ITestDataTreeNode getNode(TestObject tree, String path){
		String[] parts = path.split("->");
		ITestDataTreeNode result= null;
		boolean match = false;
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;

		cdTree = (ITestDataTree) tree.getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		for(String part:parts){
//			System.out.println(part);
			match = false;
			for(ITestDataTreeNode node: cdTreeNode){
				if(node.getNode().toString().equals(part)){
					result = node;
					cdTreeNode = node.getChildren();
					match = true;
					break;
				}
			}
		}
		if(!match){
			return null;
		}
		return result;
	}
	
	public static boolean isNodeExpanded(GuiSubitemTestObject tree, String path){
		boolean findFlag = true;
		String[] paths = path.split("->");
		TestObject[] items = (TestObject[])tree.invoke("getItems");
		TestObject item = null;
		for(String p : paths){
			item = getNode(items, p);
			if(item==null){
				findFlag = false;
				break;
			}else{
				items = (TestObject[])item.invoke("getItems");
			}
		}
		if(findFlag==true){
			return new Boolean(item.invoke("getExpanded").toString()).booleanValue();
		}else{
			return findFlag;
		}
	}
	
	public static boolean isNodeExpanded(GuiSubitemTestObject tree, Pattern path){
		boolean findFlag = true;
		String[] paths = path.split("->");
		TestObject[] items = (TestObject[])tree.invoke("getItems");
		TestObject item = null;
		for(String p : paths){
			item = getNode(items, p);
			if(item==null){
				findFlag = false;
				break;
			}else{
				items = (TestObject[])item.invoke("getItems");
			}
		}
		if(findFlag==true){
			return new Boolean(item.invoke("getExpanded").toString()).booleanValue();
		}else{
			return findFlag;
		}
	}
	

	private static TestObject getNode(TestObject[] nodes, String name){
		for(TestObject n:nodes){
			if(n.invoke("getText").equals(name)){
				return n;
			}
		}
		return null;
	}
	
	public static void expendAllAlongTheWay(ScrollGuiSubitemTestObject tree,String path){
		int count = 0;
		int maxCount = 3;
//		System.out.println("Expending tree path: "+path);
	    while (true) {
			try {
				tree.click(atPath(path+"->Location(PLUS_MINUS)"));
				break;
			} catch (SubitemNotFoundException e) {
				if(count>=maxCount){
					throw e;
				}
				sleep(1);
				System.out.println("wait...");
				count++;
			}
		}
	    
	}
	
	
	public static boolean ifEETreeItemExist(TestObject tree, String path){
	    expendAllAlongTheWay((ScrollGuiSubitemTestObject)tree,path.substring(0, path.lastIndexOf("->")));
		if (path != null && !path.startsWith("->") && !path.endsWith("->")) {
			String[] items = path.split("->");
			ITestDataTree cdTree;
			ITestDataTreeNodes cdTreeNodes;
			ITestDataTreeNode[] cdTreeNode;

			cdTree = (ITestDataTree) tree.getTestData("tree");
			cdTreeNodes = cdTree.getTreeNodes();
			cdTreeNode = cdTreeNodes.getRootNodes();
			if (cdTreeNode == null) {
				return false;
			} else {
				for (int i = 0; i < items.length; i++) {
					boolean flag = false;
					for (int j = 0; j < cdTreeNode.length; j++) {
						if (items[i].trim().equals(cdTreeNode[j].getNode().toString().trim())) {
							if (cdTreeNode[j].getChildren() != null
									&& cdTreeNode[j].getChildCount() > 0) {
								cdTreeNode = cdTreeNode[j].getChildren();
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
	
	public static String[] getTopLevelNodes(TestObject tree){
		if(tree==null)
			return null;
		ITestDataTree cdTree;
        ITestDataTreeNodes cdTreeNodes;
        ITestDataTreeNode[] cdTreeNode;

        cdTree = (ITestDataTree) tree.getTestData("tree");
        cdTreeNodes = cdTree.getTreeNodes();
        cdTreeNode = cdTreeNodes.getRootNodes();
        if(cdTreeNode!= null && cdTreeNode.length>0){
        	String[] result = new String[cdTreeNode.length];
        	for(int i =0 ;i<cdTreeNode.length;i++){
        		result[i] = cdTreeNode[i].getNode().toString();
        	}
        	return result;
        }
        return new String[0];
	}
	
	public static String[] getNodesOfLevel(TestObject tree, int level){
		ITestDataTree cdTree;
        ITestDataTreeNodes cdTreeNodes;
        ITestDataTreeNode[] cdTreeNode;

        cdTree = (ITestDataTree) tree.getTestData("tree");
        cdTreeNodes = cdTree.getTreeNodes();
        cdTreeNode = cdTreeNodes.getRootNodes();
        Vector<ITestDataTreeNode[]> container = new Vector<ITestDataTreeNode[]>();
        container.add(cdTreeNode);
        int currentLevel = 0;
        while(currentLevel < level){
        	for(int i = 0;i<container.size();i++){
        		ITestDataTreeNode[] treeNode = container.remove(i);
        		for(int j=0;j<treeNode.length;j++){
        			if(treeNode[j].getChildren()!=null){
        				container.add(treeNode[j].getChildren());
        			}
        		}
        	}
        	currentLevel ++;
        }
        return convertVectorToStringArray(container);
	}
	
	private static String[] convertVectorToStringArray(
			Vector<ITestDataTreeNode[]> container) {
		java.util.List<String> list = new ArrayList<String>();
		for(int i=0;i<container.size();i++){
			ITestDataTreeNode[] treeNode = container.get(i);
			for(int j=0;j<treeNode.length;j++){
//				System.out.println(treeNode[j].getNode().toString());
				list.add(treeNode[j].getNode().toString());
			}
		}
		return (String[])list.toArray(new String[0]);
	}

	public static boolean ifTreeItemExist(TestObject tree, String path) {
	        if (path != null && !path.startsWith("->") && !path.endsWith("->")) {
	            String[] items = path.split("->");
	            ITestDataTree cdTree;
	            ITestDataTreeNodes cdTreeNodes;
	            ITestDataTreeNode[] cdTreeNode;

	            cdTree = (ITestDataTree) tree.getTestData("tree");
	            cdTreeNodes = cdTree.getTreeNodes();
	            cdTreeNode = cdTreeNodes.getRootNodes();
	            if (cdTreeNode == null) {
	                return false;
	            } else {
	                for (int i = 0; i < items.length; i++) {
	                    boolean flag = false;
	                    for (int j = 0; j < cdTreeNode.length; j++) {
	                        if (items[i].trim().equals(cdTreeNode[j].getNode().toString().trim())) {
	                            if (cdTreeNode[j].getChildren() != null && cdTreeNode[j].getChildCount() > 0) {
	                                cdTreeNode = cdTreeNode[j].getChildren();
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
	
	public static String matchPath(TestObject tree, Pattern pattern) {
		String path = pattern.toString();
		if (path != null && !path.startsWith("->") && !path.endsWith("->")) {
			String[] items = path.split("->");
			ITestDataTree cdTree;
			ITestDataTreeNodes cdTreeNodes;
			ITestDataTreeNode[] cdTreeNode;
			
			cdTree = (ITestDataTree) tree.getTestData("tree");
			cdTreeNodes = cdTree.getTreeNodes();
			cdTreeNode = cdTreeNodes.getRootNodes();
			String result="";
			if (cdTreeNode == null) {
				return null;
			} else {
				for (int i = 0; i < items.length; i++) {
					Pattern p = Pattern.compile(items[i]);
					boolean flag = false;
					for (int j = 0; j < cdTreeNode.length; j++) {
						Matcher m = p.matcher(cdTreeNode[j].getNode().toString());
						if(m.matches()){
							result = result +m.group(0)+"->";
//						if (items[i].trim().equals(cdTreeNode[j].getNode().toString().trim())) {
							if (cdTreeNode[j].getChildren() != null && cdTreeNode[j].getChildCount() > 0) {
								cdTreeNode = cdTreeNode[j].getChildren();
							}
							flag = true;
							break;
							
						}
					}
					if (flag == false)
						return null;
				}
				return result.substring(0, result.length()-2);
			}
		} else
			return null;
	}
	
	public static String[] getChildrenOfNode(GuiSubitemTestObject tree,	Pattern pattern) {
		ITestDataTreeNode node = getNode(tree, pattern);
		boolean isExpanded = isNodeExpanded(tree, pattern);
		if (node != null) {
			if(!isExpanded){
				tree.click(atPath(matchPath(tree, pattern)+"->Location(PLUS_MINUS)"));
				sleep(2);
			}
			String[] children = new String[node.getChildCount()];
			for (int i = 0; i < children.length; i++) {
				children[i] = node.getChildren()[i].getNode().toString();
			}
			return children;
		} else {
			return new String[] {};
		}
	}
	
	public static String getCellValue(GuiSubitemTestObject tree, String row, String column){
		try{
			tree.click(atCell(atRow(atPath(row)), atColumn(column)));
			sleep(1);
			tree.click(RIGHT, atCell(atRow(atPath(row)), atColumn(column)), atPoint(5,5));
			DOF.getContextMenu().click(atPath("Copy"));
			return ClipBoardUtil.getClipboardText();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Unable to retrieve value in column "+column);
			return null;
		}
	}
	
	public static String getCellValue(GuiSubitemTestObject tree, String row, int column){
		tree.click(atCell(atRow(atPath(row)), atColumn(column)), atPoint(5,5));
		sleep(1);
		tree.click(RIGHT, atCell(atRow(atPath(row)), atColumn(column)), atPoint(5,5));
		DOF.getContextMenu().click(atPath("Copy"));
		return ClipBoardUtil.getClipboardText();
	}

	public static String[] getChildrenOfNode(GuiSubitemTestObject tree,
			String name) {
		ITestDataTreeNode node = getNode(tree, name);
//		ITestDataTreeNode node = findNodeWithName(tree, name);
		String[] children = new String[node.getChildCount()];
		for(int i=0;i<children.length;i++){
			children[i] = node.getChildren()[i].getNode().toString();
		}
		return children;
	}

	private static ITestDataTreeNode findNodeWithName(GuiSubitemTestObject tree, String name) {
		Stack<ITestDataTreeNode[]> stack = new Stack<ITestDataTreeNode[]>();
		ITestDataTree cdTree;
		ITestDataTreeNodes cdTreeNodes;
		ITestDataTreeNode[] cdTreeNode;

		cdTree = (ITestDataTree) tree.getTestData("tree");
		cdTreeNodes = cdTree.getTreeNodes();
		cdTreeNode = cdTreeNodes.getRootNodes();
		stack.push(cdTreeNode);

		while (!stack.isEmpty()) {
			ITestDataTreeNode[] current = (ITestDataTreeNode[]) stack.pop();
			if (current == null) {
				continue;
			}
			for (int i = 0; i < current.length; i++) {
				stack.push(current[i].getChildren());
				if(current[i].getNode().toString().equals(name))
					return current[i];
			}
		}
		return null;
	}
	
//	public static boolean isNodeChecked(GuiSubitemTestObject tree, String node){
//		String parent = node.substring(0, node.lastIndexOf("->"));
//		ITestDataTreeNode n = findNodeWithName(tree, parent);
//		throw new RuntimeException("Need to be implemented");
//	}
	
	public static void setTextCellValue(GuiSubitemTestObject tree, String path, String column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		getScreen().getActiveWindow().inputKeys(SpecialKeys.CLEARALL);
		getScreen().getActiveWindow().inputChars(text);
	}
	
	public static void setTextCellValue(GuiSubitemTestObject tree, String path, int column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		getScreen().getActiveWindow().inputKeys(SpecialKeys.CLEARALL);
		getScreen().getActiveWindow().inputChars(text);
	}
	
	public static void setDateCellValue(GuiSubitemTestObject tree, String path, int column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getButton(tree, "...").click();
		TopLevelTestObject dialog = DOF.getDialog("Date");
		DOF.getTextField(dialog, "Value:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(text);
		DOF.getButton(DOF.getDialog("Date"), "OK").click();
	}
	

	public static void setDateCellValue(GuiSubitemTestObject tree, String path, String column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getButton(tree, "...").click();
		TopLevelTestObject dialog = DOF.getDialog("Date");
		DOF.getTextField(dialog, "Value:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(text);
		DOF.getButton(DOF.getDialog("Date"), "OK").click();
	}
	
	public static void setTimeCellValue(GuiSubitemTestObject tree, String path, String column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getButton(tree, "...").click();
		TopLevelTestObject dialog = DOF.getDialog("Time");
		DOF.getTextField(dialog, "Value:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(text);
		DOF.getButton(DOF.getDialog("Time"), "OK").click();
	}
	public static void setDateTimeCellValue(GuiSubitemTestObject tree, String path, String column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getButton(tree, "...").click();
		TopLevelTestObject dialog = DOF.getDialog("Date Time");
		DOF.getTextField(dialog, "Value:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(text);
		DOF.getButton(DOF.getDialog("Date Time"), "OK").click();
	}
	public static void setCComboCellValue(GuiSubitemTestObject tree, String path, String column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getCCombo(tree).click();
//		DOF.getCCombo(tree).click(atPoint(40,8));
		DOF.getPoppedUpList().click(atText(text));
	}
	
	public static void setCComboCellValue(GuiSubitemTestObject tree, String path, int column, String text){
		tree.click(atCell(atRow(atPath(path)),atColumn(column)));
		DOF.getCCombo(tree).click(atPoint(40,8));
		DOF.getCCombo(tree).click(atPoint(40,8));
		DOF.getPoppedUpList().click(atText(text));
	}
	
	public static void exclusiveCheckNode(GuiSubitemTestObject tree, String node){
		String folder = node.substring(0, node.lastIndexOf("->"));
		String[] entries = TreeHelper.getChildrenOfNode(tree, folder);
		for(String entry:entries){
			String path = folder+"->"+entry;
			if(path.equals(node)){
				checkNode(tree, path);
			}else{
				unchecknode(tree, path);
			}
		}
	}
	
	public static void checkNode(GuiSubitemTestObject tree, String node){
		if(!isNodeChecked(tree, node)){
			tree.click(atPath(node+"->Location(CHECKBOX)"));
		}
	}
	
	public static void unchecknode(GuiSubitemTestObject tree, String node){
		if(isNodeChecked(tree, node)){
			tree.click(atPath(node+"->Location(CHECKBOX)"));
		}
	}
	
	public static boolean isNodeChecked(GuiSubitemTestObject tree, String path){
//		String[] layer = path.split("->");
//		for(String node: layer){
//			TestObject[] elements = (TestObject[])tree.invoke("getItems");
//			for(TestObject element:elements){
//				String nodeText = element.invoke("getText").toString();
//				if(nodeText.equals(node)){
//					tree = element;
//				}
//			}
//		}
//		return new Boolean(tree.invoke("getChecked").toString()).booleanValue();
		tree.click(atPath(path));
		TestObject[] selected = (TestObject[])tree.invoke("getSelection");
//		System.out.println(selected[0].invoke("getText")+"\t"+selected[0].invoke("getChecked"));
		return new Boolean(selected[0].invoke("getChecked").toString()).booleanValue();
	}

	public static List<String> getColumnNames(TestObject tree){
		TestObject[] columns = (TestObject[])tree.invoke("getColumns");
		List<String> names = new ArrayList<String>();
		for(TestObject column:columns){
			names.add(column.invoke("getText").toString());
		}
		return names;
	}

	public static String getDateCellValue(GuiSubitemTestObject tree, String row, String column) {
		tree.click(atCell(atRow(atPath(row)), atColumn(column)), atPoint(5,5));
		sleep(1);
		DOF.getButton(tree, "...").click();
		String date = DOF.getTextField(DOF.getDialog("Date")).getProperty("text").toString();
		DOF.getButton(DOF.getDialog("Date"), "OK").click();
		return date;
	}
	
	public static String getDateCellValue(GuiSubitemTestObject tree, String row, int column) {
		tree.click(atCell(atRow(atPath(row)), atColumn(column)), atPoint(5,5));
		sleep(1);
		DOF.getButton(tree, "...").click();
		String date = DOF.getTextField(DOF.getDialog("Date")).getProperty("text").toString();
		DOF.getButton(DOF.getDialog("Date"), "OK").click();
		return date;
	}

}

