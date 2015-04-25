package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Andersson Garc&iacute;a Sotelo
 * @author Miguel &Aacute;ngel Ossa Ruiz
 * @author Juan Pablo Nore&ntilde;a
 * @author Juan Camilo Chaverra
 * 
 */
public class CreditCard implements Serializable {

	private static final long serialVersionUID = -7706807582765491066L;

	private static String TYPE = "type";
	private static String NUMBER = "number";

	private String type;
	private String number;

	public CreditCard() {
		super();
	}

	public CreditCard(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public static CreditCard entityFromDBObject(DBObject dbObject) {
		CreditCard creditCard = new CreditCard();
		if (dbObject.containsField("TYPE") && dbObject.containsField("NUMBER")) {
			creditCard.setType((String) dbObject.get(TYPE));
			creditCard.setNumber((String) dbObject.get(NUMBER));
		}
		return (creditCard);
	}

	public DBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();
		if (this.getType() != null && this.getNumber() != null) {
			basicDBObject.put(TYPE, this.getType());
			basicDBObject.put(NUMBER, this.getNumber());
		}
		return (basicDBObject);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
