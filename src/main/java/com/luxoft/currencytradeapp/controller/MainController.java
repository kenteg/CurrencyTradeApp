package com.luxoft.currencytradeapp.controller;

import com.luxoft.currencytradeapp.dao.UserRepository;
import com.luxoft.currencytradeapp.entity.Account;
import com.luxoft.currencytradeapp.entity.User;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class MainController {
    private final
    UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
       /* User newUser = new User("newUser","7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        CurrencyUnit rur = CurrencyUnit.of("RUR");
        Money balanceRur = Money.of(rur,3000);
        Account acc1 = new Account(balanceRur);
        newUser.getAccounts().add(acc1);

        userRepository.save(newUser);*/

        return "index";
    }

}