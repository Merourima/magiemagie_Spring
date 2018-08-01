/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "ListeDesJoueursServlet", urlPatterns = {"/ListeDesJoueursServlet"})
    public class ListeDesJoueursServlet extends AutowireServlet {
    private PartieService partieService = new  PartieService();
    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Récuperer la partie et vérifier si la partie démarré ou nn pour raffréchir les pages 
        Partie p = (Partie) req.getSession().getAttribute("partie");
        if (p == null) {
            resp.sendRedirect("ListeDesJoueursServlet");
        } else {
        p = partieService.recupererLaPartie(p.getId());
        req.getSession().setAttribute("partie", p);
            if (p.siPartieDemarre()) {
                resp.sendRedirect("JouerPartie");
            } else {
                req.getRequestDispatcher("lister-joueur.jsp").forward(req, resp);
            }
        }

    }
}
