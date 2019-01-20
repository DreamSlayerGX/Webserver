package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import util.HttpParser;
import util.ServerMonitor;

public class CarShopServer {

	public static final int PORT_NUMBER = 8888;
	public static final String IP = "localhost";

	public static void main(String[] args) throws IOException {
		new CarShopServer(IP, PORT_NUMBER);

	}

	private static final String EMPLOYEES = "employees";
	private static final String CARMODELS = "carmodels";
	private static final String TOTAL_SALES = "total_sales";

	private ServerSocket serverSocket;
	private Socket clientSocket;

	private ServerMonitor monitor;

	private String ip;
	private int port;

	public CarShopServer(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;

		monitor = new ServerMonitor();
		serverSocket = new ServerSocket(port);
		
		System.out.println("Starting");
		run();

	}

	public void run() {
		try {
			while (true) {
				clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				
				String input = in.readLine();

				if (input != null) {
					System.out.println("Input message: " + input);
					String[] parsedInput = HttpParser.parseClientRequest(input);
					
					switch (parsedInput[0].toUpperCase()) {
					case "GET":
						String section = parsedInput[1].toLowerCase();
						if (section.equals(EMPLOYEES))
							out.println(HttpParser.parseToJSON(monitor.getEmployees(), EMPLOYEES));
						else if (section.equals(CARMODELS))
							out.println(HttpParser.parseToJSON(monitor.getCars(), CARMODELS));
						else if (section.equals(TOTAL_SALES))
							out.println(getTotalSales());
						break;

					case "POST":
						putInDatabase(parsedInput);
						break;
						
					default:
						break;
					}

				}
				
				out.flush();
				out.close();
				in.close();
			}

		} catch (Exception e) {
			System.out.println("ERROR, shuting down!");
			e.printStackTrace();
			System.exit(1);
		}
	}


	private String getTotalSales() {
		
		
		
		return null;
	}

	private void putInDatabase(String[] parsedInput) {
		
		
	}
	
	
//	private String getCarModels() {
//		HashMap<Integer, String> cars = monitor.getCars();
//		if (cars.isEmpty())
//			return "{}";
//
//		ArrayList<String> toParse = new ArrayList<>();
//
//		for (int id : cars.keySet()) {
//			String value = cars.get(id);
//			value.replace(" ", ,)
//			toParse.add("id," + id + "," + value);
//		}
//
//		return HttpParser.parseToJSON(toParse, "carmodels");
//		
//		return null;
//	}
//
//
//	private String getEmployees() {
//		HashMap<Integer, JSONObject> employees = monitor.getEmployees();
//		if (employees.isEmpty())
//			return "{}";
//
//		ArrayList<String> toParse = new ArrayList<>();
//
//		for (int id : employees.keySet()) {
//			toParse.add("id," + id + ",name," + employees.get(id));
//		}
//
//		return HttpParser.parseToJSON(toParse, "employees");
//	}

}
