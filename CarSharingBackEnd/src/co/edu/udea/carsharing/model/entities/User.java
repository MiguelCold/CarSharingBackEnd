package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class User implements Serializable {

	private static final long serialVersionUID = 8155172105840619244L;

	private static String NAME = "name";
	private static String LAST_NAME = "lastName";
	private static String EMAIL = "email";
	private static String PASSWORD = "password";
	private static String FACEBOOK_ID = "facebookId";
	private static String CARS = "cars";
	private static String BIRTHDATE = "birthDate";

	private String name;
	private String lastName;
	private String email;
	private String password;
	private String facebookId;
	private Date birthDate;
	private List<Car> cars;

	public User() {
		super();
	}

	public User(String name, String lastName, String email, String password,
			Date birthDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	public User(String name, String lastName, String email, String password,
			String facebookId, Date birthDate, List<Car> cars) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.facebookId = facebookId;
		this.birthDate = birthDate;
		this.cars = cars;
	}

	public static User entityFromDBObject(DBObject dbObject) {
		User user = new User();

		user.setName((String) dbObject.get(NAME));
		user.setLastName((String) dbObject.get(LAST_NAME));
		user.setEmail((String) dbObject.get(EMAIL));
		user.setPassword((String) dbObject.get(PASSWORD));
		user.setFacebookId((String) dbObject.get(FACEBOOK_ID));
		user.setBirthDate((Date) dbObject.get(BIRTHDATE));

		if (dbObject.containsField(CARS)) {
			BasicDBList basicDBbList = (BasicDBList) dbObject.get(CARS);
			user.setCars(new ArrayList<Car>());
			for (Object object : basicDBbList) {
				user.getCars().add(
						Car.entityFromDBObject((BasicDBObject) object));
			}
		}

		return (user);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		basicDBObject.put(BIRTHDATE, this.getBirthDate());
		basicDBObject.put(EMAIL, this.getEmail());
		basicDBObject.put(FACEBOOK_ID, this.getFacebookId());
		basicDBObject.put(LAST_NAME, this.getLastName());
		basicDBObject.put(PASSWORD, this.getPassword());
		basicDBObject.put(NAME, this.getName());

		if (null != this.getCars()) {
			BasicDBList basicDBList = new BasicDBList();
			for (Car car : this.getCars()) {
				basicDBList.add(car.entityToDBObject());
			}

			basicDBObject.put(CARS, basicDBList);
		}

		return (basicDBObject);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
