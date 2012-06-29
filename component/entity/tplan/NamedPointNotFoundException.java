package component.entity.tplan;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 12-6-23
 * Time: ??7:16
 * To change this template use File | Settings | File Templates.
 */
public class NamedPointNotFoundException extends Exception {
    private String pointName;

    public NamedPointNotFoundException(String pointName) {
        this.pointName = pointName;
    }

    public String getMessage(){
        return "Point "+this.pointName+" can't be found in config file";
    }
}
