package dao;

import javax.persistence.EntityManager;

import co.simplon.jpaEtHibernate.Monument;

public class MonumentJpaDao implements MonumentDao {

	private EntityManager em;

	public MonumentJpaDao(EntityManager em) {
		this.em = em;
	}

	@Override
	public Monument createMonument(Monument monument) {
		em.getTransaction().begin();
		em.persist(monument);
		em.getTransaction().commit();
		return monument;

	}

	@Override
	public Monument getMonumentById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Monument updateMonument(Monument monument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMonumentById(Long id) {
		// TODO Auto-generated method stub

	}

}
