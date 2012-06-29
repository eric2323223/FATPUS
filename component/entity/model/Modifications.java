package component.entity.model;

import java.util.ArrayList;
import java.util.List;

import component.entity.IEditable;

public class Modifications {
	List<Modification> mods = new ArrayList<Modification>();
	
	public Modifications mod(String string, IEditable widget) {
		mods.add(new Modification(string, widget));
		return this;
	}

	public void act() {
		for(Modification mod:mods){
			mod.act();
		}
		
	}

}
