package component.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;
import component.dialog.SearchForMboDailog;
import component.dialog.WFKeyDialog;
import component.entity.model.Modifications;
import component.entity.model.Module;
import component.entity.model.Screen;
import component.entity.model.StartPoint;
import component.entity.model.WFButton;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFHtmlView;
import component.entity.model.WFKey;
import component.entity.model.WFLabel;
import component.entity.model.WFLink;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.model.WizardRunner;
import component.entity.wizard.NotificationProcessingWizard;
import component.entity.wizard.WFJumpStartCreateionWizard;
import component.view.properties.workflow.WFKeyParameterMappingTab;
import component.view.properties.workflow.WFKeyPkMappingTab;

public class PropertiesView extends RationalTestScript{
	
	public static void clickTab(String tab){
		PropertiesTabHelper.clickTabName(tab);
	}
	
	public static void maximize(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Maximize"));
	}
	
	public static void restore(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(RIGHT, atText("Properties"));
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Restore"));
	}
	
	private static void highLight(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
	}
	

	
	public static void setToggleButton(TestObject button, boolean b){
		if(b){
			((ToggleGUITestObject)button).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)button).clickToState(NOT_SELECTED);
		}
	}

	public static void set(IEditable obj){

//		obj.openInPropertiesView();
//		setKey(new WFKey(obj.getKey()));
//		if(obj.getKey()!=null){
//			setKey(new WFKey(obj.getKey()));
//		}

		if(obj instanceof WFEditBox){
			setWFEditBox((WFEditBox)obj);
		}
		if(obj instanceof WFChoice){
			setWFChoice((WFChoice)obj);
		}
		if(obj instanceof WorkFlow){
			setWorkFlow((WorkFlow)obj);
		}
		if(obj instanceof WFSlider){
			setSlider((WFSlider)obj);
		}
		if(obj instanceof WFSignature){
			setSignature((WFSignature)obj);
		}
		if(obj instanceof WFCheckbox){
			setCheckbox((WFCheckbox)obj);
		}
		if(obj instanceof WFLview){
			setLview((WFLview)obj);
		}
		if(obj instanceof WFHtmlView){
			setHtmlView((WFHtmlView)obj);
		}
		if(obj instanceof StartPoint){
			setStartPoint((StartPoint)obj);
		}
		if(obj instanceof WFScreenMenuItem){
//			obj.update();
			setMenuItem((WFScreenMenuItem)obj);
		}
		if(obj instanceof WFButton){
			setButton((WFButton)obj);
		}
		//ff10.25>>>>>>>>>>>>>>>>>
		if(obj instanceof WFLabel){
			setLabel((WFLabel)obj);
		}
		if(obj instanceof WFLink){
			setLink((WFLink)obj);
		}
		//ff10.25<<<<<<<<<<<<<<<<<<<<<<
		MainMenu.saveAll();
	}

	//ff10.25>>>>>>>>>>>>>>>>>
	
	private static void setButton(WFButton obj) {
		obj.openInPropertiesView();
		obj.update();
	}

	private static void setLink(WFLink item) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		if(item.getLabel()!= null)
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), item.getLabel());
		if(item.getNewKey()!= null)
			setNewKey(item.getNewKey());
//		if(item.getKey()!=null)//has error
//			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Key:"), item.getKey());
		if(item.getDefaultValue()!= null){
			DOF.getTextField(DOF.getRoot(), "Default value:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(item.getDefaultValue());
		}
		if(item.getLogicalType()!= null){
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Logical type:"), item.getLogicalType());
		}
		if(item.getPrefix()!= null){
			DOF.getTextField(DOF.getRoot(), "Prefix:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(item.getPrefix());
		}
		if(item.getSuffix()!= null){
			DOF.getTextField(DOF.getRoot(), "Suffix:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(item.getSuffix());
		}
		restore();
	}
	
	private static void setLabel(WFLabel item) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		if(item.getNewKey()!=null)
			setNewKey(item.getNewKey());
		if(item.getKey()!=null)
			WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding"),0),item.getKey());
		
		if(item.getDefaultvalue()!= null){
			DOF.getTextField(DOF.getRoot(), "Default value:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(item.getDefaultvalue());
		}
	restore();
	}
	
	//ff10.25<<<<<<<<<<<<<<<<<<<<<<
	private static void setMenuItem(WFScreenMenuItem item) {
		maximize();
		clickTab("General");
//		if(item.getName()!=null){
//			clickTab("General");
//			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Name:"), item.getName());
//		}
		if(item.getKey()!=null){
			DOF.getTextField(DOF.getRoot(), "Key:").click();
			DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			DOF.getRoot().inputChars(item.getKey());
		}
		if(item.getDefaultItem()!=null){
			clickTab("General");
			if(item.getDefaultItem().equals("yes")||item.getDefaultItem().equals("true")){
				((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(SELECTED);
			}else{
				((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(NOT_SELECTED);
				
			}
		}
		if(item.getType()!=null){
			clickTab("General");
			DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Type:"), item.getType());
		}
		if(item.getScreen()!=null){
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Screen:"), item.getScreen());
		}
		if(item.getMbo()!=null){
			clickTab("General");
			if(item.getMbo().equals("null")){
				DOF.getButton(DOF.getRoot(), "C&lear").click();
				sleep(2);
			}else{
				String project = item.getProject();
				String mbo = item.getMbo();
				DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
				DOF.getButton(DOF.getRoot(), "&Search...").click();
				SearchForMboDailog.selectMbo(DOF.getDialog("Search For Mobile Business Object"), project, mbo);
			}
		}
		/////////////////////////ff>>>>>>>>>>>>>>>>>>>>>>>>
		if(item.getOperation()!=null){
			clickTab("General");
			DOF.getButton(DOF.getRoot(), "Invoke &operation").click();
			DOF.getCCombo(DOF.getRoot(), "Operation:").click();
			DOF.getPoppedUpList().click(atText(item.getOperation()));
		}
		if(item.getObjectQuery()!=null){
			clickTab("General");
//			DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
			DOF.getButton(DOF.getRoot(), "Invoke object &query").click();
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Object query:"), item.getObjectQuery());
		}
		//>>>> (flv 10/21 start) <<<<
		if (!item.isGenerateOldVal()) {
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Generate old &value keys")).clickToState(NOT_SELECTED);
		}
		//
		if (!item.isShowCredScreen()) {
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Show credential screen on authentication failure")).clickToState(NOT_SELECTED);
		}
		//>>>> (flv 10/21 end) <<<<
		
		if(item.getOpenScreen()!=null){
			clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "Screen:").click();
			DOF.getPoppedUpList().click(atText(item.getOpenScreen()));
		}
		
		if(item.getDefaultSuccessScreen()!=null){
			clickTab("General");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Default Success Screen:"), item.getDefaultSuccessScreen());
		}
		//>>>>>>>>>>>>>> flv 09-28  Start<<<<<<<<<<<
		//Submit Error Msg
		if(item.getSubmitErrMsg()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit error message:"), item.getSubmitErrMsg());
		}
		//Time Out
		if(item.getTimeOut()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Timeout:"), item.getTimeOut());
		}
		//on device cache time out
		if(item.getOnDevCacheTimeOut()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "On-device cache timeout:"), item.getOnDevCacheTimeOut());
		}
		// error screen
		
//		if(item.getErrorScreen()!=null){
//			clickTab("General");
//			DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
//			DOF.getPoppedUpList().click(atText(item.getErrorScreen()));
//		}
		
//ff11.15 modify to>>>>>>>>>>:
		if(item.getErrorScreen()!=null){
			clickTab("General");
			if(item.getErrorScreen().equals("General"))
				DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
			else {
				DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
				DOF.getPoppedUpList().click(atText(item.getErrorScreen()));
			}
		}
				
			
		//ff11.15<<<<<<<<<<<<
		//submit confrim msg
		if(item.getSubmitConfirmMsg()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit confirmation message:"), item.getSubmitConfirmMsg());
		}
		if(item.getResubmitConfirmMsg()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Resubmit confirmation message:"), item.getResubmitConfirmMsg());
		}
		//>>>>>>>>>>>>>> flv 09-28  End<<<<<<<<<<<
		
		
		//ff12.26>>>>>>>>>>>>>>>>>>>>>>
		if(item.listKey()!= null){
			clickTab("General");
			DOF.getCCombo(DOF.getRoot(), "List key:").click();
			DOF.getPoppedUpList().click(atText(item.listKey()));
			System.out.println("has excute list key");
		}
		
		if(item.getParameterMapping()!=null){
			clickTab("Parameter Mappings");
			WFKeyParameterMappingTab.setMapping(item.getParameterMapping());
		}	
		if(item.getPkMapping()!=null){
			MainMenu.saveAll();
			clickTab("Personalization Key Mappings");
			WFKeyPkMappingTab.setMapping(item.getPkMapping());
		}
		
//ff10.14>>>>>>>>>>>>>>
		if(item.getInvokeParentUpdate()){
			clickTab("General");
			DOF.getButton(DOF.getRoot(), "Invoke &parent update").click();
		}
		
		if(item.getGenerateErrorScreen()){
			clickTab("General");
			DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
		}

//<<<<<<<<<<<<<<ff
		//>>>>>>>jiao 05.29 <<<<<<<
		if(item.getSubject()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Subject:"), item.getSubject());
		}
		if(item.getFrom()!=null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "From:"), item.getFrom());
		}
		//>>>>>>>>jiao  05.29  End<<<<<<<<
		
		clickTab("General"); //workaround to a bug
		restore();
	}
	
///*	private static void mapSinglePk(String mapping){
//		String pk = mapping.split(",")[0];
//		String key = mapping.split(",")[1];
//		DOF.getButton(DOF.getRoot(), "&Add").click();
//		sleep(1);
//		TopLevelTestObject dialog = DOF.getDialog("Personalization Key Mapping");
//		WO.setCCombo(DOF.getCCombo(dialog, "Personalization key"), pk);
//		WO.setCCombo(DOF.getCCombo(dialog, "Key"), key);
//		DOF.getButton(dialog, "OK").click();
//		
//	}*/

	private static void setHtmlView(WFHtmlView obj) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		if(obj.getNewKey()!=null)
		setNewKey(obj.getNewKey());
		if(obj.getNewKeyBindMbo()!=null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		if(obj.getdefaultValue()!= null){
			clickTab("General");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Default value:"), obj.getdefaultValue());
		}		
		
		restore();
	}

	public static void setStartPoint(StartPoint sp) {
		DOF.getWFStartPointFigure(sp.getType()).doubleClick();
		sleep(1);
		maximize();
		if(sp.getObjectQuery()!=null){
			clickTab("General");
			new NotificationProcessingWizard().create(sp, new WizardRunner());
		}
		if(sp.getKey()!=null){
			String[] keys = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
	//		for(String key:keys){
	//			removeKey(key);
	//		}
			if(sp.getType().equals(WorkFlow.SP_SERVER_INIT)){
				for(String key:keys)
					removeKeyServer(key);
				setNewKeyServer(sp.getKey());
			}
			else{
				for(String key:keys)
					removeKey(key);
				setNewKey(sp.getKey());
			}
		}
		clickTab("General");
		if(sp.getGenerateErrorScreen()){
			DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
			sleep(1);
		}
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Error screen:"), sp.getErrorScreen());
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Subject:"), sp.getSubject());
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "From:"), sp.getFrom());
		restore();
		MainMenu.saveAll();
	}
	
	public static void setStartPoint(String name, Modifications mods){
		mods.act();
	}
	
	//ff>>>9.9
	public static void removeKeyServer(String key) {
		// TODO Auto-generated method stub
		clickTab("Keys");
		removeKey(key);
	}

	public static void setNewKeyServer(String newKey){
		clickTab("Keys");
		setNewKey(newKey);
	}
