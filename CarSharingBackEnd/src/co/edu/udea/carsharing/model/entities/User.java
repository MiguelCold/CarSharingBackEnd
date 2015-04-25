package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class User implements Serializable {

	private static final long serialVersionUID = 8155172105840619244L;

	private static String NAME = "name";
	private static String LAST_NAME = "lastName";
	private static String EMAIL = "email";
	private static String PASSWORD = "password";
	private static String FACEBOOK_ID = "facebookId";
	private static String CREDIT_CARDS = "creditCards";
	private static String BIRTHDATE = "birthDate";

	private String name;
	private String lastName;
	private String email;
	private String password;
	private String facebookId;
	private List<CreditCard> creditCards;
	private Date birthDate;

	public User() {
		super();
	}

	public User(String name, String lastName, String email, String password,
			Date birthDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
	}

	public User(String name, String lastName, String email, String password,
			String facebookId, List<CreditCard> creditcards, Date birthDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.facebookId = facebookId;
		this.creditCards = creditcards;
		this.birthDate = birthDate;
	}

	public static User entityFromDBObject(DBObject dbObject) {
		User user = new User();

		if (dbObject.containsField(NAME)) {
			user.setName((String) dbObject.get(NAME));
		}

		if (dbObject.containsField(LAST_NAME)) {
			user.setLastName((String) dbObject.get(LAST_NAME));
		}

		if (dbObject.containsField(EMAIL)) {
			user.setEmail((String) dbObject.get(EMAIL));
		}

		if (dbObject.containsField(PASSWORD)) {
			user.setPassword((String) dbObject.get(PASSWORD));
		}

		if (dbObject.containsField(FACEBOOK_ID)) {
			user.setFacebookId((String) dbObject.get(FACEBOOK_ID));
		}

		if (dbObject.containsField(BIRTHDATE)) {
			user.setBirthDate((Date) dbObject.get(BIRTHDATE));
		}

		if (dbObject.containsField(CREDIT_CARDS)) {
			BasicDBList basicDBbList = (BasicDBList) dbObject.get(CREDIT_CARDS);
			user.setCreditCards(new ArrayList<CreditCard>());
			for (Object object : basicDBbList) {
				user.getCreditCards().add(
						CreditCard.entityFromDBObject((BasicDBObject) object));
			}
		}

		return (user);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		if (null != this.getBirthDate()) {
			basicDBObject.put(BIRTHDATE, this.getBirthDate());
		}

		if (null != this.getEmail()) {
			basicDBObject.put(EMAIL, this.getEmail());
		}

		if (null != this.getFacebookId()) {
			basicDBObject.put(FACEBOOK_ID, this.getFacebookId());
		}

		if (null != this.getLastName()) {
			basicDBObject.put(LAST_NAME, this.getLastName());
		}

		if (null != this.getPassword()) {
			basicDBObject.put(PASSWORD, this.getPassword());
		}

		if (null != this.getName()) {
			basicDBObject.put(NAME, this.getName());
		}

		if (null != this.getCreditCards()) {
			BasicDBList basicDBList = new BasicDBList();

			for (CreditCard creditCard : this.getCreditCards()) {
				basicDBList.add(creditCard.entityToDBObject());
			}

			basicDBObject.put(CREDIT_CARDS, basicDBList);
		}

		return (basicDBObject);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
