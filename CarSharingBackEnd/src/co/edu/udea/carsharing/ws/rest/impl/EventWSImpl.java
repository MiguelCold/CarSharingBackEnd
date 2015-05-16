package co.edu.udea.carsharing.ws.rest.impl;

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
