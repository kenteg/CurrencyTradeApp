package com.luxoft.currencytradeapp.controller;

/**
 * Created by omsk16 on 12/29/2016.
 */

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.exceptions.ExchangeRateNotFoundException;
import com.luxoft.currencytradeapp.exceptions.NotEnoughFundsException;
import com.luxoft.currencytradeapp.service.trade.ExchangeService;
import com.luxoft.currencytradeapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/trade")
public class TradeController {
    private final
    UserService userService;

    private final
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public TradeController(UserService userService, ExchangeRateRepository exchangeRateRepository) {
        this.userService = userService;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start( Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trade");
        User currentUser = userService.getUser(principal.getName());
        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        Set<String> currencyCodes = new HashSet<>();
        for(ExchangeRate rate:rates){
            currencyCodes.add(rate.getCurrency1());
            currencyCodes.add(rate.getCurrency2());
        }
        modelAndView.addObject("currencies",currencyCodes);
        modelAndView.addObject("rates",rates);
        modelAndView.addObject("accounts",currentUser.getAccounts());
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
        ExchangeService exService = new ExchangeService(exchangeRateRepository, userService);
        User currentUser = userService.getUser(principal.getName());
        try {
            exService.trade(currentUser,currentBuy,selectPayCur,amount);
        } catch (ExchangeRateNotFoundException | NotEnoughFundsException e) {
            modelAndView.addObject("exception",e.getMessage());
        }

        return modelAndView;
    }
}
