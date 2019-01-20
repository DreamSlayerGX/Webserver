package util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpParser {

	public static String[] parseClientRequest(String input) {
		input = input.replace("/", " ");
		
		String[] output = input.split("\\s+");
		for (int i = 0; i < output.length; i++) {
			output[i] = output[i].trim();
			
		}
		
		return output;
	}
	
	public static String parseToJSON(ArrayList<String> toParse, String type) {
		JSONObject main = new JSONObject();
		JSONArray ja = new JSONArray();
		
		for (String s : toParse) {
			String[] parts = s.split(",");

			
			JSONObject unit = new JSONObject();
			for (int i = 0; i < parts.length; i+=2) {				
				String key = parts[i];
				String value = parts[i+1];
				unit.put(key, value);
				
				//System.out.println(key + " " + value);
			}
			
			ja.put(unit);
		}
		
		System.out.println(main.put(type, ja).toString());
		
		return main.put(type, ja).toString();
	}
	

}
