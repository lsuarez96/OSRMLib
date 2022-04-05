package osrm.utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import osrm.model.Poi;
import osrm.model.RouteModel;
import osrm.model.TripModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GPXWritter {

    public static void writeXMLAsGPX(RouteModel code, File file) {
        Document document = new Document();
        Element root = new Element("gpx");
        root.setAttribute("version", "1.1");
        Element trk = new Element("trk");
        List<Poi> route = Arrays.asList(code.getWaypoints());
        int orderId = 1;
        for (Poi poi : route) {
            Element wpt = new Element("wpt");
            wpt.setAttribute("lat", String.valueOf(poi.getLocation()[1]));
            wpt.setAttribute("lon", String.valueOf(poi.getLocation()[0]));
            Element ele = new Element("ele");
            ele.setText(String.valueOf(0.0));
            wpt.addContent(ele);
            Element name = new Element("name");
            //String.valueOf(poi.getIdentifier()) +
            name.setText(" (" + orderId + ")");
            wpt.addContent(name);
            Element order = new Element("order");
            order.setText(orderId + "");
            orderId++;
            root.addContent(wpt);
        }
        root.addContent(trk);
        Element trkSeg = new Element("trkseg");
        trk.addContent(trkSeg);
        for (double[] location : code.getRoutes()[0].getGeometry().getCoordinates()) {
              Element trkpt = new Element("trkpt");
            trkpt.setAttribute("lat", String.valueOf(location[1]));
            trkpt.setAttribute("lon", String.valueOf(location[0]));
            Element ele = new Element("ele");
            ele.setText("0.0");
            trkpt.addContent(ele);
            trkSeg.addContent(trkpt);
        }
        document.setRootElement(root);
        Format format = Format.getPrettyFormat();
        XMLOutputter xmlOutputter = new XMLOutputter(format);
        String data = xmlOutputter.outputString(document);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(data);
        } catch (FileNotFoundException e1) {
            System.out.println("Failed to write as xml");
            e1.printStackTrace();
        }
    }

    public static void writeXMLAsGPX(TripModel code, File file) {
        Document document = new Document();
        Element root = new Element("gpx");
        root.setAttribute("version", "1.1");
        Element trk = new Element("trk");
        List<Poi> route = Arrays.asList(code.getWaypoints());
        int orderId = 1;
        for (Poi poi : route) {
            Element wpt = new Element("wpt");
            wpt.setAttribute("lat", String.valueOf(poi.getLocation()[1]));
            wpt.setAttribute("lon", String.valueOf(poi.getLocation()[0]));
            Element ele = new Element("ele");
            ele.setText(String.valueOf(0.0));
            wpt.addContent(ele);
            Element name = new Element("name");
            //String.valueOf(poi.getIdentifier()) +
            name.setText(" (" + orderId + ")");
            wpt.addContent(name);
            Element order = new Element("order");
            order.setText(orderId + "");
            orderId++;
            root.addContent(wpt);
        }
        root.addContent(trk);
        Element trkSeg = new Element("trkseg");
        trk.addContent(trkSeg);
        for (double[] location : code.getTrips()[0].getGeometry().getCoordinates()) {
            Element trkpt = new Element("trkpt");
            trkpt.setAttribute("lat", String.valueOf(location[1]));
            trkpt.setAttribute("lon", String.valueOf(location[0]));
            Element ele = new Element("ele");
            ele.setText("0.0");
            trkpt.addContent(ele);
            trkSeg.addContent(trkpt);
        }
        document.setRootElement(root);
        Format format = Format.getPrettyFormat();
        XMLOutputter xmlOutputter = new XMLOutputter(format);
        String data = xmlOutputter.outputString(document);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(data);
        } catch (FileNotFoundException e1) {
            System.out.println("Failed to write as xml");
            e1.printStackTrace();
        }
    }
}