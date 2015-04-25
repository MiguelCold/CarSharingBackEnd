package co.edu.udea.carsharing.persistence.conection;

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

	public static DBCollection connect() throws UnknownHostException {
		mongoClient = new MongoClient(SERVER, PORT);
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DATA_BASE_NAME);
		DBCollection collection = db.getCollection(COLLECTION_NAME);
		if (collection == null) {
			collection = db.createCollection(COLLECTION_NAME, null);
		}
		return (collection);
	}
}
