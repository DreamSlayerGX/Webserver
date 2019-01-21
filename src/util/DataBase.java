package util;

import java.util.HashMap;

import org.json.JSONObject;

public class DataBase {

	private HashMap<Integer, JSONObject> employees;
	private HashMap<Integer, JSONObject> cars;
	private HashMap<Integer, JSONObject> sales;
	
	public DataBase(boolean testing) {
		employees = new HashMap<>();
		cars = new HashMap<>();
		sales = new HashMap<>();
		
		if(testing)
			setUpData();
	}

	/**
	 * 
	 * @return the employee list
	 */
	
	public HashMap<Integer, JSONObject> getEmployees() {
		return employees;
	}

	/**
	 * 
	 * @return the data for the cars.
	 */
	public HashMap<Integer, JSONObject> getCars() {
		return cars;
	}

	/**
	 * Adds a car to the database.
	 * 
	 * @param id associated with the car
	 * @param json the JSON data of the car
	 * @return false if the id is already occupied, otherwise true
	 */
	public boolean addCar(int id, JSONObject json) {
		if(id < 0 || cars.containsKey(id))
			return false;
		
		cars.put(id, json);
		return true;
	}

	/**
	 * Adds all the sales in the sales database with the sales person.
	 * 
	 * @return a complete map of employees and their sales figure.
	 */
	public HashMap<Integer, JSONObject> getSales() {
		HashMap<Integer, JSONObject> salesList = new HashMap<>();
		HashMap<Integer, Integer> carPrices = new HashMap<>();
		
		for(int carID : cars.keySet()) {
			int carPrice = cars.get(carID).getInt("price");
			carPrices.put(carID, carPrice);
		}
		
		float[] employeeSales = new float[employees.size()];
		
		for (int saleID : sales.keySet()) {
			int employeeID = sales.get(saleID).getInt("employee_id");
			int carID = sales.get(saleID).getInt("carmodel_id");
			
			employeeSales[employeeID-1] += carPrices.get(carID);
			
		}
		
		for (int employeeID : employees.keySet()) {
			salesList.put(employeeID, employees.get(employeeID).
					put("sales", employeeSales[employeeID-1]));
			
		}
		
		return salesList;
	}

	
	
	//Performs the setup data for the server in the employees, cars, and sales.
	//Used for testing.
	private void setUpData() {
		employees.put(1, new JSONObject().put("name", "Hjulia Styrén").put("id", 1));
		employees.put(2, new JSONObject().put("name", "Antonia Cylinder").put("id", 2));
		employees.put(3, new JSONObject().put("name", "Kalle Bromslöf").put("id", 3));
		employees.put(4, new JSONObject().put("name", "Johan Sportratt").put("id", 4));
		
		cars.put(1, new JSONObject().put("id", 1).put("brand", "BMW").put("model", "335i").put("price", 200000));
		cars.put(2, new JSONObject().put("id", 2).put("brand", "Aston Martin").put("model", "Vanquish").put("price", 233000));
		cars.put(3, new JSONObject().put("id", 3).put("brand", "Toyota").put("model", "Prius").put("price", 150000));
		cars.put(4, new JSONObject().put("id", 4).put("brand", "Volvo").put("model", "240").put("price", 100000));
		
		sales.put(1, new JSONObject().put("id",1).put("employee_id",2).put("carmodel_id",3));
		sales.put(2, new JSONObject().put("id",2).put("employee_id",4).put("carmodel_id",2));
		sales.put(3, new JSONObject().put("id",3).put("employee_id",4).put("carmodel_id",4));
		sales.put(4, new JSONObject().put("id",4).put("employee_id",1).put("carmodel_id",1));
		sales.put(5, new JSONObject().put("id",5).put("employee_id",3).put("carmodel_id",1));
		sales.put(6, new JSONObject().put("id",6).put("employee_id",3).put("carmodel_id",1));
		sales.put(7, new JSONObject().put("id",7).put("employee_id",2).put("carmodel_id",2));
		sales.put(8, new JSONObject().put("id",8).put("employee_id",2).put("carmodel_id",3));
	}
	
}
