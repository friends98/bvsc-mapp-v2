package dao;

import java.util.Optional;

import model.entity.UserInfo;

public interface AuthDao<T>{
	Optional<UserInfo> findByUserNameAndPassword(String username,String password);

}
