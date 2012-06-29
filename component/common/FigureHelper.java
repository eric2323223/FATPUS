package component.common;

import java.awt.Point;
import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.tool.NativeInvoker;

/**
 * Description : Super class for script helper
 * 
 * @author dongxu
 * @since July 23, 2008
 */
public abstract class FigureHelper extends RationalTestScript {

	/** Method used to get bounds of a test object */
	public static final String GET_BOUNDS_METHOD = "getBounds";

	/**
	 * Try to get center of a test object with "getBounds" method
	 * 
	 * @param to
	 * @return
	 */
	public static Point getCenter(TestObject to) {
		return getCenter(to, GET_BOUNDS_METHOD);
	}

	/**
	 * Get center of a test object with specified method
	 * 
	 * @param to
	 * @param method
	 * @return
	 */
	public static Point getCenter(TestObject to, String method) {
		return getBoundsCenter(getBounds(to, method));
	}

	/**
	 * Try to get bound of a test object with "getBounds" method
	 * 
	 * @param to
	 * @return
	 */
	public static Rectangle getBounds(TestObject to) {
		return getBounds(to, GET_BOUNDS_METHOD);
	}

	/**
	 * Get bound of a test object with specified method
	 * 
	 * @param to
	 * @param method
	 * @return
	 */
	public static Rectangle getBounds(TestObject to, String method) {
		try {
			return (Rectangle) to.invoke(method);
		} catch (Exception e) {
		}
		try {
			return makeBoundsByReflect(to, method);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Get point of a test object with specified method
	 * 
	 * @param to
	 * @param method
	 * @return
	 */
	public static Point getPoint(TestObject to, String method) {
		try {
			return (Point) to.invoke(method);
		} catch (Exception e) {
		}
		try {
			return makePointByReflect(to, method);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Get center point of a bound.
	 * 
	 * @param bounds
	 * @return
	 */
	static public Point getBoundsCenter(Rectangle bounds) {
		return new Point(bounds.x + (bounds.width / 2), bounds.y
				+ (bounds.height / 2));
	}

	/**
	 * Make a bound with invoke get bound method on test object
	 * 
	 * @param to
	 * @param method
	 * @return
	 */
	private static Rectangle makeBoundsByReflect(TestObject to, String method) {
		TestObject rectangle = (TestObject) to.invoke(method);
		String recString = rectangle.invoke("toString").toString();
		String[] numbers = recString.split(",");
		return makeBoundsByArray(numbers);
	}

	/**
	 * Make a bound with splited to string array
	 * 
	 * @param numbers
	 * @return
	 */
	private static Rectangle makeBoundsByArray(String[] numbers) {
		Integer width = new Integer(numbers[2].trim());
		Integer height = new Integer(numbers[3].trim().substring(0,
				numbers[3].trim().length() - 1));
		Integer x = new Integer(numbers[0].substring(
				numbers[0].indexOf("(") + 1, numbers[0].length()));
		Integer y = new Integer(numbers[1].trim());
		return new Rectangle(x.intValue(), y.intValue(), width.intValue(),
				height.intValue());
	}

	/**
	 * Make a point with invoke get point method on test object
	 * 
	 * @param to
	 * @param method
	 * @return
	 */
	private static Point makePointByReflect(TestObject to, String method) {
		TestObject p = (TestObject) to.invoke(method);
		String sp = (String) p.invoke("toString");
		String[] pArray = sp.split(",");
		return makePointByArray(pArray);
	}

	/**
	 * Make a point with splited to string array
	 * 
	 * @param pArray
	 * @return
	 */
	private static Point makePointByArray(String[] pArray) {
		String xCand = pArray[0].trim();
		String yCand = pArray[1].trim();
		Integer x = new Integer(xCand.substring(6, xCand.length()));
		Integer y = new Integer(yCand.substring(0, yCand.length() - 1));
		return new Point(x.intValue(), y.intValue());
	}
}