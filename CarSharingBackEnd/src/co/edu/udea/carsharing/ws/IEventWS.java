package co.edu.udea.carsharing.ws;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public interface IEventWS {

	public Event find(String eventId) throws CarSharingWSException;

	public List<Event> findAll() throws CarSharingWSException;

	public Event insert(Event event) throws CarSharingWSException;

	public Event insertComment(Comment newComment, String eventId)
			throws CarSharingWSException;

	public Event join(User newPartner, String eventId)
			throws CarSharingWSException;
}