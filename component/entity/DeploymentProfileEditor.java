package component.entity;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.dialog.properties.DeploymentPackageConfigurationDialog;
import component.entity.model.DeployOption;
import component.entity.verifier.Verifier;

public class DeploymentProfileEditor extends RationalTestScript{
	String project;
	String profileName;
	
	private GuiSubitemTestObject packageTree(){
		return DOF.getTree(DOF.getRoot(),"Packages:");
	}

	public DeploymentProfileEditor(String projectName, String profileName) {
		this.project = projectName;
		this.profileName = profileName;
	}
	
//	public void edit(Modifier modifier){
//		if(modifier.getMethod().equals(Modifier.TYPE_ADD)){
//			String attribute = modifier.getAttribute();
//			String value = modifier.getValue();
//			if(attribute.equals("pkg))
//		}else if(modifier.getMethod().equals(Modifier.TYPE_REMOVE)){
//			
//		}else{
//			
//		}
//	}
	
	public void openEditor(){
		DOF.getWNTree().doubleClick(atPath(WN.projectNameWithVersion(project)+"->"+profileName+".profile"));
		sleep(2);
	}

	public void configureDeploymentPackage(String packageName, DeployOption option) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
//		DOF.getButton(DOF.getRoot(), "Configure Package...").click();
		packageTree().doubleClick(atPath("/"+project+"/"+packageName+".pkg->"+option.getServer()));
		String dialogTitle = "Configuration for "+packageName+".pkg on "+option.getServer();
		sleep(3);
		DeploymentPackageConfigurationDialog.setSeverConnectionMapping(option.getServerConnectionMapping(), DOF.getDialog(dialogTitle));
		DeploymentPackageConfigurationDialog.ok(DOF.getDialog(dialogTitle));
		MainMenu.saveAll();
	}

	public void deployPackages() {
		openEditor();
		DOF.getButton(DOF.getRoot(), "Deploy Packages").click();
		LongOperationMonitor.waitForDialog("Deployment Status");
		DOF.getButton(DOF.getDialog("Deployment Status"), "OK").click();
	}

	public List<String> getDeploymentPackageTargets(String pkgName) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		String path = "/"+project+"/"+pkgName;
		packageTree().click(atPath(path+"->Location(PLUS_MINUS)"));
		sleep(1);
		return Arrays.asList(TreeHelper.getChildrenOfNode(packageTree(), path));
	}

	public void addDeploymentPackageTarget(String pkgName, String serverName) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		packageTree().click(atPath("/"+project+"/"+pkgName));
		DOF.getButton(DOF.getRoot(), "Add Target...").click();
		TopLevelTestObject serverDialog = DOF.getDialog("Select Target Server");
		String node = TreeHelper.matchPath(DOF.getTree(serverDialog), Pattern.compile(serverName+".*"));
		DOF.getTree(serverDialog).doubleClick(atPath(node));
	}

	public void removeDeploymentPackageTarget(String pkgName, String serverName) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		packageTree().click(atPath("/"+project+"/"+pkgName+"->"+serverName));
		DOF.getButton(DOF.getRoot(), "Remove Target...").click();
		
	}

	public void changeDeploymentPackageTarget(String pkgName, String from,
			String to) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		packageTree().click(atPath("/"+project+"/"+pkgName+"->"+from));
		DOF.getButton(DOF.getRoot(), "Change Target...").click();
		TopLevelTestObject serverDialog = DOF.getDialog("Select Target Server");
		String node = TreeHelper.matchPath(DOF.getTree(serverDialog), Pattern.compile(to+".*"));
		DOF.getTree(serverDialog).doubleClick(atPath(node));
		
	}

	public List<String> getDeploymentPackageServers() {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		GuiSubitemTestObject serverTable = DOF.getTable(DOF.getTable(DOF.getSection(DOF.getRoot(), "Servers")));
		return Arrays.asList(TableHelper.getColumnData(serverTable, 0));
	}

	public void deleteDeploymentPackage(String pkgName) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		packageTree().click(atPath("/"+this.project+"/"+pkgName+".pkg"));
		DOF.getButton(DOF.getRoot(), "Remove Package").click();

	}

	public void deleteTarget(String pkgName, String target) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		packageTree().click(atPath("/"+this.project+"/"+pkgName+".pkg->"+target));
		DOF.getButton(DOF.getRoot(), "Remove Target...").click();
		
	}

	public void setName(String newName) {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Overview").click(atText("Overview"));
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(newName);
	}

	public boolean deploy() {
		openEditor();
		DOF.getCTabFolder(DOF.getRoot(), "Configuration").click(atText("Configuration"));
		DOF.getButton(DOF.getRoot(), "Deploy packages").click();
		sleep(3);
		if(DOF.getDialog("Problem Occurred")!=null){
			DOF.getButton(DOF.getDialog("Problem Occurred"),"OK").click();
			return false;
		}else{
			LongOperationMonitor.waitForDialog("Deployment Status");
			String string = DOF.getTextFieldByAncestorLine(DOF.getDialog("Deployment Status"), "Composite->Shell->Shell").getProperty("text").toString();
			boolean result = string.startsWith("Success")? true: false;
			DOF.getButton(DOF.getDialog("Deployment Status"), "OK").click();
			return result;
		}
	}
}
