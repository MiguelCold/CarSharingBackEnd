package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;

public interface IEventDAO {

	public Event find(String eventId) throws Exception;

	public List<Event> findAll();

	public Event insert(Event event);

	public Event insertComment(Comment newComment, String eventId);

	public Event join(User newPartner, String eventId);

	public Event update(Event event);

	public Event leave(User leavingUser, String eventId);

	public List<Event> cancel(String eventId);

}
