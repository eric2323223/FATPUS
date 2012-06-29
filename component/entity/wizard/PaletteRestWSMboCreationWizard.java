package component.entity.wizard;

import java.awt.Point;

import component.entity.OD;
import component.entity.WN;
import component.view.Palette;

public class PaletteRestWSMboCreationWizard extends RestWSMboCreationWizard {

	@Override
	public String getDependOperation(String dependOperation) {
		// TODO Auto-generated method stub
		return super.getDependOperation(dependOperation);
	}

	@Override
	public void setAttribute(String str) {
		// TODO Auto-generated method stub
		super.setAttribute(str);
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
	public void setExpectedStatusCode(String str) {
		// TODO Auto-generated method stub
		super.setExpectedStatusCode(str);
	}

	@Override
	public void setFilter(String name) {
		// TODO Auto-generated method stub
		super.setFilter(name);
	}

	@Override
	public void setHeader(String str) {
		// TODO Auto-generated method stub
		super.setHeader(str);
	}

	@Override
	public void setHttpMethod(String str) {
		// TODO Auto-generated method stub
		super.setHttpMethod(str);
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setPassword(String str) {
		// TODO Auto-generated method stub
		super.setPassword(str);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public void setResourceBaseUrl(String str) {
		// TODO Auto-generated method stub
		super.setResourceBaseUrl(str);
	}

	@Override
	public void setResourceUriTemplate(String str) {
		// TODO Auto-generated method stub
		super.setResourceUriTemplate(str);
	}

	@Override
	public void setResponse(String str) {
		// TODO Auto-generated method stub
		super.setResponse(str);
	}

	@Override
	public void setSqlQuery(String string) {
		// TODO Auto-generated method stub
		super.setSqlQuery(string);
	}

	@Override
	public void setStoredProcedure(String text) {
		// TODO Auto-generated method stub
		super.setStoredProcedure(text);
	}

	@Override
	public void setUserName(String str) {
		// TODO Auto-generated method stub
		super.setUserName(str);
	}

	@Override
	public void start(String string) {
		WN.openDiagramEditor(string);
		Point point = OD.getValidPoint();
		Palette.addMBO(point.x, point.y);
	}

}
