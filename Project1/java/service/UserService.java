package lesson10.service;

import lesson10.domain.User;
import lesson10.shared.AbstractCRUD;

public interface UserService extends AbstractCRUD<User> {
	boolean checkUserExists(String email);

	User login(String email, String password);
}
