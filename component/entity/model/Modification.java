package component.entity.model;

import component.entity.IEditable;

public class Modification {
	public static final String ADD="add";
	public static final String UPDATE="update";
	public static final String DELETE="delete";
	
	String type;
	IEditable widget;
	
	public Modification(String type, IEditable w){
		this.type = type;
		this.widget = w;
	}

	public void act() {
		widget.openInPropertiesView();
		if(type.equals(ADD)){
			widget.add();
		}
		else if(type.equals(DELETE)){
			widget.delete();
		}
		else{
			widget.update();
		}
		
	}

}
