package com.luxoft.currencytradeapp.controller;

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

/**
 * @author Khrishpens Viktor
 *         created Декабрь 31 2016
 */
@Controller
@RequestMapping("/tradeform")
public class TradeFormController {
    private final
    UserService userService;

    private final
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public TradeFormController(UserService userService, ExchangeRateRepository exchangeRateRepository) {
        this.userService = userService;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(@RequestParam("selected_cur") String selected_cur){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tradeform");
        modelAndView.addObject("selected_cur",selected_cur);
        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        Set<String> currencyCodes = new HashSet<>();
        for(ExchangeRate rate:rates){
            currencyCodes.add(rate.getCurrency1());
            currencyCodes.add(rate.getCurrency2());
        }
        currencyCodes.remove(selected_cur);
        modelAndView.addObject("currencies",currencyCodes);
        return modelAndView;
    }

}
