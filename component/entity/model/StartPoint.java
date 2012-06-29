package component.entity.model;

import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.IEditable;
import component.entity.MainMenu;
import component.entity.WorkFlow;
import component.entity.wizard.NotificationProcessingWizard;

public class StartPoint extends Widget implements IEditable, IWizardEntity {

	public String type;
	public String key;
	public String mbo;
	public String name;
	public String objectQuery;
	public String parameterMapping;
	private String pkMapping;
	//ff10.17>>>>>>>>>>>>>>>>>>
	public String project;
	//ff10.17<<<<<<<<<<<<<
	
//	ff1.12>>>>>>>>>>>
	public String subject;
	public String subjectMatchingRule;
	public String parameterValue;
	private String errorScreen;
	private String from;
	private boolean generateErrorScreen;
	//<<<<<<<<<ff1.12
	
	public StartPoint type(String str){
		this.type = str;
		return this;
	}
	
	public StartPoint parameterMapping(String str){
		if(parameterMapping==null){
			parameterMapping = str;
		}else{
			parameterMapping = parameterMapping +":"+ str;
		}
		return this;
	}
	
	public String getParameterMapping(){
		return this.parameterMapping;
	}
	
	public StartPoint key(String str){
		if(key == null){
			key = str;
		}else{
			key = key+":"+str;
		}
		return this;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getKey(){
		return this.key;
	}

	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	public StartPoint mbo(String string) {
		mbo = string;
		return this;
	}
	
	public String getMbo(){
		return this.mbo;
	}

	public StartPoint name(String string) {
		this.name = string;
		return this;
	}
	
	public String getName(){
		return this.name;
	}

	public StartPoint objectQuery(String str){
		this.objectQuery = str;
		return this;
	}
	
	public String getObjectQuery() {
		// TODO Auto-generated method stub
		return this.objectQuery;
	}
	
	public String sp(){
		return null;
	}
	public IWizardEntity startParameter(String str){
		return null;
	}

	public String getPkMapping() {
		return this.pkMapping;
	}
	
	public StartPoint pkMapping(String str){
		this.pkMapping = str;
		return this;
	}
//ff10.17>>>>>>>>>>>>>>>>>>>>>>>>>
	public StartPoint project(String str) {
		// TODO Auto-generated method stub
		this.project = str;
		return this;
	}
	
	public String getProject(){
		return this.project;
	}
//ff10.17<<<<<<<<<<<<<<<<<<<<<<<

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
//		if(getObjectQuery()!=null){
//			PropertiesTabHelper.clickTabName("General");
//			new NotificationProcessingWizard().create(this, new WizardRunner());
//		}
//		if(getKey()!=null){
//			String[] keys = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
//			if(getType().equals(WorkFlow.SP_SERVER_INIT)){
//				for(String key:keys)
//					removeKeyServer(key);
//				setNewKeyServer(sp.getKey());
//			}
//			else{
//				for(String key:keys)
//					removeKey(key);
//				setNewKey(getKey());
//			}
//		}
//		MainMenu.saveAll();
	
		
	}
	//ff1.12>>>>>>>>>>>>>>>>
	public StartPoint subject(String string) {
		this.subject = string;
		return this;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public StartPoint subjectMatchingRule(String string) {
		this.subjectMatchingRule = string;
		return this;
	}
	
	public String getSubjectMatchingRule(){
		return this.subjectMatchingRule;
	}
	
	public StartPoint parameterValue(String string) {
		this.parameterValue = string;
		return this;
	}
	
	public String getParameterValue(){
		return this.parameterValue;
	}

	public StartPoint errorScreen(String text) {
		this.errorScreen=text;
		return this;
	}
	
	public String getErrorScreen(){
		return this.errorScreen;
	}

	public StartPoint from(String text) {
		this.from= text;
		return this;
	}

	public String getFrom(){
		return this.from;
	}

	public StartPoint generateErrorScreen(boolean b) {
		this.generateErrorScreen = b;
		return this;
	}
	
	public boolean getGenerateErrorScreen(){
		return this.generateErrorScreen;
	}
}
