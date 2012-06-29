package com.sybase.automation.framework.widget.eclipse;

/**
 * Widget to encapsulate the behavior the Tree Test Object
 * 
 * @author xfu
 * 
 * Tree testTree = new Tree(tree2Tree());
 * 
 * search specific nodes use part of name String[] names =
 * testTree.searchNodesbyName("Data"); if (names == null) System.out.println("no
 * results"); else for (int i = 0; i < names.length; i++) {
 * System.out.println(names[i]); }
 *  
 */
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

import com.rational.test.ft.object.TestObjectReference;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.MouseModifiers;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.rational.test.ft.vp.ITestDataTree;
import com.rational.test.ft.vp.ITestDataTreeNode;
import com.rational.test.ft.vp.ITestDataTreeNodes;
import com.rational.test.ft.vp.impl.TestDataElement;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.interfaces.*;

public class Tree extends SubitemFactory implements ITree {
	private GuiSubitemTestObject _Tree;

	private ITestDataTree _TreeData;

	private ITestDataTreeNodes _TreeNodes;

	private ITestDataTreeNode[] _TreeRootNode;

	public int currentDegree = 1;

	// public Tree(TestObject tree)
	public Tree(GuiSubitemTestObject tree) {
		_Tree = tree;
		_Tree.getObjectReference();
		_TreeData = (ITestDataTree) _Tree.getTestData("tree");
		_TreeNodes = _TreeData.getTreeNodes();
		_TreeRootNode = _TreeNodes.getRootNodes();
	}
	
	

	/**
	 * Return all pathes containing the input parameter
	 * 
	 * @param nodeName
	 * @return
	 */
	public String[] searchNodesbyName(String nodeName) {
		ArrayList nameList = new ArrayList();
		for (int i = 0; i < _TreeRootNode.length; ++i) {
			seachNode(nameList, "", _TreeRootNode[i], nodeName);
		}
		return (String[]) nameList.toArray(new String[0]);
	}

	/**
	 * Return all pathes containing the input parameter
	 * 
	 * @param nodeName
	 * @param degree
	 * @return
	 */
	public String[] searchNodesbyName(String nodeName, int degree) {
		ArrayList nameList = new ArrayList();
		for (int i = 0; i < _TreeRootNode.length; ++i) {
			seachNode(nameList, "", _TreeRootNode[i], nodeName, degree);
		}
		return (String[]) nameList.toArray(new String[0]);
	}

	/**
	 * 
	 * @param nodeName
	 * @return The first path containing the input parameter
	 */

	public String searchSingleNodebyName(String nodeName) {
		if (searchNodesbyName(nodeName).length == 0)
			return null;
		return searchNodesbyName(nodeName)[0];
	}

	/**
	 * 
	 * @param nodeName
	 * @return The first path containing the input parameter
	 */

	public void doubleClick(String path) {
		_Tree.doubleClick(atPath(path));
	}

	/**
	 * 
	 * @param nodeName
	 * @param degree
	 * @return
	 */
	public String searchSingleNodebyName(String nodeName, int degree) {
		if (searchNodesbyName(nodeName, degree).length == 0)
			return null;
		return searchNodesbyName(nodeName, degree)[0];
	}

	/**
	 * @@param sNode : path representing the full path
	 * 
	 */
	public TestObject getNode(String sNode) {
		return (TestObject) _Tree.getSubitem(new List(sNode));
	}

	/**
	 * 
	 * @@param sNode : path representing the full path check the node is checked
	 *         or not
	 * 
	 */
	public boolean isChecked(String sNode) {
		TestObject node = getNode(sNode);
		return new Boolean((node.getProperty("checked").toString())).booleanValue();
	}

	/**
	 * @@param sNode : path representing the partial path
	 */
	public boolean isNodeChecked(String path) {
		String nodes[] = this.searchNodesbyName(path);
		if (nodes.length != 1)
			throw new IllegalArgumentException(
					"No node or more than one nodes founded!");
		return isChecked(nodes[0]);
	}

