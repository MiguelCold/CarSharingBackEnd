package co.edu.udea.carsharing.ws.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.edu.udea.carsharing.business.IEventBusiness;
import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.business.impl.EventBusinessImpl;
import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.ws.IEventWS;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;
import co.edu.udea.carsharing.ws.rest.contract.RESTFulWebServicesContract;

@Path(value = RESTFulWebServicesContract.EventWebServicesContract.ROOT_PATH)
public class EventWSImpl implements IEventWS {

	private IEventBusiness eventBusiness;

	public EventWSImpl() {
		this.eventBusiness = EventBusinessImpl.getInstance();
	}

	@GET()
	@Override()
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response find(
			@QueryParam(value = RESTFulWebServicesContract.EventWebServicesContract.EVENT_ID_PARAM) String eventId)
			throws CarSharingWSException {

		Event event = null;

		if (eventId.isEmpty()) {
			return (Response.status(Response.Status.BAD_REQUEST).build());
		}

		try {
			event = this.eventBusiness.find(eventId);
		} catch (CarSharingBusinessException e) {

			return (Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build());
		}

		return ((event != null) ? Response.ok(event).build() : Response.status(
				Response.Status.NO_CONTENT).build());
	}

	@GET()
	@Override()
	@Path(value = RESTFulWebServicesContract.EventWebServicesContract.FIND_ALL_PATH)
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Event> findAll() throws CarSharingWSException {
		List<Event> eventsList = null;

		try {
			eventsList = this.eventBusiness.findAll();

			if (eventsList == null) {
				eventsList = new ArrayList<Event>();
			}
		} catch (CarSharingBusinessException e) {

			return (null);
		}

		return ((eventsList.isEmpty()) ? null : eventsList);
	}

	@Override
	public Response insert(Event event) throws CarSharingWSException {
		if (event == null) {

			return (Response.status(Response.Status.BAD_REQUEST).build());

		} else {
			Event returnedEvent = null;
			try {

				returnedEvent = this.eventBusiness.insert(event);

			} catch (CarSharingBusinessException e) {

				return (Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.build());
			}

			return ((returnedEvent != null) ? Response.ok(returnedEvent)
					.build() : Response.status(Response.Status.NO_CONTENT)
					.build());
		}
	}

	@Override
	public Response insertComment(Comment newComment, String eventId)
			throws CarSharingWSException {
		Event returnedEvent;
		if (newComment == null || eventId.equals("") || eventId.isEmpty()) {

			return (Response.status(Response.Status.BAD_REQUEST).build());

		} else {
			try {
				returnedEvent = this.eventBusiness.find(eventId);
				if (returnedEvent != null) {

					returnedEvent = this.eventBusiness.insertComment(
							newComment, eventId);

				} else {

					return (Response.status(Response.Status.BAD_REQUEST)
							.build());

				}

			} catch (CarSharingBusinessException e) {

				return (Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.build());
			}

			return ((returnedEvent != null) ? Response.ok(returnedEvent)
					.build() : Response.status(Response.Status.NO_CONTENT)
					.build());
		}
	}

	@Override
	public Response join(User newPartner, String eventId) {
		Event returnedEvent;
		if (newPartner == null || eventId.equals("") || eventId.isEmpty()) {

			return (Response.status(Response.Status.BAD_REQUEST).build());

		} else {
			try {
				returnedEvent = this.eventBusiness.find(eventId);
				if (returnedEvent != null && validateJoin(returnedEvent)) {

					returnedEvent = this.eventBusiness
							.join(newPartner, eventId);

				} else {

					return (Response.status(Response.Status.BAD_REQUEST)
							.build());

				}

			} catch (CarSharingBusinessException e) {

				return (Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.build());
			}

			return ((returnedEvent != null) ? Response.ok(returnedEvent)
					.build() : Response.status(Response.Status.NO_CONTENT)
					.build());
		}
	}

	public boolean validateJoin(Event event) {
		if (event != null) {
			int amountPeople = event.getAmountPeople();
			int capacity = event.getCar().getCapacity();
			if (capacity <= amountPeople) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
