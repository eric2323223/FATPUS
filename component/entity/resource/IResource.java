package component.entity.resource;


public interface IResource {
	public Object getProperty(String name);
	public boolean isActive();
	public void setProperty(String key, Object value);
	public boolean match(Criteria criteria);

}
