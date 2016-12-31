package com.luxoft.currencytradeapp.controller;

/**
 * Created by omsk16 on 12/29/2016.
 */

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.dao.UserRepository;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.service.UserService;
import com.luxoft.currencytradeapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    UserService userService;

    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start( Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("trade");
        User currentUser = userService.getUser(principal.getName());
        ExchangeRate exchangeRate = exchangeRateRepository.findByCurrency1AndCurrency2("USD","RUR");
        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        modelAndView.addObject("rates",rates);
        modelAndView.addObject("rurtousd",exchangeRate.getRate());
        modelAndView.addObject("accounts",currentUser.getAccounts());
        return modelAndView;
    }
}
