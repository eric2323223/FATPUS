package component.entity.WFCustomizer;

import java.util.Properties;

import component.entity.WFCustomizer.NanoHTTPD.Response;

public class WorkFlowNameHandler implements Handler {
	private String uri;
	private Response response;

	@Override
	public Response serve(final String uri, final String method, final Properties header, final	Properties parms) {
		if(uri.equals(this.uri)){
			return response;
		}else{
			return null;
		}
	}

	@Override
	public void setUriResponse(String u, Response response) {
		this.uri = u;
		this.response = response;
	}

	
	

}
