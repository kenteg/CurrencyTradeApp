package com.luxoft.currencytradeapp.dao;

import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Khrishpens Viktor
 *         created Январь 03 2017
 */
public interface OperationRepository extends CrudRepository<Operation,Integer> {
    List<Operation> findAll();
}
