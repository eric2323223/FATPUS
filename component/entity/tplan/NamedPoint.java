package component.entity.tplan;

import java.awt.Point;

public class NamedPoint {
	final Point point;
	final String name;
	String datumPoint;
	
	public NamedPoint(String name, Point point){
		this.name = name;
		this.point = point;
	}
	
	public NamedPoint(String name, int x, int y){
		this.name = name;
		this.point = new Point(x,y);
	}

	public Point getPoint() {
		return point;
	}

	public String getName() {
		return name;
	}

	public String getDatumPoint() {
		return datumPoint;
	}

}
