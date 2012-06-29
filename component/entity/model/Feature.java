package component.entity.model;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

public class Feature {
	private String wizard;
	private String context;
	private String feature;
	boolean defaultstate = true;
	
	public String getFeature() {
		return feature;
	}
	
	public Feature setFeature(String feature) {
		this.feature = feature;
		return this;
	}
	
	public String getWizard(){
		return wizard;
	}
	
	public Feature setWizard(String wizard) {
		this.wizard = wizard;
		return this;
	}
	
	public String getContext() {
		return context;
	}
	
	public Feature setContext(String context) {
		this.context = context;
		return this;
	}
	
	public boolean dofeature(String featurename,String profiletype,boolean state){
		
		//beging state should be set true
		if(state){		
			
		}else{
			 GuiSubitemTestObject table = DOF.getTableByColumnsNames(DOF.getDualHeadersTable(DOF.getRoot()), 
				new String[]{"Feature","Basic Profile","Advanced Profile"});	
				System.out.println("table :"+table.getParent());
			//need to get table..............................
//			GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
			int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Feature", "Cache");
			
			System.out.println("rowindex = "+rowIndex);
			//xuan option>>>>>>>>
			
//			DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand").click();
			
			//<<<<<<<<<<<		
		}
		return state;	
	}
	

	public boolean doWizard(String wizardpagename,String profiletype,boolean state){
		//beging state should be set true
		
		
		if(state){		
		
		}else{
			GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
			int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Wizard page", wizardpagename);
			//xuan option>>>>>>>>
			DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand").click();
			//<<<<<<<<<<<	
			
			
			
			
			
			
		}
		return state;	
	}
	
	public boolean doPropertyview(String tabname,String profiletype,boolean state){
		//beging state should be set true
		
		if(state){		
		
		}else{
			GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
			int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Tab", tabname);
			//xuan option>>>>>>>>
			DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand").click();
			//<<<<<<<<<<<		
		}
		return state;	
	}
	
	public boolean doWorkspaceNavigator(String foldername,String profiletype,boolean state){
		//beging state should be set true
		
		
		
		if(state){		
		
		}else{
			GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
			int rowIndex = TableHelper.getRowIndexOfRecordInColumn(table, "Folder", foldername);
			//xuan option>>>>>>>>
			DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand").click();
			//<<<<<<<<<<<		
		}
		return state;	
	}
}

	