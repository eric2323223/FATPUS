package component.entity.model;

import component.entity.IEditable;

public class WFLview extends Widget implements IEditable {
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	protected String emptymessage;
	private String key;
	private String lvDetailScreen;
	//add by yanxu
	private String alternatecolor;
	
	//ff0208 add>>>>>>
	private String NewKeyBindMBOQueryRequest;
	//ff0208<<<<<<<<<<<
	
	public String getalternateColor() {
		return this.alternatecolor;
	}

	public WFLview alternateColor(String string) {
		this.alternatecolor = string;
		return this;
	}
	//end
	public WFLview lvDetailScreen(String string) {
		this.lvDetailScreen = string;
		return this;
	}
	
	public String getLvDetailScreen() {
		return this.lvDetailScreen;
	}
	
	public WFLview key(String key) {
		this.key = key;
		return this;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public WFLview newKey(String str) {
		super.newKey(str);
		return this;
	}

	public WFLview emptyMessage(String str) {
		emptymessage = str;
		return this;
	}

	public String getEmptymessage() {
		return emptymessage;
	}

	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub

	}
//ff0208add>>>>>>
	public WFLview NewKeyBindMBOQueryRequest(String string) {
		NewKeyBindMBOQueryRequest= string;
		return this;
	}
	
	public String getNewKeyBindMBOQueryRequest(){
		return this.NewKeyBindMBOQueryRequest;
	}
//ff0208add<<<<<<<<<<<<<
	
}
