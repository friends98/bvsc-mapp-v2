package dao;

import java.util.List;

public interface FeedbackDao<T> extends CommonDaoUpdate<T>{
	public List<T> getFeedbackByMeeting(Integer idMeeting);
	
}
