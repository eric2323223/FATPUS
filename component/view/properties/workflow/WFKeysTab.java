package component.view.properties.workflow;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.dialog.WFKeyDialog;
import component.entity.model.WFKey;

public class WFKeysTab extends RationalTestScript{
	
	private static GuiSubitemTestObject table(){
		return DOF.getTable(DOF.getRoot());
	}

	public static void createKey(WFKey key) {
		DOF.getButton(DOF.getRoot(), "&New...").click();
		WFKeyDialog.key(key);
	}

	public static void deleteKey(String name) {
		// TODO Auto-generated method stub
		
	}

	public static void editKey(WFKey key) {
		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Key Name", key.getName());
		table().click(atCell(atRow(row), atColumn("Key Name")));
		DOF.getButton(DOF.getRoot(), "Ed&it...").click();
		sleep(1);
		WFKeyDialog.setKey(key);
	}

}
