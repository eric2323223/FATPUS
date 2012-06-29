package component.view.properties.workflow;

import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.SubitemFactory;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.PropertiesView;
import component.entity.model.WFLview;

/**
 * Common method for ET.
 * @author flv
 *
 */
public class WFControlObject {

	
	/**
	 * Return Combo box Object.
	 * .getProperty("text")
	 * @param screen  
	 *          screen name
	 * @param menuItem  
	 *              menu item
	 * @param cCombo  
	 *           combo box name
	 * @return 
	 *          combo box object
	 */
	public static ScrollTestObject getCCombo(String screen, String menuItem, String cCombo) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		//Get MenuItem
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		return DOF.getCCombo(DOF.getRoot(), cCombo);
	}
	
	/**
	 * Return radio button Object.
	 * .getProperty("text")
	 * @param screen 
	 *			screen name
	 * @param menuItem  
	 *          menu item
	 * @param button  
	 *          button name
	 * @return 
	 *          button object
	 */
	public static GuiTestObject getRadioButton(String screen, String menuItem, String button) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		//Get MenuItem
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		//
		PropertiesView.clickTab("General");
		//System.out.println(DOF.getButton(DOF.getRoot(), "Invoke &parent update").invoke("getEnabled"));
		return DOF.getButton(DOF.getRoot(), button);
	}
	
	/**
	 * Return text field Object.
	 * .getProperty("text")
	 * @param screen 
	 *			screen name
	 * @param menuItem  
	 *          menu item
	 * @param textfield 
	 *          text object name
	 * @return  Object
	 */
	public static GuiTestObject getTextField(String screen, String menuItem, String textfield) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		//Get MenuItem
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		//
		PropertiesView.clickTab("General");
		return DOF.getTextField(DOF.getRoot(), textfield);
	}
	
	/**
	 * Return check box object.
	 * .clickToState(SELECTED);
	 * .clickToState(NOT_SELECTED);
	 * .invoke("getSelection")
	 * @param screen  
	 *            screen name
	 * @param menuItem
	 *            menuitem name
	 * @param checkbox
	 *            check box name
	 * @return  check box object
	 */
	public static ToggleGUITestObject getCheckBox(String screen, String menuItem, String checkbox) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		//Get MenuItem
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		//
		PropertiesView.clickTab("General");
		return (ToggleGUITestObject)DOF.getButton(DOF.getRoot(), checkbox);
	}
	
	/**
	 * Return check box object.
	 * .clickToState(SELECTED);
	 * .clickToState(NOT_SELECTED);
	 * @param tab 
	 *      tab name
	 * @param checkbox 
	 *         check box name
	 * @return
	 */
	public static ToggleGUITestObject getCheckBoxOnRoot(String tab, String checkbox) {
		PropertiesView.clickTab(tab);
		return (ToggleGUITestObject)DOF.getButton(DOF.getRoot(), checkbox);
	}
	
	/**
	 * @param tab
	 * @param radiobutton
	 * @return
	 */
	public static GuiTestObject getRadioButtonOnRoot(String tab, String radiobutton) {
		PropertiesView.clickTab(tab);
		return (GuiTestObject)DOF.getButton(DOF.getRoot(), radiobutton);
	}
	

	/**
	 * @param tab
	 * @param textfield
	 * @return
	 */
	public static GuiTestObject getTextFieldOnRoot(String tab, String textfield) {
		//
		PropertiesView.clickTab(tab);
		return DOF.getTextField(DOF.getRoot(), textfield);
	}
	
	public static GefEditPartTestObject getEditBoxOnScreen(String screen, String editboxLabel) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		PropertiesView.clickTab("General");
		//
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		for(TestObject box:boxes){
			((GefEditPartTestObject)box).click();
			RationalTestScript.sleep(0.5);
			String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
			if(label.equals(editboxLabel)){
				return ((GefEditPartTestObject)box);
			} 
		}
		return null;
	}
	
	public static ArrayList<GefEditPartTestObject> getEditBoxOnScreen1(String screen, String editboxLabel) {
		//
		ArrayList<GefEditPartTestObject> list = new ArrayList<GefEditPartTestObject>();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		//
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		for(TestObject box:boxes){
			((GefEditPartTestObject)box).click();
			RationalTestScript.sleep(0.5);
			String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
			if(label.equals(editboxLabel)){
				list.add((GefEditPartTestObject)box);
			} 
		}
		return list;
	}
	
	public static ScrollTestObject getImageCombo() {
		TestObject[] combos = DOF.getRoot().find(SubitemFactory.atDescendant("class", "com.sybase.uep.xbw.util.ImageCombo"));
		if (combos != null && combos.length > 0) {
			System.out.println("there are " + combos.length + " image combo");
			return (ScrollTestObject) combos[0];
		}
		return null;
	}
	
	public static GuiTestObject getListViewOnScreen(String screen) {
		//
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		RationalTestScript.sleep(1);
		//Open Flow Design
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		//Open Screen
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		RationalTestScript.sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(SubitemFactory.atText("Properties"));
		TestObject[] objs = DOF.getRoot().find(SubitemFactory.atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ListviewEditPart"));
		if (null != objs[0]) {
			return (GuiTestObject) objs[0];
		}
		return null;
	}
	
}
