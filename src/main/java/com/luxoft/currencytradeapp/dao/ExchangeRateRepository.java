package com.luxoft.currencytradeapp.dao;

import com.luxoft.currencytradeapp.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by omsk16 on 12/30/2016.
 */
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate,Integer>{
    ExchangeRate findByCurrency1AndCurrency2(String currency1,String currency2);
}
