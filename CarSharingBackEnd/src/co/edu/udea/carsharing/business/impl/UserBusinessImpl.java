package co.edu.udea.carsharing.business.impl;

import java.util.List;

import co.edu.udea.carsharing.business.IUserBusiness;
import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Car;
import co.edu.udea.carsharing.model.entities.User;
import co.edu.udea.carsharing.persistence.dao.impl.UserDAOImpl;

public class UserBusinessImpl implements IUserBusiness {

	private static IUserBusiness instance;

	private UserBusinessImpl() {
		super();
	}

	public static synchronized IUserBusiness getInstance() {
		if (instance == null) {
			instance = new UserBusinessImpl();
		}

		return instance;
	}

	@Override
	public User findByEmailAndPassword(String email, String password)
			throws CarSharingBusinessException {
		try {
			if (null == email || email.trim().isEmpty() || null == password
					|| password.trim().isEmpty()) {
				throw new CarSharingBusinessException(
						String.format(
								"Clase %s: método %s. Los parámetros email y password, "
										+ "ambos tipo %s, no pueden ser ni nulos ni vacíos.",
								UserBusinessImpl.class.getSimpleName(),
								"findByEmailAndPassword()",
								String.class.getSimpleName()));
			} else {

				return UserDAOImpl.getInstance().findByEmailAndPassword(email,
						password);
			}
		} catch (Exception e) {
			throw new CarSharingBusinessException(String.format(
					"Clase %s: método %s. Se ha producido un error al tratar de buscar "
							+ "un usuario por email y password.\n%s",
					UserBusinessImpl.class.getSimpleName(),
					"findByEmailAndPassword()", e));
		}
	}

	@Override
	public User findByEmail(String email) throws CarSharingBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insert(User user) throws CarSharingBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addCar(String email, Car car)
			throws CarSharingBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getCarsByUser(String email)
			throws CarSharingBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
