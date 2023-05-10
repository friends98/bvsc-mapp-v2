package dao;

import java.util.List;
import java.util.Optional;

public interface CommonDaoUpdate<T> {
	List<T> getAll();

	Optional<T> getById(String id);

	Integer save(T t);

	Integer update(T t);

	Integer delete(T t);

}
