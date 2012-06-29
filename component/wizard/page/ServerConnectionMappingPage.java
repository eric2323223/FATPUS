package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.entity.LongOperationMonitor;

public class ServerConnectionMappingPage extends RationalTestScript{

	public static void setSingleServerConnectionMapping(String connection,	TopLevelTestObject dialog) {
		String[] connections = connection.split(",");
		GuiSubitemTestObject table = DOF.getTableByColumnsNames(dialog, new String[]{"Connection profile", "Server connection"});
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Connection profile", connections[0]);
		try{
			TableHelper.setCComboCellValue(table, row, "Server connection", connections[1]);
		}catch(Exception e){
			TableHelper.setCComboCellValue(table, row, "Server connection", "<New Server Connection ...>");
			sleep(1);
			TopLevelTestObject dialog2 = DOF.getDialog("Create New Server Connection");
			DOF.getTextField(dialog2, "Server connection name:").click();
			dialog2.inputKeys(SpecialKeys.CLEARALL);
			dialog2.inputChars(connections[1]);
			DOF.getButton(dialog2, "OK", true).click();
			LongOperationMonitor.waitForDialogToVanish("Progress Information");
		}
		table.click(atCell(atRow(0),atColumn(0)), atPoint(10,10));
		DOF.getTableByColumnsNames(dialog, new String[]{"Property", "Value"}).click(atCell(atRow(0), atColumn(1)));

	}
	
	public static void ok(TopLevelTestObject dialog){
		DOF.getButton(dialog, "OK").click();
	}

}