	/**
	 * check if the tree node exist whether the node is checked
	 * 
	 * @param path
	 * @param depth
	 * @return
	 */
	public boolean isNodeChecked(String path, int depth) {
		String nodes[] = this.searchNodesbyName(path, depth);
		if (nodes.length != 1)
			throw new IllegalArgumentException(
					"No node or more than one nodes founded!");
		return isChecked(nodes[0]);
	}

	/**
	 * check if the tree node exist
	 * <p>
	 * 
	 * @param sNode:
	 *            the full path of the node ,for
	 *            exsample:"GuessNumber->webroot->register.jsp"
	 * 
	 * @return true/false
	 */
	public boolean doesNodeExist(String path) {
		TestObject node = null;
		try {
			node = getNode(path);
		} catch (Throwable t) {
		}
		if (node == null) {
			return false;
		} else {
			node.unregister();
			return true;
		}
	}

	public void printNode(String parentTxt, ITestDataTreeNode node) {
		String nodeTxt;
		if (parentTxt.equals("")) {
			nodeTxt = node.getNode().toString();
		} else {
			nodeTxt = parentTxt + "->" + node.getNode().toString();
		}
		if (node.getChildCount() == 0) {
			System.out.println(nodeTxt);
		}
		ITestDataTreeNode[] children = node.getChildren();
		int childCount = (children != null ? children.length : 0);
		for (int i = 0; i < childCount; ++i)
			printNode(nodeTxt, children[i]);
	}

	private void seachNode(ArrayList list, String parentTxt,
			ITestDataTreeNode node, String nodeName) {
		String nodeTxt;
		if (parentTxt.equals("")) {
			nodeTxt = node.getNode().toString();
		} else {
			if (!node.getNode().toString().equals(""))
				nodeTxt = parentTxt + "->" + node.getNode().toString();
			else
				nodeTxt = parentTxt;
		}
		if (node.getChildCount() == 0 && nodeTxt.indexOf(nodeName) != -1) {
			list.add(nodeTxt);
		}
		ITestDataTreeNode[] children = node.getChildren();
		int childCount = (children != null ? children.length : 0);
		for (int i = 0; i < childCount; ++i)
			seachNode(list, nodeTxt, children[i], nodeName);
	}

	private void seachNode(ArrayList list, String parentTxt,
			ITestDataTreeNode node, String nodeName, int degree) {
		String nodeTxt;
		if (parentTxt.equals("")) {
			nodeTxt = node.getNode().toString();
		} else {
			if (!node.getNode().toString().equals(""))
				nodeTxt = parentTxt + "->" + node.getNode().toString();
			else
				nodeTxt = parentTxt;
		}
		if (getDegree(nodeTxt) == degree && nodeTxt.indexOf(nodeName) != -1) {
			list.add(nodeTxt);
		}
		ITestDataTreeNode[] children = node.getChildren();
		int childCount = (children != null ? children.length : 0);
		for (int i = 0; i < childCount; ++i)
			seachNode(list, nodeTxt, children[i], nodeName, degree);
	}

	// /**
	// *
	// */
	// public void showTreePath() {
	// for (int i = 0; i < _TreeRootNode.length; ++i)
	// printNode("", _TreeRootNode[i]);
	// }

	// /**
	// * Recursive method to print out tree nodes with proper indenting.
	// *
	// * @param node
	// * @param indent
	// */
	// public void showTree(ITestDataTreeNode node, int indent)
	// {
	//
	// String tabs = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	// //Determine number of tabs to use - to properly indent tree
	// int tabCount = (indent < tabs.length() ? indent : tabs.length());
	// //Print out node name + number of children
	// System.out.println(tabs.substring(0, tabCount) + node.getNode() + " (" +
	// node.getChildCount()
	// + " children)");
	// //Determine if node has children; recursively call this same //method to
	// print out child nodes.
	// ITestDataTreeNode[] children = node.getChildren();
	// int childCount = (children != null ? children.length : 0);
	// for (int i = 0; i < childCount; ++i)
	// showTree(children[i], indent + 1);
	// }

