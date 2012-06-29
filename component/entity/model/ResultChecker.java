package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class ResultChecker extends AbstractModel{
	public static final String TYPE_DEFAULT="Default";
	public static final String TYPE_NONE="None";
	public static final String TYPE_NEW="new";
	public static final String TYPE_EXISTING="existing";

	private String type;

	public ResultChecker(String str) {
		ResultChecker rc = (ResultChecker)new ObjectMarshaller().deserialize(str, ResultChecker.class);
		this.type = rc.getType();
	}

	public ResultChecker() {
		// TODO Auto-generated constructor stub
	}

	public ResultChecker type(String string) {
		this.type=string;
		return this;
	}
	
	public String getType(){
		return this.type;
	}
	

}
