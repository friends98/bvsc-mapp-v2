package dao;

import java.util.List;

public interface ElectionDao<T> extends CommonDaoUpdate<T> {
	
	public List<T> getByIdMeeting(Integer idMeeting);


}
