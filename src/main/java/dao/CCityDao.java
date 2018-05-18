package dao;

import co.simplon.jpaEtHibernate.City;

public class CCityDao {

	public interface CityDao {
	    City createCity(City city);
	    City getCityById(Long id);
	    City updateCity(City city);
	    void deleteCityById(Long id);
	}
}
