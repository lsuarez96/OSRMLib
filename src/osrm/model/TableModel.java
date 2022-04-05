package osrm.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement
public class TableModel implements Serializable {
    Poi[] sources;
    Poi[] destinations;
    String code;
    double[][] durations;
    double[][] distances;

    public TableModel() {
    }

    public TableModel(Poi[] sources, Poi[] destinations, String code, double[][] durations, double[][] distances) {
        this.sources = sources;
        this.destinations = destinations;
        this.code = code;
        this.durations = durations;
        this.distances = distances;
    }

    public Poi[] getSources() {
        return sources;
    }

    public void setSources(Poi[] sources) {
        this.sources = sources;
    }

    public Poi[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Poi[] destinations) {
        this.destinations = destinations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double[][] getDurations() {
        return durations;
    }

    public void setDurations(double[][] durations) {
        this.durations = durations;
    }

    public double[][] getDistances() {
        return distances;
    }

    public void setDistances(double[][] distances) {
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "TableModel{" +
               "durations=" + Arrays.toString(durations) +
               ", distances=" + Arrays.toString(distances) +
               '}';
    }
}
