package component.view;
/*************generated automatically***************/
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.eclipse.Tree;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import com.sybase.automation.framework.widget.interfaces.ITabFolder;
import com.sybase.automation.framework.widget.interfaces.ITree;
import com.sybase.automation.framework.widget.interfaces.IWindow;
import component.entity.MainMenu;
import component.entity.model.GuiError;

/**
 * Description   : Functional Test Script
 * @author Eric
 */
public class Problems extends RationalTestScript
{
	/**
	 * Script Name   : <b>Problems</b>
	 * Generated     : <b>Apr 29, 2008 5:14:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/04/29
	 * @author Eric
	 */
	public void testMain(Object[] args) 
	{
		System.out.println(getErrors().size());
	}
	
	
	public static List<GuiError> getErrors(){
		sleep(1);
		MainMenu.openView("Problems");
		sleep(1);
		List<GuiError> result = new ArrayList<GuiError>();
		GuiSubitemTestObject tree = DOF.getTreeByColumnCount(DOF.getRoot(), 5);
		try{
			String[] errors = TreeHelper.getChildrenOfNode(tree, Pattern.compile("Errors\\s+\\(.*\\)"));
			for(String error:errors){
				System.out.println(error);
				result.add(new GuiError(error));
			}
		}catch(NullPointerException e){
			
		}
//		MainMenu.closeView("Problems");
		return result;
	}

	public static List<GuiError> getWarnings() {
		MainMenu.openView("Problems");
		List<GuiError> result = new ArrayList<GuiError>();
		GuiSubitemTestObject tree = DOF.getTreeByColumnCount(DOF.getRoot(), 5);
		try{
		String[] errors = TreeHelper.getChildrenOfNode(tree, Pattern.compile("Warnings\\s+\\(.*\\)"));
		for(String error:errors){
			result.add(new GuiError(error));
		}
//		MainMenu.closeView("Problems");
		return result;
		}
		catch(NullPointerException e){
			return result;
		}
	}
	
	

}

