package component.entity.model;

import com.rational.test.ft.script.RationalTestScript;

import component.entity.IEditable;

public abstract class Widget extends RationalTestScript implements IEditable{
	protected String label;
	protected String labelPosition;
	protected String logicalType;
	protected String newKey;
	protected String useKey;
	protected String key;
	protected String maximumValue;
	protected String minValue;
	protected boolean ifReadonly;
	protected boolean ifRequired;
	protected String option;
	protected String newKeyBindMbo;
	//add by yanxu
	protected String defaultvalue;
	protected String NewKeyBindMBOQueryRequest;
	
	//ff1212>>>>>>>>>>>>>>>>>>>>>>>>
	protected String NewKeyBindMBORelationship;
	//ff1220>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	protected String emptyMessage;
	protected String alternatecolor;
	private String lvDetailScreen;
	
	public Widget lvDetailScreen(String string) {
		this.lvDetailScreen = string;
		return this;
	}
	
	public String getLvDetailScreen() {
		return this.lvDetailScreen;
	}
	
	public Widget emptyMessage(String str){
		this.emptyMessage = str;
		return this;
	}
	
	public String getEmptyMessage(){
		return this.emptyMessage;
	}
	
	public String getalternateColor() {
		return this.alternatecolor;
	}

	public Widget alternateColor(String string) {
		this.alternatecolor = string;
		return this;
	}
	//end
	
	//ff1212<<<<<<<<<<<<<<<<<<<<<
	
	//ff11.24>>>>>>>>>>>
	protected String type;
	protected String screenn;
	
	public Widget type(String str){
		this.type = str;
		return this;
	}
	public Widget screenn(String str){
		this.screenn = str;
		return this;
	}
	//ff11.24<<<<<<<<<<<
	
	
	public Widget defaultValue(String str){
		this.defaultvalue = str;
		return this;
	}
	//end
	public Widget label(String str){
		this.label = str;
		return this;
	}
	
	public Widget labelPosition(String str){
		this.labelPosition = str;
		return this;
	}
	
	public Widget logicalType(String str){
		this.logicalType = str;
		return this;
	}
	
	public  Widget maximumValue(String maximumvalue) {
		// TODO Auto-generated method stub
		this.maximumValue = maximumvalue;
		return this;
	}
	
	public Widget useKey(String key){
		this.useKey = key;
		return this;
	}
	
	public String getUseKey(){
		return this.useKey;
	}
	
	public  Widget minValue(String minvalue) {
		this.minValue = minvalue;
		return this;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public Widget key(String str){
		this.key = str;
		return this;
	}
	
	public  Widget ifReadonly(boolean value) {
		this.ifReadonly = value;
		return this;
	}
	
	public  Widget ifRequired(boolean value) {
		// TODO Auto-generated method stub
		this.ifRequired = value;
		return this;
	}
	
	public  Widget option(String option) {
		// TODO Auto-generated method stub
		this.option = option;
		return this;
	}
	
	public String getLogicalType(){
		return this.logicalType;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public String getLabelPosition(){
		return this.labelPosition;
	}
	
	public Widget newKey(String string) {
		newKey = string;
		return this;
	}

	public String getNewKey(){
		return this.newKey;
	}
	
	public Widget newKeyBindMbo(String string) {
		newKeyBindMbo= string;
		return this;
	}
	public String getNewKeyBindMbo(){
		return this.newKeyBindMbo;
	}
	
	public String getmaxvalue(){
		return this.maximumValue;
	}
	
	public String getMinvalue(){
		return this.minValue;
	}
	
	public boolean getIfreadonly(){
		return this.ifReadonly;
	}
	
	public boolean getIfRequired(){
		return this.ifRequired;
	}

	public String option() {
		return this.option;
		
	}
//add by yanxu	
	public String getdefaultValue(){
		return this.defaultvalue;
	}
	public Widget NewKeyBindMBOQueryRequest(String string) {
		NewKeyBindMBOQueryRequest= string;
		return this;
	}
	public String getNewKeyBindMBOQueryRequest(){
		return this.NewKeyBindMBOQueryRequest;
	}
//end	
	//ff1212>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Widget NewKeyBindMBORelationship(String string) {
		NewKeyBindMBORelationship= string;
		return this;
	}
	public String getNewKeyBindMBORelationship(){
		return this.NewKeyBindMBORelationship;
	}
	//<<<<<<<<<<<<<<<<<<<<<<ff1212
	
	@Override
	abstract public void openInPropertiesView();
	
	abstract public void add();
	
	abstract public void update();
	
	abstract public void delete();

}
