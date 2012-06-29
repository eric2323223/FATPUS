package com.sybase.automation.framework.widget.helper;
import java.util.Enumeration;

import resources.com.sybase.automation.framework.widget.helper.TableHelperHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.util.NativeInvoker;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class TableHelper extends TableHelperHelper
{
	/**
	 * Script Name   : <b>TableHelper</b>
	 * Generated     : <b>Nov 18, 2008 2:01:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/11/18
	 * @author Administrator
	 */

    /**
     * Script Name : <b>TableHelper </b> Generated : <b>Oct 18, 2007 6:06:14 PM
     * </b> Description : Functional Test Script Original Host : WinNT Version
     * 5.1 Build 2600 (S)
     * 
     * @since 2007/10/18
     * @author pyin
     */
    public void testMain(Object[] args) {
        GuiSubitemTestObject table = DOF.getTableByColumnsNames(DOF.getDualHeadersTable(DOF.getRoot()), 
        		new String[]{"","Name","Datatype","Nullable","Primary Key","Map to","Datatype","Nullable"});
//        System.out.println(getRowIndexOfRecordInColumn(table,"Connection Profile","sdf"));
    	System.out.println(getRowIndexOfRecordInColumn(table, "Name", "zip"));
    }

    public static String[] getColumns(GuiSubitemTestObject table) {
        int length = new Integer(table.invoke("getColumnCount").toString()).intValue();
        String[] columns = new String[length];
        for (int i = 0; i < length; i++) {
            TestObject[] cols = (TestObject[]) table.invoke("getColumns");
            columns[i] = cols[i].invoke("getText").toString();
        }
        return columns;
    }

    public static String getItemTextAt(GuiSubitemTestObject table, int index) {
        TestObject tableItem = (TestObject) NativeInvoker.invoke(table, "getItem", index);
        return tableItem.invoke("getText").toString();
    }

    public static int getItemCount(GuiSubitemTestObject table) {
        Integer count = (Integer) table.invoke("getItemCount");
        return count.intValue();
    }

    public static int getColumnCount(GuiSubitemTestObject table) {
        Integer count = (Integer) table.invoke("getColumnCount");
        return count.intValue();
    }

    public static String[] getColumnItems(GuiSubitemTestObject table, int index) {
        int itemCount = new Integer(table.invoke("getItemCount").toString()).intValue();
        int columnCount = new Integer(table.invoke("getColumnCount").toString()).intValue();
        int rowCount = itemCount / columnCount;
        String[] result = new String[rowCount];
        for (int i = 0; i < rowCount; i++) {
            result[i] = NativeInvoker.invoke(table, "getItem", rowCount * i + index).toString();
        }
        return result;
    }

    public static void printColumns(GuiSubitemTestObject table) {
        TestObject[] columns = (TestObject[]) table.invoke("getColumns");
        for (int i = 0; i < columns.length; i++) {
            System.out.print(columns[i].invoke("getText").toString()+"\t");
        }
    }

    public static int getRowCount(GuiSubitemTestObject table) {
        int rows = new Integer(table.invoke("getItemCount").toString()).intValue();
        return rows;
    }
    
    public static int getHeaderHeight(GuiSubitemTestObject table){
        int height = new Integer(table.invoke("getHeaderHeight").toString()).intValue();
        return height;
    }
    
    public static int getItemHeight(GuiSubitemTestObject table){
        int height = new Integer(table.invoke("getItemHeight").toString()).intValue();
        return height;
    }

    public static String getCellValue(GuiSubitemTestObject table, int row, int column) {
        TestObject[] items = (TestObject[]) table.invoke("getItems");
        Object[] args = new Object[1];
        args[0] = new Integer(column);
        if (items != null) {
			Object data = items[row].invoke("getText", "(I)Ljava.lang.String;",
					args);
			if (data != null) {
				return data.toString();
			} else {
				return "";
			}}
        else{
        	return "";
        }
    }
    
    public static String getCellValue(GuiSubitemTestObject table, int row, String columnName) {
        int columnIndex = getColumnIndex(table, columnName);
        if (columnIndex == -1) {
            return null;
        } else {
            return getCellValue(table,row,columnIndex);
        }
    }

    /**
     * @param table
     * @param columnName
     * @return
     */
    private static int getColumnIndex(GuiSubitemTestObject table, String columnName) {
        int columnIndex = -1;
        TestObject[] items = (TestObject[]) table.invoke("getColumns");
        for (int i = 0; i < items.length; i++) {
            String currentName = ((TestObject)NativeInvoker.invoke(table, "getColumn", i)).invoke("getText").toString();
            if (currentName.equals(columnName)) {
                columnIndex = i;
            }
        }
        return columnIndex;
    }

    public static void setCellValue(GuiSubitemTestObject table, String value, int row, int column) {
        TestObject[] items = (TestObject[]) table.invoke("getItems");
        Object[] args = new Object[2];
        args[0] = new Integer(column);
        args[1] = value;
        items[row].invoke("setText", "(ILjava.lang.String;)V", args);
    }
    
    public static void setTextCellValue(GuiSubitemTestObject table, int row, int column, String text){
		table.click(atCell(atRow(row), atColumn(column)));
    	getScreen().getActiveWindow().inputKeys(SpecialKeys.CLEARALL);
    	getScreen().getActiveWindow().inputChars(text);
    }
    
    public static void setTextCellValue(GuiSubitemTestObject table, int row, String column, String text){
    	table.click(atCell(atRow(row), atColumn(column)));
    	getScreen().getActiveWindow().inputKeys(SpecialKeys.CLEARALL);
    	getScreen().getActiveWindow().inputChars(text);
    }
    
    public static void getCellValue(GuiSubitemTestObject table, String value, int row, int column){
        TestObject[] items = (TestObject[]) table.invoke("getItems");
        Object[] args = new Object[2];
        args[0] = new Integer(column);
        args[1] = value;
        items[row].invoke("getText", "(ILjava.lang.String;)V", args);
        
    }

    public static void setCellValue(GuiSubitemTestObject table, String value, int row, String columnName) {
        int columnIndex = getColumnIndex(table,columnName);
        if (columnIndex == -1) {
            
        } else {
            setCellValue(table,value,row,columnIndex);
        }
    }
    
    public static void getCellValue(GuiSubitemTestObject table, String value, int row, String columnName) {
        int columnIndex = getColumnIndex(table,columnName);
        if (columnIndex == -1) {
            
        } else {
            getCellValue(table,value,row,columnIndex);
        }
    }

    public static int getRowHeight(GuiSubitemTestObject table) {
        String rowHeight = table.invoke("getItemHeight").toString();
        return new Integer(rowHeight).intValue();
    }

    public static int getColumnWidth(GuiSubitemTestObject table, int colIndex) {
        TestObject col = (TestObject) NativeInvoker.invoke(table, "getColumn", colIndex);
        String width = col.invoke("getWidth").toString();
        return new Integer(width).intValue();
    }

    public static void clickAtCell(GuiSubitemTestObject table, int row, int column) {
    	table.click(atCell(atRow(row), atColumn(column)));
    }
    
    public static void clickAtCell(GuiSubitemTestObject table, int row, String columnName) {
        table.click(atCell(atRow(row), atColumn(columnName)));
    }

    public static void rightClickAtCell(GuiSubitemTestObject table, int col, int row) {
        table.click(RIGHT, atCell(atRow(row), atColumn(col)));
    }
    
    public static String getColumnName(GuiSubitemTestObject table, int index){
//        TestObject column = (TestObject)NativeInvoker.invoke(table,"getColumn",index);
//        return column.invoke("getText").toString();
        Object[] args = new Object[1];
        args[0] = new Integer(index);
        TestObject column = (TestObject)table.invoke("getColumn","(I)Lorg.eclipse.swt.widgets.TableColumn;",args);
        return column.invoke("getText").toString();
    }
    
    public static int getItemIndex(GuiSubitemTestObject table, String text){
        int itemCount = new Integer(table.invoke("getItemCount").toString()).intValue();
        for(int i=0;i<itemCount;i++){
            TestObject item = (TestObject)NativeInvoker.invoke(table,"getItem",i);
            if(item.invoke("getText").toString().equals(text))
                return i;
        }
        return -1;
    }
    
    public static int getRowIndexOfRecordInColumn(GuiSubitemTestObject table, String columnName,String cellData){
//        int columnIndex = -1;
//        int columnCount = new Integer(table.invoke("getColumnCount").toString()).intValue();
//        for(int i=0;i<columnCount;i++){
//            String colName = ((TestObject)NativeInvoker.invoke(table,"getColumn",i)).invoke("getText").toString();
//            if(colName.equals(columnName))
//                columnIndex = i;
//        }
    	int columnIndex = getColumnIndex(table, columnName);
        int itemCount = new Integer(table.invoke("getItemCount").toString()).intValue();
        for(int i=0;i<itemCount;i++){
        	TestObject item = (TestObject)NativeInvoker.invoke(table,"getItem",i);
        	String data = NativeInvoker.invokeMultipleMethodsWithSameName(item,"getText",columnIndex).toString();
            if(data.equals(cellData))
                return i;
            
        }
        return -1;
    }

	public static void setCComboCellValue(GuiSubitemTestObject table, int row,int column, String data) {
		table.click(atCell(atRow(row), atColumn(column)));
//		sleep(1);
		DOF.getCCombo(table).click();
		sleep(1);
		DOF.getPoppedUpList().click(atText(data));
	}
	
	public static void setCComboCellValue(GuiSubitemTestObject table, int row,String column, String data) {
		table.click(atCell(atRow(row), atColumn(column)));
//		sleep(1);
		DOF.getCCombo(table).click();
		sleep(1);
		DOF.getPoppedUpList().click(atText(data));
	}

	public static void setCheckboxCellValue(GuiSubitemTestObject table,	int row, int i, boolean nullable) {
		throw new RuntimeException("Not yet Implemented");
	}
	
	public static boolean hasDataInColumn(GuiSubitemTestObject table, String column, String data){
		if(getRowIndexOfRecordInColumn(table,column,data)==-1){
			return false;
		}else{
			return true;
		}
	}

	public static String[] getColumnData(GuiSubitemTestObject table,
			String column) {
		ITestDataTable tableData = (ITestDataTable)table.getTestData("contents");
		String[] data = new String[tableData.getRowCount()];
		for(int row = 0;row<tableData.getRowCount();row++){
			for(int col = 0;col<tableData.getColumnCount();col++){
				if(tableData.getColumnHeader(col).toString().equals(column)){
//					System.out.println(tableData.getCell(row, col));
					data[row] = tableData.getCell(row, col).toString();
				}
			}
		}
		return data;
	}
	
	public static String[] getColumnData(GuiSubitemTestObject table,int column) {
		ITestDataTable tableData = (ITestDataTable)table.getTestData("contents");
		String[] data = new String[tableData.getRowCount()];
		for (int row = 0; row < tableData.getRowCount(); row++) {
			data[row] = tableData.getCell(row, column).toString();
		}
		return data;
	}

	public static void clickAtCell(GuiSubitemTestObject table,
			String data, String columnName) {
		int row = getRowIndexOfRecordInColumn(table, columnName, data);
		clickAtCell(table, row, columnName);
	}
	
	public static void printTableData(TestObject table) {
		Enumeration<String> testDataTypes = table.getTestDataTypes().keys();
		while (testDataTypes.hasMoreElements()) {
			String testDataType = testDataTypes.nextElement();
			System.out.println(testDataType);
			ITestData iData = table.getTestData(testDataType);
			if (iData instanceof ITestDataTable) {
				ITestDataTable iTableData = (ITestDataTable) table
						.getTestData(testDataType);
				int rows = iTableData.getRowCount();
				int cols = iTableData.getColumnCount();
				for (int col = 0; col < cols; col++) {
					System.out.print(iTableData.getColumnHeader(col));
					System.out.print("\t\t");
				}
				System.out.print("\n");
				for (int row = 0; row < rows; row++) {
					for (int col = 0; col < cols; col++) {
						System.out.print(iTableData.getCell(row, col));
						System.out.print("\t\t");
					}
					System.out.print("\n\n");
				}
				System.out.print("\n");
			} else if (iData instanceof ITestDataText) {
				ITestDataText iText = (ITestDataText) iData;
				String text = iText.getText();
				System.out.println(text + "\n\n");
			}
		}
	}
	
//ffan add>>>>20120312 :click "shift"
	public static void clickAtCell(MouseModifiers shiftLeft,
			GuiSubitemTestObject table, int i, int j) {
		// TODO Auto-generated method stub
	     table.click(SHIFT_LEFT, atCell(atRow(i), atColumn(j)));
	}
}

