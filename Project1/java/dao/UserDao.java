package lesson10.dao;

import lesson10.domain.User;
import lesson10.shared.AbstractCRUD;

public interface UserDao extends AbstractCRUD<User> {
	boolean checkUserExists(String email);

	User login(String email, String password);
}
