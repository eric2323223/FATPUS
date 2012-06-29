package component.entity.model;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class DeployOption implements IWizardEntity{
	public static final String MODE_UPDATE = "Update";
	public static final String MODE_NO_OVERWRITE = "No Overwrite";
	public static final String MODE_REPLACE = "Replace";
	public static final String MODE_VERIFY = "Verify";
	public static final String TYPE_MBS="mbs";
	public static final String TYPE_RBS="rbs";

	private String mode;
	private String packageName;
	private String mboNames;
	private String server;
	private String startParameter;
	private String serverConnctionMapping;
	private String mapRole;
	private String jar;
	private String checkResult;
	private String type;
	
	private VerificationCriteria verifyServerConnectionMapping;
	////////////////ff
	private VerificationCriteria verifySelectMbo;
	//////ff
	private VerificationCriteria verifyResult;
	private String overwriteExistingJar;
	private String targetVersion;
	private String saveAsDeploymentProfile;

	public String getServerConnectionMapping(){
		return this.serverConnctionMapping;
	}

	public String getMode() {
		return this.mode;
	}
	
	public String getType(){
		return this.type;
	}
	
	public DeployOption type(String str){
		this.type = str;
		return this;
	}
	
	public String getMapRole(){
		return this.mapRole;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getMboNames() {
		return mboNames;
	}

	public String getServer() {
		return server;
	}
	
	public DeployOption mapRole(String str){
		if(this.mapRole==null){
			mapRole = str;
		}else{
			mapRole = mapRole+":"+str;
		}
		return this;
	}

	public DeployOption serverConnectionMapping(String[] string){
		String result = string[0]+","+string[1];
		this.serverConnctionMapping = result;
		return this;
	}
	
	public DeployOption serverConnectionMapping(String string){
		if(this.serverConnctionMapping==null){
			this.serverConnctionMapping = string;
		}else{
			this.serverConnctionMapping = this.serverConnctionMapping+":"+string;
		}
		return this;
	}
	
	public DeployOption serverConnectionMapping(List<String[]> list){
		StringBuffer sb = new StringBuffer("");
		for(String[] array: list){
			sb.append(array[0]+","+array[1]);
			sb.append(":");
		}
		this.serverConnctionMapping = sb.toString();
		this.serverConnctionMapping = this.serverConnctionMapping.substring(0, this.serverConnctionMapping.length()-1);
		return this;
	}
	
	public DeployOption mode(String mode){
		this.mode = mode;
		return this;
	}
	
	public DeployOption startParameter(String string){
		this.startParameter = string;
		return this;
	}
	
	public DeployOption packageName(String string){
		this.packageName = string;
		return this;
	}
	
	public DeployOption mboNames(String string){
		this.mboNames = string;
		return this;
	}
	
	public DeployOption mboNames(String[] string){
		this.mboNames = "";
		for(String name:string){
			this.mboNames = this.mboNames + name +",";
		}
		this.mboNames = this.mboNames.substring(0, this.mboNames.length()-1);
		return this;
	}
	
	public DeployOption server(String string){
		this.server = string;
		return this;
	}


	@Override
	public String sp() {
		return this.startParameter;
	}

	public DeployOption mapRole(String[] strings) {
		this.mapRole = strings[0]+","+strings[1];
		return this;
	}
	
	public DeployOption mapRole(List<String[]> strings) {
		StringBuffer sb = new StringBuffer("");
		for(String[] map:strings){
			sb.append(map[0]+","+map[1]);
			sb.append(":");
		}
		this.mapRole = sb.toString();
		this.mapRole = this.mapRole.substring(0, this.mapRole.length()-1);
		return this;
	}

	public DeployOption verifyServerConnectionMapping(String string, boolean b) {
		this.verifyServerConnectionMapping = new VerificationCriteria(string, b);
		return this;
	}	
	public DeployOption verifySelectMbo(String string, boolean b) {
		this.verifySelectMbo = new VerificationCriteria(string, b);
		return this;
	}
	public VerificationCriteria verifySelectMbo(){
		return this.verifySelectMbo;
	}

	public VerificationCriteria verifyServerConnectionMapping(){
		return this.verifyServerConnectionMapping;
	}

	public DeployOption jar(String string) {
		this.jar = string;
		return this;
	}
	
	public String getJar(){
		return this.jar;
	}

	public DeployOption checkResult(String string) {
		this.checkResult = string;
		return this;
	}
	
	public String getCheckResult(){
		return this.checkResult;
	}
	
	public DeployOption verifyResult(String expect, boolean b){
		this.verifyResult = new VerificationCriteria(expect, b);
		return this;
	}
	
	public VerificationCriteria verifyResult(){
		return this.verifyResult;
	}

	public DeployOption overwriteExistingJar(String string) {
		this.overwriteExistingJar = string;
		return this;
	}
	
	public String getOverWriteExistingJar(){
		return this.overwriteExistingJar;
	}

	public DeployOption targetVersion(String string) {
		this.targetVersion=string;
		return this;
	}
	
	public String getTargetVersion(){
		return this.targetVersion;
	}

	public DeployOption saveAsDeploymentProfile(String string) {
		this.saveAsDeploymentProfile = string;
		return this;
	}
	
	public String getSaveAsDeploymentProfile(){
		return this.saveAsDeploymentProfile;
	}
	
}
