package component.entity.model;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.SearchForMboDailog;
import component.entity.IEditable;
import component.entity.PropertiesView;
import component.entity.WFScreenDesigner;
import component.entity.WorkFlow;
import component.view.properties.workflow.WFKeyParameterMappingTab;
import component.view.properties.workflow.WFKeyPkMappingTab;

public class WFScreenMenuItem implements IEditable{
	private String name;
	private String type;
	private String objectQuery;
	private String operation;
	private String mbo;
	private String project;

	private String parametermapping;
//	private String[] parametermapping;
	private String defaultSuccessScreen;
	private String errorScreen;
	private String key;
	private String defaultItem;
	private String screen;
	private String openScreen;
	private String pkMapping;
	private String outputKey;
	
	//ff11.24>>add condition
	private String condition;
	//<<<<<<<<<<<<ff11.24
	//ff12.26>>>>>>>>>>>>>>>>>>
	private String listKey;
	
	public String listKey() {
		return listKey;
	}
	public WFScreenMenuItem setListKey(String str) {
		this.listKey = str;
		return this;
	}
	//<<<<<<<<<<<<<<<<<<ff12.26
	
	//>>>>>>>>>>>>>> flv 09-28  Start<<<<<<<<<<<
	private String submitErrMsg;
	private String timeOut;
	private String onDevCacheTimeOut;
	private String submitConfirmMsg;
	private String resubmitConfirmMsg;
	//>>>>>>>>>>>>>> flv 09-28  End<<<<<<<<<<<
	
	//>>> (flv 10/21 start)  <<<<
	private boolean showCredScreen = true;
	private boolean generateOldVal = true;
	//
	public boolean isGenerateOldVal() {
		return generateOldVal;
	}

	public WFScreenMenuItem setGenerateOldVal(boolean generateOldVal) {
		this.generateOldVal = generateOldVal;
		return this;
	}
	//
	public boolean isShowCredScreen() {
		return showCredScreen;
	}

	public WFScreenMenuItem setShowCredScreen(boolean showCredScreen) {
		this.showCredScreen = showCredScreen;
		return this;
	}
	//>>> (flv 10/21 end)  <<<<
	
	
	//ff10.14>>>>>>>>>
	private boolean invokeParentUpdate;
	private boolean generateErrorScreen;
	private String subject;
	private String from;
	//<<<<<<<ff
	
	public String getProject(){
		return this.project;
	}
	
	public WFScreenMenuItem project(String str){
		this.project = str;
		return this;
	}

	public WFScreenMenuItem name(String string) {
		this.name = string;
		return this;
	}

	public WFScreenMenuItem mbo(String string) {
		this.mbo = string;
		return this;
	}
	
	public WFScreenMenuItem type(String str){
		this.type = str;
		return this;
	}
	
	public WFScreenMenuItem objectQuery(String  str){
		this.objectQuery = str;
		return this;
	}
	
	public WFScreenMenuItem operation(String  str){
		this.operation = str;
		return this;
	}
	
	public WFScreenMenuItem parametermapping(String[]  str){
		this.parametermapping = "";
		for(int i=0;i<str.length;i++){
			this.parametermapping = this.parametermapping + ":"+str[i];
		}
		return this;
	}
	
