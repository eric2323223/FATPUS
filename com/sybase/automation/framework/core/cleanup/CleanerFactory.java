/**
 * Created on Mar 14, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.core.cleanup;

import com.sybase.automation.framework.common.ResourceUtil;

/**
 * @author xfu
 */
public class CleanerFactory
{

    public static ICleanUp createCleaner()
    {
        try
        {
            ResourceUtil.loadResource("\\testrunner.properties");
            String cleaner = ResourceUtil.getProperty("cleaner");
            ICleanUp ret = (ICleanUp) Class.forName(cleaner).newInstance();
            return ret;
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Fail to load the parser");

        }
    }

}
