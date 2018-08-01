/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrateur
 */
@Entity
public class Joueur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void getPseudo(String pseudo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
          public enum  EtatJoueur {
                 NA_PAS_LA_MAIN,
                 A_LA_MAIN,
                 SOMMEIL_PROFON,
                 PERDU,
                 GAGNEE
                 
          }
          
          @Column(nullable = false)
          private Long ordre;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrdre() {
        return ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }
          
          @Enumerated(EnumType.STRING)
          @Column(nullable = false)
          private EtatJoueur etatjoueur;
          
           @Column(unique = true)
           private String  pseudo;
           private String avatar  ;
           
           @Column(nullable = false)
           private long nbrPartieGagnees  ;
           
           @Column(nullable = false)
           private long nbrPartieJouees  ;
           
//           @ManyToOne
//           @JoinColumn
//           private Carte carte;
//           @OneToMany(mappedBy = "joueurs")
//           private List<Partie> partieActuelle = new ArrayList();
           
           
//           @OneToMany(mappedBy = "joueurProprietaire")
//           private List<Carte> cate = new ArrayList<>();
////           @ManyToOne
////           private Carte cartes;
//           
//           @ManyToOne
//           @JoinColumn
//           private Partie partie;
           
    @ManyToOne
    @JoinColumn
    private Partie partie;
    
    @OneToMany (mappedBy = "joueur")
    private List<Carte> cartes = new ArrayList<>();

    public EtatJoueur getEtatjoueur() {
        return etatjoueur;
    }

    public void setEtatjoueur(EtatJoueur etatjoueur) {
        this.etatjoueur = etatjoueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getNbrPartieGagnees() {
        return nbrPartieGagnees;
    }

    public void setNbrPartieGagnees(long nbrPartieGagnees) {
        this.nbrPartieGagnees = nbrPartieGagnees;
    }

    public long getNbrPartieJouees() {
        return nbrPartieJouees;
    }

    public void setNbrPartieJouees(long nbrPartieJouees) {
        this.nbrPartieJouees = nbrPartieJouees;
    }

  
            

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(" \n L'id = %d du joueur = %s ", id, pseudo);
        
    }
    
}
