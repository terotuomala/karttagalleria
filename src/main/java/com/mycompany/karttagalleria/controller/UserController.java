package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.service.AccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mycompany.karttagalleria.repository.AccountRepository;

/**
 *
 * @author Tero Tuomala
 */

@Controller
@RequestMapping("addUser")
public class UserController {
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    AccountService userService;
    
    @ModelAttribute Account getUser() {
        return new Account();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUsers (Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "addUser";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute Account user, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "addUser";
        }
        userService.saveUser(user);
        return "redirect:/gallery";
    }
}
