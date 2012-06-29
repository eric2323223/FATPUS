package component.entity;
import java.awt.Point;
import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.model.MBOAttribute;
import component.entity.model.Operation;
import component.entity.model.Relationship;
import component.entity.model.RestWSOperation;
import component.entity.model.WizardRunner;
import component.entity.wizard.ODRestWSOperationCreationWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OD extends RationalTestScript
{
	/**
	 * Script Name   : <b>OD</b>
	 * Generated     : <b>Sep 26, 2010 10:37:39 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/09/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		NativeInvoker.printMethods(DOF.getMboFigure(od(), "object1"));
//		changeAttributeName("object1","type","type1");
//		addAttribute("Customer","a2");
//		addOperation("Customer","o2");
		getAllMbos();
	}
	
	private static GefEditPartTestObject od(){
		return DOF.getObjectDiagramCanvas();
	}
	
	private static Point getRandomUnusedPoint(){
		return new Point(1,1);
	}
	
	public static void unhighlightAllMbo(){
		Point point = getRandomUnusedPoint();
		DOF.getObjectDiagramCanvas().click(atPoint(point.x, point.y));
	}
	
	public static String[] getAllMbos() {
		ArrayList<String> allName = new ArrayList<String>();
		TestObject[] objs = od()
				.find(
						RationalTestScript
								.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				TestObject[] names = obj
						.find(RationalTestScript
								.atDescendant(".class",
										"com.sybase.uep.tooling.ui.diagram.edit.parts.EMobileObjectNameEditPart"));
				if (objs != null && objs.length > 0) {
					System.out.println(names[0].getProperty("text").toString());
					allName.add(names[0].getProperty("text").toString().trim());
				}
			}
		}
		return allName.toArray(new String[0]);
	}
	
	public static void addAttribute(String mbo, String attribute){
		od().click(atPoint((int)(getRandomUnusedPoint().getX()), (int)(getRandomUnusedPoint().getY())));
		TestObject obj = DOF.getMboAttributesFigure(DOF.getMboFigure(od(), mbo));
		int height = new Integer(obj.getProperty("height").toString()).intValue();
		int width = new Integer(obj.getProperty("width").toString()).intValue();
		//Danger: call "getContentPane" on MBO attribute figure will cause weird behavior
//		int width = RectangleHelper.getWidth((TestObject)obj.invoke("getContentPane"));
//		int height = RectangleHelper.getHeight((TestObject)obj.invoke("getContentPane"));
		((GefEditPartTestObject)obj).click(RIGHT, atPoint(width/2, height/2));
		DOF.getContextMenu().click(atPath("New->Attribute"));
		DOF.getRoot().inputChars(attribute);
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public static void addOperation(String mbo, String operation){
		od().click(atPoint((int)(getRandomUnusedPoint().getX()), (int)(getRandomUnusedPoint().getY())));
		TestObject obj = DOF.getMboAttributesFigure(DOF.getMboFigure(od(), mbo));
		int height = new Integer(obj.getProperty("height").toString()).intValue();
		int width = new Integer(obj.getProperty("width").toString()).intValue();
//		int width = RectangleHelper.getWidth((TestObject)obj.invoke("getContentPane"));
//		int height = RectangleHelper.getHeight((TestObject)obj.invoke("getContentPane"));
		((GefEditPartTestObject)obj).click(RIGHT, atPoint(width/2,height/2));
		DOF.getContextMenu().click(atPath("New->Operation"));
		//tbd
	}

	public static void changeAttributeName(String mbo, String from,	String to) {
		DOF.getMboAttributeFigure(DOF.getMboFigure(od(), mbo),from).click();
		sleep(1);
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getTextField(DOF.getRoot(),"Name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(to);
		DOF.getMenu().click(atPath("File->Save All"));
	}
	
	public static void cutAndPasteMbo(String mbo){
		cutMbo(mbo);
		paste();
	}
	

	public static void copyAndPasteMbo(String string) {
		copyMbo(string);
		paste();
	}

	private static void copyMbo(String string) {
		GefEditPartTestObject m = DOF.getMboNameFigure(od(), string);
		m.click(RIGHT, atPoint(5,5));
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		sleep(1);
	}

	private static void cutMbo(String mbo) {
		GefEditPartTestObject m = DOF.getMboNameFigure(od(), mbo);
		m.click(RIGHT, atPoint(5,5));
		DOF.getContextMenu().click(atPath("Edit->Cut"));
		sleep(1);
	}

	private static void paste() {
		od().click(RIGHT, atPoint(getRandomUnusedPoint().x, getRandomUnusedPoint().y));
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		save();
	}

	private static void save(){
		DOF.getMenu().click(atPath("File->Save All"));
	}

	public static void copyMboToProject(String fromProject, String mbo,String toProject) {
		System.out.println(fromProject+WN.projectNameWithVersion(fromProject));
		String fromPath = WN.projectNameWithVersion(fromProject)+"->Mobile Business Objects->"+mbo;
		String toPath = WN.projectNameWithVersion(toProject);
		DOF.getWNTree().click(RIGHT, atPath(fromPath));
		DOF.getContextMenu().click(atPath("Copy"));
		DOF.getWNTree().click(RIGHT, atPath(toPath));
		DOF.getContextMenu().click(atPath("Open in Diagram Editor"));
		DOF.getMenu().click(atPath("Edit->Paste"));
		save();
	}
	
	public static String getMboPreviewData(String mbo, int row, int column){
		GefEditPartTestObject m = DOF.getMboNameFigure(od(), mbo);
		m.click(RIGHT, atPoint(5,5));
		DOF.getContextMenu().click(atPath("Preview..."));
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(5);
		String data = TableHelper.getCellValue(DOF.getTable(DOF.getDialog("Preview"), "Preview Result:"), row, column);
		DOF.getButton(DOF.getDialog("Preview"), "OK").click();
		return data;
	}
	
	public static void swithToDeveloperProfile(String profile){
		DOF.getObjectDiagramCanvas().click(RIGHT, atPoint(5,5));
		DOF.getContextMenu().click(atPath("Switch Developer Profile->"+profile));
		DOF.getButton(DOF.getDialog("Switch Developer Profile"), "&Yes").click();
	}
	

	public static String getAssocicationName(String fromMbo, String toMbo){
		GefEditPartTestObject association = DOF.getAssociationFigure(DOF.getObjectDiagramCanvas(), fromMbo, toMbo);
		Point point = DOF.getAssociationSourceAnchorPoint(association);
		DOF.getObjectDiagramCanvas().click(atPoint(point.x, point.y));
		sleep(2);
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		return DOF.getTextField(DOF.getRoot(), "Attribute name:").getProperty("text").toString();
		
	}
	
	public static String getAssociationTarget(String fromMbo, String toMbo){
		DOF.getAssociationFigure(DOF.getObjectDiagramCanvas(), fromMbo, toMbo).click();
		sleep(2);
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		return DOF.getCCombo(DOF.getRoot(), "Target object:").getProperty("text").toString();
		
	}
	
	public static void createRelationship(Relationship relationship){
		new ODRelationshipCW().create(relationship, new WizardRunner());
	}

	public static void createOperation(Object op) {
		if(op instanceof Operation){
			new ODOCW().create((Operation)op, new WizardRunner());
		}
		else if(op instanceof RestWSOperation){
			new ODRestWSOperationCreationWizard().create((RestWSOperation)op, new WizardRunner());
		}
	}

//	static Point[] validPoints = new Point[]{new Point(10,10), new Point(150, 10), new Point(300, 10), new Point(450, 10)};
//	static int pointIndex = 0;
//	public static Point getValidPoint() {
//		int index = pointIndex++ % validPoints.length;
//		return validPoints[index];
//	}
	
	public static Point getValidPoint(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		int maxX = 600;
		int maxY = 500;
		while(true){
			int x = (int)(Math.random()* maxX);
			int y = (int)(Math.random()* maxY);
			DOF.getObjectDiagramCanvas().click(atPoint(x, y));
			sleep(2);
			if(DOF.getCLabelByContent(DOF.getRoot(), "Mobile Application Diagram")!=null){
				return new Point(x, y);
			}
		}
	}

	public static void dndRelationship(Relationship relationship) {
		new DnDRelationshipCW().create(relationship, new WizardRunner());
	}

	public static void arrangeAll() {
		Point p = getValidPoint();
		DOF.getObjectDiagramCanvas().click(RIGHT, atPoint(p.x, p.y));
		DOF.getContextMenu().click(atPath("Arrange All"));
		sleep(1);
		zoomToFit();
		MainMenu.saveAll();
	}
	
	public static void zoomToFit() {
		Point point = getValidPoint();
		DOF.getObjectDiagramCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Zoom->Zoom to Fit"));
		sleep(2);
		MainMenu.saveAll();
	}

	public static void setMBOAttribute(String mbo, MBOAttribute attribute) {
		arrangeAll();
		DOF.getMboAttributeFigure(DOF.getMboFigure(DOF.getRoot(), mbo), attribute.getName()).click();
		sleep(2);
		DOF.getMboAttributeFigure(DOF.getMboFigure(DOF.getRoot(), mbo), attribute.getName()).click();
		if(attribute.isPrimaryKey()){
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Primary key")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Primary key")).clickToState(NOT_SELECTED);
		}
//		arrangeAll();
//		Point p = getValidPoint();
//		DOF.getObjectDiagramCanvas().click(atPoint(p.x, p.y));
//		sleep(1);
//		DOF.getMboNameFigure(DOF.getMboFigure(DOF.getRoot(), mbo), attribute.getName()).click();
	}

}

