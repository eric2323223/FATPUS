package com.sybase.automation.framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author xfu
 *  
 */
public class TestCase
{

    private String    name;

    private String    result;

    private ArrayList actions;

    private HashMap   session;

    //constructor
    public TestCase(String name)
    {
        this.name = name;
        result = "PASS";
    }

    /** ********************getter and setter***************************** */

    /**
     * Returns the name.
     * 
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the result.
     * 
     * @return String
     */
    public String getResult()
    {
        return result;
    }

    /**
     * Sets the name.
     * 
     * @param name The name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the result.
     * 
     * @param result The result to set
     */
    public void setResult(String result)
    {
        this.result = result;
    }

    public void setActions(List actions)
    {
        this.actions = (ArrayList) actions;

    }

    public ArrayList getActions()
    {
        return this.actions;
    }

    /**
     * @return Returns the session.
     */
    public HashMap getSession()
    {
        return session;
    }

    /**
     * @param session The session to set.
     */
    public void setSession(HashMap session)
    {
        this.session = session;
    }

    /**
     * TODO
     *  
     */
    public void addAttribule()
    {

    }

    /**
     * TODO
     * 
     * @return
     */
    public Object getAttribute()
    {
        return null;
    }

    /**
     * @param actions The actions to set.
     */
    public void setActions(ArrayList actions)
    {
        this.actions = actions;
    }

    public Object getSession(Object key)
    {
        return session.get(key);
    }
}
