package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class ResultFilter extends AbstractModel {
	public final static String TYPE_NEW="new";
	public final static String TYPE_EXISTING="existing";
	
	private String type;
	private String javaClass;

	public ResultFilter(String str) {
		ResultFilter filter = (ResultFilter)ObjectMarshaller.deserialize(str, ResultFilter.class);
		this.type = filter.getType();
		this.javaClass = filter.getJavaClass();
	}
	
	public ResultFilter(){}
	
	public String getType(){
		return this.type;
	}
	
	public String getJavaClass(){
		return this.javaClass;
	}
	
	public ResultFilter type(String str){
		this.type = str;
		return this;
	}
	
	public ResultFilter javaClass(String str){
		this.javaClass = str;
		return this;
	}

}
