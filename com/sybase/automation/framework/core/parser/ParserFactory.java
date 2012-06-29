/**
 * Created on Mar 14, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.core.parser;

import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.core.TestCase;
import com.sybase.automation.framework.core.cleanup.ICleanUp;

/**
 * @author xfu
 */
public class ParserFactory
{
    public static ITestCaseParser createParser()
    {
        try
        {
            ResourceUtil.loadResource("\\testrunner.properties");
            String parser = ResourceUtil.getProperty("parser");
            ITestCaseParser ret = (ITestCaseParser) Class.forName(parser).newInstance();
            return ret;
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Fail to load the parser");

        }
    }
}
