package component.entity.wizard;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TextFieldHelper;
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;



public class WFJumpStartCreateionWizard extends WorkFlowCreateionWizard {
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Notification Processing Wizard");
		}
	
	public static void clickTab(String tab){
		PropertiesTabHelper.clickTabName(tab);
	}
	
	@Override
	public void start(String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "Jumpstart...").click();
	}
	//ff>>>>>>>>>>>>10.18 add some function:
	@Override
	public int getPageIndexOfOperation(String operation) {
		// TODO Auto-generated method stub
		if(operation.equals("setMbo")){
			return 0;
		}
		if(operation.equals("setObjectQuery")){
			return 0;
		}
		if(operation.equals("setFrom")){
			return 1;
		}
		if(operation.equals("setReceived")){
			return 1;
		}
		if(operation.equals("setTo")){
			return 1;
		}
		if(operation.equals("setCc")){
			return 1;
		}
		if(operation.equals("setSubject")){
			return 1;
		}
		if(operation.equals("setBody")){
			return 1;
		}
		if(operation.equals("setFromMatchingRule")){
			return 2;
		}
		if(operation.equals("setBodyMatchingRule")){
			return 2;
		}
		if(operation.equals("setToMatchingRule")){
			return 2;
		}
		if(operation.equals("setCcMatchingRule")){
			return 2;
		}
		if(operation.equals("setSubjectMatchingRule")){
			return 2;
		}
		if(operation.equals("setParameterValue")){
			return 3;
		}
		throw new RuntimeException("Unknown operaton: "+ operation);
	}
	//ff<<<<<<<<<<<<<10.18 add some function:
	
	public void setMbo(String str){
		DOF.getButton(dialog(), "&Search...").click();
		TopLevelTestObject dialogMbo = DOF.getDialog("Search For Mobile Business Object");
		DOF.getButton(dialogMbo, "&Search").click();
		sleep(1);
		GuiSubitemTestObject table = DOF.getTable(dialogMbo);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", str);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(dialogMbo, "OK").click();
	}
	
	//ff10.18>>>>>>>>>>>>>>>>>
