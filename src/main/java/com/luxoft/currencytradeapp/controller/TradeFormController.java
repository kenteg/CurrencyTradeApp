package com.luxoft.currencytradeapp.controller;

import com.luxoft.currencytradeapp.dao.ExchangeRateRepository;
import com.luxoft.currencytradeapp.entity.ExchangeRate;
import com.luxoft.currencytradeapp.entity.User;
import com.luxoft.currencytradeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 31 2016
 */
@Controller
@RequestMapping("/tradeform")
public class TradeFormController {
    @Autowired
    UserService userService;

    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tradeform");
        return modelAndView;
    }
}
