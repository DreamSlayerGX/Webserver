package util;

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
		return parseToHTTP(main.put(type, ja).toString());
	}
	
	private static String parseToHTTP(String json) {
			String headerMain =	"HTTP/1.1 200 OK\r\n"; 
			String headerType =	"Content-Type: application/json; charset=utf-8\r\n";
			
			return headerMain + headerType + "\n" + json;
	}
	

}
