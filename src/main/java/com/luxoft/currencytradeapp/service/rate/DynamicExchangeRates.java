package com.luxoft.currencytradeapp.service.rate;

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Scheduled(fixedDelayString = "#{propsbean.interval}")
    @Transactional
    public void ChangeRates(){
        List<ExchangeRate> rates = exchangeRateRepository.findAll();

        for(ExchangeRate rate:rates){
            float curRate = rate.getRate();
            float plusminus = rnd.nextFloat();
            int delta = randomInRange(0,59);
            float fdelta=(float)delta/100;
            fdelta= (float) (Math.round(fdelta*100.0)/100.0);
            if(plusminus < 0.5){
                if((curRate-fdelta)<0){
                    return;
                }
            rate.setRate(curRate-fdelta);
            rate.setReverse_rate(1/rate.getRate());
                }
            else {
                rate.setRate(curRate+fdelta);
                rate.setReverse_rate(1/rate.getRate());
                }
        }
    }

    public int randomInRange(int min, int max) {
        int range = max - min + 1;
        int scaled = rnd.nextInt() % range;
        int shifted = scaled + min;
        return shifted;
    }
}
