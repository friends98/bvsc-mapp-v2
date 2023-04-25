/**
 * 
 */
package dao;

import java.util.Optional;

/**
 * @author quangdaongoc
 *
 */
public interface AccountDao {
	
	boolean checkValidAccount(String userName,String password);
	boolean checkAccountByUserName(String username);
	Optional<?> login();
	void logout();

}

