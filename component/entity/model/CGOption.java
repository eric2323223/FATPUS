package component.entity.model;

import component.entity.model.IWizardEntity;

public class CGOption implements IWizardEntity {
	public static final String MBS = "MBS";
	public static final String RBS = "RBS";
	
	private String project;
	private String configuration;
	private String language;
	private String platform;
	private String unwiredServer;
	private String serverDomain;
	private String packageName;
	private String projectPath;
	private String fileSystemPath;
	private String cleanUp;
	private String generateMetaData;
	private String deleteConfig;
	private String selectMbo;
	private VerificationCriteria verifyConfiguration;
	private VerificationCriteria verifyDeleteConfig;
	private VerificationCriteria verifySelectMbo;
	private VerificationCriteria verifyHeaderMessage;
	private String pageSize;
	private String mboType;
	
	public String getDeleteConfig(){
		return this.deleteConfig;
	}
	
	public CGOption deleteConfig(String str){
		this.deleteConfig = str;
		return this;
	}
	
	public CGOption verifyDeleteConfig(String expected, boolean canGo){
		this.verifyDeleteConfig = new VerificationCriteria(expected, canGo);
		return this;
	}
	
	public VerificationCriteria verifyDeleteConfig(){
		return this.verifyDeleteConfig;
	}
	
	public CGOption verifySelectMbo(String expected, boolean canGo){
		this.verifySelectMbo = new VerificationCriteria(expected, canGo);
		return this;
	}
	
	public VerificationCriteria verifySelectMbo(){
		return this.verifySelectMbo;
	}

	public CGOption verifyConfiguration(String expected, boolean canGo){
		this.verifyConfiguration = new VerificationCriteria(expected, canGo);
		return this;
	}
	
	public VerificationCriteria verifyConfiguration(){
		return this.verifyConfiguration;
	}
	
	public CGOption generateMetaData(String str){
		this.generateMetaData = str;
		return this;
	}
	
	public CGOption platform(String str){
		this.platform = str;
		return this;
	}

	public CGOption cleanUp(String str){
		this.cleanUp = str;
		return this;
	}
	
	public CGOption fileSystemPath(String str){
		this.fileSystemPath = str;
		return this;
	}
	
	public CGOption projectPath(String str){
		this.projectPath = str;
		return this;
	}
	
	public CGOption packageName(String str){
		this.packageName = str;
		return this;
	}
	
	public CGOption serverDomain(String str){
		this.serverDomain = str;
		return this;
	}
	
	public CGOption unwiredServer(String str){
		this.unwiredServer = str;
		return this;
	}
	
	public CGOption language(String str){
		this.language = str;
		return this;
	}
	
	public CGOption configuration(String str){
		this.configuration = str;
		return this;
	}
	

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public CGOption startParameter(String str) {
		this.project = str;
		return this;
	}

	public String getConfiguration() {
		return configuration;
	}

	public String getLanguage() {
		return language;
	}
	
	public String getPlatform() {
		return platform;
	}

	public String getUnwiredServer() {
		return unwiredServer;
	}

	public String getServerDomain() {
		return serverDomain;
	}

	public String getPackageName() {
		return packageName;
	}
	
	public String getGenerateMetaData() {
		return generateMetaData;
	}
	
	public String getPageSize(){
		return this.pageSize;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public String getFileSystemPath() {
		return fileSystemPath;
	}

	public String getCleanUp() {
		return cleanUp;
	}

	public CGOption selectMbo(String string) {
		this.selectMbo = string;
		return this;
	}
	
	public String getSelectMbo(){
		return this.selectMbo;
	}

	public CGOption verifyHeaderMessage(String string, boolean b) {
		this.verifyHeaderMessage = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyHeaderMessage(){
		return this.verifyHeaderMessage;
	}

	public CGOption pageSize(String string) {
		this.pageSize= string;
		return this;
	}

	public CGOption mboType(String type) {
		this.mboType = type;
		return this;
	}

	public String getMboType(){
		return this.mboType;
	}
}
