import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import co.simplon.jpaEtHibernate.City;
import co.simplon.jpaEtHibernate.Monument;
import co.simplon.jpaEtHibernate.User;

public class App implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private static EntityManagerFactory factory;

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

	// public City createCity() {
	// EntityManager em = factory.createEntityManager();
	// City city = new City("New-York", 32, 500);
	// city = create(em, city);
	// em.close();
	// return city;
	// }

	// Créer une ville, récupérer l'ID de la ville et l'insérer directement dans le
	// nouveau monument saisi
	public City createCity() {
		EntityManager em = factory.createEntityManager();
		City city = new City("New-York", 32, 500);
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

	
	 //  *********** MONUMENT ***********
	
//	public Monument createMonument(String m, Long IdCity) {
//		EntityManager em = factory.createEntityManager();
//		Monument mon = new Monument(m);
//		mon.setCity(em.find(City.class, IdCity));
//		mon = createM(em, mon);
//		em.close();
//		return mon;
//	}

	
//	public Monument createMonument() {
//		EntityManager em = factory.createEntityManager();
//		Monument mon = new Monument("Cirque d'hiver");
//		mon.setCity(em.find(City.class, 45L));
//		mon = createM(em, mon);
//		em.close();
//		return mon;
//	}
//
//	public Monument createM(EntityManager em, Monument mon) {
//		em.getTransaction().begin();
//		em.persist(mon);
//		em.getTransaction().commit();
//		return mon;
//	}

	// public User createUser() {
	// EntityManager em = factory.createEntityManager();
	//
	// User user = new User("Léa-Victoria");
	// user.addMonument(em.find(Monument.class, 14L));
	// user.addMonument(em.find(Monument.class, 4L));
	// user = createUser(em, user);
	// em.close();
	// return user;
	// }
	//
	// public User createUser(EntityManager em, User user) {
	// em.getTransaction().begin();
	// em.persist(user);
	// em.getTransaction().commit();
	// return user;
	// }

	// public City createCityAndUpdate() {
	// EntityManager em = factory.createEntityManager();
	// City city = new City("Rome", 0, 0.5);
	// em.getTransaction().begin();
	// em.persist(city);
	// city.setLatitude(1000.);
	// em.getTransaction().commit();// MAGIC HAPPENS HERE !
	// em.close();
	// return city;
	// }
	//
	public City readCity() {
		EntityManager em = factory.createEntityManager();
		City city = readCity(em, 8L);
		em.close();
		return city;
	}

	public City readCity(EntityManager em, Long id) {
		return em.find(City.class, id);
	}

	// Read Monument
	public Monument readMonument() {
		EntityManager em = factory.createEntityManager();
		Monument mon = readMonument(em, 21L);
		System.out.println(mon);
		em.close();
		return mon;
	}

	public Monument readMonument(EntityManager em, Long id) {
		return em.find(Monument.class, id);
	}

	// public City updateCity() {
	// return update(new City(4L, "PaRiS", -1., -2.));
	// }
	//
	// public City update(City city) {
	// EntityManager em = factory.createEntityManager();
	// em.getTransaction().begin();
	// city = em.merge(city);
	// em.getTransaction().commit();
	// return city;
	// }
	//
	// public City removeCity(City city) {
	// EntityManager em = factory.createEntityManager();
	// em.getTransaction().begin();
	// city = em.find(City.class, 26L);
	// if (city != null) {
	// em.remove(city);
	// }
	// em.getTransaction().commit();
	// return city;
	// }

	// public void removeCity2() {
	// EntityManager em = factory.createEntityManager();
	// em.getTransaction().begin();
	// City city = readCity(em, 18L);
	// em.remove(city);
	// em.getTransaction().commit();
	// em.close();
	// }
	//
	// public City deleteCity() {
	// City city = new City(65L, "AZERTY", 5000., 15.);
	// return removeCityMerge(city);
	//
	// }

	// public City removeCityMerge(City city) {
	// EntityManager em = factory.createEntityManager();
	// em.getTransaction().begin();
	// city = em.merge(city);
	// em.remove(city);
	// em.getTransaction().commit();
	// em.close();
	// return city;
	// }

	public List<City> findAll(int first, int size, EntityManager em) {
		return em.createNamedQuery("City.findAll", City.class).setFirstResult(first).setMaxResults(size)
				.getResultList();
	}

	public List<Monument> findAllM(int first, int size, EntityManager em) {
		return em.createNamedQuery("Monument.findAll", Monument.class).setFirstResult(first).setMaxResults(size)
				.getResultList();
	}

	public List<User> findAllU(int first, int size, EntityManager em) {
		return em.createNamedQuery("User.findAll", User.class).setFirstResult(first).setMaxResults(size)
				.getResultList();
	}

	public void deleteByIdMon(Long Id, EntityManager em) {
		em.getTransaction().begin();
		em.createNamedQuery("Monument.deleteById").setParameter("id", Id).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	public static void main(String[] args) {
		try (App app = new App()) {
			// System.out.println("Créer une ville :" + app.createCity());
			// System.out.println("Créer et mettre à jour une ville :" +
			// app.createCityAndUpdate());
			System.out.println("Lire une ville :" + app.readCity());

			// System.out.println("Mettre à jour : " + app.updateCity());
			// System.out.println("Supression de ma vile :" + app.removeCity(null));

			// Monument
			//System.out.println("Créer un monument: " + app.createMonument());
			// City city = app.createCity(); // Créer NY
			// app.createMonument("Ground Zéro", city.getId());

			// System.out.println("User : " + app.createUser());

			EntityManager em = factory.createEntityManager();
			// TypedQuery<City> query = em.createQuery("SELECT c FROM City AS c WHERE
			// c.name=:nameParam", City.class);
			// query.setParameter("nameParam", "Atlantis");
			// for (City c : query.getResultList()) {
			// System.out.println(c);
			// }

			// @NamedQueries
			// for (City c : app.findAll(1, 20, em)) {
			// System.out.println(c);
			// }

			// for (Monument m : app.findAllM(0, 100, em)) {
			// System.out.println(m);
			// }

			// for (User u : app.findAllU(1, 6, em)) {
			// System.out.println(u);
			// }

			app.deleteByIdMon(31L, em);

			em.close();

			// EntityManager emM = factory.createEntityManager();
			// TypedQuery<Monument> queryM = emM.createQuery("SELECT m FROM Monument AS m
			// WHERE m.name=:nameParam",
			// Monument.class);
			// queryM.setParameter("nameParam", "Big Ben");
			// for (Monument m : queryM.getResultList()) {
			// System.out.println(m);
			// }
			// emM.close();

		} catch (Exception e) {

		}

	}

}
