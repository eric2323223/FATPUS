package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.DeployWizard;

public class PackageDeployWizard extends DeployWizard {
	@Override
	public void setType(String str) {
		// TODO Auto-generated method stub
		super.setType(str);
	}

	@Override
	public void setTargetVersion(String str) {
		// TODO Auto-generated method stub
		super.setTargetVersion(str);
	}

	@Override
	public void setOverWriteExistingJar(String str) {
		// TODO Auto-generated method stub
		super.setOverWriteExistingJar(str);
	}

	@Override
	public void setJar(String str) {
		// TODO Auto-generated method stub
		super.setJar(str);
	}

	public void start(String path){
		DOF.getWNTree().click(RIGHT, atPath(path));
		DOF.getContextMenu().click(atPath("Deploy Package..."));
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Deploy Mobile Package");
	}

	@Override
	public void setMapRole(String string) {
		super.setMapRole(string);
	}

	@Override
	public void setMboNames(String string) {
		super.setMboNames(string);
	}

	@Override
	public void setMode(String string) {
		super.setMode(string);
	}

	@Override
	public void setPackageName(String string) {
		super.setPackageName(string);
	}

	@Override
	public void setServer(String string) {
		super.setServer(string);
	}

	@Override
	public void setServerConnectionMapping(String connection) {
		super.setServerConnectionMapping(connection);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setTargetVersion"))
			return 0;
		if(operation.equals("setOverWriteExistingJar"))
			return 0;
		if(operation.equals("setMode"))
			return 0;
		if(operation.equals("setPackageName"))
			return 0;
		if(operation.equals("setType"))
			return 0;
		if(operation.equals("setServer"))
			return 1;
		if(operation.equals("setServerConnectionMapping"))
			return 3;
		if(operation.equals("setMapRole"))
			return 4;
		else
			throw new RuntimeException("Unkonwn operation: "+operation);
	}
	
	

}
