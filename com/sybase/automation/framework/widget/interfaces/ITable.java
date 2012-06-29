/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.script.Location;
import com.rational.test.ft.script.Text;

/**
 * @author xfu
 */
public interface ITable {
	boolean doesKeyValueExist(String key, String val);

	boolean doesCellValueExist(String value);

	void printTable();

	int getRowCount();

	int getColumnCount();

	String getCell(int rowIndex, int colIndex);

	String getCell(int rowIndex, String colName);

	String getCell(String rowName, int colIndex);

	String getCell(String rowName, String colName);

	void setCell(int rowIndex, int colIndex, String input);

	void setCell(int rowIndex, int colIndex, String input, boolean doubleClick);

	void setCell(int rowIndex, String colName, Location input);

	void setCell(String rowName, int colIndex, String input);

	void setCell(int rowIndex, String colName, String input);

	void setCell(String rowName, String colName, String input);

	ArrayList getColumn(int colIndex);

	ArrayList getRow(int rowIndex);

	String getColumnHeader(int index);

	String getRowHeader(int index);

	void clickCell(String rowValue, int col);

	void clickCell(int row, int col);

	// added by kliu
	void clickCell(int row, String colName);

	// added by Rain
	void doubleClickCell(String rowValue, int col);

	void doubleClickCell(int row, int col);

	void doubleClickCell(int row, String colName);

	void click(String text);

	/**
	 * Locate a row by specified cell value and column index
	 * 
	 * @param val
	 * @param t
	 * @param col
	 * @return
	 */
	public int locateRow(String val, int col);

	/**
	 * Judge whether a pair of item exist in table, to implement the mapping
	 * table verify.
	 * 
	 * @param sourceItem
	 * @param targetItem
	 * @param sourceCol
	 * @param targetCol
	 * @param table
	 * @return
	 */
	public boolean doesMapExist(String sourceItem, String targetItem,
			int sourceCol, int targetCol);
}
