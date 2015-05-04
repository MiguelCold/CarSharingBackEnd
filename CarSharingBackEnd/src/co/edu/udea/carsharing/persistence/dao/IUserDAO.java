package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.dao.exception.CarSharingDAOException;

public interface IUserDAO {

	public User findByEmailAndPassword(String email, String password)
			throws CarSharingDAOException, CarSharingBusinessException;

	public User findByEmail(String email) throws CarSharingDAOException,
			CarSharingBusinessException;

	public User insert(User user) throws CarSharingDAOException,
			CarSharingBusinessException;

	public User addCar(String email, Car car) throws CarSharingDAOException,
			CarSharingBusinessException;

	public List<Car> getCarsByUser(String email) throws CarSharingDAOException;

}