package osrm.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    
	public static String DoGetRequest(String source) throws IOException {
		String dataS = null;
		URL url = new URL(source);
		HttpURLConnection serviceRequest = (HttpURLConnection) url.openConnection();
		serviceRequest.setRequestMethod("GET");
		serviceRequest.setReadTimeout(100000000);
		BufferedReader in = new BufferedReader(new InputStreamReader(serviceRequest.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		dataS = response.toString();
		serviceRequest.disconnect();

		return dataS;
	}
}
