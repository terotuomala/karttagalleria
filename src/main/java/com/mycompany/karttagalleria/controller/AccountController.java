package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Account;
import com.mycompany.karttagalleria.repository.AccountRepository;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.service.AccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tero Tuomala
 */

@Controller
@RequestMapping("account")
public class AccountController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    AccountService accountService;
    
    @ModelAttribute Account getAccount() {
        return new Account();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable Long id) {
        accountRepository.delete(id);
        return "redirect:/account/list";
    }
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "accounts";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAccount(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "addAccount";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "addAccount";
        }
        accountService.saveAccount(account);
        return "redirect:/account/list";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAccount(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountRepository.findOne(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "editAccount";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateAccount(@Valid @ModelAttribute Account account, @PathVariable Long id, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "editAccount";
        }
        
        accountService.updateAccount(accountRepository.getOne(id), account);
        return "redirect:/gallery";
    }
}
