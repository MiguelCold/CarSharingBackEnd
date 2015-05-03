package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IEventDAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class EventDAOImpl implements IEventDAO {

	private static IEventDAO instance;

	private final DBCollection collection;

	private static final String EVENTS_COLLECTION_NAME = "Events";
	private static final String ID = "_id";
	public static final Map<String, String> INEQUALITIES = new HashMap<>();

	static {
		INEQUALITIES.put("<", "$lt");
		INEQUALITIES.put("<=", "$lte");
		INEQUALITIES.put(">", "$gt");
		INEQUALITIES.put(">=", "$gte");
		INEQUALITIES.put("!=", "$ne");
	}

	private EventDAOImpl() throws UnknownHostException {
		this.collection = MongoDBConnector.connect(EVENTS_COLLECTION_NAME);
	}

	public static synchronized IEventDAO getInstance()
			throws UnknownHostException {
		if (instance == null) {
			instance = new EventDAOImpl();
		}

		return instance;
	}

	@Override
	public Event find(String eventId) {
		if (eventId != null && !("").equals(eventId.trim())) {
			DBObject dbo;
			try {
				dbo = new BasicDBObject(ID, new ObjectId(eventId));
			} catch (Exception e) {

				return null;
			}
			DBObject dbObject = this.collection.findOne(dbo);

			return Event.entityFromDBObject(dbObject);
		} else {

			return null;
		}
	}

	@Override
	public List<Event> findAll() {
		List<Event> eventList = new ArrayList<>();
		DBCursor dbCursor = this.collection.find();

		for (DBObject dbo : dbCursor) {
			eventList.add(Event.entityFromDBObject(dbo));
		}

		return eventList;
	}

	@Override
	public Event insert(Event event) {
		if (event != null) {
			BasicDBObject basicDBObject = event.entityToDBObject();
			WriteResult wr = this.collection.insert(basicDBObject);

			ObjectId id = (ObjectId) basicDBObject.get(ID);
			DBObject dbObject = collection.findOne(id);

			return (null != dbObject && wr.getN() == 0) ? Event
					.entityFromDBObject(dbObject) : null;
		} else {
			System.out.println("El par·metro event no puede ser Nulo");

			return null;
		}

	}

	@Override
	public Event insertComment(Comment newComment, String eventId) {
		if (eventId != null && !("").equals(eventId.trim())
				&& newComment != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getComments().add(newComment);
				event = this.update(event);

				return event;
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);

				return null;
			}
		} else {
			System.out.println("Los par·metros eventId y newComment deben "
					+ "ser diferentes de Nulo");
			return null;
		}
	}

	@Override
	public Event join(User newPartner, String eventId) {
		if (eventId != null && !("").equals(eventId.trim())
				&& newPartner != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getPartners().add(newPartner);
				event = this.update(event);

				return event;
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);

				return null;
			}
		} else {
			System.out.println("Los par·metros eventId y newPartner deben "
					+ "ser diferentes de Nulo");

			return null;
		}
	}

	@Override
	public Event update(Event event) {
		if (event != null) {
			BasicDBObject searchingBasicDBObject = new BasicDBObject(ID,
					new ObjectId(event.getId()));
			BasicDBObject updatingBasicDBObject = new BasicDBObject("$set",
					event.entityToDBObject());

			WriteResult wr = this.collection.update(searchingBasicDBObject,
					updatingBasicDBObject, false, true);

			return (wr.getN() != 0) ? event : null;
		} else {
			System.out.println("El par·metro event no puede ser Nulo");

			return null;
		}
	}

	@Override
	public Event leave(User leavingUser, String eventId) {
		if (eventId != null && !("").equals(eventId.trim())
				&& leavingUser != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getPartners().remove(leavingUser);
				event = this.update(event);

				return event;
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);

				return null;
			}
		} else {
			System.out.println("Los par·metros eventId y leavingUser deben "
					+ "ser diferentes de Nulo");

			return null;
		}
	}

	@Override
	public List<Event> cancel(String eventId) {
		if (eventId != null && !("").equals(eventId.trim())) {
			this.collection.remove(new BasicDBObject(ID, eventId));

			return this.findAll();
		} else {
			System.out.println("El par√°metro eventId no puede ser Nulo");

			return new ArrayList<Event>();
		}

	}
}
