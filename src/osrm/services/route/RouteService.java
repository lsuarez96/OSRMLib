package osrm.services.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import osrm.connection.Utils;
import osrm.model.Point;
import osrm.model.RouteModel;
import osrm.model.Trip;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RouteService {
    private final String baseHost;

    public RouteService(String baseHost) {
        this.baseHost = baseHost;
    }

    public RouteModel getTrip(List<Point> points) throws IOException {
        String query = buildQuery(points) + "?overview=full&geometries=geojson";
        String result = Utils.DoGetRequest(query);
        RouteModel model = new ObjectMapper().readValue(result, RouteModel.class);
        for (int i = 0; i < model.getRoutes().length; i++) {
            Trip trip=model.getRoutes()[i];
            trip.setDistance(trip.getDistance()/1000);
            trip.setDuration(trip.getDuration()/3600);
        }
        return model;
    }

    private String buildQuery(List<Point> points) {
        String servicePath = "/route/v1/driving/";
        StringBuilder query = new StringBuilder(baseHost + servicePath);
        for (Point c:points)  {
            query.append(c.getX()).append(",").append(c.getY()).append(";");
        }
        query.replace(query.length()-1,query.length(),"");
        return query.toString();
    }

    public static void main(String[] args) throws IOException {
        List<Point> sources = Arrays.asList(new Point(-82.37049401, 23.10183774), new Point(-82.36918745,
                                                                                            23.10297582),
                                            new Point(-82.37100363, 23.10062515));
        RouteService rs=new RouteService("http://localhost:5000");
        RouteModel trip = rs.getTrip(sources);
        System.out.println(trip);
    }
}
