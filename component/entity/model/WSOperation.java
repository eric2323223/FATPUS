package component.entity.model;

public class WSOperation implements IWizardEntity {
	public String getResultChecker() {
		return resultChecker;
	}

	public String getName() {
		return name;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public String getConnectionProfile() {
		return connectionProfile;
	}

	public String getProject() {
		return project;
	}

	public String getExistingResultChecker() {
		return existingResultChecker;
	}

	public String getNewResultChecker() {
		return newResultChecker;
	}

	public String getJavaNature() {
		return javaNature;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public String getMethod() {
		return method;
	}

	public String getAttributeName() {
		return attributeName;
	}
	private String parameter;
	private String resultChecker;
	private String name;
	private String type;
	private String dataSourceType;
	private String connectionProfile;
	private String project;
	private String existingResultChecker;
	private String newResultChecker;
	private String javaNature;
	private String sourceFolder;
	private String method;
	private String attributeName;
	
	public WSOperation dataSourceType(String str){
		this.dataSourceType = str;
		return this;
	}

	public WSOperation type(String str){
		this.type = str;
		return this;
	}
	
	public String getType(){
		return this.type;
	}
	
	public WSOperation connectionProfile(String str){
		this.connectionProfile = str;
		return this;
	}
	
	public WSOperation method(String str){
		this.method = str;
		return this;
	}
	
	public WSOperation name(String str){
		this.name = str;
		return this;
	}

	@Override
	public String sp() {
		return parameter;
	}

	@Override
	public WSOperation startParameter(String str) {
		this.parameter = str;
		return this;
	}
	
	

}
