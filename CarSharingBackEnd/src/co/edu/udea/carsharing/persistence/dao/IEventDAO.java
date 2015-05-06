package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.util.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.util.exception.CarSharingDAOException;

public interface IEventDAO {

	public Event find(String eventId) throws CarSharingDAOException,
			CarSharingBusinessException;

	public List<Event> findAll() throws CarSharingDAOException;

	public Event insert(Event event) throws CarSharingDAOException,
			CarSharingBusinessException;

	public Event insertComment(Comment newComment, String eventId)
			throws CarSharingDAOException, CarSharingBusinessException;

	public Event join(User newPartner, String eventId)
			throws CarSharingDAOException, CarSharingBusinessException;

	public Event update(Event event) throws CarSharingDAOException,
			CarSharingBusinessException;
}
