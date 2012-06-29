package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

public class AttributesMappingPage extends RationalTestScript{

	public static void setAttributeName(TopLevelTestObject dialog,
			String original, String newName) {
		GuiSubitemTestObject table = DOF.getTable(DOF.getDualHeadersTable(dialog));
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", original);
		
		
		table.click(atCell(atRow(atIndex(20)), atColumn("Name")));
		dialog.inputChars(original);
		TableHelper.setTextCellValue(table, row, "Name", newName);
		table.click(atCell(atRow(row-1), atColumn("Name")));
		
	}

}
