package component.entity;

import java.util.List;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.model.Email;
import component.entity.model.Modifications;
import component.entity.model.Module;
import component.entity.model.Screen;
import component.entity.model.StartPoint;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFKey;
import component.entity.model.WFNewscreen;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;

import component.entity.model.Widget;
import component.entity.wizard.SendNotificationWizard;

public class WorkFlowEditor extends RationalTestScript{

	public static void link(String from, String to) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(31,6));
		sleep(1);
		WFFlowDesigner.link(from, to);
		WFFlowDesigner.arrangeAll();
	}
	
	public static void dragMbo(String project, String mbo){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.dragMbo(project, mbo);
		WFFlowDesigner.arrangeAll();
	}

	public static void addMenuItem(String screen,WFScreenMenuItem menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.addMenuItem( menuItem);
	}
	
	////fanfei add the following code to add screen with chinese name 20120607>>>>>
	public static void addMenuItemWithCH(String screen,WFScreenMenuItem menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.addMenuItemWithCH( menuItem);
	}
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>
	
	public static void addCustomAction(String screen, WFScreenMenuItem menuItem){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.addCustomeAction( menuItem);
	}
	
	public static void sendNotification(String project, String workflow, Email email){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.sendNotification(project, workflow, email);
	}
	
//	public static String sendNotification(Email email){
//		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
//		sleep(1);
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
//		return WFFlowDesigner.sendNotification(email);
//	}
	
	public static void sendNotification(Email email){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		new SendNotificationWizard().create(email, new WizardRunner());
	}

	public static void addEditBox(String project, String wf, String screen, WFEditBox editBox) {
		WN.openWorkFlow(project, wf);
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		sleep(1);
		WFScreenDesigner.addEditBox(editBox);
	}

	public static void addWidget(String project, String wf,	String screen, Widget widget) {
		WN.openWorkFlow(project, wf);
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		sleep(1);
		WFScreenDesigner.addWidget(widget);
		
	}
	
	public static void addWidget(String screen, Widget widget) {
//		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		sleep(2);
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		WFScreenDesigner.addWidget(widget);
	}

	public static void setWorkFlow(WorkFlow wf) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.setWorkFlow(wf);
	}
	
	public static void addScreen(String screenName){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.addScreen(screenName);
		WFFlowDesigner.arrangeAll();
		MainMenu.saveAll();
	}
	
	//fanfei add the following code to add screen with chinese name 20120607>>>>>
	public static void addScreenWithCHName(String screenName){
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);	
		WFFlowDesigner.addScreenWithCHName(screenName);
		WFFlowDesigner.arrangeAll();
		MainMenu.saveAll();
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public static boolean addStartingPoint(StartPoint sp) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		return WFFlowDesigner.addStartingPoint(sp);
	}
	//ff914>>>>>>>>>>
	public static boolean getMboProject(StartPoint sp) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		return WFFlowDesigner.hasMboProject(sp);
	}
	//<<<ff914
	public static void removeScreen(String string) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.removeScreen(string);
	}

	public static void renameStartPoint(String string, String string2) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.renameStartPoint(string, string2);
	}

	public static void deleteLink(String string, String string2) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.deleteLink(string, string2);
	}

	public static boolean hasLinkBetween(String string, String string2) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		return WFFlowDesigner.hasLinkBetween(string, string2);
	}

	public static void close(String project, String wf) {
		DOF.getWNTree().doubleClick(atPath(WN.wfPath(project, wf)));
		DOF.getCTabItem(DOF.getRoot(), wf).click(RIGHT);
		DOF.getContextMenu().click(atPath("Close"));
	}

	public static void open(String projectName, String string) {
		DOF.getWNTree().doubleClick(atPath(WN.wfPath(projectName, string)));
	}

	public static boolean hasMenuItemInScreen(String screen, String menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		//ff used to 213drop3:
		DOF.getWFScreenFigureToClick(screen).doubleClick();
		sleep(1);
		return WFScreenDesigner.hasMenuItem(menuItem);
	}

	public static boolean hasCustomActionInScreen(String screen, String action) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		sleep(1);
		return WFScreenDesigner.hasCustomAction(action);
	}

	public static boolean hasScreen(String string) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		return WFFlowDesigner.hasScreen(string);
	}

	public static void dragOperation(String project, String mbo, String operation) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		WFFlowDesigner.dragOperation(project, mbo, operation);
		WFFlowDesigner.arrangeAll();
	}

	public static Object hasWidgetInScreen(String screen, IEditable widget) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		//ff used to 213drop3:
		DOF.getWFScreenFigureToClick(screen).doubleClick();
		return WFScreenDesigner.hasWidget(widget);
	}
