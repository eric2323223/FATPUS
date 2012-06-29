package component.dialog;


import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;

/**
 * Description : Functional Test Script
 * 
 * @author Yong Li
 */
public class CacheGroupPropertiesDialog extends RationalTestScript
		 {
	/**
	 * Script Name : <b>CacheGroupPropertiesDialog</b> Generated : <b>Mar 23,
	 * 2010 8:01:21 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/03/23
	 * @author Yong Li, Eric
	 */
	private String caption;
	
	public CacheGroupPropertiesDialog(String dialog){
		this.caption = dialog;
	}
	
	private TopLevelTestObject dialog(){
		return DOF.getDialog(this.caption);
	}
	
	private void common(){
		DOF.getTree(dialog()).click(atPath("Common"));
	}
	
	private void policies(){
		DOF.getTree(dialog()).click(atPath("Policies"));
	}
	
	public void close(){
		DOF.getButton(dialog(), "OK").click();
	}
	
	public void testMain(Object[] args) {
		setCachePolicy("Scheduled");
	}

	public void setName(String name) {
		name().setText("");
		name().setText(name);
	}
	
	public TextScrollTestObject name(){
		return (TextScrollTestObject)DOF.getTextField(dialog(), "Name : ");
	}

	public String getName() {
		common();
		return DOF.getTextField(dialog(), "Name : ").getProperty("text").toString();
//		return name().getText();
	}

	public void setCachePolicy(String toogle) {
		DOF.getTree(dialog()).click(atPath("Policies"));
		if (toogle.equalsIgnoreCase("On demand")) {
			DOF.getRadioButton(this.dialog(), "On de&mand   ").click();
		} else if (toogle.equalsIgnoreCase("Scheduled")) {
			DOF.getRadioButton(this.dialog(), "&Scheduled    ").click();
		} else if (toogle.equalsIgnoreCase("DCN")) {
			DOF.getRadioButton(dialog(), "&DCN       ").click();
		} else if (toogle.equalsIgnoreCase("Online")) {
			DOF.getRadioButton(dialog(), "&Online      ").click();
		} 
	}

	public String getCachePolicy() {
		String toogle = null;
		if (DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand   ")
				.getState().isSelected()
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&Scheduled    ")
						.getState().isSelected())
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&DCN       ")
						.getState().isSelected())
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&Online      ")
						.getState().isSelected())) {
			toogle = "On demand";
		} else if (!(DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand   ")
				.getState().isSelected())
				&& DOF.getRadioButton(DOF.getActiveDialog(), "&Scheduled    ")
						.getState().isSelected()
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&DCN       ")
						.getState().isSelected())
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&Online      ")
						.getState().isSelected())) {
			toogle = "Scheduled";
		} else if (!(DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand   ")
				.getState().isSelected())
				&& !(DOF.getRadioButton(DOF.getActiveDialog(), "&Scheduled    ")
						.getState().isSelected())
				&& (DOF
						.getRadioButton(DOF.getActiveDialog(), "&DCN       ")
						.getState().isSelected())
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&Online      ")
						.getState().isSelected())) {
			toogle = "DCN";
		} else if (!(DOF.getRadioButton(DOF.getActiveDialog(), "On de&mand   ")
				.getState().isSelected())
				&& !(DOF.getRadioButton(DOF.getActiveDialog(), "&Scheduled    ")
						.getState().isSelected())
				&& (!DOF
						.getRadioButton(DOF.getActiveDialog(), "&DCN       ")
						.getState().isSelected())
				&& (DOF
						.getRadioButton(DOF.getActiveDialog(), "&Online      ")
						.getState().isSelected())) {
			toogle = "Online";
		}

		return toogle;
	}
	
	public TextGuiSubitemTestObject hours(){
		return DOF.getSpinner(dialog(), "Specify the number of hours for the duration");
	}
	
	public TextGuiSubitemTestObject minutes(){
		return DOF.getSpinner(dialog(), "Specify the number of minutes for the duration");
	}
	
	public TextGuiSubitemTestObject seconds(){
		return DOF.getSpinner(dialog(), "Specify the number of seconds for the duration");
	}

	public void setHours(String hours) {
		hours().setText("");
		hours().setText(hours);
	}

	public void setMinutes(String minutes) {
		minutes().setText("");
		minutes().setText(minutes);
	}

	public void setSeconds(String seconds) {
		seconds().setText("");
		seconds().setText(seconds);
	}

	public String getHours() {
		policies();

//		return hours().getProperty("selection").toString();
		return DOF.getSpinner(dialog(), "Specify the number of hours for the duration").getProperty("selection").toString();
	}
	

	public String getMinutes() {
		policies();
		return DOF.getSpinner(dialog(), "Specify the number of minutes for the duration").getProperty("selection").toString();
//		return minutes().getProperty("selection").toString();
	}

	public String getSeconds() {
		policies();
		return DOF.getSpinner(dialog(), "Specify the number of seconds for the duration").getProperty("selection").toString();
//		return seconds().getProperty("selection").toString();
	}

	public void OK() {
		ok().click();
	}
	
	public GuiTestObject ok(){
		return DOF.getButton(dialog(), "OK");
	}

	public void Cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	public void setCacheGroup(CacheGroup cg) {
		if(cg.getName()!=null){
			setName(cg.getName());
		}
		if(cg.getType()!=null){
			setCachePolicy(cg.getType());
			sleep(1);
		}
		if(cg.getHour()!=null){
			setHours(cg.getHour());
		}
		if(cg.getMinute()!=null){
			setMinutes(cg.getMinute());
		}
		if(cg.getSecond()!=null){
			setSeconds(cg.getSecond());
		}
	}

	public String getPartition() {
		String status = DOF.getButton(dialog(), "&Partition by requester and device identity").getProperty("selection").toString();
		if(status.equals("true")){
			return "Yes";
		}else{
			return "No";
		}
	}

	public String getType() {
		if(DOF.getButton(dialog(), "On de&mand   ").getProperty("selection").toString().equals("true")){
			return CachePolicy.ONDEMAND;
		}
		if(DOF.getButton(dialog(), "&Scheduled    ").getProperty("selection").toString().equals("true")){
			return CachePolicy.SCHEDULED;
		}
		if(DOF.getButton(dialog(), "&DCN       ").getProperty("selection").toString().equals("true")){
			return CachePolicy.DCN;
		}
		if(DOF.getButton(dialog(), "&Online      ").getProperty("selection").toString().equals("true")){
			return CachePolicy.ONLINE;
		}
		return null;
	}
}
