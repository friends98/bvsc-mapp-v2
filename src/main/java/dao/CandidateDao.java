package dao;

import java.util.List;

import model.response.CandidateResponse;

public interface CandidateDao<T> extends CommonDaoUpdate<T> {
	List<T> findAllCandidateByIdMeeting(String idMeeting);

}
