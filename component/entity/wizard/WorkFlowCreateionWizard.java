package component.entity.wizard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TextFieldHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.SearchForMboDailog;
import component.entity.ACW;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.VerificationCriteria;
import component.entity.model.WFParameterMatchingRule;
import component.entity.verifier.Verifier;

public class WorkFlowCreateionWizard extends ACW {
	private String project;

	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New");
	}

	@Override
	public void start(String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
//		DOF.getContextMenu().click(atPath("New->Mobile Workflow Forms Editor"));
		//ffan modify in sup2.2drop2  20120614>>>>>>>>
		DOF.getContextMenu().click(atPath("New->Hybrid App Designer"));
	}
	
	public void setName(String str){
		DOF.getTextField(dialog(),"File name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setParentFolder(String str){
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Enter or select the parent folder:"), str);
	}
	
	public void setProject(String str){
		project = str;
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setParentFolder")){
			return 0;
		}
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setOption")){
			return 1;
		}
		if(operation.equals("setProject")){
			return 2;
		}
		if(operation.equals("setMbo")){
			return 2;
		}
		if(operation.equals("setObjectQuery")){
			return 2;
		}
		if(operation.equals("setFrom")){
			return 3;
		}
		if(operation.equals("setReceived")){
			return 3;
		}
		if(operation.equals("setSubject")){
			return 3;
		}
		if(operation.equals("setTo")){
			return 3;
		}
		if(operation.equals("setCc")){
			return 3;
		}
		if(operation.equals("setBody")){
			return 3;
		}
		if(operation.equals("setFromMatchingRule")){
			return 4;
		}
		if(operation.equals("setSubjectMatchingRule")){
			return 4;
		}
		if(operation.equals("setToMatchingRule")){
			return 4;
		}
		if(operation.equals("setCcMatchingRule")){
			return 4;
		}
		if(operation.equals("setBodyMatchingRule")){
			return 4;
		}
		if(operation.equals("setParameterValue")){
			return 5;
		}
		if(operation.equals("setParameterMatchingRule")){
			return 5;
		}
		throw new RuntimeException("Unknown operaton: "+ operation);
	}

	public void setOption(String str){
		if(str.contains(WorkFlow.SP_CLIENT_INIT)){
			((ToggleGUITestObject)DOF.getButton(dialog(),"Can be &started, on demand, from the client.")).clickToState(SELECTED);
		}
		if(str.contains(WorkFlow.SP_ACTIVATE)){
			((ToggleGUITestObject)DOF.getButton(dialog(),"Has setup options that need to be set when the client application is first &activated.")).clickToState(SELECTED);
		}
		if(str.contains(WorkFlow.SP_SERVER_INIT)){
			((ToggleGUITestObject)DOF.getButton(dialog(),"Responds to server-&driven notifications.")).clickToState(SELECTED);
		}
		if(str.contains(WorkFlow.SP_CREDENTIAL_REQUEST)){
			((ToggleGUITestObject)DOF.getButton(dialog(),"&Credentials (authentication) may be requested dynamically from the client application.")).clickToState(SELECTED);
		}
		
	}

	//ff>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void setMbo(String str){
