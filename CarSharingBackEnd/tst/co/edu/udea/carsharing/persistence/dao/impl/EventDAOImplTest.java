package co.edu.udea.carsharing.persistence.dao.impl;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.Site;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.model.entities.util.State;

public class EventDAOImplTest {

	@Ignore
	@Test
	public void testFindAll() {
		List<Event> events = new ArrayList<Event>();

		try {
			events = EventDAOImpl.getInstance().findAll();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		for (Event event : events) {
			System.out.println(event.getId() + ": " + event.getSource());
		}

		assertTrue(events.size() > 0);
	}

	@Test
	public void testInsert() throws UnknownHostException {
		Brand brand = new Brand("CHEVROLET");
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car("Blanco", "hil456", brand, "2014", 4);
		cars.add(car);
		User author = new User("Miguel Ángel", "Ossa Ruiz",
				"miguelcold8@gmail.com", "coldlomejor", "123456789",
				new Date(), cars);
		Site source = new Site("location", "U de A");
		Site target = new Site("location", "U de A");
		State state = State.ACTIVE;
		Event event = new Event(new Date(), new Date(), author, car, source,
				target, 1000, state);

		event = EventDAOImpl.getInstance().insert(event);

		if (event != null) {
			System.out.println(event.getId() + ": "
					+ event.getAuthor().getName());
		}

		assertTrue(null != event);
	}
}
