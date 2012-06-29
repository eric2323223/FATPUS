package component.entity;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.dialog.ConfirmResourceDeleteDialog;
import component.dialog.ConfirmSaveResourceDialog;
import component.dialog.DeployedMboPropertiesDialog;
import component.dialog.properties.RestWSPropertiesDialog;
import component.editor.SQLEditor;
import component.entity.model.ASACP;
import component.entity.model.DB2CP;
import component.entity.model.DbMbo;
import component.entity.model.DeployedMbo;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.GenericDBCP;
import component.entity.model.IWizardEntity;
import component.entity.model.Operation;
import component.entity.model.OracleCP;
import component.entity.model.RestWSCP;
import component.entity.model.RestWSMbo;
import component.entity.model.RestWSOperation;
import component.entity.model.SAPCP;
import component.entity.model.ScrapbookCP;
import component.entity.model.UnwiredServerCP;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.model.WizardRunner;
import component.entity.wizard.DB2CPCreationWizard;
import component.entity.wizard.EEAttributeDbMboCreationWizard;
import component.entity.wizard.EEOperationDbMboCreationWizard;
import component.entity.wizard.EEOperationRestWSMboCreationWizard;
import component.entity.wizard.EERestWSMBOCreationWizard;
import component.entity.wizard.EEWSMBOCreationWizard;
import component.entity.wizard.EEWSMBOOperationWizard;
import component.entity.wizard.OracleCPCreationWizard;
import component.entity.wizard.RestWSCPCreationWizard;
import component.entity.wizard.UnwiredServerCreationWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EE extends RationalTestScript
{
	/**
	 * Script Name   : <b>EnterpriseExplorer</b>
	 * Generated     : <b>Jul 27, 2010 5:42:43 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/07/27
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		dnd("Database Connections->ASA (SQL Anywhere v. 11.0.1.2467) ->sampledb->Tables->contact (dba)", 10, 10);
		dndToOperationFigure(null, null);
	}
	
	public static final java.util.regex.Pattern VERSION_PATTERN = java.util.regex.Pattern.compile("\\s*\\(.*\\)\\s*");
//	public static final java.util.regex.Pattern VERSION_PATTERN = java.util.regex.Pattern.compile("\\([\\w|\\W]+v\\.\\s+[\\d|\\.]+\\)");
	public static final String[] CATEGORY = {"Database Connections", "REST Web Services",
		"SAP Servers", "Unwired Servers", "Web Services"};
	
	public static void importConnectionProfile(String fileName){
		DOF.getToolBar(DOF.getRoot(), "Import").click(atToolTipText("Import"), atPoint(14,10));;
		TopLevelTestObject dialog = DOF.getDialog("Import Connection Profiles");
		DOF.getTextField(dialog, "Specify a file name:").click();
		dialog.inputChars(fileName);
		DOF.getButton(dialog, "OK").click();
		sleep(3);
	}
	
	public static void deletePackageFromUnwiredServer(String unwiredServer, String packageName){
		deletePackageFromUnwiredServer(unwiredServer, "default", packageName);
	}
	
	public static void deletePackageFromUnwiredServer(String unwiredServer, String domainName, String packageName){
		DOF.getEETree().click(RIGHT, atPath(EE.parseResourcePath("Unwired Servers->"+unwiredServer+"->Domains->"+domainName+"->Packages->"+packageName)));
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
	}
	
	public static void deleteAllPackagesFromUnwiredServer(String server){
		for(String pkg:getAllPackagesOnUnwiredServer(server)){
			deletePackageFromUnwiredServer(server, pkg);
		}
	}
	
	private static List<String> getAllPackagesOnUnwiredServer(String server) {
		String path = EE.parseResourcePath("Unwired Servers->"+server+"->Domains->default->Packages");
		TreeHelper.expandTreePath(DOF.getEETree(), path);
		return Arrays.asList(TreeHelper.getChildrenOfNode(DOF.getEETree(), path));
	}

	public static void exportAllConnectionProfiles(String fileName){
		exportConnectionProfile("$ALL", fileName);
	}
	
	public static void connectConnectionProfile(String name){
		for(String node: CATEGORY){
			String[] children = TreeHelper.getChildrenOfNode(DOF.getEETree(), node);
			for(String child: children){
				if(child.equals(name)){
					if(TreeHelper.getChildrenOfNode(DOF.getEETree(), node+"->"+child).length==0){
						DOF.getEETree().click(RIGHT, atPath(node+"->"+child));
						DOF.getContextMenu().click(atPath("Connect"));
						sleep(5);
					}
				}
			}
		}
	}
	
	public static String fullCPName(String name){
		for(String node: CATEGORY){
			String[] children = TreeHelper.getChildrenOfNode(DOF.getEETree(), node);
			for(String child: children){
				if(child.equals(name)){
					return node+"->"+child;
				}
			}
		}
		throw new RuntimeException("Unable to find CP of name: "+name);
	}
	
	public static boolean hasConnectionProfile(String name){
		String[] cpNames = TreeHelper.getNodesOfLevel(DOF.getEETree(),1);
		for(String cpName:cpNames){
			if(cpName.equals(name)){
				return true;
			}else{
				if (cpName.startsWith(name)) {
					String leftOver = cpName.substring(name.length(),
							cpName.length()).trim();
					Matcher m = VERSION_PATTERN.matcher(leftOver);
					if (m.matches()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean hasConnectionProfile2(String name){
		for(String node:CATEGORY){
			String realPath = TreeHelper.matchPath(DOF.getEETree(), Pattern.compile(node+"->"+name+"(\\s*\\(.*\\)\\s*)?"));
			if(realPath!=null){
				return true;
			}
		}
		return false;
	}
	
	public static void exportConnectionProfileOfName(String cpName, String fileName){
		exportConnectionProfile(cpName, fileName);
	}
	
	private static void exportConnectionProfile(String name, String fileName){
		DOF.getToolBar(DOF.getRoot(), "Export").click(atToolTipText("Export"));
		TopLevelTestObject dialog = DOF.getDialog("Export Connection Profiles");
		if(name.equals("$ALL")){
			DOF.getButton(dialog, "&Select all").click();
		}else{
			DOF.getTable(dialog).click(atPath(name+"->Location(CHECKBOX)"));
		}
		DOF.getTextField(dialog, "Specify a file name:").click();
		dialog.inputChars(fileName);
		DOF.getButton(dialog, "OK").click();
	}
	

	public static String parseResourcePath(String path) {
		String[] pathParts = path.split("->");
		if (pathParts.length <= 1) {
			return path;
		} else {
			if (pathParts[0].equals(CATEGORY[0])
					|| pathParts[0].equals(CATEGORY[3])) {
				String[] children = TreeHelper.getChildrenOfNode(DOF
						.getEETree(), pathParts[0]);
				for (String node : children) {
					if (node.equals(pathParts[1])) {
						return path;
					} else {
						if (node.startsWith(pathParts[1])) {
							String leftOver = node.substring(
									pathParts[1].length(), node.length())
									.trim();
							if (VERSION_PATTERN.matcher(leftOver).matches()) {
								pathParts[1] = node;
								String result = "";
								for (int i = 0; i < pathParts.length; i++) {
									result = result + pathParts[i] + "->";
								}
								return result.substring(0, result.length()-2);
							}
						}
					}
				}
			}
			return path;
		}
	}
	
	public static void dnd(String path, int x, int y){
		dndResource(path, x, y);
		sleep(0.5);
		if(DOF.getDialog("Warning")!=null){
			DOF.getButton(DOF.getDialog("Warning"),"&Yes").click();
		}
		LongOperationMonitor.waitForDialog("Quick Create");
		DOF.getButton(DOF.getDialog("Quick Create"), "OK").click();
		sleep(5);
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public static void dnd(String path){
		dndResource(path);
		sleep(0.5);
		if(DOF.getDialog("Warning")!=null){
			DOF.getButton(DOF.getDialog("Warning"),"&Yes").click();
		}
		LongOperationMonitor.waitForDialog("Quick Create");
		DOF.getButton(DOF.getDialog("Quick Create"), "OK").click();
		sleep(5);
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public static void dndResource(String path, int x, int y){
		if(!isPathConnected(path)){
			String cpName = parseCPName(path);
			connectConnectionProfile(cpName);
		}
		String realPath = parseResourcePath(path);
		TreeHelper.expandTreePath(DOF.getEETree(), realPath);
		DOF.getEETree().dragToScreenPoint(atPath(realPath), DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(x,y)));
	}
	
	public static void dndResource(String path){
		Point point = OD.getValidPoint();
		if(!isPathConnected(path)){
			String cpName = parseCPName(path);
			connectConnectionProfile(cpName);
		}
		String realPath = parseResourcePath(path);
		TreeHelper.expandTreePath(DOF.getEETree(), realPath);
		DOF.getEETree().dragToScreenPoint(atPath(realPath), DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(point.x, point.y)));
	}

	public static boolean isPathConnected(String path){
		try{
			DOF.getEETree().click(atPath(path));
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
	}
	
	private static String parseCPName(String path){
		String firstPart = path.substring(0, path.indexOf("->"));
		path = path.replace(firstPart+"->", "");
		String secondPart = path.substring(0, path.indexOf("->"));
		return secondPart;
	}
	
	public static void dndOperationAsAttributes(String path1, int x, int y){
//		String path = EE.parseResourcePath(path1);
//		TreeHelper.expandTreePath(DOF.getEETree(), path);
//		DOF.getEETree().dragToScreenPoint(atPath(path), 
//				DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(x,y)));
		dndResource(path1, x, y);
		LongOperationMonitor.waitForDialog("New Mobile Business Object Options");
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "Attributes").click();
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
		sleep(2);
		DOF.getButton(DOF.getDialog("New Attributes"), "&Finish").click();
		MainMenu.saveAll();
	}
	
	public static void dndOperationAsOperation(String path, int x, int y){
		TreeHelper.expandTreePath(DOF.getEETree(), path);
		DOF.getEETree().dragToScreenPoint(atPath(path), 
				DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(x,y)));
		LongOperationMonitor.waitForDialog("New Mobile Business Object Options");
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "Operation").click();
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
		sleep(5);
	}
	
	public static void createMboOperation(IWizardEntity entity){
		if(entity instanceof WSMBO){
			new EEWSMBOOperationWizard().create(entity, new WizardRunner());
		}
	}
	
	public static void dndToOperationFigure(String path, GefEditPartTestObject operationFigure){
		// Frame: Mobile Development - Sybase Unwired WorkSpace
		DOF.getEETree().dragToScreenPoint(atPath("Database Connections->ASA (SQL Anywhere v. 11.0.1.2467) ->sampledb->Stored Procedures->sp_contacts (dba)"), 
				DOF.getMboOperationContainerFigure(DOF.getRoot(), "Contact").getScreenPoint(atPoint(85,9)));
//		tree2().dragToScreenPoint(atPath("Database Connections->ASA (SQL Anywhere v. 11.0.1.2467) ->sampledb->Stored Procedures->sp_contacts (dba)"), 
//                            toScreenPoint(
//                                        634, 558));
		
	}
	
	public static String getMboAttributeRoleMap(DeployedMbo mbo, String logicRole){
		openMboPropertiesDialog(mbo);
		String result = new DeployedMboPropertiesDialog().getAttributeRoleMap(logicRole);
		closePropertiesDialog();
		return result;
	}
	
	public static String getMboOperationRoleMap(DeployedMbo mbo,String operation,
			String logicRole) {
		openMboPropertiesDialog(mbo);
		String result = new DeployedMboPropertiesDialog().getOperationRoleMap(operation,logicRole);
		closePropertiesDialog();
		return result;
	}

	private static void closePropertiesDialog() {
		DOF.getButton(propertyDialog(), "OK").click();
	}

	private static void openMboPropertiesDialog(DeployedMbo mbo) {
		String path = "Unwired Servers->" + mbo.getConnectionProfile()
				+ "->Domains->" + mbo.getDomain() + "->Packages->"
				+ mbo.getPkg() + "->Mobile Business Objects->" + mbo.getName();
		String actualPath = EE.parseResourcePath(path);
		TreeHelper.expandTreePath(DOF.getEETree(), actualPath);
		DOF.getEETree().click(RIGHT, atPath(actualPath));
		DOF.getContextMenu().click(atPath("Properties"));
	}

	private static TopLevelTestObject propertyDialog() {
		String[] allDialogCaptions = new String[]{"Properties Dialog"};
		for(String caption:allDialogCaptions){
			if(DOF.getDialog(caption)!=null){
				return DOF.getDialog(caption);
			}
		}
		return null;
	}
	
	public static String getPakckageType(DeployedMbo mbo){
		openPackagePropertiesDialog(mbo);
		String type = DOF.getLabelByBound(propertyDialog(), new Rectangle(121,41,291,13)).getProperty("text").toString();
		closePropertiesDialog();
		return type;
	}
	
	public static String getPakckageName(DeployedMbo mbo){
		openPackagePropertiesDialog(mbo);
//		
//		TestObject[] texts = DOF.getDialog("Properties Dialog").find(RationalTestScript.atDescendant(
//				"class", "org.eclipse.swt.widgets.Text"));
//		System.out.println(texts.length);
//		for(TestObject text:texts){
//			System.out.println(text.getProperty("bounds"));
//		}
		
		String type = DOF.getLabelByBound(propertyDialog(), new Rectangle(121,5,291,13)).getProperty("text").toString();
		closePropertiesDialog();
		return type;
	}
	//domain:23
	public static String getPakckageSecurityConfig(DeployedMbo mbo){
		openPackagePropertiesDialog(mbo);
		String type = DOF.getLabelByBound(propertyDialog(), new Rectangle(121,59,291,13)).getProperty("text").toString();
		closePropertiesDialog();
		return type;
	}
	
	public static String getUnwiredServerCPHostName(String cpName){
		openUnwiredServerCPPropertiesDialog(cpName);
		TestObject dialog = DOF.getDialog("Properties for "+cpName);
		DOF.getTree(dialog).click(atPath("Unwired Server Connection Properties"));
		sleep(1);
		String host = DOF.getTextField(dialog, "Host:").getProperty("text").toString();
		DOF.getButton(dialog, "OK").click();
		return host;
	}
	
	public static String getUnwiredServerCPPort(String cpName){
		openUnwiredServerCPPropertiesDialog(cpName);
		TestObject dialog = DOF.getDialog("Properties for "+cpName);
		DOF.getTree(dialog).click(atPath("Unwired Server Connection Properties"));
		sleep(1);
		String host = DOF.getTextField(dialog, "Port:").getProperty("text").toString();
		DOF.getButton(dialog, "OK").click();
		return host;
	}
	

	private static void openUnwiredServerCPPropertiesDialog(String cpName) {
		String realPath = EE.parseResourcePath("Unwired Servers->"+cpName);
		DOF.getEETree().click(RIGHT, atPath(realPath));
		DOF.getContextMenu().click(atPath("Properties"));
	}

	private static GuiSubitemTestObject propertiesDialogTree() {
		return DOF.getTree(propertyDialog());
	}

	private static void openPackagePropertiesDialog(DeployedMbo mbo) {
		String path = "Unwired Servers->" + mbo.getConnectionProfile()
			+ "->Domains->" + mbo.getDomain() + "->Packages->"
			+ mbo.getPkg();
		String actualPath = EE.parseResourcePath(path);
		TreeHelper.expandTreePath(DOF.getEETree(), actualPath);
		DOF.getEETree().click(RIGHT, atPath(actualPath));
		DOF.getContextMenu().click(atPath("Properties"));
		sleep(2);
	}
	
	public static boolean ifMboDeployed(DeployedMbo mbo){
		String path = "Unwired Servers->" + mbo.getConnectionProfile()
		+ "->Domains->" + mbo.getDomain() + "->Packages->"
		+ mbo.getPkg().toLowerCase() + "->Mobile Business Objects";
		String actualPath = EE.parseResourcePath(path);
//		TreeHelper.expandTreePath(DOF.getEETree(), actualPath);
		try{
			DOF.getEETree().click(atPath(actualPath+"->"+mbo.getName()));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void renameCP(String from, String to){
//		DOF.getEETree().click(RIGHT, atPath(from));
		DOF.getEETree().click(RIGHT, atPath(getCPFullPath(from)));
		DOF.getContextMenu().click(atPath("Rename"));
		TopLevelTestObject dialog = DOF.getDialog("Rename Dialog");
		DOF.getTextField(dialog).click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(to);
		DOF.getButton(dialog, "OK").click();
	}

	public static void deleteCP(String string) {
		if (EE.hasConnectionProfile2(string)) {
			DOF.getEETree().click(RIGHT, atPath(getCPFullPath(string)));
			DOF.getContextMenu().click(atPath("Delete"));
			DOF.getButton(DOF.getDialog("Delete Confirmation"), "&Yes").click();
			sleep(3);
		}
	}
	
	public static void deleteCP(String string, Class clazz) {
		if(hasConnectionProfile(string, clazz)){
			DOF.getEETree().click(RIGHT, atPath(getCPFullPath(string)));
			DOF.getContextMenu().click(atPath("Delete"));
			DOF.getButton(DOF.getDialog("Delete Confirmation"), "&Yes").click();
			sleep(3);
		}
	}

	public static String getCPFullPath(String path) {
		if(!path.contains("->")){
			for(String category:CATEGORY){
				Pattern p1 = Pattern.compile(category+"->"+path);
				Pattern p2 = Pattern.compile(category+"->"+path+EE.VERSION_PATTERN);
				String path1 = TreeHelper.matchPath(DOF.getEETree(), p1);
				String path2 = TreeHelper.matchPath(DOF.getEETree(), p2);;
				if(path1!=null){
					return path1;
				}
				if(path2!=null){
					return path2;
				}
			}
		}
		return path;
	}

	public static String getMboDataSourceType(DeployedMbo mbo) {
		openMboPropertiesDialog(mbo);
		propertiesDialogTree().click(atPath("Data Source"));
		String type = getMboDataSourceType();
		closePropertiesDialog();
		return type;
	}

	private static String getMboDataSourceType() {
		return DOF.getLabelByBound(propertyDialog(), new Rectangle(100,5,292,13)).getProperty("text").toString();
	}

	public static boolean mboHasOperation(DeployedMbo deployedMbo, String operation) {
		openMboPropertiesDialog(deployedMbo);
		propertiesDialogTree().click(atPath("Operations"));
		boolean result = ListHelper.hasItem(DOF.getList(propertyDialog()), operation);
		closePropertiesDialog();
		return result;
	}

	public static void createASACP(ASACP cp, boolean deleteOld) {
		if(!EE.hasConnectionProfile(cp.getName())){
			new ASACPCreationWizard().create(cp, new WizardRunner());
		}
		else{
			if(deleteOld){
				EE.deleteCP(cp.getName());
				new ASACPCreationWizard().create(cp, new WizardRunner());
			}
		}
	}
	
	public static void createDBCP(GenericDBCP cp, boolean deleteOld){
		if (EE.hasConnectionProfile(cp.getName()) && deleteOld) {
			EE.deleteCP(cp.getName());
		}
		//fanfei>>>>>>>>>>>>>>>>>use to new create a cp which has existed >>>>>>
		else if (EE.hasConnectionProfile(cp.getName()) && (deleteOld ==  false)){
			if(cp instanceof DB2CP){
				new DB2CPCreationWizard().create(cp, new WizardRunner());
			}else if(cp instanceof OracleCP){
				new OracleCPCreationWizard().create(cp, new WizardRunner());
			}
			else{
				new DBCPCreationWizard().create(cp, new WizardRunner());
			}
		}
		//fanfei<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		if(!EE.hasConnectionProfile(cp.getName())){
				if(cp instanceof DB2CP){
					new DB2CPCreationWizard().create(cp, new WizardRunner());
				}else if(cp instanceof OracleCP){
					new OracleCPCreationWizard().create(cp, new WizardRunner());
				}
				else{
					new DBCPCreationWizard().create(cp, new WizardRunner());
				}
			}
//			new ASACPCreationWizard().create(cp, new WizardRunner());
		
	}

	public static Hashtable<String,String> getMboDataSourceProperties(DeployedMbo deployedMbo) {
		openMboPropertiesDialog(deployedMbo);
		propertiesDialogTree().click(atPath("Data Source"));
		GuiSubitemTestObject table = DOF.getTable(propertyDialog());
		Hashtable<String, String> result = new Hashtable<String, String>();
		int rowIndex = 0;
		while (true) {
			try {
				String value = TableHelper.getCellValue(table, rowIndex,
						"Value").trim();
				result.put(TableHelper
						.getCellValue(table, rowIndex, "Property").trim(),
						value);
				rowIndex++;
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
		closePropertiesDialog();
		return result;
	}

	public static void openScrapbook() {
		DOF.getToolBar(DOF.getRoot(), "New Connection Profile").click(atToolTipText("Open scrapbook to edit SQL statements"));
	}
	
	public static void closeScrapbook(){
		DOF.getCTabItem(DOF.getRoot(), "*SQL Scrapbook 0").click(RIGHT);
		DOF.getContextMenu().click(atPath("Close"));
		ConfirmSaveResourceDialog.no();
		sleep(3);
	}

	public static void refresh(String path) {
		DOF.getEETree().click(RIGHT, atPath(parseResourcePath(path)));
		DOF.getContextMenu().click(atPath("Refresh"));
		sleep(5);
	}

	public static void createSAPCP(SAPCP cp, boolean deleteExisting) {
		if(TreeHelper.ifEETreeItemExist(DOF.getEETree(), "SAP Servers->"+cp.getName())){
			if(deleteExisting){
				EE.deleteCP(cp.getName());
				new SAPCPCW().create(cp, new WizardRunner());
			}
		}else{
			new SAPCPCW().create(cp, new WizardRunner());
		}
		
	}

	public static void createWSCP(WSCP cp, boolean deleteExisting) {
		if(TreeHelper.ifEETreeItemExist(DOF.getEETree(), "Web Services->"+cp.getName())){
			if(deleteExisting){
				EE.deleteCP(cp.getName());
				new WSCPCW().create(cp, new WizardRunner());
			}
		}else{
			new WSCPCW().create(cp, new WizardRunner());
		}
	}

	public static void runSQL(ScrapbookCP cp, String tableCreationSQL) {
		SQLEditor.runSql(cp, new File(tableCreationSQL));
		closeScrapbook();
		refreshDB(cp);
	}
	
	public static void refreshDB(ScrapbookCP cp){
		if(cp.getDatabase()=="pubs3"){
			EE.refresh("Database Connections->"+cp.getName()+"->Databases->"+cp.getDatabase()+"->Tables");
		}
		else{
			EE.refresh("Database Connections->"+cp.getName()+"->"+cp.getDatabase()+"->Tables");
		}
		sleep(20);
	}

	public static void copyConnectionProfile(String source, String target) {
		EE.deleteCP(target.substring(target.lastIndexOf("->")+2, target.length()));
		String actual = TreeHelper.matchPath(DOF.getEETree(), Pattern.compile(source+".*"));
		DOF.getEETree().click(RIGHT, atPath(actual));
		DOF.getContextMenu().click(atPath("Copy"));
		String category = target.substring(0, target.lastIndexOf("->"));
		String newName = target.substring(target.lastIndexOf("->")+2, target.length());
		DOF.getEETree().click(RIGHT, atPath(category));
		DOF.getContextMenu().click(atPath("Paste"));
		DOF.getDialog("Profile Name Input Dialog").inputChars(newName);
		DOF.getDialog("Profile Name Input Dialog").inputKeys(SpecialKeys.ENTER);
	}
	
	public static void backupDBTableData(String tablePath, String project, String sqlfile){
		String realPath = EE.parseResourcePath(tablePath);
		DOF.getEETree().click(RIGHT, atPath(realPath));
		DOF.getContextMenu().click(atPath("Edit Data"));
		DOF.getButton(DOF.getDialog("Table Data Filter"), "OK").click();
		sleep(3);
		DOF.getTable(DOF.getRoot()).click(RIGHT,atCell(atRow(0),atColumn(1)));
		DOF.getContextMenu().click(atPath("Extract Table Data..."));
		TopLevelTestObject dialog = DOF.getDialog("Extract Table Data");
		DOF.getTextFieldByBound(dialog, new Rectangle(0,7,385,19)).click();
		dialog.inputChars(project);
		DOF.getTextField(dialog, "File name:").click();
		dialog.inputChars(sqlfile);
		DOF.getCombo(dialog, "File type:").click();
		DOF.getCombo(dialog, "File type:").click(atText("SQL (*.sql)"));
		DOF.getButton(dialog, "OK").click();
		sleep(3);
		MainMenu.saveAll();
		MainMenu.closeAll();
	}
	
	public static void restoreDBTableData(String tablePath, String sqlfilePath){
		DOF.getEETree().click(RIGHT, atPath(tablePath));
		DOF.getContextMenu().click(atPath("Truncate Table"));
		DOF.getButton(DOF.getDialog("Sybase WorkSpace"), "Yes").click();
		sleep(5);
		DOF.getWNTree().click(RIGHT, atPath(sqlfilePath));
		DOF.getContextMenu().click(atPath("Execute SQL Files"));
		sleep(5);
	}

	public static void importConnectionProfile(String cp, boolean overWrite) {
		DOF.getToolBar(DOF.getRoot(), "Import").click(atToolTipText("Import"), atPoint(14,10));;
		TopLevelTestObject dialog = DOF.getDialog("Import Connection Profiles");
		DOF.getTextField(dialog, "Specify a file name:").click();
		dialog.inputChars(cp);
		sleep(1);
		if(overWrite){
			((ToggleGUITestObject)DOF.getButton(dialog, "&Overwrite existing connection profiles with same names")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog, "&Overwrite existing connection profiles with same names")).clickToState(NOT_SELECTED);
		}
		DOF.getButton(dialog, "OK").click();
		sleep(3);
		
	}

	public static void createMbo(IWizardEntity mbo) {
		if(mbo instanceof RestWSMbo){
			new EERestWSMBOCreationWizard().create(mbo, new WizardRunner());
		}
		else if(mbo instanceof WSMBO){
			new EEWSMBOCreationWizard().create(mbo, new WizardRunner());
		}
		else if(mbo instanceof DbMbo){
			new EEAttributeDbMboCreationWizard().create(mbo, new WizardRunner());
		}
		else if(mbo instanceof RestWSOperation){
			new EEOperationRestWSMboCreationWizard().create(mbo, new WizardRunner());
		}
		else if(mbo instanceof Operation){
			new EEOperationDbMboCreationWizard().create(mbo, new WizardRunner());
		}else{
			throw new RuntimeException("Unkown type: "+mbo.getClass());
		}
	}

	public static boolean testConnectionToCP(String string) {
		DOF.getEETree().click(RIGHT, atPath(fullCPName(string)));
		DOF.getContextMenu().click(atPath("Ping..."));
		LongOperationMonitor.waitForDialogToVanish("Ping server job");
		sleep(1);
		String title = getScreen().getActiveWindow().getText();
		if(title.equals("Success")){
			DOF.getButton(DOF.getDialog("Success"), "OK").click();
			return true;
		}else{
			DOF.getButton(DOF.getDialog("Error"), "OK").click();
			return false;
		}
	}

	public static void createRestWSCP(RestWSCP cp) {
		EE.deleteCP(cp.getName(), RestWSCP.class);
		new RestWSCPCreationWizard().create(cp, new WizardRunner());
		
	}
	
	private static void deleteRestWSCP(String name) {
		try{
			DOF.getEETree().click(RIGHT, atPath("REST Web Services->"+name));
			DOF.getContextMenu().click(atPath("Delete"));
			DOF.getButton(DOF.getDialog("Delete Confirmation"), "&Yes").click();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static boolean hasConnectionProfile(String string, Class cpClass) {
		if(cpClass.getName().equals("component.entity.model.RestWSCP") ){
			try{
				DOF.getEETree().click(atPath("REST Web Services->"+string));
				return true;
			}catch(SubitemNotFoundException e){
				return false;
			}
		}else{
			return EE.hasConnectionProfile(string);
		}
	}

	public static void setRestWSCP(RestWSCP cp) {
		if(hasConnectionProfile(cp.getName(), RestWSCP.class)){
			DOF.getEETree().click(RIGHT, atPath("REST Web Services->"+cp.getName()));
			DOF.getContextMenu().click(atPath("Properties"));
			RestWSPropertiesDialog.setRestWSCP(cp, DOF.getDialog("Properties for "+cp.getName()));
			RestWSPropertiesDialog.ok(DOF.getDialog("Properties for "+cp.getName()));
		}
	}

	public static void createUnwiredServerCP(UnwiredServerCP cp, boolean deleteExisting) {
		if(TreeHelper.ifEETreeItemExist(DOF.getEETree(), "Unwired Servers->"+cp.getName())){
			if(deleteExisting){
				EE.deleteCP(cp.getName());
				new UnwiredServerCreationWizard().create(cp, new WizardRunner());
			}
		}else{
			new UnwiredServerCreationWizard().create(cp, new WizardRunner());
		}
	}

	public static boolean canConnectCP(String cpName) {
		DOF.getEETree().click(RIGHT, atPath(EE.getCPFullPath(cpName)));
		DOF.getContextMenu().click(atPath("Ping..."));
		while(true){
			String title = LongOperationMonitor.waitForDialog();
//			System.out.println(title);
			if(title.equals("Success")){
				DOF.getButton(DOF.getDialog(title), "OK").click();
				return true;
			}else if(title.equals("Error")){
				DOF.getButton(DOF.getDialog(title), "OK").click();
				return false;
			}
			sleep(1);
		}
	}

	public static boolean ifWorkFlowDeployed(DeployedWorkFlow wf) {
		try{
			DOF.getEETree().click(atPath(EE.parseResourcePath("Unwired Servers->"+wf.getUnwiredServer()+"->Workflows->"+wf.getName()+":"+wf.getVersion())));
			return true;
		}catch(SubitemNotFoundException e){
			e.printStackTrace();
			return false;
		}
	}

	public static void deleteWorkFlow(DeployedWorkFlow wf) {
		DOF.getEETree().click(RIGHT, atPath(EE.parseResourcePath("Unwired Servers->"+wf.getUnwiredServer()+"->Workflows->"+wf.getName()+":"+wf.getVersion())));
		DOF.getContextMenu().click(atPath("Delete"));
		sleep(1);
		DOF.getButton(DOF.getDialog("Delete"), "OK").click();
	}

	public static void deleteAllWorkFlowsFromUnwiredServer(String server) {
		String path = EE.parseResourcePath("Unwired Servers->"+server+"->Workflows");
		TreeHelper.expandTreePath(DOF.getEETree(), path);
		String [] workflows = TreeHelper.getChildrenOfNode(DOF.getEETree(), path);
		for(String workflow:workflows){
			DOF.getEETree().click(RIGHT, atPath(EE.parseResourcePath("Unwired Servers->"+server+"->Workflows->"+workflow)));
			DOF.getContextMenu().click(atPath("Delete"));
			sleep(1);
			DOF.getButton(DOF.getDialog("Delete"), "OK").click();
		}
	}

//	public static void createOperation(String string, Operation operation) {
//		dndResource()
//	}


}

