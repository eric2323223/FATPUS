package component.entity.resource;

public interface IRelation {

	boolean match(String value1, String value2);
	String getProperty();
	String getValue();

}
