package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;

public class DBMBORebindWizard extends DbMboCreationWizard {

	@Override
	public void start(String string) {

	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Bind Data Source");
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
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
	public void setAttributeMapping(String mapping) {
		// TODO Auto-generated method stub
		super.setAttributeMapping(mapping);
	}

	@Override
	public void setExistingFilter(String name) {
		// TODO Auto-generated method stub
		super.setExistingFilter(name);
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
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
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
	
	public int getPageIndexOfOperation(String operation){
//		if(operation.equals("setProjectName"))
//			return 0;
//		if(operation.equals("setName"))
//			return 0;
		if(operation.equals("setConnectionProfile"))
			return 0;
		if(operation.equals("setDataSourceType"))
			return 0;
		if(operation.equals("setAttribute"))
			return 1;
		if(operation.equals("setSqlQuery"))
			return 1;
		if(operation.equals("setStoredProcedure"))
			return 1;
		if(operation.equals("setFilter"))
			return 1;
		if(operation.equals("setParameter"))
			return 2;
		if(operation.equals("setAttributeMapping"))
			return 3;
		if(operation.equals("setRole"))
			return 4;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}

	@Override
	public void setRole(String str) {
		// TODO Auto-generated method stub
		super.setRole(str);
	}

}
