package lesson10.dao;

import java.util.List;

import lesson10.domain.Bucket;
import lesson10.shared.AbstractCRUD;

public interface BucketDao extends AbstractCRUD<Bucket> {
	List<Bucket> readByUserId(Integer userId);
}
