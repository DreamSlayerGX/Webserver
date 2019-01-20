package util;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public static String parseToJSON(HashMap<Integer, JSONObject> toParse, String type) {
		if(toParse.isEmpty())
			return "{}";
		
		JSONObject main = new JSONObject();
		JSONArray ja = new JSONArray();
		
		for (int id : toParse.keySet()) {
			ja.put(toParse.get(id));
		}
		
		//System.out.println(main.put(type, ja).toString());
		
		return main.put(type, ja).toString();
	}
	

}
