package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Car implements Serializable {

	private static final long serialVersionUID = 599539687173957722L;

	private static String COLOR = "color";
	private static String CARRIAGE_PLATE = "carriagePlate";
	private static String BRAND = "brand";
	private static String MODEL = "model";
	private static String CAPACITY = "capacity";

	private String color;
	private String carriagePlate;
	private Brand brand;
	private String model;
	private int capacity;

	public static Car entityFromDBObject(DBObject dbObject) {
		Car car = new Car();

		car.setColor((String) dbObject.get(COLOR));
		car.setCarriagePlate((String) dbObject.get(CARRIAGE_PLATE));
		car.setBrand(Brand.entityFromDBObject((DBObject) dbObject.get(BRAND)));
		car.setModel((String) dbObject.get(MODEL));
		car.setCapacity(Integer.parseInt((String) dbObject.get(CAPACITY)));

		return (car);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		basicDBObject.put(COLOR, this.getColor());
		basicDBObject.put(CARRIAGE_PLATE, this.getCarriagePlate());
		basicDBObject.put(BRAND, this.getModel());
		basicDBObject.put(MODEL, this.getModel());
		basicDBObject.put(CAPACITY, this.getCapacity());

		return (basicDBObject);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCarriagePlate() {
		return carriagePlate;
	}

	public void setCarriagePlate(String carriagePlate) {
		this.carriagePlate = carriagePlate;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
