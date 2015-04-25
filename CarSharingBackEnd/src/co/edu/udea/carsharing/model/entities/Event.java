package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String CREATEDATE = "createDate";
	private static String EVENTDATE = "eventDate";
	private static String AUTHOR = "author";
	private static String CAR = "car";
	private static String COMMENTS = "comments";
	private static String SOURCE = "source";
	private static String TARGET = "target";
	private static String PARTNERS = "partners";
	private static String VALUE = "value";

	private Date createDate;
	private Date eventDate;
	private User author;
	private Car car;
	private List<Comment> comments;
	private Site source;
	private Site target;
	private List<User> partners;
	private double value;

	public Event() {
		super();
	}

	public Event(Date createDate, Date eventDate, User author, Car car,
			Site source, Site target, double value) {
		this.createDate = createDate;
		this.eventDate = eventDate;
		this.author = author;
		this.car = car;
		this.source = source;
		this.target = target;
		this.value = value;
	}

	public static Event entityFromDBObject(DBObject dbObject) {
		Event event = new Event();

		if (dbObject.containsField(CREATEDATE)) {
			event.setCreateDate((Date) dbObject.get(CREATEDATE));
		}

		if (dbObject.containsField(EVENTDATE)) {
			event.setCreateDate((Date) dbObject.get(EVENTDATE));
		}

		if (dbObject.containsField(AUTHOR)) {
			event.setCreateDate((Date) dbObject.get(AUTHOR));
		}

		if (dbObject.containsField(CAR)) {
			event.setCreateDate((Date) dbObject.get(CAR));
		}

		if (dbObject.containsField(COMMENTS)) {
			BasicDBList basicDBbList = (BasicDBList) dbObject.get(COMMENTS);
			event.setComments(new ArrayList<Comment>());
			for (Object object : basicDBbList) {
				event.getComments().add(
						Comment.entityFromDBObject((BasicDBObject) object));
			}
		}

		if (dbObject.containsField(SOURCE)) {
			event.setCreateDate((Date) dbObject.get(SOURCE));
		}

		if (dbObject.containsField(TARGET)) {
			event.setCreateDate((Date) dbObject.get(TARGET));
		}

		if (dbObject.containsField(CREATEDATE)) {
			event.setCreateDate((Date) dbObject.get(CREATEDATE));
		}
		if (dbObject.containsField(PARTNERS)) {
			BasicDBList basicDBbList = (BasicDBList) dbObject.get(PARTNERS);
			event.setPartners(new ArrayList<User>());
			for (Object object : basicDBbList) {
				event.getPartners().add(
						User.entityFromDBObject((BasicDBObject) object));
			}
		}
		return (event);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		if (null != this.getCreateDate()) {
			basicDBObject.put(CREATEDATE, this.getCreateDate());
		}

		if (null != this.getEventDate()) {
			basicDBObject.put(EVENTDATE, this.getEventDate());
		}

		if (null != this.getAuthor()) {
			basicDBObject.put(AUTHOR, this.getAuthor());
		}

		if (null != this.getCar()) {
			basicDBObject.put(CAR, this.getCar());
		}

		if (null != this.getComments()) {
			BasicDBList basicDBList = new BasicDBList();

			for (Comment comment : this.getComments()) {
				basicDBList.add(comment.entityToDBObject());
			}

			basicDBObject.put(COMMENTS, basicDBList);
		}

		if (null != this.getSource()) {
			basicDBObject.put(SOURCE, this.getSource());
		}

		if (null != this.getTarget()) {
			basicDBObject.put(TARGET, this.getTarget());
		}

		if (null != this.getPartners()) {
			BasicDBList basicDBList = new BasicDBList();

			for (User user : this.getPartners()) {
				basicDBList.add(user.entityToDBObject());
			}

			basicDBObject.put(PARTNERS, basicDBList);
		}

		if (0 <= this.getValue()) {
			basicDBObject.put(VALUE, this.getValue());
		}

		return (basicDBObject);
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Site getSource() {
		return source;
	}

	public void setSource(Site source) {
		this.source = source;
	}

	public Site getTarget() {
		return target;
	}

	public void setTarget(Site target) {
		this.target = target;
	}

	public List<User> getPartners() {
		return partners;
	}

	public void setPartners(List<User> partners) {
		this.partners = partners;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
