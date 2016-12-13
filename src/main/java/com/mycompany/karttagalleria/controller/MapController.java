package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
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
@RequestMapping("addMap")
public class MapController {
    
    @Autowired
    MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @ModelAttribute Map getMap() {
        return new Map();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addMap";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addMap(@Valid @ModelAttribute Map map, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "addMap";
        }
        
        mapRepository.save(map);
        return "redirect:/gallery";
    }
}
