package com.mycompany.karttagalleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tero Tuomala
 */

@Controller
public class DefaultController {
    
    @RequestMapping("*")
    public String handeDefault() {
        return "redirect:/gallery";
    }
}
