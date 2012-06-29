package component.entity.WFCustomizer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.rational.test.ft.script.RationalTestScript;

public class WFClientResult extends RationalTestScript implements OnDataReceivedListener{
	private List<WFClientData> result = new LinkedList<WFClientData>();
	private NanoHTTPD server;
	private WFClientResult expected;
	
	public WFClientResult(List<WFClientData> data){
		this.result = data;
	}
	
	public WFClientResult() {
	}
	
	public void add(WFClientData d){
		result.add(d);
	}

	public WFClientData getElement(int index){
		return result.get(index);
	}
	
	public void println(){
		for(WFClientData data:result){
			System.out.println(data.getData());
		}
	}
	
	public void verify(WFClientResult wfr) {
		if (result.size() != wfr.size()) {
			logError("Actual record count: " + result.size()
					+ "  expected record count: " + wfr.size());
		}else{
			for (int i = 0; i < result.size(); i++) {
				if(result.get(i).isErrorSignal()){
					logError("Error!\n"+result.get(i).getErrorMessage());
				}
				if (!result.get(i).equals(wfr.getElement(i))) {
					logError("Actual:[" + this.getElement(i).toString()
							+ "] Expected[" + wfr.getElement(i).toString() + "]");
				}
			}
		}
	}
	
	public void verify() {
		verify(expected);
	}
	
	private int size() {
		return result.size();
	}

	public static void main(String[] args){
	}

	public void print() {
		for(WFClientData data:result){
			System.out.println(data);
		}
	}

	public WFClientResult data(String str) {
		String[] records = str.split("\\|");
		for(String record:records){
			WFClientData data = new WFClientData();
			if(record.contains(",")){
				//ff11.1>>>>>>>need to consider the data show type of signature 
//				String[] pairs = record.split(",");
				String[] pairs = record.split(",",2);
				//ff11.1<<<<<<<<<<<<<<<
				for(String pair:pairs){
					data.property(pair);
				}
			}else{
				data.property(record);
			}
    		result.add(data);       
		}
		return this;
	}
	
	public WFClientResult data(Map<String,String> str) {
		Set<String> set = str.keySet();
		for (Object key:set.toArray()){
			WFClientData data = new WFClientData();
			if ("" == str.get(key) || null == str.get(key)) {
				data.property(String.valueOf(key));
			} else{
				data.property(String.valueOf(key));
				data.property(String.valueOf(str.get(key)));
			}
    		result.add(data);       
		}
		return this;
	}

	@Override
	public void onDataReceived(Properties data) {
		WFClientData record = new WFClientData(data);
		if(record.isEndSignal()){
			server.stop();
		}else if(record.isRestartSignal()){
//			Robot.run(script);
			//No such requirement
		}else if(record.isErrorSignal()){
			result.add(record);
			server.stop();
		}else{
			result.add(record);
		}
	}
	
	public void onTerminate(){
//		System.out.println("acutal data:");
//		this.print();
//		System.out.println("expected data:");
//		expected.print();
//		if(this!=null){
//			this.verify(expected);
//		}else{
//			throw new RuntimeException("Oops, there is no client test result yet.");
//		}
	}
		
	public WFClientResult waitForComplete() {
//		server.waitToStop();
//		return this;
		
		try{
			while(server.isRunning()){
				Thread.sleep(500);
			}
			return this;
		}catch(InterruptedException e){
			throw new RuntimeException("oops, error when wait for WFClientResult");
		}
	}

	@Override
	public void setSever(NanoHTTPD nanoHTTPD) {
		this.server = nanoHTTPD;
	}

	public void setExpected(WFClientResult expected) {
		this.expected = expected;
		
	}
		
}
