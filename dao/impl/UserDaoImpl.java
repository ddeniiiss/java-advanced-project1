package lesson10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.UserDao;
import lesson10.domain.User;
import lesson10.mapper.Mapper;
import lesson10.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {
	static String CREATE = "INSERT INTO user (email, first_name, last_name, role, password) VALUES(?, ?, ?, ?, ?)";
	static String READ_ALL = "SELECT * FROM user";
	static String UPDATE = "UPDATE user SET email = ?, first_name = ?, last_name= ?, role = ?, password = ? WHERE id=?";
	static String DELETE = "DELETE FROM user WHERE id=?";
	static String READ_BY_ID = "SELECT * FROM user WHERE id=?";

	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			user = Mapper.userMap(result);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User update(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> listOfUsers = new ArrayList<User>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listOfUsers.add(Mapper.userMap(result));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listOfUsers;
	}

	@Override
	public boolean checkUserExists(String email) {
		boolean emailExists = false;
		try (Connection connection = ConnectionUtils.openConnection()) {
			String sql = "SELECT * FROM user WHERE email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("email").equals(email)) {
					emailExists = true;
					resultSet.close();
					preparedStatement.close();
					connection.close();
				}
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("Database error in register(): " + e.getMessage());
		}
		return emailExists;
	}

	@Override
	public User login(String email, String password) {
		User user = null;
		try (Connection connection = ConnectionUtils.openConnection()) {
			String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = Mapper.userMap(resultSet);
				resultSet.close();
				preparedStatement.close();
				connection.close();
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("Database error in register(): " + e.getMessage());
		}
		return user;
	}

}
