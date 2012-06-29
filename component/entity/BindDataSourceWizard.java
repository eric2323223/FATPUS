package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class BindDataSourceWizard extends WSMBOCreationWizard{

	@Override
	public void start(String string) {
		// do nothing
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
	
	protected TopLevelTestObject dialog() {
		return DOF.getDialog("Bind Data Source");
	}
	
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setConnectionProfile"))
			return 0;
		if(operation.equals("setDataSourceType"))
			return 0;
		if(operation.equals("setSqlQuery"))
			return 1;
		if(operation.equals("setStoredProcedure"))
			return 1;
		if(operation.equals("setFilter"))
			return 1;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}

	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}

	@Override
	public void setFilter(String name) {
		super.setFilter(name);
	}

	@Override
	public void setSqlQuery(String string) {
		super.setSqlQuery(string);
	}

	@Override
	public void setStoredProcedure(String text) {
		super.setStoredProcedure(text);
	}

	@Override
	public void setMethod(String str) {
		super.setMethod(str);
	}

	@Override
	public void setParameter(String str) {
		super.setParameter(str);
	}

	@Override
	public void setResultChecker(String str) {
		super.setResultChecker(str);
	}
}
