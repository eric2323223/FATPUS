package component.entity.wizard;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.LongOperationMonitor;
import component.entity.MainMenu;
import component.entity.OD;

public class EEAttributeDbMboCreationWizard extends DbMboCreationWizard {
	
	public void start(String str){
		Point point = OD.getValidPoint();
		EE.dndResource(str, point.x, point.y);
		sleep(1);
		if(DOF.getDialog("Warning")!=null){
			DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		}
		sleep(3);
		String dialogCapital = LongOperationMonitor.waitForDialog();
//		LongOperationMonitor.waitForDialog("New Mobile Business Object Options");
//		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
//		LongOperationMonitor.waitForDialog("New Mobile Business Object Options");
		DOF.getButton(DOF.getDialog(dialogCapital), "OK").click();
	}
	
	public void finish(){
		sleep(1);
		if(dialog()!=null){
			DOF.getButton(dialog(), "&Finish").click();
			LongOperationMonitor.waitForProgressBarVanish(dialog());
		}
		MainMenu.saveAll();
	}

	@Override
	public void setAttributeMapping(String mapping) {
		// TODO Auto-generated method stub
		super.setAttributeMapping(mapping);
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
	public void setExistingFilter(String name) {
		// TODO Auto-generated method stub
		super.setExistingFilter(name);
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setNewFilter(String name) {
		// TODO Auto-generated method stub
		super.setNewFilter(name);
	}

	@Override
	public void setParameter(String string) {
		// TODO Auto-generated method stub
		super.setParameter(string);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public void setResultSet(String str) {
		// TODO Auto-generated method stub
		super.setResultSet(str);
	}

	@Override
	public void setSingleAttributeMapping(String mapping) {
		// TODO Auto-generated method stub
		super.setSingleAttributeMapping(mapping);
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
	
	

}
