package dao;

import java.util.List;

public interface ShareHolderDao<T> extends CommonDao<T> {

	public List<T> searchByIdentityCard(String identityCard);

}
