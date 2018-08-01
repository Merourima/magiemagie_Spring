/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
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

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})

public class LoginServlet extends AutowireServlet {
    private JoueurService joueurservice = new JoueurService();
    private PartieService joueurPartie = new  PartieService();
    private PartieDAO partiedao = new PartieDAO();
    private PartieService partieservice = new PartieService();
    private CarteService joueurCarte= new  CarteService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        String pseudo = req.getParameter("pseudo");
        String avatar = req.getParameter("radio");
        
        Long idPartie = (Long) req.getSession().getAttribute("idPartie");
        
        //********************************************
        
        Joueur joueur = joueurservice.rejoindrePartie(pseudo, idPartie, avatar);
        req.getSession().setAttribute("moi", joueur);
        
        
        if (joueur.getCartes() != null && !(joueur.getCartes().isEmpty()) ) {
            for (int i = 0; i < joueur.getCartes().size(); i++) {
                joueurCarte.supprimerCarteJr(joueur.getCartes().get(i).getId());

            }
        }
        
        Partie p = partieservice.recupererLaPartie(idPartie);
        req.getSession().setAttribute("partie", p);
        
        
        // ?????????????????????  Tester avant si nbrJR > 2  ???????????????????
        resp.sendRedirect("ListeDesJoueursServlet");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //vider les cartes du jrs existe dans la bdd 
        
        Long idPartie = Long.parseLong(req.getParameter("idPartie"));
        req.getSession().setAttribute("idPartie", idPartie);
        req.getRequestDispatcher("loginJoueur.jsp").forward(req, resp);
        
    }
    
}
