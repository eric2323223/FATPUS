package com.sybase.automation.framework.common;
/**
 * A collection of utilities on system clipboard
 * @author xfu
 */
public class ClipBoardUtil 
{

    /**
	 * return the text on the clipboard.
	 * If there is no text, it returns null
	 */
    public static String getClipboardText() 
    {
        java.awt.datatransfer.Clipboard clip =
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        java.awt.datatransfer.Transferable t = clip.getContents(null);
        String s = null;
        try 
        {
            s =
                (String) t.getTransferData(
                java.awt.datatransfer.DataFlavor.stringFlavor);
        }
        catch (Exception e) 
        {
        }
        return s;
    }

    /** 
	 * Set the contents of the clipboard to the provided text
	 */
    public static void setClipboardText(String s) 
    {
        java.awt.datatransfer.Clipboard clip =
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        java.awt.datatransfer.StringSelection ss =
            new java.awt.datatransfer.StringSelection(s);
        clip.setContents(ss, ss);
    }

}
