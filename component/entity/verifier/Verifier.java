package component.entity.verifier;

import component.entity.WFCustomizer.CallBackMethod;
import component.entity.model.VerificationCriteria;
import component.entity.wizard.IWizard;

public class Verifier {

	private static VerifyResult result = null;

	public static VerifyResult verify(String name,Object expected, Object actual) {
		if(expected.equals(actual)){
			return new VerifyResult(name);
		}else{
			return new VerifyResult(name, expected.toString(), actual.toString());
		}
	}

	public static VerifyResult verify(String name, boolean expected,boolean actual) {
		if(expected==actual){
			return new VerifyResult(name);
		}else{
			return new VerifyResult(name, expected?"true":"false", actual?"true":"false");
		}
	}
	
	public static VerifyResult verifyEquals(String name, IWizard wizard, VerificationCriteria vc, String actual){
		if(vc.getExpected().equals(actual)){
			if(!vc.isContinueWhenMatch()){
				wizard.canContinue(false);
			}
			return new VerifyResult(name);
		}else{
			wizard.canContinue(false);
			return new VerifyResult(name, vc.getExpected().toString(), actual.toString());
		}
	}
	
	public static VerifyResult verifyContains(String name, IWizard wizard, VerificationCriteria vc, String actual){
		if(actual.contains(vc.getExpected())){
			if(!vc.isContinueWhenMatch()){
				wizard.canContinue(false);
			}
			return new VerifyResult(name);
		}else{
			wizard.canContinue(false);
			return new VerifyResult(name, vc.getExpected().toString(), actual.toString());
		}
	}

	public static VerifyResult verifyEquals(String vpName, VerificationCriteria vc, String actual, CallBackMethod cancelMethod) {
		if(actual.equals(vc.getExpected())){
			if(!vc.isContinueWhenMatch()){
				cancelMethod.call();
			}
			result = new VerifyResult(vpName);
		}else{
//			cancelMethod.call();
			result = new VerifyResult(vpName, vc.getExpected().toString(), actual.toString());
		}
		return result;
	}
	
	public static VerifyResult getResult(){
		return result;
	}

}
