package component.view.properties.attributes;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.LongOperationMonitor;
import component.entity.MainMenu;

public class DataSourceTab extends RationalTestScript{

	public static boolean setConnectionProfile(String connectionProfile) {
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Data Source"));
		DOF.getButton(DOF.getRoot(), "C&hange Connection Profile...").click();
		DOF.getCombo(DOF.getDialog("Data Source"),"Connection profile:").click();
		DOF.getCombo(DOF.getDialog("Data Source"),"Connection profile:").click(atText(connectionProfile));
		DOF.getButton(DOF.getDialog("Data Source"),"OK").click();
		sleep(1);
		if(DOF.getDialog("Error")!=null){
			DOF.getButton(DOF.getDialog("Error"),"OK").click();
			return false;
		}else{
			LongOperationMonitor.waitForDialogToVanish("Progress Information");
			MainMenu.saveAll();
			return true;
		}
	}
	
	public static String getConnectionProfile(){
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Data Source"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		return TableHelper.getCellValue(table, 0, "Value");
	}

}
