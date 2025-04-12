package lesson10.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.UserDao;
import lesson10.dao.impl.UserDaoImpl;
import lesson10.domain.User;
import lesson10.service.UserService;

public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	private UserDao userDao;
	private static UserService userServiceImpl;

	public UserServiceImpl() {
		try {
			userDao = new UserDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	public static UserService getUserService() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Override
	public boolean checkUserExists(String email) {
		return userDao.checkUserExists(email);
	}

	@Override
	public User login(String email, String password) {
		return userDao.login(email, password);
	}
}
