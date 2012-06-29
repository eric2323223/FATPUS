package component.entity.tplan;

import java.io.IOException;

public abstract class BlackBerryTestScript extends SuadeTestScript {

	@Override
	public abstract void doTest() throws Exception ;
	
	protected void moveToWorkflowListScreen() throws IOException, ActionFailedException {
		moveToMessageListScreen();
		clickContextMenu();
		clickOn("contextMenu_item_workflows");
	}
	
	protected void moveToMessageListScreen() throws IOException, ActionFailedException {
		clickContextMenu();
		clickOn("contextMenu_item_switchApp");
		clickOn("switch_panel_message");
	}
	
	private void clickContextMenu() throws IOException, ActionFailedException{
		clickOnWithOffSet("hangoff_BB", -230, 0);
	}
	
	protected int getClickHoldTime(){
		return 100;
	}
	

}
