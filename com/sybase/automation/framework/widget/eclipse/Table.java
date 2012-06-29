package com.sybase.automation.framework.widget.eclipse;

import java.awt.Point;
import java.util.ArrayList;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.Index;
import com.rational.test.ft.script.List;
import com.rational.test.ft.script.Location;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.script.SubitemFactory;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestDataTable;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * Widget to encapsulate the behavior the Table Test Object
 * 
 * @author xfu
 * 
 */
public class Table implements ITable {
	private GuiSubitemTestObject _Table;

	private final String CHECK_TYPE = "checked";

	private final String SELECT_TYPE = "selected";

	private final String CONTENTS_TYPE = "contents";

	private ITestDataTable dataTable;

	public Table(GuiSubitemTestObject table, String tabletype) {
		_Table = table;
		_Table.getObjectReference();
		dataTable = (ITestDataTable) _Table.getTestData(CONTENTS_TYPE);
		if (tabletype.equals(CHECK_TYPE)) {
			dataTable = (ITestDataTable) _Table.getTestData(CHECK_TYPE);
		}
		if (tabletype.equals(SELECT_TYPE)) {
			dataTable = (ITestDataTable) _Table.getTestData(SELECT_TYPE);
		}
	}

	public Table(GuiSubitemTestObject table) {
		_Table = table;
		_Table.getObjectReference();
		dataTable = (ITestDataTable) _Table.getTestData(CONTENTS_TYPE);

	}

	/**
	 * Click at the specified text
	 * 
	 * @param text
	 */
	public void click(String text) {
		_Table.click(new Text(text));
	}

	/**
	 * return the value by key , for the table only contains 2 columns , the
	 * first column is regarded as key column, and the second is value column
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	public boolean doesKeyValueExist(String key, String val) {

		for (int row = 0; row < dataTable.getRowCount(); ++row) {
			if (!key.equals(dataTable.getCell(row, 0))) {
				continue;
			} else {
				if (val.equals(dataTable.getCell(row, 1))) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Check whether or not the cell exist
	 * 
	 * @param value
	 * @return true/false
	 */
	public boolean doesCellValueExist(String value) {

		for (int row = 0; row < dataTable.getRowCount(); ++row) {
			for (int col = 0; col < dataTable.getColumnCount(); col++) {
				if (value.equals(getCell(row, col))) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Print the table content
	 */
	public void printTable() {
		for (int row = 0; row < dataTable.getRowCount(); ++row) {
			for (int col = 0; col < dataTable.getColumnCount(); ++col) {
				System.out.print("<>" + dataTable.getCell(row, col) + " ");
			}

		}
	}

	/**
	 * 
	 * @return the row count of the table
	 */
	public int getRowCount() {

		return dataTable.getRowCount();
	}

	/**
	 * 
	 * @return the column count of the table
	 */
	public int getColumnCount() {
		return dataTable.getColumnCount();
	}

	/**
	 * get cell value by row/column index(only string is supported)
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public String getCell(int rowIndex, int colIndex) {
		checkRowArgument(rowIndex);
		checkColArgument(colIndex);
		if (dataTable.getCell(rowIndex, colIndex) == null) {
			System.out.println("there no content in specified cell");
			return null;
		}
		return dataTable.getCell(rowIndex, colIndex).toString();
	}

	/**
	 * get cell value by row index,column name(only string is supported)
	 */
	public String getCell(int rowIndex, String colName) {
		int colIndex = getColumnIndexByName(colName);
		return getCell(rowIndex, colIndex);
	}

	/**
	 * get cell value by column index,row name(only string is supported)
	 */
	public String getCell(String rowName, int colIndex) {
		int rowIndex = getRowIndexByName(rowName);
		return getCell(rowIndex, colIndex);
	}

	/**
	 * get cell value by column name,row name(only string is supported)
	 */
	public String getCell(String rowName, String colName) {
		int rowIndex = getRowIndexByName(rowName);
		int colIndex = getColumnIndexByName(colName);
		return getCell(rowIndex, colIndex);
	}

	/**
	 * set cell value by row index,column index ,start with 0
	 */
	public void setCell(int rowIndex, int colIndex, String input) {
		// update Rain, change clickCell to doubleClickCell
		setCell(rowIndex, colIndex, input, true);
	}

	public void setCell(int rowIndex, int colIndex, String input,
			boolean doubleClick) {
		if (doubleClick) {
			doubleClickCell(rowIndex, colIndex);
		} else {
			clickCell(rowIndex, colIndex);
		}
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input);
		_Table.click();
	}

	public void setCell(int rowIndex, String colName, Location input) {

		checkRowArgument(rowIndex);
		int colIndex = getColumnIndexByName(colName);
		Cell cell = new Cell(new Row(new Index(rowIndex)), new Column(
				new Index(colIndex)));
		_Table.click(new List(cell, input));

	}

	public void setCell(String rowName, int colIndex, String input) {
		int rowIndex = getRowIndexByName(rowName);
		setCell(rowIndex, colIndex, input);

	}

	public void setCell(int rowIndex, String colName, String input) {
		int colIndex = getColumnIndexByName(colName);
		setCell(rowIndex, colIndex, input);

	}

	public void setCell(String rowName, String colName, String input) {
		int rowIndex = getRowIndexByName(rowName);
		int colIndex = getColumnIndexByName(colName);
		setCell(rowIndex, colIndex, input);

	}

	/**
	 * Get column contents as a ArrayList
	 * 
	 * @param colIndex
	 * @return
	 */
	public ArrayList getColumn(int colIndex) {
		ArrayList columnData = new ArrayList();
		checkColArgument(colIndex);
		if (getRowCount() == 0)
			return columnData;
		for (int i = 0; i < getRowCount(); i++) {
			columnData.add(getCell(i, colIndex));
		}
		return columnData;

	}

	/**
	 * Get row contents as a ArrayList
	 * 
	 * @param rowIndex
	 * @return
	 */
	public ArrayList getRow(int rowIndex) {
		ArrayList rowData = new ArrayList();
		checkRowArgument(rowIndex);
		if (getRowCount() == 0)
			return rowData;
		for (int i = 0; i < getColumnCount(); i++) {
			rowData.add(getCell(rowIndex, i));
		}
		return rowData;

	}

	/**
	 * Get the column name by index (start from 0)
	 * 
	 * @param index
	 * @return
	 */
	public String getColumnHeader(int index) {
		checkColArgument(index);
		return (String) dataTable.getColumnHeader(index);
	}

	/**
	 * Get the row name by index (start from 0)
	 * 
	 * @param index
	 * @return
	 */
	public String getRowHeader(int index) {
		checkRowArgument(index);
		return (String) dataTable.getRowHeader(index);
	}

	private void checkRowArgument(int row) {
		if (row >= getRowCount())
			throw new IllegalArgumentException(
					"invalid row number, your input is " + row
							+ ",which should less than " + " rowcount: "
							+ getRowCount());

	}

	private void checkColArgument(int col) {
		if (col >= getColumnCount())
			throw new IllegalArgumentException(
					"invalid column number, your input is " + col
							+ ",which should less than " + " column count: "
							+ getColumnCount());
	}

	private int getColumnIndexByName(String colName) {
		int colIndex = -1;
		for (int i = 0; i < getColumnCount(); i++) {
			if (colName.trim().equalsIgnoreCase(getColumnHeader(i).trim())) {
				colIndex = i;
				break;
			}
		}
		return colIndex;
	}

	/**
	 * Method getRowIndexByName.
	 * 
	 * @param rowName
	 * @return int
	 */
	private int getRowIndexByName(String rowName) {
		int rowIndex = -1;
		for (int i = 0; i < getRowCount(); i++) {
			if (rowName.trim().equalsIgnoreCase(getRowHeader(i).trim())) {
				rowIndex = i;
				break;
			}
		}
		return rowIndex;
	}

	private int getRowIndexByName(String rowValue, int col) {
		int rowIndex = -1;
		for (int i = 0; i < getRowCount(); i++) {
			if (rowValue.trim().equalsIgnoreCase(getCell(i, col))) {
				rowIndex = i;
				break;
			}
		}
		return rowIndex;
	}

	// [2007/6/4 Rain Chen]
	// Change clickCell to doubleClickCell, because inside the method it calls
	// _Table.doubleClick(cell)
	// please change your code to doubleClickCell(xx,xx) if you need double
	// click a cell.

	public void doubleClickCell(int row, int col) {
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(col)));
		_Table.doubleClick(cell);
	}