//	private static void removeKey(String str){
//		if(str!=null){
//			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Key Name", str);
//			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Key Name")));
//			DOF.getButton(DOF.getRoot(), "&Remove").click();
//		}
//	}
//	
	public static void removeKey(String str){
		if(str!=null){
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Key Name", str);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Key Name")));
			DOF.getButton(DOF.getRoot(), "&Remove").click();
		}
	}
	
//	public static Object getProperty(Class klass, String id, String property){
//		try {
//			IEditable obj = ((IEditable)klass.newInstance()).id(id);
//			obj.openInPropertiesView();
//			return obj.getProperty(property);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("Failed to get property in PropertiesView");
//		}
//	}

	private static void setLview(WFLview obj) {
		
		clickTab("General");
		
		//ff0208 modify as>>>>
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		//ff0208<<<<<<<<<
		
		if (null != obj.getKey()) {
			clickTab("General");
			//WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding"), "Key:"), obj.getKey());
			TestObject[] comps = DOF.getGroup(DOF.getRoot(), "Input Data Binding").find(atDescendant("class", "org.eclipse.swt.widgets.Composite"));
			if (comps != null && comps.length > 0) {
				TestObject[] combos = comps[0].find(atDescendant("class", "org.eclipse.swt.custom.CCombo"));
				ScrollTestObject sto = (ScrollTestObject)combos[0];
				sto.click();
				DOF.getPoppedUpList().click(atText(obj.getKey()));
			}
		}
		//maximize();
		if (null != obj.getLvDetailScreen()) {
			clickTab("General");
			TestObject[] combos = DOF.getRoot().find(atDescendant("class",	"org.eclipse.swt.custom.CCombo"));
			if (combos != null && combos.length > 0) {
				for (int i = 0; i < combos.length; i++) {
					if (null != combos[i].getProperty(".priorLabel")) {
						if (DOF.isVisible(combos[i])
								&& combos[i].getProperty(".priorLabel").equals("Listview Details Screen:")) {
							((ScrollTestObject) combos[i]).click();
							DOF.getPoppedUpList().click(
									atText(obj.getLvDetailScreen()));
						}
					}
				}
			}
		}
		//>>>>>>>>>>>> flv <<<<<<<<<<<<
		
//ff0208 modify as>>>>>>
		if(obj.getNewKeyBindMBOQueryRequest()!= null){
			clickTab("General");
			setNewKeyBindMBOQueryRequestCommon(obj.getNewKeyBindMBOQueryRequest());
		}
//ff0208 modify as<<<<<<<<<<<<<<
		
		//ff1220>>>>>>>>>>>>>>>>>>>>>>>>>
		if(obj.getEmptymessage() != null)
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(),"On empty list:"), obj.getEmptymessage());

		if(obj.getalternateColor()!= null)
			WO.setTextField(DOF.getRoot(), DOF.getTextFieldByAncestorLine(DOF.getRoot(), "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->PageBook->Composite->Composite->Composite->Composite->Shell"), obj.getalternateColor());
		//ff1220<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		MainMenu.saveAll();
		//restore();
	}

	private static void setCheckbox(WFCheckbox obj) {
		maximize();
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), obj.getLabel());
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Label position:"), obj.getLabelPosition());
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		if(obj.getNewKeyBindMbo()!= null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		
		if(obj.getIfreadonly())
			setToggleButton(DOF.getButton(DOF.getRoot(), "Read &only"), true);
		if(obj.getIfRequired()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "&Required"), true);
			}
		
		//add by yanxu
		if(obj.getdefaultValue()!= null){
			clickTab("General");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Default value:"), obj.getdefaultValue());
		}
		
		if(obj.getValidationMessage() !=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation message:"), obj.getValidationMessage());
		}
		
		//end
		
		restore();
	}

	private static void setSignature(WFSignature obj) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), obj.getLabel());
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Label position:"), obj.getLabelPosition());
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		if(obj.getNewKeyBindMbo()!= null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		//add by yanxu
		if(obj.getIfRequired()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "&Required"), true);
			}
		//end
		restore();
	}

	private static void setSlider(WFSlider obj) {
		maximize();
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), obj.getLabel());
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Label position:"), obj.getLabelPosition());
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		if(obj.getNewKeyBindMbo()!= null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		if(obj.getMinvalue()!= null)
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Minimum value:"), obj.getMinvalue());
		
		if(obj.getmaxvalue()!= null)
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Maximum value:"), obj.getmaxvalue());
		if(obj.getIfreadonly()){
//			DOF.getButton(DOF.getRoot(),"Read &only").click();
			setToggleButton(DOF.getButton(DOF.getRoot(), "Read &only"), true);
		}
		
		//add by yanxu
		if(obj.getValidationMessage() !=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation message:"), obj.getValidationMessage());
		}
		//end
		
		restore();
	}

	private static void setWorkFlow(WorkFlow obj) {
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Module version:"), obj.getVersion());
		DOF.getTextField(DOF.getRoot(), "Module name:").click();
	}

	private static void setWFChoice(WFChoice obj) {
		maximize();
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), obj.getLabel());
	
		//	ff11.3>>>>>>>>>>>>>>>>>>>>
		if (obj.getKey()!=null) {
			TestObject[] comps = DOF.getGroup(DOF.getRoot(), "Input Data Binding").find(atDescendant("class", "org.eclipse.swt.widgets.Composite"));
			if (comps != null && comps.length > 0) {
				TestObject[] combos = comps[0].find(atDescendant("class", "org.eclipse.swt.custom.CCombo"));
				ScrollTestObject sto = (ScrollTestObject)combos[0];
				sto.click();
				DOF.getPoppedUpList().click(atText(obj.getKey()));
			}
		}
		//ff1219>>>>>>>>>>>>>>>>>>>>>>
		if(obj.getIfreadonly()){
			setToggleButton(DOF.getButton(DOF.getRoot(), "Read &only"), true);
		}
		//ff1219<<<<<<<<<<<<<<<<
		//ff11.3<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		
		if(obj.getNewKeyBindMbo()!= null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		
		if(obj.getNewKeyBindMBORelationship()!= null)
			setNewKeyBindMBORelationship(obj.getNewKeyBindMBORelationship());
		
		if(obj.getLabelPosition() != null)
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Label position:"), obj.getLabelPosition());
		
		if(obj.getLogicalType() != null)
			WO.setCCombo(DOF.getCComboByAncestorLine(DOF.getRoot(), "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->PageBook->Composite->Composite->Composite->Composite->Shell"), obj.getLogicalType());
//		setItems(obj.getItem());
		
		if(obj.getOption()!= null&&obj.getOption().split(",")[0].equals("Dynamic")){
			DOF.getButton(DOF.getRoot(), "&Dynamic").click();
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Value key:"),obj.getOption().split(",")[1]);
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Display name key:"),obj.getOption().split(",")[2]);
		}
		
		if(obj.getOption()!= null&&obj.getOption().split(",")[0].equals("Static")){
			for(String option: obj.getOption().split(":")){
				DOF.getButton(DOF.getRoot(), "&Add").click();
				TopLevelTestObject dialog = DOF.getDialog("Choice Option Item");
				WO.setTextField(dialog,DOF.getTextField(dialog, "Display name:"),option.split(",")[1]);
				WO.setTextField(dialog,DOF.getTextField(dialog, "Value:"),option.split(",")[2]);
				sleep(0.25);
				String expect = "Input the choice option item with name and value pair.";
				String actual = DOF.getTextFieldByAncestorLine(DOF.getDialog("Choice Option Item"), "Composite->Shell->Shell").getProperty("text").toString();
				if (actual.equals(expect)){
					DOF.getButton(dialog, "OK").click();
				}
				else
					DOF.getButton(dialog, "Cancel").click();
				    System.out.println("[Actual message]"+actual);
				}
			}
		
		 //add by yanxu
		if(obj.getIfRequired()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "&Required"), true);
		}
		
		//ffan 0320>>>>>
		if(obj.getValidationMessage()!=null)
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation message:"), obj.getValidationMessage());
		//<<<<<<<<0320
		
		if(obj.getNumberofdecimal()!= null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Number of decimals:"), obj.getNumberofdecimal());
			//setToggleButton(DOF.getButton(DOF.getRoot(), "Required"), true);
		}
		
		
		if(obj.getValidationMessage()!= null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation message:"), obj.getValidationMessage());
		}
			
		restore();
		
	}

	private static void setWFEditBox(WFEditBox obj) {
		maximize();
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Label:"), obj.getLabel());
		if(obj.getLabelPosition() != null){
			clickTab("General");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Label position:"), obj.getLabelPosition());
		}
		//		>>>>>>>>>>> flv <<<<<<<<<<<<
		if(obj.getLogicalType() != null){
			sleep(1);
//			WO.setCCombo(DOF.getCComboByAncestorLine(DOF.getRoot(), "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->PageBook->Composite->Composite->Composite->Composite->Shell"), obj.getLogicalType());
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Logical type:"), obj.getLogicalType());
		}
		//>>>> flv 11-03 end<<<<<<<<
		if (null != obj.getKey()) {
			clickTab("General");
			//WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding"), "Key:"), obj.getKey());
			TestObject[] comps = DOF.getGroup(DOF.getRoot(), "Input Data Binding").find(atDescendant("class", "org.eclipse.swt.widgets.Composite"));
			if (comps != null && comps.length > 0) {
				TestObject[] combos = comps[0].find(atDescendant("class", "org.eclipse.swt.custom.CCombo"));
				ScrollTestObject sto = (ScrollTestObject)combos[0];
				sto.click();
				DOF.getPoppedUpList().click(atText(obj.getKey()));
			}
		}
//		>>>>>>>>>>> flv <<<<<<<<<<<<<<
		if(obj.getNewKey()!= null)
			setNewKey(obj.getNewKey());
		if(obj.getNewKeyBindMbo()!= null)
			setNewKeyBindMBO(obj.getNewKeyBindMbo());
		//>>>>>>>>> flv 11-03 comment <<<<<<<<
//		if(obj.getLogicalType() != null){
//			sleep(1);
////			WO.setCCombo(DOF.getCComboByAncestorLine(DOF.getRoot(), "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->PageBook->Composite->Composite->Composite->Composite->Shell"), obj.getLogicalType());
//			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Logical type:"), obj.getLogicalType());
//		}
		//>>>>>>>>> flv <<<<<<<<<<<<
		//<flv 10/20 added>
		if(null != obj.getDefaultValue()){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Default value:"), obj.getDefaultValue());
		}//<flv 10/20 added>
		if(obj.getIfreadonly()){
			clickTab("General");
			setToggleButton(DOF.getButton(DOF.getRoot(), "Read &only"), true);
		}
		if(obj.getPassword()){
			clickTab("General");
			setToggleButton(DOF.getButton(DOF.getRoot(), "&Password"), true);
		}
		if(obj.getMaxLength()!=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Maximum length:"), obj.getMaxLength());
		}
		if(obj.getLines()!=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Number of lines:"), obj.getLines());
		}
		if(obj.getIfRequired()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "&Required"), true);
		}
		//##########<flv 10/20>
		if(obj.isCredentialUserName()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "Credential cache &username"), true);
		}
		if(obj.isCredentialPassword()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "Credential cache pass&word"), true);
		}
		//##########<flv 10/20>
		
		//Add by yanxu
		if(obj.getTimeprecision() !=null){
			clickTab("Advanced");
			WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Time precision:"), obj.getTimeprecision());
		}
		
		if(obj.getcredentialusername()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "Credential cache &username"), true);
		}
		if(obj.getcredentialpassword()){
			clickTab("Advanced");
			setToggleButton(DOF.getButton(DOF.getRoot(), "Credential cache pass&word"), true);
		}
		
		if(obj.getValidationExpression() !=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation regular expression:"), obj.getValidationExpression());
		}
		if(obj.getValidationMessage() !=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Validation message:"), obj.getValidationMessage());
		}
		if(obj.getmaximumvalue() !=null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Maximum value:"), obj.getmaximumvalue());
		}
		if(obj.getNumberofdecimals()!= null){
			clickTab("Advanced");
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Number of decimals:"), obj.getNumberofdecimals());
			//setToggleButton(DOF.getButton(DOF.getRoot(), "Required"), true);
		}
		
		//end
		
		restore();
	}

	private static void setNewKey(String newKey) {
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			sleep(1);
			WFKeyDialog.key(newKey);
//			TopLevelTestObject dialog = DOF.getDialog("Key");
//			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
//			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
//			DOF.getButton(dialog, "OK").click();
		}
	}
	
	private static void setKey(WFKey key){
		if(key!=null){
			clickTab("Keys");
			if(key.getType()!=null){
				if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
					DOF.getButton(DOF.getRoot(), "&New...").click();
				}else{
					DOF.getButton(DOF.getRoot(), "&New key...").click();
				}
				sleep(1);
				WFKeyDialog.key(key);
			}else{
				//select existing key
			}
		}
	}
	
	//add by yanxu,set this method to public
	public static void NewKey(String newKey) {
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			sleep(1);
			WFKeyDialog.key(newKey);
		}
	}
	
	//////////////ff>>>>>>>>>>>>>9.6
	public static void setNewKeyBindMBO(String newKey) {
		//clickTab("Keys");
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Key");
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
			
			if(newKey.split(",")[2]!=null){
				((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
				WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[2]);//parent mbo
			}
			if(newKey.split(",")[3]!=null){
				DOF.getButton(dialog, "&Mobile business object attribute").click();
				WO.setCCombo(DOF.getCCombo(dialog, "Name:"), newKey.split(",")[3]);
			}
			DOF.getButton(dialog, "OK").click();
		}
	}
	
	//ff0208 modify as>>>>>>>>>>>>>>>>>>	
	public static void setNewKeyBindMBOQueryRequest(String newKey) {
		clickTab("Keys");
		setNewKeyBindMBOQueryRequestCommon(newKey);
	}
	
	public static void setNewKeyBindMBOQueryRequestCommon(String newKey) {
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Key");
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
			
			if(newKey.split(",")[2]!=null){
				((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
				WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[2]);//parent mbo
			}
			DOF.getButton(dialog, "MBO object &query results").click();
			
			//add by yanxu
			if(newKey.split(",")[3]!=null){
			     int i=3;
				DOF.getButton(dialog, "&Edit...").click();
				TopLevelTestObject dialogKey = DOF.getDialog("Key");
				for(i=3;i<newKey.split(",").length;i++){
					DOF.getButton(dialogKey, "&New...").click();
					sleep(1);
					TopLevelTestObject dialogKeykey = DOF.getDialog("Key");	
					((ToggleGUITestObject)DOF.getButton(dialogKeykey, "&Sent by server")).clickToState(SELECTED);
					sleep(1);
					((ToggleGUITestObject)DOF.getButton(dialogKeykey, "&Mobile business object attribute")).clickToState(SELECTED);
					
					WO.setCCombo(DOF.getCCombo(dialogKeykey, "Name:"), newKey.split(",")[i]);//son attribute
					sleep(1);
					
					DOF.getButton(dialogKeykey, "OK").click();
					}
				sleep(1);
				DOF.getButton(dialogKey, "OK").click();
			}
			//end
			
			DOF.getButton(dialog, "OK").click();
		}
	}
	
