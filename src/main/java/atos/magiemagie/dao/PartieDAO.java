/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

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
public class PartieDAO {
    
    
    public boolean determineSiPlusQueUnJoueurDansPartie(long partieID){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(" select j from Joueur j join j.partie p where p.id=:idPartie "
                + "                    except"
                + "                    select j from Joueur j join j.partie p where p.id=:idPartie and j.etatjoueur=:etatPerdu");
        query.setParameter("idPartie", partieID);
        query.setParameter("etatPerdu", Joueur.EtatJoueur.PERDU);
        
       List res = query.getResultList();
       
       if(res.size() == 1){
           return true;
       }
       else
           return false;
    }
    
    public long  rechercheOrdreMaxJoueurPourPartieID(long partieID){
             EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
             Query query = em.createQuery("SELECT Max(j.ordre) FROM Joueur j join j.partie p"
                     + "                   WHERE p.id=:id");
             query.setParameter("id", partieID);
             return (long) query.getSingleResult();
    }
    
    public void ajouterPartie(Partie p){
        
            EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
     }
    
     /**
      * Liste des parties DONT AUCUN JOUEUR n'est à l'état A LA MAIN ou GAGNEE
      * @return 
      */
     
     public List<Partie> listerPartieNonDemarrees(){
     
         EntityManager em =  Persistence.createEntityManagerFactory("PU").createEntityManager();
//         Query query = em.createQuery("select p "
//                 + "                   from Partie p "
//                 + "                   where p.id NOT IN ("
//                 + "                   select p2.id"
//                 + "                   from Partie p2"
//                 + "                   join p2.joueurs j"
//                 + "                   where j.etatjoueur =: etatjoueur1"
//                 + "                         or j.etatjoueur =: etatjoueur2 ");
                //Query query = em.createQuery("SELECT p FROM Partie p ");

         Query query = em.createQuery("SELECT p FROM Partie p "
                 + "                   EXCEPT "
                 + "                   SELECT p1 FROM Partie p1 JOIN p1.joueurs j"
                 + "                   WHERE j.etatjoueur=:etat_gagne OR j.etatjoueur=:etat_alamain");  //IN (:etat_gagne,:etat_alamain)");
         query.setParameter("etat_gagne", Joueur.EtatJoueur.GAGNEE);
         query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
                 
         return query.getResultList();
//return query.getResultList();
     }

     
     //***********************
     public Partie rechercherParID(long idPartie) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        // find: pour la recherche par clé primaire
        return em.find(Partie.class, idPartie);
    }

    public List<Partie> listerPartieDemarrees() {
         EntityManager em =  Persistence.createEntityManagerFactory("PU").createEntityManager();

         Query query = em.createQuery(" SELECT p1 FROM Partie p1 JOIN p1.joueurs j"
                 + "                   WHERE j.etatjoueur=:etat_gagne OR j.etatjoueur=:etat_alamain");  //IN (:etat_gagne,:etat_alamain)");
         query.setParameter("etat_gagne", Joueur.EtatJoueur.GAGNEE);
         query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
                 
         return query.getResultList();

         
         
         
         
    }
    
}
