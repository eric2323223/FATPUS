package component.view;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.WN;

public class SearchView extends RationalTestScript{
	
	private static GuiSubitemTestObject tree(){
//		return DOF.getTree(DOF.getRoot(), "test");
		TestObject[] trees = DOF.getRoot().find(RationalTestScript.atDescendant(
				"class", "org.eclipse.swt.widgets.Tree"));
		for (int i = 0; i < trees.length; i++) {
			try {
				if (DOF.getAncestorLine(trees[i]).equals("Composite->PageBook->PageBook->Composite->Composite->Composite->Composite->Composite->Shell")){
					return (GuiSubitemTestObject)trees[i];
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	public static boolean hasMbo(String projectName, String string) {
		String path = projectName+"->Mobile Business Objects->"+string;
		try{
			tree().click(atPath(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	private static void findMbo(String projectName,String mbo){
		String mbopath = projectName+"->Mobile Business Objects->"+mbo;	
		tree().click(RIGHT,atPath(mbopath));
	}
	
	public static boolean openMboindiagram(String projectName,String mbo) {
		WN.closeAll();
	    findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Open in Diagram Editor"));
		sleep(2);	
		return WN.closeAll();		
	}
	
	public static void openMboinproperties(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Show In->Properties"));		
	}
	
	public static boolean renameMbo(String projectName,String from,String to) {
		findMbo(projectName,from);
		DOF.getContextMenu().click(atPath("Refactor->Rename..."));
		DOF.getTextField(DOF.getDialog("Rename Resource"), "New name:").click();
		DOF.getDialog("Rename Resource").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("Rename Resource").inputChars(to);
		sleep(1);
		DOF.getButton(DOF.getDialog("Rename Resource"), "OK").click();
		String topath = projectName+"->Mobile Business Objects->"+to;
		try{
			tree().click(atPath(topath));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void getNextmbo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Next Match"));
	}
	
	public static void getPreviousmbo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Previous Match"));
	}
	
	public static void searchAgainmbo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Search Again"));
	}
	
	public static void removeSelectedmbo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Remove Selected Matches"));
	}
	
	public static void removeAllmbo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Remove All Matches"));
	}
	
	public static boolean moveMbo(String projectName,String mbo,String to) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("Refactor->Move..."));
		String topath = WN.projectNameWithVersion(to)+"->Mobile Business Objects";
		System.out.println("topath = "+topath);
		try{
			DOF.getTree(DOF.getDialog("Move"), "Choose destination for the selected resources:").click(atPath(topath));
			DOF.getButton(DOF.getDialog("Move"), "OK").click();
			sleep(1);
			DOF.getButton(DOF.getDialog("Confirm"), "OK").click();
			sleep(1);
			DOF.getButton(DOF.getDialog("Personalization Key/Role"), "OK").click();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void findReferencembo(String projectName,String mbo) {
		findMbo(projectName,mbo);
		DOF.getContextMenu().click(atPath("References->Mobile Business Object"));
	}
	
	public static boolean hasMbooperation(String projectName, String mbo,String operation) {
		String path = projectName+"->Mobile Business Objects->"+mbo+"->Operations->"+operation;
		try{
			tree().click(atPath(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean hasMboattribute(String projectName, String mbo,String attribute) {
		String path = projectName+"->Mobile Business Objects->"+mbo+"->Attributes->"+attribute;
		try{
			tree().click(atPath(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean hasProject(String projectName) {
		String path = projectName;
		try{
			tree().click(atPath(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void closeview(){
		DOF.getCTabFolder(DOF.getRoot(), "Search").click(RIGHT, atText("Search"));
		DOF.getContextMenu().click(atPath("Close"));
	}
	
}
