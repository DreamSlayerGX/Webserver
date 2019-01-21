package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import util.HttpParser;

class HttpParserTest {

	@Test
	public void parseClientRequestTest() {
		

	}
	
	@Test
	public void parseToJSONTest() {
		HashMap<Integer, JSONObject> toParseEmployees = new HashMap<>();
		toParseEmployees.put(1, new JSONObject().put("name", "Hjulia Styrén").put("id", 1));
		toParseEmployees.put(2, new JSONObject().put("name", "Antonia Cylinder").put("id", 2));
		toParseEmployees.put(3, new JSONObject().put("name", "Kalle Bromslöf").put("id", 3));
		toParseEmployees.put(4, new JSONObject().put("name", "Johan Sportratt").put("id", 4));
		
		String result = HttpParser.parseToJSON(toParseEmployees, "employees");
		JSONObject emp1 = new JSONObject().put("id", 1).put("name", "Hjulia Styrén");
		JSONObject emp2 = new JSONObject().put("id", 2).put("name", "Antonia Cylinder");
		JSONObject emp3 = new JSONObject().put("id", 3).put("name", "Kalle Bromslöf");
		JSONObject emp4 = new JSONObject().put("id", 4).put("name", "Johan Sportratt");
		
		JSONArray ja1 = new JSONArray().put(emp1).put(emp2).put(emp3).put(emp4);
		JSONObject main1 = new JSONObject().put("employees", ja1);
		
		String expected = main1.toString();


		assertEquals(expected, result);
		
		HashMap<Integer, JSONObject> toParseCars = new HashMap<>();
		toParseCars.put(1, new JSONObject().put("id", 1).
				put("brand", "BMW").put("model","335i").put("price",200000));
		toParseCars.put(2, new JSONObject().put("id", 2).
				put("brand", "Aston Martin").put("model","Vanquish").put("price",233000));
		
		String result2 = HttpParser.parseToJSON(toParseCars, "carmodels");
		JSONObject main = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject aston = new JSONObject();
		JSONObject bmw = new JSONObject();
		
		bmw.put("id", 1);
		bmw.put("brand", "BMW");
		bmw.put("model","335i");
		bmw.put("price",200000);
		
		ja.put(bmw);
		
		aston.put("id", 2);
		aston.put("brand", "Aston Martin");
		aston.put("model","Vanquish");
		aston.put("price",233000);
		
		ja.put(aston);
		
		assertEquals(main.put("carmodels", ja).toString(), result2);
		
		assertEquals(
				HttpParser.parseToJSON(new HashMap<Integer, JSONObject>(), "empty"),
				"{}");
		
	}

}