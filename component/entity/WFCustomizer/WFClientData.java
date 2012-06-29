package component.entity.WFCustomizer;

import java.util.Hashtable;
import java.util.Properties;

public class WFClientData{
	private Properties data = new Properties();
	
	public Properties getData(){
		return this.data;
	}
	
	public WFClientData(Properties p){
		this.data = p;
	}
	
	public WFClientData(){}
	
	public WFClientData property(String str){
		System.out.println(str);
		// >>>>>>>>>> flv 11-07 start <<<<<<<<<<<<<<<
		int idx = str.indexOf("=");
		String p = str.substring(0, idx);
		String v = str.substring(idx+1, str.length());
//		String p = str.split("=")[0];
//		String v = str.split("=")[1];
		// >>>>>>>>> flv 11-07 end  <<<<<<<<<<<<
		data.put(p, v);
		return this;
	}

	public boolean isEndSignal() {
		if(data.getProperty("EOM")!=null && data.getProperty("EOM").equals("true")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isRestartSignal() {
		if(data.getProperty("RESTART")!=null && data.getProperty("RESTART").equals("true")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isErrorSignal() {
		if(data.getProperty("ERROR")!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public String getErrorMessage(){
		String msg = data.getProperty("ERROR");
		return msg.replace("%20", " ").replace("%3A", ":").replace("%0D%0A", "\n").replace("%2C", ",");
	}
	
	public String toString(){
		return data.toString();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof WFClientData){
			return this.data.equals(((WFClientData)obj).getData());
		}
		return false;
	}

}
