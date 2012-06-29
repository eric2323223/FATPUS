package testscript.tplan.android;

import component.entity.tplan.SuadeTestScript;


public class SampleScript extends SuadeTestScript {

	public void doTest() throws Exception {
		connect("10.56.253.171", "secretWF");
		clickOn("add_city_button");
		verifyLoginName("city_xian.png");
		clickOn("remove_city_button");
		disconnect();
	}
}
