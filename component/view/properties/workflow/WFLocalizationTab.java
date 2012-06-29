package component.view.properties.workflow;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TabitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.RationalTestScriptConstants;
import com.rational.test.ft.script.SubitemFactory;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.entity.MainMenu;
import component.entity.Preference;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.model.WFLocalFile;

/**
 * Common method for localization Tab.
 * @author flv
 *
 */
public class WFLocalizationTab {

	/**
	 * add local file for WF.
	 * @param localf 
	 * @param message
	 */
	public static void WFAddLocalization(WFLocalFile localf, StringBuffer message) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		PropertiesView.clickTab("Localization");
		DOF.getButton(DOF.getRoot(), "&New...").click();
		TopLevelTestObject dialog = DOF.getDialog("New Locale");
		DOF.getCombo(dialog, "Language:").click();
		RationalTestScript.sleep(0.5);
		DOF.getCombo(dialog, "Language:").click(SubitemFactory.atText(localf.getLanguage()));
		//DOF.getPoppedUpList().click();
		if (null != localf.getCountry()) {
			DOF.getCombo(dialog, "Country:").click();
			RationalTestScript.sleep(0.5);
			DOF.getCombo(dialog, "Country:").click(SubitemFactory.atText(localf.getCountry()));
		}
		TestObject[] text = dialog.find(SubitemFactory.atDescendant("class",	"org.eclipse.swt.widgets.Text"));
		if (null != text) {
			message.append(text[1].getProperty("text").toString());
		}
		if (null != localf.getVariant()) {
			for (String val : localf.getVariant().split("/")) {
				DOF.getTextField(dialog, "Variant:").click();
				dialog.inputKeys(SpecialKeys.CLEARALL);
				RationalTestScript.sleep(0.5);
				DOF.getTextField(dialog, "Variant:").click();
				dialog.inputChars(val);
				RationalTestScript.sleep(0.5);
				message.append("; ");
				message.append(text[1].getProperty("text").toString());	
			}
		}
		if (null != localf.getOverwrite() && "true".equals(localf.getOverwrite())) {
			((ToggleGUITestObject)DOF.getButton(dialog, "&Overwrite existing file")).clickToState(RationalTestScriptConstants.SELECTED);
			message.append("; ");
			message.append(text[1].getProperty("text").toString());
		}
		if (null != localf.getDefaultlocal()) {
			((ToggleGUITestObject)DOF.getButton(dialog, "&Automatically create default locale")).clickToState(RationalTestScriptConstants.SELECTED);
		}
		DOF.getButton(dialog, "&Finish").click();
	}
	
	public static boolean WFGetLocalFileByName(String project, String wf, String name) {
		WN.openWorkFlow(project, wf);
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		RationalTestScript.sleep(0.5);
		PropertiesView.clickTab("Localization");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		for (int i = 0; i < ((TestObject[]) table.invoke("getItems")).length; i++) {
			String type = TableHelper.getCellValue(table, i, 1);
			if (type.equals(name)) {
				TableHelper.clickAtCell(table, i, 0);
				return true;
			}
		}
		return false;
	}
	
	public static void WFUpdate(String project, String wf, String name) {
		WFGetLocalFileByName(project, wf, name);
		DOF.getButton(DOF.getRoot(), "&Update").click();
	}
	
	public static boolean WFValidation(String project, String wf, String name) {
		WFGetLocalFileByName(project, wf, name);
		DOF.getButton(DOF.getRoot(), "&Validate").click();
		TopLevelTestObject dialog = DOF.getDialog("Mobile Workflow Forms Editor");
		RationalTestScript.sleep(1);
		if (null != dialog) {
			DOF.getButton(dialog, "&Yes").click();
			return true;
		}
		return false;
	}
	
	public static boolean WFRemove(String project, String wf, String name) {
		WFGetLocalFileByName(project, wf, name);
		DOF.getButton(DOF.getRoot(), "&Remove").click();
		TopLevelTestObject dialog = DOF.getDialog("Delete Confirmation");
		if (null != dialog) {
			DOF.getButton(dialog, "&Yes").click();
			return true;
		}
		return false;
	}
	
	public static void WFEdit(String project, String wf, String name) {
		WFGetLocalFileByName(project, wf, name);
		DOF.getButton(DOF.getRoot(), "Ed&it").click();
	}
	
	public static void WFLoad(String project, String wf, String name) {
		TopLevelTestObject dialog = DOF.getDialog("File Changed");
		if (null != dialog) {
			DOF.getButton(dialog, "&Yes").click();
		}
		WFGetLocalFileByName(project, wf, name);
		DOF.getButton(DOF.getRoot(), "&Load").click();
		//
		dialog = DOF.getDialog("Mobile Workflow Forms Editor");
		if (null != dialog) {
			RationalTestScript.sleep(0.5);
			DOF.getButton(dialog, "&Yes").click();
		}
	}
	
	public static String WFGetLocalFileContent(String name) {
		TabitemTestObject obj = DOF.getCTabItem(DOF.getRoot(), name);
		if (obj != null){
			obj.click();
			obj.click(RationalTestScriptConstants.RIGHT);
			DOF.getContextMenu().click(SubitemFactory.atPath("Close"));
		}
		//
		String cont = "";
		String temp = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(MainMenu.getCurrentWorkspace() + "\\" + Cfg.projectName + "\\" + name));
			while ((temp = reader.readLine()) != null){ 
				cont = cont + temp;
				cont = cont + "\r\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	public static void WFChangeLocalFileContent(String project, String name, String oldVal, String newVal) {
		TabitemTestObject obj = DOF.getCTabItem(DOF.getRoot(), name);
//		TestObject[] tabs = DOF.getRoot().find(RationalTestScript.atDescendant(
//				"class", "org.eclipse.swt.custom.StyledText"));
		obj.click();
//		String result = tabs[0].getProperties().get("text").toString();
//		result = result.replace(oldVal, newVal);
		obj.click(RationalTestScriptConstants.RIGHT);
		RationalTestScript.sleep(0.5);
		DOF.getContextMenu().click(SubitemFactory.atPath("Close"));
		String cont = "";
		String temp = "";
//		FileWriter writer;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(MainMenu.getCurrentWorkspace() + "\\" + project + "\\" + name));
			while ((temp = reader.readLine()) != null){ 
				cont = cont + temp;
				cont = cont + "\r\n";
			}
			cont = cont.replace(oldVal, newVal);
//			cont = new String(cont.getBytes(), "UTF-8");
			FileOutputStream fos = new FileOutputStream(MainMenu.getCurrentWorkspace() + "\\" + project + "\\" + name, false);  
//			writer = new FileWriter(MainMenu.getCurrentWorkspace() + "\\" + project + "\\" + name, false);
			OutputStreamWriter writerUTF = new OutputStreamWriter(fos, "UTF-8");  
			writerUTF.write(cont);
			writerUTF.flush();
			writerUTF.close();
			fos.flush();
			fos.close();
//			writer.write(cont);
//			writer.flush();  
//			writer.close();  
//            fos.close();  
//            fos.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (WN.hasProjectOfName(project)) {
			DOF.getWNTree().click(RationalTestScriptConstants.RIGHT, SubitemFactory.atPath(WN.projectNameWithVersion(project)));
			DOF.getContextMenu().click(SubitemFactory.atPath("Refresh"));
			RationalTestScript.sleep(1);
		}
	}
	
	public static void WFAddLocalFileContent(String project, String name, String newVal) {
		FileWriter writer;
		try {
			writer = new FileWriter(MainMenu.getCurrentWorkspace() + "\\" + project + "\\" + name, true);
			writer.write(newVal);
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void WFSetValidationDialog(String val) {
		Preference.setShowLocalValidationDialog(val);
	}
	
	public static boolean WFIsLoaded(String project, String wf, String name) {
		WN.openWorkFlow(project, wf);
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		RationalTestScript.sleep(0.5);
		PropertiesView.clickTab("Localization");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		for (int i = 0; i < ((TestObject[]) table.invoke("getItems")).length; i++) {
			String type = TableHelper.getCellValue(table, i, 1);
			String load = TableHelper.getCellValue(table, i, 0);
			if (type.equals(name) && load.contains("The current locale loaded into the Mobile Workflow Forms Editor.")) {
				TableHelper.clickAtCell(table, i, 0);
				return true;
			}
		}
		return false;
	}
}
