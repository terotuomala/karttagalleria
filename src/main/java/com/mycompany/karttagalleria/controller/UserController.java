package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.User;
import com.mycompany.karttagalleria.repository.RoleRepository;
import com.mycompany.karttagalleria.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tero Tuomala
 */

@Controller
@RequestMapping("addUser")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @ModelAttribute User getUser() {
        return new User();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getUsers (Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "addUser";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "addUser";
        }
        userRepository.save(user);
        return "redirect:/gallery";
    }
}
