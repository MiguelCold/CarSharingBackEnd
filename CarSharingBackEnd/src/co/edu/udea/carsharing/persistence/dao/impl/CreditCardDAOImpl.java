package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

import co.edu.udea.carsharing.model.entities.CreditCard;
import co.edu.udea.carsharing.persistence.conection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.ICreditCardDAO;

public class CreditCardDAOImpl implements ICreditCardDAO {

	private static ICreditCardDAO instance;
	private final DBCollection collection;

	private CreditCardDAOImpl() throws UnknownHostException {
		this.collection = MongoDBConnector.connect();
	}

	public static synchronized ICreditCardDAO getInstance()
			throws UnknownHostException {
		if (instance == null) {
			instance = new CreditCardDAOImpl();
		}
		return (instance);
	}

	@Override
	public CreditCard find(String type, String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreditCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(CreditCard creditCard) {
		BasicDBObject basicDBObject = (BasicDBObject) creditCard
				.entityToDBObject();
		WriteResult wr = this.collection.insert(basicDBObject);

		return (wr.getN() == 1);
	}

	@Override
	public CreditCard update(CreditCard creditCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(CreditCard creditCard) {
		// TODO Auto-generated method stub
		return false;
	}

}
