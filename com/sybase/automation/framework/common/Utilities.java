/**
 * Created on Mar 13, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.common;

/**
 * @author xfu
 */
public class Utilities
{
    private static String projectRoot = ResourceUtil.class.getClassLoader().getResource(".").getPath();

    /**
     * Return get project root of the current project
     * 
     * @return
     */
    public static String getProjectRoot()
    {
        return projectRoot.endsWith(".") ? projectRoot.substring(0, projectRoot.length() - 1) : projectRoot;
    }

}
