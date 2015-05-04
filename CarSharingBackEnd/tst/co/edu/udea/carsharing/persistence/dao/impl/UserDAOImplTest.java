package co.edu.udea.carsharing.persistence.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.dao.exception.CarSharingDAOException;
import co.edu.udea.carsharing.technical.exception.CarSharingTechnicalException;

public class UserDAOImplTest {

	@Test
	public void testFindByEmailAndPassword() throws CarSharingDAOException,
			CarSharingBusinessException, CarSharingTechnicalException {
		String email = "test1@gmail.com";
		String password = "test1password";

		User user = UserDAOImpl.getInstance().findByEmailAndPassword(email,
				password);

		assertTrue(user != null && !("").equals(user.getId()));
	}

	@Test
	public void testInsert() throws CarSharingDAOException,
			CarSharingBusinessException, CarSharingTechnicalException {
		User user1 = new User("Test 1 Name", "Test 1 Lastname",
				"test1@gmail.com");
		user1.setBirthDate(new Date());
		user1.setPassword("test1password");

		User user2 = new User("Test 2 Name", "Test 2 Lastname",
				"test2@gmail.com");
		user2.setBirthDate(new Date());
		user2.setPassword("test2password");

		List<Car> cars = new ArrayList<Car>();
		Car car = new Car();
		car.setBrand(new Brand("MAZDA"));
		car.setCapacity(4);
		car.setCarriagePlate("ABC123");
		car.setColor("Negro");
		car.setModel("Modelo");
		cars.add(car);

		user2.setCars(cars);

		user1 = UserDAOImpl.getInstance().insert(user1);
		user2 = UserDAOImpl.getInstance().insert(user2);

		assertTrue(user1 != null && user1.getId() != null && user2 != null
				&& user2.getId() != null);
	}

	@Test
	public void testAddCar() throws CarSharingDAOException,
			CarSharingBusinessException, CarSharingTechnicalException {
		String email1 = "test1@gmail.com";
		String email2 = "test2@gmail.com";

		Car car = new Car();
		car.setBrand(new Brand("CHEVROLET"));
		car.setCapacity(3);
		car.setCarriagePlate("DEF456");
		car.setColor("Rojo");
		car.setModel("Modelo del Carro");

		User user1 = UserDAOImpl.getInstance().addCar(email1, car);
		User user2 = UserDAOImpl.getInstance().addCar(email2, car);

		assertTrue(user1 != null && !user1.getId().isEmpty() && user2 != null
				&& !user2.getId().isEmpty());
	}

	@Test
	public void testGetCarsByUser() throws CarSharingDAOException,
			CarSharingTechnicalException {
		String email = "test2@gmail.com";

		List<Car> cars = UserDAOImpl.getInstance().getCarsByUser(email);

		assertTrue(cars.size() >= 0);
	}

	@Test
	public void testFindByEmail() throws CarSharingDAOException,
			CarSharingBusinessException, CarSharingTechnicalException {
		String email = "test1@gmail.com";

		User user = UserDAOImpl.getInstance().findByEmail(email);

		assertTrue(user != null && !("").equals(user.getId()));
	}
}
