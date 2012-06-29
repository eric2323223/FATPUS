package com.sybase.automation.framework.core;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ResourceUtil;


/**
 * Description : Functional Test Script
 * 
 * @author xfu
 */
public class TestRunner extends BaseTestRunner
{
    /**
     * Constructor
     */
    public TestRunner(){
        super();
    }
    public TestRunner(boolean doClean)
    {
        ResourceUtil.loadResource("\\testrunner.properties");
        caseFileSuffix = ResourceUtil.getProperty("case_file_suffix");
        actionComments = ResourceUtil.getProperty("comment");
        this.doClean = doClean;
    }

}
