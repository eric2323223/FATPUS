package component.editor;

public class AbstractEditor implements IEditor {

	@Override
	public void modify(Modifier modifier) {
		if(modifier.getType().equals(Modifier.TYPE_ADD)){
			addProperty(modifier.getProperty(), modifier.getNewValue());
		}else if(modifier.getType().equals(Modifier.TYPE_REMOVE)){
			removeProperty(modifier.getProperty(), modifier.getOrginalValue());
		}else{
			changeProperty(modifier.getProperty(), modifier.getOrginalValue(), modifier.getNewValue());
		}

	}

	private void changeProperty(String property, String orginalValue,String newValue) {
		// TODO Auto-generated method stub
		
	}

	private void removeProperty(String property, String orginalValue) {
		// TODO Auto-generated method stub
		
	}

	private void addProperty(String property, String newValue) {
		// TODO Auto-generated method stub
		
	}

}
