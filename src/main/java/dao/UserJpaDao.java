package dao;

import javax.persistence.EntityManager;

import co.simplon.jpaEtHibernate.User;

public class UserJpaDao implements UserDao {
	private EntityManager em;

	public UserJpaDao(EntityManager em) {
		this.em = em;
	}

	@Override
	public User createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub

	}

}
