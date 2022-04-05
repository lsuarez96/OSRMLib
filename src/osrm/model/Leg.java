package osrm.model;

public class Leg {
    double distance;
    double duration;
    double weight;
    String summary;
    Object[] steps;

    public Leg() {
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Object[] getSteps() {
        return steps;
    }

    public void setSteps(Object[] steps) {
        this.steps = steps;
    }
}
