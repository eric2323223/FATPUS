package component.view.properties.workflow;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

public class WFKeyPkMappingTab extends RationalTestScript{
	
	private static TopLevelTestObject mappingDialog(){
		return DOF.getDialog("Personalization Key Mapping");
	}

	public static String getMapping() {
		String result = "";
		sleep(1);
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(table);i++){
			String opName = TableHelper.getCellValue(table, i, 0);
			String keyName = TableHelper.getCellValue(table, i, 1);
			result = result + opName+","+keyName+":";
		}
		if(result.length()==0){
			return null;
		}else{
			return result.substring(0, result.length()-1);
		}
	}
	
	public static void setMapping(String mapping){
		if(mapping!=null){
			for(String item: mapping.split(":")){
				setSingleMapping(item);
			}
		}
	}
	
	public static void setSingleMapping(String mapping){
		String pk = mapping.split(",")[0];
		String key = mapping.split(",")[1];
		DOF.getButton(DOF.getRoot(), "&Add").click();
		sleep(1);
		WO.setCCombo(DOF.getCCombo(mappingDialog(), "Personalization key"), pk);
		WO.setCCombo(DOF.getCCombo(mappingDialog(), "Key"), key);
		DOF.getButton(mappingDialog(), "OK").click();
	}

}
