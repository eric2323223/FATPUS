package component.entity.model;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.IEditable;
import component.view.properties.workflow.WFKeyParameterMappingTab;

public class WFKeyParameterMapping extends RationalTestScript implements IEditable{
	String parameter;
	String key;
	
	private GuiSubitemTestObject table(){
		return DOF.getTable(DOF.getRoot());
	}
	
	public WFKeyParameterMapping key(String str){
		this.key = str;
		return this;
	}
	
	public WFKeyParameterMapping parameter(String str){
		this.parameter = str;
		return this;
	}

	public String getParameter() {
		return parameter;
	}

	public String getKey() {
		return key;
	}

	@Override
	public void add() {
		WFKeyParameterMappingTab.addMapping(this);
	}

	@Override
	public void delete() {
		
	}

	@Override
	public void openInPropertiesView() {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		PropertiesTabHelper.clickTabName("Parameter Mappings");
	}

	@Override
	public void update() {
		WFKeyParameterMappingTab.setMapping(this);
//		int row = TableHelper.getRowIndexOfRecordInColumn(table(), "Parameter Name", this.parameter);
//		table().click(atCell(atRow(row), atColumn("Parameter Name")));
//		DOF.getButton(DOF.getRoot(), "&Edit...").click();
	}

}
