package dao;

import java.util.List;
import java.util.Optional;

public interface ShareHolderDao<T> extends CommonDaoUpdate<T>,AuthDao<T> {

	public List<T> getByIdMeeting(String idMeeting);

	Optional<T> findByUserNameAndPassword(String username, String password);
	
	public Optional<T> getByIdentityCard(String identityCard);

}	
