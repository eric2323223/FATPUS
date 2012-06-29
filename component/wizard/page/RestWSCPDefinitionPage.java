package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

public class RestWSCPDefinitionPage extends RationalTestScript{

	public static void setResourceBaseUrl(String str, TopLevelTestObject dialog) {
		if(str!=null){
			DOF.getTextField(dialog, "Resource base URL:").click();
			dialog.inputChars(str);
		}
	}

	public static void setResourceUriTemplate(String str,
			TopLevelTestObject dialog) {
		for(String item:str.split(":")){
			setSingleResourceUriTemplate(item, dialog);
		}
	}
	
	private static void setSingleResourceUriTemplate(String item, TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Add").click();
		GuiSubitemTestObject table = DOF.getTable(dialog);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Resource URI template", "/uriTemplate");
		TableHelper.setTextCellValue(table, row, "Resource URI template", item);
		DOF.getTextField(dialog, "Resource base URL:").click();
	}

}
