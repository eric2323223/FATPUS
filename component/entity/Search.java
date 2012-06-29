package component.entity;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.SearchCriteria;
import component.entity.search.FileSearchPanel;
import component.entity.search.SUPSearchPanel;

public class Search extends RationalTestScript{	
	public static void open(){
		DOF.getMenu().click(atPath("Search->Search..."));
		sleep(1);
	}
		
//	public static void getAllreferences(){
//		
//		DOF.getMenu().click(atPath("Search->References->All References"));
//		sleep(1);
//	}
//	public static void getOperation(){
//		DOF.getMenu().click(atPath("Search->References->Operation"));
//		sleep(1);
//	}
//	public static void getMbo(){
//		DOF.getMenu().click(atPath("Search->References->Mobile Business Object"));
//		sleep(1);
//	}
	

}
