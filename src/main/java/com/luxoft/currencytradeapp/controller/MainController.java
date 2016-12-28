package com.luxoft.currencytradeapp.controller;

import com.luxoft.currencytradeapp.dao.UserRepository;
import com.luxoft.currencytradeapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model, Principal principal){

        User currentUser = userRepository.findBylogin(principal.getName());

        model.addAttribute("accounts",currentUser.getAccounts());

        return "index";
    }

}