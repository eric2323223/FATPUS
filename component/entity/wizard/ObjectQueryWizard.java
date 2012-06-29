package component.entity.wizard;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.entity.EE;
import component.entity.WN;
import component.entity.model.IWizardEntity;
import component.entity.model.ObjectQuery;
import component.entity.model.ObjectQueryParameter;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ObjectQueryWizard extends RationalTestScript implements IWizard
{
	/**
	 * Script Name   : <b>NewObjectQueryDialog</b>
	 * Generated     : <b>Sep 3, 2010 4:02:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/09/03
	 * @author eric
	 */
	public static final String RT_SINGLE = "SINGLE";
	public static final String RT_MULTIPLE = "MULTIPLE";
	public static final String RT_RESULTSET = "RESULTSET";
	private boolean canContinue = true;
	private String warningVC;
	
	public void testMain(Object[] args) 
	{
		start(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->states (dba)"));
	}
	
	private TopLevelTestObject dialog(){
		return DOF.getDialog("New Object Query");
	}

	@Override
	public void finish() {
		GuiTestObject button = DOF.getButton(dialog(), "&Finish");
		if(!button.invoke("isEnabled").toString().equalsIgnoreCase("true")){
			DOF.getButton(dialog(), "&Generate").click();
		}
		sleep(2);
		button.click();
		sleep(1);
		if(warningVC!=null){
			if( DOF.getDialog("Information")!=null){
				DOF.getButton(DOF.getDialog("Information"), "OK").click();
			}else{
				logError("Auto change verification failure");
			}
		}
		DOF.getMenu().click(atPath("File->Save All"));
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		// all methods are in one page
		return 0;
	}
	
	public void setQueryDefinition(String string){
		DOF.getTextField(DOF.getGroup(dialog(), "&Query Definition")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setReturnType(String str){
		if(str.equals(RT_SINGLE)){
			DOF.getButton(dialog(), "Return a &single object").click();
		}
		if(str.endsWith(RT_MULTIPLE)){
			DOF.getButton(dialog(), "&Return multiple objects").click();
		}
		if(str.equals(RT_RESULTSET)){
			DOF.getButton(dialog(), "R&eturn a result set").click();
		}
	}
	
	public void setName(String str){
		DOF.getTextField(dialog(),"Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setParameter(String str){
		if(str.contains(":")){
			String[] paras = str.split(":");
			for(String para : paras){
				addSingleParameter(para);
			}
		}else{
			addSingleParameter(str);
		}
		//ff used to auto-Generate defination of objectQuery:
		sleep(1);
		System.out.println("start click..");
		DOF.getButton(dialog(), "&Generate").click();
		sleep(1);

	}
	
	private void addSingleParameter(String str){
		DOF.getButton(dialog(), "&Add").click();
		ObjectQueryParameter para = new ObjectQueryParameter(str);
		GuiSubitemTestObject table = DOF.getTable(dialog(),"Parameters:");
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "parameter1");
		TableHelper.setTextCellValue(table, row, "Name", para.getName());
		TableHelper.setTextCellValue(table, row, "Datatype", para.getType());
		TableHelper.setCComboCellValue(table, row, "Mapped to", para.getMapTo());
	}

	@Override
	public void next() {
		//only one page
	}
	
	public void setCreateIndex(String str){
		ToggleTestObject button = (ToggleTestObject)DOF.getButton(dialog(), "&Create an index");
		if(str.equalsIgnoreCase("true")){
			button.setState(SELECTED);
		}else{
			button.setState(NOT_SELECTED);
		}
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().doubleClick(atPath(WN.projectNameWithVersion(parameter)));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesTabHelper.clickTabName("Attributes");
//		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		DOF.getButton(DOF.getRoot(), "&Add...").click();
	}
	
	public void create(ObjectQuery oq, WizardRunner wr){
		wr.run(oq, this);
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	//just a trigger
	public void setWarning(String str){

	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		this.create((ObjectQuery)entity, runner);
		
	}
	
	public void setVerifyAutoChangeWarning(String vc){
		this.warningVC = vc;
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
