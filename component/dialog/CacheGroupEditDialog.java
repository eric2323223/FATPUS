package component.dialog;

import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.CachePolicy;
import component.entity.model.Interval;

public class CacheGroupEditDialog extends RationalTestScript{

	public static void setType(TopLevelTestObject dialog, String str) {
		if (str.equalsIgnoreCase(CachePolicy.ONDEMAND)) {
			onDemandButton(dialog).click();
		} else if (str.equalsIgnoreCase(CachePolicy.SCHEDULED)) {
			scheduledButton(dialog).click();
		} else if (str.equalsIgnoreCase(CachePolicy.DCN)) {
			dcnButton(dialog).click();
		} else if (str.equalsIgnoreCase(CachePolicy.ONLINE)) {
			onlineButton(dialog).click();
		}
	}
	
	private static ToggleGUITestObject scheduledButton(TopLevelTestObject dialog){
		if(DOF.getRadioButton(dialog, "&Scheduled    ")!=null){
			return DOF.getRadioButton(dialog, "&Scheduled    ");
		}else{
			return DOF.getRadioButton(dialog, "&Scheduled");
		}
	}
	
	private static ToggleGUITestObject onDemandButton(TopLevelTestObject dialog){
		if(DOF.getRadioButton(dialog, "On de&mand   ")!=null){
			return DOF.getRadioButton(dialog, "On de&mand   ");
		}else{
			return DOF.getRadioButton(dialog, "On de&mand");
		}
	}
	
	private static ToggleGUITestObject dcnButton(TopLevelTestObject dialog){
		if(DOF.getRadioButton(dialog, "&DCN       ")!=null){
			return DOF.getRadioButton(dialog, "&DCN       ");
		}else{
			return DOF.getRadioButton(dialog, "D&CN      ");
		}
	}
	
	private static ToggleGUITestObject onlineButton(TopLevelTestObject dialog){
		if(DOF.getRadioButton(dialog, "&Online      ")!=null){
			return DOF.getRadioButton(dialog, "&Online      ");
		}else{
			return DOF.getRadioButton(dialog, "&Online   ");
		}
	}

	public static void setHour(TopLevelTestObject dialog, String hour) {
		if(hourSpinner(dialog).getProperty("enabled").toString().equals("true")){
			hourSpinner(dialog).setText("");
			hourSpinner(dialog).setText(hour);
		}
	}
	
	private static TextGuiSubitemTestObject hourSpinner(TopLevelTestObject dialog){
		if(DOF.getSpinner(dialog, "Specify the number of hours for duration")!=null){
			return DOF.getSpinner(dialog, "Specify the number of hours for duration");
		}else{
			return DOF.getSpinner(dialog, "Specify the number of hours for the duration");
		}
	}
	
	private static TextGuiSubitemTestObject minuteSpinner(TopLevelTestObject dialog){
		if(DOF.getSpinner(dialog, "Specify the number of minutes for duration")!=null){
			return DOF.getSpinner(dialog, "Specify the number of minutes for duration");
		}else{
			return DOF.getSpinner(dialog, "Specify the number of minutes for the duration");
		}
	}
	
	private static TextGuiSubitemTestObject secondSpinner(TopLevelTestObject dialog){
		if(DOF.getSpinner(dialog, "Specify the number of seconds for duration")!=null){
			return DOF.getSpinner(dialog, "Specify the number of seconds for duration");
		}else{
			return DOF.getSpinner(dialog, "Specify the number of seconds for the duration");
		}
	}

	public static void setMinute(TopLevelTestObject dialog, String minute) {
		if(minuteSpinner(dialog).getProperty("enabled").toString().equals("true")){
			minuteSpinner(dialog).setText("");
			minuteSpinner(dialog).setText(minute);
		}
	}

	public static void setSecond(TopLevelTestObject dialog, String second) {
		if(secondSpinner(dialog).getProperty("enabled").toString().equals("true")){
			secondSpinner(dialog).setText("");
			secondSpinner(dialog).setText(second);
		}
	}

	public static void setInterval(TopLevelTestObject dialog, Interval interval) {
		setHour(dialog, interval.getHour());
		setMinute(dialog, interval.getMinute());
		setSecond(dialog, interval.getSecond());
	}

	public static void setPolicy(TopLevelTestObject dialog, CachePolicy policy) {
		setType(dialog, policy.getType());
		setInterval(dialog, policy.getInterval());
		setPartition(dialog, policy.getPartition());
	}

	private static void setPartition(TopLevelTestObject dialog,	boolean partition) {
		if(partition){
			((ToggleGUITestObject)DOF.getButton(dialog, "&Partition by requestor and device identity")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog, "&Partition by requestor and device identity")).clickToState(NOT_SELECTED);
		}
	}

	public static boolean getIntervalStatus(TopLevelTestObject dialog) {
		String enabled = minuteSpinner(dialog).getProperty("enabled").toString();
		if(enabled.equals("true")){
			return true;
		}else{
			return false;
		}
	}

	public static boolean getPartitionStatus(TopLevelTestObject dialog) {
		String enabled = DOF.getButton(dialog, "&Partition by requester and device identity").getProperty("enabled").toString();
		if(enabled.equals("true")){
			return true;
		}else{
			return false;
		}
	}


}
