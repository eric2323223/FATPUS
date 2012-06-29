package component.entity;

import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.MenuHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.dialog.SearchDialog;
import component.entity.model.PK;
import component.entity.model.SearchCriteria;
import component.entity.model.WizardRunner;
import component.entity.search.FileSearchPanel;
import component.entity.search.SUPSearchPanel;
import component.entity.wizard.MainMenuPKCreationWizard;

public class MainMenu extends RationalTestScript{
	static String workspace;
	
	static{
		DOF.getMenu().click(atPath("File->Switch Workspace->Other..."));
		workspace = DOF.getCombo(DOF.getDialog("Workspace Launcher"), "Workspace:").getProperty("text").toString();
		DOF.getButton(DOF.getDialog("Workspace Launcher"), "Cancel").click();
	}
	
	public static void openView(String name) {
		if (DOF.getCTabFolder(DOF.getRoot(), name) == null) {
			DOF.getMenu().click(atPath("Window->Show View->Other..."));
			TopLevelTestObject dialog = DOF.getDialog("Show View");
			DOF.getTextField(dialog).click();
			dialog.inputChars(name);
			DOF.getTextField(dialog).click();
			dialog.inputKeys(SpecialKeys.ENTER);
			sleep(1);
			dialog.inputKeys(SpecialKeys.ENTER);
			sleep(1);
		}else{
			DOF.getCTabFolder(DOF.getRoot(), name).click(atText(name));
		}
	}
	
	public static void createPK(PK pk){
		new MainMenuPKCreationWizard().create(pk, new WizardRunner());
	}
	
	public static void resetPerspective(){
		DOF.getMenu().click(atPath("Window->Reset Perspective..."));
		LongOperationMonitor.waitForDialog("Reset Perspective");
//		DOF.getButton(DOF.getDialog("Reset Perspective"), "OK").click();
		DOF.getButton(DOF.getDialog("Reset Perspective"), "&Yes").click();
		sleep(3);
	}
	
	public static void saveAll(){
		if(MenuHelper.isItemEnabled(DOF.getMenu(), "File->Save All")){
			DOF.getMenu().click(atPath("File->Save All"));
		}
	}
	
	public static void closeAll(){
		if(MenuHelper.isItemEnabled(DOF.getMenu(), "File->Close All")){
			DOF.getMenu().click(atPath("File->Close All"));
		}
	}

	public static void closeView(String title) {
		DOF.getCTabFolder(DOF.getRoot(), title).click(RIGHT, atText(title));
		DOF.getContextMenu().click(atPath("Close"));
	}
	
	public static String getCurrentWorkspace(){
		return workspace;
	}

	public static void importProject(String projectArchiveFile) {
		DOF.getMenu().click(atPath("File->Import..."));
		DOF.getTree(importDialog()).click(atPath("General->Existing Projects into Workspace"));
		DOF.getButton(importDialog(), "&Next >").click();
		DOF.getButton(importDialog(), "Select &archive file:").click();
		DOF.getTextFieldByBound(importDialog(), new Rectangle(128, 33, 233, 19)).click();
		importDialog().inputChars(projectArchiveFile);
		importDialog().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		DOF.getButton(importDialog(), "&Finish").click();
		sleep(3);
	}
	
	public static void importProject(String projectArchiveFile, String project) {
		DOF.getMenu().click(atPath("File->Import..."));
		DOF.getTree(importDialog()).click(atPath("General->Existing Projects into Workspace"));
		DOF.getButton(importDialog(), "&Next >").click();
		DOF.getButton(importDialog(), "Select &archive file:").click();
		DOF.getTextFieldByBound(importDialog(), new Rectangle(128, 33, 233, 19)).click();
		importDialog().inputChars(projectArchiveFile);
		importDialog().inputKeys(SpecialKeys.ENTER);
		DOF.getButton(importDialog(), "&Deselect All").click();
		sleep(1);
		DOF.getTree(importDialog()).click(atPath(project+" ("+project+")->Location(CHECKBOX)"));
		DOF.getButton(importDialog(), "&Finish").click();
		sleep(3);
	}

	private static TopLevelTestObject importDialog() {
		return DOF.getDialog("Import");
	}

	public static void openPerspective(String string) {
		DOF.getMenu().click(atPath("Window->Open Perspective->Other..."));
		GuiSubitemTestObject table = DOF.getTable(DOF.getDialog("Open Perspective"));
		table.click(atText(string));
		DOF.getButton(DOF.getDialog("Open Perspective"), "OK").click();
		sleep(3);
	}

	public static void searchMbo(String string) {
		DOF.getMenu().click(atPath("Search->Unwired Workspace..."));
		SearchDialog.setSearchString(string);
		SearchDialog.search();
		
	}

	public static void getItemmenurefer(String project, String item, String detailitem,String refertype){	
		if(item.equals("pk"))
			DOF.getWNTree().click(RIGHT, atPath(WN.pkPath(project, detailitem)));
		if(item.equals("role"))
			DOF.getWNTree().click(RIGHT, atPath(WN.rolePath(project, detailitem)));
		if(item.equals("dsr"))
			DOF.getWNTree().click(RIGHT, atPath(WN.dataSourceReferencepath(project, detailitem)));
		if(item.equals("mbo"))
			DOF.getWNTree().click(RIGHT, atPath(WN.mboPath(project, detailitem)));
		
		if (refertype.equals("allreference"))
			DOF.getMenu().click(atPath("Search->References->All References"));
		if (refertype.equals("operation"))
			DOF.getMenu().click(atPath("Search->References->Operation"));
		if (refertype.equals("mbo"))
			DOF.getMenu().click(atPath("Search->References->Mobile Business Object"));
			}
	

	
	public static final int SEARCH_FILE = 0;
	public static final int SEARCH_C = 1;
	public static final int SEARCH_JAVA = 2;
	public static final int SEARCH_JS = 3;
	public static final int SEARCH_PLUGIN = 4;
	public static final int SEARCH_SUP = 5;
	
	public static void search(int catagory,String projname,String searchway,SearchCriteria sc){
		switch(catagory){
//		case SEARCH_FILE:
//			FileSearchPanel.searchway(searchway,sc);
//			break;
//		case SEARCH_C:
//			CSearchPanel.search(sc);//need to creat new class like FileSearchPanel.class
//			break;
//		case SEARCH_JAVA:
//			JAVASearchPanel.search(sc);//need to creat new class like FileSearchPanel.class
//			break;
//		case SEARCH_JS:
//			JavaScriptSearchPanel.search(sc);//need to creat new class like FileSearchPanel.class
//			break;
//		case SEARCH_PLUGIN:
//			PlugInSearchPanel.search(sc);//need to creat new class like FileSearchPanel.class
//			break;
		case SEARCH_SUP:
			SUPSearchPanel.searchway(projname, searchway, sc);
			break;
		}
	}
}
