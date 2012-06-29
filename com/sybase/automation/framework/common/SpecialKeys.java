package com.sybase.automation.framework.common;

/**
 * 
 * @author xfu
 */
public interface SpecialKeys 
{
    String LEFT = "{ExtLeft}";
    String RIGHT = "{ExtRight}";
    String UP = "{Up}";
    String DOWN = "{ExtDown}";
    //
    String PAGEUP = "{ExtPgUp}";
    String PAGEDOWN = "{ExtPgDn}";
    String DELETE = "{ExtDelete}";
    String INSERT = "{ExtInsert}";
    String HOME = "{ExtHome}";
    String END = "{ExtEnd}";
    //
    String ENTER = "{ENTER}";
    String BACKSPACE = "{BKSP}";
    //
    String CAPSLOCK = "{CAPSLOCK}";
    String TAB = "{TAB}";
    String ESCAPE = "{ESCAPE}";
    //
    String LEFTALT = "{LeftAlt}";
    String LEFTCTRL = "{LeftCtrl}";
    String RIGHTALT = "{RightAlt}";
    String RIGHTCTRL = "{RightCtrl}";

    //added by Eric
//    String CLEARALL = "{ExtHome}+{ExtEnd}{ExtDelete}";
    String CLEARALL = "^{ExtHome}^+{ExtEnd}{ExtDelete}";
}
