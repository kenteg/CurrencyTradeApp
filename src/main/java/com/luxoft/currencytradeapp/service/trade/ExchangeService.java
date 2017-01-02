package com.luxoft.currencytradeapp.service.trade;

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.entity.Account;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.exceptions.ExchangeRateNotFoundException;
import com.luxoft.currencytradeapp.exceptions.NotEnoughFundsException;
import com.luxoft.currencytradeapp.service.user.UserService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Khrishpens Viktor
 *         created Январь 01 2017
 */
@Service
@Transactional
public class ExchangeService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final UserService userService;

    @Autowired
    public ExchangeService(ExchangeRateRepository exchangeRateRepository, UserService userService) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.userService = userService;
    }

    @Transactional
    public void trade(User currentUser,String currencyBuy, String currencySell,String amount) throws ExchangeRateNotFoundException, NotEnoughFundsException {

        Account accSell = findAccountByCurrency(currentUser,currencySell);
        Account accBuy = findAccountByCurrency(currentUser,currencyBuy);
        float rate = findExchangeRate(currencyBuy,currencySell);
        Money withdraw = Money.of(CurrencyUnit.getInstance(currencySell),new BigDecimal(amount));
        withdraw=withdraw.multipliedBy(rate, RoundingMode.HALF_EVEN);
        accSell.withdraw(withdraw);
        accBuy.deposit(Money.of(CurrencyUnit.getInstance(currencyBuy),new BigDecimal(amount)));
        userService.saveUser(currentUser);
    }

    private float findExchangeRate(String cur1, String cur2) throws ExchangeRateNotFoundException {
        ExchangeRate ex1;
        float returnRate=0;
        ex1=exchangeRateRepository.findByCurrency1AndCurrency2(cur1,cur2);
        if(ex1!=null) { returnRate = ex1.getRate(); }
        if(ex1==null){
            ex1=exchangeRateRepository.findByCurrency1AndCurrency2(cur2,cur1);
             if(ex1!=null){ returnRate = ex1.getReverse_rate();}
        }
        if(ex1==null){
            throw new ExchangeRateNotFoundException();
        }
        return returnRate;
    }

    private Account findAccountByCurrency(User user, String cur) {
        Account account = null;
        for (Account acc: user.getAccounts()) {
            if (cur.equals(acc.getCurrencyCode())) {
                account = acc;
            }
        }
        return account;
    }
}
