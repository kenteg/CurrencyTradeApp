package com.luxoft.currencytradeapp.dao;

import com.luxoft.currencytradeapp.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 24 2016
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findBylogin(String login);
}
