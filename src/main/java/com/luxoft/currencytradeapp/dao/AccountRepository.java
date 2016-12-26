package com.luxoft.currencytradeapp.dao;

import com.luxoft.currencytradeapp.entity.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 24 2016
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
