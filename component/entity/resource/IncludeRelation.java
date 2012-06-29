package component.entity.resource;

public class IncludeRelation extends AbstractRelation {

	public IncludeRelation(String property, String value) {
		this.property = property;
		this.value = value;
	}

	@Override
	public boolean match(String value1, String value2) {
		return value1.contains(value2);
	}



}
