package lesson10.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.BucketDao;
import lesson10.domain.Bucket;
import lesson10.mapper.Mapper;
import lesson10.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {
	static String CREATE = "INSERT INTO bucket (user_id, product_id, purchase_date) VALUES(?, ?, ?)";
	static String READ_ALL = "SELECT * FROM bucket";
	static String DELETE = "DELETE FROM bucket WHERE id=?";
	static String READ_BY_ID = "SELECT * FROM bucket WHERE id=?";
	static String READ_BY_USER_ID = "SELECT * FROM bucket WHERE user_id=?";
	
	private static final Logger LOGGER = LogManager.getLogger(BucketDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public Bucket create(Bucket bucket) {
		try {
			preparedStatement = connection.prepareStatement(CREATE);
			preparedStatement.setInt(1, bucket.getUserId());
			preparedStatement.setInt(2, bucket.getProductId());
			preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return bucket;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			bucket = Mapper.bucketMap(result);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucket;
	}
	
	@Override
	public List<Bucket> readByUserId(Integer userId) {
		List<Bucket> listOfBuckets = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_BY_USER_ID);
			preparedStatement.setInt(1, userId);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listOfBuckets.add(Mapper.bucketMap(result));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listOfBuckets;
	}

	@Override
	public Bucket update(Bucket bucket) {
		throw new IllegalStateException("there is no update for bucket");
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
	public List<Bucket> readAll() {
		List<Bucket> listOfBuckets = new ArrayList<Bucket>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listOfBuckets.add(Mapper.bucketMap(result));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return listOfBuckets;
	}

}
