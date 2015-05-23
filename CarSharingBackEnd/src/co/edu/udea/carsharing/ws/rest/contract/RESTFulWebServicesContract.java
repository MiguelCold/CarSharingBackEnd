package co.edu.udea.carsharing.ws.rest.contract;

public class RESTFulWebServicesContract {

	private RESTFulWebServicesContract() {
		super();
	}

	public static final class BrandtWebServicesContract {

		/*
		 * Parameteres
		 */

		/*
		 * Paths
		 */
		public static final String ROOT_PATH = "/brand";

		private BrandtWebServicesContract() {
			super();
		}
	}

	public static final class EventWebServicesContract {

		/*
		 * Parameteres
		 */
		public static final String EVENT_ID_PARAM = "eventId";

		/*
		 * Paths
		 */
		public static final String ROOT_PATH = "/event";
		public static final String FIND_ALL_PATH = "/all";
		public static final String INSERT_COMMENT = "/comment";
		public static final String JOIN_PARTNER = "/partner";

		private EventWebServicesContract() {
			super();
		}
	}
}