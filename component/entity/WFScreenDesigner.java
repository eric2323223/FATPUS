package component.entity;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.MenuHelper;
import com.sybase.automation.framework.widget.helper.WFMenuFigureHelper;
import component.dialog.NotificationDialog;
import component.entity.model.Email;
import component.entity.model.WFButton;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFHtmlView;
import component.entity.model.WFLabel;
import component.entity.model.WFLink;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.model.Widget;

public class WFScreenDesigner extends RationalTestScript{

	public static void addMenuItem(WFScreenMenuItem item) {
		sleep(1);
//		arrangeAll();
		addMenuItem(item.getName());
		sleep(0.5);
		DOF.getWFMenuItemFigure(DOF.getRoot(), item.getName()).click(atPoint(5,5));
		sleep(1);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		sleep(1);
		PropertiesView.set(item);
		MainMenu.saveAll();
	}
	////fanfei add the following code to add screen with chinese name 20120607>>>>>
	public static void addMenuItemWithCH(WFScreenMenuItem item) {
		sleep(1);
//		arrangeAll();
		addMenuItemWithCH(item.getName());
		sleep(0.5);
		DOF.getWFMenuItemFigure(DOF.getRoot(), item.getName()).click(atPoint(5,5));
		sleep(1);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		sleep(1);
		PropertiesView.set(item);
		MainMenu.saveAll();
	}
//	<<<<<<<<<<<<<<<<<<<<<<<>
	
	public static void addCustomeAction(WFScreenMenuItem item) {
		sleep(1);
		arrangeAll();
		addCustomMenuItem(item.getName());
		sleep(0.5);
		DOF.getWFCustomMenuItemFigure(DOF.getRoot(), item.getName()).click(atPoint(5,5));
		sleep(1);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		sleep(1);
		PropertiesView.set(item);
		MainMenu.saveAll();
	}

	public static void arrangeAll() {
		Point point = getValidPoint();
		DOF.getWFScreenDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		sleep(1);
		DOF.getContextMenu().click(atPath("Arrange All"));
		sleep(1);
		MainMenu.saveAll();
	}
	
	//ff>>>>>>>>>9.28
	public static Point getValidPoint() {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		while (true) {
			int x = (int) (Math.random() * 500);
			int y = (int) (Math.random() * 500);
			DOF.getWFScreenDesignCanvas().click(atPoint(x, y));
			sleep(1);
			if(DOF.getCLabelStartWith(DOF.getRoot(), "Screen Design")!=null){
				return new Point(x,y);
			}
		}
	}
	//ff<<<<<<<<<<<<<<<9.28
	public static boolean hasMenuItem(String menuItem){
		return DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem)!=null;
	}
	
	public static boolean hasCustomAction(String action) {
		return DOF.getWFCustomActionFigure(DOF.getRoot(), action)!=null;
	}
	
	public static void addMenuItem(String name){
		GefEditPartTestObject menu = DOF.getWFMenuFigure(DOF.getRoot());
		menu.click();
//		DOF.getPaletteRoot().click(atPath("xbwscr->Menu Item"), atPoint(67,13));
		DOF.getPaletteRoot().click(atPath("xbwscr->MenuItem"), atPoint(67,13));
		List<String> items = WFMenuFigureHelper.getMenuItems(menu);
//		GefEditPartTestObject menuItem = DOF.getWFMenuItemFigure(menu, items.get(items.size()-1));
//		int menuItemHeight = WFMenuItemFigureHelper.getMenuItemHeight(menuItem);//=17
//		System.out.println("ggggggggggg"+menuItemHeight);
//		menu.click(atPoint(10, menuItemHeight*items.size()+5));
		menu.click(atPoint(10, 17*items.size()+5)); //item height: 17pixels
		DOF.getRoot().inputKeys(name+"{ENTER}");
	}
	
	////fanfei add the following code to add screen with chinese name 20120607>>>>>
	public static void addMenuItemWithCH(String name){
		GefEditPartTestObject menu = DOF.getWFMenuFigure(DOF.getRoot());
		menu.click();
		DOF.getPaletteRoot().click(atPath("xbwscr->MenuItem"), atPoint(67,13));
		List<String> items = WFMenuFigureHelper.getMenuItems(menu);
		menu.click(atPoint(10, 17*items.size()+5)); //item height: 17pixels
		
		Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection text=new StringSelection(name);  
        clb.setContents(text, null);  
		DOF.getRoot().inputKeys("^v");
		
	}
//	<<<<<<<<<<<<<<<<<<<<<<<<>
	public static void addCustomMenuItem(String name){
		GefEditPartTestObject menu = DOF.getWFCustomMenuFigure(DOF.getRoot());
		menu.click();
		DOF.getPaletteRoot().click(atPath("xbwscr->Custom Action"), atPoint(67,13));
		List<String> items = WFMenuFigureHelper.getCustomMenuItems(menu);
		menu.click(atPoint(10, 17*items.size()+5)); //item height: 17pixels
		DOF.getRoot().inputKeys(name+"{ENTER}");
		
	}

