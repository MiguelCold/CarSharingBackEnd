package co.edu.udea.carsharing.persistence.dao.impl;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.edu.udea.carsharing.model.entities.Brand;
import co.edu.udea.carsharing.util.exception.CarSharingBusinessException;
import co.edu.udea.carsharing.util.exception.CarSharingDAOException;
import co.edu.udea.carsharing.util.exception.CarSharingTechnicalException;

public class BrandDAOImplTest {

	@Test
	public void testFindAll() throws UnknownHostException,
			CarSharingDAOException, CarSharingTechnicalException {
		List<Brand> brands = new ArrayList<Brand>();

		brands = BrandDAOImpl.getInstance().findAll();

		assertTrue(brands.size() >= 0);
	}

	@Test
	public void testInsert() throws UnknownHostException,
			CarSharingDAOException, CarSharingBusinessException,
			CarSharingTechnicalException {
		Brand brand = new Brand("MAZDA");
		brand = BrandDAOImpl.getInstance().insert(brand);

		assertTrue(null != brand);
	}
}