//ff0208 modify as<<<<<<<<<<<<<<<
	public static void setEmailMbo(String mbo){
			clickTab("General");
			DOF.getButton(DOF.getRoot(), "&Search...").click();
			TopLevelTestObject dialog = DOF.getDialog("Search For Mobile Business Object");
			DOF.getButton(dialog, "&Search").click();
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog), "Name", mbo);
			sleep(1);
			DOF.getTable(dialog).click(atCell(atRow(row), atColumn("Name")));
			DOF.getButton(dialog, "OK").click();
		}
	//ff11.1>>>>>>>>>>>>>>>>>
	public static void setNewKeyBindMBORelationship(String newKey) {
		if(newKey.split(",")[0].equals("Flow Design")){
			DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
			DOF.getWFFlowDesignCanvas().click(atPoint(33, 17));
			DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
			}
		if(newKey.split(",")[0].equals("Screen Design")){
			
			//ff modify code in 1.6>>>>>>>>>>
			DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
			Point point = WFScreenDesigner.getValidPoint();
			DOF.getWFScreenDesignCanvas().click(LEFT, atPoint(point.x, point.y));
		    DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		    
			}
		//ff 1.12>>>>>>>>>>>>>>>>
		if(newKey.split(",")[0].equals("WorkFlow.SP_SERVER_INIT")){
			DOF.getWFServerInitiateFlowStartingPointFigure().click();
			DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		}
		
		if(newKey.split(",")[0].equals("WorkFlow.SP_CLIENT_INIT")){
			DOF.getWFClientInitiateFlowStartingPointFigure().click();
			DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		}
		//<<<<<<<<<<<<ff 1.12
		
	//ff11.1<<<<<<<<<<<<<<<<
		if(newKey.split(",")[1]!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Key");
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[1]);
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[2]);
			
			if(newKey.split(",")[3]!=null){
				((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
				WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[3]);//parent mbo
			}
			if(newKey.split(",")[4]!=null){
				DOF.getButton(dialog, "Mobile business object &relationship").click();
				WO.setCCombo(DOF.getCCombo(DOF.getGroup(dialog, "Input Data Binding"),2), newKey.split(",")[4]);
			}
			if(newKey.split(",")[5]!=null){
				DOF.getButton(dialog, "&Edit...").click();
				TopLevelTestObject dialogKey = DOF.getDialog("Key");
				
				for(int i=5;i<newKey.split(",").length;i++)
				{
					DOF.getButton(dialogKey, "&New...").click();
					TopLevelTestObject dialogKeykey = DOF.getDialog("Key");
					((ToggleGUITestObject)DOF.getButton(dialogKeykey, "&Sent by server")).clickToState(SELECTED);
					DOF.getButton(dialogKeykey, "&Mobile business object attribute").click();
					WO.setCCombo(DOF.getCCombo(dialogKeykey, "Name:"), newKey.split(",")[i]);//son attribute
					DOF.getButton(dialogKeykey, "OK").click();
				}
				
				DOF.getButton(dialogKey, "OK").click();
				
				
			}
			DOF.getButton(dialog, "OK").click();
		}
		
	}
	
	
	
