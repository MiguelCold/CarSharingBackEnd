package co.edu.udea.carsharing.ws;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public interface IBrandWS {

	public Brand insert(Brand brand) throws CarSharingWSException;

	public List<Brand> findAll() throws CarSharingWSException;
}
