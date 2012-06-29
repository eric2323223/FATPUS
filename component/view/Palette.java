package component.view;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Palette extends  RationalTestScript
{
	/**
	 * Script Name   : <b>Palette</b>
	 * Generated     : <b>Sep 2, 2010 4:57:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/09/02
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		addLocalMBO(10,10);
		
		// Frame: Mobile Development - asda - Mobile Application Diagram - Sybase Unwired WorkSpace
//		palette_Root().click(atPath("Mobile Application Diagram->Mobile Business Object"), 
//                       atPoint(89,10));
//		emoDiagramEditPartDiagramImpl().click(atPoint(43,30));
	}
	private static String prefix = "Mobile Application Diagram->";
	
	public static void addMBO(int x, int y){
		DOF.getPaletteRoot().click(atPath(prefix+"Mobile Business Object"));
		DOF.getObjectDiagramCanvas().click(atPoint(x,y));
//		DOF.getActiveObjectDiagramCanvas().click(atPoint(x,y));
	}
	
	public static void addLocalMBO(int x, int y){
		DOF.getPaletteRoot().click(atPath(prefix+"Local Business Object"));
		DOF.getObjectDiagramCanvas().click(atPoint(x,y));
	}
	
	public static void createItem(String name, int x, int y ){
		DOF.getPaletteRoot().click(atPath(prefix+name));
		DOF.getObjectDiagramCanvas().click(atPoint(x,y));
		
	}

}

