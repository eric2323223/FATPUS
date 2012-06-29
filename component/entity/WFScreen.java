package component.entity;

import java.util.List;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

import component.entity.model.WFScreenMenuItem;
import component.entity.model.Widget;

public class WFScreen extends RationalTestScript implements IEditable{
	String name;
	
	public String getName(){
		return this.name;
	}
	
	public WFScreen name(String str){
		this.name = str;
		return this;
	}

	@Override
	public void openInPropertiesView() {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), this.name).doubleClick();
		sleep(1);
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	

}
