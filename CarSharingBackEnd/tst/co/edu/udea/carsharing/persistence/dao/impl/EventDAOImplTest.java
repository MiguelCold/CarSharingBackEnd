package co.edu.udea.carsharing.persistence.dao.impl;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.Comment;
import co.edu.udea.carsharing.model.entities.Event;
import co.edu.udea.carsharing.model.entities.Site;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.model.entities.util.StateEnum;

public class EventDAOImplTest {

	// @Test
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

		assertTrue(events.size() >= 0);
	}

	// @Test
	public void testInsert() throws UnknownHostException {
		Brand brand = new Brand("CHEVROLET");
		List<Car> cars = new ArrayList<Car>();
		Car car = new Car("Blanco", "hil456", brand, "2014", 4);
		cars.add(car);

		User author = new User("Migue Ángel", "Ossa Ruiz",
				"miguelcold8@gmail.com");
		Site source = new Site("location", "U de A");
		Site target = new Site("location", "U de A");
		String state = StateEnum.ACTIVE.getDescription();

		List<Comment> comments = new ArrayList<Comment>();
		comments.add(new Comment(author, "Comentario 1", new Date()));
		comments.add(new Comment(author, "Comentario 2", new Date()));

		List<User> partners = new ArrayList<User>();
		partners.add(new User("Partner 1", "Partner 1", "partner1@gmail.com"));
		partners.add(new User("Partner 2", "Partner 2", "partner2@gmail.com"));

		Event event = new Event(new Date(), new Date(), author, car, source,
				target, 1000, state);
		event.setComments(comments);
		event.setPartners(partners);

		event = EventDAOImpl.getInstance().insert(event);

		if (event != null) {
			System.out.println(event.getId() + ": "
					+ event.getAuthor().getName());
		}

		assertTrue(null != event);
	}

	// @Test
	public void testFind() {
		Event event = new Event();
		String id = "554591175d1cf51480b29cef";

		try {
			event = EventDAOImpl.getInstance().find(id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		assertTrue(event != null && event.getAuthor() != null);
	}

	// @Test
	public void testInsertComment() throws UnknownHostException {
		String id = "554591175d1cf51480b29cef";
		Event event;

		User author = new User("Nombre Comentario 4", "Apellidos Comentario 4",
				"test4@gmail.com");
		Comment comment = new Comment(author, "Comentario 4", new Date());

		event = EventDAOImpl.getInstance().insertComment(comment, id);

		assertTrue(event != null);
	}

	@Test
	public void testJoin() throws UnknownHostException {
		String id = "554591175d1cf51480b29cef";
		Event event;

		User partner = new User("New Partner", "New Partner",
				"newpartner@gmail.com");

		event = EventDAOImpl.getInstance().join(partner, id);

		assertTrue(event != null);
	}
}
