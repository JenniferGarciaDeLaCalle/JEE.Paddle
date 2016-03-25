package data.daos;

import data.entities.User;

public interface UserDaoExtended {

	public User findByTokenValueIsValid(String usernameOrEmailOrTokenValue);
	
}
