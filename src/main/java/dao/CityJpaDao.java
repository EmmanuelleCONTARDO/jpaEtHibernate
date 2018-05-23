package dao;

import javax.persistence.EntityManager;

import co.simplon.jpaEtHibernate.City;

public class CityJpaDao implements CityDao {
	private EntityManager em;

	public CityJpaDao(EntityManager em) {
		this.em = em;
	}

	@Override
	public City createCity(City city) {
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
		return city;
	}

	@Override
	public City getCityById(Long id) {
		City city = em.find(City.class, id);
		// em.close();
		return city;

	}

	@Override
	public City updateCity(City city) {
		em.getTransaction().begin();
		city = em.merge(city);
		em.getTransaction().commit();
		return city;
	}

	@Override
	public void deleteCityById(Long id) {
		em.getTransaction().begin();
		 City city = em.find(City.class, id);
		 if (city != null) {
		 em.remove(city);
		 }
		 em.getTransaction().commit();
		 }
// em.getTransaction().begin();
		// City city = readCity(em, 18L);
		// em.remove(city);
		// em.getTransaction().commit();
		// em.close();
	
	
	}


