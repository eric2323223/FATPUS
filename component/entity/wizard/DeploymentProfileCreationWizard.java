package component.entity.wizard;

import java.util.regex.Pattern;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.entity.ACW;
import component.entity.WN;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;

public class DeploymentProfileCreationWizard extends ACW {
	String project;
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Deployment Profile");
	}
	
	public void finish(){
		DOF.getButton(dialog(), "&Finish").click();
		sleep(2);
	}

	@Override
	public void start(String string) {
		this.project = string;
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
		DOF.getContextMenu().click(atPath("New->Deployment Profile"));
		sleep(1);
	}
	
	public void setFileName(String str){
		DOF.getTextField(dialog(), "File name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setPkg(String str){
		if(!str.equalsIgnoreCase("all")){
			for(String item:str.split(":")){
				setSinglePkg(item);
			}
		}
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setFileName")){
			return 0;
		}
		if(operation.equals("setPkg")){
			return 2;
		}
		if(operation.equals("setTarget")){
			return 3;
		}
//		if(operation.equals("setFileName")){
//			return 0;
//		}
		throw new RuntimeException("Unkonwn operation: "+operation);
	}

	private void setSinglePkg(String item) {
		TreeHelper.checkNode(DOF.getTree(dialog()), "Deployment->"+item);
	}
	
	public void setTarget(String str){
		for(String item:str.split(":")){
			setSingleTarget(item);
		}
	}

	private void setSingleTarget(String item) {
		String pkg = "/"+this.project+"/"+item.split(",")[0]+".pkg";
		String server = item.split(",")[1];
		                             
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Package", pkg);
		DOF.getTable(dialog()).click(atCell(atRow(row),atColumn("Package")));
		DOF.getButton(dialog(), "Select target...").click();
		TopLevelTestObject targetDialog = DOF.getDialog("Select Target Server");
		String treeNode = TreeHelper.matchPath(DOF.getTree(targetDialog), Pattern.compile(server+".*"));
		DOF.getTree(targetDialog).click(atPath(treeNode));
		DOF.getButton(targetDialog, "OK").click();
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setFileName")){
			return "verifyName";
		}
		return null;
	}
	
	public void verifyName(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("name", this, vc, actual).perform();
	}
	
}
