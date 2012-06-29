package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.properties.IPropertiesPage;
import component.entity.properties.ProjectNaturesPage;

public class Properties extends RationalTestScript{
	String title;
	
	private TopLevelTestObject dialog(){
		return DOF.getDialog(title);
	}
	
	private GuiSubitemTestObject tree(){
		return DOF.getTree(dialog());
	}

	public Properties(String string) {
		title = string;
	}
	
	public IPropertiesPage path(String path){
		DOF.getTree(dialog()).click(atPath(path));
		if(path.equals("Project Natures")){
			return new ProjectNaturesPage(title);
		}
		else{
			throw new RuntimeException("Unknown path: "+path);
		}
	}
	
	public boolean hasProperiesEntry(String entry){
		try{
			tree().click(atPath(entry));
			DOF.getButton(dialog(), "OK").click();
			return true;
		}catch(Exception e){
			DOF.getButton(dialog(), "OK").click();
			return false;
		}
	}

}
