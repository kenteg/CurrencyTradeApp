package com.luxoft.currencytradeapp.service.user;

import com.luxoft.currencytradeapp.entity.User;

public interface UserService {

    User getUser(String login);

    void saveUser(User user);

}