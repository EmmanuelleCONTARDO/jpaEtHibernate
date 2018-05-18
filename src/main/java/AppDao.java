

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.jpaEtHibernate.City;

public class AppDao implements AutoCloseable{

	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;
	
	AppDao() {

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
	
//	public static void main(String[] args) {
//	
//		try (App app = new App()) {
//			EntityManager em = factory.createEntityManager();
//			// System.out.println("Créer une ville :" + app.createCity());
//			
//		} catch (Exception e) {
//
//		}
//		
//		
//		EntityManager em = factory.createEntityManager();
//
//	}

}
