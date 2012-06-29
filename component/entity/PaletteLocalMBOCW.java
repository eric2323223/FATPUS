package component.entity;

import com.sybase.automation.framework.widget.DOF;
import component.entity.model.VerificationCriteria;
import component.entity.wizard.IWizard;
import component.view.Palette;

public class PaletteLocalMBOCW extends LocalMboCreationWizard{

	private boolean canContinue = true;
	
	@Override
	public void start(String parameter) {
		Palette.addLocalMBO(10,10);
	}
	
	public void setName(String s){
		super.setName(s);
	}
	
	public void setAttributes(String s){
		super.setAttributes(s);
	}
	
	public void verifyBannerMessage(VerificationCriteria vc){
		String msg = DOF.getTextFieldByCaretLocation(dialog(), 3, 0).getProperty("text").toString();
		if(msg.equals(vc.getExpected())){
			logInfo("Banner message verification passed");
			this.canContinue = vc.isContinueWhenMatch();
		}else{
			logError("Banner message verification Failed. EXPECTED:["+vc.getExpected()+"] ACTUAL:["+msg+"]");
			this.canContinue = !vc.isContinueWhenMatch();
		}
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setAttributes")){
			return "verifyBannerMessage";
		}
		return null;
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public IWizard canContinue(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

}
