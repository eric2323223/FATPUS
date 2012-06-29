package component.entity.tplan;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: think
 * Date: 12-6-22
 * Time: ??7:39
 * To change this template use File | Settings | File Templates.
 */
public class DeviceInfo {
    Hashtable properties = new Hashtable();
    private String os;
    private String type;
    private String version;
    private String model;

    public String getProperty(String name){
        return properties.get(name).toString();
    }

    public void addProperty(String name, String value){
        properties.put(name, value);
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public boolean equals(Object object){
        if(object instanceof DeviceInfo){
            DeviceInfo info = (DeviceInfo)object;
            if(this.getModel()!=null && info.getModel()!=null){
                if(!this.getModel().equals(info.getModel())){
                    return false;
                }
            }
            if(this.getType()!=null && info.getType()!=null){
                if(!this.getType().equals(info.getType())){
                    return false;
                }
            }
            if(this.getVersion()!=null && info.getVersion()!=null){
                if(!this.getVersion().equals(info.getVersion())){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
