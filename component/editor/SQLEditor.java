package component.editor;

/*************generated automatically***************/

import java.awt.Rectangle;
import java.io.File;
import java.lang.reflect.Method;

import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.common.DialogCloser;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.eclipse.*;
import com.sybase.automation.framework.widget.interfaces.*;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

import component.entity.EE;
import component.entity.LongOperationMonitor;
import component.entity.model.ScrapbookCP;

/**
 * Description : Functional Test Script
 * 
 * @author Eric
 */
public class SQLEditor extends RationalTestScript {
	/**
	 * Script Name : <b>SQLEditor </b> Generated : <b>Jun 30, 2007 10:15:07 PM
	 * </b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2007/06/30
	 * @author Eric
	 */
//	public void testMain(Object[] args) {
//		executeSQL("\\testscript\\View\\SQLstatement\\simpleASA.sql");
//	}
//
//	public void executeSQL(String file) {
//		System.out.println(getProjectRootPath() +  file);
//		String sql = FileUtil.readFromFile(getProjectRootPath() +  file);
//		Editor_txt().setText(sql);
//		new Menu().Main_mnu().clickAtPath("Database Tools->Execute All");
//
//	}
	
	public static void runSql(ScrapbookCP cp, String sql){
		EE.openScrapbook();
		setupScrapbookCP(cp);
		DOF.getStyledText(DOF.getCompositeByEclipseId(DOF.getRoot(), "org.eclipse.datatools.sqltools.sqlscrapbook.SQLScrapbookEditor")).click(RIGHT);
		DOF.getRoot().inputChars(sql);
		run(cp);
	}
	
	public static void runSql(ScrapbookCP cp, File sql){
		try{
			EE.connectConnectionProfile(cp.getName());
			EE.openScrapbook();
			setupScrapbookCP(cp);
			DOF.getStyledText(DOF.getCompositeByEclipseId(DOF.getRoot(), "org.eclipse.datatools.sqltools.sqlscrapbook.SQLScrapbookEditor")).click(RIGHT);
			String content = FileUtil.readFromFile(sql);
			ClipBoardUtil.setClipboardText(content);
			DOF.getStyledText(DOF.getCompositeByEclipseId(DOF.getRoot(), "org.eclipse.datatools.sqltools.sqlscrapbook.SQLScrapbookEditor")).click(RIGHT);
			DOF.getContextMenu().click(atPath("Paste"));
	//		DOF.getRoot().inputChars(content);
			run(cp);
		}catch(Exception e){
			e.printStackTrace();
			closeFile();
			throw new RuntimeException("Failed to run SQL in sql editor");
		}
	}

	private static void setupScrapbookCP(ScrapbookCP cp) {	
		DOF.getCombo(DOF.getRoot(), "Type:").click();
		DOF.getCombo(DOF.getRoot(), "Type:").click(atText(cp.getType()));
		DOF.getComboByItem(DOF.getRoot(), cp.getName()).click();
		DOF.getComboByItem(DOF.getRoot(), cp.getName()).click(atText(cp.getName()));
//		DOF.getComboByBounds(DOF.getRoot(), new Rectangle(0,0,160,21)).click();
//		DOF.getComboByBounds(DOF.getRoot(),  new Rectangle(0,0,160,21)).click(atText(cp.getName()));
//		DOF.getComboByBounds(DOF.getRoot(), new Rectangle(0,0,223,21)).click();
//		DOF.getComboByBounds(DOF.getRoot(),  new Rectangle(0,0,223,21)).click(atText(cp.getName()));
		DOF.getCombo(DOF.getRoot(), "Database:").click();
		DOF.getCombo(DOF.getRoot(), "Database:").click(atText(cp.getDatabase()));
	}
	
	private static void run(ScrapbookCP cp){
		DOF.getStyledText(DOF.getCompositeByEclipseId(DOF.getRoot(), "org.eclipse.datatools.sqltools.sqlscrapbook.SQLScrapbookEditor")).click(RIGHT);
		DOF.getContextMenu().click(atPath("Execute All"));
		LongOperationMonitor.waitForDialogToVanish("SQL Statement Execution");
//		LongOperationMonitor.waitForDialogToVanish("SQL Statement Execution", new DialogCloser().dialog("Continue Execution", "&Yes"));
//		closeFile();
	}

	private static void closeFile() {
		DOF.getCTabItem(DOF.getRoot(), "*SQL Scrapbook 0").click(RIGHT);
		DOF.getContextMenu().click(atPath("Close"));
		sleep(1);
		DOF.getButton(DOF.getDialog("Save Resource"), "&No").click();
	}
}
