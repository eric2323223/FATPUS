package component.entity.tplan;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

public class CoordinateConfig {
	private Properties namedCoordinates;
    private Document document;
    private Element rootElement;
    private static NamedPoint[] datumPoints;
    private IPointFactory pointFactory;

	
	public CoordinateConfig(String configFile, IPointFactory pointFactory){
        try {
            Builder builder = new Builder();
            document = builder.build(new File(configFile));
            rootElement = document.getRootElement();
            this.pointFactory = pointFactory;
            loadDatumPoints();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private void loadDatumPoints() {
        Elements childElements = rootElement.getChildElements();
        datumPoints = new NamedPoint[childElements.size()];
        for(int i=0; i<datumPoints.length;i++){
            Element e = rootElement.getChildElements().get(i);
            Attribute attr = e.getAttribute("datum");
            String pointName = attr.getValue();
            Point point = pointFactory.getPoint(pointName);
            NamedPoint np = new NamedPoint(pointName, point);
            datumPoints[i] = np;
        }
    }

    public Point getCoordinate(String name) {
		String pointStr = namedCoordinates.getProperty(name);
		int x = new Integer(pointStr.split(",")[0]);
		int y = new Integer(pointStr.split(",")[1]);
		return new Point(x, y);
	}

    public DeviceInfo getDeviceInfo(){
        DeviceInfo deviceInfo = new DeviceInfo();
        int attributeCount = rootElement.getAttributeCount();
        for(int i=0;i<attributeCount;i++){
            Attribute attribute = rootElement.getAttribute(i);
            String attributeName = attribute.getQualifiedName();
            String value = attribute.getValue();
            deviceInfo.addProperty(attributeName, value);
        }
        return deviceInfo;
    }

    public Point getNamedPoint(String datum, String pointName) throws NamedPointNotFoundException {
        for(int i=0;i<datumPoints.length;i++){
            NamedPoint datumPoint = datumPoints[i];
            if(datumPoint.getName().equals(datum)){
                Point dp = datumPoint.getPoint();
                Element element = getNamePointsElement(i);
                Elements children = element.getChildElements();
                for(int j=0;j<children.size();j++){
                    Element child = children.get(j);
                    Attribute name = child.getAttribute("name");
                    if(pointName.equals(name.getValue())){
                        Attribute point = child.getAttribute("point") ;
                        String pointStr = point.getValue();
                        String xStr = pointStr.split(",")[0];
                        String yStr = pointStr.split(",")[1];

                        return new Point(new Integer(xStr).intValue()+dp.x, new Integer(yStr).intValue()+dp.y);
                    }
                }
            }
        }
        throw new NamedPointNotFoundException(pointName);
    }

    private Element getNamePointsElement(int index) {
        return rootElement.getChildElements().get(index);
    }
}
