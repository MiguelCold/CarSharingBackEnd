package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IUserDAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class UserDAOImpl implements IUserDAO {

	private static final String EMAIL = "email";
	private static final String USERS_COLLECTION_NAME = "Users";

	private static IUserDAO instance;
	private DBCollection collection;

	private UserDAOImpl() throws UnknownHostException {
		this.collection = MongoDBConnector.connect(USERS_COLLECTION_NAME);
	}

	public static synchronized IUserDAO getInstance()
			throws UnknownHostException {
		if (null == instance) {
			instance = new UserDAOImpl();
		}

		return instance;
	}

	@Override
	public User findByEmail(String email) {
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put(EMAIL, email);
		DBObject dbObject = this.collection.findOne(basicDBObject);

		if (null != dbObject) {

			return User.entityFromDBObject(dbObject);
		}

		return null;
	}

	@Override
	public User insert(User user) {

		return null;
	}

	@Override
	public User addCar(Car car) {

		return null;
	}

	@Override
	public List<Car> getCarsByUser(String email) {
		List<Car> cars = new ArrayList<Car>();

		return cars;
	}

}
