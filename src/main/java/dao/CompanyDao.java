package dao;

import java.util.Optional;

public interface CompanyDao<T> extends CommonDaoUpdate<T>{
	Optional<T> getById(Integer id);
	
}
