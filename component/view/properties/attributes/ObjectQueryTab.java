package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.model.ObjectQuery;

public class ObjectQueryTab extends RationalTestScript{
	private static GuiSubitemTestObject table(){
		return DOF.getTable(DOF.getRoot());
	}

	public static void set(ObjectQuery oq) {
		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Name", oq.getName());
		if(oq.getReturnType()!=null){
			table().click(atCell(atRow(row), atColumn("Return type")));
			table().click(atCell(atRow(row), atColumn("Return type")));
			DOF.getPoppedUpList().click(atText(oq.getReturnType()));
		}
		
	}

}
