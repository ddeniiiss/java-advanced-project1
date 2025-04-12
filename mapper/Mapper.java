package lesson10.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lesson10.domain.Bucket;
import lesson10.domain.Product;
import lesson10.domain.User;

public class Mapper {
	public static User userMap(ResultSet result) throws SQLException {
		Integer id = result.getInt("id");
		String email = result.getString("email");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String role = result.getString("role");
		String password = result.getString("password");
		return new User(id, email, firstName, lastName, role, password);
	}

	public static Product productMap(ResultSet result) throws SQLException {
		Integer id = result.getInt("id");
		String name = result.getString("name");
		String description = result.getString("description");
		Double price = result.getDouble("price");
		return new Product(id, name, description, price);
	}

	public static Bucket bucketMap(ResultSet result) throws SQLException {
		Integer id = result.getInt("id");
		Integer userId = result.getInt("user_id");
		Integer productId = result.getInt("product_id");
		Date purchaseDate = result.getDate("purchase_date");
		return new Bucket(id, userId, productId, purchaseDate);
	}
}
