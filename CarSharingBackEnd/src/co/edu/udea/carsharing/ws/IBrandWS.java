package co.edu.udea.carsharing.ws;

import java.util.List;

import javax.ws.rs.core.Response;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public interface IBrandWS {

	public Response insert(Brand brand) throws CarSharingWSException,
			CarSharingBusinessException;

	public List<Brand> findAll() throws CarSharingWSException;
}