//	public boolean setObjectQuery(String str){
//		DOF.getCCombo(dialog(), "Object query:").click();
//		sleep(1);
//		DOF.getPoppedUpList().click(atText(str));
//		sleep(1);
//		return true;
//	}
	
	public boolean setObjectQuery(String str){
		DOF.getCCombo(dialog(), "Object query:").click();
		sleep(1);
		Object hasoq = ListHelper.hasItem(DOF.getCCombo(dialog(), "Object query:"), str);
//		System.out.println("ifhasObjectQuery:"+ListHelper.hasItem(DOF.getCCombo(dialog(), "Object query:"), str));
		if(hasoq.equals(true)){
//			DOF.getCCombo(DOF.getRoot(), "Object query:").click();
			sleep(1);
//			DOF.getPoppedUpList().click(atText(str));
			WO.setCCombo(DOF.getCCombo(dialog(), "Object query:"), str);
			sleep(1);
			return true;
		}
		else
			return false;
	}
	
	//ff10.18<<<<<<<<<<<<<<<<<<<<<<<<
	
	//ff10.18>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void setFrom(String str){
		DOF.getTextField(dialog(),"From:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setTo(String str){
		DOF.getTextField(dialog(),"To:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setCc(String str){
		DOF.getTextField(dialog(),"cc:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	//Modify ff11.28>>>>>>>>>>>
	public void setBody(String str){
		//Used to input mulitiple lines value
		if(str.contains("\'"))
			setMulLineValues(DOF.getTextField(dialog(), "Body:"), str);
		else{
			DOF.getTextField(dialog(),"Body:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str);
		}
	}
	//modify ff11.28<<<<<<<<<<<<
	public void setReceived(String str){
		DOF.getTextField(dialog(),"Received:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setFromMatchingRule(String matchcontext){
		DOF.getStyledTextHasLabel(dialog(),"From:").click();
		TextFieldHelper.hightLightText(dialog(), DOF.getStyledTextHasLabel(dialog(),"From:"), matchcontext);
		DOF.getStyledTextHasLabel(dialog(),"From:").click(RIGHT);
		sleep(2);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
	}
	
	public void setToMatchingRule(String matchcontext){
		DOF.getStyledTextHasLabel(dialog(),"To:").click();
		TextFieldHelper.hightLightText(dialog(), DOF.getStyledTextHasLabel(dialog(),"To:"), matchcontext);
		DOF.getStyledTextHasLabel(dialog(),"To:").click(RIGHT);
		sleep(2);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
	}
	
	public void setCcMatchingRule(String matchcontext){
		DOF.getStyledTextHasLabel(dialog(),"cc:").click();
		TextFieldHelper.hightLightText(dialog(), DOF.getStyledTextHasLabel(dialog(),"cc:"), matchcontext);
		DOF.getStyledTextHasLabel(dialog(),"cc:").click(RIGHT);
		sleep(2);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
	}
	
	public void setBodyMatchingRule(String matchcontext){
		DOF.getStyledTextHasLabel(dialog(),"Body:").click();
		TextFieldHelper.hightLightText(dialog(), DOF.getStyledTextHasLabel(dialog(),"Body:"), matchcontext);
		DOF.getStyledTextHasLabel(dialog(),"Body:").click(RIGHT);
		sleep(2);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
	}
	//ff10.18<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public void setSubject(String str){
		DOF.getTextField(dialog(),"Subject:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	public void setSubjectMatchingRule(String matchcontext){
		DOF.getStyledTextHasLabel(dialog(),"Subject:").click();
		TextFieldHelper.hightLightText(dialog(), DOF.getStyledTextHasLabel(dialog(),"Subject:"), matchcontext);
		DOF.getStyledTextHasLabel(dialog(),"Subject:").click(RIGHT);
		sleep(2);
		DOF.getContextMenu().click(atPath("Select as Matching Rule"));
	}
	
//	public void setParameterValue(String str){
//		GuiSubitemTestObject table = DOF.getTable(dialog());
//		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Key", str.split(",")[0]);
//		table.click(atCell(atRow(row), atColumn("Key")));
//		sleep(0.5);
//		DOF.getCombo(dialog(), "Field:").click();
//		DOF.getCombo(dialog(), "Field:").click(atText(str.split(",")[1]));
//		sleep(0.5);
//		DOF.getTextField(dialog(),"Start tag:").click();
//		dialog().inputKeys(SpecialKeys.CLEARALL);
//		dialog().inputChars(str.split(",")[2]);
//		System.out.print("finissh.................");
////		DOF.getButton(dialog(), "&Finish").click();
//	}
	
	public void setParameterValue(String str){
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int tableLength = TableHelper.getRowCount(table);
		System.out.println("table Length="+tableLength);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Key", str.split(",")[0]);
		if(tableLength>1){
		//MAX dialog to show KeyTable
			DOF.getDialog("Notification Processing Wizard").inputKeys("% %x");
			table.click(atCell(atRow(row), atColumn("Key")));
			sleep(0.5);
		//restore dialog 
			DOF.getDialog("Notification Processing Wizard").inputKeys("% % % % % % % %{ENTER}");
		}else
			table.click(atCell(atRow(row), atColumn("Key")));
	
		sleep(0.5);
		
		if(str.split(",")[1]!=null){
			DOF.getCombo(dialog(), "Field:").click();
			DOF.getCombo(dialog(), "Field:").click(atText(str.split(",")[1]));
			sleep(0.5);
		}
		if(str.split(",")[2]!=null){
			DOF.getTextField(dialog(),"Start tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[2]);
		}
		System.out.print("str.split(\",\").length"+str.split(",").length);
		if(str.split(",").length>3){
			DOF.getTextField(dialog(),"End tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[3]);
			sleep(0.5);
		}
		
		if(str.split(",").length>4){
			DOF.getTextField(dialog(),"Format:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			
			if(!str.split(",")[4].equals("empty"))
				dialog().inputChars(str.split(",")[4]);
		
			sleep(0.5);
		}
		System.out.print("finissh.................");
//		DOF.getButton(dialog(), "&Finish").click();
	}
	
//ff>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>10.18
	
	//get the value of the matchingRules to verify when set matching rule for Subject in JumpStart
	public void verifySubject(VerificationCriteria vc){
		String actual= getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	
	public void verifyBody(VerificationCriteria vc){
		String actual= getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	public void verifyCc(VerificationCriteria vc){
		String actual= getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	
	public void verifyTo(VerificationCriteria vc){
		String actual= getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	public void verifyFrom(VerificationCriteria vc){
		String actual= getValueInMatchingRulesTable();
		Verifier.verifyEquals("vpName", this, vc, actual).perform();
	}
	
	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setSubjectMatchingRule"))
			return "verifySubject";
		if(operation.equals("setBodyMatchingRule"))
			return "verifyBody";
		if(operation.equals("setFromMatchingRule"))
			return "verifyFrom";
		if(operation.equals("setToMatchingRule"))
			return "verifyTo";
		if(operation.equals("setCcMatchingRule"))
			return "verifyCc";
		return null;
	}
	
	//get the value of the matchingRules to verify when JumpStart
	public String getValueInMatchingRulesTable(TopLevelTestObject parent){
		String[] type = TableHelper.getColumnData(DOF.getTable(parent), "Matching Rule Type");
		String[] content = TableHelper.getColumnData(DOF.getTable(parent), "Matching Rule Content");
		String data ="";
		for(int i=0;i<type.length;i++){
			data += type[i]+","+content[i];
			System.out.println("data ="+data);
			
		}
		return data;
	}
	//ff10.18<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	//ff11.28>>>>>>>>>>>>>>>
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
	//ff11.28<<<<<<<<<<<<<<<<<<<<
}
