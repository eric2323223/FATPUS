package component.dialog;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

public class SearchForMboDailog extends RationalTestScript{

	public static void selectMbo(TopLevelTestObject dialog, String project,	String mbo) {
		((ToggleGUITestObject)DOF.getButton(dialog, "&All mobile application projects")).clickToState(NOT_SELECTED);
		DOF.getCombo(dialog, "Project:").click();
		DOF.getCombo(dialog, "Project:").click(atText(project));
		DOF.getButton(dialog, "&Search").click();
		sleep(2);
		GuiSubitemTestObject table = DOF.getTable(dialog);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", mbo);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(dialog, "OK").click();
	}

}