//
//	public static void createNewscreen(String project, String wf,WFNewscreen newscreen) {
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
//		sleep(1);
//		WFScreenDesigner.addScreen(project, wf, newscreen);
//		WFFlowDesigner.arrangeAll();
//	}

	public static void setMenuItem(String screen,WFScreenMenuItem menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(2);
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		//ff modify:
//		DOF.getWFScreenFigureToClick(screen).doubleClick();
		sleep(2);
		WFScreenDesigner.setMenuItem(menuItem);
	}

	public static void createNewscreen(String project, String wf,WFNewscreen newscreen) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(1);
//		WFScreenDesigner.addScreen(project, wf, newscreen);
		WFFlowDesigner.arrangeAll();
	}

	public static void removeStartPoint(String str) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.removeStartPoint(str);
	}
	
//ff>>>>>>>>>>>>>9.14
	public static void multiselect(String screen, String startpoint) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).click();
		if(startpoint.equals(WorkFlow.SP_CLIENT_INIT)){
		DOF.getWFClientInitiateFlowStartingPointFigure().click(SHIFT_LEFT);
		DOF.getWFClientInitiateFlowStartingPointFigure().click(RIGHT);
		}
	}

	public static void removeMenuItem(String screen, String menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.removeMenuItem(screen, menuItem);
	}
	
//FF10.25>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void removeWidget(String screen,IEditable widget) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.removeWidget(widget);
	}
	//FF10.25<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public static void copyAndPasteMenuItem(String screen, String menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.copyAndPasteMenuItem(screen, menuItem);
	}
	
	public static void copyAndPasteCustomAction(String fromScreen, String customAction,	String toScreen) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), fromScreen).doubleClick();
		WFScreenDesigner.copyCustomAction(customAction);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), toScreen).doubleClick();
		WFScreenDesigner.pasteCustomAction();
	}
	
	public static void cutAndPasteMenuItem(String screen, String menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.cutAndPasteMenuItem(screen, menuItem);
		
	}

	public static boolean isMenuDeletable(String screen) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		return WFScreenDesigner.isMenuDeletable();
	}

	public static void renameMenuItem(String screen, String from, String to) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.renameMenuItem(from, to);
		
	}
	//ff928>>>>>>>>>>>>>>>>>
	public static void sendNotificationInScreenDesign(Email subject) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		WFScreenDesigner.sendNotification(subject);
	}
	
	public static void editModule(Module module) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		PropertiesView.editModule(module);
	}
	
	public static void screenProperties(Screen screen) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		PropertiesView.setScreenProperties(screen);
		
	}
	
	public static void showPropertiesViewInFD(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.showPropertiesView();
	}
	
	public static void showPropertiesViewInSD(String screen){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.showPropertiesView();
	}
	//ff928<<<<<<<<<<

	public static void setStartPoint(StartPoint startPoint) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WFFlowDesigner.setStartPoint(startPoint);
		
	}

	public static void set(Widget w, Modifications mod) {
		PropertiesView.set(w, mod);
		//TBD;
	}

//ff10.31>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//use to reset the datetime to Locale_Specific
	public static void resetWidgetInScreen(String screen, IEditable widget,String resetItem) {
		// TODO Auto-generated method stub
		hasWidgetInScreen(screen, widget);
		PropertiesView.maximize();
		if(resetItem.equals("Locale_Specific"))
			((ToggleGUITestObject) DOF.getButton(DOF.getRoot(), "&Locale-specific display")).clickToState(SELECTED);
		PropertiesView.restore();
	}
	
	public static void deleteMenuItem(String screen,String menuItem) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.deleteMenuItem(menuItem);
	}

	public static void deleteCustomAction(String screen, String action) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.deleteCustomAction(action);
	}

//FF12.6>>>>>>add :used to show the type name of link between screens
	public static String getLinkTypeBetween(String string, String string2) {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getCTabFolder(DOF.getRoot(), "Flow Design").click(atText("Flow Design"));
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		return WFFlowDesigner.getLinkTypeBetween(string, string2);
	}
	
	//fanfei add modify the long screen name to short name >>>>>20120515
	public static void renameScreenName(String from, String to) {
	// TODO Auto-generated method stub
	DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
	DOF.getWFScreenFigure(from);
	PropertiesView.clickTab("General");
	DOF.getTextField(DOF.getRoot(), "Name:").click();
	DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
	DOF.getRoot().inputChars(to);
	DOF.getRoot().inputKeys(SpecialKeys.ENTER);
	MainMenu.saveAll();
	}
//<<<<<<<<<<<FF12.6>>>>>>
	
	public static boolean hasKeyInScreen(String screen, String key){
		List<WFKey> keys = getAllKeysInScreen(screen);
		for(WFKey k:keys){
			if(k.getName().equals(key)){
				return true;
			}
		}
		return false;
	}

	public static List<WFKey> getAllKeysInScreen(String screen) {
		return PropertiesView.getAllkeysInScreen(screen);
	}

}
