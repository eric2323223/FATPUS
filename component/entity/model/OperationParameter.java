package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class OperationParameter extends AbstractModel{
	private String name;
	private String type;
	private String defaultValue;
	private String pk;
	
	public OperationParameter(String str) {
		OperationParameter parameter = (OperationParameter)ObjectMarshaller.deserialize(str, OperationParameter.class);
		this.name = parameter.getName();
		this.type = parameter.getType();
		this.defaultValue = parameter.getDefaultValue();
		this.pk = parameter.getPk();
	}
	
	public OperationParameter() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public String[] defaultValueAsArray(){
		return defaultValue.split(",");
	}

	public OperationParameter name(String str){
		this.name = str;
		return this;
	}
	public OperationParameter type(String str){
		this.type = str;
		return this;
	}
	public OperationParameter defaultValue(String str){
		this.defaultValue = str;
		return this;
	}
	public OperationParameter pk(String string) {
		this.pk=string;
		return this;
	}
	
	public String getPk(){
		return this.pk;
	}
}
