package component.entity.wizard;

import com.sybase.automation.framework.widget.DOF;

public class MainMenuPKCreationWizard extends PKCreationWizard {
	
	public void start(String para){
		DOF.getMenu().click(atPath("File->New->Personalization Key"));
		sleep(1);
//		DOF.getCombo(dialog(), "Mobile application project:").click(atText(para));
	}

	@Override
	public void setDefaultValue(String string) {
		// TODO Auto-generated method stub
		super.setDefaultValue(string);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public void setNullable(String string) {
		// TODO Auto-generated method stub
		super.setNullable(string);
	}

	@Override
	public void setProtect(String string) {
		// TODO Auto-generated method stub
		super.setProtect(string);
	}

	@Override
	public void setStorage(String string) {
		// TODO Auto-generated method stub
		super.setStorage(string);
	}

	@Override
	public void setType(String string) {
		// TODO Auto-generated method stub
		super.setType(string);
	}
	

}
