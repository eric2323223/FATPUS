package component.entity.model;

public class WFLocalFile {

	private String language;
	private String country;
	private String overwrite;
	private String defaultlocal;
	private String variant;

	public String getVariant() {
		return variant;
	}

	public WFLocalFile setVariant(String variant) {
		if (null != this.variant) {
			this.variant = this.variant + "/" + variant;
		} else {
			this.variant = variant;
		}
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public WFLocalFile setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public WFLocalFile setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getOverwrite() {
		return overwrite;
	}

	public WFLocalFile setOverwrite(String overwrite) {
		this.overwrite = overwrite;
		return this;
	}

	public String getDefaultlocal() {
		return defaultlocal;
	}

	public WFLocalFile setDefaultlocal(String defaultlocal) {
		this.defaultlocal = defaultlocal;
		return this;
	}

}
