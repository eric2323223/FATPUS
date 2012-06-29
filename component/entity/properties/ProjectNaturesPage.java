package component.entity.properties;

import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;

public class ProjectNaturesPage implements IPropertiesPage {
	String title;

	public ProjectNaturesPage(String title) {
		this.title = title;
	}

	@Override
	public String get(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	private TopLevelTestObject dialog() {
		return DOF.getDialog("Properties for " + this.title);
	}

}
