package component.view.properties.workflow;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.WFKeyDialog;
import component.entity.model.WFKey;
import component.entity.model.WFKeyParameterMapping;

public class WFKeyParameterMappingTab extends RationalTestScript{
	
	private static TopLevelTestObject mappingDialog(){
		return DOF.getDialog("Parameter Mapping");
	}
	
	public static void addMapping(String mapping){
		if(mapping!=null){
			for(String item:mapping.split(":")){
				String parameterName = item.split(",")[0];
				String key = item.split(",")[1];
				addSingleMapping(parameterName, key);
			}
		}
	}
	
	public static void addMapping(WFKeyParameterMapping mapping){
		if(mapping!=null){
			addSingleMapping(mapping.getParameter(), mapping.getKey());
		}
	}

	private static void addSingleMapping(String parameterName, String key) {
		DOF.getButton(DOF.getRoot(), "A&dd").click();
		//tbd
	}

	public static String getMapping() {
		String result = "";
		sleep(1);
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(table);i++){
			String opName = TableHelper.getCellValue(table, i, 0);
			String keyName = TableHelper.getCellValue(table, i, 3);
			result = result + opName+","+keyName+":";
		}
		
//		return result.substring(0, result.length()-1);
		if(result.length()==0){
			return null;
		}else{
			return result.substring(0, result.length()-1);
		}
	}
	
	public static void setMapping(String mapping){
		if(mapping!=null){
			for(String item:mapping.split(":")){
				String parameterName = item.split(",")[0];
				String key = item.split(",")[1];
				setSingleMapping(parameterName, key);
			}
		}
	}
	
	public static void setMapping(String[] mappings){
		if(mappings!=null && mappings.length>0){
			for(String item:mappings){
				String parameterName = item.split(",")[0];
				String key = item.split(",")[1];
				setSingleMapping(parameterName, key);
			}
		}
	}
	
	public static void setMapping(WFKeyParameterMapping mapping){
		if(mapping!=null){
			setSingleMapping(mapping.getParameter(), mapping.getKey());
		}
	}
	
	private static void setSingleMapping(String parameterName, String key){
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Parameter Name", parameterName);
		table.click(atCell(atRow(row), atColumn("Parameter Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		if(key.contains("[")){
			WFKey wfKey = new WFKey(key);
			DOF.getButton(mappingDialog(), "Ne&w Key... ").click();
			WFKeyDialog.key(wfKey);
			mapExistingKey(wfKey.getName());
		}else{
			mapExistingKey(key);
		}
		DOF.getButton(mappingDialog(), "OK").click();
	}
	
	private static void mapExistingKey(String key){
		WO.setCCombo(DOF.getCCombo(mappingDialog(), "Key:"), key);
	}
}
