package lesson10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.ProductDao;
import lesson10.domain.Product;
import lesson10.mapper.Mapper;
import lesson10.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {
	static String CREATE = "INSERT INTO product (name, description, price) VALUES(?, ?, ?)";
	static String READ_ALL = "SELECT * FROM product";
	static String UPDATE = "UPDATE product SET name = ?, desctiption = ?, price= ? WHERE id=?";
	static String DELETE = "DELETE FROM product WHERE id=?";
	static String READ_BY_ID = "SELECT * FROM product WHERE id=?";
	
	private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public ProductDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public Product create(Product product) {
		try {
			preparedStatement = connection.prepareStatement(CREATE);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return product;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			product = Mapper.productMap(result);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
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
	public List<Product> readAll() {
		List<Product> listOfProducts = new ArrayList<Product>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listOfProducts.add(Mapper.productMap(result));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listOfProducts;
	}

}
