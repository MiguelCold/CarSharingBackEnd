package co.edu.udea.carsharing.persistence.connection;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBConnector {

	public static String SERVER = "localhost";
	public static int PORT = 27017;
	public static String DATA_BASE_NAME = "car_sharingDB";
	public static String COLLECTION_NAME = "Users";
	private static MongoClient mongoClient;

	private MongoDBConnector() {
		super();
	}

	public static DBCollection connect(String collectionName) throws UnknownHostException {
		mongoClient = new MongoClient(SERVER, PORT);
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DATA_BASE_NAME);
		DBCollection collection = db.getCollection(collectionName);
		if (collection == null) {
			collection = db.createCollection(collectionName, null);
		}
		return (collection);
	}
}