	// [2007/6/4 Rain Chen]
	// added clickCell which execute the click cell action
	public void clickCell(int row, int col) {
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(col)));
		_Table.click(cell);
	}

	// [2007/6/4 Rain Chen]
	// Change clickCell to doubleClickCell, because inside the method it calls
	// _Table.doubleClick(cell)
	// please change your code to doubleClickCell(xx,xx) if you need double
	// click a cell.
	public void doubleClickCell(int row, String colName) {
		int col = getColumnIndexByName(colName);
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(col)));
		_Table.doubleClick(cell);
	}

	// [2007/6/4 Rain Chen]
	// added clickCell which execute the click cell action
	public void clickCell(int row, String colName) {
		int col = getColumnIndexByName(colName);
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(col)));
		_Table.click(cell);
	}

	public void clickCell(String rowValue, int col) {
		int rowIndex = getRowIndexByName(rowValue, col);
		System.out.println(rowIndex);
		checkColArgument(col);
		Cell cell = new Cell(new Row(new Index(rowIndex)), new Column(
				new Index(col)));

		java.awt.Point p = new Point();
		p.x = 16;
		p.y = 3;
		_Table.click(cell, p);

	}

	// [2007/6/4 Rain Chen]
	// added doubleClickCell which execute the double click cell action
	public void doubleClickCell(String rowValue, int col) {
		int rowIndex = getRowIndexByName(rowValue, col);
		System.out.println(rowIndex);
		checkColArgument(col);
		Cell cell = new Cell(new Row(new Index(rowIndex)), new Column(
				new Index(col)));

		java.awt.Point p = new Point();
		p.x = 16;
		p.y = 3;
		_Table.doubleClick(cell, p);

	}

	// [2007/6/15 Lucy Bi]
	// added clickColumnHeader
	public void clickColumnHeader(String HeaderName) {
		_Table
				.click(SubitemFactory.atHeader(SubitemFactory
						.atText(HeaderName)));
	}

	public int locateRow(String val, int col) {
		ArrayList nameList = getColumn(col);
		int i = 0;
		for (; i < nameList.size(); i++) {
			String curName = (String) nameList.get(i);
			if (curName.equals(val)) {
				break;
			}
		}
		if (i == nameList.size()) {
			i = -1;
		}
		return i;
	}

	public boolean doesMapExist(String sourceItem, String targetItem,
			int sourceCol, int targetCol) {
		int rowCount = getRowCount();
		boolean exist = false;
		for (int i = 0; i < rowCount; i++) {
			String curSourceItem = getCell(i, sourceCol);
			String curTargetItem = getCell(i, targetCol);
			if (curSourceItem.equals(sourceItem)
					&& curTargetItem.equals(targetItem)) {
				exist = true;
				break;
			}
		}
		return exist;
	}
}