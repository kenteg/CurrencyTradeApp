package dao;

import com.luxoft.currencytradeapp.dao.UserDao;
import com.luxoft.currencytradeapp.entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 24 2016
 */

public class userDaoTest {
    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setLogin("UserTest");
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        UserDao testUserDao = new UserDao();
        testUserDao.addUser(user);
    }

    @Test
    public void testGetUser(){
        UserDao testUserDao = new UserDao();
        assertEquals(testUserDao.getUserByName("UserTest").getLogin(),"UserTest");
    }
}