//		String project = str.split("/")[0];
//		String mbo = str.split("/")[1];
		DOF.getButton(dialog(), "&Search...").click();
		sleep(1);
		SearchForMboDailog.selectMbo(DOF.getDialog("Search For Mobile Business Object"), project, str);
	}
	
	public boolean setObjectQuery(String str){
		DOF.getCCombo(dialog(), "Object query:").click();
		sleep(1);
		Object hasoq = ListHelper.hasItem(DOF.getCCombo(dialog(), "Object query:"), str);
//		System.out.println("ifhasObjectQuery:"+ListHelper.hasItem(DOF.getCCombo(dialog(), "Object query:"), str));
		if(hasoq.equals(true)){
//			DOF.getCCombo(DOF.getRoot(), "Object query:").click();
//			sleep(0.5);
//			DOF.getPoppedUpList().click(atText(str));
			WO.setCCombo(DOF.getCCombo(dialog(), "Object query:"), str);
			return true;
		}
		else
			return false;
	}
	
	public void setFrom(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getTextField(dialog(),"From:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else{
		setValue(DOF.getTextField(dialog(), "From:"), str);
		}
	}
	
	public void setReceived(String str){
		setValue(DOF.getTextField(dialog(), "Received:"), str);
	}
	
	public void setSubject(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getTextField(dialog(),"Subject:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else{
		setValue(DOF.getTextField(dialog(), "Subject:"), str);
		}
//		if(!str.contains("|")){
//			DOF.getTextField(dialog(),"Subject:").click();
//			dialog().inputKeys(SpecialKeys.CLEARALL);
//			dialog().inputChars(str);
//		}else{
//			DOF.getStyledTextHasLabel(dialog(),"Subject:").click();
//			TextFieldHelper.hightLightText(dialog(),DOF.getStyledTextHasLabel(dialog(),"Subject:"), matchcontext);
//			DOF.getStyledTextHasLabel(dialog(),"Subject:").click(RIGHT);
//			DOF.getContextMenu().click(atPath("Select as Matching Rule"));
//			
//		}
	}
	
	public void setTo(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getTextField(dialog(),"To:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else{ 
		setValue(DOF.getTextField(dialog(), "To:"), str);
		}
	}
	
	public void setCc(String str){
		setValue(DOF.getTextField(dialog(), "cc:"), str);
	}
	
	//ff20120207>>>>>>>>>>>
	public void setBody(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getTextField(dialog(),"Body:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else if(str.contains("\'")){
			setMulLineValues(DOF.getTextField(dialog(), "Body:"), str);
		}else{
			DOF.getTextField(dialog(),"Body:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str);
		}
	}
	private void setMulLineValues(GuiTestObject to, String str){
		to.click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		if(str.contains("\'")){
			for(int i= 0;i<str.split("\'").length;i++){
				dialog().inputChars(str.split("\'")[i]);
				dialog().inputKeys(SpecialKeys.ENTER);
			}
		}
	}
	//ff20120207<<<<<<<<<<<<<<<<
	
	private void setMatchingRule(TextScrollTestObject to, String rule){
		to.click();
		TextFieldHelper.hightLightText(dialog(),to, rule);
		to.click(RIGHT);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
		
	}
	
	private void setValue(GuiTestObject to, String str){
		to.click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setFromMatchingRule(String str){
		setMatchingRule(DOF.getStyledTextHasLabel(dialog(), "From:"), str);
	}
	
	public void setSubjectMatchingRule(String str){
		setMatchingRule(DOF.getStyledTextHasLabel(dialog(), "Subject:"), str);
	}
	
	public void setToMatchingRule(String str){
		setMatchingRule(DOF.getStyledTextHasLabel(dialog(), "To:"), str);
	}
	
	public void setCcMatchingRule(String str){
		setMatchingRule(DOF.getStyledTextHasLabel(dialog(), "cc:"), str);
	}
	
	public void setBodyMatchingRule(String str){
		setMatchingRule(DOF.getStyledTextHasLabel(dialog(), "Body:"), str);
	}
	
	public void finish(){
		DOF.getButton(dialog(), "&Finish").click();
		sleep(3);
	}
	
	public void setParameterMatchingRule(String str){
		for(String rule:str.split(":")){
			setSingleParameterMatchingRule(rule);
		}
	}
	
	private void setSingleParameterMatchingRule(String str){
		WFParameterMatchingRule rule = new WFParameterMatchingRule(str);
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Key", rule.getName());
		table.click(atCell(atRow(row), atColumn("Key")));
		sleep(0.5);
		WO.setCombo(DOF.getCombo(dialog(), "Field:"), rule.getField());
		sleep(0.5);
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Start tag:"), rule.getStartTag());
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "End tag:"), rule.getEndTag());
	}
	
	public void setParameterValue(String str){
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Key", str.split(",")[0]);
		table.click(atCell(atRow(row), atColumn("Key")));
		sleep(0.5);
		DOF.getCombo(dialog(), "Field:").click();
		DOF.getCombo(dialog(), "Field:").click(atText(str.split(",")[1]));
		sleep(0.5);
		//ff>>>>>>>>>>>>>>>10.20>>>>>>change code:
		if(str.split(",")[2]!=""){
			DOF.getTextField(dialog(),"Start tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[2]);
		}
		
		if(str.split(",").length >3){
			DOF.getTextField(dialog(),"End tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[3]);
		}
		
		if(str.split(",").length >4){
			DOF.getTextField(dialog(),"Format:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[4]);
		
		}
//		DOF.getButton(dialog(), "&Finish").click();
	}
	//ff10.18<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void verifySubject(VerificationCriteria vc){
		String actual=new WFJumpStartCreateionWizard().getValueInMatchingRulesTable(dialog());
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	
	public void verifyBody(VerificationCriteria vc){
		String actual=new WFJumpStartCreateionWizard().getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setSubjectMatchingRule"))
			return "verifySubject";
		if(operation.equals("setBodyMatchingRule"))
			return "verifyBody";
		return null;
	}

	public String getValueInMatchingRulesTable(){
		String[] type = TableHelper.getColumnData(DOF.getTable(dialog()), "Matching Rule Type");
		String[] content = TableHelper.getColumnData(DOF.getTable(dialog()), "Matching Rule Content");
//		StartPoint sp = new StartPoint();
		String data = null;
		for(int i=0;i<type.length;i++){
			data = type[i]+","+content[i];
		}
		
		return data;
	}
	//ff10.18<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	
}
