package dao;

import java.util.List;
import java.util.Optional;

public interface ShareHolderDao<T> extends CommonDao<T>,AuthDao<T> {

	public List<T> searchByIdentityCard(String identityCard);

	Optional<T> findByUserNameAndPassword(String username, String password);

}
