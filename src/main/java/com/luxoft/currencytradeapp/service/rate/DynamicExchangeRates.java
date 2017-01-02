package com.luxoft.currencytradeapp.service.rate;

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Khrishpens Viktor
 *         created Январь 02 2017
 */
@Configuration
@EnableScheduling
public class DynamicExchangeRates {
    private final ExchangeRateRepository exchangeRateRepository;
    private Random rnd;
    @Autowired
    public DynamicExchangeRates(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        rnd= new Random();
    }


    @Scheduled(fixedDelay = 30000)
    public void ChangeRates(){
        List<ExchangeRate> rates = exchangeRateRepository.findAll();

        for(ExchangeRate rate:rates){
            float curRate = rate.getRate();
            rate.setRate(curRate*randomInRange(0.85f,1.15f));
            rate.setReverse_rate(1/rate.getRate());
            exchangeRateRepository.save(rate);
        }

    }

    public float randomInRange(float min, float max) {
        float range = max - min;
        float scaled = rnd.nextFloat() * range;
        float shifted = scaled + min;
        return shifted;
    }
}
