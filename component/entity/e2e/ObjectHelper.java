package component.entity.e2e;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectHelper {
	public static Object getObject(Class clazz, String value) {
		System.out.println("["+clazz.getName()+"]: "+value);
		if (clazz.getName().equals("java.lang.Integer")) {
			return new Integer(value);
		}
		if (clazz.getName().equals("java.sql.Date")) {			
			return java.sql.Date.valueOf(value);
		}
		if (clazz.getName().equals("java.lang.String")) {
			return new String(value);
		}
		if (clazz.getName().equals("java.lang.Float")) {
			return new Float(value);
		}
		if (clazz.getName().equals("int")) {
			return new Integer(value).intValue();
		}
		if (clazz.getName().equals("float")) {
			return new Float(value).floatValue();
		}
		if (clazz.getName().equals("boolean")) {
			return new Boolean(value).booleanValue();
		}
		if (clazz.getName().equals("double")) {
			return new Double(value).doubleValue();
		}
		if (clazz.getName().equals("byte[]")) {
			return value.getBytes();
		}
		if (clazz.getName().equals("java.sql.Timestamp")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSS");
			java.util.Date parsedDate;
			try {
				parsedDate = dateFormat.parse(value);
				return new java.sql.Timestamp(parsedDate.getTime());
			} catch (ParseException e) {
				throw new RuntimeException("Timestamp type should be in 'yyyy-MM-dd hh:mm:ss.SSS' format");
			}
		}
		if (clazz.getName().equals("java.math.BigDecimal")) {
			return new java.math.BigDecimal(value);
		}

		return new RuntimeException("Unknown type: " + clazz.getName());
	}
}
