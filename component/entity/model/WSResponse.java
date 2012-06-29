package component.entity.model;

import java.lang.reflect.InvocationTargetException;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class WSResponse {
	private String xsdUri;
	private String rootElement;
	
	public WSResponse(){};

	public WSResponse(String str){
		try{
			WSResponse response = (WSResponse)ObjectMarshaller.deserialize(str, WSResponse.class);
			this.xsdUri = response.getXsdUri();
			this.rootElement = response.getRootElement();
		}catch(Exception e){
			throw new RuntimeException("Failed to deserialize WSResponse");
		}
//		this.xsdUri = ObjectMarshaller.getAttribute("xsdUri");
//		this.rootElement = ObjectMarshaller.getAttribute("rootElement");
		
	}

	public WSResponse xsdUri(String string) {
		this.xsdUri = string;
		return this;
	}

	public WSResponse rootElement(String string) {
		this.rootElement = string;
		return this;
	}
	
	public String getXsdUri() {
		return this.xsdUri;
	}

	public String getRootElement() {
		return this.rootElement;
	}

	public String toString(){
		try {
			return ObjectMarshaller.serialize(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to serialized WSResponse");
		} 
//		String str = "";
//		if(this.xsdUri!=null){
//			str= str+"[xsdUri]"+this.xsdUri;
//		}
//		if(this.rootElement!=null){
//			str = str+"[rootElement]"+this.rootElement;
//		}
//		return str;
	}

}
