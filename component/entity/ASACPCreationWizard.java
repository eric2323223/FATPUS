package component.entity;


import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

/**
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class ASACPCreationWizard extends ACW  {
	protected boolean canContinue = true;

	protected TopLevelTestObject dialog() {
		if (DOF.getDialog("New ASA Connection Profile") != null) {
			return DOF.getDialog("New ASA Connection Profile");
		}
		if (DOF.getDialog("New Sybase ASE Connection Profile") != null) {
			return DOF.getDialog("New Sybase ASE Connection Profile");
		} else {
			return DOF.getDialog("New Connection Profile");
		}
		// return DOF.getActiveDialog();
	}

	private String getMessage() {
		return DOF.getTextFieldByClientArea(dialog(), 361, 36).getProperty(
				"text").toString();
	}

	public void verifyName(VerificationCriteria vc) {
		System.out.println("Actual message: [" + getMessage() + "]");
		if (vpManual("verifyName", vc.getExpected(), getMessage())
				.performTest()) {
			this.canContinue = vc.isContinueWhenMatch();
		} else {
			this.canContinue = !vc.isContinueWhenMatch();
		}
	}
	
	public void setDriverName(String str){
		DOF.getCombo(dialog(), "Drivers:").click();
		DOF.getCombo(dialog(), "Drivers:").click(atText(str));
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if (operation.equals("setName")) {
			return 0;
		}
		if (operation.equals("setType")) {
			return 0;
		}
		if (operation.equals("setDriver")) {
			return 1;
		}
		if (operation.equals("setDriverName")) {
			return 1;
		}
		if (operation.equals("setHost")) {
			return 1;
		}
		if (operation.equals("setPort")) {
			return 1;
		}
		if (operation.equals("setDbName")) {
			return 1;
		}
		if (operation.equals("setUserName")) {
			return 1;
		}
		if (operation.equals("setPassword")) {
			return 1;
		} else {
			throw new RuntimeException("Unknown operation: " + operation);
		}
	}

	public String getDependOperation(String operation) {
		if (operation.equals("setName")) {
			return "verifyName";
		} else {
			return null;
		}
	}

	@Override
	public void start(String parameter) {
		DOF.getEETree().click(RationalTestScript.RIGHT,
				RationalTestScript.atPath("Database Connections"));
		DOF.getContextMenu().click(RationalTestScript.atPath("New..."));
	}

	public void setName(String name) {
		setTextField("Name:", name);
	}

	public void setType(String type) {
		GuiSubitemTestObject table = DOF.getTable(dialog());
		table.click(RationalTestScript.atText(type));
	}

	public void setHost(String host) {
		try {
			setTextField("Host:", host);
		} catch (NullPointerException e) {
			setTextField("Host: ", host);
		}
	}

	public void setPort(String port) {
		setTextField("Port:", port);
	}

	public void setDbName(String dbName) {
		setTextField("Database name:", dbName);
	}

	public void setUserName(String userName) {
		setTextField("User name:", userName);
	}

	public void setPassword(String password) {
		setTextField("Password:", password);
		((ToggleGUITestObject)DOF.getButton(dialog(), "Save Password")).clickToState(SELECTED);
	}

	protected void setTextField(String priorLabel, String value) {
		DOF.getTextField(dialog(), priorLabel).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(value);
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		sleep(3);
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}

	public void create(IWizardEntity entity, WizardRunner wr) {
		wr.run(entity, this);
	}

	@Override
	public IWizard canContinue(boolean b) {
		canContinue = b;
		return this;
	}

}
