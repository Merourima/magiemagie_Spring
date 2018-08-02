/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.CarteDAOCrud;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.JoueurDAOCrud;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.dao.PartieDAOCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Carte.TypeIngredient;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PartieService {

    @Autowired 
    private PartieDAOCrud partiedaoCrud;
    private PartieDAO partiedao = new PartieDAO();
    private JoueurService joueurService = new JoueurService();
    @Autowired
    private JoueurDAOCrud jrDaoCrud;
    private JoueurDAO joueurDao = new JoueurDAO();
    @Autowired
    private CarteDAOCrud carteDaoCrud;
    private CarteDAO carteDao = new CarteDAO();
    @Autowired
    private CarteService carteServiceC;
    private CarteService carteservice = new CarteService();
    Scanner scan = new Scanner(System.in);
    
    public Partie recupererLaPartie(long idPartie){
            //return partiedao.rechercherParID(idPartie);
            return partiedaoCrud.findOne(idPartie);
    }
    
    // *****************          Déroulement des sort      ***********************
    public long choisirUnJoueur(Joueur joueurAct) {
        Partie partie = joueurAct.getPartie();
        
        for (Joueur joueurAdvers : partie.getJoueurs()) {
          if(!(joueurAdvers.getId().equals(joueurAct.getId()))){
            System.out.println(" L' Adversaire d'ID : " + joueurAdvers.getId()+ " à " +joueurAdvers.getCartes().size() + " cartes : " );
           }
        }
        System.out.println("  ");
        System.out.println(" choisi un joueur que tu veux récuperer cartes : ");
        Long idJrVict = null;
        try {
             idJrVict = Long.parseLong(scan.nextLine());
        }catch (Exception e) {
           return choisirUnJoueur(joueurAct);
        }
        
        return idJrVict;
    }
    public long choisirUneDeMesCartes(Joueur joueurAct) {
        
        List<Carte> listeCarteJrAct = (List<Carte>) carteDaoCrud.findOne(joueurAct.getId());
        for (Carte carte : listeCarteJrAct) {
            System.out.println("La liste de tes cartes est : " + carte.toString());
        }
         System.out.println(" choisi une carte (ID de la carte) parmi vos cartes ");
         long idcarteJrActChoisi = Long.parseLong(scan.nextLine());
         return idcarteJrActChoisi;  

    }
          
//   ********************  sort 1 *****************
    public void lancerSortLicorneEtCrapaud(Joueur joueurAct) {
        Partie partie = joueurAct.getPartie();
        for (Joueur joueurPartie : partie.getJoueurs()) {
            if (!(joueurPartie.getId().equals(joueurAct.getId()))) {
                carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurPartie.getId());
            }

        }
    }
