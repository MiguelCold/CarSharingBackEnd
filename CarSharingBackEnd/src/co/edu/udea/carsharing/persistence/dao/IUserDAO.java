package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;

public interface IUserDAO {

	public User findByEmail(String email);

	public User insert(User user);

	public User addCar(Car car);

	public List<Car> getCarsByUser(String email);

}