/////<<<<<<<<<<<<<<<<<<//ff 9.6
	private static void setItems(String items){
		for(String item:items.split(":")){
			setSingleItem(item);
		}
	}

	private static void setSingleItem(String item) {
		String name = item.split(",")[0];
		String value = item.split(",")[1];
		DOF.getButton(DOF.getRoot()	, "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Choice Option Item");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Display name:"), name);
		WO.setTextField(dialog, DOF.getTextField(dialog, "Value:"), value);
		DOF.getButton(dialog, "OK").click();
	}

	public static StartPoint getStartPoint(String type) {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		sleep(0.5);
		if(type.equals(WorkFlow.SP_SERVER_INIT)){
			return getServerInitStartPoint();
		}else if(type.equals(WorkFlow.SP_ACTIVATE)){
			return getActivitedStartPoint();
		}else if(type.equals(WorkFlow.SP_CLIENT_INIT)){
			return getClientInitStartPoint();
		}else{
			return getCredentialStartPoint();
		}
	}
	
	private static StartPoint getCredentialStartPoint() {
		DOF.getWFCredentialRequestFlowStartingPointFigure().click();
		return getSimpleStartPoint();
	}

	private static StartPoint getClientInitStartPoint() {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFClientInitiateFlowStartingPointFigure().click();
		return getSimpleStartPoint();
	}

//	private static StartPoint getServerInitStartPoint() {
//		DOF.getWFServerInitiateFlowStartingPointFigure().click();
//		clickTab("General");
//		String mbo = DOF.getTextField(DOF.getRoot(),"Mobile business object:").getProperty("text").toString();
//		return new StartPoint().type(WorkFlow.SP_SERVER_INIT).mbo(mbo);
//	}
	
	private static StartPoint getServerInitStartPoint() {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
//		clickTab("Keys");
//		String mbo = DOF.getTextField(DOF.getRoot(),"Mobile business object:").getProperty("text").toString();
//		return new StartPoint().type(WorkFlow.SP_SERVER_INIT).mbo(mbo);
		
		
//		return getSimpleStartPoint();
		StartPoint sp = new StartPoint().type(WorkFlow.SP_SERVER_INIT);
		sp = getGeneralInfoOfStartPoint(sp);
		sp = getKeyAttributesofStartPoint(sp);
		sp = getParameterMappingOfStartPoint(sp);
		sp = getPkMappingOfStartPoint(sp);
		return sp;
	}
	
	private static StartPoint getGeneralInfoOfStartPoint(StartPoint sp) {
		clickTab("General");
		sp.errorScreen(WO.getText(DOF.getCCombo(DOF.getRoot(), "Error screen:")));
		sp.subject(WO.getText(DOF.getTextField(DOF.getRoot(), "Subject:")));
		sp.from(WO.getText(DOF.getTextField(DOF.getRoot(), "From:")));
		return sp;
	}

	private static StartPoint getPkMappingOfStartPoint(StartPoint sp) {
		clickTab("Personalization Key Mapping");
		return sp.pkMapping(WFKeyPkMappingTab.getMapping());
	}

	private static StartPoint getParameterMappingOfStartPoint(StartPoint sp){
		clickTab("Parameter Mapping");
		return sp.parameterMapping(WFKeyParameterMappingTab.getMapping());
	}
	
	private static StartPoint getActivitedStartPoint(){
		DOF.getWFActivateFlowStartingPointFigure().click();
		return getSimpleStartPoint();
	}
	
	private static StartPoint getSimpleStartPoint(){
		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] types = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		StartPoint sp = new StartPoint().type(WorkFlow.SP_ACTIVATE);
		for(int i=0;i<names.length;i++){
			sp.key(names[i]+","+types[i]);
		}
		return sp;
	}

	//>>>>>>>>>>>>>>>>>ff 9.7>>>>>>>>>>>>>>>>>	
	public static void jumpStart(WorkFlow wf){	
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		new WFJumpStartCreateionWizard().create(wf, new WizardRunner());
	}
//clean  Email's mbo 
	public static void clearMbo() {
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "C&lear").click();
	}
	
//ff10.26>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// bind key for control in screen:
	public static void bindKeyInScreen(String screen,String from,String newKey) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		WFScreenDesigner.showPropertiesView();
		clickTab("Keys");
		editKey(from);
		sleep(1);
		
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
		
		if(newKey.split(",")[2]!=null){
			((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
			WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[2]);//parent mbo
		}
		if(newKey.split(",")[3]!=null){
			DOF.getButton(dialog, "&Mobile business object attribute").click();
			WO.setCCombo(DOF.getCCombo(dialog, "Name:"), newKey.split(",")[3]);
		}
		DOF.getButton(dialog, "OK").click();
		MainMenu.saveAll();
	}
	//ff10.26<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public static void editKey(String str) {
		if(str!=null){
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Key Name", str);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Key Name")));
			DOF.getButton(DOF.getRoot(), "Ed&it...").click();
		}	
	}
	
	public static void setKeyUserDefined(String key){
		clickTab("Keys");
		editKey(key);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		DOF.getButton(dialog,"&User-defined").click();
		DOF.getButton(dialog,"OK").click();
	}
	
	public static void editKeyType(String key,String newtype){
		editKey(key);
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newtype);
		 DOF.getButton(dialog, "OK").click();
	}
	
	public static boolean ifUserDefined(String key,String newtype){
		editKey(key);
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newtype);
		System.out.println(DOF.getButton(DOF.getDialog("Key"), "&User-defined").invoke("getSelection"));
		Object ifuserdefined = DOF.getButton(DOF.getDialog("Key"), "&User-defined").invoke("getSelection");
		DOF.getButton(dialog, "OK").click();
		if(ifuserdefined.equals(true))
			return true;
		
		return false;
	}
	
	public static StartPoint getKeyAttributesofStartPoint(StartPoint sp){
		clickTab("Keys");
		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] types = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databind = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
//		StartPoint sp = new StartPoint().type(WorkFlow.SP_ACTIVATE);
		for(int i=0;i<names.length;i++){
			sp.key(names[i]+","+types[i]+","+databind[i]);
		}
		return sp;
	}
	
	public static void setObjectQuery(String mbo,String objectquery){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		setEmailMbo(mbo);
		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
		DOF.getPoppedUpList().click(atText(objectquery));
	}
	
	public static boolean ifbuttondisable(String button){
		clickTab("Keys");
		Object ifbuttondisable = DOF.getButton(DOF.getRoot(),button).invoke("getEnabled");
		if(ifbuttondisable.equals(true))
			return true;
		else 
			return false;
		
	}
	
	
	public static boolean ifhasObjectQuery(String str){
		//TBD
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		
		
		clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
//		DOF.getPoppedUpList().click(atText(str));
		System.out.println(ListHelper.hasItem(DOF.getCCombo(DOF.getRoot(), "Object query:"), str));
		Object ifhasoq = ListHelper.hasItem(DOF.getCCombo(DOF.getRoot(), "Object query:"), str);
		if(ifhasoq.equals(true))
			return true;
		return false;
	}
	
	public static WFScreenMenuItem getMenuItem(String screen, String item){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		return getMenuItem(item);
	}

//	public static WFScreenMenuItem getCustomAction(String screen, String action) {
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
//		sleep(0.5);
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
//		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
//		return getCustomAction(action);
//	}
//
//	private static WFScreenMenuItem getCustomAction(String action) {
//		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
//		DOF.getWFCustomActionFigure(DOF.getRoot(), action).click();
//		return getMenuItem();
//	}

	public static WFScreenMenuItem getMenuItem(String name) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		DOF.getWFMenuItemFigure(DOF.getRoot(), name).click();
		return getMenuItem();
	}
	
	private static WFScreenMenuItem getMenuItem(){
		maximize();
		sleep(1);
		clickTab("General");
		sleep(1);
		WFScreenMenuItem item = new WFScreenMenuItem();
		item.defaultItem(WO.getSelection(DOF.getButton(DOF.getRoot(), "&Default")));
		item.name(WO.getText(DOF.getTextField(DOF.getRoot(), "Key:")));
		item.type(WO.getText(DOF.getCCombo(DOF.getRoot(), "Type:")));
		item.screen(WO.getText(DOF.getTextField(DOF.getRoot(), "Screen:")));
		item.mbo(WO.getText(DOF.getTextField(DOF.getRoot(), "Mobile business object:")));
		item.operation(WO.getText(DOF.getCCombo(DOF.getRoot(), "Operation:")));
		item.errorScreen(DOF.getCCombo(DOF.getRoot(), "Error screen:").getProperty("text").toString());
		item.subject(WO.getText(DOF.getTextField(DOF.getRoot(), "Subject:")));
		item.from(WO.getText(DOF.getTextField(DOF.getRoot(), "From:")));
		clickTab("Parameter Mappings");
		sleep(1);
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(table);i++){
			String opName = TableHelper.getCellValue(table, i, 0);
			String keyName = TableHelper.getCellValue(table, i, 3);
			item.parametermapping(opName+","+keyName);
		}
		clickTab("Personalization Key Mappings");
		sleep(1);
		GuiSubitemTestObject pktable = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(pktable);i++){
			String opName = TableHelper.getCellValue(pktable, i, 0);
			String keyName = TableHelper.getCellValue(pktable, i, 1);
			item.pkMapping(opName+","+keyName);
		}
		clickTab("Output Keys");
		sleep(1);
		GuiSubitemTestObject keyTable = DOF.getTable(DOF.getRoot());
		for(int i=0;i<TableHelper.getRowCount(keyTable);i++){
			String keyName = TableHelper.getCellValue(keyTable, i, 0);
			String type = TableHelper.getCellValue(keyTable, i, 1);
			item.outputKey(keyName+","+type);
		}
		restore();
		return item;
	}
	
	public static WFScreenMenuItem getMenuItemPart(String name) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		DOF.getWFMenuItemFigure(DOF.getRoot(), name).click();
		return getMenuItemPart();
	}
	
	private static WFScreenMenuItem getMenuItemPart(){
		WFScreenMenuItem item = new WFScreenMenuItem();
		clickTab("Parameter Mappings");
		sleep(1);
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(table);i++){
			String opName = TableHelper.getCellValue(table, i, 0);
			String keyName = TableHelper.getCellValue(table, i, 3);
			item.parametermapping(opName+","+keyName);
		}
		clickTab("Personalization Key Mappings");
		sleep(1);
		GuiSubitemTestObject pktable = DOF.getTable(DOF.getRoot());
		
		for(int i=0;i<TableHelper.getRowCount(pktable);i++){
			String opName = TableHelper.getCellValue(pktable, i, 0);
			String keyName = TableHelper.getCellValue(pktable, i, 1);
			item.pkMapping(opName+","+keyName);
		}
		clickTab("Output Keys");
		sleep(1);
		GuiSubitemTestObject keyTable = DOF.getTable(DOF.getRoot());
		for(int i=0;i<TableHelper.getRowCount(keyTable);i++){
			String keyName = TableHelper.getCellValue(keyTable, i, 0);
			String type = TableHelper.getCellValue(keyTable, i, 1);
			item.outputKey(keyName+","+type);
		}
		restore();
		return item;
		
		
	}
	
	
