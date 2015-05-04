package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.business.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.persistence.dao.exception.CarSharingDAOException;

public interface IBrandDAO {

	public List<Brand> findAll() throws CarSharingDAOException;

	public Brand insert(Brand brand) throws CarSharingDAOException,
			CarSharingBusinessException;
}
