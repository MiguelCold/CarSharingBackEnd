package co.edu.udea.carsharing.ws.rest.impl;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.ws.IEventWS;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public class EventWSImpl implements IEventWS {

	@Override
	public Event find(String eventId) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findAll() throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event insert(Event event) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event insertComment(Comment newComment, String eventId)
			throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event join(User newPartner, String eventId)
			throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

}
