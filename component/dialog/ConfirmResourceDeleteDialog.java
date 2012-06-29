package component.dialog;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class ConfirmResourceDeleteDialog extends RationalTestScript{

	public static void delete() {
		sleep(1);
		if(DOF.getDialog("Confirm Resource Delete")!=null){
			DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
		}
		if(DOF.getDialog("Confirm Delete")!=null){
			DOF.getButton(DOF.getDialog("Confirm Delete"), "&Yes").click();
		}
		if(DOF.getDialog("Delete")!=null){
			DOF.getButton(DOF.getDialog("Delete"), "OK").click();
		}
		sleep(1);
	}
	

}
