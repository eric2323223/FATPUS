package component.entity.model;

public class VerificationCriteria {
	private String expect;
	private boolean isContinue;

	public VerificationCriteria(String expected, boolean isContinueWhenMatch) {
		this.expect = expected;
		this.isContinue = isContinueWhenMatch;
	}
	
	public String getExpected(){
		return this.expect;
	}
	
	public boolean isContinueWhenMatch(){
		return this.isContinue;
	}

}
