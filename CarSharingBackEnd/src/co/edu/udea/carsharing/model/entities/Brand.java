package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Brand implements Serializable {

	private static final long serialVersionUID = -8444184090726568260L;

	private static final String BRAND = "brand";

	private String brand;

	public Brand() {
		super();
	}

	public Brand(String brand) {
		super();
		this.brand = brand;
	}

	public static Brand entityFromDBObject(DBObject dbObject) {
		Brand brand = new Brand((String) dbObject.get(BRAND));

		return (brand);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		basicDBObject.put(BRAND, this.getBrand());

		return (basicDBObject);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
