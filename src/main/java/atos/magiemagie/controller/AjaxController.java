/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.controller;

import atos.magiemagie.dto.JoueurALAMainDTO;
import atos.magiemagie.service.JoueurService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrateur
 */
@Controller
public class AjaxController {
    
//    @Autowired
//    private JoueurService jrService;
    
    @RequestMapping(value = {"/ajax-quialamain"}, method = RequestMethod.GET)
    public JoueurALAMainDTO dertermineJoueurALaMain(){
        JoueurALAMainDTO jrDTO = new  JoueurALAMainDTO();
        
        jrDTO.setIdJoueur(1L);
        jrDTO.setNomJoueur("youyoyuyoyuoy");
        
        return jrDTO;
    }
}
