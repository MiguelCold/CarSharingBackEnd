package co.edu.udea.carsharing.persistence.dao.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.connection.MongoDBConnector;
import co.edu.udea.carsharing.persistence.dao.IEventDAO;

public class EventDAOImpl implements IEventDAO {

	private static IEventDAO instance;
	private final DBCollection collection;
	public static final Map<String, String> INEQUALITIES = new HashMap<>();

	private static final String EVENTID = "_id";

	static {
		INEQUALITIES.put("<", "$lt");
		INEQUALITIES.put("<=", "$lte");
		INEQUALITIES.put(">", "$gt");
		INEQUALITIES.put(">=", "$gte");
		INEQUALITIES.put("!=", "$ne");
	}

	private EventDAOImpl() throws UnknownHostException {
		this.collection = MongoDBConnector.connect("Events");
	}

	public static synchronized IEventDAO getInstance()
			throws UnknownHostException {
		if (instance == null) {
			instance = new EventDAOImpl();
		}

		return (instance);
	}

	@Override
	public Event find(String eventId) {
		if (eventId != null && !eventId.equals("")) {
			BasicDBObject basicDBObject = new BasicDBObject();
			basicDBObject.put(EVENTID, eventId);
			DBCursor dbCursor = this.collection.find(basicDBObject);

			return (Event.entityFromDBObject(dbCursor.one()));
		} else {
			System.out.println("El parámetro eventId no puede ser Nulo");
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

		return (eventList);
	}

	@Override
	public Event insert(Event event) {
		if (event != null) {
			BasicDBObject basicDBObject = event.entityToDBObject();
			WriteResult wr = this.collection.insert(basicDBObject);
			// System.out.print(wr.toString());
		} else {
			System.out.println("El parámetro event no puede ser Nulo");
		}
		return (event);

	}

	@Override
	public Event insertComment(Comment newComment, String eventId) {
		if (eventId != null && !eventId.equals("") && newComment != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getComments().add(newComment);
				event = this.update(event);
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);
			}
			return event;
		} else {
			System.out.println("Los parámetros eventId y newComment deben "
					+ "ser diferentes de Nulo");
			return null;
		}
	}

	@Override
	public Event join(User newPartner, String eventId) {
		if (eventId != null && !eventId.equals("") && newPartner != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getPartners().add(newPartner);
				event = this.update(event);
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);
			}
			return event;
		} else {
			System.out.println("Los parámetros eventId y newPartner deben "
					+ "ser diferentes de Nulo");
			return null;
		}
	}

	@Override
	public Event update(Event event) {
		if (event != null) {
			BasicDBObject searchingBasicDBObject = new BasicDBObject(EVENTID,
					event.getId());
			BasicDBObject updatingBasicDBObject = new BasicDBObject("$set",
					event.entityToDBObject());

			WriteResult wr = this.collection.update(searchingBasicDBObject,
					updatingBasicDBObject, false, true);

			return ((wr.getN() != 0) ? event : null);
		} else {
			System.out.println("El parámetro event no puede ser Nulo");
			return null;
		}
	}

	@Override
	public Event leave(User leavingUser, String eventId) {
		if (eventId != null && !eventId.equals("") && leavingUser != null) {
			Event event = this.find(eventId);
			if (event != null) {
				event.getPartners().remove(leavingUser);
				event = this.update(event);
			} else {
				System.out.println("No se ha encontrado el evento con id: "
						+ eventId);
			}
			return event;
		} else {
			System.out.println("Los parámetros eventId y leavingUser deben "
					+ "ser diferentes de Nulo");
			return null;
		}
	}

	@Override
	public List<Event> cancel(String eventId) {
		if (eventId != null && !eventId.equals("")) {
			WriteResult wr = this.collection.remove(new BasicDBObject(EVENTID,
					eventId));
			return (this.findAll());
		} else {
			System.out.println("El parámetro eventId no puede ser Nulo");
			return (new ArrayList<Event>());
		}

	}
}
