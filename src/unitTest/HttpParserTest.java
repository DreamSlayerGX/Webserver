package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import util.HttpParser;

class HttpParserTest {

	@Test
	public void parseClientRequestTest() {
		String[] result1 = HttpParser.parseClientRequest("GET / employees");
		String[] expected1 = { "GET", "employees" };

		assertEquals(expected1.length, result1.length);
		for (int i = 0; i < expected1.length; i++) {
			assertEquals(expected1[i], result1[i]);

		}

		String[] result2 = HttpParser.parseClientRequest("POST /carmodels Volvo V70");
		String[] expected2 = { "POST", "carmodels", "Volvo", "V70" };

		assertEquals(expected2.length, result2.length);
		for (int i = 0; i < expected1.length; i++) {
			assertEquals(expected2[i], result2[i]);

		}

	}
	
	@Test
	public void parseToJSONTest() {
		ArrayList<String> toParseEmployees = new ArrayList<>();
		toParseEmployees.add("id,1,name,Hjulia Styrén");
		toParseEmployees.add("id,2,name,Anton Cylinder");
		toParseEmployees.add("id,3,name,Kalle Bromslöf");
		toParseEmployees.add("id,4,name,Johan Sportratt");
		
		String result = HttpParser.parseToJSON(toParseEmployees, "employees");
		String expected = "{\"employees\":[{\"name\":\"Hjulia Styrén\","
				+ "\"id\":\"1\"},{\"name\":\"Anton Cylinder\","
				+ "\"id\":\"2\"},{\"name\":\"Kalle Bromslöf\","
				+ "\"id\":\"3\"},{\"name\":\"Johan Sportratt\","
				+ "\"id\":\"4\"}]}";
		
		assertEquals(expected, result);
		
		ArrayList<String> toParseCars = new ArrayList<>();
		toParseCars.add("id,"+1+",brand,BMW,model,335i,price,"+200000);
		toParseCars.add("id,"+2+",brand,Aston Martin,model,Vanquish,price,"+233000);
		
		String result2 = HttpParser.parseToJSON(toParseCars, "carmodels");
		JSONObject main = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject aston = new JSONObject();
		JSONObject bmw = new JSONObject();
		
		bmw.put("id", "1");
		bmw.put("brand", "BMW");
		bmw.put("model","335i");
		bmw.put("price","200000");
		
		ja.put(bmw);
		
		aston.put("id", "2");
		aston.put("brand", "Aston Martin");
		aston.put("model","Vanquish");
		aston.put("price","233000");
		
		ja.put(aston);
		System.out.println(main.put("carmodels", ja).toString());
		assertEquals(main.put("carmodels", ja).toString(), result2);
		
	}

}