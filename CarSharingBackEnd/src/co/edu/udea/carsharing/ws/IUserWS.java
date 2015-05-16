package co.edu.udea.carsharing.ws;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.ws.exception.CarSharingWSException;

public interface IUserWS {

	public User findByEmailAndPassword(String email, String password)
			throws CarSharingWSException;

	public User findByEmail(String email) throws CarSharingWSException;

	public User insert(User user) throws CarSharingWSException;

	public User addCar(String email, Car car) throws CarSharingWSException;

	public List<Car> getCarsByUser(String email) throws CarSharingWSException;
}