	public WFScreenMenuItem parametermapping(String str){
		if(this.parametermapping==null){
			this.parametermapping = str;
		}else{
			this.parametermapping = this.parametermapping + ":"+str;
		}
		return this;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getObjectQuery() {
		return objectQuery;
	}

	public String getMbo() {
		return mbo;
	}


	public String getOperation() {
		return operation;
	}

	public WFScreenMenuItem defaultSuccessScreen(String string) {
		this.defaultSuccessScreen = string;
		return this;
	}

	public String[] getParameterMapping() {
		if(parametermapping!=null){
			return parametermapping.split(":");
		}else{
			return new String[]{};
		}
	}

	public String getDefaultSuccessScreen(){
		return this.defaultSuccessScreen;
	}

	@Override
	public void openInPropertiesView() {
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		if(DOF.getWFMenuItemFigure(DOF.getRoot(), name)!=null){
			DOF.getWFMenuItemFigure(DOF.getRoot(), name).click();
		}
		RationalTestScript.sleep(1);
//		PropertiesTabHelper.clickTabName("Parameter Mappings");
	}

	public WFScreenMenuItem key(String string) {
		this.key = string;
		return this;
	}
	
	public String getKey(){
		return this.key;
	}

	public WFScreenMenuItem defaultItem(String string) {
		this.defaultItem = string;
		return this;
	}
	
	public String getDefaultItem(){
		return this.defaultItem;
	}

	public String getScreen() {
		return this.screen;
	}
	
	public String getOpenScreen() {
		return this.openScreen;
	}
	
	public WFScreenMenuItem screen(String str){
		this.screen = str;
		return this;
	}

	public WFScreenMenuItem setErrorScreen(String string) {
		// TODO Auto-generated method stub
		this.errorScreen = string;
		return this;
	}

	public WFScreenMenuItem setOpenScreen(String string) {
		this.openScreen = string;
		return this;
	}

	public WFScreenMenuItem pkMapping(String str) {
		if(this.pkMapping==null){
			this.pkMapping = str;
		}else{
			this.pkMapping = this.pkMapping + ":"+str;
		}
		return this;
	}
	
	public String getPkMapping(){
			return this.pkMapping;
	}
	
	//>>>>>>>>>>>>>> flv 09-28 Submit Error Message Start<<<<<<<<<<<
	public String getSubmitErrMsg() {
		return this.submitErrMsg;
	}
	
	public WFScreenMenuItem submitErrMsg(String string) {
		this.submitErrMsg = string;
		return this;
	}
	//>>>>>>>>>>>>>> flv 09-28 Submit Error Message End<<<<<<<<<<<
	
	//>>>>>>>>>>>>>> flv 09-28  Start<<<<<<<<<<<
	public String getTimeOut() {
		return this.timeOut;
	}
	
	public WFScreenMenuItem timeOut(String string) {
		this.timeOut = string;
		return this;
	}
	public String getOnDevCacheTimeOut() {
		return this.onDevCacheTimeOut;
	}
	
	public WFScreenMenuItem onDevCacheTimeOut(String string) {
		this.onDevCacheTimeOut = string;
		return this;
	}
	
	public WFScreenMenuItem errorScreen(String string) {
		this.errorScreen = string;
		return this;
	}
	
	public String getErrorScreen(){
		return this.errorScreen;
	}
	
	public String getSubmitConfirmMsg() {
		return this.submitConfirmMsg;
	}
	
	public WFScreenMenuItem submitConfirmMsg(String string) {
		this.submitConfirmMsg = string;
		return this;
	}
	
	public String getResubmitConfirmMsg() {
		return this.resubmitConfirmMsg;
	}
	
	public WFScreenMenuItem resubmitConfirmMsg(String string) {
		this.resubmitConfirmMsg = string;
		return this;
	}

	@Override
	public void add() {
		WFScreenDesigner.addMenuItem(name);
		update();
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		PropertiesView.maximize();
		if(getName()!=null){
			PropertiesView.clickTab("General");
			if(DOF.getTextField(DOF.getRoot(), "Name:")!=null){
				WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Name:"), getName());
			}
		}
		if(getKey()!=null){
			PropertiesView.clickTab("General");
			DOF.getTextField(DOF.getRoot(), "Key:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(getKey());
		}
		if(getDefaultItem()!=null){
			PropertiesView.clickTab("General");
			if(getDefaultItem().equals("yes")||getDefaultItem().equals("true")){
				((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(RationalTestScript.SELECTED);
			}else{
				((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(RationalTestScript.NOT_SELECTED);
				
			}
		}
		if(getType()!=null){
			PropertiesView.clickTab("General");
			DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
			DOF.getCCombo(DOF.getRoot(), "Type:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getType()));
			RationalTestScript.sleep(1);
			DOF.getTextField(DOF.getRoot(), "Key:").click();
		}
		if(getScreen()!=null){
			PropertiesView.clickTab("General");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Screen:"), getScreen());
		}
		if(getMbo()!=null){
			PropertiesView.clickTab("General");
			DOF.getButton(DOF.getRoot(), "C&lear").click();
		}
		if(getMbo()!=null){
			PropertiesView.clickTab("General");
			String project = getProject();
			String mbo = getMbo();
			DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
			DOF.getButton(DOF.getRoot(), "&Search...").click();
			SearchForMboDailog.selectMbo(DOF.getDialog("Search For Mobile Business Object"), project, mbo);
		}
		if(getOperation()!=null){
			PropertiesView.clickTab("General");
			DOF.getButton(DOF.getRoot(), "Invoke &operation").click();
			DOF.getCCombo(DOF.getRoot(), "Operation:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getOperation()));
		}
		if(getDefaultSuccessScreen()!=null){
			PropertiesView.clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "Default Success Screen:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getDefaultSuccessScreen()));
			
		}
		if(getObjectQuery()!=null){
			PropertiesView.clickTab("General");
//			DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
			DOF.getButton(DOF.getRoot(), "Invoke object &query").click();
			DOF.getCCombo(DOF.getRoot(), "Object query:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getObjectQuery()));
		}
		if (!isGenerateOldVal()) {
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Generate old &value keys")).clickToState(RationalTestScript.NOT_SELECTED);
		}
		//
		if (!isShowCredScreen()) {
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Show credential screen on authentication failure")).clickToState(RationalTestScript.NOT_SELECTED);
		}
		
		if(getOpenScreen()!=null){
			PropertiesView.clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "Screen:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getOpenScreen()));
		}
		
		if(getDefaultSuccessScreen()!=null){
			PropertiesView.clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "Default Success Screen:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getDefaultSuccessScreen()));
		}
		
		if(getSubmitErrMsg()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit error message:"), getSubmitErrMsg());
		}
		//Time Out
		if(getTimeOut()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Timeout:"), getTimeOut());
		}
		if(getOnDevCacheTimeOut()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "On-device cache timeout:"), getOnDevCacheTimeOut());
		}
		if(getErrorScreen()!=null){
			PropertiesView.clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
			DOF.getPoppedUpList().click(RationalTestScript.atText(getErrorScreen()));
		}
		if(getSubmitConfirmMsg()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit confirmation message:"), getSubmitConfirmMsg());
		}
		if(getResubmitConfirmMsg()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Resubmit confirmation message:"), getResubmitConfirmMsg());
		}
		if(getSubject()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Subject:"), this.subject);
		}
		if(getFrom()!=null){
			PropertiesView.clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "From:"), this.from);
		}
		if(getParameterMapping()!=null){
			PropertiesView.clickTab("Parameter Mappings");
			WFKeyParameterMappingTab.setMapping(getParameterMapping());
		}	
		if(getPkMapping()!=null){
			PropertiesView.clickTab("Personalization Key Mappings");
			WFKeyPkMappingTab.setMapping(getPkMapping());
		}
		
		if(getInvokeParentUpdate()){
			PropertiesView.clickTab("General");
			DOF.getButton(DOF.getRoot(), "Invoke &parent update").click();
		}
		
		if(getGenerateErrorScreen()){
			PropertiesView.clickTab("General");
			DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
		}
		
		PropertiesView.restore();
	}
	
	//>>>>>>>>>>>>>> flv 09-28  End<<<<<<<<<<<
	//ff10.14>>>>>>
	public boolean getInvokeParentUpdate() {
		return this.invokeParentUpdate;
	}
	
	public WFScreenMenuItem invokeParentUpdate(boolean b) {
		this.invokeParentUpdate = b;
		return this;
	}
	
	public boolean getGenerateErrorScreen() {
		return this.generateErrorScreen;
	}
	
	public WFScreenMenuItem generateErrorScreen(boolean b) {
		// TODO Auto-generated method stub
		this.generateErrorScreen = b;
		return this;
	}
	//<<<<<<<<<<ff
//ff11.24>>>>>>>>>>>>>>>>>>>>
	public String getCondition() {
		return condition;
	}

	public WFScreenMenuItem setCondition(String condition) {
		this.condition = condition;
		return this;
	}
	//ff11.24<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public String[] getOutputKey() {
		if(this.outputKey==null){
			return new String[]{};
		}else{
			return this.outputKey.split(":");
		}
	}
	
	public WFScreenMenuItem outputKey(String key){
		if(this.outputKey==null){
			this.outputKey = key;
		}else{
			this.outputKey = this.outputKey + ":"+key;
		}
		return this;
	}
	public WFScreenMenuItem subject(String string) {
		this.subject = string;
		return this;
	}
	
	
	public String getSubject(){
		return this.subject;
	}
	
	public WFScreenMenuItem from(String str){
		this.from = str;
		return this;
	}
	
	public String getFrom(){
		return this.from;
	}
}
