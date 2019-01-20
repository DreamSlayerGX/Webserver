package util;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class ServerMonitor {

	private HashMap<Integer, String> employees;
	private HashMap<Integer, String> cars;
	private HashMap<Integer, String> sales;
	
	public ServerMonitor() {
		employees = new HashMap<>();
		cars = new HashMap<>();
		sales = new HashMap<>();
		
		setUpData();
	}

	public synchronized HashMap<Integer, String> getEmployees() {
		return employees;
	}

	public synchronized HashMap<Integer, String> getCars() {
		return cars;
	}

	//TODO: Fix
	public synchronized void addCar( HashMap<Integer, String> cars) {
		this.cars = cars;
	}

	public synchronized HashMap<Integer, String> getSales() {
		return sales;
	}

	//Performs the setup data for the server in the three data tables: 
	//employee, cars, and sales
	private void setUpData() {
		employees.put(1, "Hjulia Styrén");
		employees.put(2, "Antonia Cylinder");
		employees.put(3, "Kalle Bromslöf");
		employees.put(4, "Johan Sportratt");
		
	}
	
}
