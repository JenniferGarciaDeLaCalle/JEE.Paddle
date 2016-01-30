package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.entities.Token;
import business.entities.User;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoITest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testCreate() {
        daosService.createUsers(3);
        assertTrue(userDao.count() >= 3);
    }

    @Test
    public void testFindDistinctByUsernameOrEmail() {
        User[] users = daosService.createUsers(3);
        assertNotNull(userDao.findByUsernameOrEmail(users[0].getUsername()));
        assertNotNull(userDao.findByUsernameOrEmail(users[1].getEmail()));
        assertNull(userDao.findByUsernameOrEmail("kk"));
    }

    @Test
    public void testFindByTokenValue() {
        Token[] tokens = daosService.createTokensAndUsers(3);
        assertEquals(tokens[1].getUser(), userDao.findByTokenValue(tokens[1].getValue()));
        assertNull(userDao.findByTokenValue("kk"));
    }

    @After
    public void deleteAll() {
        daosService.deleteAll();
    }

}
