package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class Jar extends AbstractModel implements IWizardEntity {

	public static final String TYPE_NEW = "new";
	public static final String TYPE_EXISTING = "existing";
	public static final String TYPE_EXTERNAL = "external";
	
	
	private String type;
	private String fileName;
	private String pkg;


	public Jar(String str) {
		Jar jar = (Jar)ObjectMarshaller.deserialize(str, Jar.class);
		this.type = jar.getType();
		this.fileName = jar.getFileName();
		this.pkg = jar.getPkg();
	}
	
	public Jar(){}

	public String getType() {
		return type;
	}

	public String getFileName() {
		return fileName;
	}

	public String getPkg() {
		return pkg;
	}

	@Override
	public String sp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public Jar type(String str) {
		this.type = str;
		return this;
	}

	public Jar fileName(String string) {
		this.fileName = string;
		return this;
	}

	public Jar pkg(String string) {
		this.pkg = string;
		return this;
	}
	


}
