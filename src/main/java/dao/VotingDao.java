package dao;

import java.util.List;

public interface VotingDao<T> extends CommonDaoUpdate<T> {
	
	public List<T> getByIdMeeting(String idMeeting);
}