//   ********************  sort 2 *****************    
    public void lancerSortLicorneEtMandragore(Joueur joueurAct) {
        long idJoueurVictime = choisirUnJoueur(joueurAct);
        Joueur joueurAdvers = jrDaoCrud.findByid(idJoueurVictime);

        if (joueurAdvers.getCartes().size() > 1) {
            int nbrCarteMoitie = (joueurAdvers.getCartes().size()) % 2;
            for (int i = 0; i <= nbrCarteMoitie; i++) {
                carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurAdvers.getId());
            }
        } else {
           carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurAdvers.getId());
        }
    }
     
 //   ********************  sort 3 *****************       
     public void lancerSortCrapaudLapisLazuli(Joueur joueurAct) {
        long idJoueurAdvers = choisirUnJoueur(joueurAct);
        Joueur joueurAdvers = jrDaoCrud.findByid(idJoueurAdvers);
        long idcarteJrAct = choisirUneDeMesCartes(joueurAct);
        Carte carteJrActChoisi = carteDaoCrud.findByid(idcarteJrAct);

//         si l'adversaire à une seule carte!!!!!!! ===>  Piocher 2 cartes !!!!!! + la carte de l'advers
        int nbrCartes = joueurAdvers.getCartes().size() > 3 ? 3 :joueurAdvers.getCartes().size();
        for (int i = 0; i < nbrCartes; i++) {
            carteservice.prendreUneCarteDunJoueur(idcarteJrAct,idJoueurAdvers);
        }

        joueurAdvers.getCartes().add(carteJrActChoisi);
        jrDaoCrud.save(joueurAdvers);
    }
     
      
  //   ********************  sort 4 *****************   
     public void lancerSortLapisLazuliChauveSouris(Joueur joueurAct) {
         Partie partie = joueurAct.getPartie();
         System.out.println(" les cartes des joueurs de votre partie sont : ");
         
         for (Joueur joueur : partie.getJoueurs()) {
             if (!(joueur.getId().equals(joueurAct.getId()))){
             System.out.println(" les cartes de:  " + joueur.getPseudo()+ " sont: " + carteDaoCrud.findOne(joueur.getId()));
             }
         }
     }

   //   ********************  sort 5 *****************      
     public void lancerSortMandragoreChauveSouris(Joueur joueurAct) {
            long idJrVictime = choisirUnJoueur(joueurAct);
            Joueur joueurVictime = jrDaoCrud.findByid(idJrVictime);
            joueurVictime.setEtatjoueur(Joueur.EtatJoueur.SOMMEIL_PROFON);
            jrDaoCrud.save(joueurVictime);
     }
     
     // *****************          Lancer un sort      ***********************
    
    public void lancerSort(long idCarte1, long idCarte2, long idJrAct, long idVictime){
    
        Carte carte1 = carteDaoCrud.findByid(idCarte1);
        Carte carte2 = carteDaoCrud.findByid(idCarte2);
        Joueur joueurAct = jrDaoCrud.findByid(idJrAct);
            
        if ((carte1.getTypeIngredient() == TypeIngredient.LICORNE && carte2.getTypeIngredient() == TypeIngredient.CRAPAUD)||(carte1.getTypeIngredient() == TypeIngredient.CRAPAUD && carte2.getTypeIngredient() == TypeIngredient.LICORNE)) {
            System.out.println(" le sort est INVISIBILITE et tu va gagner une carte de chaque adversaires");
            lancerSortLicorneEtCrapaud(joueurAct);
        } else {
            if ((carte1.getTypeIngredient() == TypeIngredient.LICORNE && carte2.getTypeIngredient() == TypeIngredient.MANDRAGORE)|| (carte1.getTypeIngredient() == TypeIngredient.MANDRAGORE && carte2.getTypeIngredient() == TypeIngredient.LICORNE)) {
                System.out.println(" le sort est PHILTRE D’AMOUR");
                lancerSortLicorneEtMandragore(joueurAct);
                
            } else {
                if ((carte1.getTypeIngredient() == TypeIngredient.CRAPAUD && carte2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI)|| (carte1.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI && carte2.getTypeIngredient() == TypeIngredient.CRAPAUD)) {
                    System.out.println(" le sort est HYPNOSE");
                    lancerSortCrapaudLapisLazuli(joueurAct);
                } else {
                    if ((carte1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && carte2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI)||(carte1.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI && carte2.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS)) {
                        System.out.println(" le sort est DIVINATION");
                        lancerSortLapisLazuliChauveSouris(joueurAct);
                    } else {
                        if ((carte1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && carte2.getTypeIngredient() == TypeIngredient.MANDRAGORE)||(carte1.getTypeIngredient() == TypeIngredient.MANDRAGORE && carte2.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS)) {
                            System.out.println(" le sort est SOMMEIL-PROFOND");
                            lancerSortMandragoreChauveSouris(joueurAct);
                        }
                    }
                }
            }
        }
    }
    
    // *****************          Déterminer Le Joueur Suivant      *******************************
    
    public void passeJoueurSuivant(long idpartie){
    
//        recuperer un joueur qui A_LA_MAIN
        Joueur joueurQuiAlaMain = jrDaoCrud.determineJoueurQuiALaMainDanspartie(idpartie);//joueurDao.determineJoueurQuiALaMainDansPArtie(idpartie);
            
//        determine si les autres joueurs ont perdu
//          et Passe le joueur à l'état GAGNé
            
        if(partiedaoCrud.exists(idpartie)){ //determineSiPlusQueUnJoueurDansPartie === partiedao.determineSiPlusQueUnJoueurDansPartie(idpartie)
                 joueurQuiAlaMain.setEtatjoueur(Joueur.EtatJoueur.GAGNEE);
                 jrDaoCrud.save(joueurQuiAlaMain);
                 return;
        }
//        La partie n'est pas terminée
//        recupere l'ordre Max des joueurs de la partie
         
        long ordreMax = partiedaoCrud.rechercheOrdreMaxJoueurPourPartieID(idpartie);
        
//        joueurEvalue = joueurQuiALaMain
        Joueur joueurEvalue = joueurQuiAlaMain;
         //boucle qui determine le joueur qui 'attrape' A LA MAIN
         while(true){
             //si joueurEvalue est le dernier joueur alors on évalue le joueur d'ordre 1
             if(joueurEvalue.getOrdre() >= ordreMax){
                 joueurEvalue = jrDaoCrud.findBypartieAndOrdre(idpartie, 1L);
             }  else {   //             recupere le joueur d'ordre suivant  (ordre+1)
                     joueurEvalue = jrDaoCrud.findBypartieAndOrdre(idpartie, joueurEvalue.getOrdre()+1);
                }
             
             //Return si tout les joueurs non éliminés était en sommeil profond (et qu'on la a juste réveillés)
             if(joueurEvalue.getId() == joueurQuiAlaMain.getId()){
                return;
             }
             
             // si joueur évaluer en sommeil profond en passe à PAS_LA_MAIN
              if (joueurEvalue.getEtatjoueur()== Joueur.EtatJoueur.SOMMEIL_PROFON){
                  joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
                  jrDaoCrud.save(joueurEvalue);
              } else{
              //si n'est pas en sommeil profond
              
              //si joueurEvalue pas la main alors c'est lui qui prend la main
               if (joueurEvalue.getEtatjoueur() == Joueur.EtatJoueur.NA_PAS_LA_MAIN){
                   joueurQuiAlaMain.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
                   jrDaoCrud.save(joueurQuiAlaMain);
                   
                   joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
                   jrDaoCrud.save(joueurEvalue);
                   return;
               } 
               // sinon : ETAT JOUEUR EVALUER  ==>  passe à PAS LA MAIN
               joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
               
              }
         }
            
    }

    public List<Partie> listerPartieNonDemarrees() {

        //return partiedao.listerPartieNonDemarrees();
        return partiedaoCrud.listerPartieNonDemarrees();
    }
    //********************recuperer le ID de la partie**********************

    //*****Partie******
    @Transactional
    public Partie creerNouvellePartie(String nom) {
        Partie p = new Partie();
        p.setNom(nom);
        //partiedao.ajouterPartie(p);
        partiedaoCrud.save(p);
        return p;
    }
    //******Démarrer Partie
    @Transactional
    public void demarrerPartie(long idPartie) {

        // Recuperer la partie par id
        Partie p = partiedaoCrud.findOne(idPartie);//partiedao.rechercherParID(idPartie);
       // Carte c = daoCarte.

        //Erreur  si pas au moins 2 joueurs dans la partie
        if (p.getJoueurs().size() < 2) {
            System.out.println("Nombre des joueurs doit étre supérieur à 2");
            return ;
        }

        //Passe le joueur d'ordre 1 à l'état A_LA_MAIN
        //Joueur ord = joueurDao.rechercheLeJoueurOrdre1(idPartie);
        Joueur ord = jrDaoCrud.findOneBypartieAndOrdre(idPartie, 1);
        ord.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
        jrDaoCrud.save(ord);

        //distribuer 7 cartes au hasard pour chaque joueur de la partie
        for (Joueur j : p.getJoueurs()) {
            for (int i = 0; i < 7; i++) {
                carteservice.distribuerCarte(j.getId());
            }
        }
    }

    public List<Partie> listerPartieDemarrees() {
        //return partiedao.listerPartieDemarrees();
        return partiedaoCrud.listerPartieDemarrees();
    }
    
     public void showEcranJeuPourChaqueJr(long idPartie, long idJoueurConsole) throws InterruptedException {
        Partie parte = partiedaoCrud.findOne(idPartie);//partiedao.rechercherParID(idPartie);
        System.out.println("****************************** Debut Ecran *************************");
        Joueur moi = jrDaoCrud.findByid(idJoueurConsole);
         for (Joueur joueur : parte.getJoueurs()) {
             if(joueur.getId() != idJoueurConsole){
                  System.out.println("idJoueur = "+joueur.getId()+" nom =" + joueur.getPseudo() 
                          +" Etat =" + joueur.getEtatjoueur() +" nbr Carte =" + moi.getCartes().size() );
             }
            }
         System.out.println("********Mes cartes********");
         System.out.println("moi :  idJoueur = "+moi.getId()+" nom =" + moi.getPseudo() 
                          +" Etat  =" + moi.getEtatjoueur() + " nbr Carte =" + moi.getCartes().size());
         int index = 1;
         for (Carte carte : moi.getCartes()) {
             System.out.println("carte " + index + " :  idCarte =" + carte.getId() + " nom Carte =" + carte.getTypeIngredient());
             index++;
         }
        
         System.out.println("****************************** Fin Ecran *************************");
     }
     
     public void ecranJeu(long idPartie, long joueurConsole) throws InterruptedException {
        long moi = joueurConsole;
        String choix;
        boolean siPartieDemarre = false;
        do {// BOUCLE DE JEU
            // Détermine qui a la main
            //Joueur joueurALaMain = joueurService.determineJoueurQuiALaMainDansPArtie(idPartie);
            Joueur joueurALaMain = jrDaoCrud.determineJoueurQuiALaMainDanspartie(idPartie);
            if (joueurALaMain != null){// la parte est bien demarrer car il y a un joueur qui a main 
             siPartieDemarre = true;
                        if( moi == joueurALaMain.getId() ) {//la partie demarre et jr de cosole a la main
                             showEcranJeuPourChaqueJr(idPartie, moi);
                            do {
                                System.out.println(" Si tu veux  *** Piocher une carte *** Tape [1]  ou Si tu veux *** Lancer un sort *** Tape [2]");
                                choix = scan.nextLine();
                                switch (choix) {
                                    case "1":
                                        //*********         piocherUneCarte    *********
                                        carteservice.distribuerCarte(joueurALaMain.getId());
                                        System.out.println("  \n une carte est rajouter à la liste de tes cartes \n");
                                        break;

                                    case "2":
                                        //*********         LancerUnSort   *********
                                        System.out.println(" La liste de tes cartes est : ");
                                        System.out.println("  " + carteDaoCrud.findOne(joueurALaMain.getId()) + " ");
                                        System.out.println("");
                                        System.out.println(" tu doit choisir un id de deux cartes de votre choix idcarte0 et idcarte1");
                                        int idcarte0 = scan.nextInt();
                                        int idcarte1 = scan.nextInt();

                                        lancerSort(idcarte0, idcarte1, joueurALaMain.getId(), idPartie);
                                        break;

                                    default:
                                        System.out.println("  !!!!!!!! Choix inconnu !!!!!! \n");
                                        break;
                                }
                            } while (!(choix.equals("1") || choix.equals("2")));
                            passeJoueurSuivant(idPartie);
                         }else {//si la partie est demarre et le joueur de console n pas la main 
                            showEcranJeuPourChaqueJr(idPartie, moi);
                        }
            }else {//auqun joueur a lamain 
                if(siPartieDemarre == false){
                     System.out.println("la partie n'est pas encore demarré");
                }else {
                    System.out.println("la partie est bien terminé");
                }
            }
           Thread.sleep(5000);
        } while (true);
    }

    
    
 
}
