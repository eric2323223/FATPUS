package component.entity.resource;

public class EqualRelation extends AbstractRelation {

	public EqualRelation(String property, String value) {
		this.property = property;
		this.value = value;
	}
	
	@Override
	public boolean match(String value1, String value2) {
		return value1.equals(value2);
	}

}
