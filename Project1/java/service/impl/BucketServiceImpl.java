package lesson10.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lesson10.dao.BucketDao;
import lesson10.dao.impl.BucketDaoImpl;
import lesson10.domain.Bucket;
import lesson10.service.BucketService;

public class BucketServiceImpl implements BucketService {
	private static final Logger LOGGER = LogManager.getLogger(BucketServiceImpl.class);

	private BucketDao bucketDao;
	private static BucketService bucketServiceImpl;

	public BucketServiceImpl() {
		try {
			bucketDao = new BucketDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	public static BucketService getBucketService() {
		if (bucketServiceImpl == null) {
			bucketServiceImpl = new BucketServiceImpl();
		}
		return bucketServiceImpl;
	}

	@Override
	public Bucket create(Bucket bucket) {
		return bucketDao.create(bucket);
	}

	@Override
	public Bucket read(Integer id) {
		return bucketDao.read(id);
	}

	@Override
	public Bucket update(Bucket bucket) {
		return bucketDao.create(bucket);
	}

	@Override
	public void delete(Integer id) {
		bucketDao.delete(id);
	}

	@Override
	public List<Bucket> readAll() {
		return bucketDao.readAll();
	}

	@Override
	public List<Bucket> readByUserId(Integer userId) {
		return bucketDao.readByUserId(userId);
	}

}
