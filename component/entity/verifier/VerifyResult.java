package component.entity.verifier;

import com.rational.test.ft.script.RationalTestScript;

public class VerifyResult extends RationalTestScript{
	String name;
	String expected;
	String actual;
	boolean isPass;

	public VerifyResult(String name) {
		this.name = name;
		this.isPass = true;
	}

	public VerifyResult(String name, String expected, String message) {
		this.name = name;
		this.expected = expected;
		this.actual = message;
		this.isPass = false;
	}

	public void perform() {
		if(isPass()){
			logInfo("Verification point ["+name+"] passed.");
		}else{
			logError("Verification point ["+name+"] failed: EXPECTED["+this.expected+
					"]  ACTUAL["+this.actual+"].");
		}
		
	}

	public boolean isPass() {
		return this.isPass;
	}


}
