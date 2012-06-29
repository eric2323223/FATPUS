package component.entity;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.JarUtil;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.common.XMLUtil;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.dialog.CacheGroupPropertiesDialog;
import component.dialog.ConfirmResourceDeleteDialog;
import component.dialog.MoveTargetSelectionDialog;
import component.dialog.PKPropertiesDialog;
import component.dialog.RenameResourceDialog;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.customJsGenerator.GlobalAction;
import component.entity.customJsGenerator.JsFileHelper;
import component.entity.customJsGenerator.ScreenTransitionAction;
import component.entity.model.CGOption;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.DeploymentPackage;
import component.entity.model.DeploymentProfile;
import component.entity.model.GeneratedFile;
import component.entity.model.IWizardEntity;
import component.entity.model.Interval;
import component.entity.model.LocalMbo;
import component.entity.model.MigrationResult;
import component.entity.model.ObjectQuery;
import component.entity.model.Operation;
import component.entity.model.PK;
import component.entity.model.Relationship;
import component.entity.model.RestWSMbo;
import component.entity.model.Role;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.SyncGroup;
import component.entity.model.SynchronizationGroup;
import component.entity.model.WSMBO;
import component.entity.model.WSOperation;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
//import component.entity.model.WorkFlowPackage;
import component.entity.wizard.CacheGroupCreationWizard;
import component.entity.wizard.DbMboCreationWizard;
import component.entity.wizard.DeploymentProfileCreationWizard;
import component.entity.wizard.ObjectQueryWizard;
import component.entity.wizard.PKCreationWizard;
import component.entity.wizard.PackageDeployWizard;
import component.entity.wizard.RestWSMboCreationWizard;
import component.entity.wizard.RoleCreationWizard;
import component.entity.wizard.WNWSOCW;
import component.entity.wizard.WSOperationCW;
import component.entity.wizard.WorkFlowCreateionWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class WN extends RationalTestScript
{
	/**
	 * Script Name   : <b>WorkSpaceNavigator</b>
	 * Generated     : <b>Jul 27, 2010 5:46:44 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/07/27
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		createPK(new PK().name("IK1").type("STRING").startParameter(WN.projectNameWithVersion("db_asa")));
	}
	
	static final java.util.regex.Pattern RPOJECT_VERSION = java.util.regex.Pattern.compile("\\[Version\\s+.*\\]");
	
	public static void createProject(String projectName){
		DOF.getMenu().click(atPath("File->New->Mobile Application Project"));
		TopLevelTestObject dialog = DOF.getDialog("New Mobile Application Project");
		DOF.getTextField(dialog, "Project name:").click();
		dialog.inputChars(projectName);
		DOF.getButton(dialog, "&Finish").click();
		sleep(2);
	}
	
	public static boolean hasProjectOfName(String name) {
		String[] projects = TreeHelper.getTopLevelNodes(DOF.getWNTree());
		if(projects==null)
			return false;
		for (String project : projects) {
//			System.out.println(project);
			if (project.equals(projectNameWithVersion(name))) {
				return true;
			}
		}
		return false;
	}
	
	public static void deleteProject(String projectName){
		if (hasProjectOfName(projectName)) {
			DOF.getWNTree().click(RIGHT,
					atPath(projectNameWithVersion(projectName)));
			DOF.getContextMenu().click(atPath("Delete"));

			DOF.getButton(DOF.getDialog("Confirm Project Delete"), 0).click();
			DOF.getButton(DOF.getDialog("Confirm Project Delete"), "&Yes")
					.click();
			sleep(3);
		}
	}
	
	public static void expandAll(){
//		System.out.println(DOF.getToolBar(DOF.getRoot(), "Expand All").getProperty("itemCount"));
		DOF.getToolBar(DOF.getRoot(), "Expand All").click(atToolTipText("Expand All"), atPoint(12,9));
	}
	
	public static void collapseAll(){
//		System.out.println(DOF.getToolBar(DOF.getRoot(), "Collapse All").getProperty("itemCount"));
		DOF.getToolBar(DOF.getRoot(), "Expand All").click(atToolTipText("Collapse All"), atPoint(15,9));
	}
	
	public static String projectNameWithVersion(String name){
		String[] projects = TreeHelper.getTopLevelNodes(DOF.getWNTree());
		for(String project:projects){
			if(project!=null && project.startsWith(name)){
				String leftOver = project.substring(name.length(), project.length()).trim();
				if(RPOJECT_VERSION.matcher(leftOver).matches()){
					return project;
				}
			}
		}
		return name;
	}

	public static void useProject(String projName) {
		closeWelcome();
		Preference.switchToAdavanceProfile();
		Preference.setEnterpriseExplorerSubGrouping("no grouping");
		if(hasProjectOfName(projName)){
			deleteProject(projName);
		}
		createProject(projName);
	}
	
	public static void exclusiveUseProject(String project){
		deleteAllProject();
		useProject(project);
	}
	
	public static void deleteAllProject() {
		if(WN.getAllProjects()!=null){
			for(String project:WN.getAllProjects()){
				deleteProject(project);
			}
		}
	}

	public static void closeWelcome(){
		if(DOF.getCTabFolder(DOF.getRoot(), "Welcome")!=null){
			DOF.getCTabFolder(DOF.getRoot(), "Welcome").click(RIGHT, atText("Welcome"));
			DOF.getContextMenu().click(atPath("Close"));
		}
	}
	
	public static void useProject(String projName, boolean deleteExisting) {
		if(hasProjectOfName(projName)){
			if (deleteExisting) {
				deleteProject(projName);
				createProject(projName);
				}
		}else{
			createProject(projName);
		}
	}

	public static String[] getAllProjects() {
		return TreeHelper.getTopLevelNodes(DOF.getWNTree());
	}

	public static String mboFolderPath(String projectName) {
		return projectNameWithVersion(projectName)+"->Mobile Business Objects";
	}
	
	public static void createPK(PK pk){
		new PKCreationWizard().create(pk, new WizardRunner());
	}
	
	public static void selectMBO(String projectName, String mboName){
		DOF.getWNTree().doubleClick(atPath(WN.projectNameWithVersion(projectName)+"->Mobile Business Objects->"+mboName));
//		sleep(2);
	}
	
	public static String[] getAllMboInProject(String projectName){
		TreeHelper.expandTreePath(DOF.getWNTree(), projectNameWithVersion(projectName)+"->Mobile Business Objects");
		return TreeHelper.getChildrenOfNode(DOF.getWNTree(), projectNameWithVersion(projectName)+"->Mobile Business Objects");
	}

	public static boolean hasMboInProject(String projectName, String mbo){
		for(String name:getAllMboInProject(projectName)){
			if(name.equals(mbo)){
				return true;
			}
		}
		return false;
	}
	
	public static void copyMbo(String project, String string, String string2) {
		String basePath = WN.projectNameWithVersion(project)+"->Mobile Business Objects";
		String sourcePath = basePath+"->"+string;
		String targetPath = basePath+"->CopyOf"+string;
		DOF.getWNTree().click(RIGHT, atPath(sourcePath));
		DOF.getContextMenu().click(atPath("Copy"));
		DOF.getWNTree().click(RIGHT, atPath(basePath));
		DOF.getContextMenu().click(atPath("Paste"));
		DOF.getWNTree().click(RIGHT, atPath(targetPath));
		DOF.getContextMenu().click(atPath("Refactor->Rename..."));
		DOF.getTextField(DOF.getDialog("Rename Resource")).click();
		DOF.getDialog("Rename Resource").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("Rename Resource").inputChars(string2);
		DOF.getButton(DOF.getDialog("Rename Resource"), "OK").click();
		MainMenu.saveAll();
	}
	
	public static void importProjectFromFolder(String fileName){
		DOF.getMenu().click(atPath("File->Import..."));
		TopLevelTestObject dialog = DOF.getDialog("Import");
		DOF.getTree(dialog).click(atPath("General->Existing Projects into Workspace"));
		DOF.getButton(dialog, "&Next >").click();
		DOF.getButton(dialog, "Select roo&t directory:").click();
		DOF.getTextFieldByBound(dialog, new Rectangle(128,33,233,19));
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(fileName);
		dialog.inputKeys(SpecialKeys.ENTER);
		DOF.getButton(dialog, "&Finish").click();
		sleep(3);
	}
	
	public static void importProjectFromFile(String fileName){
		DOF.getMenu().click(atPath("File->Import..."));
		TopLevelTestObject dialog = DOF.getDialog("Import");
		DOF.getTree(dialog).click(atPath("General->Existing Projects into Workspace"));
		DOF.getButton(dialog, "&Next >").click();
		DOF.getButton(dialog, "Select &archive file:").click();
		DOF.getTextFieldByBound(dialog, new Rectangle(128,5,233,19));
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(fileName);
		dialog.inputKeys(SpecialKeys.ENTER);
		sleep(1);
		DOF.getButton(dialog, "&Finish").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog);
	}
	
	public static void exportProject(String project, String file){
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(project)));
		DOF.getContextMenu().click(atPath("Export..."));
		TopLevelTestObject dialog = DOF.getDialog("Export");
		DOF.getTree(dialog).click(atPath("General->Archive File"));
		DOF.getButton(dialog, "&Next >").click();
		DOF.getCombo(dialog, "To archive file:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(file);
//		dialog.inputKeys(SpecialKeys.ENTER);
		DOF.getButton(DOF.getDialog("Export"), "&Finish").click();
		DOF.getButton(DOF.getDialog("Export"), "&Finish").click();
		sleep(3);
	}

	public static boolean isPathExist(String projectName, String path) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), WN.projectNameWithVersion(projectName)+"->"+path);
	}
	
	public static String realPath(String path){
		String project = path.substring(0, path.indexOf("->"));
		String rest = path.substring(path.indexOf("->"), path.length());
		return projectNameWithVersion(project)+rest;
	}

	public static boolean hasMethodInGeneratedFile(GeneratedFile file, String methodName) {
		DOF.getWNTree().doubleClick(atPath(realPath(file.getWNPath())));
		MainMenu.openView("Outline");
		GuiSubitemTestObject tree = DOF.getTreeByRootElement(DOF.getRoot(), file.getPackageName());
		boolean isExist;
		String path = file.getFileName().substring(0, file.getFileName().indexOf("."))+"->"+methodName;
		try{
			tree.click(atPath(path));
			isExist = true;
		}catch(Exception e){
			e.printStackTrace();
			isExist = false;
		}
		MainMenu.closeView("Outline");
		MainMenu.closeView(file.getFileName());
		return isExist;
	}

	public static boolean hasGeneratedFile(GeneratedFile file) {
		String folder = file.getFolder()==null?"":file.getFolder()+"->";
		String path = realPath(file.getProject()+"->"+folder+"src->"+
				file.getPackageName()+"->"+file.getFileName());
		path = path.replace("null->", "");
		System.out.println(path);
		try{
			DOF.getWNTree().click(atPath(path));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
//		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), path);
	}
	
	public static void createDeployementPackage(DeploymentPackage dp){
		new DeployementPackageCreationWizard().create(dp, new WizardRunner());
	}
	
	public static boolean buildPackage(String project, String fileName){
		String packageJarFile = WN.projectNameWithVersion(project)+ "->"+fileName+".pkg";
		DOF.getWNTree().click(RIGHT, atPath(packageJarFile));
		DOF.getContextMenu().click(atPath("Build Package (Full)..."));
		sleep(1);
		if(DOF.getDialog("Problem Occurred")!=null){
			DOF.getButton(DOF.getDialog("Problem Occurred"), "OK").click();
			return false;
		}
		LongOperationMonitor.waitForDialog("Success");
		DOF.getButton(DOF.getDialog("Success"), "OK").click();
		return true;
	}

	public static File generateDU(String projectName){
		new DeployementPackageCreationWizard().create(new DeploymentPackage()
			.startParameter(WN.projectNameWithVersion(projectName))
			.mbo("all"), new WizardRunner());
		buildPackage(projectName, projectName);
		String projectPath = MainMenu.getCurrentWorkspace()+"\\"+projectName;
		String jarPath = projectPath +"\\Deployment\\"+projectName+".pkg\\"+projectName+".jar";
		File rawFile;
		try {
			rawFile = JarUtil.extractFile(jarPath, "deployment_unit.xml", projectPath);
			return FileUtil.replaceContent(rawFile, "xmlns=\".*\"", "");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to generate DU");
		}
	}

	public static String mboPath(String projectName, String mboName) {
		return WN.projectNameWithVersion(projectName)+"->Mobile Business Objects->"+mboName;
	}
	
	public static void createLocalMbo(LocalMbo mbo){
		new WNLocalMBOCW().create(mbo, new WizardRunner());
	}
	
	public static void createRelationship(Relationship relationship){
		new WNRelationshipCW().create(relationship, new WizardRunner());
	}

	public static void createDbMbo(DbMbo mbo) {
		new DbMboCreationWizard().create(mbo, new WizardRunner());
		
	}

	public static void deployProject(DeployOption deployOption) {
		new DeployWizard().deploy(deployOption, new WizardRunner());
	}

	public static void addOperation(Operation operation) {
		new WNOCW().create(operation, new WizardRunner());
	}

	public static void generateCode(CGOption cg) {
		new CodeGenerationWizard().generate(cg, new WizardRunner());
	}

	public static boolean openLegacyProjectMbo(String project, String mbo, boolean willShowLater) {
		DOF.getWNTree().doubleClick(atPath(WN.mboPath(project, mbo)));
		sleep(2);
		if(DOF.getDialog("Migrate")!=null){
			ToggleTestObject button = (ToggleTestObject)DOF.getButton(DOF.getDialog("Migrate"), "&Do not show this dialog again");
			if(willShowLater==true){
				button.setState(SELECTED);
			}else{
				button.setState(NOT_SELECTED);
			}
			DOF.getButton(DOF.getDialog("Migrate"), "&Yes").click();
//			LongOperationMonitor.waitForDialogToVanish("Progress Information");
			LongOperationMonitor.waitForDialog("Migrating Results", 30);	
			return true;
		}else{
//			LongOperationMonitor.waitForDialogToVanish("Progress Information");
			LongOperationMonitor.waitForDialog("Migrating Results", 30);			
		    return false;
		}
	}
	
	public static MigrationResult getMigratingResult() {
		if(DOF.getDialog("Migrating Results")!=null){
			GuiSubitemTestObject table = DOF.getTable(DOF.getDialog("Migrating Results"));
			int rowCount = TableHelper.getRowCount(table);
			List<String> results = new ArrayList<String>();
			for(int i=0;i<rowCount;i++){
				results.add(TableHelper.getCellValue(table, i, 0));
			}
			MigrationResult result = new MigrationResult(results);
			DOF.getButton(DOF.getDialog("Migrating Results"), "OK").click();
			return result;
		}
		List<String> noResult =new ArrayList<String>();
		MigrationResult noresult = new MigrationResult(noResult);
		if(DOF.getDialog("Information")!=null){
			DOF.getButton(DOF.getDialog("Information"), "OK").click();
		}
		return noresult;
//		return null;
	}

	public static String getModelTagVersionOfProject(String project) {
		try {
			String modelFile = MainMenu.getCurrentWorkspace() + "//" + project
					+ "//sup.model";
			Document document;
			document = XMLUtil.readFile(new File(modelFile));
			Node node = document.selectSingleNode("//uepmodels:EMODiagram");
			return node.valueOf("@modelVersion");
		} catch (DocumentException e) {
//			e.printStackTrace();
			throw new RuntimeException("Failed to parse ["+project+"] model file");
		}
	}

	public static CachePolicy getCachePolicy(String project, String group) {
		DOF.getWNTree().click(RIGHT, atPath(WN.cacheGroupPath(project, group)));
		DOF.getContextMenu().click(atPath("Properties"));
		TestObject dialog = DOF.getDialog("Properties for "+group);
		DOF.getTree(dialog).click(atPath("Policies"));
		sleep(1);
		GuiTestObject ondemandButton = DOF.getButton(dialog, "On de&mand   ");
		CachePolicy result;
		String hour = DOF.getSpinner(dialog, "Specify the number of hours for the duration").getProperty("text").toString();
		String minute = DOF.getSpinner(dialog, "Specify the number of minutes for the duration").getProperty("text").toString();
		String second = DOF.getSpinner(dialog, "Specify the number of seconds for the duration").getProperty("text").toString();
		Interval interval = new Interval(new Integer(hour).intValue(), new Integer(minute).intValue(), new Integer(second).intValue());
		if(ondemandButton.getProperty("selection").toString().equals("true")){
			result = new CachePolicy(CachePolicy.ONDEMAND, interval, false);
		}else{
			throw new RuntimeException("TBD");
		}
		DOF.getButton(DOF.getDialog("Properties for "+group), "OK").click();
		return result;
	}

//	private static String cacheGroupPath(String project, String group) {
//		String escapedString = new String(WN.projectNameWithVersion(project)+"->Cache Groups->"+group).replace("[", "\\[");
//		Pattern pattern = Pattern.compile(escapedString+" (\\(Default\\))+");
//		return TreeHelper.matchPath(DOF.getWNTree(), pattern);
//	}
	
	private static String cacheGroupPath(String project, String group) {
		return WN.projectNameWithVersion(project)+"->Cache Groups->"+group+" (Default)";

	}

	public static SyncGroup getSyncGroup(String project, String group) {
		DOF.getWNTree().click(RIGHT, atPath(WN.syncGroupPath(project, group)));
		DOF.getContextMenu().click(atPath("Properties"));
		TestObject dialog = DOF.getDialog("Properties for "+group);
		SyncGroup result;
		String hour = DOF.getSpinner(dialog, "Input change detection interval in hours, which defines how frequently the data changes of the synchronization group can get server's attention.").getProperty("text").toString();
		String minute = DOF.getSpinner(dialog, "Input change detection interval in minutes, which defines how frequently the data changes of the synchronization group can get server's attention.").getProperty("text").toString();
		String second = DOF.getSpinner(dialog, "Input change detection interval in seconds, which defines how frequently the data changes of the synchronization group can get server's attention.").getProperty("text").toString();
		Interval interval = new Interval(new Integer(hour).intValue(), new Integer(minute).intValue(), new Integer(second).intValue());
		result = new SyncGroup(project, interval);
		DOF.getButton(DOF.getDialog("Properties for "+group), "OK").click();
		return result;
	}

	private static String syncGroupPath(String project, String group) {
		return WN.projectNameWithVersion(project)+"->Synchronization Groups->"+group+" (Default)";

	}
//	
//	private static String syncGroupPath(String project, String group) {
//		String escapedString = new String(WN.projectNameWithVersion(project)+"->Synchronization Groups->"+group).replace("[", "\\[");
//		Pattern pattern = Pattern.compile(escapedString+" (\\(Default\\))+");
//		return TreeHelper.matchPath(DOF.getWNTree(), pattern);
//	}

	public static PK getPersonalizationKey(String project, String pk) {
		DOF.getWNTree().click(RIGHT, atPath(WN.pkPath(project, pk)));
		DOF.getContextMenu().click(atPath("Properties"));
		TestObject dialog = DOF.getDialog("Properties for "+pk);
		PK result;
		String type = DOF.getCombo(dialog, "Type:").getProperty("text").toString();
		String defaultValue = DOF.getCCombo(dialog).getProperty("text").toString();
		String nullable = DOF.getButton(dialog, "N&ullable").getProperty("selection").toString();
		String protect = DOF.getButton(dialog, "P&rotected").getProperty("selection").toString();
		result = new PK().type(type).defaultValue(defaultValue).nullable(nullable).protect(protect);
		DOF.getButton(DOF.getDialog("Properties for "+pk), "OK").click();
		return result;

	}

	public static String pkPath(String project, String pk) {
		return WN.projectNameWithVersion(project)+"->Personalization Keys->"+pk;
	}

	public static String rolePath(String project, String role) {
		return WN.projectNameWithVersion(project)+"->Roles->"+role;
	}
	
	public static String dataSourceReferencepath(String project, String dsr) {
		return WN.projectNameWithVersion(project)+"->Data Source References->"+dsr;
	}
	
	///////////////8.15 >>>>>>>
	public static void getItemrightrefer(String project, String item, String detailitem,String refertype){	
		if(item.equals("pk"))
			DOF.getWNTree().click(RIGHT, atPath(WN.pkPath(project, detailitem)));
		if(item.equals("role"))
			DOF.getWNTree().click(RIGHT, atPath(WN.rolePath(project, detailitem)));
		if(item.equals("dsr"))
			DOF.getWNTree().click(RIGHT, atPath(WN.dataSourceReferencepath(project, detailitem)));
		if(item.equals("mbo"))
			DOF.getWNTree().click(RIGHT, atPath(WN.mboPath(project, detailitem)));
		
		if (refertype.equals("allreference"))
			DOF.getContextMenu().click(atPath("References->All References"));
		if (refertype.equals("operation"))
			DOF.getContextMenu().click(atPath("References->Operation"));
		if (refertype.equals("mbo"))
			DOF.getContextMenu().click(atPath("References->Mobile Business Object"));
	}
	//<<<<<<<<<<<<<<<<
	public static boolean hasDeployementPackage(String projectName, String string) {
		return hasTreeNode(projectName, string);
	}

	public static void createWsMbo(WSMBO mbo) {
		new WSMBOCreationWizard().create(mbo, new WizardRunner());
		
	}

	public static boolean hasFilter(String projectName, String string) {
		return hasTreeNode(projectName, "Filters->"+string);
	}
	
	private static boolean hasTreeNode(String projectName, String node){
		try{
			DOF.getWNTree().click(atPath(WN.projectNameWithVersion(projectName) + "->" + node));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public static Properties getProjectProperties(String project) {
		DOF.getWNTree().click(RIGHT, atPath(projectNameWithVersion(project)));
		DOF.getContextMenu().click(atPath("Properties"));
		sleep(1);
		return new Properties("Properties for "+project);
	}

	public static File getFilterFilePath(String proj_name, String string) {
		String workspace = MainMenu.getCurrentWorkspace();
		return new File(workspace+"\\"+proj_name+"\\Filters\\"+string);
	}

	public static void rename(String path, String name) {
		DOF.getWNTree().click(RIGHT, atPath(realPath(path)));
		DOF.getContextMenu().click(atPath("Refactor->Rename..."));
		sleep(1);
		DOF.getDialog("Rename Type").inputChars(name);
		DOF.getDialog("Rename Type").inputKeys(SpecialKeys.ENTER);
		sleep(3);
	}

	public static void moveJavaFileToNewPackge(String file, String pkg) {
		DOF.getWNTree().click(RIGHT, atPath(realPath(file)));
		DOF.getContextMenu().click(atPath("Refactor->Move..."));
		DOF.getButton(DOF.getDialog("Move"), "Cr&eate Package...").click();
		sleep(1);
		DOF.getDialog("New Java Package").inputChars(pkg);
		DOF.getDialog("New Java Package").inputKeys(SpecialKeys.ENTER);
		sleep(1);
		DOF.getButton(DOF.getDialog("Move"), "OK").click();
		sleep(3);
		
	}

	public static void createSAPMbo(SAPMBO parameter) {
		new SAPMBOCreationWizard().create(parameter, new WizardRunner());
	}

	public static void setJavaFileContent(String path, String content) {
		DOF.getWNTree().doubleClick(atPath(WN.realPath(path)));
		sleep(1);
		ClipBoardUtil.setClipboardText(content);
		DOF.getStyledText(DOF.getRoot()).click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getStyledText(DOF.getRoot()).click(RationalTestScript.RIGHT);
		DOF.getContextMenu().click(RationalTestScript.atPath("Paste"));
		MainMenu.saveAll();
		RationalTestScript.sleep(3);
	}

	public static void addSAPOperation(SAPOperation operation) {
		new WNSAPOCW().create(operation, new WizardRunner());
		
	}
	
	public static void addOperation(IWizardEntity operation){
		if(operation instanceof SAPOperation){
			new WNSAPOCW().create(operation, new WizardRunner());
		}
		if(operation instanceof WSOperation){
			new WNWSOCW().create(operation, new WizardRunner());
		}
	}

	public static void createMbo(Object mbo) {
		if(mbo instanceof DbMbo){
			createDbMbo((DbMbo)mbo);
		}
		else if(mbo instanceof SAPMBO){
			createSAPMbo((SAPMBO)mbo);
		}
		else if(mbo instanceof WSMBO){
			createWsMbo((WSMBO)mbo);
		}
		else if(mbo instanceof RestWSMbo){
			createRestWSMbo((RestWSMbo)mbo);
		}else{
			throw new RuntimeException("Unkonw type of mbo: "+mbo.getClass());
		}
	}

	private static void createRestWSMbo(RestWSMbo mbo) {
		new RestWSMboCreationWizard().create(mbo, new WizardRunner());
		
	}

	public static void deleteDU(String projectName) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->Deployment"));
		DOF.getContextMenu().click(atPath("Delete"));
		DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
		sleep(2);
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+projectName+".pkg"));
		DOF.getContextMenu().click(atPath("Delete"));
		DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
		sleep(2);
		
		
	}

	public static void openDiagramEditor(String parameter) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(atPath("Open in Diagram Editor"));
		
	}

	public static void setPK(String project, String pk, PK key) {
		DOF.getWNTree().click(RIGHT, atPath(WN.pkPath(project, pk)));
		DOF.getContextMenu().click(atPath("Properties"));
		
		PKPropertiesDialog.setPK(DOF.getDialog("Properties for "+pk), key);
		
		
//		TestObject dialog = DOF.getDialog("Properties for "+pk);
//		String type = DOF.getCombo(dialog, "Type:").getProperty("text").toString();
//		String defaultValue = DOF.getCCombo(dialog).getProperty("text").toString();
//		String nullable = DOF.getButton(dialog, "N&ullable").getProperty("selection").toString();
//		String protect = DOF.getButton(dialog, "P&rotected").getProperty("selection").toString();
//		result = new PK().type(type).defaultValue(defaultValue).nullable(nullable).protect(protect);
//		DOF.getButton(DOF.getDialog("Properties for "+pk), "OK").click();


	}

	public static void deployPackage(DeployOption deployOption) {
		new PackageDeployWizard().deploy(deployOption, new WizardRunner());
	}

	public static String filePath(String projectName, String string) {
		return WN.projectNameWithVersion(projectName)+"->"+string;
	}

	public static boolean hasFolderInProject(String proj_name, String string) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), WN.projectNameWithVersion(proj_name)+"->"+string);
	}

	public static void createCacheGroup(CacheGroup name) {
		new CacheGroupCreationWizard().create(name, new WizardRunner());
	}

	public static Object hasCacheGroup(String proj_name, String string) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+string);
	}

	public static CacheGroup getCacheGroup(String proj_name, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+string));
		DOF.getContextMenu().click(atPath("Properties"));
		CacheGroupPropertiesDialog dialog = new CacheGroupPropertiesDialog("Properties for "+string);
		
		CacheGroup group= new CacheGroup().name(dialog.getName())
			.hour(dialog.getHours())
			.minute(dialog.getMinutes())
			.second(dialog.getSeconds())
			.type(dialog.getType())
			.partition(dialog.getPartition());
		dialog.close();
		return group;
	}

	public static String setCacheGroup(String proj_name, String string,CacheGroup cg) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+string));
		DOF.getContextMenu().click(atPath("Properties"));
		sleep(0.5);
		CacheGroupPropertiesDialog dialog = new CacheGroupPropertiesDialog("Properties for "+string.replace("(Default)", "").trim());
		
		dialog.setCacheGroup(cg);
		sleep(2);
		String message = null;
		
		if(messageText(string)!=null && !messageText(string).getProperty("text").toString().trim().equals("")){
			message = messageText(string).getProperty("text").toString();
//			dialog.Cancel();
//		}else{
		}
		dialog.OK();
		return message;
	}
	
	private static GuiTestObject messageText(String string){
		return (GuiTestObject)DOF.getTextFieldByAncestorLine(DOF.getDialog("Properties for "+string.replace("(Default)", "").trim()), 
		"Composite->Composite->Composite->Composite->Composite->Composite->Composite->Shell->Shell"); 

	}

	public static void deleteCacheGroup(String proj_name, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+string));
		DOF.getContextMenu().click(atPath("Delete"));
		DOF.getButton(DOF.getDialog("Confirm Delete"), "&Yes").click();
	}

	public static void changeCacheGroup(String proj_name, String mbo,
			String from, String to) {
		((ScrollGuiSubitemTestObject)DOF.getWNTree()).drag(atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+from+"->"+mbo), 
	              atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+to));
		sleep(1);
		if(DOF.getDialog("Confirm")!=null){
			DOF.getButton(DOF.getDialog("Confirm"),"OK").click();
		}
		if(DOF.getDialog("Confirm Changes")!=null){
			DOF.getButton(DOF.getDialog("Confirm Changes"),"OK").click();
		}
//		DOF.getButton(DOF.getDialog("Confirm"), "OK").click();
		MainMenu.saveAll();
	}

	public static boolean hasMboInCacheGroup(String proj_name, String group,String mbo) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+group+"->"+mbo);
	}

	public static void setDefaultCacheGroup(String proj_name, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups->"+string));
		DOF.getContextMenu().click(atPath("Set as Default"));
		
	}

	public static void createSynchronizationGroup(SynchronizationGroup sg){
		new SyncGroupCreationWizard().create(sg, new WizardRunner());
	}

	public static void setDefaultSyncGroup(String proj_name, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Synchronization Groups->"+string));
		DOF.getContextMenu().click(atPath("Set as Default"));
	}

	public static Object hasMboInSyncGroup(String proj_name, String syncGroup,String mbo) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(), WN.projectNameWithVersion(proj_name)+"->Synchronization Groups->"+syncGroup+"->"+mbo);
	}

	public static void addWSOperation(WSOperation wsOperation) {
		new WSOperationCW().create(wsOperation, new WizardRunner());
		
	}

	public static void copyCacheGroup(String proj_name, String from,
			String to) {
		DOF.getWNTree().click(RIGHT, atPath(WN.cacheGroupPath(proj_name, from)));
		DOF.getContextMenu().click(atPath("Copy"));
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->Cache Groups"));
		DOF.getContextMenu().click(atPath("Paste"));
		DOF.getDialog("Name Conflict").inputChars(to);
		DOF.getDialog("Name Conflict").inputKeys(SpecialKeys.ENTER);
		sleep(2);
	}

	public static void createRole(Role role) {
		new RoleCreationWizard().create(role, new WizardRunner());
		
	}

	public static void createObjectQuery(ObjectQuery objectQuery) {
		new ObjectQueryWizard().create(objectQuery, new WizardRunner());
		
	}

	public static void getResultCheckerReferences(String proj_name,	String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.filePath(proj_name, "Filters->"+string)));
		DOF.getContextMenu().click(atPath("References->Mobile Business Object"));
		sleep(3);
	}

	public static void addMboToDeploymentPackage(String projectName, String pkgname, String mbo) {
//		DOF.getWNTree().doubleClick(atPath(WN.filePath(projectName, string+".pkg")));
//		return DeploymentPackageEditor.getDeploymentPackage(string+".pkg");
		DeploymentPackageEditor pkgEditor = new DeploymentPackageEditor(projectName, pkgname+".pkg");
		pkgEditor.addMbo(mbo);
		
	}

	public static List<String> getDeploymentPackageMbos(String projectName, String pkgname) {
		return new DeploymentPackageEditor(projectName, pkgname+".pkg").getMbos();
	}

	public static boolean hasDeployementJar(String projectName, String string) {
		return TreeHelper.ifEETreeItemExist(DOF.getWNTree(),WN.projectNameWithVersion(projectName)+"->Deployment->"+string+".pkg->deploy.jar");
	}

	public static void createDeploymentProfile(DeploymentProfile pkg) {
		new DeploymentProfileCreationWizard().create(pkg, new WizardRunner());
		
	}

	public static boolean hasDeploymentProfile(String projectName, String string) {
		return WN.isPathExist(projectName, string+".profile");
	}

	public static void configureDeploymentPackage(String projectName,String profileName, String pkgName, DeployOption option) {
//		DeploymentPackageEditor;
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.configureDeploymentPackage(pkgName, option);
		MainMenu.saveAll();
	}

	public static void deployThroughDeploymentProfile(String projectName,String profileName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.deployPackages();
		
	}

	public static List<String> getDeploymentPackageTargets(String projectName,String profileName, String pkgName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		return editor.getDeploymentPackageTargets(pkgName);
	}

	public static void addDeploymentPackageTarget(String projectName,
			String profileName, String pkgName, String serverName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.addDeploymentPackageTarget(pkgName, serverName);
		MainMenu.saveAll();
	}

	public static void removeDeploymentPackageTarget(String projectName,
			String profileName, String pkgName, String serverName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.removeDeploymentPackageTarget(pkgName, serverName);
		MainMenu.saveAll();
	}

	public static void changeDeploymentPackageTarget(String projectName,String profileName, String pkgName, String from, String to) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.changeDeploymentPackageTarget(pkgName, from, to);
		MainMenu.saveAll();
		
		
	}

	public static void addExternalJarToBuildPath(String projectName,String androidJar) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)));
		DOF.getContextMenu().click(atPath("Properties"));
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Properties for "+ projectName);
		DOF.getTree(dialog).click(atPath("Java Build Path"));
		sleep(1);
		DOF.getTabFolder(dialog, "&Libraries").setState(SINGLE_SELECT, atText("&Libraries"));
		DOF.getButton(dialog, "Add E&xternal JARs...").click();
//		System.out.println(androidJar);
		sleep(1);
		getScreen().getActiveWindow().inputChars(androidJar);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		DOF.getButton(dialog, "OK").click();
		sleep(10);
	}

	public static void moveDeploymnetPackageToFolder(String projectName,
			String deployPkg, String toFolder) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+deployPkg));
		DOF.getContextMenu().click(atPath("Move..."));
		MoveTargetSelectionDialog.setDestinition(projectName+"/"+toFolder);

		
	}

	public static void deleteDepoymentPackage(String projectName, String pkgName) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+pkgName));
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
		
	}

	public static void renameDepoymentPackage(String projectName,
			String from, String to) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+from));
		DOF.getContextMenu().click(atPath("Rename"));
		DOF.getRoot().inputChars(to);
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		if(DOF.getDialog("Resource Exists")!=null){
			DOF.getButton(DOF.getDialog("Resource Exists"), "&Yes").click();
		}
		
	}

	public static void moveDeploymentProfileToProject(String projectName,
			String profileName, String toProject) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+profileName+".profile"));
		DOF.getContextMenu().click(atPath("Move..."));
		MoveTargetSelectionDialog.setDestinition(toProject);
		
	}

	public static void deleteDeplymentProfile(String projectName, String profileName) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+profileName+".profile"));
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
		
	}

	public static void removeDeploymentPackageFromDepolymentProfile(String projectName, String profileName, String pkgName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.deleteDeploymentPackage(pkgName);
		MainMenu.saveAll();
		
	}

	public static void removeTargetFromDepolymentProfile(String projectName,
			String profileName, String pkgName, String target) {
		
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.deleteTarget(pkgName, target);
		MainMenu.saveAll();
		
	}

	public static void setDeploymentProfileName(String projectName,
			String profileName, String newName) {
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		editor.setName(newName);
		MainMenu.saveAll();
		
	}

	public static void deleteMbo(String projectName, String mbo) {
		DOF.getWNTree().click(RIGHT,atPath(WN.projectNameWithVersion(projectName)+"->Mobile Business Objects->"+mbo));
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
		MainMenu.saveAll();
	}

	public static boolean deployDeployemntProfile(String projectName, String profileName) {
		
		DeploymentProfileEditor editor = new DeploymentProfileEditor(projectName, profileName);
		return editor.deploy();
	}

	public static void deleteFolder(String proj_name, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(proj_name)+"->"+string))	;
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
	}

	public static void renameMbo(String proj_name, String from, String to) {
		DOF.getWNTree().click(RIGHT, atPath(WN.mboPath(proj_name, from)));
		DOF.getContextMenu().click(atPath("Refactor->Rename..."));
		RenameResourceDialog.setName(to);
		MainMenu.saveAll();
	}

	public static boolean hasPK(String projectName, String pk) {
		return WN.isPathExist(projectName, "Personalization Keys->"+pk);
	}

	public static void deletePK(String projectName, String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->Personalizaton Keys->"+string));
		DOF.getContextMenu().click(atPath("Delete"));
		ConfirmResourceDeleteDialog.delete();
	}

	public static void renameDeploymentProfile(String projectName,String from, String to) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+from));
		DOF.getContextMenu().click(atPath("Rename"));
		DOF.getRoot().inputChars(to);
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		if(DOF.getDialog("Resource Exists")!=null){
			DOF.getButton(DOF.getDialog("Resource Exists"), "&Yes").click();

		}
	}

	public static boolean closeAll(){
		try{
		DOF.getMenu().click(atPath("File->Close All"));
		return true;
		}catch (Exception e){
			System.out.print("can not close all diagram!");
			return false;
		}	
	}
	
	public static void createWorkFlow(WorkFlow wf){
		new WorkFlowCreateionWizard().create(wf, new WizardRunner());
	}

	public static void openWorkFlow(String project, String wf) {
		if(!wf.endsWith("xbw")){
			wf = wf+".xbw";
		}
		DOF.getWNTree().doubleClick(atPath(WN.projectNameWithVersion(project)+"->"+wf));
		sleep(1);
	}

	public static void createWorkFlowPackage(WorkFlowPackage wfp) {
		new WorkFlowPackageCreationWizard().create(wfp, new WizardRunner());

	}
	
	public static String wfPath(String project, String mbo) {
		if(mbo.endsWith(".xbw")){
			return WN.filePath(project, mbo);
		}else{
			return WN.filePath(project, mbo);
			
		}
	}
	
	public static String wfPathwf(String project, String mbo) {
		
			return WN.filePath(project, mbo);
		
	}

	
	public static void refresh(String path) {
		DOF.getWNTree().click(RIGHT, atPath(path));
		DOF.getContextMenu().click(atPath("Refresh"));
		sleep(3);
	}

	public static String operationPath(String project, String mbo, String operation) {
		return mboPath(project, mbo)+"->Operations->"+operation;
	}

	public static boolean hasWorkFlowInProject(String project, String wf) {
		try{
			DOF.getWNTree().click(atPath(WN.filePath(project, wf+".xbw")));
			
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
	}
	
	public static boolean hasGeneratedWorkFlowFile(String project,String wf, String file) {
		try{
			DOF.getWNTree().click(atPath(WN.filePath(project, "Generated Workflow->"+wf+"->"+file)));
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
	}

	public static void renameProject(String project,String to){
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(project)));
		DOF.getContextMenu().click(atPath("Rename"));
		DOF.getRoot().inputChars(to);
		sleep(1);
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		MainMenu.saveAll();
	}

	public static boolean hasFile(String projectName, String filePath) {
		try{
			DOF.getWNTree().click(atPath(filePath(projectName, filePath)));
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
	}

	public static Object hasFile(String filePath) {
		try{
			DOF.getWNTree().click(atPath(filePath));
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
		
	}
	
	//FF11.25>>>>>>>>>>>>
	public static void restart(){
		DOF.getMenu().click(atPath("File->Restart"));
	}
	
	//ff 1.9>>>>>>>>>>>>>>
	public static void openWFWithRight(String projectName,String wf) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projectName)+"->"+wf));
		DOF.getContextMenu().click(atPath("Open"));
		}

	public static String generateWorkFlowFilePath(String projectName,String wfName, String filePath) {
		return WN.projectNameWithVersion(projectName)+"->Generated Workflow->"+wfName+"->"+filePath;
	}

	public static String getAbsoluteFilePath(String projectPath) {
		DOF.getWNTree().click(RIGHT, atPath(projectPath));
		DOF.getContextMenu().click(atPath("Properties"));
		sleep(0.5);
		TopLevelTestObject dialog = DOF.getDialog(DOF.getTopDialogName());
		String path = DOF.getTextField(dialog, "Location:").getProperty("text").toString();
		DOF.getButton(dialog, "OK").click();
		return path;
	}


}

