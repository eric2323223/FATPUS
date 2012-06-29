package component.entity.search;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.SearchCriteria;

public class FileSearchPanel extends RationalTestScript{
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Search");
	}
	
	public static void search(SearchCriteria sc) {
		DOF.getTabFolder(dialog(), "File Search").click(atText("File Search"));
		if(sc.getProperty("text")!=null){
			DOF.getTextField(dialog(), "Containing text:").click();
			dialog().inputChars(sc.getProperty("text"));
		}
		if(sc.getProperty("pattern")!=null){
			DOF.getButton(dialog(), "Ch&oose...").click();
		
		
		
		}
		
		
		
		
		
	}
	
	
	
	

}
