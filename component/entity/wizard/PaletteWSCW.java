package component.entity.wizard;

import component.entity.WN;
import component.entity.WSMBOCreationWizard;
import component.view.Palette;

public class PaletteWSCW extends WSMBOCreationWizard {

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
	public void setExistingResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setExistingResultChecker(str);
	}

	@Override
	public void setJavaNature(String str) {
		// TODO Auto-generated method stub
		super.setJavaNature(str);
	}

	@Override
	public void setMethod(String str) {
		// TODO Auto-generated method stub
		super.setMethod(str);
	}

	@Override
	public void setName(String str) {
		// TODO Auto-generated method stub
		super.setName(str);
	}

	@Override
	public void setNewResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setNewResultChecker(str);
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

	@Override
	public void setResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setResultChecker(str);
	}

	@Override
	public void setSingleParameter(String str) {
		// TODO Auto-generated method stub
		super.setSingleParameter(str);
	}

	@Override
	public void setXslt(String file) {
		// TODO Auto-generated method stub
		super.setXslt(file);
	}

	@Override
	public void start(String parameter) {
		WN.openDiagramEditor(parameter);
		Palette.addMBO(10, 10);
	}


}
