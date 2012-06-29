package component.entity.WFCustomizer;
public class WFCustomizeAction {
	private WFCustomizeActionType type;
	private String screenName;
	private String id;
	private String method;
	private String[] args;
	
	public WFCustomizeAction(WFCustomizeActionType type ,String screen, String id, String method, String[] parameters){
		this.type = type;
		this.screenName = screen;
		this.id = id;
		this.method = method;
		this.args = parameters;
	}

	public WFCustomizeActionType getType() {
		return this.type;
	}

}