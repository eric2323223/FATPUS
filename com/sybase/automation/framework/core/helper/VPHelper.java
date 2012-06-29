package com.sybase.automation.framework.core.helper;

import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.IFtVerificationPoint;

public class VPHelper  extends RationalTestScript{
	@Override
	public void onVpFailure(IFtVerificationPoint vp){
		System.out.println("Expected: ["+vp.getExpectedData()+"]");
		System.out.println("Actual  : ["+vp.getActualData()+"]");
		super.onVpFailure(vp);
	}

}
