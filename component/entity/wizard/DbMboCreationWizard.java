package component.entity.wizard;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.entity.LongOperationMonitor;
import component.entity.WN;
import component.entity.model.DbMbo;
import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DbMboCreationWizard extends RationalTestScript implements IWizard
{
	/**
	 * Script Name   : <b>DbMboCreationWizard</b>
	 * Generated     : <b>Aug 12, 2010 5:23:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/12
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		setAttributeMapping("title,STRING(1),title,STRING(2)");
		System.out.println(getMessageOnAttributeMapping());
		
		
		// Frame: New Attributes
//		text2().click(atPoint(158,7));
	}
	
	private String messageOnAttributeMapping = "";
	private String messageOnLoadParameter = "";
	protected boolean canContinue = true;
	
	public IWizard canContinue(boolean b){
		this.canContinue = b;
		return this;
	}
	
	public String getMessageOnAttributeMapping(){
		return this.messageOnAttributeMapping;
	}
	
	public String getMessageOnLoadParameter(){
		return this.messageOnLoadParameter;
	}

	
	public void setConnectionProfile(String string){
		System.out.println("set connectonProfile to: "+ string);
		DOF.getCombo(dialog(), "Connection profile:").click();
		DOF.getCombo(dialog(), "Connection profile:").click(atText(string));
	}
	
	public void setDataSourceType(String string){
		if(string.equals("NA")){
			DOF.getButton(dialog(), "Bind data source &later").click();
		} else {
			DOF.getCombo(dialog(), "Data source type:").click();
			DOF.getCombo(dialog(), "Data source type:").click(atText(string));
		}
	}
	
	public void setSqlQuery(String string){
		DOF.getButton(dialog(), "SQL quer&y statement").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setProjectName(String string){
		DOF.getTree(dialog()).click(atPath(string));
	}
	
	public void setName(String string){
		TopLevelTestObject dialog = dialog();
		DOF.getTextField(dialog, "Name:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(string);
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#next()
	 */
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog());
//		sleep(3);
	}

	protected TopLevelTestObject dialog() {
		if (DOF.getDialog("New Mobile Business Object") != null) {
			return DOF.getDialog("New Mobile Business Object");
		} else {
			return DOF.getDialog("New Attributes");
		}
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#finish()
	 */
	public void finish(){
		sleep(1);
		DOF.getButton(dialog(), "&Finish").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog());
//		sleep(3);
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public void setStoredProcedure(String text){
		DOF.getButton(dialog(), "St&ored procedure").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(text);
	}
	
	public void setNewFilter(String name){
		DOF.getLabel(dialog(), "Result Set Filters").click();
		DOF.getButton(dialog(), "&Create...").click();
		sleep(1);
		if(DOF.getDialog("Add Java Nature")!=null){
			DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
		}
		TopLevelTestObject dialog = DOF.getDialog("New Java Class");
		DOF.getTextField(dialog, "Name:").click();
		dialog.inputChars(name);
		DOF.getButton(dialog, "&Finish").click();
		sleep(5);
	}
	
	public void setExistingFilter(String name){
		DOF.getLabel(dialog(), "Result Set Filters").click();
		DOF.getButton(dialog(), "&Add...").click();
		sleep(1);
		DOF.getDialog("Select Result Set Filter Class").inputChars(name);
		DOF.getButton(DOF.getDialog("Select Result Set Filter Class"), "OK").click();
		sleep(5);
//		// Frame: New Attributes
//		resultSetFilters().click();
//		_Add().click();
//		
//		// Frame: Select Result Set Filter Class
//		text().click(atPoint(41,14));
//		selectResultSetFilterClass().inputChars("SampleFilger");
//		text().dragToScreenPoint(atPoint(80,9), selectResultSetFilterClass().getScreenPoint(atPoint(3,76)));
//		selectResultSetFilterClass().inputChars("ff");
//		ok().click();
	}
	
	
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#getPageIndexOfOperation(java.lang.String)
	 */
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setProjectName"))
			return 0;
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 1;	
		if(operation.equals("setDataSourceType"))
			return 1;		
		if(operation.equals("setSqlQuery"))
			return 2;
		if(operation.equals("setStoredProcedure"))
			return 2;
		if(operation.equals("setExistingFilter"))
			return 2;
		if(operation.equals("setNewFilter"))
			return 2;
		if(operation.equals("setParameter"))
			return 3;
		if(operation.equals("setRole"))
			return 5;
		if(operation.equals("setAttributeMapping"))
			return 4;
		if(operation.equals("setResultSet"))
			return 4;
		
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}
	
	public void setRole(String str){
		for(String role:str.split(":")){
			setSingleRole(role);
		}
	}
	
	public void setSingleRole(String str){
		GuiSubitemTestObject leftTable = DOF.getTable(dialog(), "Available Roles:");
		GuiSubitemTestObject rightTable = DOF.getTable(dialog(), "Assigned Roles:");
		int row = TableHelper.getRowIndexOfRecordInColumn(leftTable, "Name", str);
		leftTable.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(dialog(), "A&dd >>").click();
	}
	
	public void setResultSet(String str){
		DOF.getSybaseCCombo(dialog()).click();
		DOF.getPoppedUpList().click(atText(str));
	}
	
	public String getDependOperation(String operation){
		if(operation.equals("setName")){
			return "verifyName";
		}
		if(operation.equals("setSqlQuery")){
			return "verifyPreview";
		}
		if(operation.equals("setAttributeMapping")){
			return "verifyAttributeMapping";
		}
		else{
			return null;
		}
	}
	
	public void verifyAttributeMapping(VerificationCriteria vc){
		sleep(2);
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("attributeMapping", this, vc, actual).perform();
	}
	
