package co.edu.udea.carsharing.ws.rest.impl;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.ws.IUserWS;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public class UserWSImpl implements IUserWS {

	@Override
	public User findByEmailAndPassword(String email, String password)
			throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insert(User user) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addCar(String email, Car car) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getCarsByUser(String email) throws CarSharingWSException {
		// TODO Auto-generated method stub
		return null;
	}

}
