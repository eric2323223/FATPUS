package component.dialog;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.model.Email;

public class NotificationDialog extends RationalTestScript{
	
	static TopLevelTestObject dialog(){
		return DOF.getDialog("Send Notification To A Device User");
	}
	
	private static void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	private static String checkMessage(){
		sleep(0.5);
		String msg = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		if(msg.trim().equals("The Cc and Bcc fields are not sent in the notification.  These fields are used for matching rules only.")){
			return "";
		}else{
			return msg;
		}
	}
	
	private static String setTo(Email mail){
		if(mail.getTo()!=null){
			WO.setTextField(dialog(), DOF.getCombo(dialog(), "To:"), mail.getTo());
		}
		if(mail.getSelectTo()!=null){
			WO.setCombo(DOF.getCombo(dialog(), "To:"), mail.getSelectTo());
		}
		DOF.getTextField(dialog(), "Body:").click();
		sleep(1);
		return checkMessage();
	}
	
	private static String setCc(Email mail){
		if(mail.getCc()!=null){
			WO.setTextField(dialog(), DOF.getCombo(dialog(), "Cc:"), mail.getCc());
		}
		if(mail.getSelectCc()!=null){
			WO.setCombo(DOF.getCombo(dialog(), "Cc:"), mail.getSelectCc());
		}
		DOF.getTextField(dialog(), "Body:").click();
		sleep(1);
		return checkMessage();
	}
	
	private static String setBcc(Email mail){
		if(mail.getBcc()!=null){
			WO.setTextField(dialog(), DOF.getCombo(dialog(), "Bcc:"), mail.getBcc());
		}
		if(mail.getSelectBcc()!=null){
			WO.setCombo(DOF.getCombo(dialog(), "Bcc:"), mail.getSelectBcc());
		}
		DOF.getTextField(dialog(), "Body:").click();
		sleep(1);
		return checkMessage();
	}
	
	private static String setFrom(Email mail){
		if(mail.getFrom()!=null){
			WO.setTextField(dialog(), DOF.getCombo(dialog(), "From:"), mail.getFrom());
		}
		if(mail.getSelectFrom()!=null){
			WO.setCombo(DOF.getCombo(dialog(), "From:"), mail.getSelectFrom());
		}
		DOF.getTextField(dialog(), "Body:").click();
		sleep(1);
		return checkMessage();
	}
	
	public static String send(Email email){
		String msg;
		DOF.getCombo(dialog(), "Unwired Server profile:").click();
		DOF.getCombo(dialog(), "Unwired Server profile:").click(atText(email.getUnwiredServer()));
//		if(email.getAutoGetDeviceUser()!=null){
//			DOF.getButton(dialog(), "&Get Device Users").click();
//			sleep(2);
//		}
		msg = setTo(email);
		if(!msg.equals("")){
			cancel();
			return msg;
		}
		msg = setCc(email);
		if(!msg.equals("")){
			cancel();
			return msg;
		}
		msg = setBcc(email);
		if(!msg.equals("")){
			cancel();
			return msg;
		}
		msg = setFrom(email);
		if(!msg.equals("")){
			cancel();
			return msg;
		}

		if(email.getSubject()!= null){
			DOF.getTextField(dialog(),"Subject:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(email.getSubject());
			msg = checkMessage();
			if(!msg.equals("")){
				cancel();
				return msg;
			}
		}
	//Modify by ff11.28>>>>>>>>>>>>>>
		if(email.getBody()!= null){
			if(email.getBody().contains("\'"))
				setMulLineValues(DOF.getTextField(dialog(), "Body:"),email.getBody());	
			else{
				DOF.getTextField(dialog(),"Body:").click();
				dialog().inputKeys(SpecialKeys.CLEARALL);
				dialog().inputChars(email.getBody());
			}
			msg = checkMessage();
			if(!msg.equals("")){
				cancel();
				return msg;
			}
		}
	//<<<<<<<<<<<<<<<<<<ff11.28
		DOF.getButton(dialog(), "&Send").click();
		sleep(2);
		if(dialog()!=null){
			msg = checkMessage();
			cancel();
			return msg;
		}else{
			return "";
		}
	}
	
	//ff11.28>>>>>>>>>>>>>>>
	private static void setMulLineValues(GuiTestObject to, String str){
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
