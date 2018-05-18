package dao;

import co.simplon.jpaEtHibernate.Monument;

public class MMonumentDao {
	public interface MonumentDao {
	    Monument createMonument(Monument monument);
	    Monument getMonumentById(Long id);
	    Monument updateMonument(Monument monument);
	    void deleteMonumentById(Long id);
	}
}