//ff>>>928
	public static void editModule(Module module) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		sleep(1);
		clickTab("General");
		sleep(1);
//		maximize();
		if(module.getName()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Module name:"), module.getName());
			DOF.getTextField(DOF.getRoot(), "Module version:").click();
		}
		if(module.getVersion()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Module version:"), module.getVersion());
			DOF.getTextField(DOF.getRoot(), "Module name:").click();
		}
		if(module.getDiscription()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Module description:"), module.getDiscription());
			sleep(2);
			DOF.getTextField(DOF.getRoot(), "Module version:").click();
			sleep(2);
		}
		if(module.getDisplayName()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Module display name:"), module.getDisplayName());
			DOF.getTextField(DOF.getRoot(), "Module version:").click();
		}
		if(module.getCredentialCacheKey()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Credentials cache key:"), module.getCredentialCacheKey());
			DOF.getTextField(DOF.getRoot(), "Module version:").click();
		}
		if(module.getActiveKey()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Requires activation key:"), module.getActiveKey());
			DOF.getTextField(DOF.getRoot(), "Module version:").click();
		}
//ff929>>>>>>>>>>>>>>	
		if(module.getMarkMessage()){
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Mark message as processed after processing")).setState(SELECTED);
		}else {
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Mark message as processed after processing")).setState(NOT_SELECTED);
		
		}
		
		if(module.getDeleteMessage()){
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).setState(SELECTED);
		    System.out.println("state="+((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).getState());
		}else {
			((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).setState(NOT_SELECTED);
		}
//		restore();
	}

	public static String getContent(String item){
		String str = "";
		if(item.equals("cck"))
		 str = DOF.getTextField(DOF.getRoot(), "Credentials cache key:").getProperty("text").toString();
//		System.out.print(str);
		if(item.equals("rak"))
			str = DOF.getTextField(DOF.getRoot(), "Requires activation key:").getProperty("text").toString();
			
		return str;
	}
	
	public static String getStateOfButton(String str){
		String state="";
		if(str.equals("deleteMessage"))
			state = ((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Delete message after processing")).getState().toString();
		if(str.equals("markMessage"))
			state = ((ToggleTestObject)DOF.getButton(DOF.getRoot(), "Mark message as processed after processing")).getState().toString();
		return state;
	}
//ff929<<<<<<<<<<<<<
	
	public static void setScreenProperties(Screen screen) {
		// TODO Auto-generated method stub
		highLight();
		sleep(1);
		clickTab("General");
		sleep(1);
		if(screen.getName()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Name:"), screen.getName());
			DOF.getTextField(DOF.getRoot(), "Key:").click();
		}
		if(screen.getKey()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Key:"), screen.getKey());
			DOF.getTextField(DOF.getRoot(), "Name:").click();
		}
		if(screen.getBackGroundColor()!= null){
			WO.setTextField(DOF.getRoot(), DOF.getTextFieldByAncestorLine(DOF.getRoot(), "LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->LayoutComposite->ScrolledComposite->LayoutComposite->TabbedPropertyComposite->PageBook->PageBook->Composite->Composite->Composite->Composite->Shell"), screen.getBackGroundColor());
			DOF.getTextField(DOF.getRoot(), "Key:").click();
		}
		if(screen.getAction()!= null){
			DOF.getCCombo(DOF.getRoot(), "OK action:").click();
			DOF.getPoppedUpList().click(atText(screen.getAction()));
			DOF.getTextField(DOF.getRoot(), "Key:").click();
		}
		MainMenu.saveAll();
	}
	
	public static String getStatusMessage(){
		System.out.println("message:"+DOF.getCustomCLabel(DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString());
		sleep(1);
		return DOF.getCustomCLabel(DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString();
	}
	
//	public static StartPoint getKeyAttributesofMenuItem(){
//		clickTab("Output Keys");
//		
//		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
//		String[] types = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
//		StartPoint sp = new StartPoint().type(WorkFlow.SP_ACTIVATE);
//		for(int i=0;i<names.length;i++){
//			sp.key(names[i]+","+types[i]);
//		}
//		System.out.print(sp);
//		return sp;
//	}
	public static Object getButtonState(String tab,String buttonname){
		clickTab(tab);
		Object ifbuttondisable = "null";
		if(buttonname.equals("Remove")){
			ifbuttondisable = DOF.getButton(DOF.getRoot(),"&Remove").invoke("getEnabled");
		}
		if(buttonname.equals("Add")){
			ifbuttondisable = DOF.getButton(DOF.getRoot(),"&Add...").invoke("getEnabled");
//			System.out.println("6666"+DOF.getButton(DOF.getRoot(), "&Add...").invoke("getEnabled"));
		}
		if(buttonname.equals("Edit")){
			ifbuttondisable = DOF.getButton(DOF.getRoot(),"&Edit...").invoke("getEnabled");
		}
		if(buttonname.equals("Delete")){
			ifbuttondisable = DOF.getButton(DOF.getRoot(),"&Delete").invoke("getEnabled");
		}
		return ifbuttondisable;
	}
	
	
	//10.10FF>>>>>>>>>>>>>>>>>>
	public static void editKeyAttributesofStartPoint(String from,String to){
		editKey(from);
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), to);
		DOF.getButton(dialog, "OK").click();
	}
	
	public static void newExtractionRulesofStartPoint(String str) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "&New...").click();
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Edit Extraction Rule");
		if(str.split(",")[0]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Field type:"), str.split(",")[0]);
		if(str.split(",")[1]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Tag before parameter:"), str.split(",")[1]);
		if(str.split(",")[2]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Tag after parameter:"), str.split(",")[2]);
		if(str.split(",")[3]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Key:"), str.split(",")[3]);
		DOF.getButton(dialog, "OK").click();
	}
	
	public static StartPoint getExtractionRulesofStartPoint(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("General");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] tagbefore = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Tag Before Parameter");
		String[] tagafter = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Tag After Parameter");
		String[] format = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Format");
		String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		if(type.length!=0){
			for(int i=0;i<type.length;i++){
				sp.key(type[i]+","+tagbefore[i]+","+tagafter[i]+","+format[i]+","+key[i]);
			}
		}
		else
			sp.key("null");
			
		return sp;

	}
	
	public static void removeExtractionRulesofStartPoint(String str){
		if(str!=null){
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Type", str);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Type")));
			DOF.getButton(DOF.getRoot(), "&Remove").click();
		}
	}

	public static void editExtractionRulesofStartPoint(String from,String to) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("General");
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Type", from);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Type")));
			DOF.getButton(DOF.getRoot(), "Ed&it...").click();
			sleep(1);
			
			TopLevelTestObject dialog = DOF.getDialog("Edit Extraction Rule");
			
			if(to.split(",")[0]!=null)
				WO.setCCombo(DOF.getCCombo(dialog, "Field type:"), to.split(",")[0]);
			
			if(to.split(",")[1]!=null)
				WO.setTextField(dialog, DOF.getTextField(dialog, "Tag before parameter:"), to.split(",")[1]);
			
			if(to.split(",")[2]!=null){
				WO.setTextField(dialog, DOF.getTextField(dialog, "Tag after parameter:"), to.split(",")[2]);
				sleep(1);
				DOF.getTextField(dialog, "Tag before parameter:").click();
			}
			
			if(to.split(",")[3]!=null)
				WO.setCCombo(DOF.getCCombo(dialog, "Key:"), to.split(",")[3]);
			
			if(to.split(",").length>4)
				WO.setTextField(dialog, DOF.getTextField(dialog, "Format:"), to.split(",")[3]);
			//<<<<<<<<<<<<<ff10.20
			
			DOF.getButton(dialog, "OK").click();
			MainMenu.saveAll();
	}

	public static void addPkMappingOfStartPoint(String str) {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Personalization Key Mapping");
		WFKeyPkMappingTab.setMapping(str);
//		mapSinglePk(str);
	}
	
	public static void addPkMappingOfMenuItem(String str) {
		clickTab("Personalization Key Mappings");
		if(str.contains("[")){
			WFKeyPkMappingTab.setMapping(str);
//			mapSinglePk(str);
		}
		else{
			DOF.getButton(DOF.getRoot(), "&Add").click();
			TopLevelTestObject dialog = DOF.getDialog("Personalization Key Mapping");
			WO.setCCombo(DOF.getCCombo(dialog, "Personalization key"), str.split(",")[0]);
			sleep(1);
			DOF.getButton(dialog, "Ne&w key...").click();
			sleep(1);
			TopLevelTestObject dialog2= DOF.getDialog("Key");
			WO.setTextField(dialog2, DOF.getTextField(dialog2, "Name:"), str.split(",")[1]);
			WO.setCCombo(DOF.getCCombo(dialog2, "Type:"), str.split(",")[2]);
			DOF.getButton(dialog2, "OK").click();
			sleep(0.5);
			DOF.getButton(dialog, "OK").click();
		}
			
	}
	
	public static void deletePkMappingOfStartPoint(String str) {
		// TODO Auto-generated method stub
		if(str!=null){
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Personalization Key", str);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Personalization Key")));
			DOF.getButton(DOF.getRoot(), "&Delete").click();
		}
	}
	
	public static void editPkMappingOfMenuItem(String from,String to) {
		// TODO Auto-generated method stub
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Personalization Key Mappings");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Personalization Key", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Personalization Key")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Personalization Key Mapping");
		if(to.split(",")[0]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Personalization key"), to.split(",")[0]);
		if(to.split(",")[1]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Key"), to.split(",")[1]);
			DOF.getButton(dialog, "OK").click();
	}
	
	public static StartPoint getPKMappingofMenuItem(){
		clickTab("Personalization Key Mappings");
		String[] pk = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Personalization Key");
		String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		if(pk.length!=0){
			for(int i=0;i<pk.length;i++){
				sp.key(pk[i]+","+key[i]);
			}
		}
		else  
			sp.key("null");
		return sp;
	}
	
	public static void editPkMappingOfStartPoint(String from,String to) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
//		DOF.getWFScreenFigure(DOF.getRoot(), "SS").doubleClick();
		clickTab("Personalization Key Mapping");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Personalization Key", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Personalization Key")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Personalization Key Mapping");
	
		if(to.split(",")[0]!=null){
			if(!from.equals(to.split(",")[0]))
				WO.setCCombo(DOF.getCCombo(dialog, "Personalization key"), to.split(",")[0]);
		}
		if(to.split(",")[1]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Key"), to.split(",")[1]);
		DOF.getButton(dialog, "OK").click();
	}
	
	public static StartPoint getPKMappingofStartPoint(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Personalization Key Mapping");
		String[] pk = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Personalization Key");
		String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		if(pk.length!=0){
			for(int i=0;i<pk.length;i++){
				sp.key(pk[i]+","+key[i]);
			}
		}else
			sp.key("null");
		return sp;
	}
	
	public static void editParameterMapping(WFScreenMenuItem item){
		clickTab("Parameter Mappings");
		GuiSubitemTestObject table = DOF.getTable(DOF.getRoot());
		for(int i = 0;i<item.getParameterMapping().length;i++)
		{
			int row = TableHelper.getRowIndexOfRecordInColumn(table, "Parameter Name", item.getParameterMapping()[i].split(",")[0]);
			table.click(atCell(atRow(row), atColumn("Parameter Name")));
			DOF.getButton(DOF.getRoot(), "&Edit...").click();
			TopLevelTestObject dialog = DOF.getDialog("Parameter Mapping");
			DOF.getCCombo(dialog, "Key:").click();
			String keyName = item.getParameterMapping()[i].split(",")[1];
			sleep(1);
			DOF.getCCombo(dialog, "Key:").click();
			if(!keyName.contains("[")){
				DOF.getPoppedUpList().click(atText(keyName));
				sleep(1);
			}
			else{
				DOF.getButton(dialog, "Ne&w Key... ").click();
				String type = TableHelper.getCellValue(table, i, "Parameter Type");
				WFKey key = (WFKey)ObjectMarshaller.deserialize(keyName, WFKey.class);
				key.type(type);
				WFKeyDialog.key(key);
			}
			DOF.getButton(dialog, "OK").click();
		
		}
		sleep(1);
		MainMenu.saveAll();
		sleep(1);
	}
	
	public static void addPkMappingOfMenuItemInScreen(String screen,String menuItem,String str) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		addPkMappingOfMenuItem(str);
	}
	
	public static StartPoint getParameterMappingofMenuItem(){
		clickTab("Parameter Mappings");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Parameter Name");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Parameter Type");
		String[] maptype = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Mapping Type");
		String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		for(int i=0;i<name.length;i++){
			sp.key(name[i]+","+type[i]+","+maptype[i]+","+key[i]);
		}
		return sp;
	}
	
	public static StartPoint getParameterMappingofStartPoint(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Parameter Mapping");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Parameter Name");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Parameter Type");
		String[] maptype = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Mapping Type");
		String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		if(name.length!=0){
			for(int i=0;i<name.length;i++){
			sp.key(name[i]+","+type[i]+","+maptype[i]+","+key[i]);
			}
		}
		else
			sp.key("null");
		return sp;
	}
	//ff10.12>>>>>>>>>>>>>
	public static String getListOfDefaultSuccessScreen(){
		maximize();
//		clickTab("Start Screen(s)");
//		DOF.getCCombo(DOF.getRoot(), "Default Screen:").click();
//		String[] items = ListHelper.getItems(DOF.getCCombo(DOF.getRoot(), "Default Screen:"));
		DOF.getCCombo(DOF.getRoot(), "Default Success Screen:").click();
		String[] items = ListHelper.getItems(DOF.getCCombo(DOF.getRoot(), "Default Success Screen:"));
		String actual  =arrayToString(items);
		restore();
			return actual ;
	}
	
	public static String arrayToString(String[] array){
		String result = "";
		for(String str:array){
			result = result + str + ",";
		}
		return result.substring(0, result.length()-1);
	}
	
	public static StartPoint getConditionTableOfMenuItem(){
		maximize();
		clickTab("General");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Condition Name");
		String[] screen = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Conditional Success Screen Name");
		StartPoint sp = new StartPoint();
		if(name.length==0)
			sp.key("null");
		else{
			for(int i=0;i<name.length;i++){
				if(name[i]!="")
					sp.key(name[i]+","+screen[i]);
			}
		}
		restore();
		return sp;
	}
	public static StartPoint getConditionSPNewTableOfMenuItem(){
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Condition Name");
		String[] screen = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Conditional Success Screen Name");
		StartPoint sp = new StartPoint();
		if(name.length==0)
			sp.key("null");
		else{
			for(int i=0;i<name.length;i++){
				if(name[i]!="")
					sp.key(name[i]+","+screen[i]);
			}
		}
		return sp;
	}

	public static void addConditionTableOfMenuItem(String name,String screen) {
		maximize();
		clickTab("General");
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"), name);
		DOF.getCCombo(dialog, "Target Screen").click();
		DOF.getPoppedUpList().click(atText(screen));
		DOF.getButton(dialog,"OK").click();
		restore();
		MainMenu.saveAll();
	}
	public static void addConditionSPNewTableOfMenuItem(String name) {
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"), name);
//		DOF.getCCombo(dialog, "Target Screen").click();
		DOF.getButton(dialog,"Ne&w Screen").click();
		sleep(0.5);
		DOF.getButton(dialog,"OK").click();
//		restore();
		MainMenu.saveAll();
	}
	
	public static void addConditionSPTableOfMenuItem(String name,String screen) {
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"), name);
		DOF.getCCombo(dialog, "Target Screen").click();
		DOF.getPoppedUpList().click(atText(screen));
		DOF.getButton(dialog,"OK").click();
		MainMenu.saveAll();
	}
	
	public static void addConditionSPPTableOfMenuItem(String name,String screen) {
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"), name);
		DOF.getCCombo(dialog, "Target Screen").click();
		DOF.getPoppedUpList().click(atText(screen));
	}

	public static void editConditionTableOfMenuItem(String from,String to) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		if(to.split(",")[0]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"),to.split(",")[0]);
		if(to.split(",")[1]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Target Screen"), to.split(",")[1]);
		DOF.getButton(dialog, "OK").click();
		restore();
	}
	
	public static void editConditionTableSPNewOfMenuItem(String from,String to) {
		// TODO Auto-generated method stub
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		if(to.split(",")[0]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"),to.split(",")[0]);
		if(to.split(",")[1]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Target Screen"), to.split(",")[1]);
		DOF.getButton(dialog, "OK").click();
		MainMenu.saveAll();
	}
	public static void editConditionTableSPPOfMenuItem(String from,String to) {
		// TODO Auto-generated method stub
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		if(to.split(",")[0]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"),to.split(",")[0]);
		if(to.split(",")[1]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Target Screen"), to.split(",")[1]);
	}

	public static void deleteConditionTableOfMenuItem(String screen,String menuItem,String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItem).click();
		clickTab("General");
		maximize();
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", string);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		TopLevelTestObject dialog = DOF.getDialog("Delete Confirmation");
		sleep(1);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		restore();
	}
	public static void deleteConditionSPNewTableOfMenuItem(String string) {
		// TODO Auto-generated method stub
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", string);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		TopLevelTestObject dialog = DOF.getDialog("Delete Confirmation");
		sleep(1);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		sleep(1);
		MainMenu.saveAll();
	}

	public static void downConditionTableOfMenuItem(String string) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", string);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Down").click();
		sleep(0.5);
		restore();
	}
	
	public static void upConditionTableOfMenuItem(String string) {
		// TODO Auto-generated method stub
		maximize();
		clickTab("General");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Condition Name", string);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Condition Name")));
		DOF.getButton(DOF.getRoot(), "&Up").click();
		sleep(0.5);
		restore();
	}
	
	
	public static void addConditionTableOfSP(String name,String screen) {
		//maximize();
		clickTab("Start Screen(s)");
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Conditional Navigation Entry");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Condition Name"), name);
		DOF.getCCombo(dialog, "Target Screen").click();
		DOF.getPoppedUpList().click(atText(screen));
		DOF.getButton(dialog,"OK").click();
		//restore();
		MainMenu.saveAll();
	}

	public static void set(IEditable w, Modifications mods) {
		w.openInPropertiesView();
		mods.act();
		MainMenu.saveAll();
	}
	
	//ff10.14>>>>>>>>>>>>>
