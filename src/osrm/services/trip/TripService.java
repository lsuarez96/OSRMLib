package osrm.services.trip;

import com.fasterxml.jackson.databind.ObjectMapper;
import osrm.connection.Utils;
import osrm.model.*;
import osrm.services.route.RouteService;
import osrm.utils.GPXWritter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TripService {

    private final String baseHost;

    public TripService(String baseHost) {
        this.baseHost = baseHost;
    }

    public TripModel getTrip(List<Point> points) throws IOException {
        String query = buildQuery(points) + "&overview=full&geometries=geojson";
        String result = Utils.DoGetRequest(query);
        TripModel model = new ObjectMapper().readValue(result, TripModel.class);
        for (int i = 0; i < model.getTrips().length; i++) {
            Trip trip=model.getTrips()[i];
            trip.setDistance(trip.getDistance()/1000);
            trip.setDuration(trip.getDuration()/3600);
        }
        return model;
    }

    private String buildQuery(List<Point>points) {
        String servicePath = "/trip/v1/driving/";
        StringBuilder query = new StringBuilder(baseHost + servicePath);

        for (Point c:points)  {
                query.append(c.getX()).append(",").append(c.getY()).append(";");
        }
        query.replace(query.length()-1,query.length(),"");
        query.append("?source=first&destination=last");
        return query.toString();
    }
    public static void main(String[] args) throws IOException {
        List<Point> sources = Arrays.asList(new Point(-82.37049401, 23.10183774), new Point(-82.36918745,
                                                                                            23.10297582),
                                            new Point(-82.37100363, 23.10062515));
        TripService rs=new TripService("http://localhost:5000");
        TripModel trip = rs.getTrip(sources);
        System.out.println(trip);
        GPXWritter.writeXMLAsGPX(trip,new File("Gpx.gpx"));
    }
}
