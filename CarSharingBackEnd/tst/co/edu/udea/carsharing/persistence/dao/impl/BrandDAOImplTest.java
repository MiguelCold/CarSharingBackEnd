package co.edu.udea.carsharing.persistence.dao.impl;

import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import co.edu.udea.carsharing.model.entities.Brand;

public class BrandDAOImplTest {

	@Test
	public void testFindAll() {
		List<Brand> brands = new ArrayList<Brand>();

		try {
			brands = BrandDAOImpl.getInstance().findAll();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		for (Brand brand : brands) {
			System.out.println(brand.getId() + ": " + brand.getBrand());
		}

		assertTrue(brands.size() > 0);
	}

	@Test
	public void testInsert() throws UnknownHostException {
		Brand brand = new Brand("MERCEDES-BENZ");
		brand = BrandDAOImpl.getInstance().insert(brand);

		if (brand != null) {
			System.out.println(brand.getId() + ": " + brand.getBrand());
		}

		assertTrue(null != brand);
	}

}
