package component.entity.search;

import java.util.HashMap;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.WN;
import component.entity.model.SearchCriteria;

public class SUPSearchPanel extends RationalTestScript{
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Search");
	}
	
	public static void search(){
		DOF.getButton(dialog(), "&Search").click();	
	}
	
	public static void searchfrommenu(SearchCriteria sc){
		DOF.getMenu().click(atPath("Search->Unwired Workspace..."));
		search(sc);
	}
	
	public static void searchfromright(String projname,SearchCriteria sc){
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(projname)));
		DOF.getContextMenu().click(atPath("Search..."));
		search(sc);
	}
	
	public static void searchfrommenusearchsearch(SearchCriteria sc){
		DOF.getMenu().click(atPath("Search->Search..."));
		DOF.getTabFolder(dialog(), "Sybase Unwired Platform").setState(SINGLE_SELECT, atText("Sybase Unwired Platform"));
		search(sc);
	}
	
	public static void searchway(String projname,String way,SearchCriteria sc){
		if (way.equals("right"))
			searchfromright(projname,sc);
		if(way.equals("menu"))
			searchfrommenu(sc);
		if(way.equals("menusearchsearch"))
			searchfrommenusearchsearch(sc);
	}
	
	public static void search(SearchCriteria sc) {
		if(sc.getProperty("text")!=null){
			DOF.getCombo(dialog(),"Search string (* = any string, ? = any character):").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			sleep(0.5);
			dialog().inputChars(sc.getProperty("text"));
			DOF.getCombo(dialog(),"Search string (* = any string, ? = any character):").click();
			sleep(1);
		}
		
		if (sc.getProperty("M")!=null){
			DOF.getButton(dialog(), "&Mobile Business Object").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("A")!=null){
			DOF.getButton(dialog(), "&Attribute").click();	
			sleep(0.5);
		}
		
		if (sc.getProperty("O")!=null){
			DOF.getButton(dialog(), "&Operation").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("D")!=null){
			DOF.getButton(dialog(), "&Declarations").click();
		    sleep(0.5);
		}
		if (sc.getProperty("R")!=null){
			DOF.getButton(dialog(), "&References").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("AO")!=null){
			DOF.getButton(dialog(), "All occurre&nces").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("Y")!=null){
			DOF.getButton(dialog(), "Yes").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("N")!=null){
			DOF.getButton(dialog(), "No").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("I")!=null){
			DOF.getButton(dialog(), "Ignore").click();
			sleep(0.5);
		}
		
//		//----------------***********************---------------
//		if (sc.getProperty("OPERATIONOPTION")!=null){
//			DOF.getCombo(DOF.getDialog("Search")).click();
//			DOF.getCombo(DOF.getDialog("Search")).click(atText(sc.getProperty("OPERATIONOPTION")));
//			sleep(0.5);
//		}
//	
//		//-----------------**************************----------------
		
		if (sc.getProperty("DST")!=null){
			DOF.getCombo(DOF.getDialog("Search"),"Data source type:").click();
			DOF.getCombo(DOF.getDialog("Search"),"Data source type:").click(atText(sc.getProperty("DST")));
			sleep(0.5);
		}
		
		if (sc.getProperty("CP")!=null){	
			DOF.getCombo(DOF.getDialog("Search"),"Connection profile:").click();
			DOF.getCombo(DOF.getDialog("Search"),"Connection profile:").click(atText(sc.getProperty("CP")));
			sleep(0.5);
		}
		
		if (sc.getProperty("MODE")!=null){
			DOF.getTabFolder(dialog(), "&Role Assignment").setState(SINGLE_SELECT, atText("&Role Assignment"));
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"),"&Role Assignment"), "Mode").click();	
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"), "&Role Assignment"),"Mode").click(atText(sc.getProperty("MODE")));
		}
		
		if (sc.getProperty("ROLES")!=null){
			DOF.getTabFolder(dialog(), "&Role Assignment").setState(SINGLE_SELECT, atText("&Role Assignment"));
			DOF.getTabFolder(DOF.getDialog("Search"), "&Role Assignment").click();	
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(sc.getProperty("ROLES"));	
			sleep(0.5);
		}
		
		if (sc.getProperty("ROLES")==null){
			DOF.getTabFolder(dialog(), "&Role Assignment").setState(SINGLE_SELECT, atText("&Role Assignment"));
			DOF.getTabFolder(DOF.getDialog("Search"), "&Role Assignment").click();	
			dialog().inputKeys(SpecialKeys.CLEARALL);
			sleep(0.5);
		}
		
		if (sc.getProperty("PK")!=null){
			DOF.getTabFolder(dialog(), "Personalization &Key Reference").setState(SINGLE_SELECT, atText("Personalization &Key Reference"));
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"),"Personalization &Key Reference"), "Key:").click();	
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"), "Personalization &Key Reference"),"Key:").click(atText(sc.getProperty("PK")));
			sleep(0.5);
		}
		
		if (sc.getProperty("PK")==null){
			DOF.getTabFolder(dialog(), "Personalization &Key Reference").setState(SINGLE_SELECT, atText("Personalization &Key Reference"));
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"),"Personalization &Key Reference"), "Key:").click();	
			dialog().inputKeys(SpecialKeys.CLEARALL);
			sleep(0.5);
		}
		
		if (sc.getProperty("FILTER")!=null){
			DOF.getTabFolder(dialog(), "Resultset &Filter Reference").setState(SINGLE_SELECT, atText("Resultset &Filter Reference"));
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"),"Resultset &Filter Reference"), "Filter:").click();	
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"), "Resultset &Filter Reference"),"Filter:").click(atText(sc.getProperty("FILTER")));
			sleep(0.5);
		}
		
		if (sc.getProperty("FILTER")==null){
			DOF.getTabFolder(dialog(), "Resultset &Filter Reference").setState(SINGLE_SELECT, atText("Resultset &Filter Reference"));
			DOF.getCombo(DOF.getTabFolder(DOF.getDialog("Search"),"Resultset &Filter Reference"), "Filter:").click();	
			dialog().inputKeys(SpecialKeys.CLEARALL);
			sleep(1);
		}
	
		if (sc.getProperty("AM")!=null){
			DOF.getButton(dialog(), "All Mobile Application pro&jects").click();
			sleep(0.5);
		}
		
		if (sc.getProperty("EM")!=null){
			DOF.getButton(dialog(), "En&closing Mobile Application projects").click();
		    sleep(0.5);
		}
		
		if (sc.getProperty("SM")!=null){
			DOF.getButton(dialog(), "&Single Mobile Application project: ").click();
			sleep(0.5);
		}
		sleep(1);
		search();	
	}

}