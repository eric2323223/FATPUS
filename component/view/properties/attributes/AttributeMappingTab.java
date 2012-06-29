package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.MainMenu;
import component.entity.model.MBOAttribute;

public class AttributeMappingTab extends RationalTestScript {
	
	private static GuiSubitemTestObject table() {
		GuiSubitemTestObject table = DOF.getTableByColumnsNames(DOF.getDualHeadersTable(DOF.getRoot()), 
        		new String[]{"","Name","Datatype","Nullable","Primary Key","Map to","Datatype","Nullable"});
		return table;
	}

	public static void rename(String from, String to) {
		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Name", from);
		table().click(atCell(atRow(row), atColumn("Name")));
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(to);
	}

	public static void setAttribute(MBOAttribute attribute) {
		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Name", attribute.getName());
		setType(row, attribute.getType());
		setMapTo(row, attribute.getMapTo());
	}
	
	private static void setMapTo(int row, String mapTo) {
		if(mapTo!=null){
			TableHelper.setTextCellValue(table(), row, "Map to", mapTo);
		}
	}

	private static void setType(int row, String type) {
		if(type!=null){
			TableHelper.setTextCellValue(table(), row, 2, type);
			TableHelper.setTextCellValue(table(), row, 6, type);
		}
		
	}

	public static void deleteAttribute(String attribute){
		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Name", attribute);
		table().click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
	}

}
