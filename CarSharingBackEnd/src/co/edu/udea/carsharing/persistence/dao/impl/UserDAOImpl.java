package co.edu.udea.carsharing.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IUserDAO;
import co.edu.udea.carsharing.persistence.dao.exception.CarSharingDAOException;
import co.edu.udea.carsharing.technical.exception.CarSharingTechnicalException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class UserDAOImpl implements IUserDAO {

	private static final String ID = "_id";
	private static final String EMAIL = "email";
	private static final String CARS = "cars";
	private static final String PASSWORD = "password";
	private static final String USERS_COLLECTION_NAME = "Users";

	private static IUserDAO instance;
	private DBCollection collection;

	private UserDAOImpl() throws CarSharingTechnicalException {
		this.collection = MongoDBConnector.connect(USERS_COLLECTION_NAME);
	}

	public static synchronized IUserDAO getInstance()
			throws CarSharingTechnicalException {
		if (null == instance) {
			instance = new UserDAOImpl();
		}

		return instance;
	}

	@Override
	public User findByEmailAndPassword(String email, String password)
			throws CarSharingDAOException, CarSharingBusinessException {
		try {
			if (null == email || ("").equals(email.trim()) || null == password
					|| ("").equals(password.trim())) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. Los parámetros email o password (ambos de tipo %s) "
								+ "no pueden ser ni nulos ni vacíos.",
						UserDAOImpl.class.getSimpleName(), "findByEmail",
						String.class.getSimpleName()));
			} else {
				BasicDBObject basicDBObject = new BasicDBObject();
				basicDBObject.put(EMAIL, email);
				basicDBObject.put(PASSWORD, password);
				DBObject dbObject = this.collection.findOne(basicDBObject);

				return (dbObject == null) ? null : User
						.entityFromDBObject(dbObject);
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: método %s. Se presentó un error inesperado.\n%s",
							UserDAOImpl.class.getSimpleName(),
							"findByEmailAndPassword", e));
		}
	}

	@Override
	public User insert(User user) throws CarSharingDAOException,
			CarSharingBusinessException {
		try {
			if (user != null) {
				BasicDBObject basicDBObject = user.entityToDBObject();
				WriteResult wr = this.collection.insert(basicDBObject);

				ObjectId id = (ObjectId) basicDBObject.get(ID);
				DBObject dbObject = collection.findOne(id);

				return (dbObject != null && wr.getN() == 0) ? User
						.entityFromDBObject(dbObject) : null;
			} else {
				throw new CarSharingBusinessException(
						String.format(
								"Clase %s: método %s. El objeto user de tipo %s no puede ser nulo.",
								UserDAOImpl.class.getSimpleName(), "insert()",
								User.class.getSimpleName()));
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: método %s. Se presentó un error inesperdado.\n%s",
							UserDAOImpl.class.getSimpleName(), "insert()", e));
		}
	}

	@Override
	public User findByEmail(String email) throws CarSharingDAOException,
			CarSharingBusinessException {
		try {
			if (null == email || ("").equals(email.trim())) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. El parámetro email de tipo %s "
								+ "no puede ser ni nulo ni vacío.",
						UserDAOImpl.class.getSimpleName(), "findByEmail",
						String.class.getSimpleName()));
			} else {
				BasicDBObject basicDBObject = new BasicDBObject();
				basicDBObject.put(EMAIL, email);
				DBObject dbObject = this.collection.findOne(basicDBObject);

				return (dbObject == null) ? null : User
						.entityFromDBObject(dbObject);
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: método %s. Se presentó un error inesperado.\n%s",
							UserDAOImpl.class.getSimpleName(), "findByEmail", e));
		}
	}

	@Override
	public User addCar(String email, Car car) throws CarSharingDAOException,
			CarSharingBusinessException {
		if (null == email || email.trim().isEmpty() || null == car) {
			throw new CarSharingBusinessException(String.format(
					"Clase: %s, método %s. El parámetro email (%s) no puede ser ni nulo ni vacío, "
							+ "y el parámemtro car (%s) no puede ser nulo.",
					UserDAOImpl.class.getSimpleName(), "addCar()",
					String.class.getSimpleName(), Car.class.getSimpleName()));
		} else {
			User user = this.findByEmail(email);

			if (user.getCars() == null) {
				user.setCars(new ArrayList<Car>());
			}

			List<Car> cars = user.getCars();
			cars.add(car);
			user.setCars(cars);

			return this.update(user);
		}
	}

	private User update(User user) throws CarSharingDAOException {
		try {
			if (user == null) {
				throw new CarSharingBusinessException(
						String.format(
								"Clase: %s, método %s. El parámetro user, de tipo %s, no puede ser nulo.",
								UserDAOImpl.class.getSimpleName(), "update()",
								User.class.getSimpleName()));
			} else {
				BasicDBObject searchingBasicDBObject = new BasicDBObject(ID,
						new ObjectId(user.getId()));
				BasicDBObject updatingBasicDBObject = new BasicDBObject("$set",
						user.entityToDBObject());

				WriteResult wr = this.collection.update(searchingBasicDBObject,
						updatingBasicDBObject, false, true);

				return (wr.getN() != 0) ? user : null;
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(String.format(
					"Clase %s: método %s. Se presentó un error inesperado "
							+ "al tratar de actualizar un usuario.\n%s",
					UserDAOImpl.class.getSimpleName(), "update()", e));
		}
	}

	@Override
	public List<Car> getCarsByUser(String email) throws CarSharingDAOException {
		try {
			if (null == email || email.trim().isEmpty()) {
				throw new CarSharingBusinessException(
						String.format(
								"Clase %s: método %s. El parámetro email de tipo %s no puede ser ni nulo ni vacío.",
								UserDAOImpl.class.getSimpleName(),
								"getCarsByUser", String.class.getSimpleName()));
			} else {
				List<Car> cars = new ArrayList<Car>();
				DBObject projection = new BasicDBObject(CARS, 1);
				projection.put(ID, 0);
				DBObject query = new BasicDBObject(EMAIL, email);

				DBCursor dbCursor = this.collection.find(query, projection);
				DBObject dbObject = dbCursor.one();

				if (dbObject != null && dbObject.containsField(CARS)) {

					BasicDBList basicDBList = (BasicDBList) dbObject.get(CARS);
					for (Object o : basicDBList) {
						cars.add(Car.entityFromDBObject((DBObject) o));
					}
				}

				return cars;
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(String.format(
					"Clase %s: método %s. Se generó un error al tratar de obtener "
							+ "el listado de carros de un usuario.\n%s",
					UserDAOImpl.class.getSimpleName(), "getCarsByUser", e));
		}
	}
}
