package co.edu.udea.carsharing.persistence.dao;

import java.util.List;

import co.edu.udea.carsharing.model.entities.CreditCard;

public interface ICreditCardDAO {

	public CreditCard find(String type, String number);

	public List<CreditCard> findAll();

	public boolean insert(CreditCard creditCard);

	public CreditCard update(CreditCard creditCard);

	public boolean delete(CreditCard creditCard);
}
