package lesson10.service.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.ProductDao;
import lesson10.dao.impl.ProductDaoImpl;
import lesson10.domain.Product;
import lesson10.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

	private ProductDao productDao;
	private static ProductService productServiceImpl;

	public ProductServiceImpl() {
		try {
			productDao = new ProductDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	public static ProductService getProductService() {
		if (productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}

	@Override
	public Product create(Product product) {
		return productDao.create(product);
	}

	@Override
	public Product read(Integer id) {
		return productDao.read(id);
	}

	@Override
	public Product update(Product product) {
		return productDao.update(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() {
		return productDao.readAll();
	}

	@Override
	public Map<Integer, Product> readAllMap() {
		List<Product> products = readAll();
		return products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
	}
}
