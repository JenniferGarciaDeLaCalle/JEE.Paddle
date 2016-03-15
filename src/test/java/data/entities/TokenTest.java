package data.entities;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void tokenIsValid(){
    	User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.isValid());
    }
    
    @Test
    public void tokenIsNotValid(){
    	User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, - (token.getTIMEEXPIRE() + 1));
        token.setCreateTime(calendar);
        assertFalse(token.isValid());
    }

}
