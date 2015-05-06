package co.edu.udea.carsharing.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IBrandDAO;
import co.edu.udea.carsharing.util.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.util.exception.CarSharingDAOException;
import co.edu.udea.carsharing.util.exception.CarSharingTechnicalException;

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

	private BrandDAOImpl() throws CarSharingTechnicalException {
		this.collection = MongoDBConnector.connect(BRANDS_COLLECTION_NAME);
	}

	public static synchronized IBrandDAO getInstance()
			throws CarSharingTechnicalException {
		if (null == instance) {
			instance = new BrandDAOImpl();
		}

		return instance;
	}

	@Override
	public List<Brand> findAll() throws CarSharingDAOException {
		try {
			List<Brand> brands = new ArrayList<Brand>();

			DBCursor dbCursor = this.collection.find();
			for (DBObject dbObject : dbCursor) {
				brands.add(Brand.entityFromDBObject(dbObject));
			}

			return brands;
		} catch (Exception e) {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: método: %s. Error mientras se obtenían todos objetos %s.\n%s",
							BrandDAOImpl.class.getSimpleName(), "findAll()",
							Brand.class.getSimpleName(), e));
		}
	}

	@Override
	public Brand insert(Brand brand) throws CarSharingDAOException,
			CarSharingBusinessException {
		try {
			if (brand != null) {
				BasicDBObject dbo = brand.entityToDBObject();
				WriteResult wr = this.collection.insert(dbo);

				ObjectId id = (ObjectId) dbo.get(ID);
				DBObject dbObject = collection.findOne(id);

				return (null == dbObject && wr.getN() == 0) ? null : Brand
						.entityFromDBObject(dbObject);
			} else {
				throw new CarSharingBusinessException(
						String.format(
								"Clase %s: método: %s. El parámetro brand de tipo %s no puede ser nulo.",
								BrandDAOImpl.class.getSimpleName(), "insert()",
								Brand.class.getSimpleName()));
			}
		} catch (Exception e) {
			throw new CarSharingDAOException(
					String.format(
							"Clase %s: Se presentó un error inesperado mientras se ejecutaba el método %s.\n%s",
							BrandDAOImpl.class.getSimpleName(), "insert()", e));
		}

	}
}