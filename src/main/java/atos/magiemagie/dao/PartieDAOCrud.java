/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Partie;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface PartieDAOCrud extends CrudRepository<Partie, Long> {
    
    public Partie findByid(long idpartie);
    @Query("SELECT p "
            + "FROM Partie p "
            + " EXCEPT "
            + " SELECT p1 "
            + " FROM Partie p1 "
            + " JOIN p1.joueurs j"
            + " WHERE j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.GAGNEE OR j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.A_LA_MAIN")
    public List<Partie> listerPartieNonDemarrees();

    @Query("SELECT Max(j.ordre) "
            + "FROM Joueur j join j.partie p"
            + "                   WHERE p.id=?1")
    public long rechercheOrdreMaxJoueurPourPartieID(long partieID);

//    @Query(" select j from Joueur j "
//            + " join j.partie p "
//            + " where p.id=?1 "
//            + " except"
//            + " select j from Joueur j "
//            + " join j.partie p where p.id=?1 and j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.PERDU")
//    public boolean determineSiPlusQueUnJoueurDansPartie(long partieID);
    @Query(" SELECT p1 FROM Partie p1 JOIN p1.joueurs j"
            + "                   WHERE j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.GAGNEE OR j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.A_LA_MAIN")
    public List<Partie> listerPartieDemarrees();

}
