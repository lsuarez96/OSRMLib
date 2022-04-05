package osrm.model;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    double distance;
    double duration;
    double weight;
    String weight_name;
    Geometry geometry;
    Leg[] legs;

    public Trip() {
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeight_name() {
        return weight_name;
    }

    public void setWeight_name(String weight_name) {
        this.weight_name = weight_name;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Leg[] getLegs() {
        return legs;
    }

    public void setLegs(Leg[] legs) {
        this.legs = legs;
    }


    @Override
    public String toString() {
        return "Trip{" +
               "distance=" + distance +
               ", duration=" + duration +
               ", weight=" + weight +
               ", weight_name='" + weight_name + '\'' +
               ", geometry=" + geometry +
               '}';
    }
}
