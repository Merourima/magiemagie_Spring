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
import atos.magiemagie.dao.PartieDAOCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */

@Service
public class JoueurService {
    
    @Autowired
    private PartieDAOCrud partieDaoCrud;
    private PartieDAO partiedao = new PartieDAO();
    @Autowired
    private JoueurDAOCrud jrDAOCrud;
    private JoueurDAO joueurDao = new JoueurDAO();
    
    private CarteDAO caretdao = new CarteDAO();
    
//    public void infoJoueurDeLaPArtie(long idpartie){
//           Partie p = partiedao.rechercherParID(idpartie);
//           List<Joueur> joueurDeLaPartie = p.getJoueurs();
//           
//           for (Joueur joueur : joueurDeLaPartie) {
//                if(!joueur.getEtatjoueur().equals("A_LA_MAIN")){
//                    System.out.println(" joueur : " +joueur.toString()+ "!!!!!!!!!!! NBR  DE CARTES!!!!!!!!!!!");
//                }
//                if(joueur.getEtatjoueur().equals("A_LA_MAIN")){
//                    System.out.println(" Liste Cartes est:  " +caretdao.listerCartesJoueurs(idpartie) );
//                }
//        }
//    }
    public Joueur determineJoueurQuiALaMainDansPArtie(long idPartie){
       // return joueurDao.determineJoueurQuiALaMainDansPArtie(idPartie);
       return jrDAOCrud.determineJoueurQuiALaMainDanspartie(idPartie);
    }
    @Transactional
    public Joueur rejoindrePartie( String pseudo,long idPartie,String avatar){
    
      //recherche si le joueur existe déjà
      //Joueur joueur = joueurDao.rechercherParPseudo(pseudo);
      Joueur joueur = jrDAOCrud.findOneByPseudo(pseudo);
      
      if (joueur == null){
      // le joueur n'existe pas encore
          joueur = new Joueur();
          joueur.setPseudo(pseudo);
      }
      
      joueur.setAvatar(avatar);
      joueur.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
      //joueur.setOrdre(joueurDao.rechercheOrdreNouveauJoueurPourPartieID(idPartie));
      Long jrNew = jrDAOCrud.rechercheOrdreNouveauJoueurPourpartie(idPartie);
      
      if( jrNew != null){
          joueur.setOrdre(jrDAOCrud.rechercheOrdreNouveauJoueurPourpartie(idPartie));
      }
//      else
//          throw new RuntimeException("N'existe pas");
      
      //Assicie le joueur a la partie et vice_versa (JPA relations bidirec...)
      Partie partie = partieDaoCrud.findOne(idPartie);// partiedao.rechercherParID(idPartie);
      joueur.setPartie(partie);
      
      //partie.getJoueurs().add(joueur);  equi ==
      List<Joueur> listJoueurs = partie.getJoueurs();
      listJoueurs.add(joueur);
      
      if(joueur.getId() == null){
      
         jrDAOCrud.save(joueur);
      }
      else{
           jrDAOCrud.save(joueur);
      }
      return joueur;
    }
}
