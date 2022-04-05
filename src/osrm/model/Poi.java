package osrm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class Poi {
    double[] location;
    String name;
    double distance;
    String hint;
    @JsonIgnore
    int trips_index;
    int waypoint_index;
    public Poi() {
    }

    public Poi(double[] location, String name, double distance, String hint) {
        this.location = location;
        this.name = name;
        this.distance = distance;
        this.hint = hint;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getTrips_index() {
        return trips_index;
    }

    public void setTrips_index(int trips_index) {
        this.trips_index = trips_index;
    }

    public int getWaypoint_index() {
        return waypoint_index;
    }

    public void setWaypoint_index(int waypoint_index) {
        this.waypoint_index = waypoint_index;
    }

    @Override
    public String toString() {
        return "Poi{" +
               "location=" + Arrays.toString(location) +
               ", name='" + name + '\'' +
               '}';
    }
}
