package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IBrandDAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class BrandDAOImpl implements IBrandDAO {

	private static String COLLECTION_NAME = "Brands";
	private static String ID = "_id";

	private static IBrandDAO instance;
	private DBCollection collection;

	private BrandDAOImpl() throws UnknownHostException {
		this.collection = MongoDBConnector.connect(COLLECTION_NAME);
	}

	public static synchronized IBrandDAO getInstance()
			throws UnknownHostException {
		if (null == instance) {
			instance = new BrandDAOImpl();
		}

		return (instance);
	}

	@Override
	public List<Brand> findAll() {
		List<Brand> brands = new ArrayList<Brand>();

		DBCursor dbCursor = this.collection.find();
		for (DBObject dbObject : dbCursor) {
			brands.add(Brand.entityFromDBObject(dbObject));
		}

		return brands;
	}

	@Override
	public Brand insert(Brand brand) {
		BasicDBObject dbo = brand.entityToDBObject();
		this.collection.insert(dbo);
		
		ObjectId id = (ObjectId) dbo.get(ID);
		DBObject dbObject = collection.findOne(id);
		
		return ((null == dbObject) ? null : Brand.entityFromDBObject(dbObject));
	}

}
