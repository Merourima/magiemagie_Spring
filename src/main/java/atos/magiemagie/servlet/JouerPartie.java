/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
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
@WebServlet(name = "JouerPartie", urlPatterns = {"/JouerPartie"})
public class JouerPartie extends AutowireServlet {
    @Autowired
    private PartieService partieService ;
    @Autowired
    private CarteService carteService ;
    @Autowired
    private JoueurService joueurService ;

      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Partie p = (Partie) req.getSession().getAttribute("partie");
        // Action
        if(req.getParameter("passerTour")!= null){
            
             Joueur joueurALaMain = joueurService.determineJoueurQuiALaMainDansPArtie(p.getId());
             carteService.distribuerCarte(joueurALaMain.getId());
             partieService.passeJoueurSuivant(p.getId());
             req.getSession().setAttribute("jrAlaMain",joueurALaMain);   
        }
        else if(req.getParameter("lancerSort")!= null){
                //TODO
            }
            
       resp.sendRedirect("JouerPartie");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Partie p = (Partie) req.getSession().getAttribute("partie");

        if (!p.siPartieDemarre()) {
            partieService.demarrerPartie(p.getId());
        }
        
        // MAJ DE LA PARTIE
        p = partieService.recupererLaPartie(p.getId());
        req.getSession().setAttribute("partie", p);
        
        Joueur monCompte = (Joueur) req.getSession().getAttribute("moi");
        for (Joueur jr : p.getJoueurs()) {
            if (jr.getId().equals(monCompte.getId())) {
                monCompte = jr;
                break;
            }
        }
        req.getSession().setAttribute("moi", monCompte);
//        req.getSession().setAttribute("partie",p);
        
        req.getRequestDispatcher("jouer-Partie.jsp").forward(req, resp);
    }

}
