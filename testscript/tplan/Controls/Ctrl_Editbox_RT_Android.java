package testscript.tplan.Controls;

import component.entity.tplan.SuadeTestScript;

public class Ctrl_Editbox_RT_Android extends SuadeTestScript{

	@Override
	public void doTest() throws Exception {
		connect("10.56.253.171", "secretWF");
		clickOn("add_city_button");
		verifyLoginName("city_xian.png");
		clickOn("remove_city_button");
		disconnect();
		
	}

}
