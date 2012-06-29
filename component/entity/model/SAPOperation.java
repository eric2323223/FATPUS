package component.entity.model;

public class SAPOperation implements IWizardEntity{
	private String name;
	private String type;
	private String operation;
	private String parameter;
	private String parameterValue;
	private String mbo;
	private String bapiOperaton;
	
	public String getBapiOperation(){
		return this.bapiOperaton;
	}
	
	public SAPOperation bapiOperation(String str){
		this.bapiOperaton = str;
		return this;
	}
	
	public SAPOperation type(String str){
		this.type = str;
		return this;
	}
	
	public String getParameterValue(){
		return this.parameterValue;
	}
	
	public SAPOperation parameterValue(String str){
		if(this.parameterValue==null){
			this.parameterValue = str;
		}else{
			this.parameterValue = this.parameterValue+":"+str;
		}
		return this;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getOperation() {
		return operation;
	}

	public String getParameter() {
		return parameter;
	}
	
	public SAPOperation operation(String str){
		this.operation = str;
		return this;
	}
	
	public SAPOperation parameter(String str){
		if(this.parameter==null){
			this.parameter = str;
		}else{
			this.parameter = this.parameter +":"+str;
		}
		return this;
	}
	
	public SAPOperation name(String str){
		this.name = str;
		return this;
	}

	@Override
	public String sp() {
		return this.mbo;
	}

	@Override
	public SAPOperation startParameter(String str) {
		this.mbo = str;
		return this;
	}

	
}
