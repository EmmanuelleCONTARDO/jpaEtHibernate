package dao;

import javax.persistence.EntityManager;

import co.simplon.jpaEtHibernate.City;

public class CityJpaDao implements CityDao {
	private EntityManager em;

	
	
	public CityJpaDao (EntityManager em) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCityById(Long id) {
		// TODO Auto-generated method stub
		
	}

	
}
