package dao;

import java.util.Optional;

public interface AdminDao<T>{
	Optional<T> findByUserNameAndPassword(String username,String password);
}
