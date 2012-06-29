package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.model.IWizardEntity;
import component.entity.model.LocalMboAttribute;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public abstract class LocalMboCreationWizard extends RationalTestScript implements IWizard{
	protected boolean canContinue = true;
	
	protected TopLevelTestObject dialog(){
		if(DOF.getDialog("New Attributes")==null){
		return DOF.getDialog("New Local Business Object");}
		else{
			return DOF.getDialog("New Attributes");
		}
	}
	
	public void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		sleep(1);
		MainMenu.saveAll();
	}
	
	public void setName(String name){
		DOF.getTextField(dialog(), "Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(name);
	}
	
	public void setAttributes(String text){
		if(text.contains(":")){
			String[] parts = text.split(":");
			for(int i = 0;i<parts.length;i++){
				addSingleAttribute(parts[i], i);
			}
		}else{
			addSingleAttribute(text, 0);
		}
	}
	
	private void addSingleAttribute(String str, int row){
		DOF.getButton(dialog(), "&Add").click();
		GuiSubitemTestObject table = DOF.getTable(dialog());
		LocalMboAttribute att = new LocalMboAttribute(str);
		TableHelper.setTextCellValue(table, row, 1, att.getName());
		TableHelper.setTextCellValue(table, row, 2, att.getType());
		
		
		
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setAttributes")){
			return 1;
		}else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}


	public abstract void start(String parameter) ;
	
	public final void create(IWizardEntity model, WizardRunner wr){
		wr.run(model, this);
	}

}
