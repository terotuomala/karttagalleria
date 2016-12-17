package com.mycompany.karttagalleria.controller;

import com.mycompany.karttagalleria.domain.Map;
import com.mycompany.karttagalleria.repository.CategoryRepository;
import com.mycompany.karttagalleria.repository.CoordinateSystemRepository;
import com.mycompany.karttagalleria.repository.MapRepository;
import com.mycompany.karttagalleria.service.MapService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("map")
public class MapController {
    
    @Autowired
    MapRepository mapRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    CoordinateSystemRepository coordinateSystemRepository;
    
    @Autowired
    MapService mapService;
    
    @ModelAttribute Map getMap() {
        return new Map();
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
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("coordinateSystems", coordinateSystemRepository.findAll());
        return "addMap";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMap(@Valid @ModelAttribute Map map, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("coordinateSystems", coordinateSystemRepository.findAll());
            return "addMap";
        }
        
        mapService.saveMap(map);
        return "redirect:/gallery";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMap(@PathVariable Long id, Model model) {
        model.addAttribute("map", mapRepository.findOne(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("coordinateSystems", coordinateSystemRepository.findAll());
        return "editMap";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateMap(@Valid @ModelAttribute Map map, @PathVariable Long id, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("coordinateSystems", coordinateSystemRepository.findAll());
            return "editMap";
        }
        
        mapService.updateMap(mapRepository.getOne(id), map);
        return "redirect:/gallery";
    }
    
}
