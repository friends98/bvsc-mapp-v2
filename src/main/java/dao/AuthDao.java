package dao;

import java.util.Optional;

public interface AuthDao<T>{
	Optional<T> findByUserNameAndPassword(String username,String password);

}
