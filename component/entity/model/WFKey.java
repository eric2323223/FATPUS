package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;

import component.entity.IEditable;
import component.view.properties.workflow.WFKeysTab;

public class WFKey extends AbstractModel implements IEditable {
	private String name;
	private String type;
	private String sentByServer;
	private String mbo;
	private String mboRelationship;
	
	VerificationCriteria verifyName;
	
	public WFKey(){}
	
	public WFKey(String key) {
		WFKey obj = (WFKey)ObjectMarshaller.deserialize(key, WFKey.class);
		this.name = obj.name;
		this.type = obj.type;
		this.sentByServer = obj.sentByServer;
		this.mbo = obj.mbo;
		this.mboRelationship = obj.mboRelationship;
	}
	
	public WFKey sentByServer(String str){
		this.sentByServer = str;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getSentByServer() {
		return sentByServer;
	}
	
	public WFKey type(String str){
		this.type = str;
		return this;
	}
	
	public WFKey name(String str){
		this.name = str;
		return this;
	}
	
	public WFKey mbo(String str){
		this.mbo = str;
		return this;
	}
	
	public String getMbo(){
		return this.mbo;
	}
	
	public WFKey  verifyName(String str, boolean b) {
		this.verifyName = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	
	public String getMboRelationship() {
		return this.mboRelationship;
	}
	
	public WFKey mboRelationship(String str) {
		this.mboRelationship = str;
		return this;
	}

	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add() {
		PropertiesTabHelper.clickTabName("Keys");
		WFKeysTab.createKey(this);
	}

	@Override
	public void delete() {
		PropertiesTabHelper.clickTabName("Keys");
		WFKeysTab.deleteKey(this.getName());
	}

	@Override
	public void update() {
		PropertiesTabHelper.clickTabName("Keys");
		WFKeysTab.editKey(this);
	}
}
