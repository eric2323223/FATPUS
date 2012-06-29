package com.sybase.automation.framework.common;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.Robot;
import java.awt.image.*;

import com.rational.test.ft.object.interfaces.TestObject;
import com.sun.image.codec.jpeg.*;

/**
 * A collection of utilities on system snapshot
 * @author xfu
 */

public class ImageUtil 
{
    //this method captures the whole screen when only one arguement is given
    public static void screenCapture(String filename) 
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        doScreenCapture(filename, 0, 0, width, height);
    }

    //this method captures an individual TestObject (e.g. browser, div, table, etc.)
    public static void screenCapture(String filename, TestObject to) 
    {
        Rectangle r = (Rectangle) to.getProperty(".bounds");
        doScreenCapture(filename, r.x, r.y, r.width, r.height);
    }

    private static void doScreenCapture(
        String filename,
        int x,
        int y,
        int width,
        int height) 
    {
        try 
        {
            BufferedImage capture = null;
            Rectangle area = new Rectangle(x, y, width, height);
            Robot robot = new Robot();
            capture = robot.createScreenCapture(area);
            FileOutputStream out = new FileOutputStream(filename);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(capture);
            out.flush();
            out.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


}
