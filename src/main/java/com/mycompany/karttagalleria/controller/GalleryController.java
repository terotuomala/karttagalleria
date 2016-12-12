package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tero Tuomala
 */

@Controller
@RequestMapping("gallery")
public class GalleryController {
    
    @Autowired
    MapRepository mapRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String getMaps(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("maps", mapRepository.findAll());
        model.addAttribute("username", name);
        return "gallery";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMap(@PathVariable Long id, Model model) {
        model.addAttribute("map", mapRepository.findOne(id));
        return "map";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMap(@PathVariable Long id) {
        mapRepository.delete(id);
        return "redirect:/gallery";
    }
    
}