//	public void verifyName(String expected, boolean isContinueIfMatch){
//		String msg = DOF.getTextFieldByCaretLocation(dialog(), 3, 0).getProperty("text").toString();
//		System.out.println("Expected: ["+msg+"]");
//		if( !vpManual("autoVerification", expected, msg).performTest()){
//			this.canContinue = isContinueIfMatch;
//		}else{
//			this.canContinue = !isContinueIfMatch;
//		}
//	}
	
	public void verifyName(VerificationCriteria vc){
		String msg = DOF.getTextFieldByCaretLocation(dialog(), 3, 0).getProperty("text").toString();
		System.out.println("Expected: ["+vc.getExpected()+"]");
		if(vpManual("autoVerification", vc.getExpected(), msg).performTest()){
			this.canContinue = vc.isContinueWhenMatch();
		}else{
			this.canContinue = !vc.isContinueWhenMatch();
		}
	}
//	
//	public void verifyPreview(String expected){
//		DOF.getButton(dialog(), "Pre&view...").click();
//		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
//		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
//		sleep(3);
//		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"),"Preview Result:"), 0, 1);
//		System.out.println(data);
//		vpManual("preview", expected, data).performTest();
//		DOF.getButton(DOF.getDialog("Preview"), "OK").click();
//	}
	
	public void verifyPreview(VerificationCriteria vc){
		DOF.getButton(dialog(), "Pre&view...").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(3);
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"),"Preview Result:"), 0, 1);
		System.out.println(data);
		boolean result = vpManual("preview", vc.getExpected(), data).performTest();
		DOF.getButton(DOF.getDialog("Preview"), "OK").click();
		if(result){
			this.canContinue = vc.isContinueWhenMatch();
		}else{
			this.canContinue = !vc.isContinueWhenMatch();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#start()
	 */
	public void start(String string){
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
		DOF.getContextMenu().click(atPath("New->Mobile Business Object"));
	}
	
	public void setAttributeMapping(String mapping){
		for(String map:mapping.split(":")){
			setSingleAttributeMapping(map);
		}
	}
	
	public void setSingleAttributeMapping(String mapping){
		String[] map = mapping.split(",");
		String mboName = map[0];
		String mboType = map[1];
		String dsName = map[2];
		String dsType = map[3];
		GuiSubitemTestObject table = attributeMapTable();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", mboName);
		TableHelper.setTextCellValue(table, row, 2, mboType);
		TableHelper.setCComboCellValue(table, row, 4, dsName);
		TableHelper.setTextCellValue(table,  row, 5, dsType);
		table.click(atCell(atRow(1),atColumn(1)));
		this.messageOnAttributeMapping =  DOF.getTextFieldByAncestorLine(dialog(), "Composite->Composite->Composite->Composite->Composite->Composite->Composite->Shell->Shell")
												.getProperty("text").toString();
		
	}
	
	public void setParameter(String string){
		for(String p:string.split(":")){
			setSingleParameter(p);
		}
	}
	
	protected void setSingleParameter(String str){
		String[] parameter = str.split(",");
		String parameterName = parameter[0];
		String parameterType = parameter[1];
		String defaultValue = parameter[2];
//		GuiSubitemTestObject tree = DOF.getTree(dialog());
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(dialog()));
//		GuiSubitemTestObject tree = DOF.getDualHeadersTree(dialog());
		TreeHelper.setTextCellValue(tree, parameterName, 1, parameterType);
		TreeHelper.setTextCellValue(tree, parameterName, 4, parameterType);
		if(parameterType.equalsIgnoreCase("date")){
			TreeHelper.setDateCellValue(tree, parameterName, "Default Value", defaultValue);
		}else{
			TreeHelper.setTextCellValue(tree, parameterName, "Default Value", defaultValue);
		}
		tree.click(atCell(atRow(atPath(parameterName)),atColumn("Name")));
	}
	
	private GuiSubitemTestObject attributeMapTable(){
//		return DOF.getTable(DOF.getDualHeadersTable(dialog()));
		return DOF.getTableByColumnsNames(dialog(),new String[]{"","Name","Datatype","Nullable","Map to","Datatype","Nullable"});
	}

	/* (non-Javadoc)
	 * @see component.wizard.IWizard#create(component.entity.model.DbMbo, component.entity.model.WizardRunner)
	 */
	public void create(DbMbo mbo, WizardRunner wr) {
		// TODO Auto-generated method stub
		wr.run(mbo, this);
	}


//	public void fromPageToPage(DbMbo mbo, WizardRunner wr,int from, int to) {
//		// TODO Auto-generated method stub
//		wr.run(mbo, this, from, to);
//	}
	
	public String preview(int row, int column){
		DOF.getButton(dialog(), "Pre&view...").click();
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		TestObject table = DOF.getTable(DOF.getDialog("Preview"),"Preview Result:");
		return TableHelper.getCellValue((GuiSubitemTestObject) table,1,1);
	}

	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		create((DbMbo)entity, runner);
	}


}

