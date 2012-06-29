package component.entity.model;

import com.sybase.automation.framework.widget.DOF;

import component.entity.IEditable;
import component.entity.WFWidgetHelper;

public class WFEditBox extends Widget implements IEditable {

	private String label;
	private String labelPosition;
	private String logicalType;
	private String newKey;
	//ff>>>>>>>>>9.6
	private String newKeyBindMbo;
	//<<<<<<<ff 9.6
	private String maxLength;
	private String lines;
	private String validationExpression;
	private String validationMessage;
	private boolean password;
	//********add by yanxu
	private String Timeprecision;
	private boolean credentialusername;
	private boolean credentialpassword;
	private String numberofdecimal;
	private String numberofdecimals;
	private String maximumvalue;


	
//	>>>>>>>>>>> flv <<<<<<<<<<<<<<<<<
	private String key;
	//<10/20 added>
	private boolean credentialUserName;
	private boolean credentialPassword;
	private String defaultValue;
	
//	>>>>>>>>>>> flv <<<<<<<<<<<<<<<<<
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public WFEditBox setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public WFEditBox setMaximumvalue(String maximumvalue ) {
		this.maximumvalue = maximumvalue;
		return this;
	}
	
	public WFEditBox key(String string) {
		this.key = string;
		return this;
	}
	
	public boolean isCredentialUserName() {
		return credentialUserName;
	}

	public WFEditBox setCredentialUserName(boolean credentialUserName) {
		this.credentialUserName = credentialUserName;
		return this;
	}

	public boolean isCredentialPassword() {
		return credentialPassword;
	}

	public WFEditBox setCredentialPassword(boolean credentialPassword) {
		this.credentialPassword = credentialPassword;
		return this;
	}

	public String getKey() {
		return this.key;
	}
	
	public WFEditBox Numberofdecimal(String string) {
		numberofdecimal = string;
		return this;
	}
	public WFEditBox Numberofdecimals(String string) {
		numberofdecimals = string;
		return this;
	}
	public WFEditBox Timeprecision(String string) {
		Timeprecision = string;
		return this;
	}
	
	public WFEditBox credentialusername(boolean username) {
		credentialusername = username;
		return this;
	}
	
	public WFEditBox credentialpassword(boolean password) {
		credentialpassword = password;
		return this;
	}
	//end
	
	public WFEditBox label(String string) {
		label = string;
		return this;
	}

	public WFEditBox labelPosition(String string) {
		this.labelPosition = string;
		return this;
	}
	
	public WFEditBox useKey(String str){
		this.useKey = str;
		return this;
	}

	public WFEditBox logicalType(String string) {
		logicalType = string;
		return this;
	}

	public WFEditBox newKey(String string) {
		newKey = string;
		return this;
	}
	
	public WFEditBox newKeyBindMbo(String string) {
		newKeyBindMbo = string;
		return this;
	}
	
	public WFEditBox maxLength(String string) {
		maxLength = string;
		return this;
	}

	public WFEditBox lines(String string) {
		lines = string;
		return this;
	}

	public WFEditBox validationExpression(String string) {
		validationExpression = string;
		return this;
	}

	public WFEditBox validationMessage(String string) {
		validationMessage = string;
		return this;
	}
	
	public WFEditBox ifReadonly(boolean value){
		super.ifReadonly(value);
		return this;
	}
	
	public WFEditBox ifRequired(boolean value){
		super.ifRequired(value);
		return this;
	}
	public WFEditBox password(boolean pw) {
		password = pw;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public String getLabelPosition() {
		return labelPosition;
	}

	public String getLogicalType() {
		return logicalType;
	}

	public String getNewKey() {
		return newKey;
	}
	
	public String getNewKeyBindMbo() {
		return newKeyBindMbo;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public String getLines() {
		return lines;
	}

	public String getValidationExpression() {
		return validationExpression;
	}

	public String getValidationMessage() {
		return validationMessage;
	}
	
	public boolean getPassword() {
		return password;
	}
	
	@Override
	public void openInPropertiesView() {
				
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	//add by yanxu
	public String getTimeprecision() {
		return Timeprecision;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public boolean getcredentialusername() {
		return credentialusername;
	}
	
	public boolean getcredentialpassword() {
		return credentialpassword;
	}
	public String getNumberofdecimal() {
		return numberofdecimal;
	}
	public String getNumberofdecimals() {
		return numberofdecimals;
	}
	public String getmaximumvalue() {
		return maximumvalue;
	}

}
