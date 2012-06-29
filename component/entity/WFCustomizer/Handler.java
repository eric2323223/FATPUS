package component.entity.WFCustomizer;

import java.net.URI;
import java.util.Properties;

import component.entity.WFCustomizer.NanoHTTPD.Response;

public interface Handler {
	
	public Response serve(final String uri, final String method, final Properties header, final Properties parms);
	
	public void setUriResponse(String uri, Response response);


}
