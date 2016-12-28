package com.mycompany.karttagalleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tero Tuomala
 * @version 1.0
 */

@Controller
public class DefaultController {
    
    // Redirects requests to galleryController
    @RequestMapping("*")
    public String handeDefault() {
        return "redirect:/gallery";
    }
}
