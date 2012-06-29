package component.entity.wizard;

import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;

import component.entity.model.OperationParameter;
import component.view.properties.attributes.OperationParameterTab;
import component.wizard.page.WebServiceDefinitionPage;


public class WSOperationCW extends AbstractOperationCW {
	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}

	@Override
	public void setName(String string) {
		super.setName(string);
	}

	@Override
	public void setType(String str) {
		super.setType(str);
	}
	
	public void setMethod(String str){
		WebServiceDefinitionPage.setMethod(dialog(), str);
	}

	@Override
	public void start(String string) {
		DOF.getWNTree().doubleClick(atPath(string));
		sleep(1);
		PropertiesTabHelper.clickTabName("Operations");
		DOF.getButton(DOF.getRoot(), "&Add...").click();
		sleep(1);
	}
	
	public void setOperationParameter(String str){
		for(String s:str.split(":")){
			setSingleOperationParameter(s);
		}
		
	}
	
	private void setSingleOperationParameter(String str){
		OperationParameter op = new OperationParameter(str);
		OperationParameterTab.set(op, dialog());
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setMethod")){
			return 1;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}
	
}
