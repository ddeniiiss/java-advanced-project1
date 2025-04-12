package lesson10.service;

import java.util.List;

import lesson10.domain.Bucket;
import lesson10.shared.AbstractCRUD;

public interface BucketService extends AbstractCRUD<Bucket> {
	List<Bucket> readByUserId(Integer userId);
}
