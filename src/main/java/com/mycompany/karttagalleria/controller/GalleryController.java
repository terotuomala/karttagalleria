package com.mycompany.karttagalleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tero Tuomala
 */

@Controller
@RequestMapping("gallery")
public class GalleryController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getMapItems() {
        return "gallery";
    }
    
}