//DoubleClick a screen in FlowDesign,open the tab "Keys",then get the value:
	public static StartPoint getKeyOfScreen(String screen){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		clickTab("Keys");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databinding = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
		StartPoint sc = new StartPoint();
		if(name.length != 0){
			for(int i=0;i<name.length;i++){
				sc.key(name[i]+","+type[i]+","+databinding[i]+",");
			}
		}
		else
			sc.key("null");
		return sc;
	}
	//ff add 20120521 used to get the key of screen by simple click screen >>>:
	public static StartPoint getKeyOfScreenByClick(String screen){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(screen);
		clickTab("Keys");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databinding = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
		StartPoint sc = new StartPoint();
		if(name.length != 0){
			for(int i=0;i<name.length;i++){
				sc.key(name[i]+","+type[i]+","+databinding[i]+",");
			}
		}
		else
			sc.key("null");
		return sc;
	}
	
	//<<<<<<<<<<<<<
	
	
//Enter into the Flow Designer of a screen,then edit the data in MatchingRules:
	public static void editMatchingRulesOfEmail(String from, String to) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		maximize();
		clickTab("Matching Rules");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Type", from);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Type")));
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		TopLevelTestObject dialog = DOF.getDialog("Matching Rule");
		if(to.split(",")[0]!=null)
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), to.split(",")[0]);
		if(to.split(",")[1]!=null)
			WO.setTextField(dialog, DOF.getTextField(dialog, "Regular expression:"),to.split(",")[1]);
		DOF.getButton(dialog, "OK").click();
