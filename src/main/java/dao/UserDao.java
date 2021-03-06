package dao;

import co.simplon.jpaEtHibernate.User;

public interface UserDao {
	User createUser(User user);

	User getUserById(Long id);

	User updateUser(User user);

	void deleteUserById(Long id);
}
