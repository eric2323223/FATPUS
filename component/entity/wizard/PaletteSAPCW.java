package component.entity.wizard;

import component.entity.SAPMBOCreationWizard;
import component.entity.WN;
import component.view.Palette;

public class PaletteSAPCW extends SAPMBOCreationWizard {

	public void start(String str){
		WN.openDiagramEditor(str);
		Palette.addMBO(10, 10);
//		Palette.createItem("Mobile Business Object", 10, 10);
	}

	@Override
	public void setActivateVerify(String str) {
		// TODO Auto-generated method stub
		super.setActivateVerify(str);
	}
	
	public void setAttribute(String str){
		super.setAttribute(str);
	}

	@Override
	public void setBapiOperation(String str) {
		// TODO Auto-generated method stub
		super.setBapiOperation(str);
	}

	@Override
	public void setConnectionProfile(String string) {
		// TODO Auto-generated method stub
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		// TODO Auto-generated method stub
		super.setDataSourceType(string);
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setOperation(String str) {
		// TODO Auto-generated method stub
		super.setOperation(str);
	}

	@Override
	public void setParameter(String str) {
		// TODO Auto-generated method stub
		super.setParameter(str);
	}

	@Override
	public void setParameterValue(String str) {
		// TODO Auto-generated method stub
		super.setParameterValue(str);
	}
}
