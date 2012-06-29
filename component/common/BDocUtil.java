package component.common;

import java.awt.Point;
import java.util.ArrayList;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.tool.Cfg;
import com.sybase.automation.framework.tool.StringUtil;
import com.sybase.automation.framework.widget.eclipse.Table;

/**
 * Description : Functional Test Script
 * 
 * @author dongxu
 */
public class BDocUtil extends RationalTestScript {

	public static final int ROLE_LIST_COL = 0;

	private Cfg cfg = Cfg.BDOC;

	/**
	 * Script Name : <b>BDocUtil</b> Generated : <b>Jun 28, 2008 6:29:25 PM</b>
	 * Description : Functional Test Script Original Host : WinNT Version 5.1
	 * Build 2600 (S)
	 * 
	 * @since 2008/06/28
	 * @author dongxu
	 */
	public void testMain(Object[] args) {
	}

	/**
	 * Create a new role for bdoc, negative case is not concerned.
	 * 
	 * @param name
	 * @param desc
	 */
	public void createRole(GuiTestObject createButton,
			TextScrollTestObject nameText,
			TextScrollTestObject descriptionText, GuiTestObject finishButton,
			String name, String desc) {
		createButton.click();
		nameText.setText(name);
		descriptionText.setText(desc);
		finishButton.click();
	}

	/**
	 * Add a role, if specified role is not exist in available list, an
	 * exception will be thrown
	 * 
	 * @param name
	 */
	public void addRole(ScrollGuiSubitemTestObject availableTable,
			GuiTestObject addButton, String name) {
		operateOneRole(availableTable, addButton, name);
	}

	/**
	 * Remove a role, if specified role is not exist in assigned list, an
	 * exception will be thrown
	 * 
	 * @param name
	 */
	public void removeRole(ScrollGuiSubitemTestObject assignedTable,
			GuiTestObject removeButton, String name) {
		operateOneRole(assignedTable, removeButton, name);
	}

	private void operateOneRole(ScrollGuiSubitemTestObject roleTable,
			GuiTestObject operateButton, String name) {
		Table t = new Table(roleTable);
		int row = t.locateRow(name, ROLE_LIST_COL);
		if (row == -1) {
			throw new RuntimeException("Operate one role: Specified role("
					+ name + ") not exist!");
		}
		t.clickCell(row, 0);
		operateButton.click();
	}

	public boolean verifyRolesExist(ScrollGuiSubitemTestObject rolesTable,
			String[] names, boolean exist) {
		boolean ret = doesRowsExist(new Table(rolesTable), names,
				ROLE_LIST_COL, exist);
		return vpManual("RolesExist", Boolean.valueOf(exist),
				Boolean.valueOf(ret)).performTest();
	}

	/**
	 * Judge whether values on specified col exist
	 * 
	 * @param t
	 * @param vals
	 * @param col
	 * @param exist
	 * @return
	 */
	public boolean doesRowsExist(Table t, String[] vals, int col, boolean exist) {
		for (int i = 0; i < vals.length; i++) {
			if (exist != (t.locateRow(vals[i], col) != -1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify single file preview
	 * 
	 * @param path
	 *            file path
	 */
	public boolean verifySinglePreview(GuiTestObject previewButton,
			GuiTestObject pathLabel,
			TextSelectGuiSubitemTestObject previewList, GuiTestObject ok,
			String path) {
		boolean rst = true;
		invokePreviewDialog(previewButton);
		rst = rst && verifyPreviewLabel(pathLabel, path);
		rst = rst
				&& vpManual("SinglePreviewCombo", Boolean.valueOf(false),
						Boolean.valueOf(previewList.exists())).performTest();
		ok.click();
		return rst;
	}

	/**
	 * Verify multi files preview
	 * 
	 * @param path
	 *            file path
	 * @param itemCount
	 *            selected files count
	 */
	public boolean verifyMultiPreview(GuiTestObject previewButton,
			GuiTestObject pathLabel,
			TextSelectGuiSubitemTestObject previewList, GuiTestObject ok,
			String path, int itemCount) {
		boolean rst = true;
		invokePreviewDialog(previewButton);
		rst = rst && verifyPreviewLabel(pathLabel, path);
		rst = rst && verifyMultiPreviewList(previewList, itemCount);
		ok.click();
		return rst;
	}

	private boolean verifyMultiPreviewList(
			TextSelectGuiSubitemTestObject previewList, int itemCount) {
		Boolean combo = Boolean
				.valueOf(previewList.exists()
						&& (((Integer) previewList.getProperty("itemCount"))
								.intValue() == itemCount));
		return vpManual("MultiPreviewCombo", Boolean.valueOf(true), combo)
				.performTest();
	}

	private boolean invokePreviewDialog(GuiTestObject previewButton) {
		if (!previewButton.exists() || !previewButton.isEnabled()) {
			throw new RuntimeException(
					"BDocUtil.invokePreviewDialog: preview button is unavailable!");
		}
		previewButton.click();
		sleep(3);
		if (getScreen().getActiveWindow().getText().toLowerCase().indexOf(
				"download") != -1) {
			getScreen().getActiveWindow().inputKeys("{ESCAPE}");
		}
		return true;
	}

	private boolean verifyPreviewLabel(GuiTestObject pathLabel, String path) {
		Boolean labelRst = Boolean.valueOf(StringUtil.verifySegment(
				(String) pathLabel.getProperty("text"), path, false));
		return vpManual("SinglePreviewLabel", Boolean.valueOf(true), labelRst)
				.performTest();
	}

	public void setComboText(TextSelectGuiSubitemTestObject combo, String text) {
		combo.setText(text);
		combo.setText(text);
	}

	public void setCComboText(ScrollTestObject cCombo, String text) {
		cCombo.doubleClick();
		getScreen().getActiveWindow().inputChars(text);
	}

	public void selectCCombo(ScrollTestObject cCombo,
			SelectGuiSubitemTestObject comboList, String text) {
		int width = ((Point) cCombo.getProperty("size")).x;
		cCombo.click(atPoint(width - 5, 6));
		comboList.click(atText(text));
	}

	public void browse(GuiTestObject browseButton, String path) {
		browseButton.click();
		sleep(2);
		getScreen().getActiveWindow().inputChars(path);
		getScreen().getActiveWindow().inputKeys("{ENTER}");
		if (getScreen().getActiveWindow().getText().indexOf("Open") != -1) {
			getScreen().getActiveWindow().inputKeys("{ENTER}");
		}
	}
}