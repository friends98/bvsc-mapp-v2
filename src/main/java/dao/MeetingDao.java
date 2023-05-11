package dao;

import java.util.Optional;

public interface MeetingDao<T> extends CommonDaoUpdate<T>{
	
	Optional<T> getById(Integer id);

}
