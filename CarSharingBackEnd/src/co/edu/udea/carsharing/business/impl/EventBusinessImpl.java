package co.edu.udea.carsharing.business.impl;

import java.util.List;

import co.edu.udea.carsharing.business.IEventBusiness;
import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.dao.impl.EventDAOImpl;

public class EventBusinessImpl implements IEventBusiness {

	private static IEventBusiness instance;

	private EventBusinessImpl() {
		super();
	}

	public static synchronized IEventBusiness getInstance() {
		if (instance == null) {
			instance = new EventBusinessImpl();
		}
		return instance;
	}

	@Override
	public Event find(String eventId) throws CarSharingBusinessException {
		try {
			if (null == eventId || eventId.trim().isEmpty()) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. El parámetro eventId,"
								+ " no puede ser ni nulo ni vacío.",
						EventBusinessImpl.class.getSimpleName(), "find()",
						String.class.getSimpleName()));
			} else {

				return EventDAOImpl.getInstance().find(eventId);
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de buscar "
							+ "un evento por el id.\n%s",
					EventBusinessImpl.class.getSimpleName(), "find()", e));
		}
	}

	@Override
	public List<Event> findAll() throws CarSharingBusinessException {
		try {
			return EventDAOImpl.getInstance().findAll();
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de buscar "
							+ "una lista de eventos.\n%s",
					EventBusinessImpl.class.getSimpleName(), "findAll()", e));
		}
	}

	@Override
	public Event insert(Event event) throws CarSharingBusinessException {
		try {
			if (null == event) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. El parámetro event,"
								+ " no puede ser nulo.",
						EventBusinessImpl.class.getSimpleName(), "insert()",
						String.class.getSimpleName()));
			} else {
				if (event.getId() == null || event.getId().trim().isEmpty()) {
					return EventDAOImpl.getInstance().insert(event);
				}
				return event;
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de insertar "
							+ "un evento.\n%s",
					EventBusinessImpl.class.getSimpleName(), "insert()", e));
		}
	}

	@Override
	public Event insertComment(Comment newComment, String eventId)
			throws CarSharingBusinessException {
		try {
			if (null == newComment || eventId == null
					|| eventId.trim().isEmpty()) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. Los parámetros newComment y eventId,"
								+ " no pueden ser nulo ni vacíos.",
						EventBusinessImpl.class.getSimpleName(),
						"insertComment()", String.class.getSimpleName()));
			} else {
				Event event;
				event = this.find(eventId);
				if (event != null) {
					event.getComments().add(newComment);
					event = this.update(event);
				}
				return event;
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de insertar "
							+ "un comentario en el evento.\n%s",
					EventBusinessImpl.class.getSimpleName(), "insertComment()",
					e));
		}
	}

	@Override
	public Event join(User newPartner, String eventId)
			throws CarSharingBusinessException {
		try {
			if (null == newPartner || eventId == null
					|| eventId.trim().isEmpty()) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. Los parámetros newPartner y eventId,"
								+ " no pueden ser nulo ni vacíos.",
						EventBusinessImpl.class.getSimpleName(),
						"insertComment()", String.class.getSimpleName()));
			} else {
				Event event;
				event = this.find(eventId);
				if (event != null) {
					event.getPartners().add(newPartner);
					event = this.update(event);
				}
				return event;
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de insertar "
							+ "un compañero en el evento.\n%s",
					EventBusinessImpl.class.getSimpleName(), "insertPartner()",
					e));
		}
	}

	@Override
	public Event update(Event event) throws CarSharingBusinessException {
		try {
			if (null == event) {
				throw new CarSharingBusinessException(String.format(
						"Clase %s: método %s. El parámetro event,"
								+ " no puede ser nulo.",
						EventBusinessImpl.class.getSimpleName(), "update()",
						String.class.getSimpleName()));
			} else {
				return EventDAOImpl.getInstance().update(event);
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de insertar "
							+ "un evento.\n%s",
					EventBusinessImpl.class.getSimpleName(), "insert()", e));
		}
	}

}
