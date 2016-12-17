package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("maps", mapRepository.findAll());
        return "gallery";
        
    }
    
}
