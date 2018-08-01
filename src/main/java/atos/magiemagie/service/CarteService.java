/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.JoueurDAOCrud;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Carte.TypeIngredient;
import atos.magiemagie.entity.Joueur;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */
@Service
public class CarteService {
    
    private PartieDAO dao = new PartieDAO();
    private JoueurService dao1 = new JoueurService();
    private JoueurDAO daoJr = new JoueurDAO();
    @Autowired
    private JoueurDAOCrud jrDaoCrud ;
    private CarteDAO daoCarte = new CarteDAO();
    
    
    
    public void supprimerCarteJr(long caretId){
         daoCarte.supprimerCarte(caretId);
    }
    
    // cartes au hasard
    @Transactional
    public Carte distribuerCarte(long idJoueur) {

        //0. Récup joueur
        Joueur j = jrDaoCrud.findOne(idJoueur); //rechercheParId(idJoueur);
        
        // 1. Générer nouvelle carte
        TypeIngredient[] tabCarteIng = TypeIngredient.values();
        Random r = new Random();
        int n = r.nextInt(tabCarteIng.length);
        Carte c = new Carte();
        c.setTypeIngredient(tabCarteIng[n]);

        // 2. Associe la carte au joueur et vice-versa
        j.getCartes().add(c);
        c.setJoueur(j);

        // 3. Persiste la carte
       // daoJr.modifier(j);
        daoCarte.ajouterCarte(c);
        return (Carte) c;
    }
      public Carte prendreUneCarteDunJoueur(long idJoueur, long idVictime) {

        //0. Récup joueur
        Joueur jAct = jrDaoCrud.findOne(idJoueur);//daoJr.rechercheParId(idJoueur);
        Joueur jVictime = daoJr.rechercheParId(idVictime);
        
        // 1. Prendre une carte d'un joueur
        Random r = new Random();
        int index = r.nextInt(jVictime.getCartes().size());
        Carte carte = jVictime.getCartes().get(index);

        // 2. Associe la carte au joueur et vice-versa
        //jAct.getCartes().add(c);
        carte.setJoueur(jAct);

        // 3. Persiste la carte
       // daoJr.modifier(j);
        carte =  daoCarte.modifierCarte(carte);
        
        if (jVictime.getCartes().size() == 0){                    //ou 1
           jVictime = jrDaoCrud.findOne(idJoueur);//daoJr.rechercheParId(idVictime);
           jVictime.setEtatjoueur(Joueur.EtatJoueur.PERDU);
           daoJr.modifier(jVictime);
        }
        
        return  carte;
    }
      
      



//    
    
}
