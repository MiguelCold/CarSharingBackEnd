package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IBrandDAO;
import co.edu.udea.carsharing.persistence.dao.exception.CarSharingDAOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class BrandDAOImpl implements IBrandDAO {

	private static final String BRANDS_COLLECTION_NAME = "Brands";
	private static final String ID = "_id";

	private static IBrandDAO instance;
	private DBCollection collection;

	private BrandDAOImpl() throws CarSharingDAOException, UnknownHostException {
		this.collection = MongoDBConnector.connect(BRANDS_COLLECTION_NAME);
	}

	public static synchronized IBrandDAO getInstance()
			throws UnknownHostException, CarSharingDAOException {
		if (null == instance) {
			instance = new BrandDAOImpl();
		}

		return instance;
	}

	@Override
	public List<Brand> findAll() throws CarSharingDAOException {
		List<Brand> brands = new ArrayList<Brand>();

		DBCursor dbCursor = this.collection.find();
		for (DBObject dbObject : dbCursor) {
			brands.add(Brand.entityFromDBObject(dbObject));
		}

		return brands;
	}

	@Override
	public Brand insert(Brand brand) throws CarSharingDAOException {
		if (brand != null) {
			BasicDBObject dbo = brand.entityToDBObject();
			WriteResult wr = this.collection.insert(dbo);

			ObjectId id = (ObjectId) dbo.get(ID);
			DBObject dbObject = collection.findOne(id);

			return (null == dbObject && wr.getN() == 0) ? null : Brand
					.entityFromDBObject(dbObject);
		} else {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: método: %s. El parámetro brand de tipo %s no puede ser nulo.",
							BrandDAOImpl.class.getSimpleName(), "insert",
							Brand.class.getSimpleName()));
		}
	}

}
