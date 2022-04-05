package osrm.model;



import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement
public class RouteModel implements Serializable {
     Poi[] waypoints;
     Trip[] routes;
     String code;

    public RouteModel() {
    }

    public Poi[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Poi[] waypoints) {
        this.waypoints = waypoints;
    }

    public Trip[] getRoutes() {
        return routes;
    }

    public void setRoutes(Trip[] routes) {
        this.routes = routes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RouteModel{" +
               "waypoints=" + Arrays.toString(waypoints) +
               ", routes=" + Arrays.toString(routes) +
               ", code='" + code + '\'' +
               '}';
    }
}