//*************add yanxu1123	
	public static void invokeaddItem(String name){	
		addMenuItem(name);
	}
//**************end yanxu1123
	
//	public static void addEditBox(WFEditBox editBox) {
//		DOF.getWFScreenDisplayFigure().click();
//		
////		+++++++++++++++++++++++++++++++++++
////		below code will cause problem, this is a ET bug
////		+++++++++++++++++++++++++++++++++++
////		Point p = getValidPoint();
////		DOF.getWFScreenDesignCanvas().click(atPoint(p.x, p.y));
////		-----------------------------------
//		
//		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->EditBox"));
//		DOF.getWFScreenDisplayFigure().click();
////		System.out.println(editBox.getLabel()+"{ENTER}");
//		sleep(0.5);
//		DOF.getRoot().inputKeys(editBox.getLabel()+"{ENTER}");
//		PropertiesView.set(editBox);
//	}
	public static void addEditBox(WFEditBox editBox) {
		DOF.getWFScreenDisplayFigure().click();
		
//		+++++++++++++++++++++++++++++++++++
//		below code will cause problem, this is a ET bug
//		+++++++++++++++++++++++++++++++++++
//		Point p = getValidPoint();
//		DOF.getWFScreenDesignCanvas().click(atPoint(p.x, p.y));
//		-----------------------------------
		
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->EditBox"));
		DOF.getWFScreenDisplayFigure().click();
//		System.out.println(editBox.getLabel()+"{ENTER}");
		sleep(0.5);
		
		////ffan add to add editbox with chinese label 20120608>>>>>>>>>>>
		if(editBox.getLabel().contains("CH,")){
			System.out.println("begin to input chinese>>>>>>>>>");
			Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection text=new StringSelection(editBox.getLabel().split(",")[1]);  
	        clb.setContents(text, null);  
			DOF.getRoot().inputKeys("^v");
		}
		else
//			<<<<<<<<<<<<<<<<<<>
		DOF.getRoot().inputKeys(editBox.getLabel()+"{ENTER}");
		PropertiesView.set(editBox);
	}
	
	public static void addChoice(WFChoice choice) {

//		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Choice"));
//		DOF.getWFScreenDisplayFigure().click();
//		DOF.getRoot().inputKeys(choice.getLabel()+"{ENTER}");
//		PropertiesView.set(choice);
	
		//Modify by ff 1.17>>>>>>>:
		DOF.getWFScreenDisplayFigure().click();

		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Choice"));
		DOF.getWFScreenDisplayFigure().click();
		sleep(0.5);
		DOF.getRoot().inputKeys(choice.getLabel()+"{ENTER}");
		PropertiesView.set(choice);
	}

	public static void addWidget(Widget widget) {
//		zoomToFit();
		if(widget instanceof WFEditBox){
			addEditBox((WFEditBox)widget);
		}
		if(widget instanceof WFChoice){
			addChoice((WFChoice)widget);
		}
		if(widget instanceof WFSlider){
			addSlider((WFSlider)widget);
		}
		if(widget instanceof WFSignature){
			addSignature((WFSignature)widget);
		}
		if(widget instanceof WFCheckbox){
			addCheckbox((WFCheckbox)widget);
		}
		if(widget instanceof WFLview){
			addLview((WFLview)widget);
		}
		if(widget instanceof WFHtmlView){
			addHtmlView((WFHtmlView)widget);
		}
		//ff10.25>>>>>>>>>>>>>>>>>>>>>>>
		if(widget instanceof WFLabel){
			addLabel((WFLabel)widget);
		}
		if(widget instanceof WFLink){
			addLink((WFLink)widget);
		}
		if(widget instanceof WFButton){
			addButton((WFButton)widget);
		}
	}

	private static void addButton(WFButton button) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Button"));
		DOF.getWFScreenDisplayFigure().click();
		sleep(0.5);
		if(button.getName()!=null)
			DOF.getRoot().inputKeys(button.getName()+"{ENTER}");
		sleep(0.5);
		PropertiesView.set(button);
	}

	private static void addLink(WFLink link) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Link"));
		DOF.getWFScreenDisplayFigure().click();
		PropertiesView.set(link);
	}

	private static void addLabel(WFLabel label) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Label"));
		DOF.getWFScreenDisplayFigure().click();
		sleep(0.5);
		if(label.getName()!=null)
			DOF.getRoot().inputKeys(label.getName()+"{ENTER}");
		sleep(0.5);
		PropertiesView.set(label);
	}

	private static void addHtmlView(WFHtmlView htmlview) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->HtmlView"));
		DOF.getWFScreenDisplayFigure().click();
		PropertiesView.set(htmlview);
	}

	private static void addLview(WFLview lview) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Listview"));
		DOF.getWFScreenDisplayFigure().click();
		PropertiesView.set(lview);
	}

	private static void addCheckbox(WFCheckbox checkbox) {
//		Point point = getValidPoint();
//		DOF.getWFScreenDesignCanvas().click(RIGHT, atPoint(point.x, point.y));

		DOF.getWFScreenDisplayFigure().click();
		
		
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Checkbox"));
		DOF.getWFScreenDisplayFigure().click();
		sleep(0.5);
		DOF.getRoot().inputKeys(checkbox.getLabel()+"{ENTER}");
		PropertiesView.set(checkbox);
		
	}

	private static void addSignature(WFSignature signature) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Signature"));
		DOF.getWFScreenDisplayFigure().click();
		DOF.getRoot().inputKeys(signature.getLabel()+"{ENTER}");
		PropertiesView.set(signature);
	}

	private static void addSlider(WFSlider slider) {
		DOF.getPaletteRoot().click(atPath("xbwscr->Controls->Slider"));
		DOF.getWFScreenDisplayFigure().click();
		DOF.getRoot().inputKeys(slider.getLabel()+"{ENTER}");
		PropertiesView.set(slider);
		
	}

	public static boolean hasWidget(IEditable widget) {
		PropertiesView.clickTab("General");
		sleep(0.5);
		if(widget instanceof WFEditBox){
			TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(0.5);
				String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
				if(label.equals(((WFEditBox)widget).getLabel())){
					return true;
				}
			}
//			return DOF.getWFEditBoxFigure(DOF.getRoot(),((WFEditBox)widget).getLabel())!=null;
		}
		
		//20120216>>>>>>>>>>>>ff add listView:
		if(widget instanceof WFLview){
			PropertiesView.clickTab("General");
			TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(0.5);
				String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
				if(key.equals(((WFLview)widget).getKey())){
					return true;
				}
			}
		}
		//ff<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		return false;
	}

	public static void setMenuItem(WFScreenMenuItem menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem.getName()).click();
		PropertiesView.set(menuItem);
		MainMenu.saveAll();
	}
	
	public static void zoomToFit() {
		Point point = getValidPoint();
		DOF.getWFScreenDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Zoom->Zoom to Fit"));
		sleep(2);
		MainMenu.saveAll();
	}

	public static void removeMenuItem(String screen, String menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}
	//ff10.25>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//delete the control has name in screen:
	public static void removeWidget(IEditable widget) {
		
		if(widget instanceof WFEditBox){
			TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(0.5);
				String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
				if(label.equals(((WFEditBox)widget).getLabel()))
				{
					((GefEditPartTestObject)box).click(RIGHT);
					DOF.getContextMenu().click(atPath("Delete"));
					
				}
			}
		}
		//ff10.31>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		if(widget instanceof WFChoice){
			TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(0.5);
				String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
				if(label.equals(((WFChoice)widget).getLabel()))
				{
					((GefEditPartTestObject)box).click(RIGHT);
					DOF.getContextMenu().click(atPath("Delete"));
					
				}
			}
		}
		//ff10.31<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	}
	//ff10.25>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public static void removeMenuItem2(String screen, String menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		DOF.getRoot().inputKeys("{DEL}");
		MainMenu.saveAll();
	}

	public static void copyAndPasteMenuItem(String screen, String menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		
		DOF.getPaletteRoot().click(atPath("xbwscr->Menu Item"), atPoint(67,13));
		GefEditPartTestObject menu = DOF.getWFMenuFigure(DOF.getRoot());
		List<String> items = WFMenuFigureHelper.getMenuItems(menu);
		menu.click(RIGHT, atPoint(10, 17*items.size()+5));
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		MainMenu.saveAll();
		
	}
	
	public static void copyCustomAction(String action){
		DOF.getWFCustomActionFigure(DOF.getRoot(), action).click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
	}
	
	public static void pasteCustomAction(){
		DOF.getWFCustomActionsFigure(DOF.getRoot()).click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
	}
	
	public static void cutAndPasteMenuItem(String screen, String menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Cut"));
		
		DOF.getPaletteRoot().click(atPath("xbwscr->Menu Item"), atPoint(67,13));
		GefEditPartTestObject menu = DOF.getWFMenuFigure(DOF.getRoot());
		List<String> items = WFMenuFigureHelper.getMenuItems(menu);
		menu.click(RIGHT, atPoint(10, 17*items.size()+5));
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		MainMenu.saveAll();
		
	}

	public static boolean isMenuDeletable() {
		sleep(1);
		DOF.getWFMenuFigure(DOF.getRoot()).click(RIGHT);
		return MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Delete");
	}

	public static void renameMenuItem(String from, String to) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), from).click();
		PropertiesView.set(new WFScreenMenuItem().name(to));
		MainMenu.saveAll();
	}
//ff>>>>>>>>>9.28
	public static String sendNotification(Email email) {
		// TODO Auto-generated method stub
		Point point = getValidPoint();
		DOF.getWFScreenDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Send a notification..."));
		return NotificationDialog.send(email);
	}

	public static void showPropertiesView() {
		// TODO Auto-generated method stub
		Point point = getValidPoint();
		DOF.getWFScreenDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Show Properties View"));
	}
//ff11.2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void deleteMenuItem(String menuItem) {
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}
//ff11.2<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static void deleteCustomAction(String action) {
		DOF.getWFCustomActionFigure(DOF.getRoot(), action).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}

}
