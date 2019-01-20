package unitTest;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import util.HttpParser;

class HttpParserTest {

	@Test
	public void parseClientRequestTest() {
		String[] result1 = HttpParser.parseClientRequest("GET / employees");
		String[] requested1 = { "GET", "employees" };

		assertEquals(requested1.length, result1.length);
		for (int i = 0; i < requested1.length; i++) {
			assertEquals(requested1[i], result1[i]);

		}

		String[] result2 = HttpParser.parseClientRequest("POST /carmodels Volvo V70");
		String[] requested2 = { "POST", "carmodels", "Volvo", "V70" };

		assertEquals(requested2.length, result2.length);
		for (int i = 0; i < requested1.length; i++) {
			assertEquals(requested2[i], result2[i]);

		}

//		String[] result3 = HttpParser.parseClientRequest("/carmodels Volvo V70   ");
//		assertNull(null, result3);

	}

}