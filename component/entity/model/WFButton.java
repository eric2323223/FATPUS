package component.entity.model;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.SearchForMboDailog;
import component.entity.IEditable;
import component.entity.PropertiesView;
import component.view.properties.workflow.WFKeyParameterMappingTab;
import component.view.properties.workflow.WFKeyPkMappingTab;
//ff11.24>>>>>>>>>
public class WFButton extends Widget {
	private String newKey;
	private String defaultValue;
	private String type;
	private String labelPosition;
	private String screenn;
	private String name;
	
	
	public String getNewKey() {
		return newKey;
	}
	
	public String getName(){
		return this.name;
	}
	
	public WFButton name(String str){
		this.name = str;
		return this;
	}
	
	public WFButton setNewKey(String newKey) {
		this.newKey = newKey;
		return this;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public WFButton defaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	
	public String getType() {
		return type;
	}
	
	public WFButton type(String type) {
		this.type = type;
		return this;
	}
	
	public String getScreenn() {
		return screenn;
	}
	
	public WFButton screenn(String screenn) {
		this.screenn = screenn;
		return this;
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
	public void openInPropertiesView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		PropertiesView.maximize();
		if(getKey()!=null){
			PropertiesView.clickTab("General");
			DOF.getTextField(DOF.getRoot(), "Key:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(getKey());
		}
		if(getType()!=null){
			PropertiesView.clickTab("General");
//			DOF.getCCombo(DOF.getRoot(), "Type:").click();
//			DOF.getPoppedUpList().click(RationalTestScript.atText(getType()));
//			RationalTestScript.sleep(1);
//			DOF.getTextField(DOF.getRoot(), "Key:").click();
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Type:"), getType());
		}
		if(getScreen()!=null){
			PropertiesView.clickTab("General");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Screen:"), this.getScreenn());
		}
		
		PropertiesView.restore();
	}

}
