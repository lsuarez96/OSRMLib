package osrm.model;

import osrm.model.Trip;
import osrm.model.Poi;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement
public class TripModel implements Serializable {
     Poi[] waypoints;
     Trip[] trips;
     String code;

    public TripModel() {
    }

    public Poi[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Poi[] waypoints) {
        this.waypoints = waypoints;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public void setTrips(Trip[] trips) {
        this.trips = trips;
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
               ", routes=" + Arrays.toString(trips) +
               ", code='" + code + '\'' +
               '}';
    }
}