	private int getDegree(String path) {
		return path.split("->").length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.ITree#printTree()
	 */
	public void printTree() {
		throw new UnsupportedMethodException("Not supported function!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.ITree#clickAtPath()
	 */
	public void clickAtPath(String path) {
		_Tree.click(atPath(path));
	}

	public void clickAtPathForTable(String node) {
		ArrayList all = new ArrayList();
		for (int i = 0; i < _TreeRootNode.length; ++i)
			listNodes("", _TreeRootNode[i], all);
		int index = all.indexOf(node) + 1;
		if (index == all.size()) {
			clickAtPath((String) all.get(index - 1));
			RationalTestScript.getScreen().getActiveWindow().inputKeys(
					"{ExtDown}");
		} else {
			clickAtPath((String) all.get(index));
		}
	}

	private void listNodes(String parentTxt, ITestDataTreeNode node,
			ArrayList all) {
		String nodeTxt;
		if (parentTxt.equals("")) {
			nodeTxt = node.getNode().toString();
		} else {
			nodeTxt = parentTxt + "->" + node.getNode().toString();
		}
		all.add(nodeTxt);
		/*
		 * do recursively
		 */
		ITestDataTreeNode[] children = node.getChildren();
		int childCount = (children != null ? children.length : 0);
		for (int i = 0; i < childCount; ++i)
			listNodes(nodeTxt, children[i], all);
	}

	/**
	 * check if the tree node exist
	 * <p>
	 * 
	 * @param sNode:
	 *            the full path of the node ,for
	 *            exsample:"GuessNumber->webroot->register.jsp"
	 * 
	 * @return true/false
	 */
	public boolean doesNodeExist_new(String path) {
		_Tree.getSubitem(new List(path));
		ArrayList all = new ArrayList();
		for (int i = 0; i < _TreeRootNode.length; ++i)
			listNodes("", _TreeRootNode[i], all);
		return all.indexOf(path) != -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ITree#rightClickAtPath(java.lang.String)
	 */
	public void rightClickAtPath(String path) {
		_Tree.click(RationalTestScript.RIGHT, atPath(path));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ITree#rightClickAtPoint(java.lang.String)
	 */
	public void rightClickAtPoint(int x, int y) {
		_Tree.click(RationalTestScript.RIGHT, atPoint(x, y));
	}

	public void checkPath(String path, boolean toggle) {
		_Tree.click(atPath(path));
		TestObject o = (TestObject) _Tree.getSubitem(new List(path));
		if (((Boolean) o.invoke("getChecked")).booleanValue() != toggle) {
			_Tree.click(atPath(path + "->Location(CHECKBOX)"));
		}
	}

	public void expandPath(String path, boolean toggle) {
		_Tree.click(atPath(path));
		TestObject o = (TestObject) _Tree.getSubitem(new List(path));
		if (((Boolean) o.invoke("getExpanded")).booleanValue() != toggle) {
			_Tree.click(atPath(path + "->Location(PLUS_MINUS)"));
		}
	}
	
	public String findFirstLeafNodePath(String nodeRegex) {
		Pattern p = Pattern.compile(nodeRegex);
		String nodePath = doFindLeafNodePath(_TreeRootNode, p);
		return nodePath;
	}
	
	private String doFindLeafNodePath(ITestDataTreeNode[] nodes, Pattern p) {
		for(int i = 0; i < nodes.length; i++) {
			ITestDataTreeNode node = nodes[i];
			String nodeText = node.getNode().toString();
			if(node.getChildCount() == 0) {
				if(p.matcher(nodeText).find()) {
					return nodeText;
				}
			} else {
				ITestDataTreeNode[] subNodes = node.getChildren();
				String subPath = doFindLeafNodePath(subNodes, p);
				if(subPath != null) {
					return nodeText + "->" + subPath;
				}
			}
		}
		return null;
	}
	
	//Add by Yong (York) Li
	public void drag(String source_path, String target_path){
		_Tree.drag(atPath(source_path), atPath(target_path));
	}
}