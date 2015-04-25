package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.Brand;

public interface IBrandDAO {

	public List<Brand> findAll();

	public Brand insert(Brand brand);
}
