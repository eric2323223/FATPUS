package component.entity.wizard;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.ACW;
import component.entity.WFFlowDesigner;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;

public class SendNotificationWizard extends ACW {
	private boolean hasGetUsers = false;

	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Send Notification To A Device User");
	}
	
	private String arrayToString(String[] array){
		String result = "";
		for(String str:array){
			result = result + str + ",";
		}
		return result.substring(0, result.length()-1);
	}
	
	private void clickBody(){
		DOF.getTextField(dialog(), "Body:").click();
	}
	
	private void getDeviceUsers(){
		if(!hasGetUsers ){
			DOF.getButton(dialog(), "&Get Device Users").click();
			hasGetUsers = true;
		}
	}
	
	public void setCc(String str){
		WO.setTextField(dialog(), DOF.getCombo(dialog(), "Cc:"), str);
		clickBody();
	}
	
	public void setSelectCc(String str){
		getDeviceUsers();
		WO.setCombo(DOF.getCombo(dialog(), "Cc:"), str);
	}
	
	public void verifySelectCc(VerificationCriteria vc){
		String[] data = ListHelper.getItems(DOF.getCombo(dialog(), "Cc:"));
		String actual  = arrayToString(data);
		Verifier.verifyEquals("cc", this, vc, actual).perform();
	}
	
	public void setFrom(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getCombo(dialog(), "From:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else{
		WO.setTextField(dialog(), DOF.getCombo(dialog(), "From:"), str);
		}
		DOF.getCombo(dialog(), "To:").click();
	}
	
	public void setSelectFrom(String str){
		getDeviceUsers();
		WO.setCombo(DOF.getCombo(dialog(), "From:"), str);
	}
	
	public void verifySelectFrom(VerificationCriteria vc){
		String[] data = ListHelper.getItems(DOF.getCombo(dialog(), "From:"));
		String actual  = arrayToString(data);
		Verifier.verifyEquals("from", this, vc, actual).perform();
	}
	
	public void setBcc(String str){
		WO.setTextField(dialog(), DOF.getCombo(dialog(), "Bcc:"), str);
		clickBody();
	}
	
	public void setSelectBcc(String str){
		getDeviceUsers();
		WO.setCombo(DOF.getCombo(dialog(), "Bcc:"), str);
	}
	
	public void verifySelectBcc(VerificationCriteria vc){
		String[] data = ListHelper.getItems(DOF.getCombo(dialog(), "Bcc:"));
		String actual  = arrayToString(data);
		Verifier.verifyEquals("bcc", this, vc, actual).perform();
	}
	
	public void setTo(String str){
		//ffan add ,used to input chinese
		if(str.contains("CH")){
			DOF.getCombo(dialog(),"To:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(str.split(",")[1]);  
	        clb.setContents(text, null);  
	        dialog().inputKeys("^v");
		}
//		<<<<<<<<<<<<<<<<>
		else{
		WO.setTextField(dialog(), DOF.getCombo(dialog(), "To:"), str);
		}
		clickBody();
	}
	
	public void setSelectTo(String str){
		getDeviceUsers();
		WO.setCombo(DOF.getCombo(dialog(), "To:"), str);
	}
	
	public void verifySelectTo(VerificationCriteria vc){
		String[] data = ListHelper.getItems(DOF.getCombo(dialog(), "To:"));
		String actual  = arrayToString(data);
		Verifier.verifyEquals("to", this, vc, actual).perform();
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
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Subject:"), str);
		}
	}
	
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
		else{
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Body:"), str);
		}
	}
	
	public void setUnwiredServer(String str){
		DOF.getCombo(dialog(), "Unwired Server profile:").click();
		DOF.getCombo(dialog(), "Unwired Server profile:").click(atText(str));
	}

	@Override
	public void start(String string) {
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Send a notification..."));
	}
	
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setUnwiredServer"))
			return 0;
		if(operation.equals("setFrom"))
			return 0;
		if(operation.equals("setSelectFrom"))
			return 0;
		if(operation.equals("setCc"))
			return 0;
		if(operation.equals("setSelectCc"))
			return 0;
		if(operation.equals("setBcc"))
			return 0;
		if(operation.equals("setSelectBcc"))
			return 0;
		if(operation.equals("setTo"))
			return 0;
		if(operation.equals("setSelectTo"))
			return 0;
		if(operation.equals("setSubject"))
			return 0;
		if(operation.equals("setBody"))
			return 0;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setSelectFrom")){
			return "verifySelectFrom";
		}
		if(operation.equals("setSelectTo")){
			return "verifySelectTo";
		}
		if(operation.equals("setSelectCc")){
			return "verifySelectTo";
		}
		if(operation.equals("setSelectBcc")){
			return "verifySelectBcc";
		}
		if(operation.equals("setUnwiredServer")){
			return "verifyUnwiredServer";
		}
		if(operation.equals("setFrom")){
			return "verifyMessage";
		}
		if(operation.equals("setTo")){
			return "verifyMessage";
		}
		if(operation.equals("setCc")){
			return "verifyMessage";
		}
		if(operation.equals("setBcc")){
			return "verifyMessage";
		}
		return null;
	}
	
	public void verifyUnwiredServer(VerificationCriteria vc){
		DOF.getCombo(dialog(), "Unwired Server profile:").click();
		String[] data = ListHelper.getItems(DOF.getCombo(dialog(), "Unwired Server profile:"));
		String actual  = arrayToString(data);
		Verifier.verifyEquals("unwiredServer", this, vc, actual).perform();
	}
	
	public void verifyMessage(VerificationCriteria vc){
		sleep(1);
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("message", this, vc, actual).perform();
	}
	
	public void finish(){
		sleep(1);
		DOF.getButton(dialog(), "&Send").click();
		sleep(2);
		if(dialog()!=null){
			DOF.getButton(dialog(), "&Send").click();
			
		}
	}
	
}
