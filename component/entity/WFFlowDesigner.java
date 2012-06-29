package component.entity;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TextFieldHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.NotificationDialog;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.wizard.WorkFlowCreateionWizard;

public class WFFlowDesigner extends RationalTestScript {
	public static Point getValidPoint() {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		while (true) {
			int x = (int) (Math.random() * 500);
			int y = (int) (Math.random() * 500);
			DOF.getWFFlowDesignCanvas().click(atPoint(x, y));
			sleep(1);
			if (DOF.getCLabelByContent(DOF.getRoot(), "Application") != null) {
				return new Point(x, y);
			}
		}
	}

	public static void link(String from, String to) {
//		zoomToFit();xbwflow->Connections->GoTo
		DOF.getPaletteRoot().click(atPath("xbwflow->Connections->GoTo"));
		if (isStartingPoint(from)) {
			linkStartingPointToScreen(from, to);
		} else {
			linkScreenToScreen(from, to);
		}
//		MainMenu.saveAll();
	}

	private static boolean isStartingPoint(String from) {
		if (from.equals(WorkFlow.SP_ACTIVATE)) {
			return true;
		}
		if (from.equals(WorkFlow.SP_CLIENT_INIT)) {
			return true;
		}
		if (from.equals(WorkFlow.SP_CREDENTIAL_REQUEST)) {
			return true;
		}
		if (from.startsWith(WorkFlow.SP_SERVER_INIT)) {
			return true;
		}
		return false;
	}

	private static void linkScreenToScreen(String from, String to) {
		DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(), from)
				.dragToScreenPoint(
						atPoint(33, 17),
						DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(), to)
								.getScreenPoint(atPoint(17, 18))
								);

	}
	//ff modify 20120313>>>>>>>>>>TBD..
//	private static void linkScreenToScreen(String from, String to) {
//		DOF.getWFScreenFigureToClick(from).dragToScreenPoint(atPoint(33, 17),
//				DOF.getWFScreenFigureToClick(to).getScreenPoint(atPoint(17, 18))
//		);
//		
//	}
	//<<<<<<<<<<<<<<<<<<<<<ff

	private static void linkStartingPointToScreen(String from, String to) {
		if (from.equals(WorkFlow.SP_ACTIVATE)) {
			DOF.getWFActivateFlowStartingPointFigure().dragToScreenPoint(
					atPoint(33, 17),
					DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(), to)
							.getScreenPoint(atPoint(17, 18)));
		}else if (from.equals(WorkFlow.SP_CLIENT_INIT)) {
			DOF.getWFClientInitiateFlowStartingPointFigure().dragToScreenPoint(atPoint(33, 17),
					DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(), to)
							.getScreenPoint(atPoint(17, 18)));
		}else if (from.equals(WorkFlow.SP_CREDENTIAL_REQUEST)) {
			DOF.getWFCredentialRequestFlowStartingPointFigure()
					.dragToScreenPoint(atPoint(33, 17),
							DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(),
									to).getScreenPoint(atPoint(17, 18)));
		}
		else {
			DOF.getWFServerInitiateFlowStartingPointFigure().dragToScreenPoint(atPoint(33, 17),
					DOF.getWFScreenFigure(DOF.getWFFlowDesignCanvas(), to)
							.getScreenPoint(atPoint(17, 18)));
		}
	}

	public static void dragMbo(String project, String mbo) {
		Point point = getValidPoint();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		DOF.getWNTree().dragToScreenPoint(
				atPath(WN.mboPath(project, mbo)),
				DOF.getWFFlowDesignCanvas().getScreenPoint(
						atPoint(point.x, point.y)));

	}

	public static void arrangeAll() {
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Arrange All"));
		sleep(1);
		MainMenu.saveAll();
	}
	
	public static void zoomToFit() {
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Zoom->Zoom to Fit"));
		sleep(2);
		MainMenu.saveAll();
	}

	public static void sendNotification(String project, String workflow,Email email) {
		WN.openWorkFlow(project, workflow);
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Send a notification..."));
		NotificationDialog.send(email);

	}
	
	public static String sendNotification(Email email) {
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Send a notification..."));
		return NotificationDialog.send(email);
	}

	public static void setWorkFlow(WorkFlow wf) {
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		PropertiesView.set(wf);
	}

	public static void addScreen(String screenName) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getPaletteRoot().click(atPath("xbwflow->Screens->Screen"));
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		DOF.getWFScreenFigure(DOF.getRoot(), "<...>").click();
		sleep(1);
