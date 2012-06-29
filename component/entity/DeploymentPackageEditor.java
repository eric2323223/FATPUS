package component.entity;

import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.dialog.MboSelectionDialog;
import component.entity.model.DeploymentPackage;

public class DeploymentPackageEditor extends RationalTestScript {
	String projectName;
	String pkgName;

	public DeploymentPackageEditor(String projectName, String string) {
		this.projectName = projectName;
		this.pkgName = string;
	}

	public void setPackageName(String string2) {
		openEditor();
		DOF.getCTabItem(DOF.getRoot(), "Configuration").click();
		DOF.getTextField(DOF.getRoot(), "Package name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(string2);
		MainMenu.saveAll();
		closeEditor();
	}
	
	private void openEditor(){
		String path = WN.projectNameWithVersion(projectName)+"->"+pkgName;
		DOF.getWNTree().doubleClick(atPath(path));
	}

	public String getPackageName() {
		openEditor();
		DOF.getCTabItem(DOF.getRoot(), "Configuration").click();
		String name = DOF.getTextField(DOF.getRoot(), "Package name:").getProperty("text").toString();
//		MainMenu.saveAll();
		closeEditor();
		return name;
	}
	
	private void closeEditor(){
		DOF.getCTabItem(DOF.getRoot(), pkgName).click(RIGHT);
		DOF.getContextMenu().click(atPath("Close"));
	}

	public void removeMbo(String string) {
		openEditor();
		DOF.getCTabItem(DOF.getRoot(), "Configuration").click();
		contentsTable().click(atText(string));
		DOF.getButton(DOF.getRoot(), "&Remove").click();
		MainMenu.saveAll();
		closeEditor();
	}
	
	public void addMbo(String string){
		openEditor();
		DOF.getCTabItem(DOF.getRoot(), "Configuration").click();
		DOF.getButton(DOF.getRoot(), "&Add...").click();
		MboSelectionDialog.selectMbo(DOF.getDialog("Add Mobile Business Objects"), string);
		MboSelectionDialog.finish(DOF.getDialog("Add Mobile Business Objects"));
		MainMenu.saveAll();
		closeEditor();
	}
	
	private GuiSubitemTestObject contentsTable(){
		TestObject section = DOF.getSection(DOF.getRoot(), "Contents");
		return DOF.getTable(section);
	}
	
	private GuiSubitemTestObject rolesTable(){
		TestObject section = DOF.getSection(DOF.getRoot(), "Roles");
		return DOF.getTable(section);
		
	}

	public List<String> getMbos() {
		openEditor();
		DOF.getCTabItem(DOF.getRoot(), "Configuration").click();
		String[] data = TableHelper.getColumnData(contentsTable(), 0);
		List<String> result = new ArrayList<String>();
		for(String mbo:data){
			result.add(mbo);
		}
		closeEditor();
		return result;
	}

}
