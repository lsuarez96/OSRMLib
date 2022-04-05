package osrm.services.table;

import com.fasterxml.jackson.databind.ObjectMapper;
import osrm.connection.Utils;
import osrm.model.Point;
import osrm.model.TableModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TableService {
    private final String baseHost;

    public TableService(String baseHost) {
        this.baseHost = baseHost;
    }

    public TableModel getCostMatrixLocations(
            List<Point> sources, List<Point> destinations,
            AnnotationTypes annotationTypes) throws IOException {
        String queryCoordinates = TableService.buildCoordinatesQueryLocations(sources, destinations);
        String duration_distance_annotation = "annotations=duration,distance";
        String duration_annotation = "annotations=duration";
        String distance_annotation = "annotations=distance";
        switch (annotationTypes) {
            case DISTANCE:
                queryCoordinates += "&" + distance_annotation;
                break;
            case DURATION:
                queryCoordinates += "&" + duration_annotation;
                break;
            case BOTH:
                queryCoordinates += "&" + duration_distance_annotation;
                break;
        }
        String servicePath = "/table/v1/driving/";
        String fullRequestURL = baseHost + servicePath + queryCoordinates;
        String result = Utils.DoGetRequest(fullRequestURL);
        TableModel tableModel = new ObjectMapper().readValue(result, TableModel.class);
        if (tableModel.getDurations() != null) {
            for (int i = 0; i < tableModel.getDurations().length; i++) {
                for (int j = 0; j < tableModel.getDurations()[i].length; j++) {
                    tableModel.getDurations()[i][j] /= 3600;
                }
            }
        }
        if (tableModel.getDistances() != null) {
            for (int i = 0; i < tableModel.getDistances().length; i++) {
                for (int j = 0; j < tableModel.getDistances()[i].length; j++) {
                    tableModel.getDistances()[i][j] /= 1000;
                }
            }
        }
        return tableModel;
    }

    private static String buildCoordinatesQueryLocations(List<Point> sources, List<Point> destinations) {
        StringBuilder sourcesParam = new StringBuilder();
        StringBuilder destinationsParam = new StringBuilder();
        StringBuilder query = new StringBuilder();
        int position = 0;
        for (Point l : sources) {
            query.append(l.getX()).append(",").append(l.getY()).append(";");
            sourcesParam.append(position).append(";");
            position++;
        }
        for (Point l : destinations) {
            query.append(l.getX()).append(",").append(l.getY()).append(";");
            destinationsParam.append(position).append(";");
            position++;
        }
        return query.substring(0, query.length() - 1) + "?sources=" +
                sourcesParam.substring(0, sourcesParam.length() - 1) + "&destinations=" +
                destinationsParam.substring(0, destinationsParam.length() - 1);
    }

    public enum AnnotationTypes {
        DURATION, DISTANCE, BOTH
    }

    public static void main(String[] args) throws IOException {
        TableService ts = new TableService("http://localhost:5000");
        List<Point> sources = Arrays.asList(new Point(-82.37049401, 23.10183774), new Point(-82.36918745,
                                                                                            23.10297582),
                                            new Point(-82.37100363, 23.10062515));
        List<Point> destinations = Arrays.asList(new Point(-82.37049401, 23.10183774),
                                                 new Point(-82.36918745, 23.10297582),
                                                 new Point(-82.37100363, 23.10062515));
        TableModel tm = ts.getCostMatrixLocations(sources, destinations, AnnotationTypes.DISTANCE);
        System.out.println(tm);
    }
}
