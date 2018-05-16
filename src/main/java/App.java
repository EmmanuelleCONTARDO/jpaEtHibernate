import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.jpaEtHibernate.City;
import co.simplon.jpaEtHibernate.Monument;

public class App implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;

	App() {

		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
			if (envName.contains("DB_USER")) {
				configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
			}
			if (envName.contains("DB_PASS")) {
				configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
			}
			if (envName.contains("DB_URL")) {
				configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
			}
		}
		// Methode de classe
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, configOverrides);

	}

	public void close() {
		factory.close();
	}

	public City createCity() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Atlantis", 0, 0.5);
		city = create(em, city);
		em.close();
		return city;
	}
	

	public City create(EntityManager em, City city) {
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
		return city;
	}

	// Monument
	public Monument createMonument() {
		EntityManager em = factory.createEntityManager();
		Monument mon = new Monument("Le BHV");
		mon.setCity(em.find(City.class, 5L));
		mon = createM(em, mon);
		em.close();
		return mon;
	}
	public Monument createM(EntityManager em, Monument mon) {
		em.getTransaction().begin();
		em.persist(mon);
		em.getTransaction().commit();
		return mon;
	}

	
	
	public City createCityAndUpdate() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Préchac-sur-Adour", 0, 0.5);
		em.getTransaction().begin();
		em.persist(city);
		city.setLatitude(1000.);
		em.getTransaction().commit();// MAGIC HAPPENS HERE !
		em.close();
		return city;
	}

	public City readCity() {
		EntityManager em = factory.createEntityManager();
		City city = readCity(em, 8L);
		em.close();
		return city;
	}

	public City readCity(EntityManager em, Long id) {
		return em.find(City.class, id);
	}

	public City updateCity() {
		return update(new City(4L, "PaRiS", -1., -2.));
	}

	public City update(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		city = em.merge(city);
		em.getTransaction().commit();
		return city;
	}

	public City removeCity(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		city = em.find(City.class, 26L);
		if (city != null) {
			em.remove(city);
		}
		em.getTransaction().commit();
		return city;
	}

	public void removeCity2() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		City city = readCity(em, 18L);
		em.remove(city);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public City deleteCity() {
		City city = new City(65L, "AZERTY", 5000., 15.);
		return removeCityMerge(city);
		
	}
	public City removeCityMerge(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		city = em.merge(city);
		em.remove(city);
		em.getTransaction().commit();
		em.close();
		return city;
	}
	

	public static void main(String[] args) {
		try (App app = new App()) {
			System.out.println("Créer une ville :" + app.createCity());
			System.out.println("Créer et mettre à jour une ville :" + app.createCityAndUpdate());
			System.out.println("Lire une ville :" + app.readCity());
			System.out.println("Mettre à jour : " + app.updateCity());
			System.out.println("Supression de ma vile :" + app.removeCity(null));
			
			//Monument
			System.out.println("Créer un monument: " + app.createMonument());
		} catch (Exception e) {

		}

	}

}
