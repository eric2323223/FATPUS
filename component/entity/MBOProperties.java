package component.entity;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestDataTable;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TabFolderHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.dialog.ComplexTypeDefaultValueDialog;
import component.dialog.EditRelationshipDialog;
import component.dialog.OperationEditDialog;
import component.dialog.OperationPropertiesDialog;
import component.dialog.PreviewDialog;
import component.entity.model.IWizardEntity;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;
import component.entity.model.LocalMboAttribute;
import component.entity.model.MBOAttribute;
import component.entity.model.ObjectQuery;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.Relationship;
import component.entity.model.RestWSMbo;
import component.entity.model.RestWSOperation;
import component.entity.model.ResultFilter;
import component.entity.model.SynchronizationParameter;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;
import component.view.properties.attributes.AttributeMappingTab;
import component.view.properties.attributes.DataSourceTab;
import component.view.properties.attributes.LoadArgumentTab;
import component.view.properties.attributes.LoadParameterTab;
import component.view.properties.attributes.ObjectQueryTab;
import component.wizard.page.RestWSDefinitionPage;
import component.wizard.page.ResultCheckerDefinitionPage;
import component.wizard.page.ResultSetFilterDefinitionDefinitionPage;
import component.wizard.page.SQLDefinitionPage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class MBOProperties extends RationalTestScript
{
	/**
	 * Script Name   : <b>MBOProperties</b>
	 * Generated     : <b>Jul 29, 2010 5:56:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/07/29
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		getMessageOnLocalMboAttribute();
	}
	protected String project;
	protected String mbo;
	
	public MBOProperties(String projectName, String mbo){
		this.project = WN.projectNameWithVersion(projectName);
		this.mbo = mbo;
	}
	
	private void save(){
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public void setMboName(String name){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(name);
		save();
		this.mbo = name;
	}
	
	public void setMboDefinition(String type, String definition){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabItem(DOF.getRoot(), "Definition").click();
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		if(type.equalsIgnoreCase("SQL")){
			SQLDefinitionPage.setSQLDefinition(DOF.getDialog("Definition"),definition);
			SQLDefinitionPage.ok(DOF.getDialog("Definition"));
		}else{
			SQLDefinitionPage.setSPDefinition(DOF.getDialog("Definition"),definition);
			SQLDefinitionPage.ok(DOF.getDialog("Definition"));
		}
		sleep(1);
		if(DOF.getDialog("Refresh prompt")!=null){
			DOF.getButton(DOF.getDialog("Refresh prompt"), "&Yes").click();
		}
		// >>>>>>> flv Start 11-03<<<<<<<<<<<<<<
		if(DOF.getDialog("Execution Error")!=null){
			DOF.getButton(DOF.getDialog("Execution Error"), "OK").click();
		}
		// >>>>>>> flv End 11-03<<<<<<<<<<<<<<
		sleep(3);
	}
	
	public String getMboName(){
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		return DOF.getTextField(DOF.getRoot(), "Name:").invoke("getText").toString();
	}
	
	public boolean setConnectionProfile(String cpName){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
//		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		return DataSourceTab.setConnectionProfile(cpName);
	}
	
	public void setDateSource(String dsType, String cpName){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Data Source"));
		DOF.getButton(DOF.getRoot(), "&Bind Data Source...").click();
		DOF.getButton(DOF.getDialog("Question"), "&Yes").click();
		DOF.getCombo(DOF.getDialog("Bind Data Source"),"Data source type:").click();
		DOF.getCombo(DOF.getDialog("Bind Data Source"),"Data source type:").click(atText(dsType));
		DOF.getCombo(DOF.getDialog("Bind Data Source"),"Connection profile:").click();
		DOF.getCombo(DOF.getDialog("Bind Data Source"),"Connection profile:").click(atText(cpName));
		
		DOF.getButton(DOF.getDialog("Bind Data Source"),"&Finish").click();
		save();
	}
	
	public void setSQLQuery(String string){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		DOF.getTextField(DOF.getGroup(DOF.getDialog("Definition"), "&SQL Query")).click();
		DOF.getDialog("Definition").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("Definition").inputChars(string);
		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		
		DOF.getButton(DOF.getDialog("Refresh prompt"), "&Yes").click();
	}
	
	public String getAttributePreviewData(int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "Pre&view...").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		TableHelper.printColumns(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"));
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Preview"),"OK").click();
		return data;
	}
	
	public String getAttributePreviewData(java.util.List<String[]> parameters,int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "Pre&view...").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		
		for(String[] para : parameters){
			setAttributePreviewParameter(para[0],para[1]);
		}
		
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		TableHelper.printColumns(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"));
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Preview"),"OK").click();
		return data;
	}
	
	public String getAttributePreviewDataOfResultSet(java.util.List<String[]> parameters, String resultSet, int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "Pre&view...").click();
		sleep(3);
		DOF.getCComboTip(DOF.getDialog("Preview"), "Select a result set to preview: ").click();
		DOF.getPoppedUpList().click(atText(resultSet));
		for(String[] para : parameters){
			setAttributePreviewParameter(para[0],para[1]);
		}
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		TableHelper.printColumns(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"));
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Preview"),"OK").click();
		return data;
	}
	
	public String getAttributePreviewDataOfResultSet(String parameters, String resultSet, int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "Pre&view...").click();
		sleep(3);
		DOF.getCComboTip(DOF.getDialog("Preview"), "Select a result set to preview: ").click();
		DOF.getPoppedUpList().click(atText(resultSet));
		for(String para : parameters.split(":")){
			if(para.contains(",")){
				String name = para.split(",")[0];
				String value = para.split(",")[1];
				setAttributePreviewParameter(name, value);
			}
		}
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		TableHelper.printColumns(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"));
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Preview"),"OK").click();
		return data;
	}
	
	public String getOperationPreviewData(String operation, java.util.List<String[]> parameters, int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(rowIndex), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Definition"));
//		DOF.getButton(dialog, "&Edit...").click();
		DOF.getButton(dialog, "Test E&xecute...").click();
//		DOF.getButton(DOF.getDialog("Definition"), "Test E&xecute...").click();
		sleep(1);
		for(String[] para : parameters){
			setOperationPreviewParameter(para[0],para[1]);
		}
		DOF.getButton(DOF.getDialog("Test Execute"), "Test E&xecute").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(3); //wait for result to come up
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Test Execute"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Test Execute"), "OK").click();
//		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		DOF.getButton(dialog, "Cancel").click();
		return data;
	}
	
	public String getOperationPreviewData(String operation, int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(rowIndex), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		return OperationPropertiesDialog.getPreviewData(dialog, row, column);
	}
	
	public String getOperationPreviewData(String operation, String[] parameters, int row, int column){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(rowIndex), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Definition"));
//		DOF.getButton(dialog, "&Edit...").click();
		DOF.getButton(dialog, "Test E&xecute...").click();
//		DOF.getButton(DOF.getDialog("Definition"), "Test E&xecute...").click();
		sleep(1);
		for(String para : parameters){
			String[] map = para.split(",");
			setOperationPreviewParameter(map[0], map[1]);
		}
		DOF.getButton(DOF.getDialog("Test Execute"), "Test E&xecute").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(3); //wait for result to come up
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Test Execute"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Test Execute"), "OK").click();
//		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		DOF.getButton(dialog, "Cancel").click();
		return data;
	}
	
	private void setOperationPreviewParameter(String paraName, String value){
		GuiSubitemTestObject paraTable = DOF.getTableByColumnsNames(DOF.getDialog("Test Execute"),new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
		if(!paraName.contains("->")){
			int r = TableHelper.getRowIndexOfRecordInColumn(paraTable, "Argument Name", paraName);
			TableHelper.setTextCellValue(paraTable, r, "Value", value);
		}else{
			String complextPara = paraName.substring(0, paraName.indexOf("->"));
			int row = TableHelper.getRowIndexOfRecordInColumn(paraTable, "Argument Name", "_HEADER_");
			String dataType = TableHelper.getCellValue(paraTable, row, "Datatype");
			paraTable.click(atCell(atRow(row), atColumn("Value")));
			sleep(1);
			DOF.getButton(paraTable, "...").click();
			TopLevelTestObject dialog = DOF.getDialog("Edit Values");
			GuiSubitemTestObject tree2 = DOF.getTree(dialog);
			String path = paraName.replace(complextPara, dataType);
			TreeHelper.setTextCellValue(tree2, path, "Values", value);
			DOF.getButton(dialog, "OK").click();
		
		}
	}
	
	private void setAttributePreviewParameter(String paraName, String value){
		GuiSubitemTestObject paraTable = DOF.getTable(DOF.getDialog("Preview"));
//		GuiSubitemTestObject paraTable = DOF.getTableByColumnsNames(DOF.getDialog("Preview"),new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
		int r = TableHelper.getRowIndexOfRecordInColumn(paraTable, "Argument Name", paraName);
		TableHelper.setTextCellValue(paraTable, r, "Value", value);
	}
	
	public void addAttributeRole(String role){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Roles"));
		GuiSubitemTestObject leftTable = DOF.getTable(DOF.getRoot(),"Available Roles:");
		int row = TableHelper.getRowIndexOfRecordInColumn(leftTable, "Name", role);
		leftTable.click(atCell(atRow(row), atColumn("Name")),atPoint(10,10));
		sleep(1);
		DOF.getButton(DOF.getRoot(), "A&dd >>").click();
		sleep(1);
		save();
	}

	public void addOperationRole(String operation, String role){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(row), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		OperationPropertiesDialog.addRole(role, DOF.getDialog("Operation '"+operation+"'"));
		save();
	}
	
	public boolean hasRoleInAssignedRoleTable(String role){
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot(),"Assigned Roles:");
		if(TableHelper.hasDataInColumn(table, "Name", role)){
			return true;
		}else{
			return false;
		}
	}
	
	public void removeRole(String role){
		selectMbo();
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Roles"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot(),"Assigned Roles:");
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", role);
		table.click(atCell(atRow(row), atColumn("Name")),atPoint(10,10));
		sleep(1);
		DOF.getButton(DOF.getRoot(), "<< &Remove").click();
		restore();
		sleep(1);
		save();
		
	}
	
	
	
	public String setAttributeMapping(String mboColumn, String mboType, String dsColumn, String dsType){
		selectMbo();
		maximize();
		sleep(3);
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", mboColumn);
		TableHelper.setTextCellValue(table, row, 2, mboType);
		TableHelper.setCComboCellValue(table, row, 5, dsColumn);
		TableHelper.setTextCellValue(table,  row, 6, dsType);
		table.click(atCell(atRow(1),atColumn(1)));
		restore();
		save();
		sleep(1);
		return getPropertiesBannerMessageLabel().getProperty("text").toString();
	}
	
	private GuiTestObject getPropertiesBannerMessageLabel(){
		return DOF.getSybaseLabel(DOF.getTabbedPropertyTitle(DOF.getRoot()));
//		return DOF.getLabelByBound(DOF.getTabbedPropertyTitle(DOF.getRoot()), new Rectangle(302,2,601,22));
	}
	
	public String setAttributeMapping(String mboColumn, String dsColumn, String dsType){
		selectMbo();
		sleep(3);
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();
		
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", mboColumn);
		
//		table.click(atCell(atRow(row), atColumn("Map to")));
		TableHelper.setCComboCellValue(table, row, 5, dsColumn);
		
//		table.click(atCell(atRow(row), atColumn(5)));
//		DOF.getCCombo(table).click(atPoint(140,8));
////		list2().click(atText(dsColumn));
//		DOF.getPoppedUpList().click(atText(dsColumn));
//		table.click(atCell(atRow(row), atColumn(6)));
		
		TableHelper.setTextCellValue(table,  row, 6, dsType);
//		table.click(atCell(atRow(row), atColumn(6)));
//		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
//		DOF.getRoot().inputChars(type);
		
		table.click(atCell(atRow(1),atColumn(1)));
		
		save();
		sleep(1);
		restore();
		return getPropertiesBannerMessageLabel().getProperty("text").toString();
		
//		// Frame: Mobile Development - db_asa - Mobile Application Diagram - Sybase Unwired WorkSpace
//		c5DATE().click(atPoint(138,7));
//		properties().doubleClick(atPoint(53,6));
//		
//		// Frame: Mobile Development - Sybase Unwired WorkSpace
//		attributesMapping().click(atPoint(23,8));
//		table().click(atCell(atRow(atIndex(0)), atColumn("Name")), 
//                atPoint(43,7));
//		table().click(atCell(atRow(atIndex(0)), atColumn("Map to")), 
//                atPoint(89,10));
	}
	
	public String setAttributeMapping(String mboColumn, String dsColumn){
		selectMbo();
		sleep(3);
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", mboColumn);
		TableHelper.setCComboCellValue(table, row, 5, dsColumn);
		table.click(atCell(atRow(1),atColumn(1)));
		save();
		sleep(1);
		restore();
		return getPropertiesBannerMessageLabel().getProperty("text").toString();
	}
	

	private GuiSubitemTestObject getAttributesMappingTable() {
		GuiSubitemTestObject table = DOF.getTableByColumnsNames(DOF.getDualHeadersTable(DOF.getRoot()), 
        		new String[]{"","Name","Datatype","Nullable","Primary Key","Map to","Datatype","Nullable"});
		return table;
	}
	
	public String getMessageOnAttributeMapping(){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
//		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
//		cLabel().click(atPoint(92,10));
		sleep(2);
		return getPropertiesBannerMessageLabel().getProperty("text").toString();
	}
	
	public String getMessageOnLocalMboAttribute(){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Attributes"));
//		System.out.println(cLabel().getProperties().get("bounds"));
		return DOF.getSybaseLabel(DOF.getRoot()).getProperty("text").toString();
//		return cLabel().getProperty("text").toString();
	}
	
	public String getMessageOnLoadParameter(){
		selectMbo();
//		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		sleep(2);	
		return getPropertiesBannerMessageLabel().getProperty("text").toString();
//		if( DOF.getSybaseLabelByBounds(DOF.getRoot(), new Rectangle(302, 2, 601, 22) )!=null){
//			return DOF.getSybaseLabelByBounds(DOF.getRoot(), new Rectangle(302, 2, 601, 22) ).getProperty("text").toString();
//		}else{
//			return null;
//		}
	}

	public void selectMbo(){
//		int MaxRetry = 0;
//		String[] allMbo = WN.getAllMboInProject(this.project);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getWNTree().doubleClick(atPath(WN.mboPath(project, mbo)));
		DOF.getMboNameFigure(DOF.getObjectDiagramCanvas(),this.mbo).click();
		DOF.getWNTree().doubleClick(atPath(WN.mboPath(project, mbo)));
//		while (MaxRetry < allMbo.length) {
//			try {
//				WN.selectMBO(this.project, this.mbo);
//				DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
//				break;
//			} catch (NullPointerException e) {
//				String mbo = allMbo[MaxRetry];
//				WN.selectMBO(this.project, mbo);
//				MaxRetry++;
//			}
//		}
	}

	//Assuming there is no attribute name "attribute1"
	//modify on 11.25 by ff>>>>>>>>>>>>>>>>>>>>
	public void addAttribute(String name, String dataType,boolean nullable,String mapto){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();
		DOF.getButton(DOF.getRoot(), "&Add").click();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "attribute1");
		TableHelper.setTextCellValue(table, row, 1, name);
		TableHelper.setTextCellValue(table, row, 2, dataType);
		
//		TableHelper.setCheckboxCellValue(table, row, 3, nullable);
//		TableHelper.setTextCellValue(table, row, 5, mapto);
		TableHelper.setCComboCellValue(table, row, 5, mapto);
		TableHelper.setTextCellValue(table, row, 6, dataType);
		save();
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<modify on 11.25 by ff
	//Assuming there is no attribute name "attribute1"
	public void addLocalMboAttribute(LocalMboAttribute attribute){
		selectMbo();
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		DOF.getButton(DOF.getRoot(), "&Add").click();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "attribute1");
		TableHelper.setTextCellValue(table, row, 1, attribute.getName());
		TableHelper.setTextCellValue(table, row, 2, attribute.getType());
//		TableHelper.setCheckboxCellValue(table, row, 3, nullable);
//		TableHelper.setCheckboxCellValue(table, row, 4, primaryKey);
		restore();
		save();
	}
	
	public GuiSubitemTestObject getLoadParametersTree() {
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
		return tree;
	}

	public void setLoadParameterPropagateTo(String string, String string2) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setCComboCellValue(tree, string, "Propagate to Attribute", string2);
		tree.click(atPath(string));
		restore();
		save();
	}
	
	public void setLoadParameterDatatype(String string, String string2) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setTextCellValue(tree, string, 1, string2);
		TreeHelper.setTextCellValue(tree, string, 7, string2);
		tree.click(atPath(string));
		restore();
		save();
	}

	public void setLoadParameterDefaultValue(String string, String defaultValue) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setTextCellValue(tree, string, "Default Value", defaultValue);
		tree.click(atPath(string));
		restore();
		save();
	}
	
	public void setLoadParameterDefaultValue(String name, String type, String defaultValue) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setTextCellValue(tree, name,  1, type);
		TreeHelper.setTextCellValue(tree, name,  7, type);
		TreeHelper.setTextCellValue(tree, name, "Default Value", defaultValue);
		sleep(1);
		tree = getLoadParametersTree();
		tree.click(atCell(atRow(atPath(name)), atColumn(1)));
		restore();
		save();
	}
	
	public void setLoadParameterComplexTypeDefaultValue(String parameter, String defaultValues){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Default Value")));
		DOF.getButton(DOF.getRoot(), "...").click();
		ComplexTypeDefaultValueDialog.setDefaultValue(defaultValues);
		restore();
		save();
	}
	
	public void setLoadParameterPK(String name, String pk){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setCComboCellValue(tree, name, "Personalization Key", pk);
		tree.click(atPath(name));
		restore();
		save();
	}
	
	
	public void maximize(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		DOF.getContextMenu().click(atPath("Maximize"));
	}
	
	public void restore(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		DOF.getContextMenu().click(atPath("Restore"));
	}

	public String getConnectionProfile() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		return DataSourceTab.getConnectionProfile(); 
	}

	public void deleteLoadParameter(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		getLoadParametersTree().click(atPath(string));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		save();
	}
	public void RemapLoadParameter(){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		DOF.getButton(DOF.getRoot(), "Re&map").click();
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		save();
	}
	
	public boolean hasLoadParameter(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		try{
			getLoadParametersTree().click(atCell(atRow(atPath(string)), atColumn("Name")));
			return true;
		}catch(Exception e){
			return false;
		}
//		String node = TreeHelper.getFirstItem((ScrollGuiSubitemTestObject)getLoadParametersTree());
//		return node.equals(string);
	}

	public void moveUpLoadParameter(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		getLoadParametersTree().click(atPath(string));
		DOF.getButton(DOF.getRoot(), "&Up").click();
		save();
	}
	
	public void moveDownLoadParameter(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		getLoadParametersTree().click(atPath(string));
		DOF.getButton(DOF.getRoot(), "Do&wn").click();
		save();
	}

	public int getLoadParameterIndex(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		String[] parameters = TreeHelper.getTopLevelNodes(getLoadParametersTree());
		for(int i =0;i<parameters.length;i++){
			System.out.println(i+":"+parameters[i]);
			if(parameters[i].equals(string)){
				return i;
			}
		}
		return -1;
	}

	public boolean hasObjectQuery(String oq) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		String[] oqs = TableHelper.getColumnData(table,"Name");
		for(String name:oqs){
			if(name.equals(oq)){
				return true;
			}
		}
		return false;
	}

	public void setReturnTypeOfObjectQuery(String string, String rtResultset) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(1);
		String dialogName = "Object Query '"+string+"'";
		DOF.getButton(DOF.getDialog(dialogName), "R&eturn a result set").click();
		DOF.getButton(DOF.getDialog(dialogName), "OK").click();
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public void deleteObjectQuery(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public void deleteLocalMboObjectQuery(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public void deleteAllObjectQuery() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Object Queries"));
		DOF.getButton(DOF.getRoot(), "Delete A&ll").click();
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public int hasObjectQueryCount() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		String[] oqs = TableHelper.getColumnData(table,"Name");
		return oqs.length;
	}

	public String getStuctureParameterType(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		return TableHelper.getCellValue(table, row, "Datatype");
	}
	
	public String setOperationParameterPK(String operation, String parameter,String pk){
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
//		DOF.getTabFolder(DOF.getDialog(dialogName), "Data Source").setState(SINGLE_SELECT, atText("Parameter"));
		DOF.getTabFolder(DOF.getDialog(dialogName), "Data Source").setState(SINGLE_SELECT, atText("Operation Parameters"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getDialog(dialogName)));
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Personalization Key")));
		sleep(0.5);
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Personalization Key")));
		sleep(0.5);
		DOF.getPoppedUpList().click(atText(pk));
		sleep(1);
		String result = null;
		if(DOF.getTextFieldByAncestorLine(DOF.getDialog(dialogName), "Composite->Shell->Shell")!=null){
			result = DOF.getTextFieldByAncestorLine(DOF.getDialog(dialogName), "Composite->Shell->Shell").getProperty("text").toString();
		}
		DOF.getButton(DOF.getDialog(dialogName), "OK").click();
		DOF.getMenu().click(atPath("File->Save All"));
		return result;
	}
	
	public void setOperationParameterComplexTypeDefaultValue(String operation, String parameter, String defaultValues){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
		DOF.getTabFolder(DOF.getDialog(dialogName), "Data Source").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getDialog(dialogName)));
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Default Value")));
		DOF.getButton(DOF.getDialog(dialogName), "...").click();
		ComplexTypeDefaultValueDialog.setDefaultValue(defaultValues);
		DOF.getButton(DOF.getDialog(dialogName), "OK").click();
		DOF.getMenu().click(atPath("File->Save All"));

	}
	
	public void setAttributeParameterPK(String parameter, String pk){
		selectMbo();
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot()));
		tree.click(atPath(parameter));
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Personalization Key")));
		sleep(0.5);
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Personalization Key")));
		sleep(0.5);
		DOF.getPoppedUpList().click(atText(pk));
		restore();
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public void changeLocalMboAttributeName(String from,String to) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", from);
		TableHelper.setTextCellValue(table, row, "Name", to);
		table.click(atCell(atRow(row), atColumn("Datatype")));
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public List<String> getLoadParameters() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		String[] parameters = TreeHelper.getTopLevelNodes(getLoadParametersTree());
		restore();
		return new ArrayList<String>(Arrays.asList(parameters));
	}
	
	public String[] getAttributes(){
		selectMbo();
		sleep(3);
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();
		String[] result = TableHelper.getColumnData(table, "Name");
		restore();
		return result;
	}
	
	public String[] getLocalMboAttributes(){
		selectMbo();
		sleep(3);
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		String[] result = TableHelper.getColumnData(table, "Name");
		restore();
		return result;
	}
	
	public String[] getOperations(){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		return TableHelper.getColumnData(table, "Name");
	}

	public boolean hasAttribute(String string) {
		for(String att:getAttributes()){
			if(att.equals(string)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasLocalMboAttribute(String string) {
		for(String att:getLocalMboAttributes()){
			if(att.equals(string)){
				return true;
			}
		}
		return false;
	}

	public boolean hasOperation(String string) {
		for(String op:getOperations()){
			if(op.equals(string)){
				return true;
			}
		}
		return false;
	}

	public boolean hasRelationshipOfTarget(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		return TableHelper.hasDataInColumn(table, "Target object", string);
	}

	public boolean hasRelationshipOfName(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		return TableHelper.hasDataInColumn(table, "Name", string);
	}

	public String getRelationshipType(String name) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		return TableHelper.getCellValue(table, row, "Type");
	}
	
	public void deleteRelationship(String name){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "Dele&te").click();
		save();
	}

	public void deleteOperation(String operation) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		save();
	}
	
	public boolean hasTab(String name){
		return PropertiesTabHelper.hasTabName(name);
	}

	public void addSynchronizationParameter(SynchronizationParameter sp) {
		selectMbo();
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "4").click();
		DOF.getButton(DOF.getRoot(), "&Add").click();
		GuiSubitemTestObject spTable = DOF.getTableByColumnsNames(DOF.getRoot(), 
				new String[]{"","Parameter Name", "Datatype", "Nullable", "Default Value",
				"Personalization Key", "Mapped to", "Query Limiting"});
//		GuiSubitemTestObject spTable = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(spTable,
				"Parameter Name", "parameter1");
		TableHelper.setTextCellValue(spTable, row, "Parameter Name", sp
				.getName());
		TableHelper
				.setTextCellValue(spTable, row, "Datatype", sp.getDatatype());
		if (sp.getDefaultValue() != null) {
			TableHelper.setTextCellValue(spTable, row, "Default Value", sp
					.getDefaultValue());
		}
		if (sp.getPk() != null) {
			TableHelper.setTextCellValue(spTable, row, "Personalization Key",
					sp.getPk());
		}
		if (sp.getMapTo() != null) {
			TableHelper.setCComboCellValue(spTable, row, "Mapped to", sp.getMapTo());
//			TableHelper.setTextCellValue(spTable, row, "Mapped to", sp.getMapTo());
		}
		restore();
		save();
	}

	public void setCustomizedDownloadDataQuery(String sql) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "4").click();
		((ToggleTestObject)DOF.getButton(DOF.getRoot(), "&Customized download data")).setState(SELECTED);
		DOF.getTextFieldByToolTip(DOF.getRoot(), "Specify a download SQL here to synchronize the cache data").click();
		DOF.getRoot().inputChars(sql);
	}
	
	public ObjectQuery getObjectQuery(String name){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		if(row!=-1){
			String oqName = TableHelper.getCellValue(table, row, "Name");
			String returnType = TableHelper.getCellValue(table, row, "Return type");
			String parameter = TableHelper.getCellValue(table, row, "Parameters");
			String definition = TableHelper.getCellValue(table, row, "Query Definition");
			return new ObjectQuery().name(oqName).parameter(parameter).returnType(returnType).queryDefinition(definition);
		}else{
			return null;
		}
	}

	public void generateLocalMboOperation(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		if(string.equalsIgnoreCase("update")){
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "u&pdate")).setState(SELECTED);
		}
		if(string.equalsIgnoreCase("delete")){
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "d&elete")).setState(SELECTED);
		}
//		save();
	}

	public void setLocalMboAttribute(String attr,	LocalMboAttribute localMboAttribute) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", attr);
		TableHelper.setTextCellValue(table, row, "Name", localMboAttribute.getName());
		TableHelper.setTextCellValue(table, row, "Datatype", localMboAttribute.getType());
		save();
		
	}

	public LocalMboAttribute getLocalMbo(String attrName) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", attrName);
		return new LocalMboAttribute(attrName, TableHelper.getCellValue(table, row, "Datatype"),false, false);
		
	}

	public void deleteLocalMboAttribute(String attrName) {
		selectMbo();
		maximize();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", attrName);
		table.click(atCell(atRow(row),atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		restore();
		save();
	}

	public List<Relationship> getRelationships() {
		List<Relationship> result = new ArrayList<Relationship>();
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		ITestDataTable tdt = (ITestDataTable)table.getTestData("contents");
		int rowCount = tdt.getRowCount();
		for(int i =0;i< rowCount;i++){
			String name = TableHelper.getCellValue(table, i, "Name");
			String target = TableHelper.getCellValue(table, i, "Target Object");
			String type = TableHelper.getCellValue(table, i, "Type");
			if(!name.trim().equals("")){
				result.add(new Relationship().name(name).type(type).target(target));
			}
		}
		return result;

//		String[] relationships = TableHelper.getColumnData(table, "Name");
//		for(String relationship:relationships){
//			int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", relationship);
//			String type = TableHelper.getCellValue(table, row, "Type");
//			String target = TableHelper.getCellValue(table, row, "Target Object");
//			result.add(new Relationship().name(relationship).type(type).target(target));
//		}
//		return result;
		
	}

	public OperationParameter getOperationParameter(String operation, String parameter) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
//		DOF.getTabFolder(DOF.getDialog(dialogName), "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
		DOF.getTabFolder(DOF.getDialog(dialogName), "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDialog(dialogName));
//		String defaultValue = TreeHelper.getCellValue(tree, parameter, 11);
//		String type = TreeHelper.getCellValue(tree, parameter, 2);
		String type = TreeHelper.getCellValue(tree, parameter, "Datatype");
		String defaultValue;
		if(type.equalsIgnoreCase("date")){
			tree.click(atCell(atRow(atPath(parameter)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(tree, "...").click();
			defaultValue = DOF.getTextField(DOF.getDialog("Date"), "Value:").getProperty("text").toString();
			DOF.getButton(DOF.getDialog("Date"), "OK").click();
		}else{
			defaultValue = TreeHelper.getCellValue(tree, parameter, "Default Value");}
		OperationParameter result = new OperationParameter().name(parameter).type(type).defaultValue(defaultValue);
		DOF.getButton(DOF.getDialog(dialogName), "Cancel").click();
		return result;
	}

	public String getDefaultValueOfOperationParameter(String operation,String parameter) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
//		DOF.getTabFolder(DOF.getDialog(dialogName), "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
		DOF.getTabFolder(DOF.getDialog(dialogName), "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDialog(dialogName));
		String result = TreeHelper.getCellValue(tree, parameter, "Default Value");
		
//		DOF.getTabFolder(DOF.getDialog(dialogName), "Definition").setState(SINGLE_SELECT, atText("Definition"));
//		DOF.getButton(DOF.getDialog(dialogName), "Test E&xecute...").click();
//		GuiSubitemTestObject parameterTable = DOF.getTableByColumnsNames(DOF.getDialog("Test Execute"), new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
//		int row = TableHelper.getRowIndexOfRecordInColumn(parameterTable, "Argument Name", parameter);
//		String result = TableHelper.getCellValue(parameterTable, row, "Value");
//		DOF.getButton(DOF.getDialog("Test Execute"), "OK").click();
		DOF.getButton(DOF.getDialog(dialogName), "Cancel").click();
		return result;
	}
	
	public void setDefaultValueOfOperationParameter(String operation, String parameter, String defalutValue){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
	//  DOF.getTabFolder(DOF.getDialog(dialogName), "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
		DOF.getTabFolder(DOF.getDialog(dialogName), "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDialog(dialogName));
		tree.click(atCell(atRow(atPath(parameter)), atColumn("Default Value")));
		DOF.getDialog(dialogName).inputChars(defalutValue);
		DOF.getButton(DOF.getDialog(dialogName), "OK").click();
	}

	public MBOAttribute getAttribute(String name) {
		selectMbo();
		DOF.getMboAttributeFigure(DOF.getMboFigure(DOF.getObjectDiagramCanvas(), mbo), name).click();
		String n = DOF.getTextField(DOF.getRoot(), "Name:").getProperty("text").toString();
		String type = DOF.getTextField(DOF.getRoot(), "Datatype:").getProperty("text").toString();
		String mapTo = DOF.getTextField(DOF.getRoot(), "Map to:").getProperty("text").toString();
		String isPK = DOF.getButton(DOF.getRoot(), "&Primary key").invoke("getSelection").toString();
		
		return new MBOAttribute().name(n).type(type).mapTo(mapTo).primaryKey(isPK.equals("true")?true:false);
		
//		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
//		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Attributes Mapping"));
//		GuiSubitemTestObject table = DOF.getTable(DOF.getTable(DOF.getRoot()));
//		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
//		String type = TableHelper.getCellValue(table, row, "Datatype");
//		return new MBOAttribute(name, type, false, false);
	}

	public void addXSTL(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		DOF.getButton(DOF.getDialog("Definition"),"&Add..").click();
		DOF.getTextField(DOF.getDialog("Define SXTL"), "Name").click();
		DOF.getDialog("Define SXTL").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("Define SXTL").inputChars(string);
		DOF.getButton(DOF.getDialog("Define SXTL"), "OK").click();
		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		sleep(2);
		DOF.getButton(DOF.getDialog("Refresh Prompt"), "&Yes").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
	}

	public int getXSTLCount() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getDialog("Definition"));
		int result = TableHelper.getRowCount(table);
		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		return result;
	}

	public String getRCInfo() {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		String result = DOF.getTextFieldByBound(DOF.getRoot(), new Rectangle(5,5,916,19)).getProperty("text").toString();
		return result;
	}

	public void rebindDataSource(IWizard wizard, IWizardEntity entity) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Data Source"));
		DOF.getButton(DOF.getRoot(), "&Bind Data Source...").click();
		DOF.getButton(DOF.getDialog("Question"), "&Yes").click();
		wizard.create(entity, new WizardRunner());
	}

	public String getPreviewAttributeDefaultValue(String attribute) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "Pre&view...").click();
		TopLevelTestObject dialog = DOF.getDialog("Preview");
		GuiSubitemTestObject table = DOF.getTableByColumnsNames(dialog, new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Argument Name", attribute);
		String result = TableHelper.getCellValue(DOF.getTable(dialog), row, "Value");
		DOF.getButton(dialog, "OK").click();
		return result;
	}

	public List<String> getOperationParameters(String operation) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		sleep(2);
		String dialogName = "Operation '"+operation+"'";
		return OperationPropertiesDialog.getParameters(DOF.getDialog(dialogName));
//		DOF.getTabFolder(DOF.getDialog(dialogName), "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
//		GuiSubitemTestObject tree = DOF.getTree(DOF.getDialog(dialogName));
//		String[] nodes = TreeHelper.getNodesOfLevel(tree, 0);
		
	}

	public void addStructureAttribute(String name, String dataType) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		DOF.getButton(DOF.getRoot(), "&Add").click();
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "attribute1");
		TableHelper.setTextCellValue(table, row, 1, name);
		TableHelper.setTextCellValue(table, row, 2, dataType);
		MainMenu.saveAll();
	}

	public void setStructureType(String name,  String type) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		TableHelper.setTextCellValue(table, row, "Datatype", type);
		TableHelper.clickAtCell(table, row, "Name");
		MainMenu.saveAll();
		
	}

	public LoadParameter getLoadParameter(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Load Parameters").click(atText("Load Parameters"));
		maximize();
		LoadParameter result = LoadParameterTab.getParameter(string);
		restore();
		return result;
	}
	
	public void setLoadParameter(String name, LoadParameter parameter){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Load Parameters").click(atText("Load Parameters"));
		maximize();
		LoadParameterTab.setParameter(name, parameter);
		restore();
		MainMenu.saveAll();
	}

	public List<String> getObjectQueries() {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		String[] data = TableHelper.getColumnData(table, "Name");
		List<String>result = new ArrayList<String>();
		for(String d:data){
			result.add(d);
		}
		return result;
	}

	public Object canAddObjectQuery() {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		boolean result;
		if(DOF.getButton(DOF.getRoot(), "&Add...")!=null){
			result = true;
		}else{
			result = false;
		}
		return result;
	}

	public void setOperation(Operation operation) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation.getName(), "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		if(operation.getResultToCache()!=null){
			if(operation.getResultToCache().equalsIgnoreCase("true")){
				OperationEditDialog.setUpdateCache(DOF.getDialog("Operation '"+operation.getName()+"'"),true);
			}else{
				OperationEditDialog.setUpdateCache(DOF.getDialog("Operation '"+operation.getName()+"'"),false);
			}
		}
		OperationEditDialog.setDeifinition(operation.getSqlQuery(), DOF.getDialog("Operation '"+operation.getName()+"'"));
		OperationEditDialog.setParameter(operation.getParameter(), DOF.getDialog("Operation '"+operation.getName()+"'"));
		OperationEditDialog.setResultChecker(operation.getResultChecker(), DOF.getDialog("Operation '"+operation.getName()+"'"));
		OperationEditDialog.setRole(DOF.getDialog("Operation '"+operation.getName()+"'"), operation.getRole());
		OperationEditDialog.setConnectionProfile(operation.getConnectionProfile(), DOF.getDialog("Operation '"+operation.getName()+"'"));
		OperationEditDialog.ok(DOF.getDialog("Operation '"+operation.getName()+"'"));
		MainMenu.saveAll();
	}

	public OperationEditDialog getOperationEditDialog(String string) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, string, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		return new OperationEditDialog(string);
	}

	public RestWSMbo getRestWSMbo() {
		selectMbo();
		maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Definition").click(atText("Definition"));
		TestObject parent = DOF.getRoot();
		String resourseBaseUrl = DOF.getTextField(parent, "Resource base URL:").getProperty("text").toString();
		String userName = DOF.getTextField(parent, "Authentication user name:").getProperty("text").toString();
		String resourceUriTemplate = DOF.getTextField(parent, "Resource URI template:").getProperty("text").toString();
		String httpMethod = DOF.getTextField(parent, "HTTP method:").getProperty("text").toString();
		String statusCode = DOF.getTextField(parent, "Expected status code:").getProperty("text").toString();
		String resultCheker = DOF.getTextFieldByAncestorLine(parent, "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->CTabFolder->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->Composite->Composite->Composite->Composite->Shell").getProperty("text").toString();
//		String resultCheker = DOF.getTextField(parent, "Result checker:").getProperty("text").toString();
//		String resultCheker = DOF.getTextFieldByBound(parent, new Rectangle(5,5,928,19)).getProperty("text").toString();
		
		
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		String header = TableHelper.getCellValue(table, 0, "Name")+","+TableHelper.getCellValue(table, 0, "Value");
		restore();
		return new RestWSMbo().userName(userName)
			.resourceBaseUrl(resourseBaseUrl)
			.resourceUriTemplate(resourceUriTemplate)
			.httpMethod(httpMethod)
			.expectedStatusCode(statusCode)
			.header(header)
			.resultChecker(resultCheker);
	}

	public void setRestWSMbo(RestWSMbo mbo) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		if(mbo.getConnectionProfile()!=null){
			DataSourceTab.setConnectionProfile(mbo.getConnectionProfile());
		}else{
			DOF.getCTabFolder(DOF.getRoot(), "Definition").click(atText("Definition"));
			DOF.getButton(DOF.getRoot(), "&Edit...").click();
			RestWSDefinitionPage.setHttpMethod(mbo.getHttpMethod(), DOF.getDialog("Definition"));
			RestWSDefinitionPage.setResourceBaseUri(mbo.getResourceBaseUrl(), DOF.getDialog("Definition"));
			RestWSDefinitionPage.setExpectedStatusCode(mbo.getExpectedStatusCode(), DOF.getDialog("Definition"));
			RestWSDefinitionPage.setResourceUriTemplate(mbo.getResourceUriTemplate(), DOF.getDialog("Definition"));
			RestWSDefinitionPage.setUserName(mbo.getUserName(), DOF.getDialog("Definition"));
			ResultCheckerDefinitionPage.setResultChecker(mbo.getResultChecker(), DOF.getDialog("Definition"));
			RestWSDefinitionPage.ok(DOF.getDialog("Definition"));
			DOF.getButton(DOF.getDialog("Refresh prompt"), "&Yes").click();
			LongOperationMonitor.waitForDialogToVanish("Progress Information");
		}
	}

	public void setName(String string) {
		selectMbo();
		PropertiesTabHelper.clickTabName("General");
		DOF.getTextField(DOF.getRoot(),"Name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(string);
		MainMenu.saveAll();
	}

	public void addLoadParameter(LoadParameter parameter) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Load Parameters").click(atText("Load Parameters"));
		LoadParameterTab.addLoadParameter(parameter);
		MainMenu.saveAll();
	}

	public String[] getAttributeAssignedRoles() {
		selectMbo();
		maximize();
		List<String> roles = new ArrayList<String> ();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Roles").click(atText("Roles"));
		String[] result = TableHelper.getColumnData(DOF.getTable(DOF.getRoot(), "Assigned Roles:"), "Name");
		restore();
		return result;
	}
	
	public void renameOpration(String from, String to){
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, from, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Operation "+from);
		OperationEditDialog.setName(to, dialog);
		DOF.getButton(DOF.getDialog("Operation "+to), "OK");
		MainMenu.saveAll();
	}
	
	public Operation getOperation(String name) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		String dataSource = TableHelper.getCellValue(table, row, "Data Source Type");
		String connectionProfile = TableHelper.getCellValue(table, row, "Data Source");
		String type = TableHelper.getCellValue(table, row, "Type");
		Operation op = new Operation()
			.dataSource(dataSource)
			.conenctionProfile(connectionProfile)
			.type(type);
		return op;
	}

	public RestWSOperation getRestWSOperation(String operation) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		RestWSOperation op = OperationEditDialog.getOperation(DOF.getDialog("Operation '"+operation+"'"));
		OperationEditDialog.ok(DOF.getDialog("Operation '"+operation+"'"));
		return op;
	}

	public void setLoadArgument(LoadArgument la) {
		selectMbo();
		PropertiesView.maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Load Arguments").click(atText("Load Arguments"));
		LoadArgumentTab.setLoadArgument(la);
		MainMenu.saveAll();
		PropertiesView.restore();
	}
	
	public String getMessageOnLoadArgument(){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Load Arguments").click(atText("Load Arguments"));
		if( DOF.getSybaseLabelByBounds(DOF.getRoot(), new Rectangle(302, 2, 601, 22) )!=null){
			return DOF.getSybaseLabelByBounds(DOF.getRoot(), new Rectangle(302, 2, 601, 22) ).getProperty("text").toString();
		}else{
			return null;
		}
//		return cLabel2().getProperty("text").toString();
	}

	public Object hasLoadArgument(String string) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Load Arguments").click(atText("Load Arguments"));
		try{
			DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot())).click(atCell(atRow(atPath(string)), atColumn("Argument")));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public List<String> getLoadArguments() {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Load Arguments").click(atText("Load Arguments"));
		maximize();
		String[] parameters = TreeHelper.getTopLevelNodes(DOF.getTree(DOF.getDualHeadersTree(DOF.getRoot())));
		restore();
		return new ArrayList<String>(Arrays.asList(parameters));
	}

	public LoadArgument getLoadArgument(String string) {
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Load Arguments").click(atText("Load Arguments"));
		maximize();
		LoadArgument result = LoadArgumentTab.getParameter(string);
		restore();
		return result;
	}

	public boolean canEditOperationCachePolicy(String operation) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, operation, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		boolean result = TabFolderHelper.hasItem(DOF.getTabFolder(dialog, "Data Source"), "Cache Update Policy");
		DOF.getButton(dialog, "Cancel").click();
		return result;
	}

	public String getPreviewOperationParameterDefaultValue(String operation,String parameter) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.doubleClick(atCell(atRow(row), atColumn("Name")));
		String result = OperationEditDialog.getParameterDefaultValue(parameter, DOF.getDialog("Operation '"+operation+"'"));
		OperationEditDialog.ok(DOF.getDialog("Operation '"+operation+"'"));
		return result;
	}
	
	public void addOperation(Operation operation){
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		DOF.getButton(DOF.getRoot(), "&Add...").click();
		new WNOCW().create(operation.startParameter(null), new WizardRunner());
	}

	public boolean canAddRelationship() {
		selectMbo();
		PropertiesTabHelper.clickTabName("Relationships");
		return DOF.getButton(DOF.getRoot(), "A&dd").invoke("isEnabled").toString().equals("true")?true: false;
	}

	public void renameRelationship(String string, String string2) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Relationships");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Edit").click();
		EditRelationshipDialog.setRelationship(new Relationship().name(string2));
		EditRelationshipDialog.ok();
		MainMenu.saveAll();
	}

	public void setRelationship(Relationship relationship) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Relationships");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", relationship.getName());
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Edit").click();
		EditRelationshipDialog.setRelationship(relationship);
		EditRelationshipDialog.ok();
		MainMenu.saveAll();
	}

	public Relationship getRelationship(String string) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Relationships");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Edit").click();
		return EditRelationshipDialog.getRelationship();
	}

	public void setResultSetFilter(ResultFilter filter) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Definition").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		ResultSetFilterDefinitionDefinitionPage.setResultSetFilter(filter, DOF.getDialog("Definition"));
		ResultSetFilterDefinitionDefinitionPage.ok(DOF.getDialog("Definition"));
	}

	public List<ResultFilter> getResultFilters() {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Definition").click(atText("Definition"));
		DOF.getLabel(DOF.getRoot(), "Result Set Filters").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowCount = TableHelper.getRowCount(table);
		List<ResultFilter> filters = new ArrayList<ResultFilter>();
		for(int i=0;i<rowCount;i++){
			ResultFilter filter = new ResultFilter();
			filter.javaClass(TableHelper.getCellValue(table, i, "Name"));
			filters.add(filter);
		}
		return filters;
	}

	public void deleteResultFilter(String filter) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Definition").click(atText("Definition"));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		ResultSetFilterDefinitionDefinitionPage.ok(DOF.getDialog("Definition"));
		MainMenu.saveAll();
	}

	public String getMessageOnOperationPreviewData(String operation, String parameter) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(rowIndex), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Definition"));
//		DOF.getButton(dialog, "&Edit...").click();
		DOF.getButton(dialog, "Test E&xecute...").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		DOF.getButton(DOF.getDialog("Test Execute"), "Test E&xecute").click();
		return PreviewDialog.getMessage(DOF.getDialog("Test Execute"), parameter);
	}
	
	public void changeLoadParameterDatatype(String path, String type) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Data Source").click(atText("Load Parameters"));
		maximize();
		GuiSubitemTestObject tree = getLoadParametersTree();
		TreeHelper.setTextCellValue(tree, path, 1, type);
		tree.click(atPath(path));
		restore();
		save();
	}

	public void deleteAllAttribute(){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		DOF.getButton(DOF.getRoot(), "Delete Al&l").click();
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		save();
	}
	
	public void deleteAttribute(String attribute){
		selectMbo();
		PropertiesView.maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		AttributeMappingTab.deleteAttribute(attribute);
		save();
		PropertiesView.restore();
	}
	
	public void renameAttribute(String form, String to){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		AttributeMappingTab.rename(form, to);
		selectMbo();
		MainMenu.saveAll();
	}
	public void setAttribute(MBOAttribute attribute) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes Mapping").click(atText("Attributes Mapping"));
		AttributeMappingTab.setAttribute(attribute);
		OD.setMBOAttribute(this.mbo, attribute);
		selectMbo();
		MainMenu.saveAll();
	}
	
	public void moveUpAttribute(String string) {
		selectMbo();		
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();

		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "&Up").click();
		save();
	}

	//ffan
	public void moveDownAttribute(String string) {
		selectMbo();		
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		GuiSubitemTestObject table = getAttributesMappingTable();

		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", string);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(DOF.getRoot(), "Do&wn").click();
		save();
	}

	public void remapAttribute(){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		DOF.getButton(DOF.getRoot(), "Re&map").click();
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		save();
	}
	
	public void refreshAttribute(){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		DOF.getButton(DOF.getRoot(), "&Refresh").click();
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		save();
	}
	//fan
	////////////////feifan728>>>>>>>>>>>
	public void renameOperation(String from,String to) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Operations");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		TableHelper.clickAtCell(table, from, "Name");
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		OperationEditDialog.setName(to, DOF.getDialog("Operation '"+from+"'"));
		OperationEditDialog.ok(DOF.getDialog("Operation '"+to+"'"));
		MainMenu.saveAll();	
	}
	////////////////feifan728<<<<<<<<<<<<<<<<<<<
	////////////////feifan729>>>>>>>>>>>
	public String setOperationSQLQuery(String operation,String sql){
		selectMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(rowIndex), atColumn("Name")), atPoint(10,10));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TestObject dialog = DOF.getDialog("Operation '"+operation+"'");
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Definition"));
		DOF.getButton(dialog, "&Edit...").click();		
		DOF.getTextField(DOF.getGroup(DOF.getDialog("Definition"), "&SQL Query")).click();
		DOF.getDialog("Definition").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("Definition").inputChars(sql);
		
		DOF.getButton(DOF.getDialog("Definition"), "Validate Synta&x").click();
		sleep(1);
		String actual = DOF.getLabel(DOF.getDialog("Validation Information")).getProperty("text").toString();		
		DOF.getButton(DOF.getDialog("Validation Information"), "OK").click();
		DOF.getButton(DOF.getDialog("Definition"), "OK").click();
		DOF.getButton(DOF.getDialog("Refresh prompt"), "&Yes").click();
		DOF.getButton(DOF.getDialog(" '"+operation+"'"), "OK").click();
		return actual;
	}
////////////////feifan728<<<<<<<<<<<<<<<<<<<	
	public void renameLoadparameter(String form, String to){
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Load Parameters"));
		LoadParameterTab.rename(form, to);
		MainMenu.saveAll();
	}

	public void setObjectQuery(ObjectQuery oq) {
		selectMbo();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		ObjectQueryTab.set(oq);
		MainMenu.saveAll();
	}



//////////////////////////////////////whan 327///////////////////////////

public void setOperationCachePolicy(Operation operation,String parameter,boolean value) {
	selectMbo();
	PropertiesTabHelper.clickTabName("Operations");
	GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
	TableHelper.clickAtCell(table, operation.getName(), "Name");
	DOF.getButton(DOF.getRoot(), "&Edit...").click();
	sleep(0.5);
	TopLevelTestObject dialog = DOF.getDialog("Operation '"+ operation.getName()+"'");
	DOF.getTabFolder(dialog,"Cache Policy ").setState(SINGLE_SELECT, atText("Cache Policy "));
	
	if(parameter.equalsIgnoreCase("Immediately update the cache")){
		if(value){
    		((ToggleGUITestObject)DOF.getButton(dialog, "I&mmediately update the cache")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog, "I&mmediately update the cache")).setState(NOT_SELECTED);
			
		}
	}
	else{
		if(value){
			((ToggleTestObject)DOF.getButton(dialog, "&Invalidate the cache")).setState(SELECTED);
		}else{
			((ToggleTestObject)DOF.getButton(dialog, "&Invalidate the cache")).setState(NOT_SELECTED);
		}
					
	}

	sleep(1);
	DOF.getButton(DOF.getDialog("Operation '"+ operation.getName()+"'"), "OK").click();
	}



}