package data.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;

public class TokenDaoImpl implements TokenDaoExtended {

	@Autowired
	private TokenDao tokenDao;

	@Override
	public void deleteIsNotValidToken() {
		List<Token> tokens = tokenDao.findAll();
		for(int i=0; i< tokens.size(); i++){
			if (!tokens.get(i).isValid()) {
				tokenDao.delete(tokens.get(i));
			}
		}
	}
}
