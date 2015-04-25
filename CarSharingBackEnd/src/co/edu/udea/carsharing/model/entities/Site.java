package co.edu.udea.carsharing.model.entities;

import java.io.Serializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String GEOGRAPHICALLOCATION = "geographicalLocation";
	private static String DESCRIPTION = "description";

	private String geographicalLocation;
	private String description;

	public Site() {
		super();
	}

	public Site(String geographicalLocation, String description) {
		super();
		this.geographicalLocation = geographicalLocation;
		this.description = description;
	}

	public static Site entityFromDBObject(DBObject dbObject) {
		Site site = new Site();

		if (dbObject.containsField(GEOGRAPHICALLOCATION)) {
			site.setGeographicalLocation((String) dbObject
					.get(GEOGRAPHICALLOCATION));
		}

		if (dbObject.containsField(DESCRIPTION)) {
			site.setDescription((String) dbObject.get(DESCRIPTION));
		}

		return (site);
	}

	public BasicDBObject entityToDBObject() {
		BasicDBObject basicDBObject = new BasicDBObject();

		if (null != this.getGeographicalLocation()) {
			basicDBObject.put(GEOGRAPHICALLOCATION,
					this.getGeographicalLocation());
		}

		if (null != this.getDescription()) {
			basicDBObject.put(DESCRIPTION, this.getDescription());
		}

		return (basicDBObject);
	}

	public String getGeographicalLocation() {
		return geographicalLocation;
	}

	public void setGeographicalLocation(String geographicalLocation) {
		this.geographicalLocation = geographicalLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