//		restore();
	}
	
	public static StartPoint getMatchingRulesOfEmail(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		maximize();
		clickTab("Matching Rules");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] expression = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Matching Rule (regular expression)");
		StartPoint sc = new StartPoint();
		if(type.length != 0){
			for(int i=0;i<type.length;i++){
				sc.key(type[i]+","+expression[i]);
			}
		}
		else
			sc.key("null");
		restore();
		return sc;
	}

	public static void deleteMatchingRulesOfEmail(String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		maximize();
		clickTab("Matching Rules");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Type", string);
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Type")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		MainMenu.saveAll();
		restore();
	}

	public static void addMatchingRulesOfEmail(String string) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		maximize();
		clickTab("Matching Rules");
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Matching Rule");
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), string.split(",")[0]);
		WO.setTextField(dialog, DOF.getTextField(dialog, "Regular expression:"),string.split(",")[1]);
		DOF.getButton(dialog, "OK").click();
		sleep(1);
		MainMenu.saveAll();
//		//enter ctrl+s from the Keyboard.
//		DOF.getRoot().inputKeys("^s");
//		restore();
	}
	
	//ff10.17>>>>>>>>>>>>>>>>>>>>>
	//Get the all Type items in list of the dialog "Matching rules"
	public static String getAllTypeInMatchingRule(boolean ifcontinuematch){
		clickTab("Matching Rules");
		DOF.getButton(DOF.getRoot(),"&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Matching Rule");
		DOF.getCCombo(dialog, "Type:").click();
		return VerifyKeyListCom(dialog,ifcontinuematch);
	}
	
	//Only get item ,click cancle button,or click OK button
	public static String VerifyKeyListCom(TopLevelTestObject dialog, boolean ifcontinuematch){
		String[] data = ListHelper.getItems(DOF.getPoppedUpList());
		String actual = arrayToString(data);
		if(ifcontinuematch)
			DOF.getButton(dialog,"OK").click();
		else
			DOF.getButton(dialog,"Cancel").click();
		return actual;
	}
	//get item in list
	public static String VerifyKeyListCom(){
		String[] data = ListHelper.getItems(DOF.getPoppedUpList());
		String actual = arrayToString(data);
		return actual;
	}
	//Server_inital Properties -search mbo
	public static void setEmailMbo(String mboname,String project){
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "&Search...").click();
		TopLevelTestObject dialog = DOF.getDialog("Search For Mobile Business Object");
		if(mboname != null){
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"),mboname);
		}
		
		if(project!= null){
			WO.setCombo(DOF.getCombo(dialog, "Project:"), project);
		}
		DOF.getButton(dialog, "&Search").click();
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog), "Name", mboname);
		sleep(1);
		DOF.getTable(dialog).click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(dialog, "OK").click();
	}
	
	public static String getEmailMbo(){
		clickTab("General");
		String mbo= DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text").toString();
		return mbo;
	}
	
	//Only to set Object Query ,not to set mbo:
	public static void setObjectQueryOnly(String objectquery){
		clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
		DOF.getPoppedUpList().click(atText(objectquery));
		MainMenu.saveAll();
	}
	
	//get the value of object Query
	public static String getObjectQueryOfEmailMbo(){
		clickTab("General");
		String objectQuery = DOF.getCCombo(DOF.getRoot(),"Object query:").getProperty("text").toString();
		return objectQuery;
	}
	
	public static String getKeyWhenEditExtractionRulesofStartPoint(String from) {
		// TODO Auto-generated method stub
			int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Type", from);
			DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Type")));
			DOF.getButton(DOF.getRoot(), "Ed&it...").click();
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Edit Extraction Rule");
			String key = DOF.getCCombo(dialog, "Key:").getProperty("text").toString();
			DOF.getButton(dialog, "OK").click();
			return key;
	}
	
//use to create new key to ServerInitial
	public static void setKeyOfServerInitial(String newkey) {
		// TODO Auto-generated method stub
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Keys");
		setNewKeyBindMBO(newkey);
	}
	
	//set key to bind mbo Attribute ,return the value of text "type:" to verify ,then click cancel button
	public static String getTypeOfNewKeyBindMBOAttribute(String newKey) {
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		clickTab("Keys");
		String type = null;
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Key");
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
			
			if(newKey.split(",")[2]!=null){
				((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
				WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[2]);//parent mbo
			}
			if(newKey.split(",")[3]!=null){
				DOF.getButton(dialog, "&Mobile business object attribute").click();
				WO.setCCombo(DOF.getCCombo(dialog, "Name:"), newKey.split(",")[3]);
				type = DOF.getCCombo(dialog, "Type:").getProperty("text").toString();
			}
			DOF.getButton(dialog, "Cancel").click();
		}
		return type;
	}
// ff10.25>>>>>>>>>>>>>>>>>>>>>>>
	public static String getDefaultValueOfLabel() {
		// TODO Auto-generated method stub
		return DOF.getTextField(DOF.getRoot(), "Default value:").getProperty("text").toString();
	}
	//ff10.25<<<<<<<<<<<<<<<<
	
	//add by yanxu
	public  static String verifykeylist(){
		DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).click();
		return verifykeylistcom();
	}
	

	public  static String verifykeylistcom(){
		//DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).click();
		sleep(1);
		String[] data = ListHelper.getItems(DOF.getPoppedUpList());
		String actual  = arrayToString(data);
		return actual;
	}
	//end
	
	//ff10.28>>>>>>>>>>>>>>>>>>
	public static void setAuthentication(String username,String pw) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		sleep(1);
		clickTab("General");
		
		maximize();
		clickTab("Authentication");
		((ToggleGUITestObject) DOF.getButton(DOF.getRoot(),"Use static credentials")).clickToState(SELECTED);
		DOF.getButton(DOF.getRoot(),"Use hard-coded credentials").click();
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Username:"), username);
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Password:"), pw);
		restore();
	}
	//ff10.28<<<<<<<<<<<<<<<<<<<<<<<

	//ff10.31>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static void resetWidget(String screen, IEditable obj) {
		// TODO Auto-generated method stub
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		
		//ff modify 20120315 used 213drop3:
//		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
	//	DOF.getWFScreenFigureToClick(screen).doubleClick();
		//ff<<<<<
		
		//Click the control ---1. -choice
		if(obj instanceof WFChoice){
			TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
			System.out.println("choice="+boxes.length);
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(0.5);
				String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
				if(label.equals(((WFChoice)obj).getLabel())){
					((GefEditPartTestObject)box).click();
				}
			}
		}
		//need to add more control ....
		
		//ff1219>>>>>>>>>>>>>>>>>>>>>>>>>
		//Click the control ---2. -editbox
		if(obj instanceof WFEditBox){
			TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
			System.out.println("editbox="+boxes.length);
			for(TestObject box:boxes){
				((GefEditPartTestObject)box).click();
				sleep(1);
				String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
				System.out.println("Label="+label);
				sleep(0.5);
				if(label.equals(((WFEditBox)obj).getLabel())){
					((GefEditPartTestObject)box).click();
					break;
				}
			}
		}
		
		
		set(obj);
	}
	
	//used to set the key ,which has existed,to set new databinding in design:
	public static void editKeyDataBinding(String design, String key,String databinding) {
		if(design.equals("Flow Design")){
			DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
			DOF.getWFFlowDesignCanvas().click(atPoint(33, 17));
			DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
			}
		if(design.equals("Screen Design")){
			DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
			DOF.getWFScreenDesignCanvas().click(atPoint(500, 17));
		    DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
			}
		editKey(key);
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), databinding.split(",")[0]);
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), databinding.split(",")[1]);
		if(databinding.split(",")[2]!=null){
			((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
			WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), databinding.split(",")[2]);//parent mbo
		}
		if(databinding.split(",")[3]!=null){
			DOF.getButton(dialog, "&Mobile business object attribute").click();
			WO.setCCombo(DOF.getCCombo(dialog, "Name:"), databinding.split(",")[3]);
		}
		DOF.getButton(dialog, "OK").click();
	}
	//ff10.31<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	//ff11.11>>>>>>>>>>>>>>>>>>>>>>>>
	//After click the valid point on flow Design ,get the value of the table of Keys
	public static StartPoint getKeyAttributesofFlowDesign(){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));	//click the valid Point on Flow Design
		maximize();
		clickTab("Keys");
		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] types = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databind = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
		StartPoint sp = new StartPoint();
