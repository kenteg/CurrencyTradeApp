package com.luxoft.currencytradeapp.service;

import com.luxoft.currencytradeapp.entity.User;

public interface UserService {

    User getUser(String login);

}