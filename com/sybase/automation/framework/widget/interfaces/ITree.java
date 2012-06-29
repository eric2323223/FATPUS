/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.vp.ITestDataTreeNode;

/**
 * @author xfu
 */
public interface ITree {
	String[] searchNodesbyName(String nodeName);

	String[] searchNodesbyName(String nodeName, int degree);

	String searchSingleNodebyName(String nodeName);

	String searchSingleNodebyName(String nodeName, int degree);

	TestObject getNode(String sNode);

	boolean isChecked(String sNode);

	boolean isNodeChecked(String path);

	boolean isNodeChecked(String path, int depth);

	boolean doesNodeExist(String path);

	boolean doesNodeExist_new(String path); // YongLiu, Nov 12,2007

	void printTree();

	void clickAtPath(String path);

	void rightClickAtPath(String path);

	void rightClickAtPoint(int x, int y);

	void clickAtPathForTable(String path);

	void doubleClick(String path);

	public void checkPath(String path, boolean toggle);

	public void expandPath(String path, boolean toggle);
	
	public void drag(String source_path, String target_path);

	/**
	 * Find first leaf node match specified regular expression
	 * 
	 * @param nodeRegex
	 * @return the node's path. if not found, return null.
	 * 
	 * @author dongxu
	 * @since 2008/07/23
	 */
	public String findFirstLeafNodePath(String nodeRegex);
}
