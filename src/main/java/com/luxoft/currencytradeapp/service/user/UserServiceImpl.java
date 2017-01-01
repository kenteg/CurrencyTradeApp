package com.luxoft.currencytradeapp.service.user;

import com.luxoft.currencytradeapp.dao.UserRepository;
import com.luxoft.currencytradeapp.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository ur;
    @Override
    public User getUser(String login) {
        /*
        7110eda4d09e062aa5e4a390b0a572ac0d2c0220
        */
        User user;
        user = ur.findBylogin(login);
        logger.debug("finded user: "+user.getLogin()+user.getPassword());
        return user;
    }

    @Override
    public void saveUser(User user) {
        ur.save(user);
    }

}