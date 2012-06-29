package component.entity.model;

public class GeneratedFile {
	private String project;
	private String folder;
	private String packageName;
	private String fileName;
	
	public String getProject() {
		return project;
	}
	public String getFolder() {
		return folder;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getFileName() {
		return fileName;
	}
	
	public GeneratedFile fileName(String str){
		this.fileName = str;
		return this;
	}
	
	public GeneratedFile folder(String str){
		this.folder = str;
		return this;
	}
	
	public GeneratedFile packageName(String str){
		this.packageName = str.replace(".", "->");
		return this;
	}
	
	public GeneratedFile project(String str){
		this.project = str;
		return this;
	}
	public String getWNPath() {
		return project+"->"+folder+"->src->"+packageName+"->"+fileName;
	}

}
