package util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import javafx.util.Pair;

public class DataBase {

	private HashMap<Integer, JSONObject> employees;
	private HashMap<Integer, JSONObject> cars;
	private HashMap<Integer, JSONObject> sales;
	
	public DataBase() {
		employees = new HashMap<>();
		cars = new HashMap<>();
		sales = new HashMap<>();
		
		setUpData();
	}

	public synchronized HashMap<Integer, JSONObject> getEmployees() {
		return employees;
	}

	public synchronized HashMap<Integer, JSONObject> getCars() {
		return cars;
	}

	//TODO: Fix
	public synchronized void addCar( HashMap<Integer, JSONObject> cars) {
		this.cars = cars;
	}

	public synchronized HashMap<Integer, JSONObject> getSales() {
		return sales;
	}

	//Performs the setup data for the server in the three data tables: 
	//employee, cars, and sales
	private void setUpData() {
		employees.put(1, new JSONObject().put("name", "Hjulia Styrén").put("id", 1));
		employees.put(2, new JSONObject().put("name", "Antonia Cylinder").put("id", 2));
		employees.put(3, new JSONObject().put("name", "Kalle Bromslöf").put("id", 3));
		employees.put(4, new JSONObject().put("name", "Johan Sportratt").put("id", 4));
		
	}
	
}
