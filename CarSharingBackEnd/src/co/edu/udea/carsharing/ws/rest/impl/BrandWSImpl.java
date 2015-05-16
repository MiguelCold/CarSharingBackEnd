package co.edu.udea.carsharing.ws.rest.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.business.impl.BrandBusinessImpl;
import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.ws.IBrandWS;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;
import co.edu.udea.carsharing.ws.rest.contract.RESTFulWebServicesContract;

@Path(value = RESTFulWebServicesContract.BrandtWebServicesContract.ROOT_PATH)
public class BrandWSImpl implements IBrandWS {

	@POST
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override()
	public Response insert(Brand brand) throws CarSharingWSException,
			CarSharingBusinessException {
		Brand b = BrandBusinessImpl.getInstance().insert(brand);

		return (b != null) ? Response.ok(b).build() : Response.status(
				Response.Status.NO_CONTENT).build();
	}

	@Override
	public List<Brand> findAll() throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

}
