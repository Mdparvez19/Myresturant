package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import DTO.CustomerSignup;

public class Mydao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(CustomerSignup customer) {

		transaction.begin();
		manager.persist(customer);
		transaction.commit();

	}

	public CustomerSignup fetchByEmail(String email) {
		Query query = manager.createQuery("select x from CustomerSignup x where email = ?1").setParameter(1, email);
		List<CustomerSignup> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public CustomerSignup fetchByMoblie(long mobile) {
		Query query = manager.createQuery("select x from CustomerSignup x where mobile = ?1").setParameter(1, mobile);
		List<CustomerSignup> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