//		PropertiesView.clickTab("General");
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputChars(screenName);
		// DOF.getRoot().inputKeys("screen3{ENTER}");
		// DOF.getShellObject(
		// "Mobile Development - wfe/postTest.xbw - Sybase Unwired WorkSpace"
		// ).inputKeys("screen3{ENTER}");
	}
	
//	//fanfei add the following code to add screen with chinese name 20120607>>>>>
	public static void addScreenWithCHName(String screenName) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(1);
		DOF.getPaletteRoot().click(atPath("xbwflow->Screens->Screen"));
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		DOF.getRoot().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		DOF.getWFScreenFigure(DOF.getRoot(), "<...>").click();
		sleep(1);
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputChars("tem");
		sleep(1);
		MainMenu.saveAll();
		DOF.getWFScreenFigure(DOF.getRoot(), "tem").doubleClick();
		PropertiesView.clickTab("General");
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		sleep(1);
		System.out.println("begin to input chinese>>>>>>>>>");
		Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection text=new StringSelection(screenName);  
        clb.setContents(text, null);  
		DOF.getRoot().inputKeys("^v");
		
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
	}
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public static boolean addStartingPoint(StartPoint sp) {
		Point point = getValidPoint();
		DOF.getPaletteRoot().click(
				atPath("xbwflow->Starting Points->" + sp.getType()));
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		if (sp.getType().equals(WorkFlow.SP_SERVER_INIT)) {
			TopLevelTestObject dialog = DOF.getDialog("Notification Processing Wizard");
			
			if(sp.getMbo()!=null){
				if (dialog == null) 
					return false;
				else {
					DOF.getButton(dialog, "&Search...").click();
					TopLevelTestObject dialog2 = DOF.getDialog("Search For Mobile Business Object");
					if(sp.getName()!=null)
						WO.setTextField(dialog2, DOF.getTextField(dialog2, "Name:"),sp.getName());
					if(sp.getProject() !=null)
						WO.setCombo(DOF.getCombo(dialog2, "Project:"), sp.getProject());
					DOF.getButton(dialog2, "&Search").click();
					int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog2), "Name", sp.getMbo());
					sleep(1);
					DOF.getTable(dialog2).click(atCell(atRow(row), atColumn("Name")));
					DOF.getButton(dialog2, "OK").click();
					if(sp.getObjectQuery()!=null){
						sleep(1);
						DOF.getCCombo(DOF.getDialog("Notification Processing Wizard"), "Object query:").click();
						sleep(1);
						DOF.getPoppedUpList().click(atText(sp.getObjectQuery()));
						sleep(1);
					}	
				}
			}
	//ff1.12>>>>>>>>>>>>>>>		
				if(sp.getSubject()!=null){
					DOF.getButton(dialog, "&Next >").click();
					DOF.getTextField(dialog, "Subject:").click();
					dialog.inputKeys(SpecialKeys.CLEARALL);
					dialog.inputChars(sp.getSubject());
					sleep(1);
				}	
				
				if(sp.getSubjectMatchingRule()!=null){
					DOF.getButton(dialog, "&Next >").click();
					DOF.getStyledTextHasLabel(dialog, "Subject:").click();
					TextFieldHelper.hightLightText(dialog,DOF.getStyledTextHasLabel(dialog, "Subject:"), sp.getSubjectMatchingRule());
					DOF.getStyledTextHasLabel(dialog, "Subject:").click(RIGHT);
					DOF.getContextMenu().click(atPath("Select as Matching Rule"));
				}	
				
				if(sp.getParameterValue()!=null){
					DOF.getButton(dialog, "&Next >").click();
				   setParameterValue(sp.getParameterValue());
				}	
				
	//ff1.12<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				sleep(1);
				DOF.getButton(dialog, "&Finish").click();
			}
		if(DOF.getCLabelByContent(DOF.getRoot(), "Application") != null){
			return false;
		}else{
			if(sp.getKey()!=null)
			PropertiesView.set(sp);
			return true;
		}
	}
	
	protected static TopLevelTestObject dialog(){
		return DOF.getDialog("Notification Processing Wizard");
	}
	
	public static void setParameterValue(String str){
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Key", str.split(",")[0]);
		table.click(atCell(atRow(row), atColumn("Key")));
		sleep(0.5);
		DOF.getCombo(dialog(), "Field:").click();
		DOF.getCombo(dialog(), "Field:").click(atText(str.split(",")[1]));
		sleep(0.5);
		//ff>>>>>>>>>>>>>>>10.20>>>>>>change code:
		if(str.split(",")[2]!=""){
			DOF.getTextField(dialog(),"Start tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[2]);
		}
		
		if(str.split(",").length >3){
			DOF.getTextField(dialog(),"End tag:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[3]);
		}
		
		if(str.split(",").length >4){
			DOF.getTextField(dialog(),"Format:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str.split(",")[4]);
		
		}
	}
	
	//ff>>>>>>>>>>9.14
	public static boolean hasMboProject(StartPoint sp) {
		Point point = getValidPoint();
		DOF.getPaletteRoot().click(
				atPath("xbwflow->Starting Points->" + sp.getType()));
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		TopLevelTestObject dialog = DOF.getDialog("Notification Processing Wizard");
		DOF.getButton(dialog, "&Search...").click();
		TopLevelTestObject dialog2 = DOF.getDialog("Search For Mobile Business Object");
		if(((ToggleTestObject)DOF.getButton(dialog2, "&All mobile application projects")).getState().equals("NOT_SELECTED")){
					DOF.getButton(dialog2, "Cancel").click();
					DOF.getButton(dialog, "Cancel").click();
					return true;
		}else{
					DOF.getButton(dialog2, "Cancel").click();
					DOF.getButton(dialog, "Cancel").click();
					return false;
				}
	}
	//<<<<<<<<<ff9.14
	
	public static void removeScreen(String string) {
		DOF.getWFScreenFigure(DOF.getRoot(), string).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}

	public static void renameStartPoint(String string, String string2) {
		if(string.equals(WorkFlow.SP_CLIENT_INIT))
			DOF.getWFStartPointNameFigure(DOF.getRoot(), string).click();
			
		if(string.equals(WorkFlow.SP_SERVER_INIT))
			DOF.getWFServerPointNameFigure(DOF.getRoot(), string).click();
		
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(string2);
		MainMenu.saveAll();
		
	}

	public static void deleteLink(String from, String to) {
		DOF.getWFGoToFigure(DOF.getRoot(), from, to).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}

	public static boolean hasLinkBetween(String from, String to) {
		unregisterAll();
		if(DOF.getWFGoToFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		if(DOF.getWFListviewDetailsNavigationFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		if(DOF.getOperationSuccessNavigationFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		if(DOF.getOperationErrorNavigationFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		if(DOF.getConditionalOperationSuccessFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		if(DOF.getAsyncRequestErrorNavigationFigure(DOF.getRoot(), from, to)!=null){
			return true;
		}
		return false;
	}
	
	//FF12.6>>>>>>add :used to show the type name of link between screens
	public static String getLinkTypeBetween(String from, String to) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		String type ="";
		if(DOF.getWFGoToFigure(DOF.getRoot(), from, to)!=null){
			type = "GoTo";
		} 
		if(DOF.getWFListviewDetailsNavigationFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/ListView";
		}
		if(DOF.getOperationSuccessNavigationFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/OperationSuccess";
		}
		if(DOF.getOperationErrorNavigationFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/OperationError";
		}
		if(DOF.getConditionalOperationSuccessFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/ConditionnalOperation";
		}
		
		//ff add AsyncRequestError 20120605>>>>>>>
		if(DOF.getAsyncRequestErrorNavigationFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/AsyncRequestError";
		}
//		System.out.println("hasTypeOfLink:"+type);
		//>> flv 2012/03/21 add for async error link type
		if(DOF.getWFAsyncErrorLinkFigure(DOF.getRoot(), from, to)!=null){
			type = type+"/AsyncErrorRequest";
		}
//		System.out.println("hasTypeOfLink:"+type);

		return type;
	}
//<<<<<<<<<<<<<<<ff12.6
	
	//FF Modify>>>>>>>>>20120305
//	public static boolean hasScreen(String string) {
//		return DOF.getWFScreenFigure(DOF.getRoot(), string)!=null;
//	}
	//modify as..
	public static boolean hasScreen(String string) {
		return DOF.getWFScreenFigure(string);
	}
	//<<<<<<<<<<<<<<<
	
	public static void dragOperation(String project, String mbo,String operation) {
		Point point = getValidPoint();
		DOF.getWNTree().dragToScreenPoint(atPath(WN.operationPath(project, mbo, operation)),
				DOF.getWFFlowDesignCanvas().getScreenPoint(
						atPoint(point.x, point.y)));

	
		
	}
	//ff>>>>>>>>>>>9.13
	public static String getStartPointname(String sp){
		String spName = "";
		if(sp.equals(WorkFlow.SP_SERVER_INIT))
			spName = DOF.getWFServerInitiatedStartPointName(DOF.getRoot());
		if(sp.equals(WorkFlow.SP_ACTIVATE))
			spName = DOF.getWFActivateInitiatedStartPointName(DOF.getRoot());
		if(sp.equals(WorkFlow.SP_CLIENT_INIT))
			spName = DOF.getWFClientInitiatedStartPointName(DOF.getRoot());
		if(sp.equals(WorkFlow.SP_CREDENTIAL_REQUEST))
			spName = DOF.getWFCredentialRequestStartPointName(DOF.getRoot());
		
		MainMenu.saveAll();
		return  spName;
	}

	public static void removeStartPoint(String str) {
		
		if(str.equals(WorkFlow.SP_CLIENT_INIT))
			DOF.getWFStartPointNameFigure(DOF.getRoot(), str).click(RIGHT);
			
		if(str.equals(WorkFlow.SP_SERVER_INIT))
			DOF.getWFServerPointNameFigure(DOF.getRoot(), str).click(RIGHT);
		
//		DOF.getWFStartPointNameFigure(DOF.getRoot(), str).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
	}
	//ff<<<<<<<<<<<<<<<<<<<<9.13
//ff>>>>>>>>928
	public static void showPropertiesView() {
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Show Properties View"));
	}
	//ff<<<<<<<<<<928
	
	//ff1014>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void setMarkMessageAsProcessedAfterProcessing(String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		PropertiesView.clickTab("General");
		if(string.equals("true"))
		((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Mark message as processed after processing")).clickToState(SELECTED);
		else
		((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Mark message as processed after processing")).clickToState(NOT_SELECTED);
			
	}

	public static void setDeleteMessageAsProcessedAfterProcessing(String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		PropertiesView.clickTab("General");
		if(string.equals("true"))
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).clickToState(SELECTED);
		else
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).clickToState(NOT_SELECTED);
	}
	
	public static void setStartPoint(StartPoint startPoint) {
		String type = startPoint.getType();
		if(type.equals(WorkFlow.SP_ACTIVATE)){
			DOF.getWFActivateFlowStartingPointFigure().click();
		}else if(type.equals(WorkFlow.SP_CLIENT_INIT)){
			DOF.getWFClientInitiateFlowStartingPointFigure().click();
		}else if(type.equals(WorkFlow.SP_CREDENTIAL_REQUEST)){
			DOF.getWFCredentialRequestFlowStartingPointFigure().click();
		}else{
			DOF.getWFServerInitiateFlowStartingPointFigure().click();
		}
		sleep(1);
		PropertiesView.setStartPoint(startPoint);
	}

	//ff 20120306>>>>>>>>>
//	if there is the objectQuery selected in AddStartingPoint(server)
	public static boolean hasObjectQueryAddStartingPoint(StartPoint sp) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = getValidPoint();
		DOF.getPaletteRoot().click(
				atPath("xbwflow->Starting Points->" + sp.getType()));
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		TopLevelTestObject dialog = DOF.getDialog("Notification Processing Wizard");
			if(sp.getMbo()!=null){
					DOF.getButton(dialog, "&Search...").click();
					TopLevelTestObject dialog2 = DOF.getDialog("Search For Mobile Business Object");
					if(sp.getName()!=null)
						WO.setTextField(dialog2, DOF.getTextField(dialog2, "Name:"),sp.getName());
					if(sp.getProject() !=null)
						WO.setCombo(DOF.getCombo(dialog2, "Project:"), sp.getProject());
					DOF.getButton(dialog2, "&Search").click();
					int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog2), "Name", sp.getMbo());
					sleep(1);
					DOF.getTable(dialog2).click(atCell(atRow(row), atColumn("Name")));
					DOF.getButton(dialog2, "OK").click();
					if(sp.getObjectQuery()!=null){
						sleep(1);
						DOF.getCCombo(DOF.getDialog("Notification Processing Wizard"), "Object query:").click();
						sleep(1);
						String oq = PropertiesView.verifykeylistcom();
						if(oq.contains(sp.getObjectQuery())){
							DOF.getPoppedUpList().click(atText(sp.getObjectQuery()));
							sleep(1);
						}else{
							DOF.getButton(dialog, "&Finish").click();
							return false;
						}
					}	
				}
			DOF.getButton(dialog, "&Finish").click();
			return true;
	}

	
}
