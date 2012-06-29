package component.entity.model;

public class JavaClass extends AbstractModel{
	String name;
	String sourceFolder;

	public JavaClass(String str) {
		if (!str.contains(",")) {
			this.name = str;
		} else {
			String[] attributes = str.split(",");
			for (String item : attributes) {
				String att = item.substring(1, item.indexOf("]"));
				String value = item.substring(item.indexOf("]") + 1, item
						.length());
				System.out.println(att + ":" + value);
				if (att.equals("sf")) {
					this.sourceFolder = value;
				}
				if (att.equals("name")) {
					this.name = value;
				}
			}
		}
	}

	public JavaClass() {
	}

	public String toString() {
		String result = "";
		if (this.sourceFolder != null) {
			result = result + "[sf]" + this.sourceFolder + ",";
		}
		if (this.name != null) {
			result = result + "[name]" + this.name;
		}
		return result;
	}

	public JavaClass name(String str) {
		this.name = str;
		return this;
	}

	public JavaClass sourceFolder(String str) {
		this.sourceFolder = str;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public String getSourceFolder() {
		return this.sourceFolder;
	}

}