//		StartPoint sp = new StartPoint().type(WorkFlow.SP_ACTIVATE);
		for(int i=0;i<names.length;i++){
			sp.key(names[i]+","+types[i]+","+databind[i]+",");
		}
		restore();
		return sp;
	}
	
	//After click the MenuItem of screen on flow Design ,get the value of the table of Parameter Mapping:
	public static StartPoint getPatameterMappingofMenuItem(String screen,String menuItemName){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		//click the valid Point on Flow Design
		DOF.getWFMenuItemFigure(DOF.getRoot(), menuItemName).click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		maximize();
		clickTab("Parameter Mappings");
		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Parameter Name");
		String[] keys = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key");
		StartPoint sp = new StartPoint();
		for(int i=0;i<names.length;i++){
			sp.key(names[i]+","+keys[i]+",");
		}
		restore();
		return sp;
	}
	//ff11.11<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	//ff11.15>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static StartPoint getKeyAttributeofStartPoint() {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		StartPoint sp = new StartPoint().type(WorkFlow.SP_SERVER_INIT);
		getKeyAttributesofStartPoint(sp);
		return sp;
	}
	
	//ff11.28>>>>>>>>>>>>>
	//used to get the cell value of listView...
	public static StartPoint getCellOfListView(String cellName){
//		maximize();
		clickTab("Cell");
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int J=0;J<cellname.length;J++){
			if(cellname[J].equals(cellName))
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),J , 0);
		}
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Fields for "+cellName)), "Key");
		StartPoint sp = new StartPoint();
		if(name.length==0)
			sp.key("null");
		else{
			for(int i=0;i<name.length;i++){
				if(name[i]!="")
					sp.key(name[i]+",");
			}
		}
//		restore();
		return sp;
	}
	
	//ff1.09>>>>>>>>>>>>>
	//After click the valid point on screen Design ,get the value of the table of Keys
	public static StartPoint getKeyAttributesofScreenDesign(){
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));	//click the valid Point on Flow Design
		maximize();
		clickTab("Keys");
		String[] names = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] types = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databind = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
		StartPoint sp = new StartPoint();
//		StartPoint sp = new StartPoint().type(WorkFlow.SP_ACTIVATE);
		for(int i=0;i<names.length;i++){
			sp.key(names[i]+","+types[i]+","+databind[i]+",");
		}
		restore();
		return sp;
	}
	
	//yanxu1122
	public static void setNewKeyBindMBORelationshipInScrDesign(String newKey) {
		DOF.getWFScreenDesignCanvas().click();
		setRelationshipKey(newKey);
		
	}

	public static void setRelationshipKey(String newKey) {
		clickTab("Keys");
		if(newKey!=null){
			if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
				DOF.getButton(DOF.getRoot(), "&New...").click();
			}else{
				DOF.getButton(DOF.getRoot(), "&New key...").click();
			}
			
			sleep(1);
			TopLevelTestObject dialog = DOF.getDialog("Key");
			WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), newKey.split(",")[0]);
			WO.setCCombo(DOF.getCCombo(dialog, "Type:"), newKey.split(",")[1]);
			
			if(newKey.split(",")[2]!=null){
				((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
				WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), newKey.split(",")[2]);//parent mbo
			}
			if(newKey.split(",")[3]!=null){
				DOF.getButton(dialog, "Mobile business object &relationship").click();
				WO.setCCombo(DOF.getCCombo(DOF.getGroup(dialog, "Input Data Binding"),2), newKey.split(",")[3]);

			}
			if(newKey.split(",")[4]!=null){
				int i = 4;
				DOF.getButton(dialog, "&Edit...").click();
				TopLevelTestObject dialogKey = DOF.getDialog("Key");
				for(i=4;i<newKey.split(",").length;i++){
				DOF.getButton(dialogKey, "&New...").click();
				TopLevelTestObject dialogKeykey = DOF.getDialog("Key");
				((ToggleGUITestObject)DOF.getButton(dialogKeykey, "&Sent by server")).clickToState(SELECTED);
				sleep(1);
				DOF.getButton(dialogKeykey, "&Mobile business object attribute").click();
				sleep(1);
				WO.setCCombo(DOF.getCCombo(dialogKeykey, "Name:"), newKey.split(",")[4]);//son attribute
				DOF.getButton(dialogKeykey, "OK").click();
				}
				sleep(1);
				DOF.getButton(dialogKey, "OK").click();
			}
			DOF.getButton(dialog, "OK").click();
		}
		
	}
	//end
	
	
	//****yanxu1118
	public static void setKey1(WFKey key){
		if(key!=null){
			if(key.getType()!=null){
				if(DOF.getButton(DOF.getRoot(), "&New...")!=null){
					DOF.getButton(DOF.getRoot(), "&New...").click();
				}else{
					DOF.getButton(DOF.getRoot(), "&New key...").click();
				}
				sleep(1);
				WFKeyDialog.key(key);
			}else{
				//select existing key
			}
		}
	}
	
	
	
	public static void setMenuItemNotMaxNotRestore(WFScreenMenuItem item) {
		clickTab("General");
		setMenuItemDetail(item);
	}
	
	
	private static void setMenuItemDetail(WFScreenMenuItem item) {
	if(item.getName()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Name:"), item.getName());
	}
	if(item.getKey()!=null){
		DOF.getTextField(DOF.getRoot(), "Key:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars(item.getKey());
	}
	if(item.getDefaultItem()!=null){
		clickTab("General");
		if(item.getDefaultItem().equals("yes")||item.getDefaultItem().equals("true")){
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "&Default")).clickToState(NOT_SELECTED);
			
		}
	}
	if(item.getType()!=null){
		clickTab("General");
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Type:"), item.getType());
	}
	if(item.getScreen()!=null){
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Screen:"), item.getScreen());
	}
//	if(item.getMbo()!=null){
//		clickTab("General");
//		DOF.getButton(DOF.getRoot(), "C&lear").click();
//	}
	if(item.getMbo()!=null){
		clickTab("General");
		String project = item.getProject();
		String mbo = item.getMbo();
		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getButton(DOF.getRoot(), "&Search...").click();
		SearchForMboDailog.selectMbo(DOF.getDialog("Search For Mobile Business Object"), project, mbo);
	}
	/////////////////////////ff>>>>>>>>>>>>>>>>>>>>>>>>
	if(item.getOperation()!=null){
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "Invoke &operation").click();
		DOF.getCCombo(DOF.getRoot(), "Operation:").click();
		DOF.getPoppedUpList().click(atText(item.getOperation()));
	}
	if(item.getObjectQuery()!=null){
		clickTab("General");
//		DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getButton(DOF.getRoot(), "Invoke object &query").click();
		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
		DOF.getPoppedUpList().click(atText(item.getObjectQuery()));
	}
	//>>>> (flv 10/21 start) <<<<
	if (!item.isGenerateOldVal()) {
		((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Generate old &value keys")).clickToState(NOT_SELECTED);
	}
	//
	if (!item.isShowCredScreen()) {
		((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Show credential screen on authentication failure")).clickToState(NOT_SELECTED);
	}
	//>>>> (flv 10/21 end) <<<<
	
	if(item.getOpenScreen()!=null){
		clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Screen:").click();
		DOF.getPoppedUpList().click(atText(item.getOpenScreen()));
	}
	
	if(item.getDefaultSuccessScreen()!=null){
		clickTab("General");
		WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Default Success Screen:"), item.getDefaultSuccessScreen());
	}
	//>>>>>>>>>>>>>> flv 09-28  Start<<<<<<<<<<<
	//Submit Error Msg
	if(item.getSubmitErrMsg()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit error message:"), item.getSubmitErrMsg());
	}
	//Time Out
	if(item.getTimeOut()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Timeout:"), item.getTimeOut());
	}
	//on device cache time out
	if(item.getOnDevCacheTimeOut()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "On-device cache timeout:"), item.getOnDevCacheTimeOut());
	}
	// error screen
	if(item.getErrorScreen()!=null){
		clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
		DOF.getPoppedUpList().click(atText(item.getErrorScreen()));
	}
	//submit confrim msg
	if(item.getSubmitConfirmMsg()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Submit confirmation message:"), item.getSubmitConfirmMsg());
	}
	if(item.getResubmitConfirmMsg()!=null){
		clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Resubmit confirmation message:"), item.getResubmitConfirmMsg());
	}
	//>>>>>>>>>>>>>> flv 09-28  End<<<<<<<<<<<
	
	if(item.getParameterMapping()!=null){
		clickTab("Parameter Mappings");
		WFKeyParameterMappingTab.setMapping(item.getParameterMapping());
	}	
	if(item.getPkMapping()!=null){
		clickTab("Personalization Key Mappings");
		WFKeyPkMappingTab.setMapping(item.getPkMapping());
	}
	
//ff10.14>>>>>>>>>>>>>>
	if(item.getInvokeParentUpdate()){
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "Invoke &parent update").click();
	}
	
	if(item.getGenerateErrorScreen()){
		clickTab("General");
		DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
	}
	
//<<<<<<<<<<<<<<ff
	//********************end yanxu1123
	
	}
	
	//add by yanxu
	public  static String verifykeylistingroup(){
		DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "HREF settings")).click();
		return verifykeylistcom();
	}
	
	//ff0208>>>>>>>>>>>>>>>>>>
	//add cells for listview:
	public static void addCell(String order,String cellName,String cellKey,String length){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.clickTab("Cell");
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
		
		PropertiesView.maximize();
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName)){
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
				DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line "+order), "&Add").click();
				TopLevelTestObject dialog = DOF.getDialog("Listview Field");
		
				DOF.getCCombo(dialog, "Key:").click();
				DOF.getCCombo(dialog, "Key:").setProperty("text", cellKey);
		
				WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), length);
				DOF.getButton(dialog,"OK").click();
			}
		}
		sleep(1);
		MainMenu.saveAll();
		PropertiesView.restore();
	}

	public static List<WFKey> getAllkeysInScreen(String screen) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		
		clickTab("Keys");
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Key Name");
		String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Type");
		String[] databinding = TableHelper.getColumnData(DOF.getTable(DOF.getRoot()), "Data Binding");
		List<WFKey> keys = new ArrayList<WFKey>();
		if(name != null){
			for(int i=0;i<name.length;i++){
				WFKey key = new WFKey().name(name[i]).type(type[i]);
				keys.add(key);
			}
		}
		return keys;
	}

	

}

