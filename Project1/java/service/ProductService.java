package lesson10.service;

import java.util.Map;

import lesson10.domain.Product;
import lesson10.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {
	public Map<Integer, Product> readAllMap();
}
