package dao;

import java.util.List;
import java.util.Optional;



public interface CommonDao<T> {
	List<T> getAll();

	Optional<T> get(String id);

	void save(T t);

	void update(T t);

	void delete(T t);
	
	
	
}
