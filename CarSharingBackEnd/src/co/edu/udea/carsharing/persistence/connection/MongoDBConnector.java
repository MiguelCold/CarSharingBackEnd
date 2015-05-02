package co.edu.udea.carsharing.persistence.connection;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBConnector {

	public static String SERVER = "mongodb://admin:admin@ds031982.mongolab.com:31982/heroku_app36463312";
	public static String DATA_BASE_NAME = "heroku_app36463312";
	private static MongoClient mongoClient;
	private static MongoClientURI mongoClientURI;

	private MongoDBConnector() {
		super();
	}

	public static DBCollection connect(String collectionName)
			throws UnknownHostException {
		mongoClientURI = new MongoClientURI(SERVER);
		mongoClient = new MongoClient(mongoClientURI);

		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(DATA_BASE_NAME);
		DBCollection collection = db.getCollection(collectionName);
		if (collection == null) {
			collection = db.createCollection(collectionName, null);
		}
		return (collection);
	}
}