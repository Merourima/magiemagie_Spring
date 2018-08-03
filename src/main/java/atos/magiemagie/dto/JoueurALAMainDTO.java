/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dto;

/**
 *
 * @author Administrateur
 */

// Provisoire !!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class JoueurALAMainDTO {
    
    private long idJoueur;
    private String nomJoueur;

    public JoueurALAMainDTO() {
    }

    public long getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(long idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }
    
}
