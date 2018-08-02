/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.io.Serializable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur, Long> {

    public Joueur findOneByPseudo(String pseudo);

    public Joueur findByid(long IDjoueur);

    public Joueur findBypartieAndOrdre(long idpartie, long ordre);

    public Joueur findOneBypartieAndOrdre(long partieId, long ordre);

    @Query(" SELECT j"
            + " FROM Joueur j"
            + " JOIN j.partie p"
            + " WHERE j.etatjoueur=atos.magiemagie.entity.Joueur.EtatJoueur.A_LA_MAIN AND p.id=?1")
    public Joueur determineJoueurQuiALaMainDanspartie(long idPartie);

    @Query(" SELECT MAX(j.ordre)+1"
            + " FROM Joueur j"
            + " JOIN j.partie p"
            + " WHERE p.id=?1")
    public Long rechercheOrdreNouveauJoueurPourpartie(long partieId);

}
