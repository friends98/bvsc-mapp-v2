package dao;

import java.util.List;

public interface CandidateDao<T> extends CommonDaoUpdate<T> {
	List<T> findAllCandidateByIdElection(String idElection);
	Integer deleteByIdElection(T t);
	List<T> getCandidateByMeeting(Integer idMeeting);
}