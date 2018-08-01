/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "listePartieServlet", urlPatterns = {"/listePartieServlet"})

public class listePartieServlet extends AutowireServlet {
    
    @Autowired
    private PartieService servicePartie ;
    @Autowired
    private JoueurService jrServ ;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//        req.getSession().invalidate();
        
        //créer une partie
        String nompartie = req.getParameter("nomPartie");
        
        Partie p = servicePartie.creerNouvellePartie(nompartie);
        
        resp.sendRedirect("listePartieServlet");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Partie> listPartNonDem = servicePartie.listerPartieNonDemarrees();
        
        //Long idpartie = jrServ 
        req.setAttribute("listeParNoDema", listPartNonDem);
        req.getRequestDispatcher("liste-Partie.jsp").forward(req, resp);
    }
}
