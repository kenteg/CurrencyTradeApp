package com.luxoft.currencytradeapp.controller;

/**
 * Created by omsk16 on 12/29/2016.
 */

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.dao.OperationRepository;
import com.luxoft.currencytradeapp.entity.Account;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.Operation;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.exceptions.ExchangeRateNotFoundException;
import com.luxoft.currencytradeapp.exceptions.NotEnoughFundsException;
import com.luxoft.currencytradeapp.service.trade.ExchangeService;
import com.luxoft.currencytradeapp.service.user.UserService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.enterprise.inject.Produces;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/trade")
@Transactional
public class TradeController {
    private final
    UserService userService;
    private final
    ExchangeRateRepository exchangeRateRepository;
    private final
    OperationRepository operationRepository;
    private @Value("#{propsbean.default_rur_balance}") String rur_balance;
    private @Value("#{propsbean.default_eur_balance}") String eur_balance;
    private @Value("#{propsbean.default_usd_balance}") String usd_balance;


    @Autowired
    public TradeController(UserService userService, ExchangeRateRepository exchangeRateRepository, OperationRepository operationRepository) {
        this.userService = userService;
        this.exchangeRateRepository = exchangeRateRepository;
        this.operationRepository = operationRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(Principal principal, @RequestParam(value = "page", required = false) Integer pageNum){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trade");
        User currentUser = userService.getUser(principal.getName());
        initBalances(currentUser);
        List<ExchangeRate> rates = exchangeRateRepository.findAll();

        Map<String,String> colors = new HashMap<>();
        for(ExchangeRate rate:rates){
            if(rate.getOld_rate()>rate.getRate()){
                modelAndView.addObject(rate.getCurrency1()+rate.getCurrency2()+"color","red");
                colors.put(rate.getCurrency1()+rate.getCurrency2(),"red");
            }
            else
            if (rate.getOld_rate()<rate.getRate()){
                modelAndView.addObject(rate.getCurrency1()+rate.getCurrency2()+"color","green");
                colors.put(rate.getCurrency1()+rate.getCurrency2(),"green");
            }
            else
            if (rate.getOld_rate()==rate.getRate()){
                modelAndView.addObject(rate.getCurrency1()+rate.getCurrency2()+"color","grey");
                colors.put(rate.getCurrency1()+rate.getCurrency2(),"grey");
            }
        }

        Set<String> currencyCodes = new HashSet<>();
        for(ExchangeRate rate:rates){
            currencyCodes.add(rate.getCurrency1());
            currencyCodes.add(rate.getCurrency2());
        }
        if (pageNum==null) {pageNum=1;};
        PageRequest pagReq = new PageRequest(pageNum-1,20, Sort.Direction.DESC,"Id");
        Page page = operationRepository.findAll(pagReq);
        modelAndView.addObject("currentpage",pageNum);
        modelAndView.addObject("total",page.getTotalPages());
        modelAndView.addObject("page",page);
        modelAndView.addObject("currencies",currencyCodes);
        modelAndView.addObject("rates",rates);
        modelAndView.addObject("accounts",currentUser.getAccounts());
        modelAndView.addObject("colors",colors);
        return modelAndView;
    }

    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    public ModelAndView trade(@RequestParam(value="currentbuy", required=false) String currentBuy,
                              @RequestParam(value="amount", required=false) String amount,
                              @RequestParam(value="selectPayCur", required=false) String selectPayCur,
                              Principal principal
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        ExchangeService exService = new ExchangeService(exchangeRateRepository, userService, operationRepository);
        User currentUser = userService.getUser(principal.getName());
        try {
            exService.trade(currentUser,currentBuy,selectPayCur,amount);
        } catch (ExchangeRateNotFoundException | NotEnoughFundsException e) {
            modelAndView.addObject("exception",e.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping(value = "/rates",method = RequestMethod.GET,produces = "application/json")
    public  @ResponseBody List<ExchangeRate> allRates(){
        return exchangeRateRepository.findAll();
    }

    @Transactional
    private void initBalances(User currentuser){
        if(currentuser.getFirstlogin()){
            currentuser.getAccounts().get(0).setBalance(Money.of(CurrencyUnit.of("RUR"),new BigDecimal(this.rur_balance)));
            currentuser.getAccounts().get(1).setBalance(Money.of(CurrencyUnit.of("EUR"),new BigDecimal(this.eur_balance)));
            currentuser.getAccounts().get(2).setBalance(Money.of(CurrencyUnit.of("USD"),new BigDecimal(this.usd_balance)));
            currentuser.setFirstlogin(false);
        }
    }
}
