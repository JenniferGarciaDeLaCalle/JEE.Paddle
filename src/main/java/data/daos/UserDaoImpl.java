package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;
import data.entities.User;

public class UserDaoImpl implements UserDaoExtended {

	@Autowired 
	private TokenDao tokenDao;
	
	@Override
	public User findByTokenValueIsValid(String tokenValue) {
		Token token = tokenDao.findByValue(tokenValue);
        if (token != null && token.isValid()) {
            return token.getUser();
        } else {
            return null;
        }
	}

}
