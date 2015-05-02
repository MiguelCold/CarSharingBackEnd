package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Brand implements Serializable {

	private static final long serialVersionUID = -8444184090726568260L;

	private static final String BRAND = "brand";
	private static final String ID = "_id";

	private String id;
	private String brand;

	public Brand() {
		super();
	}

	public Brand(String brand) {
		super();
		this.brand = brand;
	}

	public static Brand entityFromDBObject(DBObject dbObject) {
		Brand brand = new Brand();

		if (dbObject.containsField(BRAND)) {
			brand.setBrand(((String) dbObject.get(BRAND)).trim());
		}

		if (dbObject.containsField(ID)) {
			brand.setId(dbObject.get(ID).toString().trim());
		}

		return (brand);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		if (null != this.getBrand() && !this.getBrand().trim().equals("")) {
			basicDBObject.put(BRAND, this.getBrand().trim());
		}

		if (null != this.getId() && !this.getId().trim().equals("")) {
			basicDBObject.put(ID, this.getId().trim());
		}

		return (basicDBObject);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
