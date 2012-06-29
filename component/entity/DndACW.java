package component.entity;

import com.sybase.automation.framework.widget.DOF;

public class DndACW extends ACW{
	
	private boolean canContinue = true;

	@Override
	public void start(String parameter) {
		EE.dndOperationAsAttributes(parameter, 10, 10);
	}
	
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setProjectName"))
			return 0;
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 0;
		if(operation.equals("setDataSourceType"))
			return 0;
		if(operation.equals("setSqlQuery"))
			return 0;
		if(operation.equals("setStoredProcedure"))
			return 0;
		if(operation.equals("setFilter"))
			return 0;
		if(operation.equals("setAttributeMapping"))
			return 0;
		if(operation.equals("setParameter"))
			return 0;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	} 
	
	public void setStoredProcedure(String string){
		super.setStoredProcedure(string);
	}
	
	public void setSqlQuery(String string){
		super.setSqlQuery(string);
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
	public void setFilter(String name) {
		// TODO Auto-generated method stub
		super.setFilter(name);
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
}
