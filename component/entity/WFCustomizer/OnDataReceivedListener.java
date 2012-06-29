package component.entity.WFCustomizer;

import java.io.IOException;
import java.util.Properties;

public interface OnDataReceivedListener {
	
	public void onDataReceived(Properties data);

	public void setSever(NanoHTTPD nanoHTTPD);
	
	public void onTerminate();

}
