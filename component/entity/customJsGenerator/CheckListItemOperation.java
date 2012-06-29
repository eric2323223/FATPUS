package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class CheckListItemOperation implements IScreenOperation {
	String columnData;
	String columnIndex;
	String screenName;

	@Override
	public String toJavaScriptCode() {
		return "getListItem(\""+this.columnData+"\", "+ this.columnIndex +");\n";
	}

	public void setColumnData(String columnData) {
		this.columnData = columnData;
		
	}

	public void setColumnIndex(String columnIndex) {
		this.columnIndex = columnIndex;
		
	}

	public String getColumnData() {
		return columnData;
	}

	public String getColumnIndex() {
		return columnIndex;
	}

	@Override
	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

}
