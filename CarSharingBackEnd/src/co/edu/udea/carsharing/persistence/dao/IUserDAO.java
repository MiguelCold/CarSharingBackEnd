package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.User;

public interface IUserDAO {

	public User find(String email);

	public List<User> findAll();

	public boolean insert(User user);

	public User update(User user);

	public boolean delete(User user);

}
