/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    
    public void ajouterCarte(Carte carte) {
        
         EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         em.getTransaction().begin();
         em.persist(carte);
         em.flush();
         em.getTransaction().commit();     
    }
    
    public Carte modifierCarte(Carte carte) {
         EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         em.getTransaction().begin();
         em.merge(carte);
         em.flush();
         em.getTransaction().commit();   
         return carte;
    }

    

    public List<Carte> listerCartesJoueurs(long joueurID) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
//            Query query = em.createQuery(" SELECT j FROM Joueur j JOIN j.cartes c"
//                   + "                    WHERE c.joueur_id=j.id ");
             Query query = em.createQuery(" SELECT c FROM Carte c "
                     + "                    JOIN c.joueur j "
                   + "                      WHERE j.id=:idjoueur ");
             query.setParameter("idjoueur", joueurID);
            return   query.getResultList();
    }

    public Carte rechercheParIDCarte(long idCarte1) {
                EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
                return em.find(Carte.class, idCarte1);
    }

    public void supprimerCarte(long carteId) {
            EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
            em.getTransaction().begin();
            Carte carteJR = em.find(Carte.class,carteId);
            em.remove(carteJR);
            em.getTransaction().commit();
            
//           Query query = em.createQuery("DELETE FROM Carte c WHERE c IN (SELECT j FROM Joueur j JOIN j.cartes jc "
//                   + "WHERE j.cartes=j.id)");
           
//            Query query = em.createQuery("DELETE  from Carte c  JOIN c.joueur j where j.id=:jr");
//            query.setParameter("jr", joueurID);
//            
//            Query query = em.createQuery(" select c from carte c");
                   
           

    }

    
}
